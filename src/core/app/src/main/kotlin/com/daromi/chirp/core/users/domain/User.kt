package com.daromi.chirp.core.users.domain

import java.time.Clock
import java.time.Instant

class User private constructor(
    private val _id: UserId,
    private var _name: UserName,
    private var _handle: UserHandle,
    private val _createdAt: UserCreatedAt,
    private var _updatedAt: UserUpdatedAt? = null,
) {
    companion object {
        fun create(
            rawId: String,
            rawName: String,
            rawHandle: String,
            clock: Clock,
        ): User? {
            val id = UserId.from(rawId) ?: return null
            val name = UserName.from(rawName) ?: return null
            val handle = UserHandle.from(rawHandle) ?: return null

            val createdAt = UserCreatedAt(clock.instant())

            return User(
                id,
                name,
                handle,
                createdAt,
            )
        }
    }

    val id: String get() = this._id.value

    val name: String get() = this._name.value

    val handle: String get() = this._handle.value

    val createdAt: Instant get() = this._createdAt.value

    val updatedAt: Instant? get() = this._updatedAt?.value

    fun updateName(
        rawName: String,
        clock: Clock,
    ): Boolean {
        val name = UserName.from(rawName) ?: return false
        val updatedAt = UserUpdatedAt(clock.instant())

        assert(updatedAt.isAfter(this._createdAt))
        assert(this._updatedAt == null || updatedAt.isAfter(this._updatedAt!!))

        this._name = name
        this._updatedAt = updatedAt

        return true
    }

    fun updateHandle(
        rawHandle: String,
        clock: Clock,
    ): Boolean {
        val handle = UserHandle.from(rawHandle) ?: return false
        val updatedAt = UserUpdatedAt(clock.instant())

        assert(updatedAt.isAfter(this._createdAt))
        assert(this._updatedAt == null || updatedAt.isAfter(this._updatedAt!!))

        this._handle = handle
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
        private const val PREFIX: String = "@"

        fun from(value: String): UserHandle? = if (value.isBlank() || !value.startsWith(PREFIX)) null else UserHandle(value)
    }
}

@JvmInline
private value class UserCreatedAt(
    val value: Instant,
)

@JvmInline
private value class UserUpdatedAt(
    val value: Instant,
) {
    fun isAfter(other: UserUpdatedAt): Boolean = this.value.isAfter(other.value)

    fun isAfter(createdAt: UserCreatedAt): Boolean = this.value.isAfter(createdAt.value)
}
