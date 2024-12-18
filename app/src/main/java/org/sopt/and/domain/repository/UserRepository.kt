package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.ResponseMyHobby
import org.sopt.and.domain.entity.ResponseSignIn
import org.sopt.and.domain.entity.ResponseSignUp
import org.sopt.and.domain.entity.UserInfo

interface UserRepository {
    suspend fun signUpUser(request: UserInfo) : Result<ResponseSignUp>
    suspend fun signInUser(request: UserInfo) : Result<ResponseSignIn>
    suspend fun getMyHobby(request: String) : Result<ResponseMyHobby>
}