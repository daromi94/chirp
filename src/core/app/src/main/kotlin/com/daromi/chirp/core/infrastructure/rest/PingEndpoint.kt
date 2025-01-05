package com.daromi.chirp.core.infrastructure.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingEndpoint {
    @GetMapping("/ping")
    fun apply(): ResponseEntity<String> = ResponseEntity.ok("pong")
}
