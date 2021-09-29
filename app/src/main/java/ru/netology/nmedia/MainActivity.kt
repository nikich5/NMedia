package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) {post ->
            with(binding) {
                avatar.setImageResource(R.drawable.ic_launcher_foreground)
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = formatNumber(post.likes)
                shareCount.text = formatNumber(post.shares)
                viewCount.text = formatNumber(post.views)
                if (!post.likedByMe) {
                    like.setImageResource(R.drawable.like)
                } else {
                    like.setImageResource(R.drawable.liked)
                }
        }
            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.share.setOnClickListener {
                viewModel.share()
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

