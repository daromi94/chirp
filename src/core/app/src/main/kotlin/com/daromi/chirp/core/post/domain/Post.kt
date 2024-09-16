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

    val id: String get() = this._id.value

    val userId: String get() = this._userId.value

    val content: String get() = this._content.value

    val createdAt: LocalDateTime get() = this._createdAt.value

    val updatedAt: LocalDateTime get() = this._updatedAt.value

    fun update(
        content: String,
        updatedAt: LocalDateTime,
    ) {
        this._content = PostContent(content)
        this._updatedAt = PostUpdatedAt(updatedAt)
    }

    override fun toString(): String =
        "Post(id=${this.id}, userId=${this.userId}, content=${this.content}, createdAt=${this.createdAt}, updatedAt=${this.updatedAt})"
}

@JvmInline
value class PostId(
    val value: String,
) {
    companion object {
        fun from(value: String): Either<IllegalPostIdError, PostId> =
            either {
                ensure(value.isNotBlank()) {
                    IllegalPostIdError(value)
                }
                PostId(value)
            }

        data class IllegalPostIdError(
            val value: String,
        ) : Error {
            override val message: String get() = "illegal post id '${this.value}'"
        }
    }
}

@JvmInline
value class PostContent(
    val value: String,
) {
    companion object {
        private const val MAX_LENGTH: Int = 280

        fun from(value: String): Either<IllegalPostContentError, PostContent> =
            either {
                ensure(value.isNotBlank()) {
                    PostContentIsBlankError(value)
                }
                ensure(value.length <= MAX_LENGTH) {
                    PostContentExceedsMaxLengthError(value, MAX_LENGTH)
                }
                PostContent(value)
            }

        sealed interface IllegalPostContentError : Error

        data class PostContentIsBlankError(
            val value: String,
        ) : IllegalPostContentError {
            override val message: String get() = "post content cannot be blank"
        }

        data class PostContentExceedsMaxLengthError(
            val value: String,
            val maxLength: Int,
        ) : IllegalPostContentError {
            override val message: String get() = "post content exceeds the maximum length of ${this.maxLength} characters"
        }
    }
}

@JvmInline
value class PostCreatedAt(
    val value: LocalDateTime,
)

@JvmInline
value class PostUpdatedAt(
    val value: LocalDateTime,
)
