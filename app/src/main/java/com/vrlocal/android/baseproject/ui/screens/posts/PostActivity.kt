package com.vrlocal.android.baseproject.ui.screens.posts

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityListBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.comments.ICommentView
import com.vrlocal.android.baseproject.ui.screens.posts.data.Posts
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class PostActivity : BaseActivity<ActivityListBinding, PostViewModel>(), ICommentView {

    @Inject
    override lateinit var viewModel: PostViewModel
    override val transparentStatusBar: Boolean = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_list)
        bindingData.title="Posts List"
        onCommentsList()
    }

    override fun onCommentsList() {
        viewModel.getUsersPosts().observe(
            this,
            Observer { result -> VResultHandler(this, result) { onGettingPosts(result.data) } })
    }

    private fun onGettingPosts(data: Posts?) {
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()
        rvListRecyclerView.adapter =
            PostAdapter(this, data!!, clickedListener = { onPostSelected(it) })
        rvListRecyclerView.setHasFixedSize(true)
        rvListRecyclerView.layoutManager = LinearLayoutManager(this)


    }

    private fun onPostSelected(postId: Int) {

    }
}

