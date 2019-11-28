package com.vrlocal.android.baseproject.di.module

/**
 * Dagger module to provide core data functionality.
// */
//@Module
//class CoreDataModule {
////    private val cacheSize = 10 * 1024 * 1024 // 10MB Cache size
////        .toLong()
//    // Build interceptor
// /*   private val rewriteCacheController =
//        label@ Interceptor { chain: Interceptor.Chain ->
//            val originalResponse = chain.proceed(chain.request())
//            if (VNetworkUtils.isAvailable()) {
//                val maxAge = 60 // read from cache for 1 minute
//                return@label originalResponse.newBuilder()
//                    .header("Cache-Control", "public, max-age=$maxAge")
//                    .build()
//            } else {
//                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
//                return@label originalResponse.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
//                    .build()
//            }
//        }*/
//
//
//    @Provides
//    fun provideOkHttpClient(
//        interceptor: HttpLoggingInterceptor,
//        application: Application
//    ): OkHttpClient {
//      /*  val rewriteCacheController =  Interceptor { chain: Interceptor.Chain ->
//            val originalResponse = chain.proceed(chain.request())
//            if (VNetworkUtils.isAvailable()) {
//                val maxAge = 60 // read from cache for 1 minute
//                return@Interceptor originalResponse.newBuilder()
//                    .header("Cache-Control", "public, max-age=$maxAge")
//                    .build()
//            } else {
//                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
//                return@Interceptor originalResponse.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
//                    .build()
//            }
//        }*/
//        return OkHttpClient.Builder().addInterceptor(interceptor)
////            .addNetworkInterceptor(rewriteCacheController)
////            .cache(Cache(application.cacheDir, cacheSize))
//            .build()
//    }
//
//    @Provides
//    fun provideLoggingInterceptor() =
//        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }
//
//    @Provides
//    @Singleton
//    fun provideGson(): Gson = Gson()
//
//    @Provides
//    @Singleton
//    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
//        GsonConverterFactory.create(gson)
//}
