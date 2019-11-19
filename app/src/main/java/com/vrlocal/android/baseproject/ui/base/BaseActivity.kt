package com.vrlocal.android.baseproject.ui.base

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.crashlytics.android.Crashlytics
import com.google.android.material.snackbar.Snackbar
import com.vrlocal.android.baseproject.ui.common.makeStatusBarTransparent
import com.vrlocal.android.baseproject.ui.screens.home.HomeActivity
import com.vrlocal.android.baseproject.ui.screens.login.LoginActivity
import com.vrlocal.android.baseproject.util.viewutils.ViewUtils
import com.vrlocal.uicontrolmodule.app.VPermissionUtils
import dagger.android.support.DaggerAppCompatActivity
import io.fabric.sdk.android.Fabric


@SuppressLint("Registered")
abstract class BaseActivity<B : ViewDataBinding, T : BaseViewModel<*>> :
    DaggerAppCompatActivity(), IView {
    private var doubleBackToExitPressedOnce = false
    private var darkStatusBar = false
    open lateinit var bindingData: B
    open lateinit var viewModel: T
    private lateinit var mProgressBar: ProgressBar
    private lateinit var frameLayout: FrameLayout


//    @Inject
//    public lateinit var sessionManager: SessionManager

    protected fun bindView(layoutId: Int) {
        bindingData = DataBindingUtil.setContentView(this, layoutId)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
//        VPermissionUtils.requestAllPermissions(this)
//        subscribeObservers()
        if (darkStatusBar)
            makeStatusBarTransparent()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


    }

    /**warning:
     * The root of the activity container must be of type ConstraintLayout
     *  DO NOT CHANGE THIS CONSISTENCY
     * */
    override fun setContentView(layoutResID: Int) {

        val constraintLayout: ConstraintLayout =
            LayoutInflater.from(this).inflate(layoutResID, null) as ConstraintLayout

        val initFrame = initFrame()
        constraintLayout.addView(initFrame)
        super.setContentView(constraintLayout)
        removeStatusBarPadding(constraintLayout)

    }

    override fun setContentView(view: View?) {
        val constraintLayout = getConstraintLayout()
        constraintLayout.addView(view, 0)
        constraintLayout.addView(frameLayout, 1)
        constraintLayout.fitsSystemWindows = true
        super.setContentView(constraintLayout)
        removeStatusBarPadding(constraintLayout)

    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        val constraintLayout = getConstraintLayout()
        constraintLayout.addView(view, 0)
        constraintLayout.addView(frameLayout, 1)
        constraintLayout.fitsSystemWindows = true
        super.setContentView(view, params)
        removeStatusBarPadding(constraintLayout)


    }


    private fun removeStatusBarPadding(constraintLayout: ConstraintLayout) {

        ViewCompat.setOnApplyWindowInsetsListener(constraintLayout) { _, insets ->
            insets.consumeSystemWindowInsets()
        }
    }

    fun getProgressBar(): ProgressBar {
        return mProgressBar;
    }

    override fun onDestroy() {
        viewModel.detachView()
        super.onDestroy()
    }


    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()


    private fun showProgressBar(visibility: Boolean) {
        mProgressBar.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
        if (visibility) frameLayout.setBackgroundColor(0x3CFFFFFF) else frameLayout.background =
            null
        frameLayout.isClickable = visibility
    }

    override fun hideProgressBar() {
        showProgressBar(false)
    }

    override fun showProgressBar() {
        showProgressBar(true)

    }

    override fun onBackPressed() {

        if (mProgressBar.isVisible) {
            hideProgressBar()
            return;
        }

        if (this is HomeActivity) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }

            this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
            showSnackBar("press BACK again to exit", 0)

            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)


        } else
            super.onBackPressed()
    }


    private fun subscribeObservers() {

        /* sessionManager.getLogeInUser().observe(this, Observer { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    VResult.Status.LOADING -> {
                    }
                    VResult.Status.AUTHENTICATED -> {
                    }
                    VResult.Status.ERROR -> {
                    }

                    VResult.Status.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                    VResult.Status.SUCCESS -> TODO()
                }
            }
        })*/
    }

    private fun navLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun getConstraintLayout(): ConstraintLayout {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.layoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        constraintLayout.id = com.vrlocal.android.baseproject.R.id.base_constrainetlayout

        initFrame();

        return constraintLayout
    }

    private fun initFrame(): FrameLayout {
        frameLayout = FrameLayout(this)
        val frameParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        frameParam.gravity = Gravity.CENTER
        frameLayout.layoutParams =
            frameParam
        frameLayout.id = com.vrlocal.android.baseproject.R.id.base_framelayout

        mProgressBar = ProgressBar(this)
        mProgressBar.visibility = View.GONE
        mProgressBar.elevation = 8f
        val pbParam: FrameLayout.LayoutParams = FrameLayout.LayoutParams(45.px, 45.px)
        pbParam.gravity = Gravity.CENTER
        mProgressBar.layoutParams = pbParam
        frameLayout.addView(mProgressBar)
        return frameLayout

    }

    @TargetApi(23)
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {

        if (requestCode == VPermissionUtils.PERMISSION_ALL) {
            VPermissionUtils.handlePermissionResult(
                this@BaseActivity,
                requestCode,
                permissions,
                grantResults
            )
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }


    @TargetApi(23)
    public override
            /**
             * @param iRequestCode calling frag
             * @param iStatus status
             * @param data intent included bundle data
             */

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VPermissionUtils.OVER_LAY_PERMISSION) {
            VPermissionUtils.handleOverlayPermissionResult(this@BaseActivity)
        } else {
            ViewUtils.hideKeyboard(this)

        }

    }

    protected fun setDarkStatusBar(isDark: Boolean) {
        this.darkStatusBar = isDark
    }

    override fun onResponse(responseObject: Any?) {

    }

    override fun showToast(message: String) {
        Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show()
    }

    override fun showSnackBar(message: String, statusColor: Int) {

        val snackBar = Snackbar.make(
            frameLayout, message,
            Snackbar.LENGTH_LONG
        )
        val viewGroup = snackBar.view as ViewGroup
        viewGroup.background =
            ViewUtils.getGradientDrawable(Color.parseColor("#72A325"), Color.parseColor("#91C22B"))
        val textView = (viewGroup.getChildAt(0) as ViewGroup).getChildAt(0) as TextView
//        textView.isSingleLine=true
        textView.setTextColor(
            Color.WHITE
        )
//        snackBar.setAction("OK") {
//            snackBar.dismiss()
//        }
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.show()

    }

    override fun showError(error: String) {

        val snackBar = Snackbar.make(
            frameLayout,
            "Error : $error",
            Snackbar.LENGTH_INDEFINITE
        )
        val viewGroup = snackBar.view as ViewGroup

        viewGroup.background =
            ViewUtils.getGradientDrawable(Color.parseColor("#FEA6B1"), Color.parseColor("#EF718D"))
        val textView = (viewGroup.getChildAt(0) as ViewGroup).getChildAt(0) as TextView
//        textView.isSingleLine=true
        textView.setTextColor(
            Color.WHITE
        )
        snackBar.setAction("OK") {
            snackBar.dismiss()
        }
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.show()

    }


}