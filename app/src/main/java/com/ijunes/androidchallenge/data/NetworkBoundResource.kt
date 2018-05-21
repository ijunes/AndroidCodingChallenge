package com.ijunes.androidchallenge.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import android.support.annotation.MainThread
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.annotation.WorkerThread


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
internal constructor() {
    private val result = MediatorLiveData<Resource<ResultType>>()

    val asLiveData: LiveData<Resource<ResultType>>
        get() = result

    init {
        result.setValue(Resource.loading<ResultType>(null))
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            data?.let {
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource)
                } else {
                    result.addSource(dbSource) { newData -> result.setValue(Resource.success(newData!!)) }
                }
            } ?:  result.addSource(dbSource) { newData -> result.setValue(Resource.success(newData!!)) }


        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource) { newData -> result.setValue(Resource.loading(newData)) }
        createCall().enqueue(object : Callback<RequestType> {
            override fun onResponse(call: Call<RequestType>, response: Response<RequestType>) {
                result.removeSource(dbSource)
                saveResultAndReInit(response.body())
            }

            override fun onFailure(call: Call<RequestType>, t: Throwable) {
                onFetchFailed()
                result.removeSource(dbSource)
                result.addSource(dbSource) { newData -> result.setValue(Resource.error(t.message, newData)) }
            }
        })
    }

    @MainThread
    private fun saveResultAndReInit(response: RequestType?) {
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                saveCallResult(response)
                return null
            }

            override fun onPostExecute(aVoid: Void) {
                result.addSource(loadFromDb()) { newData -> result.setValue(Resource.success(newData!!)) }
            }
        }.execute()
    }

    @WorkerThread
    protected abstract fun saveCallResult(@NonNull item: RequestType?)

    @MainThread
    protected fun shouldFetch(@Nullable data: ResultType): Boolean {
        return true
    }

    @NonNull
    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @NonNull
    @MainThread
    protected abstract fun createCall(): Call<RequestType>

    @MainThread
    protected fun onFetchFailed() {
    }
}