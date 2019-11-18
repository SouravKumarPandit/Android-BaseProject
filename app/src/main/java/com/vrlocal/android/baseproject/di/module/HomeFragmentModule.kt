package com.vrlocal.android.baseproject.di.module


import com.vrlocal.android.baseproject.ui.screens.legoset.ui.LegoSetFragment
import com.vrlocal.android.baseproject.ui.screens.legoset.ui.LegoSetsFragment
import com.vrlocal.android.baseproject.ui.screens.legotheme.ui.LegoThemeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class HomeFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): LegoThemeFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetsFragment(): LegoSetsFragment

    @ContributesAndroidInjector
    abstract fun contributeLegoSetFragment(): LegoSetFragment
}
