package com.daromi.chirp.core.users.domain

interface UserRepository {
    fun save(user: User)

    fun search(id: UserId): User?
}
