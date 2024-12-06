package org.sopt.and.api.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyHobby(
    @SerialName("hobby")
    val hobby: String,
)
