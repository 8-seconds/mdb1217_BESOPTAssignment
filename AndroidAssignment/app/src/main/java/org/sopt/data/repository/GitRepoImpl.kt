package org.sopt.data.repository

import org.sopt.data.remote.datasource.GitDataSource
import org.sopt.data.remote.model.response.ResFollower

class GitRepoImpl(private val gitDataSource: GitDataSource) : GitRepo {
    override suspend fun getFollower(path: String): List<ResFollower> = gitDataSource.getFollower(path)
}