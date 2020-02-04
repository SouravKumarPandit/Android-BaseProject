package com.vrlocal.android.baseproject.ui.screens.photos

import com.vrlocal.android.baseproject.di.CoroutineScropeIO
import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.comments.ICommentView
import com.vrlocal.android.baseproject.ui.screens.login.data.PhotosRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val repository: PhotosRepository,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : BaseViewModel<ICommentView>() {
//    fun getPhotos() = repository.getListOfPhotos();


    var connectivityAvailable: Boolean = false
    var themeId: Int? = null



    val photoSets by lazy {
        repository.observePagedSets(
            connectivityAvailable, themeId, ioCoroutineScope)
    }

}
