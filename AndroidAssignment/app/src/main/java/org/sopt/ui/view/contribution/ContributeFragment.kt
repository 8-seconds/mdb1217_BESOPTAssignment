package org.sopt.ui.view.contribution


import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.FragmentContributeBinding
import org.sopt.ui.base.BaseFragment
import org.sopt.ui.viewmodel.HomeViewModel

@AndroidEntryPoint
class ContributeFragment : BaseFragment<FragmentContributeBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_contribute
    override val viewModel: HomeViewModel by activityViewModels()

}