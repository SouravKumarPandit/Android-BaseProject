package com.vrlocal.android.baseproject.ui.common.paginationview

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo
import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PageDataSourceFactory @Inject constructor(
    private val themeId: Int? = null,
    private val dataSource: PageListDataSource,
    private val dao: PhotosDao,
    private val scope: CoroutineScope) : DataSource.Factory<Int, Photo>() {

    private val liveData = MutableLiveData<PageDataSource>()


    companion object {
        private const val PAGE_SIZE = 100

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

    override fun create(): DataSource<Int, Photo> {
        val source = PageDataSource(themeId, dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

}