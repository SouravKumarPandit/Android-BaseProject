package com.vrlocal.android.baseproject.ui.screens.photos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.databinding.ActivityListBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.common.GridSpacingItemDecoration
import com.vrlocal.android.baseproject.ui.common.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class PhotosActivity : BaseActivity<ActivityListBinding, PhotosViewModel>() {

    private val spanCount = 3


    @Inject
    override lateinit var viewModel: PhotosViewModel

    override val transparentStatusBar: Boolean = true;
    private var isLinearLayoutManager: Boolean = false
    private lateinit var linearLayoutManager: LinearLayoutManager


    private lateinit var gridLayoutManager: GridLayoutManager

    private val adapter: PhotosAdapter by lazy { PhotosAdapter() }


    private val linearDecoration: RecyclerView.ItemDecoration by lazy {
        VerticalItemDecoration(
            resources.getDimension(R.dimen.margin_normal).toInt()
        )
    }
    private val gridDecoration: RecyclerView.ItemDecoration by lazy {
        GridSpacingItemDecoration(
            spanCount, 0
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_list)
        setSupportActionBar(appToolbar)
        bindingData.title = "Photos List"
//        loadPhotosList()
        linearLayoutManager = LinearLayoutManager(this)
        gridLayoutManager = GridLayoutManager(this, spanCount)
        setLayoutManager()
        viewModel.connectivityAvailable = true;
        bindingData.rvListRecyclerView.adapter = adapter
        subscribeUi(adapter)


    }


    private fun subscribeUi(adapter: PhotosAdapter) {
        viewModel.photoSets.observe(this) {
            adapter.submitList(it)
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        this.menuInflater.inflate(R.menu.menu_list_representation, menu)

        setDataRepresentationIcon(menu!!.findItem(R.id.list))

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list -> {
                isLinearLayoutManager = !isLinearLayoutManager
                setDataRepresentationIcon(item)
                setLayoutManager()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setDataRepresentationIcon(item: MenuItem) {
        item.setIcon(
            if (isLinearLayoutManager)
                R.drawable.ic_grid_list_24dp else R.drawable.ic_list_white_24dp
        )
    }

    private fun setLayoutManager() {
        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (rvListRecyclerView.layoutManager != null) {
            scrollPosition = (rvListRecyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        if (isLinearLayoutManager) {
            rvListRecyclerView.removeItemDecoration(gridDecoration)
            rvListRecyclerView.addItemDecoration(linearDecoration)
            rvListRecyclerView.layoutManager = linearLayoutManager
        } else {
            rvListRecyclerView.removeItemDecoration(linearDecoration)
            rvListRecyclerView.addItemDecoration(gridDecoration)
            rvListRecyclerView.layoutManager = gridLayoutManager
        }


    }
} 
