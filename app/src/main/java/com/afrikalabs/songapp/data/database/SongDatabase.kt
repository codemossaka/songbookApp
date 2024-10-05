package com.afrikalabs.songapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.afrikalabs.songapp.data.datasource.SongLocalDataSource
import com.afrikalabs.songapp.data.model.SongEntity

@Database(entities = [SongEntity::class], version = 1)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao(): SongLocalDataSource
}
