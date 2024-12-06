package org.sopt.and.api.service

import org.sopt.and.api.dto.BaseResponse
import org.sopt.and.api.dto.request.RequestSignIn
import org.sopt.and.api.dto.request.RequestSignUp
import org.sopt.and.api.dto.response.ResponseMyHobby
import org.sopt.and.api.dto.response.ResponseSignIn
import org.sopt.and.api.dto.response.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    fun signUpUser(
        @Body
        requestSignUp: RequestSignUp
    ): Call<BaseResponse<ResponseSignUp>>

    @POST("/login")
    fun userLogin(
        @Body
        requestSignIn: RequestSignIn
    ): Call<BaseResponse<ResponseSignIn>>

    @GET("/user/my-hobby")
    fun getMyHobby(
        @Header("token")
        token: String
    ): Call<BaseResponse<ResponseMyHobby>>
}