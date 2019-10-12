package com.vrlocal.android.baseproject.ui.widgets.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityLoginBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.widgets.home.MainActivity
import com.vrlocal.android.baseproject.ui.widgets.login.data.User
import com.vrlocal.android.baseproject.ui.widgets.login.data.UserDao
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), ILoginView {


    @Inject
    lateinit var userDao: UserDao

    @Inject
    override lateinit var baseViewModel: LoginViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.bindView(R.layout.activity_login)
        baseViewModel.attachView(this)

        btnCacheNetwork.setOnClickListener(View.OnClickListener { _ ->
            val userNumber = edtUserId.text.toString()
            if (userNumber.isEmpty())
                return@OnClickListener
//            baseViewModel.userId = userNumber
            baseViewModel.cacheOrNetwork(userNumber).removeObservers(this)
            baseViewModel.cacheOrNetwork(userNumber)
                .observe(this, Observer { result -> VResultHandler(this, result) })

        })

        btnNetwork.setOnClickListener(View.OnClickListener { _ ->
            val userNumber = edtUserId.text.toString()
            if (userNumber.isEmpty())
                return@OnClickListener
            baseViewModel.networkUser(userNumber).removeObservers(this)
            baseViewModel.networkUser(userNumber)
                .observe(this, Observer { result -> VResultHandler(this, result) })
        })
        btnDatabase.setOnClickListener(View.OnClickListener { _ ->

            val userNumber = edtUserId.text.toString()
            if (userNumber.isEmpty())
                return@OnClickListener
            baseViewModel.cacheUser(userNumber).removeObservers(this)
            baseViewModel.cacheUser(userNumber)
                .observe(this, Observer { result -> VResultHandler(this, result) })

//            navHomeActivity();

//            for testing crashlatics
//            Crashlytics.getInstance().crash()

        })


    }

    private fun navHomeActivity() {

        val intent = Intent(this, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
//        finishAffinity()
    }

    private fun subscribeUi(
        binding: ActivityLoginBinding,
        responseObject
        : User
    ) {
        binding.user = responseObject

    }

    override fun onResponse(responseObject: Any?) {
        super.onResponse(responseObject)
        if (responseObject is User) {
            subscribeUi(dataBinding, responseObject)
            showSnackBar(message = responseObject.name, statusColor = 0)

        }
    }


    override fun showError(error: String) {
        super.showError(error)
    }


    override fun loginUser(items: String) {

    }

    override fun logoutUser(items: String) {
    }

    override fun authenticateUser(items: String) {

    }

}
