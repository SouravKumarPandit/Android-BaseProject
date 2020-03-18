package com.vrlocal.android.baseproject.ui.screens.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.vrlocal.android.baseproject.R
import com.vrlocal.android.baseproject.api.VResultHandler
import com.vrlocal.android.baseproject.databinding.ActivityProfileBinding
import com.vrlocal.android.baseproject.ui.base.BaseActivity
import com.vrlocal.android.baseproject.ui.screens.login.LoginActivity
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import kotlinx.android.synthetic.main.activity_profile.*
import javax.inject.Inject


class UserProfileActivity : BaseActivity<ActivityProfileBinding, UserProfileViewModel>(),
    IUserProfileView {

    @Inject
    override lateinit var viewModel: UserProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindView(R.layout.activity_profile)
        viewModel.attachView(this)

        setSupportActionBar(tbProfileToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Profile"
    }

    private fun subscribedUI(data: User?) {
        bindingData.userData = data
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserInfo().observe(this, Observer { result ->
            VResultHandler(
                this@UserProfileActivity,
                result
            ) { subscribedUI(result.data) }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            viewModel.logoutUser().observe(this, Observer { result ->
                VResultHandler(
                    this@UserProfileActivity,
                    result
                ) { logoutCurrentUser(result.data) }
            })
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logoutCurrentUser(logout: Boolean?) {
        if (logout!!){

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

    override fun onDebouncingClick(view: View) {
    }


}