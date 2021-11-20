package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val likes: Long = 0L,
    val shares: Long = 0L,
    val views: Long = 0L,
    val author: String = "",
    val published: String = "",
    val content: String = "",
    val video: String = "",
    val likedByMe: Boolean = false
) {

    fun toDto(postEntity: PostEntity): Post {
        return Post(
            id = postEntity.id,
            likes = postEntity.likes,
            shares = postEntity.shares,
            views = postEntity.views,
            author = postEntity.author,
            published = postEntity.published,
            content = postEntity.content,
            video = postEntity.video,
            likedByMe = postEntity.likedByMe
        )
    }

    fun fromDto(post: Post): PostEntity {
        return PostEntity(
            id = post.id,
            likes = post.likes,
            shares = post.shares,
            views = post.views,
            author = post.author,
            published = post.published,
            content = post.content,
            video = post.video,
            likedByMe = post.likedByMe
        )
    }
}