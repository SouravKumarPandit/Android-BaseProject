package com.vrlocal.android.baseproject.util

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle




class AppExceptionHandler(val systemHandler: Thread.UncaughtExceptionHandler,
                          val crashlyticsHandler: Thread.UncaughtExceptionHandler,
                          application: Application
) : Thread.UncaughtExceptionHandler {

    private var lastStartedActivity: Activity? = null

    private var startCount = 0

    init {
        application.registerActivityLifecycleCallbacks(
          object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
                // empty
//                super.onActivityPostPaused(activity)

            }

            override fun onActivityResumed(activity: Activity?) {
                // empty
            }

            override fun onActivityStarted(activity: Activity?) {
                startCount++
                lastStartedActivity = activity
            }

            override fun onActivityDestroyed(activity: Activity?) {
                // empty

            }

            override fun onActivitySaveInstanceState(activity: Activity?, 
                                                     outState: Bundle?) {
                // empty
            }

            override fun onActivityStopped(activity: Activity?) {
                startCount--
                if (startCount <= 0) {
                    lastStartedActivity = null
                }
            }

            override fun onActivityCreated(activity: Activity?, 
                                           savedInstanceState: Bundle?) {
                // empty
            }
        })
    }


    override fun uncaughtException(t: Thread?, e: Throwable) {
//        Log.e(e)

        lastStartedActivity?.let { activity ->
      /*      emailReport(activity,"",crashlyticsHandler)
            if (crashlyticsHandler != null) crashlyticsHandler.uncaughtException(t, e); //Delegates to Android's error handling
            else {
                System.exit(2)
                return
            };*/


            val isRestarted = activity.intent
                                  .getBooleanExtra(RESTARTED, false)
                                  
            val lastException = activity.intent
                                  .getSerializableExtra(LAST_EXCEPTION) as Throwable?


            if (!isRestarted || !isSameException(e, lastException)) {
                killThisProcess {
                    // signal exception to be logged by crashlytics
                    crashlyticsHandler.uncaughtException(t, e) 

                    val intent = activity.intent
                            .putExtra(RESTARTED, true)
                            .putExtra(LAST_EXCEPTION, e)
                            .addFlags(
                                Intent.FLAG_ACTIVITY_NEW_TASK or
                                      Intent.FLAG_ACTIVITY_CLEAR_TASK)

                    with(activity) {
                      finish()
                      startActivity(intent)
                    }
                }
            } else {
//                Log.d("The system exception handler will handle the caught exception.")
                killThisProcess { systemHandler.uncaughtException(t, e) }
            }
        } ?: killThisProcess {
            crashlyticsHandler.uncaughtException(t, e)
            systemHandler.uncaughtException(t, e)
        }
    }

    /**
     * Not bullet-proof, but it works well.
     */
    private fun isSameException(originalException: Throwable, 
                                lastException: Throwable?): Boolean {
        if (lastException == null) return false

        return originalException.javaClass == lastException.javaClass &&
                originalException.stackTrace[0] == originalException.stackTrace[0] &&
                originalException.message == lastException.message
    }

    private fun killThisProcess(action: () -> Unit = {}) {
        action()

        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(10)
    }

    companion object {
        private const val RESTARTED = "appExceptionHandler_restarted"
        private const val LAST_EXCEPTION = "appExceptionHandler_lastException"
    }

/*
    private fun emailReport(mContext:Activity,
        report: String,
        oldHandler: Thread.UncaughtExceptionHandler?
    ) {
        AlertDialog.Builder(mContext)
            .setTitle("Crash Report")
            .setMessage("Send crash to user")
            .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:support@example.com")
                intent.type = "message/rfc822"
                intent.putExtra(Intent.EXTRA_SUBJECT, "Crash Report")
                intent.putExtra(Intent.EXTRA_TEXT, report)
//                val mailer = Intent.createChooser(intent, null)
//                startActivity(mailer)
            })
            .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                if (oldHandler != null) {
                    //Delegates to Android's error handling
//                    oldHandler.uncaughtException(paramThread, paramThrowable)
                } else {
                    // Force to close the app now after handling the crash bug
                    System.exit(2)
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }*/
}