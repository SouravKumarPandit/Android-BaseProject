package com.vrlocal.android.baseproject.ui.screens.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.Observer
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityLoginBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.home.HomeActivity
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), ILoginView {
    @Inject
    lateinit var userDao: UserDao
    @Inject
    override lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindView(R.layout.activity_login)
        viewModel.attachView(this)
        applyDebouchingClickListener(btnLogin)

    }

    private fun subscribeUi(
        binding: ActivityLoginBinding,
        responseObject
        : User?
    ) {
        binding.user = responseObject

    }

    override fun onResponse(responseObject: Any?) {
    }


    override fun showError(error: String) {
        super.showError(error)
    }

    override fun isValidUser(authenticatedUser: User?) {
//        showToast("user authentication method ${authenticatedUser?.email}")
//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed({ edtUserName.error = null }, 1500)
        if (authenticatedUser != null && authenticatedUser.id > 0)
            navHomeActivity()
    }

    override fun validateUserSession(data: User?) {
    }

    private fun navHomeActivity() {

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    inner class LoginActivityListener : View.OnClickListener {
        override fun onClick(v: View?) {
            if (v!!.id == R.id.btnLogin) {


            }
        }

    }

    override fun onDebouncingClick(view: View) {
        when (view.id){
            R.id.btnLogin->{
                loginUser()
            }

        }
    }

    private fun loginUser() {

        val userName = edtUserName.text.toString()
        if (userName.isEmpty()) {
            edtUserName.error = "Please enter a valid  user name"
            edtUserName.requestFocus()
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({ edtUserName.error = null }, 1500)
            return
        }

        viewModel.authenticateUser(userName).removeObservers(this@LoginActivity)
        viewModel.authenticateUser(userName).observe(
            this@LoginActivity,
            Observer { result ->
                VResultHandler(
                    this@LoginActivity,
                    result
                ) { isValidUser(result.data) }
            })

    }
}

