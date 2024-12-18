package org.sopt.and.data.service

import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.RequestSignIn
import org.sopt.and.data.dto.request.RequestSignUp
import org.sopt.and.data.dto.response.ResponseMyHobbyDto
import org.sopt.and.data.dto.response.ResponseSignInDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun signUpUser(
        @Body
        requestSignUp: RequestSignUp
    ): BaseResponse<ResponseSignUpDto>

    @POST("/login")
    suspend fun userLogin(
        @Body
        requestSignIn: RequestSignIn
    ): BaseResponse<ResponseSignInDto>

    @GET("/user/my-hobby")
    suspend fun getMyHobby(
        @Header("token")
        token: String
    ): BaseResponse<ResponseMyHobbyDto>
}