package com.ijunes.androidchallenge.ui.base.presenter

import com.ijunes.androidchallenge.ui.base.interactor.MVPInteractor
import com.ijunes.androidchallenge.ui.base.view.MVPView

/**
 * Created by jkang on 5/15/18.
 */
interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}