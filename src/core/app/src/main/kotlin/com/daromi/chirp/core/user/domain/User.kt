package com.daromi.chirp.core.user.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.daromi.chirp.core.shared.Error
import java.time.LocalDateTime

class User private constructor(
    private val _id: UserId,
    private var _name: UserName,
    private var _handle: UserHandle,
    private val _createdAt: UserCreatedAt,
    private var _updatedAt: UserUpdatedAt,
) {
    companion object {
        fun create(
            id: String,
            name: String,
            handle: String,
            createdAt: LocalDateTime,
        ): User =
            User(
                UserId(id),
                UserName(name),
                UserHandle(handle),
                UserCreatedAt(createdAt),
                UserUpdatedAt(createdAt),
            )
    }

    val id: String get() = _id.value

    val name: String get() = _name.value

    val handle: String get() = _handle.value

    val createdAt: LocalDateTime get() = _createdAt.value

    val updatedAt: LocalDateTime get() = _updatedAt.value

    fun updateName(
        name: String,
        updatedAt: LocalDateTime,
    ) {
        this._name = UserName(name)
        this._updatedAt = UserUpdatedAt(updatedAt)
    }

    fun updateHandle(
        handle: String,
        updatedAt: LocalDateTime,
    ) {
        this._handle = UserHandle(handle)
        this._updatedAt = UserUpdatedAt(updatedAt)
    }

    override fun toString(): String = "User(id=$id, name=$name, handle=$handle, createdAt=$createdAt, updatedAt=$updatedAt)"
}

data class UserId(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalUserIdError, UserId> =
            either {
                ensure(value.isEmpty()) {
                    IllegalUserIdError(value)
                }
                UserId(value)
            }

        class IllegalUserIdError(
            private val value: String,
        ) : Error {
            override val message: String get() = "illegal user id '$value'"
        }
    }
}

data class UserName(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalUserNameError, UserName> =
            either {
                ensure(value.isEmpty()) {
                    IllegalUserNameError(value)
                }
                UserName(value)
            }

        class IllegalUserNameError(
            private val value: String,
        ) : Error {
            override val message: String get() = "illegal user name '$value'"
        }
    }
}

data class UserHandle(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalUserHandleError, UserHandle> =
            either {
                ensure(value.isEmpty() || !value.startsWith("@")) {
                    IllegalUserHandleError(value)
                }
                UserHandle(value)
            }

        class IllegalUserHandleError(
            private val value: String,
        ) : Error {
            override val message: String get() = "illegal user handle '$value'"
        }
    }
}

data class UserCreatedAt(
    val value: LocalDateTime,
)

data class UserUpdatedAt(
    val value: LocalDateTime,
)
