package com.vrlocal.android.baseproject.util.viewutils.fontutils

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import com.vrlocal.android.baseproject.R

class FontIconView : AppCompatTextView {
    private var isBrandingIcon = false
    private var isSolidIcon = false

    constructor(context: Context?) : super(context) {}
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int = 0
    ) : super(context, attrs, defStyle) {
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
            this,
            12,
            1000,
            1,
            TypedValue.COMPLEX_UNIT_SP
        )
        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.FontIconView,
            0, 0
        )
        this.gravity = Gravity.CENTER
        isSolidIcon = a.getBoolean(R.styleable.FontIconView_solid_icon, false)
        isBrandingIcon = a.getBoolean(R.styleable.FontIconView_brand_icon, false)
        init()
    }

    /*todo : change for different dense icons */
    private fun init() {
        typeface = if (isBrandingIcon) FontCache.get(
            context,
            "fonts/font_icons.ttf"
        ) else if (isSolidIcon) FontCache.get(
            context,
            "fonts/font_icons.ttf"
        ) else FontCache.get(context, "fonts/font_icons.ttf")
    }
}