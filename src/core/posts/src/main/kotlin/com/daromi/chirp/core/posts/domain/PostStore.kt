package com.daromi.chirp.core.posts.domain

interface PostStore {
    fun save(post: Post)

    fun search(id: PostId): Post?

    fun exists(id: PostId): Boolean
}
