package com.vrlocal.android.baseproject.ui.screens.login.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.data.*
import com.vrlocal.android.baseproject.ui.screens.home.data.HomeOption
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class LoginRepository @Inject constructor(
    private val dao: UserDao,
    private val remoteSource: LoginRemoteDataSource
) {

    fun cacheOrNetworkUser(id: String) = resultLiveData(
        databaseQuery = { dao.getCurrentUser() },
        networkCall = { remoteSource.authenticateUser(id) },
        saveCallResult = { dao.upsert(it) })
        .distinctUntilChanged()

    fun authenticateNetworkUser(id: String) = networkLiveData(
        networkCall = {
            remoteSource.authenticateUser(id)
        }).distinctUntilChanged()

    fun authenticateDatabaseUser() = databaseLiveData(
        databaseQuery = {
            dao.getCurrentUser()
        }).distinctUntilChanged()


    fun deleteUser() =
        backgroundLiveData(backgroundCallback = { getSumValue() }).distinctUntilChanged()

    private fun getSumValue(): VResult<Boolean> {
        dao.removeUser()
        return VResult.success(true)
    }

    fun getHomeOptionList()=
        backgroundLiveData(backgroundCallback = { generateHomeOptionList() }).distinctUntilChanged()

    private fun generateHomeOptionList(): VResult<List<HomeOption>>{

        val list: ArrayList<HomeOption> = ArrayList<HomeOption>()

        val arrayListOf = arrayListOf("users", "posts", "comments", "albums", "photos", "todos");
        val iconList = arrayListOf(
            R.string.ic_user,
            R.string.ic_local_post_office,
            R.string.ic_comment,
            R.string.ic_photo_album,
            R.string.ic_photo,
            R.string.ic_calendar_check_o
        );

//        val classList = arrayListOf(
//            UserProfileActivity::class.java,
//            PostActivity::class.java,
//            CommentsActivity::class.java,
//            AlbumsActivity::class.java,
//            PhotosActivity::class.java,
//            TodoActivity::class.java
//        );
        arrayListOf.forEachIndexed() { index, sValue ->
            val homeOptions = HomeOption(sValue, iconList[index], "\\$sValue")
            list.add(homeOptions)
        }

        return VResult.success(list);
    }


}
