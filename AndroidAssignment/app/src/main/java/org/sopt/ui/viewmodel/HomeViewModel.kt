package org.sopt.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.data.local.entity.ProfileData
import org.sopt.data.repository.HomeRepo
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {
    private val profileDataList = homeRepo.getProfileDataAll()
    fun getProfileDataAll(): LiveData<List<ProfileData>> = profileDataList

    fun insertProfileData(profileData: ProfileData) {
        homeRepo.insertProfileData(profileData)
    }

    fun deleteProfileData(profileData: ProfileData) {
        homeRepo.deleteProfileData(profileData)
    }
}