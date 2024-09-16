package com.daromi.chirp.core.user.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface UserRepository {
    fun save(user: User): Either<UserSaveError, User>

    fun search(id: UserId): Either<UserSearchError, User>
}

sealed interface UserSaveError : Error

data class UserSaveFailedError(
    val id: UserId,
) : UserSaveError {
    override val message: String get() = "failed to save user with id '${this.id.value}'"
}

sealed interface UserSearchError : Error

data class UserSearchFailedError(
    val id: UserId,
) : UserSearchError {
    override val message: String get() = "failed to search for user with id '${this.id.value}'"
}

data class UserNotFoundError(
    val id: UserId,
) : UserSearchError {
    override val message: String get() = "user with id '${this.id.value}' was not found"
}
