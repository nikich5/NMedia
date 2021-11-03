package ru.netology.nmedia.adapter

import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ItemPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Format

class PostViewHolder (
    private val binding: ItemPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            avatar.setImageResource(R.drawable.ic_launcher_foreground)
            author.text = post.author
            published.text = post.published
            content.text = post.content
            share.text = Format.formatNumber(post.shares)
            view.text = Format.formatNumber(post.views)
            like.isChecked = post.likedByMe
            like.text = Format.formatNumber(post.likes)
            videoGroup.isVisible = post.video.isNotBlank()

            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            playButton.setOnClickListener {
                onInteractionListener.onPlay(post)
            }

            videoImage.setOnClickListener {
                onInteractionListener.onPlay(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}