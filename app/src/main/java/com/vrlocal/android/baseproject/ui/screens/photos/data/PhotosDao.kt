package com.vrlocal.android.baseproject.ui.screens.photos.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos WHERE id = :themeId ORDER BY albumID DESC")
    fun getLegoSets(themeId: Int): LiveData<List<Photo>>

    @Query("SELECT * FROM photos WHERE id = :themeId ORDER BY albumID DESC")
    fun getPagedLegoSetsByTheme(themeId: Int): DataSource.Factory<Int, Photo>


    @Query("SELECT * FROM photos ORDER BY albumID DESC")
    fun getPagedLegoSets(): DataSource.Factory<Int, Photo>


    @Query("SELECT * FROM photos ORDER BY id DESC")
    fun getPosts(): LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(plants: List<Photo>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)
}