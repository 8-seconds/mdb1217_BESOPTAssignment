package org.sopt.ui.view.contribution


import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.FragmentContributeBinding
import org.sopt.ui.base.BaseFragment
import org.sopt.ui.view.adapter.FollowerListAdapter
import org.sopt.ui.viewmodel.HomeViewModel

@AndroidEntryPoint
class ContributeFragment : BaseFragment<FragmentContributeBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_contribute
    override val viewModel: HomeViewModel by activityViewModels()
    private val followerListAdapter = FollowerListAdapter()

    override fun initView() {
        initAdapter()
    }

    override fun initBeforeBinding() {
        viewModel.getFollower()
    }

    override fun initAfterBinding() {
        observeFollowerData()
    }

    private fun initAdapter() { binding.rvFollowerList.adapter = followerListAdapter }

    private fun observeFollowerData() {
        viewModel.followerList.observe(viewLifecycleOwner, {
            followerListAdapter.data = it
        })
    }
}