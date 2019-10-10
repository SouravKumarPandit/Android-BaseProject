package com.vrlocal.android.baseproject.util

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

/** A tree which logs important information for crash reporting. */
class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) return

        //FakeCrashLibrary.log(priority, tag, message)

        if (t != null) {
            if (priority == Log.ERROR) {
//                Crashlytics.getInstance().crash()
//                FakeCrashLibrary.logError(t)
            } else if (priority == Log.WARN) {
                //FakeCrashLibrary.logWarning(t)
            }
        }
    }
}