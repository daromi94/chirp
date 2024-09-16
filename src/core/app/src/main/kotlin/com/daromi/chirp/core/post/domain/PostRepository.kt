package com.daromi.chirp.core.post.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface PostRepository {
    fun save(post: Post): Either<PostSaveError, Unit>

    fun search(id: PostId): Either<PostSearchError, Post>
}

sealed interface PostSaveError : Error

data class PostSaveFailedError(
    val id: PostId,
) : PostSaveError {
    override val message: String get() = "failed to save post with id '${this.id.value}'"
}

sealed interface PostSearchError : Error

data class PostSearchFailedError(
    val id: PostId,
) : PostSearchError {
    override val message: String get() = "failed to search for post with id '${this.id.value}'"
}

data class PostNotFoundError(
    val id: PostId,
) : PostSearchError {
    override val message: String get() = "post with id '${this.id.value}' was not found"
}
