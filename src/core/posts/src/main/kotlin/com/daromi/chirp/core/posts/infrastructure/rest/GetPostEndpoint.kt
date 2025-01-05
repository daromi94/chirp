package com.daromi.chirp.core.posts.infrastructure.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetPostEndpoint {
    @GetMapping("/posts/{id}")
    fun apply(
        @PathVariable id: String,
    ): ResponseEntity<String> = ResponseEntity.ok("Post $id")
}
