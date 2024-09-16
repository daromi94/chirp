package com.daromi.chirp.core.post.domain

import arrow.core.Either
import arrow.core.flatMap
import com.daromi.chirp.core.shared.Error
import java.time.LocalDateTime

data class FindPostQuery(
    val id: String,
)

fun findPost(
    repository: PostRepository,
    query: FindPostQuery,
): Either<Error, PostResponse> = PostId.from(query.id).flatMap { id -> repository.search(id) }.map { post -> toResponse(post) }

data class CreatePostCommand(
    val id: String,
    val userId: String,
    val content: String,
    val createdAt: LocalDateTime,
)

fun createPost(
    repository: PostRepository,
    command: CreatePostCommand,
): Either<Error, Unit> {
    val post = Post.create(command.id, command.userId, command.content, command.createdAt)

    return repository.save(post)
}

data class EditPostCommand(
    val id: String,
    val updatedAt: LocalDateTime,
    val content: String,
)

fun editPost(
    repository: PostRepository,
    command: EditPostCommand,
): Nothing = TODO()

data class PostResponse(
    val id: String,
    val userId: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

private fun toResponse(post: Post) = PostResponse(post.id, post.userId, post.content, post.createdAt, post.updatedAt)
