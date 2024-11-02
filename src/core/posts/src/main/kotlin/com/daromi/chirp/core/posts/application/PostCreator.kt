package com.daromi.chirp.core.posts.application

import com.daromi.chirp.core.posts.domain.Post
import com.daromi.chirp.core.posts.domain.PostRepository

class PostCreator(
    private val repository: PostRepository,
) {
    fun apply(command: CreatePostCommand) {
        val post = Post.create(command.id, command.userId, command.content)
        if (post == null) {
            TODO()
        }

        repository.save(post)
    }
}

data class CreatePostCommand(
    val id: String,
    val userId: String,
    val content: String,
)
