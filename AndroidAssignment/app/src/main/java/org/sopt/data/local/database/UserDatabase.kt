package org.sopt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sopt.data.local.dao.UserDao
import org.sopt.data.local.entity.UserData

@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}