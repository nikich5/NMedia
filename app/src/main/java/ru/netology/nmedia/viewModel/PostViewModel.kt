package ru.netology.nmedia.viewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepositoryInMemoryImplementation

class PostViewModel : ViewModel() {
    private val repository = PostRepositoryInMemoryImplementation()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}