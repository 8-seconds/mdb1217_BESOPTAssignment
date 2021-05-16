package org.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.local.dao.ProfileDao
import org.sopt.data.local.dao.RepoDao
import org.sopt.data.remote.datasource.GitDataSource
import org.sopt.data.remote.datasource.UserDataSource
import org.sopt.data.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun userRepo(
        userDataSource: UserDataSource
    ) : UserRepo = UserRepoImpl(userDataSource)

    @Provides
    @Singleton
    fun homeRepo(
            profileDao: ProfileDao,
            repoDao: RepoDao
    ) : HomeRepo = HomeRepoImpl(profileDao, repoDao)

    @Provides
    @Singleton
    fun gitRepo(
        gitDataSource: GitDataSource
    ) : GitRepo = GitRepoImpl(gitDataSource)
}