package com.afrikalabs.songapp.data.repository

import com.afrikalabs.songapp.data.datasource.SongLocalDataSource
import com.afrikalabs.songapp.data.model.SongEntity
import com.afrikalabs.songapp.domain.model.Song
import com.afrikalabs.songapp.domain.repository.SongRepository
import javax.inject.Inject


class SongRepositoryImpl @Inject constructor(private val dataSource: SongLocalDataSource) : SongRepository {
    override suspend fun getAllSongs(): List<Song> {
        return dataSource.getAllSongs().map { it.toDomain() }
    }

    override suspend fun searchByTitle(title: String): List<Song> {
        return dataSource.searchByTitle(title).map { it.toDomain() }
    }

    override suspend fun searchByContent(content: String): List<Song> {
        return dataSource.searchByContent(content).map { it.toDomain() }
    }

    override suspend fun searchByAuthor(author: String): List<Song> {
        return dataSource.searchByAuthor(author).map { it.toDomain() }
    }
}
private fun SongEntity.toDomain() = Song(id, title, author, lyrics)