package com.afrikalabs.songapp.domain.usecase

import com.afrikalabs.songapp.domain.repository.SongRepository
import javax.inject.Inject

class SearchSongByTitleUseCase  @Inject constructor(private val repository: SongRepository) {
    suspend operator fun invoke(title: String) = repository.searchByTitle(title)
}