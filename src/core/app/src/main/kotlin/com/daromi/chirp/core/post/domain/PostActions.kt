package com.daromi.chirp.core.post.domain

import java.time.LocalDateTime

data class FindPostQuery(
    val id: String,
)

fun findPost(
    repository: PostRepository,
    query: FindPostQuery,
): Nothing = TODO()

data class CreatePostCommand(
    val id: String,
    val userId: String,
    val content: String,
    val createdAt: LocalDateTime,
)

fun createPost(
    repository: PostRepository,
    command: CreatePostCommand,
): Nothing = TODO()

data class EditPostCommand(
    val id: String,
    val updatedAt: LocalDateTime,
    val content: String,
)

fun editPost(
    repository: PostRepository,
    command: EditPostCommand,
): Nothing = TODO()
