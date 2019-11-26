package com.vrlocal.android.baseproject.ui.screens.photos

import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.comments.ICommentView
import com.vrlocal.android.baseproject.ui.screens.login.data.PhotosRepository
import javax.inject.Inject

class PhotosViewModel @Inject constructor(private val repository: PhotosRepository
) : BaseViewModel<ICommentView>() {
    fun getPhotos() = repository.getListOfPhotos();

}
