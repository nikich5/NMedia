package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentItemPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Format
import ru.netology.nmedia.utils.StringArg
import ru.netology.nmedia.viewModel.PostViewModel

class ItemPostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentItemPostBinding.inflate(inflater, container, false)

        val post = viewModel.currentPost.value ?: Post()
        viewModel.currentPost.observe(viewLifecycleOwner, {
            binding.apply {
                avatar.setImageResource(R.drawable.ic_launcher_foreground)
                content.movementMethod = ScrollingMovementMethod()
                author.text = it.author
                published.text = it.published
                content.text = it.content
                share.text = Format.formatNumber(it.shares)
                view.text = Format.formatNumber(it.views)
                like.isChecked = it.likedByMe
                like.text = Format.formatNumber(it.likes)
                videoGroup.isVisible = it.video.isNotBlank()
            }
        })

        with(binding) {
            like.setOnClickListener {
                viewModel.likeById(post.id)
                sync(post.id)
            }

            share.setOnClickListener {
                viewModel.shareById(post.id)
                sync(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, "")
                startActivity(shareIntent)
            }

            playButton.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(post.video)
                }
                startActivity(intent)
            }

            videoImage.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(post.video)
                }
                startActivity(intent)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                viewModel.removeById(post.id)
                                findNavController().navigateUp()
                                true
                            }
                            R.id.edit -> {
                                viewModel.edit(post)
                                findNavController().navigate(R.id.action_itemPostFragment_to_editPostFragment,
                                    Bundle().apply
                                    { textArg = post.content })
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
        return binding.root
    }
    private fun sync(id: Long) {
        viewModel.currentPost.value = viewModel.findById(id)
    }
}

