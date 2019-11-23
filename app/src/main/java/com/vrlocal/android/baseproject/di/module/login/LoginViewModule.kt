package com.vrlocal.android.baseproject.di.module.login

import androidx.lifecycle.ViewModel
import com.vrlocal.android.baseproject.di.scope.ViewModelKey
import com.vrlocal.android.baseproject.ui.screens.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel?): ViewModel?
}