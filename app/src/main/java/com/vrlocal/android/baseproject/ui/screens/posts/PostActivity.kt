package com.vrlocal.android.baseproject.ui.screens.posts

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityPostsBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.posts.data.Posts
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostActivity : BaseActivity<ActivityPostsBinding, PostViewModel>(), IPostView {

    @Inject
    override lateinit var viewModel: PostViewModel
    override val transparentStatusBar: Boolean = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_posts)
        getUsersPost()
    }

    override fun getUsersPost() {
        viewModel.getUsersPosts().observe(
            this,
            Observer { result -> VResultHandler(this, result) { onGettingPosts(result.data) } })
    }

    private fun onGettingPosts(data: Posts?) {
        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()

        rvPostList.adapter =
            PostAdapter(this, data!!, clickedListener = { onPostSelected(it) })
        rvPostList.setHasFixedSize(true)
        rvPostList.layoutManager = LinearLayoutManager(this)

    }

    private fun onPostSelected(postId: Int) {

    }
}

