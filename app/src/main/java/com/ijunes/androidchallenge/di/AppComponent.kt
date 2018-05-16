package com.ijunes.androidchallenge.di

import android.app.Application
import com.ijunes.androidchallenge.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by jkang on 5/15/18.
 */

@Component(modules = arrayOf(AndroidInjectionModule::class, MVPAppModule::class, ActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    @Singleton
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}