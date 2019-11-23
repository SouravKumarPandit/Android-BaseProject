package com.vrlocal.android.baseproject.di.module.posts

import androidx.lifecycle.ViewModel
import com.vrlocal.android.baseproject.di.scope.ViewModelKey
import com.vrlocal.android.baseproject.ui.screens.posts.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PostsViewModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindLoginViewModel(viewModel: PostViewModel?): ViewModel?
}