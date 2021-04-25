package org.sopt.data.repository

import androidx.lifecycle.LiveData
import org.sopt.data.local.entity.ProfileData
import org.sopt.data.local.entity.RepoData

interface HomeRepo {
    fun getProfileDataAll(): LiveData<List<ProfileData>>

    fun insertProfileData(profileData : ProfileData)

    fun deleteProfileData(profileData : ProfileData)

    fun getRepoDataAll(): LiveData<List<RepoData>>

    fun insertRepoData(repoData : RepoData)

    fun deleteRepoData(repoData : RepoData)
}