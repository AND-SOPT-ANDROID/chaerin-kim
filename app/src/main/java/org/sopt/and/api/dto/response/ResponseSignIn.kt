package org.sopt.and.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignIn(
    @SerialName("result")
    val result: ResponseSignInResult,
) {
    @Serializable
    data class ResponseSignInResult(
        @SerialName("token")
        val token: String,
    )
}
