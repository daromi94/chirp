package com.daromi.chirp.core.post.domain

import com.daromi.chirp.core.user.domain.UserId
import java.time.LocalDateTime

data class PostId(
    val value: String,
)

data class PostCreatedAt(
    val value: LocalDateTime,
)

data class PostUpdatedAt(
    val value: LocalDateTime,
)

data class PostContent(
    val value: String,
)

class Post private constructor(
    private val id: PostId,
    private val userId: UserId,
    private val createdAt: PostCreatedAt,
    private var updatedAt: PostUpdatedAt,
    private var content: PostContent,
) {
    companion object {
        @JvmStatic
        fun create(
            id: String,
            userId: String,
            createdAt: LocalDateTime,
            content: String,
        ): Post = Post(PostId(id), UserId(userId), PostCreatedAt(createdAt), PostUpdatedAt(createdAt), PostContent(content))
    }

    fun update(
        updatedAt: LocalDateTime,
        content: String,
    ) {
        this.updatedAt = PostUpdatedAt(updatedAt)
        this.content = PostContent(content)
    }

    override fun toString(): String =
        "Post(id=${id.value}, userId=${userId.value}, createdAt=${createdAt.value}, updatedAt=${updatedAt.value}, content=${content.value})"
}

interface PostRepository {
    fun save(post: Post): Post?

    fun search(id: PostId): Post?
}
