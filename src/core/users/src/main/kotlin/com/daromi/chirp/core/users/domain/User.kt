package com.daromi.chirp.core.users.domain

class User private constructor(
    private val id: UserId,
    private var name: UserName,
    private var handle: UserHandle,
) {
    companion object {
        @JvmStatic
        fun create(
            rawId: String,
            rawName: String,
            rawHandle: String,
        ): User? {
            val id = UserId.from(rawId) ?: return null
            val name = UserName.from(rawName) ?: return null
            val handle = UserHandle.from(rawHandle) ?: return null

            return User(id, name, handle)
        }
    }

    fun changeName(name: String): Boolean {
        this.name = UserName.from(name) ?: return false

        return true
    }

    fun changeHandle(handle: String): Boolean {
        this.handle = UserHandle.from(handle) ?: return false

        return true
    }
}

@JvmInline
value class UserId(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun from(value: String): UserId? {
            if (value.isBlank()) {
                return null
            }

            return UserId(value)
        }
    }
}

@JvmInline
private value class UserName(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun from(value: String): UserName? {
            if (value.isBlank()) {
                return null
            }

            return UserName(value)
        }
    }
}

@JvmInline
private value class UserHandle(
    val value: String,
) {
    companion object {
        private const val PREFIX: String = "@"

        private const val MIN_LENGTH: Int = 10

        @JvmStatic
        fun from(value: String): UserHandle? {
            if (value.isBlank()) {
                return null
            }

            if (!value.startsWith(PREFIX)) {
                return null
            }

            if (value.length < MIN_LENGTH) {
                return null
            }

            return UserHandle(value)
        }
    }
}
