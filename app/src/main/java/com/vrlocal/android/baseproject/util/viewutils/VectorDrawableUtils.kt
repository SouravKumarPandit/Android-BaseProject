package com.vrlocal.android.baseproject.util.viewutils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

object VectorDrawableUtils {
    fun getVectorDrawable(
        clContext: Context,
        drawableResId: Int,
        colorFilter: Int
    ): Drawable? {
        val drawable: Drawable? = VectorDrawableCompat.create(
            clContext.resources,
            drawableResId,
            clContext.theme
        )
        drawable?.setTint(colorFilter)
        return drawable
    }

    fun getNormalDrawable(
        clContext: Context,
        drawableResId: Int,
        colorFilter: Int
    ): Drawable? {
        var drawable: Drawable? = clContext.resources.getDrawable(drawableResId, clContext.theme)
        drawable?.setTint(colorFilter)
        return drawable
    }
}