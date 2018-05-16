package com.ijunes.androidchallenge.ui.main.interactor

import com.ijunes.androidchallenge.dto.ReviewResponse
import com.ijunes.androidchallenge.net.ReviewsApi
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Interactor instance for MainActivity
 * Created by jkang on 5/15/18.
 */
class MainInteractor @Inject internal constructor(retrofit: Retrofit) : MainMVPInteractor {

    // Retrofit interface
    var api: ReviewsApi = retrofit.create(ReviewsApi::class.java)

    override fun getReviews(): Single<ReviewResponse> {
        return api.getAllReviews()
    }

}
