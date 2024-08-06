package com.daromi.chirp.core.user.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface UserRepository {
    fun save(user: User): Either<UserSaveFailedError, User>

    fun search(id: UserId): Either<UserSearchError, User>
}

class UserSaveFailedError(
    private val id: String,
) : Error {
    override val message: String get() = "user '${this.id}' save failed"
}

sealed class UserSearchError : Error

class UserSearchFailedError(
    private val id: String,
) : UserSearchError() {
    override val message: String get() = "user '${this.id}' search failed"
}

class UserNotFoundError(
    private val id: String,
) : UserSearchError() {
    override val message: String get() = "user '${this.id}' not found"
}
