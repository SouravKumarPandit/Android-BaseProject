package com.vrlocal.android.baseproject

import com.vrlocal.android.baseproject.di.DaggerApplication
import com.vrlocal.android.baseproject.di.component.DaggerAppComponent
import com.vrlocal.android.baseproject.util.CLExceptionHandler
import com.vrlocal.android.baseproject.util.CrashReportingTree
import dagger.android.AndroidInjector
import timber.log.Timber

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder().application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())
        /* val fabric = Fabric.Builder(this)
             .kits(Crashlytics())
             .debuggable(BuildConfig.DEBUG) // Enables Crashlytics debugger
             .build()
         Fabric.with(fabric)*/
        Thread.setDefaultUncaughtExceptionHandler(CLExceptionHandler(this));
    }
}