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
    override val message: String get() = "post '$id' save failed"
}

sealed class PostSearchError : Error

class PostSearchFailedError(
    private val id: String,
) : PostSearchError() {
    override val message: String get() = "post '$id' search failed"
}

class PostNotFoundError(
    private val id: String,
) : PostSearchError() {
    override val message: String get() = "post '$id' not found"
}
