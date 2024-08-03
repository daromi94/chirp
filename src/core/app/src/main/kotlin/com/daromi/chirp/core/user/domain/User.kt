package com.daromi.chirp.core.user.domain

import java.time.LocalDateTime

data class UserId(
    val value: String,
)

data class UserCreatedAt(
    val value: LocalDateTime,
)

data class UserName(
    val value: String,
)

data class UserHandle(
    val value: String,
)

class User private constructor(
    private val id: UserId,
    private val createdAt: UserCreatedAt,
    private var name: UserName,
    private var handle: UserHandle,
) {
    companion object {
        fun create(
            id: String,
            createdAt: LocalDateTime,
            name: String,
            handle: String,
        ): User = User(UserId(id), UserCreatedAt(createdAt), UserName(name), UserHandle(handle))
    }

    fun updateName(name: String) {
        this.name = UserName(name)
    }

    fun updateHandle(handle: String) {
        this.handle = UserHandle(handle)
    }

    override fun toString(): String = "User(id=${id.value}, createdAt=${createdAt.value}, name=${name.value}, handle=${handle.value})"
}

interface UserRepository {
    fun save(user: User): User?

    fun search(id: UserId): User?
}
