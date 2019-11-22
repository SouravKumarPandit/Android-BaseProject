package com.vrlocal.android.baseproject.ui.screens.login.data

import androidx.lifecycle.distinctUntilChanged
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.data.backgroundLiveData
import com.vrlocal.android.baseproject.ui.screens.home.data.HomeOption
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class HomeRepository @Inject constructor() {

    fun getHomeOptionList()=
        backgroundLiveData(backgroundCallback = { generateHomeOptionList() }).distinctUntilChanged()

    private fun generateHomeOptionList(): VResult<List<HomeOption>>{

        val list: ArrayList<HomeOption> = ArrayList()

        val arrayListOf = arrayListOf("users", "posts", "comments", "albums", "photos", "todos","Users");
        val iconList = arrayListOf(
            R.string.ic_user,
            R.string.ic_local_post_office,
            R.string.ic_comment,
            R.string.ic_photo_album,
            R.string.ic_photo,
            R.string.ic_calendar_check_o,

            R.string.ic_person_add
        );

        arrayListOf.forEachIndexed() { index, sValue ->
            val homeOptions = HomeOption(sValue, iconList[index], "\\$sValue")
            list.add(homeOptions)
        }

        return VResult.success(list);
    }


}
