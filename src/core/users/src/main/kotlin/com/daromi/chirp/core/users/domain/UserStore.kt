package com.daromi.chirp.core.users.domain

interface UserStore {
    fun save(user: User)

    fun search(id: UserId): User?

    fun exists(id: UserId): Boolean
}
