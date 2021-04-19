package org.sopt.data.repository

import androidx.lifecycle.LiveData
import org.sopt.data.local.entity.UserData

interface UserRepo {
    fun getAll(): LiveData<List<UserData>>

    fun findPasswordById(id : String, password : String) : Boolean

    fun insert(userData : UserData)

    fun delete(userData : UserData)
}