package com.vrlocal.android.baseproject.di.module

import com.vrlocal.android.baseproject.util.VConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


public fun <T> provideService(
    okhttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory, clazz: Class<T>
): T {
    return createRetrofit(okhttpClient, converterFactory).create(clazz)
}

fun createRetrofit(
    okhttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory
): Retrofit {

    return Retrofit.Builder()
        .baseUrl(VConstants.BASE_URL)
        .client(okhttpClient)
        .addConverterFactory(converterFactory)
        .build()
}
