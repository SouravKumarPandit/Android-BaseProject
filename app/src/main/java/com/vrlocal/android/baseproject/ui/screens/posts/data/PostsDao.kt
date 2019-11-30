package com.vrlocal.android.baseproject.ui.screens.posts.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PostsDao {


    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun getPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(plants: List<Post>)

}