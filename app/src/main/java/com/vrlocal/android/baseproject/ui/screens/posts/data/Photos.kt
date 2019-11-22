package com.vrlocal.android.baseproject.ui.screens.posts.data

import com.google.gson.annotations.SerializedName
data class Post (
    @SerializedName("userId")
    val userID: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)
typealias Posts = ArrayList<Post>
