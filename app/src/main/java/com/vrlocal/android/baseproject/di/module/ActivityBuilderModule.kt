package com.vrlocal.android.baseproject.di.module

import com.vrlocal.android.baseproject.ui.screens.alubums.AlbumsActivity
import com.vrlocal.android.baseproject.ui.screens.comments.CommentsActivity
import com.vrlocal.android.baseproject.ui.screens.home.HomeActivity
import com.vrlocal.android.baseproject.ui.screens.home.SplashActivity
import com.vrlocal.android.baseproject.ui.screens.home.old.LegoMainActivity
import com.vrlocal.android.baseproject.ui.screens.login.LoginActivity
import com.vrlocal.android.baseproject.ui.screens.posts.PostActivity
import com.vrlocal.android.baseproject.ui.screens.profile.UserProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module(includes = [ViewModelModule::class])
abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeMainActivity(): LegoMainActivity

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector()
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector()
    abstract fun contributeUserProfileActivity(): UserProfileActivity

    @ContributesAndroidInjector()
    abstract fun contributePostActivity(): PostActivity


    @ContributesAndroidInjector()
    abstract fun contributeCommentsActivity(): CommentsActivity


    @ContributesAndroidInjector()
    abstract fun contributeAlbumsActivity(): AlbumsActivity

//    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, LoginModule::class])
//    abstract fun contributeAuthActivity(): LoginActivity


}
