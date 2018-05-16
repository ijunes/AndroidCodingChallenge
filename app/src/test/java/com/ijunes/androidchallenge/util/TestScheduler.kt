package com.ijunes.androidchallenge.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.TestScheduler

/**
 * Created by jkang on 5/16/18.
 */
class TestSchedulerProvider() : SchedulerProvider() {

    val testScheduler: TestScheduler = TestScheduler()

    override fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T> = ObservableTransformer { upstream ->
        upstream.subscribeOn(getIOThreadScheduler())
                .observeOn(getMainThreadScheduler())
    }

    override fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T> = SingleTransformer { upstream ->
        upstream.subscribeOn(getIOThreadScheduler())
                .observeOn(getMainThreadScheduler())
    }


    override fun ioToMainCompletableScheduler(): CompletableTransformer = CompletableTransformer { upstream ->
        upstream.subscribeOn(getIOThreadScheduler())
                .observeOn(getMainThreadScheduler())
    }


    override fun <T> ioToMainFlowableScheduler(): FlowableTransformer<T, T> = FlowableTransformer { upstream ->
        upstream.subscribeOn(getIOThreadScheduler())
                .observeOn(getMainThreadScheduler())
    }


    override fun <T> ioToMainMaybeScheduler(): MaybeTransformer<T, T> = MaybeTransformer { upstream ->
        upstream.subscribeOn(getIOThreadScheduler())
                .observeOn(getMainThreadScheduler())
    }

    override fun getIOThreadScheduler() = testScheduler

    override fun getMainThreadScheduler() = testScheduler


}