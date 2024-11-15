package org.sopt.and.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    @SerialName("result")
    val result: ResponseSignUpResult,
) {
    @Serializable
    data class ResponseSignUpResult(
        @SerialName("no")
        val no: Int,
    )
}
