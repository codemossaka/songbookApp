package com.afrikalabs.songapp.domain.usecase

import com.afrikalabs.songapp.domain.repository.SongRepository

class SearchSongByTitleUseCase(private val repository: SongRepository) {
    suspend operator fun invoke(title: String) = repository.searchByTitle(title)
}