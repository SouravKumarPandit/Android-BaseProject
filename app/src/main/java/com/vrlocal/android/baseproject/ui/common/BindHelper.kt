package com.vrlocal.android.baseproject.ui.common

import android.graphics.drawable.Drawable
import android.widget.TextView
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.util.viewutils.fontutils.IconFontDrawable

class BindHelper {

    object Instence{
        @JvmStatic
        fun fontDrawable(view: TextView, stringResource: Int): Drawable {
            return IconFontDrawable(view.context, stringResource)

        }

        @JvmStatic
        fun bindLabel(view: TextView, stringResource: Int): String {
            return view.context.resources.getString(R.string.app_name)
        }
    }



//    @BindingAdapter("iconDrawable")


//    @BindingAdapter("labelResource")



}