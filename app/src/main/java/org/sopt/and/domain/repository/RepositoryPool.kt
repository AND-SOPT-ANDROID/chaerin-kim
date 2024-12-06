package org.sopt.and.domain.repository

object RepositoryPool {
    val userRepository by lazy { UserRepository() }
}