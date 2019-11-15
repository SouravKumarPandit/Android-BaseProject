package com.vrlocal.android.baseproject.util.viewutils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.inputmethod.InputMethodManager





object  ViewUtils{
    fun getGradientDrawable(color1: Int, color2: Int):Drawable{
        val gradDrawable = GradientDrawable()
        gradDrawable.colors= intArrayOf(color1,color2)
//        gradDrawable.cornerRadius=8f
//        gradDrawable.setPadding(5,5,5,5)
//        gradDrawable.gradientRadius=0.5f
//        gradDrawable.setColor()
//        gradDrawable.setColor(Color.parseColor("#EF718D"))
        return gradDrawable

    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val clView = activity.currentFocus
        if (clView != null)
            imm.hideSoftInputFromWindow(clView.windowToken, 0)
        else
            imm.hideSoftInputFromWindow(activity.window?.decorView?.windowToken, 0)
    }

    fun hideDialogKeyboard(clDialog: Dialog) {
        val imm =
            clDialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(clDialog.window?.decorView?.windowToken, 0)

    }


}