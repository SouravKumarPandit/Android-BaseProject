package com.vrlocal.android.baseproject.ui.screens.posts

import android.os.Bundle
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.databinding.ActivityPostsBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import javax.inject.Inject

class PostActivity  : BaseActivity<ActivityPostsBinding,PostViewModel>(){

    @Inject
    override lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_posts)


    }
}

