package com.vrlocal.android.baseproject.di.module.profile

import androidx.lifecycle.ViewModel
import com.vrlocal.android.baseproject.di.scope.ViewModelKey
import com.vrlocal.android.baseproject.ui.screens.profile.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileViewModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindUserProfileViewModel(viewModel: UserProfileViewModel): ViewModel

}