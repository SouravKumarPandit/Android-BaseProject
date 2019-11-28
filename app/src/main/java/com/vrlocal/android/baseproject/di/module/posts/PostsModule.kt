package com.vrlocal.android.baseproject.di.module.posts

import com.vrlocal.android.baseproject.api.PostsService
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.ui.screens.login.data.PostsRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import com.vrlocal.android.baseproject.ui.screens.posts.data.PostRemoteDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object PostsModule {

    @JvmStatic
    @PostActivityScope
    @Provides
    fun provideUserDao(db: AppDatabase):UserDao = db.userDao()


    @JvmStatic
    @PostActivityScope
    @Provides
    fun providePostsService(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }


    @JvmStatic
    @PostActivityScope
    @Provides
    fun providePostRemoteDataSource(postsService: PostsService) =
        PostRemoteDataSource(postsService)

    @JvmStatic
    @PostActivityScope
    @Provides
    fun provideLoginRepository(dao: UserDao, dataSource: PostRemoteDataSource) =
        PostsRepository(dao, dataSource)
}