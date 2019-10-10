package com.vrlocal.android.baseproject.ui.widgets.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.data.VResult
import com.vrlocal.android.baseproject.databinding.ActivityLoginBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.widgets.home.MainActivity
import com.vrlocal.android.baseproject.ui.widgets.login.data.User
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject



class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), ILoginView {


    @Inject
    override lateinit var baseViewModel: LoginViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.bindView(com.vrlocal.android.baseproject.R.layout.activity_login)
        baseViewModel.attachView(this)


        btnLogin.setOnClickListener(View.OnClickListener { _ ->
            //            baseViewModel.authenticateUser
            val userNumber = edtUserId.text.toString()
            if (userNumber.isEmpty())
                return@OnClickListener
//            Crashlytics.getInstance().crash()
//            val userNumber = edtUserId.text.toString()
            baseViewModel.authenticateUser(userNumber.toInt()).removeObservers(this)
            baseViewModel.authenticateUser(userNumber.toInt())
                .observe(this, Observer { result: VResult<User> ->
                    VResultHandler(
                        this,
                        result
                    )
                })


        })


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
            showSnackBar("response $responseObject", 0)
            subscribeUi(dataBinding, responseObject)

        }
    }


    override fun showError(error: String) {
        super.showError(error)
        val intent = Intent(this,MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//        finishAffinity()
//        startHomeActivity()

        startActivity(intent)
    }


    override fun loginUser(items: String) {

    }

    override fun logoutUser(items: String) {
    }

    override fun authenticateUser(items: String) {

    }

}