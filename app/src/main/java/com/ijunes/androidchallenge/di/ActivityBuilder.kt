package com.ijunes.androidchallenge.di

import com.ijunes.androidchallenge.ui.main.view.MainActivity
import com.ijunes.androidchallenge.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by jkang on 5/15/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MVPAppModule::class), (MainActivityModule::class)])
    @Singleton
    abstract fun bindMainActivity(): MainActivity

}