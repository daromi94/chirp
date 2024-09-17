package com.daromi.chirp.core.posts.domain

import com.daromi.chirp.core.users.domain.UserId
import java.time.Clock
import java.time.Instant

class Post private constructor(
    private val _id: PostId,
    private val _userId: UserId,
    private var _content: PostContent,
    private val _createdAt: PostCreatedAt,
    private var _updatedAt: PostUpdatedAt,
) {
    companion object {
        fun create(
            rawId: String,
            rawUserId: String,
            rawContent: String,
            rawCreatedAt: Instant,
        ): Post? {
            val id      = PostId.from(rawId)           ?: return null
            val userId  = UserId.from(rawUserId)       ?: return null
            val content = PostContent.from(rawContent) ?: return null

            val createdAt = PostCreatedAt(rawCreatedAt)
            val updatedAt = PostUpdatedAt(rawCreatedAt)

            return Post(
                id,
                userId,
                content,
                createdAt,
                updatedAt,
            )
        }
    }

    val id: String get() = this._id.value

    val userId: String get() = this._userId.value

    val content: String get() = this._content.value

    val createdAt: Instant get() = this._createdAt.value

    val updatedAt: Instant get() = this._updatedAt.value

    fun update(
        rawContent: String,
        clock: Clock,
    ): Boolean {
        val content = PostContent.from(rawContent) ?: return false

        val updatedAt = PostUpdatedAt(clock.instant())
        check(updatedAt.isAfter(this._createdAt) && updatedAt.isAfter(this._updatedAt))

        this._content   = content
        this._updatedAt = updatedAt

        return true
    }

    override fun toString(): String =
        "Post(id=${this.id}, userId=${this.userId}, content=${this.content}, createdAt=${this.createdAt}, updatedAt=${this.updatedAt})"
}

@JvmInline
value class PostId(
    val value: String,
) {
    companion object {
        fun from(value: String): PostId? = if (value.isBlank()) null else PostId(value)
    }
}

@JvmInline
private value class PostContent(
    val value: String,
) {
    companion object {
        private const val MAX_LENGTH: Int = 280

        fun from(value: String): PostContent? = if (value.isBlank() || value.length > MAX_LENGTH) null else PostContent(value)
    }
}

@JvmInline
private value class PostCreatedAt(
    val value: Instant,
)

@JvmInline
private value class PostUpdatedAt(
    val value: Instant,
) {
    fun isAfter(createdAt: PostCreatedAt): Boolean = this.value.isAfter(createdAt.value)

    fun isAfter(updatedAt: PostUpdatedAt): Boolean = this.value.isAfter(updatedAt.value)
}
