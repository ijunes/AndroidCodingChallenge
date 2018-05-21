package com.ijunes.androidchallenge.data

import android.arch.lifecycle.LiveData

import com.ijunes.androidchallenge.data.local.dao.ReviewDao
import com.ijunes.androidchallenge.data.local.dao.entity.ReviewEntity
import com.ijunes.androidchallenge.dto.ReviewResponse
import com.ijunes.androidchallenge.net.ReviewsApi

import javax.inject.Inject

import io.reactivex.annotations.NonNull
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by jkang on 5/20/18.
 */
class ReviewRepository @Inject
constructor(private val reviewDao: ReviewDao, retrofit: Retrofit) {
    private val movieDBService: ReviewsApi

    init {
        this.movieDBService = retrofit.create(ReviewsApi::class.java)
    }

    fun loadLatestReviews(): LiveData<Resource<List<ReviewEntity>>> {
        return object : NetworkBoundResource<List<ReviewEntity>, ReviewResponse>() {

            protected override fun saveCallResult(@NonNull item: ReviewResponse?) {
                reviewDao.saveReviews(item!!.results)
            }

            @NonNull
            override fun loadFromDb(): LiveData<List<ReviewEntity>> {
                return reviewDao.loadReviews()
            }

            @NonNull
            override fun createCall(): Call<ReviewResponse> {
                return movieDBService.getAllReviewsRepo()
            }
        }.asLiveData
    }

    fun getReview(updateDate: String): LiveData<ReviewEntity> {
        return reviewDao.getReview(updateDate)
    }
}