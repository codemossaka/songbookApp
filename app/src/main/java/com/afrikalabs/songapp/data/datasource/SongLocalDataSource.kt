package com.afrikalabs.songapp.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrikalabs.songapp.data.model.SongEntity

@Dao
interface SongLocalDataSource {

    // Requête pour obtenir toutes les chansons
    @Query("SELECT * FROM songs")
    suspend fun getAllSongs(): List<SongEntity>

    // Requête pour chercher par titre
    @Query("SELECT * FROM songs WHERE title LIKE '%' || :title || '%'")
    suspend fun searchByTitle(title: String): List<SongEntity>

    // Requête pour chercher par contenu (paroles)
    @Query("SELECT * FROM songs WHERE lyrics LIKE '%' || :content || '%'")
    suspend fun searchByContent(content: String): List<SongEntity>

    // Requête pour chercher par auteur
    @Query("SELECT * FROM songs WHERE author LIKE '%' || :author || '%'")
    suspend fun searchByAuthor(author: String): List<SongEntity>

    // Insérer une liste de chansons dans la base de données
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songs: List<SongEntity>)
}
