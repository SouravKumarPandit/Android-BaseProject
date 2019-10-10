package com.vrlocal.android.baseproject.ui.base

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
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
     Snackbar.make(fragmentView,"Error : $error",Snackbar.LENGTH_SHORT).show()

    }

    override fun onResponse(responseObject: Any?) {
     Snackbar.make(fragmentView,"Response",Snackbar.LENGTH_SHORT).show()


    }

    override fun showToast(message: String) {

        Snackbar.make(fragmentView,"Error : $message",Snackbar.LENGTH_SHORT).show()
    }

    override fun showSnackBar(message: String, statusColor: Int) {
        view?.let { Snackbar.make(it,message,Snackbar.LENGTH_SHORT).show() }

        Snackbar.make(fragmentView, message,Snackbar.LENGTH_SHORT).show()

    }


}