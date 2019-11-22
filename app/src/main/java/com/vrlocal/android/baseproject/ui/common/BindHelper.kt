package com.vrlocal.android.baseproject.ui.common

import java.math.BigDecimal
import java.text.DecimalFormat


class BindHelper {

    fun bindString(stringLabel: String="", stringContent: String= ""):String{
        return "$stringLabel $stringContent"
    }

    fun getQuantityString(quantity: Int): String? {
        return "Qty: $quantity"
    }

    fun getValue(value: BigDecimal?): String? {
        val df = DecimalFormat("###,###,###.00")
        return java.lang.String.valueOf(df.format(value))
    }

    fun getFloat(value: BigDecimal): Float {
        return value.toFloat()
    }

    companion object {
        @JvmStatic
        fun convertIntToString(value: Long): String? {
            return "($value)"
        }
    }

}