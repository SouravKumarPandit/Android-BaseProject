package com.vrlocal.android.baseproject.ui.screens.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityHomeBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.common.MiddleDividerItemDecoration
import com.vrlocal.android.baseproject.ui.screens.albums.AlbumsActivity
import com.vrlocal.android.baseproject.ui.screens.comments.CommentsActivity
import com.vrlocal.android.baseproject.ui.screens.home.data.HomeOption
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
        getDashBoardOption()

    }

    private fun setRecyclerViewData(data: List<HomeOption>) {
        rvDashBoard.adapter =
            HomeAdapter(this, data, clickedListener = { onHomeOptionSelected(it) })

        val middleDividerItemDecoration =
            MiddleDividerItemDecoration(this, MiddleDividerItemDecoration.ALL)
        middleDividerItemDecoration.setDividerColor(Color.GRAY)
        rvDashBoard.addItemDecoration(middleDividerItemDecoration)
        rvDashBoard.setHasFixedSize(true)
        rvDashBoard.layoutManager = GridLayoutManager(this, 3)
    }

    private fun onHomeOptionSelected(position: Int) {
        val activityClass: Class<out AppCompatActivity>
        when (position) {
            0 -> activityClass = UserProfileActivity::class.java
            1 -> activityClass = PostActivity::class.java
            2 -> activityClass = CommentsActivity::class.java
            3 -> activityClass = AlbumsActivity::class.java
            4 -> activityClass = PhotosActivity::class.java
            5 -> activityClass = TodoActivity::class.java
            else -> activityClass = UserProfileActivity::class.java
        }
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    override fun getDashBoardOption() {
        viewModel.getDashBoardOption().observe(this, Observer { result ->
            VResultHandler(this, result) {
                setRecyclerViewData(
                    result.data!!
                )
            }
        }) //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDebouncingClick(view: View) {
    }

}

/*
 Knowledge gain


        /*FOR MAKING DIVIDER SQUARE*/
//        rvDashBoard.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
//        rvDashBoard.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
*
*
   /*  rvDashBoard.addItemDecoration(
              GridSpacingItemDecoration(
                  spanCount = 2,
                  spacing = 2.px,
                  includeEdge = true
              )
          )*/



* */