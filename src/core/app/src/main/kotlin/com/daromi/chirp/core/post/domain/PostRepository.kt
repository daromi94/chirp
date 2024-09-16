package com.daromi.chirp.core.post.domain

interface PostRepository {
    fun save(post: Post)

    fun search(id: PostId): Post?
}
