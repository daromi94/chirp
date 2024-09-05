package com.daromi.chirp.core.post.domain

import arrow.core.Either
import com.daromi.chirp.core.shared.Error

interface PostRepository {
    fun save(post: Post): Either<PostSaveFailedError, Post>

    fun search(id: PostId): Either<PostSearchError, Post>
}

class PostSaveFailedError(
    private val id: String,
) : Error {
    override val message: String get() = "post '${this.id}' save failed"
}

sealed class PostSearchError(
    protected val id: String,
) : Error {
    class PostSearchFailedError(
        id: String,
    ) : PostSearchError(id) {
        override val message: String get() = "post '${super.id}' search failed"
    }

    class PostNotFoundError(
        id: String,
    ) : PostSearchError(id) {
        override val message: String get() = "post '${this.id}' not found"
    }
}
