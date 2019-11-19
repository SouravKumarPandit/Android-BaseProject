package com.vrlocal.android.baseproject.util.viewutils.fontutils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class FontCache {
    static final String FA_FONT_REGULAR = "fonts/font_icons.ttf";
    static final String FA_FONT_SOLID = "fonts/font_icons.ttf";
    static final String FA_FONT_BRANDS = "fonts/font_icons.ttf";
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    public static Typeface get(Context context, String name) {
        Typeface typeface = fontCache.get(name);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(name, typeface);
        }
        return typeface;
    }
}