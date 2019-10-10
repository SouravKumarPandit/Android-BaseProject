package com.vrlocal.android.baseproject.di.module

import com.vrlocal.android.baseproject.ui.widgets.home.MainActivity
import com.vrlocal.android.baseproject.ui.widgets.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module(includes = [ViewModelModule::class])
abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity


    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): LoginActivity

//    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, LoginModule::class])
//    abstract fun contributeAuthActivity(): LoginActivity



}
