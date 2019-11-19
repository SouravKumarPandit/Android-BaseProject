package com.vrlocal.android.baseproject.util.viewutils.fontutils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.vrlocal.android.baseproject.R;

public class IconView extends AppCompatTextView {
    private boolean isBrandingIcon, isSolidIcon;

    public IconView(Context context) {
        super(context);
    }

    public IconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IconView,
                0, 0);
        isSolidIcon = a.getBoolean(R.styleable.IconView_solid_icon, false);
        isBrandingIcon = a.getBoolean(R.styleable.IconView_brand_icon, false);
        init();
    }
/*todo : change for different dense icons */
    private void init() {
        if (isBrandingIcon)
            setTypeface(FontCache.get(getContext(), "fonts/font_icons.ttf"));
        else if (isSolidIcon)
            setTypeface(FontCache.get(getContext(), "fonts/font_icons.ttf"));
        else
            setTypeface(FontCache.get(getContext(), "fonts/font_icons.ttf"));
    }
}