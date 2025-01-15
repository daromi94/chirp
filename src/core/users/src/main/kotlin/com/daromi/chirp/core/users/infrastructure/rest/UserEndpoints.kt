package com.daromi.chirp.core.users.infrastructure.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserEndpoints {
    @GetMapping("/users/{id}")
    fun getById(
        @PathVariable id: String,
    ): ResponseEntity<String> = ResponseEntity.ok("User $id")
}
