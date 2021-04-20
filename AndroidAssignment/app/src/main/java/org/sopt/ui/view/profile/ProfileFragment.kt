package org.sopt.ui.view.profile

import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.FragmentProfileBinding
import org.sopt.ui.base.BaseFragment
import org.sopt.ui.viewmodel.HomeViewModel
import org.sopt.util.setFadeInAnim

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_profile
    override val viewModel: HomeViewModel by activityViewModels()

    override fun initView() {
        setResId()
        setAnim()
    }

    private fun setResId() {
        binding.logoResId = R.raw.gif_move_mint
        binding.profileResId = R.drawable.img_dabin
    }

    private fun setAnim() {
        val fadeIn1000 = setFadeInAnim(requireContext(), R.anim.fade_in_1000)
        val fadeIn1500 = setFadeInAnim(requireContext(), R.anim.fade_in_1500)
        val fadeIn2000 = setFadeInAnim(requireContext(), R.anim.fade_in_2000)

        binding.apply {
            with(fadeIn1000) {
                ivOwner.startAnimation(this)
                tvName.startAnimation(this)
                tvGit.startAnimation(this)
            }

            with(fadeIn1500) {
                line.startAnimation(this)
            }

            with(fadeIn2000) {
                tvWelcome.startAnimation(this)
                tvIntroduce.startAnimation(this)
            }
        }
    }
}