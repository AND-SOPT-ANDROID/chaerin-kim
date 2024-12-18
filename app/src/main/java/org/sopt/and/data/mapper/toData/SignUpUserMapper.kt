package org.sopt.and.data.mapper.toData

import org.sopt.and.data.dto.request.RequestSignUp
import org.sopt.and.domain.entity.UserInfo

fun UserInfo.toRequestSignUp(): RequestSignUp = RequestSignUp(
    username = this.username,
    password = this.password,
    hobby = this.hobby
)