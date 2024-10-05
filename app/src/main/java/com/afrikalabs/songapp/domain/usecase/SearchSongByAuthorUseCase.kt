package com.afrikalabs.songapp.domain.usecase

import com.afrikalabs.songapp.domain.repository.SongRepository
import javax.inject.Inject

class SearchSongByAuthorUseCase  @Inject constructor(private val repository: SongRepository) {
    suspend operator fun invoke(author: String) = repository.searchByAuthor(author)
}