package com.vrlocal.android.baseproject.ui.screens.login.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Company {


    @field:SerializedName("name")
    @Expose
    var name: String? = null

    @field:SerializedName("catchPhrase")
    @Expose
    var catchPhrase: String? = null

    @field:SerializedName("bs")
    @Expose
    var bs: String? = null

    override fun toString(): String {
        return "Company name: ${name}\n"+
                "CatchPhrase : ${name}\n"+
                "BS : ${name}\n";
    }
}
