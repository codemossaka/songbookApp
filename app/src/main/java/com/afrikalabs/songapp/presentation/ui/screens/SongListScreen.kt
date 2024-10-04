package com.afrikalabs.songapp.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.afrikalabs.songapp.domain.model.Song
import com.afrikalabs.songapp.presentation.ui.component.SongItem
import com.afrikalabs.songapp.presentation.viewmodel.SongViewModel

@Composable
fun SongListScreen(
    viewModel: SongViewModel,
    onSongClick: (Song) -> Unit
) {
    val songs = viewModel.songs

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(songs) { song ->
            SongItem(song, onSongClick)
        }
    }
}