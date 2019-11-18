package com.vrlocal.android.baseproject.util.viewutils

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Layout
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

object FontIconUtil {
    const val ROOT = "fonts/"
    // FONTAWESOME = ROOT + "fontawesome-webfont.ttf",FONTMOON=ROOT+"icomoon.ttf";
    const val DEFAULT_FONT = ROOT + "font_icons.ttf"
//    const val NEW_FONT = ROOT + "font_icons.ttf"
    /**
     * To mark all the TextViews in the container with Font Icon TypeFace
     * @param v
     * @param typeface
     */
    fun markAsIconContainer(
        v: View?,
        typeface: Typeface?
    ) { //View childd;
        if (v is ViewGroup) {
            val vg = v
            for (i in 0 until vg.childCount) {
                val child = vg.getChildAt(i)
                markAsIconContainer(child, typeface)
            }
        } else if (v is TextView) {
            v.typeface = typeface
        }
    }

    fun getTypeface(context: Context, font: String?): Typeface {
        return Typeface.createFromAsset(context.assets, font)
    }

    fun getFontDrawable(
        context: Context,
        size: Int,
        sResIconId: String?,
        iColor: Int
    ): Drawable {
        val faIcon = FontIconDrawable(context)
        faIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size.toFloat())
        faIcon.textAlign = Layout.Alignment.ALIGN_OPPOSITE
        faIcon.setTextColor(iColor)
        faIcon.setTypeface(getTypeface(context, DEFAULT_FONT))
        faIcon.text = sResIconId
        return faIcon
    }

    fun getFontDrawable(
        context: Context,
        size: Int,
        sResIconId: String?
    ): Drawable {
        return getFontDrawable(context, size, sResIconId, 0xdebdee)
    }
}