package com.vrlocal.android.baseproject.di.module.comments

import androidx.lifecycle.ViewModel
import com.vrlocal.android.baseproject.di.scope.ViewModelKey
import com.vrlocal.android.baseproject.ui.screens.comments.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CommentsViewModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    abstract fun bindLoginViewModel(viewModel: CommentsViewModel?): ViewModel?
}