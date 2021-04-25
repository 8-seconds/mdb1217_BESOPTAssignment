package org.sopt.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.data.local.dao.ProfileDao
import org.sopt.data.local.dao.RepoDao
import org.sopt.data.local.dao.UserDao
import org.sopt.data.local.database.ProfileDatabase
import org.sopt.data.local.database.RepoDatabase
import org.sopt.data.local.database.UserDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "user database"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao =
            database.userDao

    @Provides
    @Singleton
    fun provideProfileDatabase(@ApplicationContext context: Context): ProfileDatabase =
            Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "profile database"
            ).fallbackToDestructiveMigration()
                    .build()

    @Provides
    fun provideProfileDao(database: ProfileDatabase): ProfileDao =
            database.profileDao

    @Provides
    @Singleton
    fun provideRepoDatabase(@ApplicationContext context: Context): RepoDatabase =
            Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java,
                    "repo database"
            ).fallbackToDestructiveMigration()
                    .build()

    @Provides
    fun provideRepoDao(database: RepoDatabase): RepoDao =
            database.repoDao
}