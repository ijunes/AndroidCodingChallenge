package com.ijunes.androidchallenge

import android.app.Application
import dagger.android.HasActivityInjector
import android.app.Activity
import com.ijunes.androidchallenge.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject



/**
 * Created by jkang on 5/15/18.
 */
class App : Application(), HasActivityInjector{
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}