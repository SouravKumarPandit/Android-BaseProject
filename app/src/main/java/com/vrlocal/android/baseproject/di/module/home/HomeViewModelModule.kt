package com.vrlocal.android.baseproject.di.module.home

import androidx.lifecycle.ViewModel
import com.vrlocal.android.baseproject.di.scope.ViewModelKey
import com.vrlocal.android.baseproject.ui.screens.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindPostsViewModel(viewModel: HomeViewModel?): ViewModel?
}