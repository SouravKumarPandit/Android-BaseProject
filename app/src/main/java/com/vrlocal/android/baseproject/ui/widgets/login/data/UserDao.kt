package com.vrlocal.android.baseproject.ui.widgets.login.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User)

    @Update
    fun updateUsers(vararg users: User)

    @Query("SELECT * FROM current_user")
    fun getCurrentUser(): LiveData<User>


    @Query("DELETE FROM current_user")
    fun removeUser()


}
