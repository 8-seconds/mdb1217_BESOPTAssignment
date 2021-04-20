package org.sopt.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import org.sopt.data.local.SOPTSharedPreference.setName
import org.sopt.data.local.dao.UserDao
import org.sopt.data.local.entity.UserData
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDao: UserDao) : UserRepo {
    override fun getAll(): LiveData<List<UserData>> {
        return userDao.getAll()
    }

    override fun findPasswordById(id: String, password: String) : Boolean {
        runBlocking {
            val job = GlobalScope.launch {
                try {
                    val userData = userDao.findPasswordById(id)
                    try {
                        if (userData.password == password) {
                            isIdPassExist = true
                            setName(userData.name)
                        }
                        else {
                            isIdPassExist = false
                        }
                    } catch (e: Exception) {
                        isIdPassExist = false
                    }
                } catch (e: Exception) {

                }
            }
            job.join()
        }
        return isIdPassExist
    }

   override fun insert(userData: UserData) {
        try {
           GlobalScope.launch {
               userDao.insert(userData)
           }
        } catch(e: Exception) { }
    }

    override fun delete(userData: UserData) {
        try {
            GlobalScope.launch {
                userDao.delete(userData)
            }
        } catch(e: Exception) { }
    }

    companion object {
        var isIdPassExist : Boolean = false
    }
}