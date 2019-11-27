package com.vrlocal.android.baseproject.util.viewutils.fontutils

import android.content.Context
import android.graphics.Typeface
import java.util.*

object FontCache {
    const val FA_FONT_REGULAR = "fonts/font_icons.ttf"
    const val FA_FONT_SOLID = "fonts/font_icons.ttf"
    const val FA_FONT_BRANDS = "fonts/font_icons.ttf"
    private val fontCache =
        Hashtable<String, Typeface?>()

    @JvmStatic
    operator fun get(context: Context, name: String): Typeface? {
        var typeface = fontCache[name]
        if (typeface == null) {
            typeface = try {
                Typeface.createFromAsset(context.assets, name)
            } catch (e: Exception) {
                return null
            }
            fontCache[name] = typeface
        }
        return typeface
    }
}