package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ItemPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Format

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
            likeCount.text = Format.formatNumber(post.likes)
            shareCount.text = Format.formatNumber(post.shares)
            viewCount.text = Format.formatNumber(post.views)
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