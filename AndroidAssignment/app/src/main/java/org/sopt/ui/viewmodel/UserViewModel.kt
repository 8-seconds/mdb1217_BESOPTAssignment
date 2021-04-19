package org.sopt.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.data.local.entity.UserData
import org.sopt.data.repository.UserRepo
import javax.inject.Inject

@HiltViewModel
class UserViewModel@Inject constructor(private val userRepo: UserRepo) : ViewModel() {
    fun getAll(): LiveData<List<UserData>> = userRepo.getAll()

    fun findPasswordById(id : String, password : String) : Boolean = userRepo.findPasswordById(id, password)


    fun insert(userData: UserData) {
        userRepo.insert(userData)
    }

    fun delete(userData: UserData) {
        userRepo.delete(userData)
    }
}