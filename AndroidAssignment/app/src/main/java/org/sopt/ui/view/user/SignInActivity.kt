package org.sopt.ui.view.user

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.data.local.SOPTSharedPreference.getAutoLogin
import org.sopt.data.local.SOPTSharedPreference.getName
import org.sopt.data.local.SOPTSharedPreference.setAutoLogin
import org.sopt.databinding.ActivitySignInBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.view.MainActivity
import org.sopt.ui.viewmodel.UserViewModel
import org.sopt.util.shortToast

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_in
    override val viewModel: UserViewModel by viewModels()

    private val requestIdAndPassActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if(activityResult.resultCode == Activity.RESULT_OK) {
            val intent = activityResult.data
            if (intent != null) {
                binding.etId.setText(intent.extras?.getString("id"))
                binding.etPassword.setText(intent.extras?.getString("password"))
            }
        }
        else {
            binding.etId.text.clear()
            binding.etPassword.text.clear()
        }

        binding.isIdExist = !isEtIdEmpty()
        binding.isPassExist = !isEtPasswordEmpty()
    }

    override fun initView() {
        autoLogin()
        initClickEvent()
        initFocusEvent()
    }

    private fun initFocusEvent() {
        binding.etId.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)
                binding.isIdExist = true
            else {
                if(isEtIdEmpty())
                    binding.isIdExist = false
            }
        }

        binding.etPassword.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)
                binding.isPassExist = true
            else {
                if(isEtPasswordEmpty())
                   binding.isPassExist = false
            }
        }
    }

    private fun initClickEvent() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()

            if(isEtIdEmpty() || isEtPasswordEmpty())
                shortToast(getString(R.string.is_empty))
            else {
                if(viewModel.findPasswordById(id, password)) {
                    shortToast(getString(R.string.welcome) + getName() + getString(R.string.sir))
                    setAutoLogin(binding.ibCheckBox.isSelected)
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
                else
                    shortToast(getString(R.string.not_match_id_password))
            }
        }

        binding.ibCheckBox.setOnClickListener {
            it.isSelected = !it.isSelected
        }

        binding.tvSignUp.setOnClickListener {
            requestIdAndPassActivity.launch(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }

    private fun autoLogin() {
        if(getAutoLogin()) {
            shortToast(getString(R.string.auto_login_done))
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        }
    }

    private fun isEtIdEmpty() : Boolean = binding.etId.text.isNullOrEmpty()

    private fun isEtPasswordEmpty() : Boolean = binding.etPassword.text.isNullOrEmpty()
}