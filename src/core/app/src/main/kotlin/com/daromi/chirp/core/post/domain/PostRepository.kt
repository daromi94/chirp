package com.daromi.chirp.core.post.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface PostRepository {
    fun save(post: Post): Either<PostSaveFailedError, Post>

    fun search(id: PostId): Either<PostSearchError, Post>
}

data class PostSaveFailedError(
    val id: PostId,
) : Error {
    override val message: String get() = "post '${this.id.value}' save failed"
}

sealed interface PostSearchError : Error

data class PostSearchFailedError(
    val id: PostId,
) : PostSearchError {
    override val message: String get() = "post '${this.id.value}' search failed"
}

data class PostNotFoundError(
    val id: PostId,
) : PostSearchError {
    override val message: String get() = "post '${this.id.value}' not found"
}
