package com.vrlocal.android.baseproject

import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.vrlocal.android.baseproject.di.component.DaggerAppComponent
import com.vrlocal.android.baseproject.util.AppExceptionHandler
import com.vrlocal.android.baseproject.util.CrashReportingTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder().application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())

        val fabric = Fabric.Builder(this)
            .kits(Crashlytics())
            .debuggable(BuildConfig.DEBUG) // Enables Crashlytics debugger
            .build()
        Fabric.with(fabric)

//        val defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
//        Thread.setDefaultUncaughtExceptionHandler(CrashlyticsHandler(defaultUncaughtExceptionHandler!!))
        val fabricExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        val systemHandler = Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler(systemHandler!!, fabricExceptionHandler!!, this))

    }

//    override fun activityInjector() = dispatchingAndroidInjector
}