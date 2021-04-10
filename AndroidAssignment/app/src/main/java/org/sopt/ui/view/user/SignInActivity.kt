package org.sopt.ui.view.user

import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.ActivitySignInBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.viewmodel.UserViewModel

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_in
    override val viewModel: UserViewModel by viewModels()

    override fun initView() {
        Log.d("hi", "안녕하세요")
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}