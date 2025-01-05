package com.daromi.chirp.core.users.application

import com.daromi.chirp.core.users.domain.User
import com.daromi.chirp.core.users.domain.UserStore

class UserCreator(
    private val store: UserStore,
) {
    fun apply(command: CreateUserCommand) {
        val user = User.create(command.id, command.name, command.handle)
        if (user == null) {
            TODO()
        }

        store.save(user)
    }
}

data class CreateUserCommand(
    val id: String,
    val name: String,
    val handle: String,
)
