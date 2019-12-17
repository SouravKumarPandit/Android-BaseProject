package com.vrlocal.android.baseproject.ui.screens.photos.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "photos")
data class Photo(
    @SerializedName("albumId")
    val albumID: Long,

    @PrimaryKey
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailURL: String
){


}
