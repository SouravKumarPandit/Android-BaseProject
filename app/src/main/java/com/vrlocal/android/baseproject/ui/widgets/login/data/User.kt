package com.vrlocal.android.baseproject.ui.widgets.login.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "current_user")
data class User (


    @PrimaryKey(autoGenerate = false)
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
    @Embedded(prefix = "company_")
    @field:SerializedName("company")
    var company: Company? = null,

    @Embedded(prefix = "address_")
    @field:SerializedName("address")
    var address: Address? = null
){

//    @PrimaryKey(autoGenerate = false)
//    @field:SerializedName("userId")
//    var userId:Int= VConstants.CURRENT_USER_CONST;

}