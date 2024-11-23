package com.daromi.chirp.core.posts.domain

interface PostRepository {
    fun save(post: Post)

    fun retrieve(id: PostId): Post?

    fun exists(id: PostId): Boolean
}
