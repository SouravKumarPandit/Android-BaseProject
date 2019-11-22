package com.vrlocal.android.baseproject.ui.screens.comments

import com.vrlocal.android.baseproject.ui.base.BaseViewModel
import com.vrlocal.android.baseproject.ui.screens.comments.data.Comments
import com.vrlocal.android.baseproject.ui.screens.comments.data.CommentsRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private var userDao: UserDao,
    private val repository: CommentsRepository
) : BaseViewModel<ICommentView>() {
    lateinit  var comments:Comments

    fun getUsersPosts() = repository.getListOfComment();

}
