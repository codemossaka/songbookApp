package com.afrikalabs.songapp.domain.usecase

import com.afrikalabs.songapp.domain.repository.SongRepository

class SearchSongByAuthorUseCase(private val repository: SongRepository) {
    suspend operator fun invoke(author: String) = repository.searchByAuthor(author)
}