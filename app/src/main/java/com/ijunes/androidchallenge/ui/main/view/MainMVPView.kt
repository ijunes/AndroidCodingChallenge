package com.ijunes.androidchallenge.ui.main.view

import com.ijunes.androidchallenge.dto.ReviewResponse
import com.ijunes.androidchallenge.ui.base.view.MVPView

/**
 * Created by jkang on 5/15/18.
 */
interface MainMVPView : MVPView {

    fun loadReviews(reviewResponse: ReviewResponse)
    fun addMoreReviews(reviewResponse: ReviewResponse)
    fun showError()
    fun hideError()
}