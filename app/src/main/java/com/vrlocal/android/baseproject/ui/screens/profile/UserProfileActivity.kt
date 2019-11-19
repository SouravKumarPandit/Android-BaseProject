package com.vrlocal.android.baseproject.ui.screens.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityProfileBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import javax.inject.Inject


class  UserProfileActivity : BaseActivity<ActivityProfileBinding,UserProfileViewHolder>() , IUserProfileView {


    @Inject
    lateinit var userDao: UserDao

    @Inject
    override lateinit var viewModel: UserProfileViewHolder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindView(R.layout.activity_profile)
        viewModel.attachView(this)

        viewModel.getUserInfo().observe(this, Observer { result ->
            VResultHandler(
                this@UserProfileActivity,
                result
            ) { subscribedUI(result.data) }
        })
//        val drawable = IconFontDrawable(this, R.string.ic_av_timer)
//        drawable.setTextColor(ContextCompat.getColor(this, android.R.color.black))
//        drawable.textSize=25.px.toFloat()
//        iconText.background=drawable
    }

    private fun subscribedUI(data: User?) {
        bindingData.userData=data
    }
}