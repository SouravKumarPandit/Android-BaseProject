package com.vrlocal.android.baseproject.ui.screens.photos

import android.os.Bundle
import androidx.lifecycle.Observer
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityListBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo
import javax.inject.Inject

class PhotosActivity : BaseActivity<ActivityListBinding, PhotosViewModel>() {

    @Inject
    override lateinit var viewModel: PhotosViewModel

    override val transparentStatusBar: Boolean = true;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val paginationView:PaginationRecyclerView= PaginationRecyclerView(this)
//        paginationView.setBackgroundColor(Color.LTGRAY)

//        paginationView.setHolderListener(viewModel)
//
//        setContentView(paginationView)
        bindView(R.layout.activity_list)
        bindingData.title = "Photos List"
        loadPhotosList()
        /*
         rvListRecyclerView.addItemDecoration(
             DividerItemDecoration(
                 this,
                 DividerItemDecoration.VERTICAL
             )
         )*/

    }

    private fun loadPhotosList() {

        viewModel.getPhotos().observe(
            this,
            Observer { result -> VResultHandler(this, result) { onGettingPhotos(result.data) } })

    }

    private fun onGettingPhotos(data: List<Photo>?) {
        showToast("got response")
//        val adapter:PaginationAdapter = PaginationAdapter(this, this);
//        rvListRecyclerView.adapter=adapter;

    }
}
