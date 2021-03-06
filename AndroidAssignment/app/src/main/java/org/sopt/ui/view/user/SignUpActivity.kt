package org.sopt.ui.view.user

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.data.local.SOPTSharedPreference
import org.sopt.data.remote.model.request.ReqSignUp
import org.sopt.databinding.ActivitySignUpBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.viewmodel.UserViewModel
import org.sopt.util.EventObserver
import org.sopt.util.shortToast

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: UserViewModel by viewModels()

    override fun initView() {
        initFocusEvent()
        initClickEvent()
    }

    override fun initAfterBinding() {
        observeSignUpResult()
    }

    private fun initFocusEvent() {
        binding.etName.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)
                binding.isNameExist = true
            else {
                if(isEtNameEmpty())
                    binding.isNameExist = false
            }
        }

        binding.etId.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus)
                binding.isEmailExist = true
            else {
                if(isEtIdEmpty())
                    binding.isEmailExist = false
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
        binding.btnSignUp.setOnClickListener {
            if(isAllEditTextEmpty())
                shortToast(getString(R.string.is_empty))
            else {
                val name = binding.etName.text.toString()
                val id = binding.etId.text.toString()
                val password = binding.etPassword.text.toString()

                viewModel.postSignUp(ReqSignUp(id, password, getString(R.string.one), name, getString(R.string.phone), getString(R.string.birth)))

                setResult(Activity.RESULT_OK, Intent().putExtra("name", name)
                    .putExtra("id", id)
                    .putExtra("password", password)
                )
            }
        }
    }

    private fun observeSignUpResult() {
        viewModel.signUpEvent.observe(this, EventObserver{
            when(it) {
                true -> {
                    shortToast(getString(R.string.sign_up_done))
                    finish()
                }
                else -> shortToast(getString(R.string.sign_up_fail))
            }
        })
    }

    private fun isAllEditTextEmpty() : Boolean = isEtNameEmpty() || isEtIdEmpty() || isEtPasswordEmpty()

    private fun isEtNameEmpty() : Boolean = binding.etName.text.isNullOrEmpty()

    private fun isEtIdEmpty() : Boolean = binding.etId.text.isNullOrEmpty()

    private fun isEtPasswordEmpty() : Boolean = binding.etPassword.text.isNullOrEmpty()

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
    }

}