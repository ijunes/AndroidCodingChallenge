package com.ijunes.androidchallenge.net

import com.ijunes.androidchallenge.dto.ReviewResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Interface for calling NY Times Movie Reviews
 * Created by jkang on 5/15/18.
 */
interface ReviewsApi {

    @GET("svc/movies/v2/reviews/dvd-picks.json")
    fun getAllReviews(@Query("order") orderBy: String = "by-date",
                      @Query("api-key") apiKey: String = "b75da00e12d54774a2d362adddcc9bef"): Single<ReviewResponse>


    @GET("svc/movies/v2/reviews/dvd-picks.json")
    fun getAllReviewsRepo(@Query("order") orderBy: String = "by-date",
                      @Query("api-key") apiKey: String = "b75da00e12d54774a2d362adddcc9bef"): Call<ReviewResponse>
}