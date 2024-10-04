package com.afrikalabs.songapp.data.datasource

import com.afrikalabs.songapp.data.model.SongEntity

interface SongLocalDataSource {
    suspend fun getAllSongs(): List<SongEntity>
    suspend fun searchByTitle(title: String): List<SongEntity>
    suspend fun searchByContent(content: String): List<SongEntity>
    suspend fun searchByAuthor(author: String): List<SongEntity>
}