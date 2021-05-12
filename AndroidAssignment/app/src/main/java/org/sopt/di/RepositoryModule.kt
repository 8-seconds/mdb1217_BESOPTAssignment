package org.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.local.dao.ProfileDao
import org.sopt.data.local.dao.RepoDao
import org.sopt.data.local.dao.UserDao
import org.sopt.data.repository.HomeRepo
import org.sopt.data.repository.HomeRepoImpl
import org.sopt.data.repository.UserRepo
import org.sopt.data.repository.UserRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun userRepo(
        userDao: UserDao
    ) : UserRepo = UserRepoImpl(userDao)

    @Provides
    @Singleton
    fun homeRepo(
            profileDao: ProfileDao,
            repoDao: RepoDao
    ) : HomeRepo = HomeRepoImpl(profileDao, repoDao)
}