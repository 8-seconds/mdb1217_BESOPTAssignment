package org.sopt.ui.view

import androidx.activity.viewModels
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
        setResId()
        setAnim()
    }

    private fun setResId() {
        binding.logoResId = R.raw.gif_move_mint
        binding.profileResId = R.drawable.img_dabin
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