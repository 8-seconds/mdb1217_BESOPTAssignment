package org.sopt.ui.view.profile

import android.app.Dialog
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.data.local.entity.ProfileData
import org.sopt.databinding.FragmentProfileBinding
import org.sopt.databinding.LayoutDetailedProfileSecondBinding
import org.sopt.databinding.LayoutProfileAddDialogBinding
import org.sopt.databinding.LayoutRepositoryListSecondBinding
import org.sopt.ui.base.BaseFragment
import org.sopt.ui.view.adapter.ProfileListAdapter
import org.sopt.ui.viewmodel.HomeViewModel
import org.sopt.util.makeDialog
import org.sopt.util.setDialog
import org.sopt.util.setFadeInAnim
import org.sopt.util.shortToastRequireContext
import widget.com.expandablelayout.ExpandableLayout

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_profile
    override val viewModel: HomeViewModel by activityViewModels()
    private lateinit var profileDialogBinding : LayoutProfileAddDialogBinding
    private lateinit var profileAddDialog : Dialog
    private lateinit var layoutDetailedProfileBinding : LayoutDetailedProfileSecondBinding
    private lateinit var layoutRepositoryListBinding : LayoutRepositoryListSecondBinding
    private val profileListAdapter = ProfileListAdapter()

    override fun initView() {
        initExpandableLayoutBinding()
        initDialogDataBinding()
        initDialog()
        initAdapter()
        initClickEvent()
        setResId()
        setAnim()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        viewModel.getProfileDataAll().observe(this, {
            profileListAdapter.setTodoList(it)
        })
    }

    private fun setResId() {
        binding.logoResId = R.raw.gif_move_rabbit
        binding.profileResId = R.drawable.img_dabin
    }

    private fun setAnim() {
        val fadeIn1000 = setFadeInAnim(requireContext(), R.anim.fade_in_1000)
        val fadeIn1500 = setFadeInAnim(requireContext(), R.anim.fade_in_1500)
        val fadeIn2000 = setFadeInAnim(requireContext(), R.anim.fade_in_2000)

        binding.apply {
            with(fadeIn1000) {
                tvWelcome.startAnimation(this)
                ivOwner.startAnimation(this)
            }

            with(fadeIn1500) {
                line.startAnimation(this)
            }

            with(fadeIn2000) {
                tvGit.startAnimation(this)
                tvIntroduce.startAnimation(this)
            }
        }
    }

    private fun initAdapter() {
        layoutDetailedProfileBinding.rvProfileList.adapter = profileListAdapter
        layoutRepositoryListBinding.rvRepositoryList.adapter
    }

    private fun initClickEvent() {
        binding.elDetailedProfile.setOnClickListener {
            if(binding.elDetailedProfile.isExpanded) {

            }
        }
        layoutDetailedProfileBinding.tvAdd.setOnClickListener { profileAddDialog.show() }

        //elRepoList.secondLayout.findViewById<ImageButton>(R.id.ib_plus).setOnClickListener {  }*/

        with(profileDialogBinding) {
            tvCancel.setOnClickListener { profileAddDialog.dismiss() }

            tvRestore.setOnClickListener {
                if(isProfileDialogEtAllEmpty())
                    shortToastRequireContext(requireContext(),getString(R.string.is_empty))
                else {
                    viewModel.insertProfileData(ProfileData(null, etTitle.text.toString(), etContent.text.toString()))
                    profileAddDialog.dismiss()
                }
            }
        }
    }

    private fun initDialog() {
        requireContext().apply {
            profileAddDialog = makeDialog(this)
        }

        setDialog(profileAddDialog, profileDialogBinding.root)
    }

    private fun initDialogDataBinding() {
        profileDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.layout_profile_add_dialog, null, false)
    }

    private fun initExpandableLayoutBinding() {
        layoutDetailedProfileBinding = binding.elDetailedProfile.contentLayoutBinding as LayoutDetailedProfileSecondBinding
        layoutRepositoryListBinding = binding.elRepoList.contentLayoutBinding as LayoutRepositoryListSecondBinding
    }

    private fun isProfileDialogEtAllEmpty()
    = profileDialogBinding.etTitle.text.isNullOrEmpty() || profileDialogBinding.etContent.text.isNullOrEmpty()
}