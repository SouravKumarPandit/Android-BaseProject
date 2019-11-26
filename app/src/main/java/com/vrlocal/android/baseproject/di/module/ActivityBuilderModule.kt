package com.vrlocal.android.baseproject.di.module

import com.vrlocal.android.baseproject.di.module.albums.AlbumsModule
import com.vrlocal.android.baseproject.di.module.albums.AlbumsViewModule
import com.vrlocal.android.baseproject.di.module.comments.CommentsActivityScope
import com.vrlocal.android.baseproject.di.module.comments.CommentsModule
import com.vrlocal.android.baseproject.di.module.comments.CommentsViewModule
import com.vrlocal.android.baseproject.di.module.home.HomeActivityScope
import com.vrlocal.android.baseproject.di.module.home.HomeFragmentBuildersModule
import com.vrlocal.android.baseproject.di.module.home.HomeModule
import com.vrlocal.android.baseproject.di.module.home.HomeViewModelModule
import com.vrlocal.android.baseproject.di.module.login.LoginActivityScope
import com.vrlocal.android.baseproject.di.module.login.LoginModule
import com.vrlocal.android.baseproject.di.module.login.LoginViewModule
import com.vrlocal.android.baseproject.di.module.photos.PhotosModule
import com.vrlocal.android.baseproject.di.module.photos.PhotosViewModule
import com.vrlocal.android.baseproject.di.module.posts.PostActivityScope
import com.vrlocal.android.baseproject.di.module.posts.PostsModule
import com.vrlocal.android.baseproject.di.module.posts.PostsViewModule
import com.vrlocal.android.baseproject.di.module.profile.ProfileModule
import com.vrlocal.android.baseproject.di.module.profile.ProfileViewModule
import com.vrlocal.android.baseproject.ui.screens.albums.AlbumsActivity
import com.vrlocal.android.baseproject.ui.screens.comments.CommentsActivity
import com.vrlocal.android.baseproject.ui.screens.home.HomeActivity
import com.vrlocal.android.baseproject.ui.screens.login.LoginActivity
import com.vrlocal.android.baseproject.ui.screens.login.SplashActivity
import com.vrlocal.android.baseproject.ui.screens.photos.PhotosActivity
import com.vrlocal.android.baseproject.ui.screens.posts.PostActivity
import com.vrlocal.android.baseproject.ui.screens.profile.UserProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module()
abstract class ActivityBuilderModule {



    @LoginActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class,LoginViewModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @LoginActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class,LoginViewModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @HomeActivityScope
    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class, HomeModule::class, HomeViewModelModule::class])
    abstract fun contributeHomeActivity(): HomeActivity


    @PostActivityScope
    @ContributesAndroidInjector(modules = [PostsModule::class,PostsViewModule::class])
    abstract fun contributePostActivity(): PostActivity


    @CommentsActivityScope
    @ContributesAndroidInjector(modules = [CommentsModule::class,CommentsViewModule::class])
    abstract fun contributeCommentsActivity(): CommentsActivity

    @ContributesAndroidInjector(modules = [AlbumsModule::class,AlbumsViewModule::class])
    abstract fun contributeAlbumsActivity(): AlbumsActivity


    @ContributesAndroidInjector(modules = [ProfileModule::class,ProfileViewModule::class])
    abstract fun contributeUserProfileActivity(): UserProfileActivity


    @ContributesAndroidInjector(modules = [PhotosModule::class, PhotosViewModule::class])
    abstract fun contributePhotosActivity(): PhotosActivity



//    @ContributesAndroidInjector(modules = [AuthViewModelsModule::class, LoginModule::class])
//    abstract fun contributeAuthActivity(): LoginActivity



}
