package org.sopt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sopt.data.local.dao.ProfileDao
import org.sopt.data.local.entity.ProfileData

@Database(entities = [ProfileData::class], version = 1)
abstract class ProfileDatabase: RoomDatabase() {
    abstract val profileDao: ProfileDao
}