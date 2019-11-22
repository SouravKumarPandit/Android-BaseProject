package com.vrlocal.android.baseproject.ui.screens.comments.data

import com.google.gson.annotations.SerializedName

typealias Comments = ArrayList<Comment>

data class Comment (
    @SerializedName("postId")
    val postID: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
)
