package com.ijunes.androidchallenge.ui.main.presenter

import com.ijunes.androidchallenge.ui.base.presenter.MVPPresenter
import com.ijunes.androidchallenge.ui.main.interactor.MainMVPInteractor
import com.ijunes.androidchallenge.ui.main.view.MainMVPView

/**
 * Interface definition for accessible methods in MainPresenter
 * Created by jkang on 5/15/18.
 */
interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {

    fun fetchAllReviews()
    fun retry()
}