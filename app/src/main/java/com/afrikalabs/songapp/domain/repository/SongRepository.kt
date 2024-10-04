package com.afrikalabs.songapp.domain.repository

import com.afrikalabs.songapp.domain.model.Song

interface SongRepository {
    suspend fun getAllSongs(): List<Song>
    suspend fun searchByTitle(title: String): List<Song>
    suspend fun searchByContent(content: String): List<Song>
    suspend fun searchByAuthor(author: String): List<Song>
}