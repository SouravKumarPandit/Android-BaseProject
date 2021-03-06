package com.vrlocal.android.baseproject.ui.screens.login.data

import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address {

    
    @field:SerializedName("street")
    @Expose
    var street: String? = null
    
    @field:SerializedName("suite")
    @Expose
    var suite: String? = null
    
    @field:SerializedName("city")
    @Expose
    var city: String? = null
    
    @field:SerializedName("zipcode")
    @Expose
    var zipcode: String? = null

    @Embedded(prefix = "geo_")
    @field:SerializedName("geo")
    @Expose
    var geo: Geo? = null

    override fun toString(): String {
        return         "Street : ${street}\n"+
        "Suite : ${suite}\n"+
        "City : ${city}\n"+
        "ZipCode : ${zipcode}\n"+
        "Geo : ${geo?.lat} /${geo?.lng}\n";
    }
}
