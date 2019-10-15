package com.vrlocal.android.baseproject.util

import androidx.core.app.ActivityCompat
import com.crashlytics.android.Crashlytics
import javax.inject.Inject

class CrashlyticsHandler(private val defaultUncaughtExceptionHandler: Thread.UncaughtExceptionHandler) : Thread.UncaughtExceptionHandler {

    val runtime by lazy { Runtime.getRuntime() }
    @Inject
    lateinit var context: ActivityCompat

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        // Our custom logic goes here. For example calculate the memory heap
        val maxMemory = runtime.maxMemory()
        val freeMemory = runtime.freeMemory()
        val usedMemory = runtime.totalMemory() - freeMemory
        val availableMemory = maxMemory - usedMemory

        //Set values to Crashlytics
        Crashlytics.setLong("used_memory", usedMemory)
        Crashlytics.setLong("available_memory", availableMemory)
//        val dialog=AlertDialog(context)
//        val reportDialog:DialogCompat= AlertDialog()



        // This will make Crashlytics do its job
        defaultUncaughtExceptionHandler.uncaughtException(thread, ex)
    }
}