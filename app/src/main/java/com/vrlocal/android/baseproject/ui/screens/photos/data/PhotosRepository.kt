package com.vrlocal.android.baseproject.ui.screens.login.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vrlocal.android.baseproject.ui.common.paginationview.PageDataSourceFactory
import com.vrlocal.android.baseproject.ui.common.paginationview.PageListDataSource
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo
import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class PhotosRepository @Inject constructor(
    private val dao: PhotosDao,
    private val remoteSource: PageListDataSource
) {



    fun observePagedSets(
        connectivityAvailable: Boolean, themeId: Int? = null,
        coroutineScope: CoroutineScope
    ) =
        if (connectivityAvailable) observeRemotePagedSets(themeId, coroutineScope)
        else observeLocalPagedSets(themeId)

    private fun observeLocalPagedSets(themeId: Int? = null): LiveData<PagedList<Photo>> {
        val dataSourceFactory =
            if (themeId == null) dao.getPagedLegoSets()
            else dao.getPagedLegoSetsByTheme(themeId)

        return LivePagedListBuilder(
            dataSourceFactory,
            PageDataSourceFactory.pagedListConfig()
        ).build()
    }


    private fun observeRemotePagedSets(themeId: Int? = null, ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<Photo>> {
        val dataSourceFactory = PageDataSourceFactory(
            themeId, remoteSource,
            dao, ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            PageDataSourceFactory.pagedListConfig()
        ).build()
    }
}
