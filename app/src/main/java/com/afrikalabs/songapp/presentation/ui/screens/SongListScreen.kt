package com.afrikalabs.songapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.afrikalabs.songapp.domain.model.Song
import com.afrikalabs.songapp.presentation.ui.component.SearchBar
import com.afrikalabs.songapp.presentation.ui.component.SongItem
import com.afrikalabs.songapp.presentation.ui.component.VoiceSearchButton
import com.afrikalabs.songapp.presentation.viewmodel.SongViewModel

@Composable
fun SongListScreen(
    viewModel: SongViewModel,
    modifier: Modifier = Modifier,
    onSongClick: (Song) -> Unit = {}
) {
    val songs = viewModel.songs.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Appel du champ de recherche textuelle
        SearchBar(viewModel = viewModel)

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(songs.value) { song ->
                SongItem(song, onSongClick)
            }
        }
    }
}