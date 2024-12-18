package org.sopt.and.data.datasource

import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.RequestSignIn
import org.sopt.and.data.dto.request.RequestSignUp
import org.sopt.and.data.dto.response.ResponseMyHobbyDto
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto

interface UserDataSource {
    suspend fun signUpUser(requestSignUp: RequestSignUp): BaseResponse<ResponseSignUpDto>
    suspend fun signInUser(request: RequestSignIn): BaseResponse<ResponseSignInDto>
    suspend fun getMyHobby(token: String): BaseResponse<ResponseMyHobbyDto>
}