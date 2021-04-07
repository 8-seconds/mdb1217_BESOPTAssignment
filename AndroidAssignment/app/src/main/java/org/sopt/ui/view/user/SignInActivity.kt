package org.sopt.ui.view.user

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.data.local.SOPTSharedPreference.getAutoLogin
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

    override fun initView() {
        autoLogin()
        clickEvent()
        initFocusEvent()
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    private fun initFocusEvent() {
        binding.etId.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                binding.clId.setBackgroundResource(R.drawable.rectangle_border_sky_10)
                binding.ivMail.setBackgroundResource(R.drawable.ic_baseline_mail_sky)
            }
            else {
                if(binding.etId.text.isEmpty()) {
                    binding.clId.setBackgroundResource(R.drawable.rectangle_fill_gray_10)
                    binding.ivMail.setBackgroundResource(R.drawable.ic_baseline_mail)
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                binding.clPassword.setBackgroundResource(R.drawable.rectangle_border_sky_10)
                binding.ivLock.setBackgroundResource(R.drawable.ic_lock_sky)
            }
            else {
                if(binding.etPassword.text.isEmpty()) {
                    binding.clPassword.setBackgroundResource(R.drawable.rectangle_fill_gray_10)
                    binding.ivLock.setBackgroundResource(R.drawable.ic_lock)
                }
            }
        }
    }

    private fun clickEvent() {
        binding.btnLogin.setOnClickListener {
            if(binding.etId.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty())
                shortToast("빈 칸이 있습니다!")
            else {
                setAutoLogin(binding.ibCheckBox.isSelected)
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }
        }

        binding.ibCheckBox.setOnClickListener {
            it.isSelected = !it.isSelected
        }

        binding.tvSignUp.setOnClickListener {
            val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                if(activityResult.resultCode == Activity.RESULT_OK) {

                }
            }
        }
    }

    private fun autoLogin() {
        if(getAutoLogin()) {
            shortToast("자동로그인 되었습니다")
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        }
    }

}