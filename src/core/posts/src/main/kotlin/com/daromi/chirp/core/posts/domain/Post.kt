package com.daromi.chirp.core.posts.domain

import com.daromi.chirp.core.users.domain.UserId

class Post private constructor(
    private val id: PostId,
    private val userId: UserId,
    private var content: PostContent,
) {
    companion object {
        @JvmStatic
        fun create(
            rawId: String,
            rawUserId: String,
            rawContent: String,
        ): Post? {
            val id = PostId.from(rawId) ?: return null
            val userId = UserId.from(rawUserId) ?: return null
            val content = PostContent.from(rawContent) ?: return null

            return Post(id, userId, content)
        }
    }

    fun changeContent(content: String): Boolean {
        this.content = PostContent.from(content) ?: return false

        return true
    }
}

@JvmInline
value class PostId(
    val value: String,
) {
    companion object {
        @JvmStatic
        fun from(value: String): PostId? {
            if (value.isBlank()) {
                return null
            }

            return PostId(value)
        }
    }
}

@JvmInline
private value class PostContent(
    val value: String,
) {
    companion object {
        private const val MAX_LENGTH: Int = 280

        @JvmStatic
        fun from(value: String): PostContent? {
            if (value.isBlank()) {
                return null
            }

            if (value.length > MAX_LENGTH) {
                return null
            }

            return PostContent(value)
        }
    }
}
