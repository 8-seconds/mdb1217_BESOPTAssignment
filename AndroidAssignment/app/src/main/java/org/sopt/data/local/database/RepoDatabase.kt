package org.sopt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sopt.data.local.dao.RepoDao
import org.sopt.data.local.entity.RepoData

@Database(entities = [RepoData::class], version = 1)
abstract class RepoDatabase: RoomDatabase() {
    abstract val repoDao: RepoDao
}