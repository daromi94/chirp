package com.daromi.chirp.core.users.application

import com.daromi.chirp.core.users.domain.User
import com.daromi.chirp.core.users.domain.UserRepository

class UserCreator(
    private val repository: UserRepository,
) {
    fun apply(command: CreateUserCommand) {
        val user = User.create(command.id, command.name, command.handle)
        if (user == null) {
            TODO()
        }

        repository.save(user)
    }
}

data class CreateUserCommand(
    val id: String,
    val name: String,
    val handle: String,
)
