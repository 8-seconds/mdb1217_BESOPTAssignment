package org.sopt.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.data.local.SOPTSharedPreference
import org.sopt.data.local.entity.ProfileData
import org.sopt.data.local.entity.RepoData
import org.sopt.data.remote.model.response.ResFollower
import org.sopt.data.repository.GitRepo
import org.sopt.data.repository.HomeRepo
import org.sopt.util.Event
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val homeRepo: HomeRepo, private val gitRepo: GitRepo) : ViewModel() {
    private val _followerList = MutableLiveData<List<ResFollower>>()
    val followerList: LiveData<List<ResFollower>>
        get() = _followerList

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

    fun getFollower() = viewModelScope.launch {
        runCatching { gitRepo.getFollower("mdb1217") }
            .onSuccess {
                _followerList.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}