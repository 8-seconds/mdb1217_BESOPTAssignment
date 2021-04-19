package org.sopt.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.sopt.data.local.entity.UserData

@Dao
interface UserDao {
    @Query("SELECT * FROM user_data_table")
    fun getAll(): LiveData<List<UserData>>

    @Query("SELECT * FROM user_data_table WHERE email == :email")
    fun findPasswordById(email: String): UserData

    @Insert
    fun insert(userData: UserData)

    @Delete
    fun delete(userData: UserData)
}