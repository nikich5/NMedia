package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            likes = 999L,
            shares = 923325L,
            views = 1253644L,
            author = "Нетология",
            published = "25 сентября в 13:37",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        )

        with(binding) {
            avatar.setImageResource(R.drawable.ic_launcher_foreground)
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = formatNumber(post.likes)
            shareCount.text = formatNumber(post.shares)
            viewCount.text = formatNumber(post.views)
            if (post.likedByMe) {
                like.setImageResource(R.drawable.liked)
            }

            like.setOnClickListener {
                if(!post.likedByMe) {
                    like.setImageResource(R.drawable.liked)
                    post.likedByMe = !post.likedByMe
                    post.likes++
                    likeCount.text = formatNumber(post.likes)
                } else {
                    like.setImageResource(R.drawable.like)
                    post.likedByMe = !post.likedByMe
                    post.likes--
                    likeCount.text = formatNumber(post.likes)
                }
            }

            share.setOnClickListener {
                post.shares++
                shareCount.text = formatNumber(post.shares)
            }
        }
    }
}

fun formatNumber(number: Long): String {
    val result = when {
        (number >= 1_000_000) -> {
            val newNumber = (number.toDouble() / 1000000)
            if (String.format("%.1f", newNumber).endsWith("0")) {
                return String.format("%.0f", newNumber) + "M"
            }
            return String.format("%.1f", newNumber) + "M"
        }

        (number >= 10_000) -> {
            val newNumber = number / 1000
            return "$newNumber" + "K"
        }

        (number >= 1_000) -> {
            val newNumber: Double = (number.toDouble() / 1000)
            if (String.format("%.1f", newNumber).endsWith("0")) {
                return String.format("%.0f", newNumber) + "K"
            }
            return String.format("%.1f", newNumber) + "K"
        }

        else -> "$number"
    }
    return result
}