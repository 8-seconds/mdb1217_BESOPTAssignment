package org.sopt.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.sopt.data.local.entity.RepoData

@Dao
interface RepoDao {
    @Query("SELECT * FROM Repo_data_table")
    fun getAll(): LiveData<List<RepoData>>

    @Insert
    fun insert(userData: RepoData)

    @Delete
    fun delete(userData: RepoData)
}