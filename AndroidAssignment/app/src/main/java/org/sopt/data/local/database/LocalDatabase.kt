package org.sopt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sopt.data.local.dao.ProfileDao
import org.sopt.data.local.dao.RepoDao
import org.sopt.data.local.entity.ProfileData
import org.sopt.data.local.entity.RepoData

@Database(entities = [ProfileData::class, RepoData::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract val profileDao: ProfileDao
    abstract val repoDao: RepoDao
}