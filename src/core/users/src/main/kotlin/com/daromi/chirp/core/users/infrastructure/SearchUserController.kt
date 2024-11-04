package com.daromi.chirp.core.users.infrastructure

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchUserController {
    @GetMapping("/users/{id}")
    fun apply(
        @PathVariable id: String,
    ): String = "User $id"
}
