package com.afrikalabs.songapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val author: String,
    val lyrics: String
)