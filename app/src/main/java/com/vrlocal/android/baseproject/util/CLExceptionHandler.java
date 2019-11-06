package com.vrlocal.android.baseproject.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;

import com.crashlytics.android.Crashlytics;
import com.vrlocal.android.baseproject.BuildConfig;
import com.vrlocal.android.baseproject.ui.base.CrashHandlerActivity;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.fabric.sdk.android.Fabric;

public class CLExceptionHandler implements
        java.lang.Thread.UncaughtExceptionHandler
{
    private final Context myContext;
    private final String LINE_SEPARATOR = "\n";

    public CLExceptionHandler(Context context)
    {
        myContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable exception)
    {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder errorReport = new StringBuilder();
        errorReport.append("<br><h1>We are sorry our team will try to resolve this please report issue to us</h1><br><br>");
        errorReport.append("<br><h2> CAUSE OF ERROR </h2>");
        errorReport.append(stackTrace.toString());
        errorReport.append("<br><h2> DEVICE INFORMATION </h2>");
        errorReport.append("Brand: ");
        errorReport.append(Build.BRAND);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Device: ");
        errorReport.append(Build.DEVICE);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Model: ");
        errorReport.append(Build.MODEL);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Id: ");
        errorReport.append(Build.ID);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Product: ");
        errorReport.append(Build.PRODUCT);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("<br><h2> FIRMWARE </h2>");
        errorReport.append("SDK: ");
        errorReport.append(Build.VERSION.SDK);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Release: ");
        errorReport.append(Build.VERSION.RELEASE);
        errorReport.append(LINE_SEPARATOR);
        errorReport.append("Incremental: ");
        errorReport.append(Build.VERSION.INCREMENTAL);
        errorReport.append("<br>");
        Fabric fabric = new Fabric.Builder(myContext)
                .kits(new Crashlytics())
                .debuggable(BuildConfig.DEBUG) // Enables Crashlytics debugger
                .build();
        Fabric.with(fabric);

        Intent intent = new Intent(myContext, CrashHandlerActivity.class);
        intent.setAction("com.vrlocal.application.SEND_LOG"); // see step 5.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("error", errorReport.toString());
        myContext.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    public boolean isUIThread()
    {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
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

}