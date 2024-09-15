package com.daromi.chirp.core.user.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface UserRepository {
    fun save(user: User): Either<UserSaveFailedError, User>

    fun search(id: UserId): Either<UserSearchError, User>
}

data class UserSaveFailedError(
    val id: UserId,
) : Error {
    override val message: String get() = "user '${this.id.value}' save failed"
}

sealed interface UserSearchError : Error

data class UserSearchFailedError(
    val id: UserId,
) : UserSearchError {
    override val message: String get() = "user '${this.id.value}' search failed"
}

data class UserNotFoundError(
    val id: UserId,
) : UserSearchError {
    override val message: String get() = "user '${this.id.value}' not found"
}
