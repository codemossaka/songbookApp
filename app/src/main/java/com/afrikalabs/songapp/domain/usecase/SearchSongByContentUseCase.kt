package com.afrikalabs.songapp.domain.usecase

import com.afrikalabs.songapp.domain.repository.SongRepository
import javax.inject.Inject

class SearchSongByContentUseCase @Inject constructor(private val repository: SongRepository) {
    suspend operator fun invoke(content: String) = repository.searchByContent(content)
}