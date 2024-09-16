package com.daromi.chirp.core.user.domain

interface UserRepository {
    fun save(user: User)

    fun search(id: UserId): User?
}
