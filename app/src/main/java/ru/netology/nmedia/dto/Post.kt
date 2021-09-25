package ru.netology.nmedia.dto

data class Post (
    val id: Long = 0L,
    var likes: Long = 0L,
    var shares: Long = 0L,
    var views: Long = 0L,
    val author: String = "",
    val published: String = "",
    val content: String = "",
    var likedByMe: Boolean = false
        )