package com.vrlocal.android.baseproject.ui.screens.home

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.databinding.ActivityHomeBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.common.GridSpacingItemDecoration
import com.vrlocal.android.baseproject.ui.common.MiddleDividerItemDecoration
import com.vrlocal.android.baseproject.ui.screens.alubums.AlbumsActivity
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

        rvDashBoard.adapter = HomeAdapter(this, getOptionList())
          rvDashBoard.addItemDecoration(
              GridSpacingItemDecoration(
                  spanCount = 2,
                  spacing = 2.px,
                  includeEdge = true
              )
          )
        val middleDividerItemDecoration =
            MiddleDividerItemDecoration(this, MiddleDividerItemDecoration.ALL)
        middleDividerItemDecoration.setDividerColor(Color.GRAY)
        /*FOR MAKING DIVIDER SQUARE*/
//        rvDashBoard.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
//        rvDashBoard.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvDashBoard.addItemDecoration(middleDividerItemDecoration)
        rvDashBoard.setHasFixedSize(true)
        rvDashBoard.layoutManager = GridLayoutManager(this, 2)

    }

    private fun getOptionList(): List<HomeOption> {
        val list: ArrayList<HomeOption> = ArrayList<HomeOption>()

        val arrayListOf = arrayListOf("users", "posts", "comments", "albums", "photos", "todos");
        val iconList = arrayListOf(
            R.string.ic_user,
            R.string.ic_local_post_office,
            R.string.ic_comment,
            R.string.ic_photo_album,
            R.string.ic_photo,
            R.string.ic_calendar_check_o
        );

        val classList = arrayListOf(
            UserProfileActivity::class.java,
            PostActivity::class.java,
            CommentsActivity::class.java,
            AlbumsActivity::class.java,
            PhotosActivity::class.java,
            TodoActivity::class.java
        );
        arrayListOf.forEachIndexed() { index, sValue ->
            val homeOptions = HomeOption(sValue, iconList[index], "\\$sValue", classList[index])
            list.add(homeOptions)
        }

        return list;
    }
}