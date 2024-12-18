package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.mapper.toData.toRequestSignUp
import org.sopt.and.data.mapper.toData.toRequestSingIn
import org.sopt.and.domain.entity.ResponseMyHobby
import org.sopt.and.domain.entity.ResponseSignIn
import org.sopt.and.domain.entity.ResponseSignUp
import org.sopt.and.domain.entity.UserInfo
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun signUpUser(request: UserInfo): Result<ResponseSignUp> = runCatching {
        val response = userDataSource.signUpUser(request.toRequestSignUp())
        ResponseSignUp(response.result.no)
    }

    override suspend fun signInUser(request: UserInfo): Result<ResponseSignIn> = runCatching {
        val response = userDataSource.signInUser(request.toRequestSingIn())
        ResponseSignIn(response.result.token)
    }

    override suspend fun getMyHobby(token: String): Result<ResponseMyHobby> = runCatching {
        val response = userDataSource.getMyHobby(token)
        ResponseMyHobby(response.result.hobby)
    }
}