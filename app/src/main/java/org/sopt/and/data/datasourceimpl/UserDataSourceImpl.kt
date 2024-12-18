package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.RequestSignIn
import org.sopt.and.data.dto.request.RequestSignUp
import org.sopt.and.data.dto.response.ResponseMyHobbyDto
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import org.sopt.and.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserDataSource {
    override suspend fun signUpUser(request: RequestSignUp): BaseResponse<ResponseSignUpDto> =
        userService.signUpUser(request)

    override suspend fun signInUser(request: RequestSignIn): BaseResponse<ResponseSignInDto> =
        userService.userLogin(request)

    override suspend fun getMyHobby(token: String): BaseResponse<ResponseMyHobbyDto> =
        userService.getMyHobby(token)
}