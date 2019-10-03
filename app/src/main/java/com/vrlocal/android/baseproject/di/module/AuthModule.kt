package com.vrlocal.android.baseproject.di.module

import com.vrlocal.android.baseproject.api.AuthApi
import com.vrlocal.android.baseproject.di.scope.AuthScope
import com.vrlocal.android.baseproject.ui.widgets.login.User
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    internal fun someUser(): User {
        return User()
    }

    @AuthScope
    @Provides
    internal fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}