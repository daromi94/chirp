package com.daromi.chirp.core.user.domain

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
            rawId: String,
            rawName: String,
            rawHandle: String,
            rawCreatedAt: LocalDateTime,
        ): User? {
            val id     = UserId.from(rawId)         ?: return null
            val name   = UserName.from(rawName)     ?: return null
            val handle = UserHandle.from(rawHandle) ?: return null

            val createdAt = UserCreatedAt(rawCreatedAt)
            val updatedAt = UserUpdatedAt(rawCreatedAt)

            return User(
                id,
                name,
                handle,
                createdAt,
                updatedAt,
            )
        }
    }

    val id: String get() = this._id.value

    val name: String get() = this._name.value

    val handle: String get() = this._handle.value

    val createdAt: LocalDateTime get() = this._createdAt.value

    val updatedAt: LocalDateTime get() = this._updatedAt.value

    fun updateName(
        rawName: String,
        rawUpdatedAt: LocalDateTime,
    ): Boolean {
        val name = UserName.from(rawName) ?: return false

        val updatedAt = UserUpdatedAt(rawUpdatedAt)
        if (!updatedAt.isAfter(this._createdAt)) {
            return false
        }

        this._name      = name
        this._updatedAt = updatedAt

        return true
    }

    fun updateHandle(
        rawHandle: String,
        rawUpdatedAt: LocalDateTime,
    ): Boolean {
        val handle = UserHandle.from(rawHandle) ?: return false

        val updatedAt = UserUpdatedAt(rawUpdatedAt)
        if (!updatedAt.isAfter(this._createdAt)) {
            return false
        }

        this._handle    = handle
        this._updatedAt = updatedAt

        return true
    }

    override fun toString(): String =
        "User(id=${this.id}, name=${this.name}, handle=${this.handle}, createdAt=${this.createdAt}, updatedAt=${this.updatedAt})"
}

@JvmInline
value class UserId(
    val value: String,
) {
    companion object {
        fun from(value: String): UserId? = if (value.isBlank()) null else UserId(value)
    }
}

@JvmInline
private value class UserName(
    val value: String,
) {
    companion object {
        fun from(value: String): UserName? = if (value.isBlank()) null else UserName(value)
    }
}

@JvmInline
private value class UserHandle(
    val value: String,
) {
    companion object {
        fun from(value: String): UserHandle? = if (value.isBlank() || !value.startsWith("@")) null else UserHandle(value)
    }
}

@JvmInline
private value class UserCreatedAt(
    val value: LocalDateTime,
)

@JvmInline
private value class UserUpdatedAt(
    val value: LocalDateTime,
) {
    fun isAfter(createdAt: UserCreatedAt): Boolean = this.value.isAfter(createdAt.value)
}
