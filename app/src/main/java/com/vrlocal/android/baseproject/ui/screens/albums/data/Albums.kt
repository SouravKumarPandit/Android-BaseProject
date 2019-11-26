package com.vrlocal.android.baseproject.ui.screens.albums.data

import com.google.gson.annotations.SerializedName

typealias Albums = ArrayList<Album>

data class Album (
    @SerializedName("userId")
    val userID: Long,

    val id: Long,
    val title: String
)
