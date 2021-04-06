package org.sopt.ui.view.user

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.ActivitySignUpBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.viewmodel.UserViewModel

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: UserViewModel by viewModels()

    override fun initView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }
}