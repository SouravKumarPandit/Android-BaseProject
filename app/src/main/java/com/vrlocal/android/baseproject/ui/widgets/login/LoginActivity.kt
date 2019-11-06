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
            baseViewModel.cacheOrNetwork(userNumber).removeObservers(this)
            baseViewModel.cacheOrNetwork(userNumber).observe(
                this,
                Observer { result ->
                    VResultHandler(
                        this,
                        result
                    ) { authenticateUser(result.data) }
                })
        })

        btnNetwork.setOnClickListener(View.OnClickListener { _ ->
            val userNumber = edtUserId.text.toString()
            if (userNumber.isEmpty())
                return@OnClickListener
            baseViewModel.networkUser(userNumber).removeObservers(this)
            baseViewModel.networkUser(userNumber)
                .observe(
                    this,
                    Observer { result -> VResultHandler(this, result) { logoutUser(result.data) } })
        })
        btnDatabase.setOnClickListener(View.OnClickListener { _ ->
            val userNumber = edtUserId.text.toString()
            if (userNumber.isEmpty())
                return@OnClickListener
            baseViewModel.cacheUser().removeObservers(this)
            baseViewModel.cacheUser()
                .observe(
                    this,
                    Observer { result -> VResultHandler(this, result) { loginUser(result.data) } })
        })
        btnDeleteData.setOnClickListener(View.OnClickListener { _ ->

            val userNumber = edtUserId.text.toString()
            if (userNumber.isEmpty())
                return@OnClickListener
            baseViewModel.deleteUser().observe(this, Observer { _ ->
                txtName.text = ""
                txtEmail.text = ""
                txtAddress.text = ""
                txtCompany.text = ""

            })

        })

        btnClearText.setOnClickListener(View.OnClickListener { _ ->
            txtName.text = ""
            txtEmail.text = ""
            txtAddress.text = ""
            txtCompany.text = ""

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
        : User?
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

    override fun authenticateUser(authenticatedUser: User?) {
        showToast("user authentication method ${authenticatedUser?.email}")
        subscribeUi(dataBinding, authenticatedUser)
    }

    override fun loginUser(response: User?) {
        showToast("user Login method ${response?.email}")
        subscribeUi(dataBinding, response)
    }

    override fun logoutUser(items: User?) {
        showToast(
            "user Logout method ${items?.email}"
        )
        subscribeUi(dataBinding, items)

    }


}
