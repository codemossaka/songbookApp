package com.afrikalabs.songapp.domain.usecase

import com.afrikalabs.songapp.domain.repository.SongRepository

class SearchSongByContentUseCase(private val repository: SongRepository) {
    suspend operator fun invoke(content: String) = repository.searchByContent(content)
}