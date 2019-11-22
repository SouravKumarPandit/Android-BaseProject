package com.vrlocal.android.baseproject.ui.screens.alubums

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityListBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.alubums.data.Albums
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class AlbumsActivity : BaseActivity<ActivityListBinding, AlbumsViewModel>(), IAlbumsView {

    @Inject
    override lateinit var viewModel: AlbumsViewModel

    override val transparentStatusBar: Boolean = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_list)
        bindingData.title = "Albums List"
        fetchAlbumList()
        rvListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )


    }

    override fun fetchAlbumList() {
        viewModel.getAlbumList().observe(
            this,
            Observer { result -> VResultHandler(this, result) { onGettingPosts(result.data) } })
    }

    private fun onGettingPosts(data: Albums?) {
//        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show()
        if (data != null) viewModel.albums = data;
        rvListRecyclerView.adapter =
            AlbumsAdapter(this, data!!, clickedListener = { onPostSelected(it) })
        rvListRecyclerView.layoutManager = LinearLayoutManager(this)


    }

    private fun onPostSelected(position: Int) {
        showToast("clicked $position")
    }
}