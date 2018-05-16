package com.ijunes.androidchallenge.ui.main

import com.ijunes.androidchallenge.ui.main.interactor.MainInteractor
import com.ijunes.androidchallenge.ui.main.interactor.MainMVPInteractor
import com.ijunes.androidchallenge.ui.main.presenter.MainMVPPresenter
import com.ijunes.androidchallenge.ui.main.presenter.MainPresenter
import com.ijunes.androidchallenge.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides

/**
 * Created by jkang on 05/15/18.
 */
@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter

}