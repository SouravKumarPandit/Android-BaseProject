package com.vrlocal.android.baseproject

import com.facebook.stetho.Stetho
import com.vrlocal.android.baseproject.di.component.DaggerAppComponent
import com.vrlocal.android.baseproject.util.CrashReportingTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class BaseApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder().application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())

    }

//    override fun activityInjector() = dispatchingAndroidInjector
}