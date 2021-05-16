package org.sopt.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.data.remote.api.GitService
import org.sopt.data.remote.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class GitRetrofit

@Qualifier
annotation class UserRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor())
        .build()

    @UserRetrofit
    @Provides
    @Singleton
    fun provideUserRetrofitObject(): Retrofit {
        return Retrofit.Builder().baseUrl(USER_URL).client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }

    @GitRetrofit
    @Provides
    @Singleton
    fun provideGitRetrofitObject(): Retrofit {
        return Retrofit.Builder().baseUrl(GIT_URL).client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideUserService(@UserRetrofit retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideGitService(@GitRetrofit retrofit: Retrofit): GitService =
        retrofit.create(GitService::class.java)

    private const val USER_URL = "http://cherishserver.com"
    private const val GIT_URL = "https://api.github.com"
}