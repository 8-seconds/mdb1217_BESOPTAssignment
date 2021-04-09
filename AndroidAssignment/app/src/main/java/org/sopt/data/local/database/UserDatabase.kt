package org.sopt.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.sopt.data.local.dao.UserDao
import org.sopt.data.local.entity.UserData

@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE
            }
        }
    }
}