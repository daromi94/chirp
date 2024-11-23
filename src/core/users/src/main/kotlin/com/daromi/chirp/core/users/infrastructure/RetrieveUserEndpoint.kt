package com.daromi.chirp.core.users.infrastructure

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RetrieveUserEndpoint {
    @GetMapping("/users/{id}")
    fun apply(
        @PathVariable id: String,
    ): ResponseEntity<String> = ResponseEntity.ok("User $id")
}
