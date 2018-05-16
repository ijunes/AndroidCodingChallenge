package com.ijunes.androidchallenge.di

import android.app.Application
import android.content.Context
import com.ijunes.androidchallenge.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import io.reactivex.disposables.CompositeDisposable

/**
 * Core Application Module
 * Created by jkang on 5/15/18.
 */

@Module(includes = [(AndroidInjectionModule::class), (NetworkModule::class)])
class MVPAppModule {

    @Provides
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}