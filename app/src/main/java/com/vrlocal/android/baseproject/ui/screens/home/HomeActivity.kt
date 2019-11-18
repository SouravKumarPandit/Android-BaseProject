package com.vrlocal.android.baseproject.ui.screens.home

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.databinding.ActivityHomeBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.common.GridSpacingItemDecoration
import com.vrlocal.android.baseproject.ui.screens.alubums.AlbumsActivity
import com.vrlocal.android.baseproject.ui.screens.comments.CommentsActivity
import com.vrlocal.android.baseproject.ui.screens.home.data.HomeOptions
import com.vrlocal.android.baseproject.ui.screens.photos.PhotosActivity
import com.vrlocal.android.baseproject.ui.screens.posts.PostActivity
import com.vrlocal.android.baseproject.ui.screens.profile.UserProfileActivity
import com.vrlocal.android.baseproject.ui.screens.todo.TodoActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), IHomeView {

    @Inject
    override lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindView(R.layout.activity_home)
        viewModel.attachView(this)

        rvDashBoard.adapter = HomeAdapter(this, getOptionList())
        rvDashBoard.addItemDecoration(
            GridSpacingItemDecoration(
                spanCount = 2,
                spacing = 10.px,
                includeEdge = true
            )
        )
        rvDashBoard.layoutManager = GridLayoutManager(this, 2)

    }
    private fun getOptionList(): List<HomeOptions> {
        val list: ArrayList<HomeOptions> = ArrayList<HomeOptions>()

        val arrayListOf = arrayListOf("users", "posts", "comments", "albums", "photos", "todos");
        val classList = arrayListOf(
            UserProfileActivity::class.java,
            PostActivity::class.java,
            CommentsActivity::class.java,
            AlbumsActivity::class.java,
            PhotosActivity::class.java,
            TodoActivity::class.java
        );
        arrayListOf.forEachIndexed(){
            index, sValue->
            val homeOptions = HomeOptions(sValue, 0, "\\$sValue",classList[index])
            list.add(homeOptions)
        }

        return list;
    }
}