package org.sopt.ui.view.profile

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.data.local.entity.ProfileData
import org.sopt.data.local.entity.RepoData
import org.sopt.databinding.*
import org.sopt.ui.base.BaseFragment
import org.sopt.ui.view.adapter.ItemTouchHelperCallback
import org.sopt.ui.view.adapter.ProfileListAdapter
import org.sopt.ui.view.adapter.RepoListAdapter
import org.sopt.ui.viewmodel.HomeViewModel
import org.sopt.util.CalendarUtil.convertCalendarToString
import org.sopt.util.makeDialog
import org.sopt.util.setDialog
import org.sopt.util.setFadeInAnim
import org.sopt.util.shortToastRequireContext
import widget.com.expandablelayout.ExpandableLayout.OnExpandedListener
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_profile
    override val viewModel: HomeViewModel by activityViewModels()
    private lateinit var profileDialogBinding : LayoutProfileAddDialogBinding
    private lateinit var repoDialogBinding : LayoutRepositoryAddDialogBinding
    private lateinit var profileAddDialog : Dialog
    private lateinit var repoAddDialog : Dialog
    private lateinit var layoutDetailedProfileParentBinding : LayoutDetailedProfileParentBinding
    private lateinit var layoutDetailedProfileBinding : LayoutDetailedProfileSecondBinding
    private lateinit var layoutRepositoryListParentBinding : LayoutRepositoryListParentBinding
    private lateinit var layoutRepositoryListBinding : LayoutRepositoryListSecondBinding
    private val profileListAdapter = ProfileListAdapter()
    private val repoListAdapter = RepoListAdapter()

    override fun initView() {
        initExpandableLayoutBinding()
        initDialogDataBinding()
        initDialog()
        initAdapter()
        initExpandEvent()
        initClickEvent()
        setResId()
        setAnim()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        viewModel.getProfileDataAll().observe(this, {
            profileListAdapter.data = it
        })

        viewModel.getRepoDataAll().observe(this, {
            repoListAdapter.data = it
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
        profileListAdapter.setDeleteButtonClickListener { viewModel.deleteProfileData(it) }

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(repoListAdapter))
        itemTouchHelper.attachToRecyclerView(layoutRepositoryListBinding.rvRepositoryList)

        layoutRepositoryListBinding.rvRepositoryList.adapter = repoListAdapter
        repoListAdapter.setStarButtonClickListener { id, isSelected ->
            when(isSelected) {
                true -> viewModel.updateStar(STAR, id)
                else -> viewModel.updateStar(NONE_STAR, id)
            }
        }

        repoListAdapter.setDataSwipeListener {
            viewModel.deleteRepoData(it)
        }
    }

    private fun initClickEvent() {
        layoutDetailedProfileParentBinding.ibSpanner.setOnClickListener { profileAddDialog.show() }
        layoutRepositoryListParentBinding.ibSpanner.setOnClickListener { repoAddDialog.show() }

        with(profileDialogBinding) {
            tvCancel.setOnClickListener { profileAddDialog.dismiss() }

            tvRestore.setOnClickListener {
                if(isProfileDialogEtAllEmpty())
                    shortToastRequireContext(requireContext(), getString(R.string.is_empty))
                else {
                    viewModel.insertProfileData(ProfileData(null, etTitle.text.toString(), etContent.text.toString(), convertCalendarToString(Calendar.getInstance())))
                    profileAddDialog.dismiss()
                }
            }
        }

        with(repoDialogBinding) {
            var type = NOT_SELECTED
            tvCancel.setOnClickListener { repoAddDialog.dismiss() }

            tvRestore.setOnClickListener {
                if(isRepoDialogEtAllEmpty())
                    shortToastRequireContext(requireContext(), getString(R.string.is_empty))
                else {
                    when (isRepoDialogAllIbNotClicked()) {
                        true -> shortToastRequireContext(requireContext(), getString(R.string.plz_one_clicked))
                        else -> {
                            viewModel.insertRepoData(RepoData(null, etTitle.text.toString(), etContent.text.toString(), etLanguage.text.toString(), type, false))
                            repoAddDialog.dismiss()
                        }
                    }
                }
            }

            ibBulb.setOnClickListener {
                it.isSelected = !it.isSelected
                if(it.isSelected)
                    type = INFORMATION
                ibPeople.isSelected = false
                ibStudy.isSelected = false
            }

            ibPeople.setOnClickListener {
                it.isSelected = !it.isSelected
                if(it.isSelected)
                    type = TEAM_PLAY
                ibBulb.isSelected = false
                ibStudy.isSelected = false
            }

            ibStudy.setOnClickListener {
                it.isSelected = !it.isSelected
                if(it.isSelected)
                    type = STUDY
                ibBulb.isSelected = false
                ibPeople.isSelected = false
            }
        }
    }

    private fun initDialog() {
        requireContext().apply {
            profileAddDialog = makeDialog(this)
            repoAddDialog = makeDialog(this)
        }

        setDialog(profileAddDialog, profileDialogBinding.root)
        setDialog(repoAddDialog, repoDialogBinding.root)
    }

    private fun initDialogDataBinding() {
        profileDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.layout_profile_add_dialog, null, false)
        repoDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.layout_repository_add_dialog, null, false)
    }

    private fun initExpandableLayoutBinding() {
        layoutDetailedProfileParentBinding = binding.elDetailedProfile.headerLayoutBinding as LayoutDetailedProfileParentBinding
        layoutDetailedProfileBinding = binding.elDetailedProfile.contentLayoutBinding as LayoutDetailedProfileSecondBinding
        layoutRepositoryListBinding = binding.elRepoList.contentLayoutBinding as LayoutRepositoryListSecondBinding
        layoutRepositoryListParentBinding = binding.elRepoList.headerLayoutBinding as LayoutRepositoryListParentBinding
    }

    private fun initExpandEvent() {
        binding.elDetailedProfile.setOnExpandedListener(object : OnExpandedListener() {
            override fun onExpandChanged(view: View?, isExpanded: Boolean) {
                //handle onExpandChanged
                if(binding.elDetailedProfile.isExpanded) {
                    val profileData = viewModel.getProfileDataAll().value
                    if(profileData != null)
                        profileListAdapter.data = profileData
                }
            }
        })

        binding.elRepoList.setOnExpandedListener(object : OnExpandedListener() {
            override fun onExpandChanged(view: View?, isExpanded: Boolean) {
                //handle onExpandChanged
                if(binding.elRepoList.isExpanded) {
                    val repoData = viewModel.getRepoDataAll().value
                    if(repoData != null)
                        repoListAdapter.data = repoData
                }
            }
        })
    }

    private fun isProfileDialogEtAllEmpty()
    = profileDialogBinding.etTitle.text.isNullOrEmpty() || profileDialogBinding.etContent.text.isNullOrEmpty()

    private fun isRepoDialogEtAllEmpty()
    = repoDialogBinding.etTitle.text.isNullOrEmpty() || repoDialogBinding.etContent.text.isNullOrEmpty() || repoDialogBinding.etLanguage.text.isNullOrEmpty()

    private fun isRepoDialogAllIbNotClicked() = !repoDialogBinding.ibBulb.isSelected && !repoDialogBinding.ibPeople.isSelected && !repoDialogBinding.ibStudy.isSelected

    companion object {
        const val NOT_SELECTED = 0
        const val STUDY = 1
        const val TEAM_PLAY = 2
        const val INFORMATION = 3
        const val STAR = 1
        const val NONE_STAR = 0
    }
}