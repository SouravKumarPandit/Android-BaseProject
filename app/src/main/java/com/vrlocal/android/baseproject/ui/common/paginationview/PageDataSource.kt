package com.vrlocal.android.baseproject.ui.common.paginationview

import androidx.paging.PageKeyedDataSource
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo
import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosDao
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class PageDataSource @Inject constructor(
    private val themeId: Int? = null,
    private val dataSource: PageListDataSource,
    private val dao: PhotosDao,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Photo>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<Photo>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchSets(page, pageSize, themeId)
            if (response.status == VResult.Status.SUCCESS) {
                val results = response.data!!
                dao.insertPhotos(results)
                callback(results)
            } else if (response.status == VResult.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }

}