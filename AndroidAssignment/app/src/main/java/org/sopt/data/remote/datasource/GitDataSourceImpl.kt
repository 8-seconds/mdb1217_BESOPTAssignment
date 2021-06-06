package org.sopt.data.remote.datasource

import org.sopt.data.remote.api.GitService
import org.sopt.data.remote.model.response.ResFollower
import javax.inject.Inject

class GitDataSourceImpl@Inject constructor(private val gitService: GitService) : GitDataSource {
    override suspend fun getFollower(path: String): List<ResFollower> = gitService.getFollower(path)
}