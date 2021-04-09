package org.sopt.ui.view

import android.app.Activity
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.ActivityMainBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.viewmodel.HomeViewModel
import org.sopt.util.setFadeInAnim

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        setImage()
        setAnim()
    }

    private fun setImage() {
        Glide.with(this).load(R.raw.gif_move_mint).circleCrop().into(binding.ivMintLogo)
        Glide.with(this).load(R.drawable.img_dabin).circleCrop().into(binding.ivOwner)
    }

    private fun setAnim() {
        binding.ivOwner.startAnimation(setFadeInAnim(R.anim.fade_in_1000))
        binding.tvName.startAnimation(setFadeInAnim(R.anim.fade_in_1000))
        binding.tvGit.startAnimation(setFadeInAnim(R.anim.fade_in_1000))

        binding.line.startAnimation(setFadeInAnim(R.anim.fade_in_1500))

        binding.tvWelcome.startAnimation(setFadeInAnim((R.anim.fade_in_2000)))
        binding.tvIntroduce.startAnimation(setFadeInAnim((R.anim.fade_in_2000)))
    }
    
}