package com.daromi.chirp.core

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    @GetMapping("/ping")
    fun apply(): ResponseEntity<String> = ResponseEntity.ok("pong")
}
