package org.sopt.and.domain.entity

data class UserPreferences(
    val userName: String = "",
    val password: String = "",
    val hobby: String = "",
    val token: String = "",
)
