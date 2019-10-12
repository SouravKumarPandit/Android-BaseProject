package com.vrlocal.android.baseproject.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.vrlocal.android.baseproject.util.viewutils.ViewUtils
import dagger.android.support.DaggerFragment

open class BaseFragment :DaggerFragment() ,IView{


    lateinit var fragmentView:View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentView=view

    }

    override fun showProgressBar() {
        (activity as BaseActivity<*, *>).showProgressBar()//To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        (activity as BaseActivity<*, *>).hideProgressBar()//To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        val snackBar = Snackbar.make(
            fragmentView,
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

    override fun onResponse(responseObject: Any?) {
        showSnackBar(responseObject.toString(),0)
    }

    override fun showToast(message: String) {

        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
//        Snackbar.make(fragmentView,"Error : $message",Snackbar.LENGTH_SHORT).show()
    }

    override fun showSnackBar(message: String, statusColor: Int) {


        val snackBar = Snackbar.make(
            fragmentView, message,
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


}