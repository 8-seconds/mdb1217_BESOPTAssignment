package org.sopt.ui.view.user

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.data.local.SOPTSharedPreference.getAutoLogin
import org.sopt.data.local.SOPTSharedPreference.getIdPasswordExist
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

    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
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

        when(isEtIdEmpty()) {
            true -> setIdGray()
            false -> setIdSky()
        }

        when(isEtPasswordEmpty()) {
            true -> setPasswordGray()
            false -> setPasswordSky()
        }
    }

    override fun initView() {
        autoLogin()
        initClickEvent()
        initFocusEvent()
    }

    private fun initFocusEvent() {
        binding.etId.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)
                setIdSky()
            else {
                if(isEtIdEmpty())
                    setIdGray()
            }
        }

        binding.etPassword.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)
                setPasswordSky()
            else {
                if(isEtPasswordEmpty())
                   setPasswordGray()
            }
        }
    }

    private fun initClickEvent() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.findPasswordById(id, password)

            if(isEtIdEmpty() || isEtPasswordEmpty())
                shortToast(getString(R.string.is_empty))
            else {
                if(getIdPasswordExist()) {
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
            requestActivity.launch(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }

    private fun autoLogin() {
        if(getAutoLogin()) {
            shortToast(getString(R.string.auto_login_done))
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        }
    }

    private fun setIdSky() {
        binding.clId.setBackgroundResource(R.drawable.rectangle_border_sky_10)
        binding.ivMail.setBackgroundResource(R.drawable.ic_baseline_mail_sky)
    }

    private fun setIdGray() {
        binding.clId.setBackgroundResource(R.drawable.rectangle_fill_gray_10)
        binding.ivMail.setBackgroundResource(R.drawable.ic_baseline_mail)
    }

    private fun setPasswordSky() {
        binding.clPassword.setBackgroundResource(R.drawable.rectangle_border_sky_10)
        binding.ivLock.setBackgroundResource(R.drawable.ic_lock_sky)
    }

    private fun setPasswordGray() {
        binding.clPassword.setBackgroundResource(R.drawable.rectangle_fill_gray_10)
        binding.ivLock.setBackgroundResource(R.drawable.ic_lock)
    }

    private fun isEtIdEmpty() : Boolean {
        return binding.etId.text.isNullOrEmpty()
    }

    private fun isEtPasswordEmpty() : Boolean {
        return binding.etPassword.text.isNullOrEmpty()
    }
}