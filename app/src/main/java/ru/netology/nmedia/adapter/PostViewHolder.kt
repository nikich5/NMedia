package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ItemPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder (
    private val binding: ItemPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
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

            like.setOnClickListener {
                onLikeListener(post)
            }

            share.setOnClickListener {
                onShareListener(post)
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
