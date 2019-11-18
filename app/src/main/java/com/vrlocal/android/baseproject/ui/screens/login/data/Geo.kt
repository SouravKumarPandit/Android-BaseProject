package com.vrlocal.android.baseproject.ui.screens.login.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geo {


    @field:SerializedName("lat")
    @Expose
    var lat: String? = null

    @field:SerializedName("lng")
    @Expose
    var lng: String? = null


}
