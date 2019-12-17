package com.vrlocal.android.baseproject.di.module.photos

import com.vrlocal.android.baseproject.api.PhotosService
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.ui.common.paginationview.PageListDataSource
import com.vrlocal.android.baseproject.ui.screens.login.data.PhotosRepository
import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosDao
import com.vrlocal.android.baseproject.ui.screens.photos.data.PhotosRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object PhotosModule {

    @JvmStatic
    @PhotosActivityScope
    @Provides
    fun provideUserDao(db: AppDatabase): PhotosDao = db.photoDao()


    @JvmStatic
    @PhotosActivityScope
    @Provides
    fun providePhotosService(retrofit: Retrofit): PhotosService {
        return retrofit.create(PhotosService::class.java)
    }

    @JvmStatic
    @PhotosActivityScope
    @Provides
    fun providePhotosRemoteDataSource(appService: PhotosService) =
        PhotosRemoteDataSource(appService)

    @JvmStatic
    @PhotosActivityScope
    @Provides
    fun providePageListDataSource(appService: PhotosService) =
        PageListDataSource(appService)


    @JvmStatic
    @PhotosActivityScope
    @Provides
    fun providePhotosRepository(dataSource: PageListDataSource, dao:PhotosDao) =
        PhotosRepository(dao,dataSource)

}