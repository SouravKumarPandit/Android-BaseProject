package com.vrlocal.android.baseproject.di.module.login

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.ui.screens.login.data.LoginRemoteDataSource
import com.vrlocal.android.baseproject.ui.screens.login.data.LoginRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object LoginModule {

    @JvmStatic
    @LoginActivityScope
    @Provides
    fun provideAppApi(retrofit: Retrofit): AppApiService {
        return retrofit.create(AppApiService::class.java)
    }

    @JvmStatic
    @LoginActivityScope
    @Provides
    fun provideLoginRemoteDataSource(appApiService: AppApiService) =
        LoginRemoteDataSource(appApiService)

    @JvmStatic
    @LoginActivityScope
    @Provides
    fun provideLoginRepository(dao: UserDao, dataSource: LoginRemoteDataSource) =
        LoginRepository(dao, dataSource)

}