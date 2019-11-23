package com.vrlocal.android.baseproject.di.module

import android.app.Application
import com.vrlocal.android.baseproject.api.AppApiService
import com.vrlocal.android.baseproject.data.AppDatabase
import com.vrlocal.android.baseproject.di.CoroutineScropeIO
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
       okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, AppApiService::class.java)


    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(VConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideLoginRemoteDataSource(appApiService: AppApiService) =
//        LoginRemoteDataSource(appApiService)



    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)



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
            .baseUrl(VConstants.BASE_URL)
            .client(okhttpClient)
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
