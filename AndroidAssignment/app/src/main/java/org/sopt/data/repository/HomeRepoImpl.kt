package org.sopt.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.sopt.data.local.dao.ProfileDao
import org.sopt.data.local.dao.RepoDao
import org.sopt.data.local.entity.ProfileData
import org.sopt.data.local.entity.RepoData
import javax.inject.Inject

class HomeRepoImpl@Inject constructor(private val profileDao: ProfileDao, private val repoDao: RepoDao) : HomeRepo {
    override fun getProfileDataAll(): LiveData<List<ProfileData>> = profileDao.getAll()

    override fun insertProfileData(profileData: ProfileData) {
        try {
            GlobalScope.launch {
                profileDao.insert(profileData)
            }
        } catch(e: Exception) { }
    }
    
    override fun deleteProfileData(profileData: ProfileData) {
        try {
            GlobalScope.launch {
                profileDao.delete(profileData)
            }
        } catch(e: Exception) { }
    }
    
    override fun getRepoDataAll(): LiveData<List<RepoData>> = repoDao.getAll()

    override fun getStaredRepo(): LiveData<List<RepoData>> = repoDao.getStaredRepo()

    override fun updateStar(isSelected: Int, id: Long) {
        try {
            GlobalScope.launch {
                repoDao.updateStar(isSelected, id)
            }
        } catch(e: Exception) { }
    }

    override fun insertRepoData(repoData: RepoData) {
        try {
            GlobalScope.launch {
                repoDao.insert(repoData)
            }
        } catch(e: Exception) { }
    }

    override fun deleteRepoData(repoData: RepoData) {
        try {
            GlobalScope.launch {
                repoDao.delete(repoData)
            }
        } catch(e: Exception) { }
    }

}