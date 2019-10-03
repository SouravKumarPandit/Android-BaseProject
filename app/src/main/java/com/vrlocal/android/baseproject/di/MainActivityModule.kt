package com.vrlocal.android.baseproject.di

import com.vrlocal.android.baseproject.ui.widgets.home.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

//    @ContributesAndroidInjector()
//    abstract fun contributeBaseActivity(): BaseActivity
}
