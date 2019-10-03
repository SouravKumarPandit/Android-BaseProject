package com.vrlocal.android.baseproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vrlocal.android.baseproject.di.scope.ViewModelKey
import com.vrlocal.android.baseproject.ui.widgets.legoset.ui.LegoSetViewModel
import com.vrlocal.android.baseproject.ui.widgets.legoset.ui.LegoSetsViewModel
import com.vrlocal.android.baseproject.ui.widgets.legotheme.ui.LegoThemeViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LegoThemeViewModel::class)
    abstract fun bindThemeViewModel(viewModel: LegoThemeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetsViewModel::class)
    abstract fun bindLegoSetsViewModel(viewModel: LegoSetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LegoSetViewModel::class)
    abstract fun bindLegoSetViewModel(viewModel: LegoSetViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
