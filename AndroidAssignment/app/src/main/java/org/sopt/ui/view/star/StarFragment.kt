package org.sopt.ui.view.star

import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.FragmentStarBinding
import org.sopt.ui.base.BaseFragment
import org.sopt.ui.view.adapter.StarCardAdapter
import org.sopt.ui.viewmodel.HomeViewModel

@AndroidEntryPoint
class StarFragment : BaseFragment<FragmentStarBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_star
    override val viewModel: HomeViewModel by activityViewModels()
    private val starCardAdapter = StarCardAdapter()

    override fun initView() {
        initStarViewPager()
    }

    override fun initAfterBinding() {
        viewModel.getStaredRepo().observe(viewLifecycleOwner, {
            starCardAdapter.data = it
        })
    }

    private fun initStarViewPager() {
        binding.vpStar.apply { this.adapter = starCardAdapter }
    }
}