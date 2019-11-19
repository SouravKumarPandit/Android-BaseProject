package com.vrlocal.android.baseproject.ui.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.lifecycle.Observer
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivitySplashBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.login.LoginActivity
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import com.vrlocal.uicontrolmodule.common.VUtil
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), ISplashView {

    @Inject
    lateinit var userDao: UserDao
    @Inject
    override lateinit var viewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        viewModel.getLogeInUserData().observe(this, Observer { result ->
            VResultHandler(
                this@SplashActivity,
                result
            ) { validateUserSession(data = result.data) }
        })
        getProgressBar().visibility = View.VISIBLE
        getProgressBar().animate().translationY(VUtil.screenHeight / 3.toFloat()).duration = 1000
        ivAppLogo.translationX = -(VUtil.screenWidth / 2).toFloat()
        ivAppLogo.animate().translationX(0f).setDuration(1000).interpolator =
            AccelerateDecelerateInterpolator()
    }

    private fun navigateToActivity(boolean: Boolean) {

        val navigatorClass = if (boolean)
            HomeActivity::class.java
        else
            LoginActivity::class.java
        val i = Intent(
            this@SplashActivity,
            navigatorClass
        )
        startActivity(i)
        finishAffinity()
        /*  Handler().postDelayed({
              val i = Intent(
                  this@SplashActivity,
                  navigatorClass
              )
              startActivity(i)
              finishAffinity()
          }, 2000)*/
    }
    override fun validateUserSession(data: User?) {

        navigateToActivity(data != null)
    }

    override fun showError(error: String) {
//        super.showError(error)
        validateUserSession(null)
    }
}