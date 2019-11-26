package com.vrlocal.android.baseproject.ui.screens.photos.data

import com.google.gson.annotations.SerializedName

typealias Photos = ArrayList<Photo>


data class Photo(
    @SerializedName("albumId")
    val albumID: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailURL: String
)
