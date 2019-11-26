package com.vrlocal.android.baseproject.ui.screens.photos

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityListBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photos
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class PhotosActivity : BaseActivity<ActivityListBinding, PhotosViewModel>() {

    @Inject
    override lateinit var viewModel: PhotosViewModel

    override val transparentStatusBar: Boolean = true;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_list)
        bindingData.title = "Photos List"
        loadPhotosList()
        rvListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )


    }

    private fun loadPhotosList() {

        viewModel.getPhotos().observe(
            this,
            Observer { result -> VResultHandler(this, result) { onGettingPhotos(result.data) } })

    }

    private fun onGettingPhotos(data: Photos?) {
        showToast("got response")
    }
}
