package com.daromi.chirp.core.post.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.daromi.chirp.core.shared.Error
import com.daromi.chirp.core.user.domain.UserId
import java.time.LocalDateTime

class Post private constructor(
    private val _id: PostId,
    private val _userId: UserId,
    private var _content: PostContent,
    private val _createdAt: PostCreatedAt,
    private var _updatedAt: PostUpdatedAt,
) {
    companion object {
        fun create(
            id: String,
            userId: String,
            content: String,
            createdAt: LocalDateTime,
        ): Post =
            Post(
                PostId(id),
                UserId(userId),
                PostContent(content),
                PostCreatedAt(createdAt),
                PostUpdatedAt(createdAt),
            )
    }

    val id: String get() = _id.value

    val userId: String get() = _userId.value

    val content: String get() = _content.value

    val createdAt: LocalDateTime get() = _createdAt.value

    val updatedAt: LocalDateTime get() = _updatedAt.value

    fun update(
        content: String,
        updatedAt: LocalDateTime,
    ) {
        this._content = PostContent(content)
        this._updatedAt = PostUpdatedAt(updatedAt)
    }

    override fun toString(): String = "Post(id=$id, userId=$userId, content=$content, createdAt=$createdAt, updatedAt=$updatedAt)"
}

data class PostId(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalPostIdError, PostId> =
            either {
                ensure(value.isEmpty()) {
                    IllegalPostIdError(value)
                }
                PostId(value)
            }

        class IllegalPostIdError(
            private val value: String,
        ) : Error {
            override val message: String get() = "illegal post id '$value'"
        }
    }
}

data class PostContent(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalPostCommentError, PostContent> =
            either {
                ensure(value.isEmpty()) {
                    IllegalPostCommentError(value)
                }
                PostContent(value)
            }

        class IllegalPostCommentError(
            private val value: String,
        ) : Error {
            override val message: String get() = "illegal post comment '$value'"
        }
    }
}

data class PostCreatedAt(
    val value: LocalDateTime,
)

data class PostUpdatedAt(
    val value: LocalDateTime,
)
