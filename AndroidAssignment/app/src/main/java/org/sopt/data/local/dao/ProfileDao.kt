package org.sopt.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.sopt.data.local.entity.ProfileData

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile_data_table")
    fun getAll(): LiveData<List<ProfileData>>

    @Insert
    fun insert(userData: ProfileData)

    @Delete
    fun delete(userData: ProfileData)
}