package com.ijunes.androidchallenge.ui.main.interactor

import com.ijunes.androidchallenge.dto.ReviewResponse
import com.ijunes.androidchallenge.ui.base.interactor.MVPInteractor
import io.reactivex.Single

/**
 * Created by jkang on 08/01/18.
 */
interface MainMVPInteractor : MVPInteractor {

    fun getReviews(): Single<ReviewResponse>

}