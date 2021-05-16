package org.sopt.ui.view.user

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.sopt.R
import org.sopt.data.local.SOPTSharedPreference.getAutoLogin
import org.sopt.data.local.SOPTSharedPreference.getName
import org.sopt.data.local.SOPTSharedPreference.setAutoLogin
import org.sopt.data.remote.model.request.ReqSignIn
import org.sopt.databinding.ActivitySignInBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.view.MainActivity
import org.sopt.ui.viewmodel.UserViewModel
import org.sopt.util.EventObserver
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
        binding.lifecycleOwner = this
        autoLogin()
        initClickEvent()
        initFocusEvent()
    }

    override fun initAfterBinding() {
        observeSignInResult()
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

            when(isEtIdEmpty() || isEtPasswordEmpty()) {
                true -> shortToast(getString(R.string.is_empty))
                else -> lifecycleScope.launch {
                    viewModel.postSignIn(ReqSignIn(email = id, password = password)).join()
                }
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
            startMainActivity()
        }
    }

    private fun observeSignInResult() {
        viewModel.signInEvent.observe(this, EventObserver{
            when(it) {
                true -> {
                    shortToast(getString(R.string.welcome) + getName() + getString(R.string.sir))
                    setAutoLogin(binding.ibCheckBox.isSelected)
                    startMainActivity()
                }
                else -> shortToast(getString(R.string.not_match_id_password))
            }
        })
    }


    private fun isEtIdEmpty() : Boolean = binding.etId.text.isNullOrEmpty()

    private fun isEtPasswordEmpty() : Boolean = binding.etPassword.text.isNullOrEmpty()

    private fun startMainActivity() {
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finish()
    }
}