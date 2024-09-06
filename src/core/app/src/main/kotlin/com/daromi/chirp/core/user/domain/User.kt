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

    val id: String get() = this._id.value

    val name: String get() = this._name.value

    val handle: String get() = this._handle.value

    val createdAt: LocalDateTime get() = this._createdAt.value

    val updatedAt: LocalDateTime get() = this._updatedAt.value

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

    override fun toString(): String =
        "User(id=${this.id}, name=${this.name}, handle=${this.handle}, createdAt=${this.createdAt}, updatedAt=${this.updatedAt})"
}

data class UserId(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalUserIdError, UserId> =
            either {
                ensure(value.isNotBlank()) {
                    IllegalUserIdError(value)
                }
                UserId(value)
            }

        data class IllegalUserIdError(
            val value: String,
        ) : Error {
            override val message: String get() = "illegal user id '${this.value}'"
        }
    }
}

data class UserName(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalUserNameError, UserName> =
            either {
                ensure(value.isNotBlank()) {
                    IllegalUserNameError(value)
                }
                UserName(value)
            }

        data class IllegalUserNameError(
            val value: String,
        ) : Error {
            override val message: String get() = "illegal user name '${this.value}'"
        }
    }
}

data class UserHandle(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalUserHandleError, UserHandle> =
            either {
                ensure(value.isNotBlank() && value.startsWith("@")) {
                    IllegalUserHandleError(value)
                }
                UserHandle(value)
            }

        data class IllegalUserHandleError(
            val value: String,
        ) : Error {
            override val message: String get() = "illegal user handle '${this.value}'"
        }
    }
}

data class UserCreatedAt(
    val value: LocalDateTime,
)

data class UserUpdatedAt(
    val value: LocalDateTime,
)
