package com.daromi.chirp.core.post.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface PostRepository {
    fun save(post: Post): Either<PostSaveFailedError, Post>

    fun search(id: PostId): Either<PostSearchError, Post>
}

data class PostSaveFailedError(
    val id: String,
) : Error {
    override val message: String get() = "post '${this.id}' save failed"
}

sealed class PostSearchError : Error {
    data class PostSearchFailedError(
        val id: String,
    ) : PostSearchError() {
        override val message: String get() = "post '${this.id}' search failed"
    }

    data class PostNotFoundError(
        val id: String,
    ) : PostSearchError() {
        override val message: String get() = "post '${this.id}' not found"
    }
}
