package com.vrlocal.android.baseproject.di.module

import android.app.Application
import com.vrlocal.android.baseproject.BuildConfig
import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.api.AuthInterceptor
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.di.CoroutineScropeIO
import com.vrlocal.android.baseproject.di.LegoAPI
import com.vrlocal.android.baseproject.ui.screens.legoset.data.LegoSetRemoteDataSource
import com.vrlocal.android.baseproject.ui.screens.legotheme.data.LegoThemeRemoteDataSource
import com.vrlocal.android.baseproject.util.VConstants
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [CoreDataModule::class, ActivityBuilderModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAppApiService(
        @LegoAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, AppApiService::class.java)


    @Singleton
    @Provides
    fun provideLegoSetRemoteDataSource(appApiService: AppApiService) =
        LegoSetRemoteDataSource(appApiService)

    @Singleton
    @Provides
    fun provideLegoThemeRemoteDataSource(appApiService: AppApiService) =
        LegoThemeRemoteDataSource(appApiService)


//    @Singleton
//    @Provides
//    fun provideLoginRemoteDataSource(appApiService: AppApiService) =
//        LoginRemoteDataSource(appApiService)


    @LegoAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
//            .build()
            .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideLegoSetDao(db: AppDatabase) = db.legoSetDao()


    @Singleton
    @Provides
    fun provideLegoThemeDao(db: AppDatabase) = db.legoThemeDao()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)


    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder()
//            .baseUrl(VConstants.ENDPOINT)
            .baseUrl(VConstants.BASE_URL)
            .client(okhttpClient)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }


}
