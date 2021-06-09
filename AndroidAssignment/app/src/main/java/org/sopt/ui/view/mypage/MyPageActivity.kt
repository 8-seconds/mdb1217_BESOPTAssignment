package org.sopt.ui.view.mypage

import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import org.sopt.R
import org.sopt.data.local.SOPTSharedPreference.clearStorage
import org.sopt.data.local.SOPTSharedPreference.getName
import org.sopt.databinding.ActivityMyPageBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.view.user.SignInActivity
import org.sopt.ui.viewmodel.UserViewModel

class MyPageActivity : BaseActivity<ActivityMyPageBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_my_page
    override val viewModel: UserViewModel by viewModels()


    override fun initView() {
        initToolBar()
        initUserName()
        initClickEvent()
    }

    private fun initUserName() {
        binding.name = getName()
    }

    private fun initToolBar() {
        setSupportActionBar(binding.tbMypage)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tbMypage.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initClickEvent() {
        binding.clLogout.setOnClickListener {
            clearStorage()
            startSignIn()
            finishAffinity()
        }

        binding.ibBlog.setOnClickListener {
            startContact(BLOG_URL)
        }

        binding.ibGithub.setOnClickListener {
            startContact(GIT_URL)
        }
    }

    private fun startSignIn() {
        startActivity(Intent(this@MyPageActivity, SignInActivity::class.java))
    }

    private fun startContact(url : String) {
        with(Intent(Intent.ACTION_VIEW)) {
            data = Uri.parse(url)
            startActivity(this)
        }
    }

    companion object {
        const val BLOG_URL = "https://blog.naver.com/mdb1217"
        const val GIT_URL = "https://github.com/mdb1217"
    }
}