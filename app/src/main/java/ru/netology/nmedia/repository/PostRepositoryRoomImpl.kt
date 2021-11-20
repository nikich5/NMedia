package ru.netology.nmedia.repository

import androidx.lifecycle.Transformations
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.entity.PostEntity


class PostRepositoryRoomImpl(private val dao: PostDao) : PostRepository {

    override fun getAll() = Transformations.map(dao.getAll()) { list ->
        list.map {
            Post(
                id = it.id,
                likes = it.likes,
                shares = it.shares,
                views = it.views,
                author = it.author,
                published = it.published,
                content = it.content,
                video = it.video,
                likedByMe = it.likedByMe
            )
        }
    }

    override fun likeById(id: Long) {
        dao.likeById(id)
    }

    override fun shareById(id: Long) {
        dao.shareById(id)
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }

    override fun findById(id: Long): Post {
        if(id == 0L) { return Post() }
        return PostEntity().toDto(dao.findById(id))
    }

    override fun save(post: Post) {
        dao.save(PostEntity().fromDto(post))
    }
}
