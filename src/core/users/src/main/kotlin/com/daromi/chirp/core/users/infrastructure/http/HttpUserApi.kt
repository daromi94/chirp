package com.daromi.chirp.core.users.infrastructure.http

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HttpUserApi {
    @GetMapping("/users/{id}")
    fun findById(
        @PathVariable id: String,
    ): ResponseEntity<String> = ResponseEntity.ok("user $id")
}
