package org.sopt.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.data.local.entity.ProfileData
import org.sopt.data.local.entity.RepoData
import org.sopt.data.repository.HomeRepo
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {
    fun getProfileDataAll(): LiveData<List<ProfileData>> = homeRepo.getProfileDataAll()

    fun insertProfileData(profileData: ProfileData) {
        homeRepo.insertProfileData(profileData)
    }

    fun deleteProfileData(profileData: ProfileData) {
        homeRepo.deleteProfileData(profileData)
    }

    fun getRepoDataAll(): LiveData<List<RepoData>> = homeRepo.getRepoDataAll()

    fun insertRepoData(repoData: RepoData) {
        homeRepo.insertRepoData(repoData)
    }

    fun getStaredRepo(): LiveData<List<RepoData>> = homeRepo.getStaredRepo()

    fun updateStar(isSelected : Int, id : Long) {
        homeRepo.updateStar(isSelected, id)
    }

    fun deleteRepoData(repoData: RepoData) {
        homeRepo.deleteRepoData(repoData)
    }
}