package com.daromi.chirp.core.user.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface UserRepository {
    fun save(user: User): Either<UserSaveFailedError, User>

    fun search(id: UserId): Either<UserSearchError, User>
}

data class UserSaveFailedError(
    val id: String,
) : Error {
    override val message: String get() = "user '${this.id}' save failed"
}

sealed class UserSearchError : Error {
    data class UserSearchFailedError(
        val id: String,
    ) : UserSearchError() {
        override val message: String get() = "user '${this.id}' search failed"
    }

    data class UserNotFoundError(
        val id: String,
    ) : UserSearchError() {
        override val message: String get() = "user '${this.id}' not found"
    }
}
