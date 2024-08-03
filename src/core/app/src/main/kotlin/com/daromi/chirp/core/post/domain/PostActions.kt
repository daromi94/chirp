package com.daromi.chirp.core.post.domain

import java.time.LocalDateTime

data class CreatePostCommand(
    val id: String,
    val userId: String,
    val createdAt: LocalDateTime,
    val content: String,
)

fun createPost(
    command: CreatePostCommand,
    repository: PostRepository,
) {
    val post = Post.create(command.id, command.userId, command.content, command.createdAt)
    repository.save(post)
}

data class EditPostCommand(
    val id: String,
    val updatedAt: LocalDateTime,
    val content: String,
)

fun editPost(
    command: EditPostCommand,
    repository: PostRepository,
): Nothing = TODO()
