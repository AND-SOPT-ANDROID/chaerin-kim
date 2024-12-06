package org.sopt.and.data.mapper.toData

import org.sopt.and.data.dto.request.RequestSignIn
import org.sopt.and.domain.entity.UserInfo

fun UserInfo.toRequestSingIn() : RequestSignIn = RequestSignIn(
    username = this.username,
    password = this.password
)