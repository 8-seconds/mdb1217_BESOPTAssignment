package org.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.data.remote.api.UserService
import org.sopt.data.remote.datasource.UserDataSource
import org.sopt.data.remote.datasource.UserDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideUserDataSource(userService: UserService): UserDataSource = UserDataSourceImpl(userService)
}