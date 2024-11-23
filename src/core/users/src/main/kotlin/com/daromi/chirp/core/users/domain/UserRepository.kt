package com.daromi.chirp.core.users.domain

interface UserRepository {
    fun save(user: User)

    fun retrieve(id: UserId): User?

    fun exists(id: UserId): Boolean
}
