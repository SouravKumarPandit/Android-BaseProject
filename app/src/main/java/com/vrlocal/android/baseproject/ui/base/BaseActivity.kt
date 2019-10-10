package com.vrlocal.android.baseproject.ui.base

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import com.vrlocal.android.baseproject.util.viewutils.ViewUtils
import dagger.android.support.DaggerAppCompatActivity


@SuppressLint("Registered")
open class BaseActivity<B : ViewDataBinding, T : BaseViewModel<*>> :
    DaggerAppCompatActivity(), IView {

    open lateinit var dataBinding: B
    open lateinit var baseViewModel: T

    private lateinit var mProgressBar: ProgressBar
    private lateinit var frameLayout: FrameLayout
    protected fun bindView(layoutId: Int) {
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun setContentView(layoutResID: Int) {

        val constraintLayout: ConstraintLayout =
            LayoutInflater.from(this).inflate(layoutResID, null) as ConstraintLayout

        val initFrame = initFrame()
        constraintLayout.addView(initFrame)
        super.setContentView(constraintLayout)
//        showProgressBar()


    }

    override fun setContentView(view: View?) {
        val constraintLayout = getConstraintLayout()
        constraintLayout.addView(view, 0)
        constraintLayout.addView(frameLayout, 1)
        super.setContentView(view)
//        showProgressBar()
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        val constraintLayout = getConstraintLayout()
        constraintLayout.addView(view, 0)
        constraintLayout.addView(frameLayout, 1)
        super.setContentView(view, params)
        showProgressBar()

    }

    override fun onDestroy() {
        baseViewModel.detachView()
        super.onDestroy()
    }


    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    private val Int.px: Int
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
            /*todo close network connection here*/
            hideProgressBar()
            return;
        }

        super.onBackPressed()
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


    override fun onResponse(responseObject: Any?) {
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message+"",Toast.LENGTH_SHORT).show()
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