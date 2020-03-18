package com.vrlocal.android.baseproject.ui.screens.comments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityListBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.comments.data.Comments
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class CommentsActivity  : BaseActivity<ActivityListBinding,CommentsViewModel>() ,ICommentView{

    @Inject
    override lateinit var viewModel: CommentsViewModel

    override val transparentStatusBar: Boolean = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_list)
        bindingData.title="Comments List"
        onCommentsList()
        rvListRecyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))


    }

    override fun onCommentsList() {
        viewModel.getUsersPosts().observe(
            this,
            Observer { result -> VResultHandler(this, result) { onGettingPosts(result.data) } })
    }

    private fun onGettingPosts(data: Comments?) {
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()
        if (data != null) viewModel.comments=data;
        rvListRecyclerView.adapter =
            CommentsAdapter(this, data!!, clickedListener = { /*todo*/})
        rvListRecyclerView.setHasFixedSize(true)
        rvListRecyclerView.layoutManager = LinearLayoutManager(this)


    }

    override fun onDebouncingClick(view: View) {

    }

}

