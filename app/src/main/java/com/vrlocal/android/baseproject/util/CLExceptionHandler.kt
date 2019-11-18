package com.vrlocal.android.baseproject.util

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Looper
import android.os.Process
import com.crashlytics.android.Crashlytics
import com.vrlocal.android.baseproject.BuildConfig
import com.vrlocal.android.baseproject.ui.base.CrashHandlerActivity
import io.fabric.sdk.android.Fabric
import java.io.PrintWriter
import java.io.StringWriter

class CLExceptionHandler(private val myContext: Context) :
    Thread.UncaughtExceptionHandler {
    private val LINE_SEPARATOR = "\n"
    override fun uncaughtException(
        thread: Thread,
        exception: Throwable
    ) {
        val stackTrace = StringWriter()
        exception.printStackTrace(PrintWriter(stackTrace))
        val errorReport = StringBuilder()
        errorReport.append("<br><h1>our team will try to resolve this please report issue </h1><br><br>")
        errorReport.append("<br><h2> CAUSE OF ERROR </h2>")
        errorReport.append(stackTrace.toString())
        errorReport.append("<br><h2> DEVICE INFORMATION </h2>")
        errorReport.append("Brand: ")
        errorReport.append(Build.BRAND)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Device: ")
        errorReport.append(Build.DEVICE)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Model: ")
        errorReport.append(Build.MODEL)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Id: ")
        errorReport.append(Build.ID)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Product: ")
        errorReport.append(Build.PRODUCT)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("<br><h2> FIRMWARE </h2>")
        errorReport.append("SDK: ")
        errorReport.append(Build.VERSION.SDK_INT)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Release: ")
        errorReport.append(Build.VERSION.RELEASE)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Incremental: ")
        errorReport.append(Build.VERSION.INCREMENTAL)
        errorReport.append("<br>")
        val fabric = Fabric.Builder(myContext)
            .kits(Crashlytics())
            .debuggable(BuildConfig.DEBUG) // Enables Crashlytics debugger
            .build()
        Fabric.with(fabric)
        val intent = Intent(myContext, CrashHandlerActivity::class.java)
        intent.action = "com.vrlocal.application.SEND_LOG" // see step 5.
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("error", errorReport.toString())
        myContext.startActivity(intent)
        Process.killProcess(Process.myPid())
        System.exit(10)
    }

    /*public void handleUncaughtException(final Thread thread, final Throwable e) {
	    e.printStackTrace(); // not all Android versions will print the stack trace automatically

	    if(isUIThread()) {
	    	uncaughtException(thread,e);
	    }else{  //handle non UI thread throw uncaught exception

	        new Handler(Looper.getMainLooper()).post(new Runnable() {
	            @Override
	            public void run() {
	            	uncaughtException(thread,e);
	            }
	        });
	    }
	}*/
    val isUIThread: Boolean
        get() = Looper.getMainLooper().thread === Thread.currentThread()

}