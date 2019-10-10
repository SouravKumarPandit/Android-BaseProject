package com.vrlocal.android.baseproject.ui.widgets.login.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


//@Entity(tableName = "user")
data class User (

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("name")
    var name: String,
    @field:SerializedName("username")
    var username: String,
    @field:SerializedName("email")
    var email: String,
    @field:SerializedName("phone")
    var phone: String,
    @field:SerializedName("website")
    var website: String?=null,
    @field:SerializedName("company")
    var company: Company? = null,
    @field:SerializedName("address")
    var address: Address? = null
){
    override fun toString() = name
}