package org.sopt.ui.viewmodel

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.data.local.entity.UserData
import org.sopt.data.repository.UserRepo
import org.sopt.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel@Inject constructor(private val userRepo: UserRepo) : BaseViewModel() {
    fun getAll(): LiveData<List<UserData>> {
        return userRepo.getAll()
    }

    fun findPasswordById(id : String, password : String) {
        userRepo.findPasswordById(id, password)
    }

    fun insert(userData: UserData) {
        userRepo.insert(userData)
    }

    fun delete(userData: UserData) {
        userRepo.delete(userData)
    }
}