package com.vrlocal.android.baseproject.util.viewutils

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

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

}