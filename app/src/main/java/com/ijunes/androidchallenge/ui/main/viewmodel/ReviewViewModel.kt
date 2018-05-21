package com.ijunes.androidchallenge.ui.main.viewmodel

import android.arch.lifecycle.ViewModel
import com.ijunes.androidchallenge.data.ReviewRepository
import retrofit2.Retrofit
import javax.inject.Inject
import android.arch.lifecycle.LiveData
import com.ijunes.androidchallenge.data.Resource
import com.ijunes.androidchallenge.data.local.dao.entity.ReviewEntity


/**
 * Created by jkang on 5/20/18.
 */
class ReviewViewModel @Inject internal constructor(reviewRepository: ReviewRepository): ViewModel(){
    private val latestReviews: LiveData<Resource<List<ReviewEntity>>> = reviewRepository.loadLatestReviews()

    fun getPopularMovies(): LiveData<Resource<List<ReviewEntity>>> {
        return latestReviews
    }
}