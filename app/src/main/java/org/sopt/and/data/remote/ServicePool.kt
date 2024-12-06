package org.sopt.and.data.remote

import org.sopt.and.data.service.UserService

object ServicePool {
    val userService by lazy { ApiFactory.create<UserService>() }
}