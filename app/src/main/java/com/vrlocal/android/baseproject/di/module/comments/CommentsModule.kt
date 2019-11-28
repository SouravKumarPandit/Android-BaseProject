package com.vrlocal.android.baseproject.di.module.comments

import com.vrlocal.android.baseproject.api.CommentsService
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.ui.screens.comments.data.CommentRemoteDataSource
import com.vrlocal.android.baseproject.ui.screens.comments.data.CommentsRepository
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object CommentsModule {

    @JvmStatic
    @CommentsActivityScope
    @Provides
    fun provideUserDao(db: AppDatabase):UserDao = db.userDao()


    @JvmStatic
    @CommentsActivityScope
    @Provides
    fun provideCommentsService(retrofit: Retrofit): CommentsService {
        return retrofit.create(CommentsService::class.java)
    }
    @JvmStatic
    @CommentsActivityScope
    @Provides
    fun provideCommentsDataSource(appService: CommentsService) =
        CommentRemoteDataSource(appService)

    @JvmStatic
    @CommentsActivityScope
    @Provides
    fun provideCommentRepository(dao: UserDao, dataSource: CommentRemoteDataSource) =
        CommentsRepository(dao, dataSource)
}