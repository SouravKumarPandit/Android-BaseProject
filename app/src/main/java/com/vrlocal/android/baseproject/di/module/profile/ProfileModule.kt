package com.vrlocal.android.baseproject.di.module.profile

import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import com.vrlocal.android.baseproject.ui.screens.profile.data.ProfileRepository
import com.vrlocal.android.baseproject.ui.screens.profile.data.UserProfileRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ProfileModule {

    @JvmStatic
    @ProfileActivityScope
    @Provides
    fun provideAppApi(retrofit: Retrofit): AppApiService {
        return retrofit.create(AppApiService::class.java)
    }

    @JvmStatic
    @ProfileActivityScope
    @Provides
    fun provideProfileRemoteDataSource(apiService: AppApiService) =
        UserProfileRemoteDataSource(apiService)

    @JvmStatic
    @ProfileActivityScope
    @Provides
    fun provideProfileRepository(dao: UserDao, dataSource: UserProfileRemoteDataSource) =
        ProfileRepository(dao, dataSource)
}