package org.sopt.and.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyHobby(
    @SerialName("result")
    val result: ResponseMyHobbyResult,
) {
    @Serializable
    data class ResponseMyHobbyResult(
        @SerialName("hobby")
        val hobby: String,
    )
}
