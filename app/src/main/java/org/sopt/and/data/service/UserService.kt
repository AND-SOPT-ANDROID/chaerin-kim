package org.sopt.and.data.service

import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.RequestSignIn
import org.sopt.and.data.dto.request.RequestSignUp
import org.sopt.and.data.dto.response.ResponseMyHobby
import org.sopt.and.data.dto.response.ResponseSignIn
import org.sopt.and.data.dto.response.ResponseSignUp
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