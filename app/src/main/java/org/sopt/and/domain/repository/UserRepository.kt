package org.sopt.and.domain.repository

import org.sopt.and.data.dto.request.RequestSignIn
import org.sopt.and.data.dto.request.RequestSignUp
import org.sopt.and.data.dto.response.ResponseMyHobby
import org.sopt.and.data.dto.response.ResponseSignIn
import org.sopt.and.data.dto.response.ResponseSignUp
import org.sopt.and.data.remote.ServicePool

class UserRepository {
    private val service = ServicePool.userService

    suspend fun signUpUser(request: RequestSignUp): Result<ResponseSignUp> = runCatching {
        val response = service.signUpUser(request)
        response.result ?: throw Throwable("Failed to SignUp")
    }

    suspend fun signInUser(request: RequestSignIn) : Result<ResponseSignIn> = runCatching {
        val response = service.userLogin(request)
        response.result ?: throw Throwable("Failed to SignIn")
    }

    suspend fun getMyHobby(request: String) : Result<ResponseMyHobby> = runCatching {
        val response = service.getMyHobby(request)
        response.result ?: throw Throwable("Failed to GetMyHobby")
    }
}