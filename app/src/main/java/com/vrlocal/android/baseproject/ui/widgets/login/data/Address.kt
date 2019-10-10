package com.vrlocal.android.baseproject.ui.widgets.login.data

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
    
    @field:SerializedName("geo")
    @Expose
    var geo: Geo? = null



}
