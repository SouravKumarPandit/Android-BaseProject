package com.vrlocal.android.baseproject.di.module.albums

import com.vrlocal.android.baseproject.api.AlbumsService
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.ui.screens.albums.data.AlbumsRemoteDataSource
import com.vrlocal.android.baseproject.ui.screens.albums.data.AlbumsRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AlbumsModule {


    @JvmStatic
    @AlbumsActivityScope
    @Provides
    fun provideUserDao(db: AppDatabase):UserDao = db.userDao()


    @JvmStatic
    @AlbumsActivityScope
    @Provides
    fun provideAlbumsService(retrofit: Retrofit): AlbumsService {
        return retrofit.create(AlbumsService::class.java)
    }

    @JvmStatic
    @AlbumsActivityScope
    @Provides
    fun provideAlbumsDataSource(appService: AlbumsService) =
        AlbumsRemoteDataSource(appService)

    @JvmStatic
    @AlbumsActivityScope
    @Provides
    fun provideAlbumsRepository(dao: UserDao, dataSource: AlbumsRemoteDataSource) =
        AlbumsRepository(dao, dataSource)

}