package com.afrikalabs.songapp.domain.usecase

import com.afrikalabs.songapp.domain.repository.SongRepository

class GetAllSongsUseCase(private val repository: SongRepository) {
    suspend operator fun invoke() = repository.getAllSongs()
}