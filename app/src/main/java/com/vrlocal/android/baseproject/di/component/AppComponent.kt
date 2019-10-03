package com.vrlocal.android.baseproject.di.component

import android.app.Application
import com.vrlocal.android.baseproject.BaseApplication
import com.vrlocal.android.baseproject.di.AppModule
import com.vrlocal.android.baseproject.di.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)

interface AppComponent : AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}