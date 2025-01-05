package com.daromi.chirp.core.posts.application

import com.daromi.chirp.core.posts.domain.Post
import com.daromi.chirp.core.posts.domain.PostStore

class PostCreator(
    private val store: PostStore,
) {
    fun apply(command: CreatePostCommand) {
        val post = Post.create(command.id, command.userId, command.content)
        if (post == null) {
            TODO()
        }

        store.save(post)
    }
}

data class CreatePostCommand(
    val id: String,
    val userId: String,
    val content: String,
)
