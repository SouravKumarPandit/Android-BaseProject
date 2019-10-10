package com.vrlocal.android.baseproject.di.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment

/**
 * Kotlin extensions for dependency injection
 */

inline fun <reified T : ViewModel> DaggerAppCompatActivity.injectViewModel(factory: ViewModelProvider.Factory): T {

    return ViewModelProvider(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> DaggerFragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}
