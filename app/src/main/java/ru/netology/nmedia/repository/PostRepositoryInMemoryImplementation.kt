package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post


class PostRepositoryInMemoryImplementation : PostRepository {

    private var post = Post(
        likes = 999L,
        shares = 923325L,
        views = 1253644L,
        author = "Нетология",
        published = "25 сентября в 13:37",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
    )
    private val data: LiveData<Post> = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        if (!post.likedByMe) {
//            like.setImageResource(R.drawable.liked)
            post = post.copy(likedByMe = !post.likedByMe, likes = post.likes + 1L)
            data.value = post
//            likeCount.text = formatNumber(post.likes)
        } else {
//            like.setImageResource(R.drawable.like)
            post = post.copy(likedByMe = !post.likedByMe, likes = post.likes - 1L)
            data.value = post
//            likeCount.text = formatNumber(post.likes)
        }
    }

    override fun share() {
        post = post.copy(shares = post.shares + 1L)
        data.value = post
//        shareCount.text = formatNumber(post.shares)
    }


}