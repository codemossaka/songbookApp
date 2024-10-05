package com.afrikalabs.songapp.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.afrikalabs.songapp.presentation.viewmodel.SongViewModel

@Composable
fun SongDetailScreen(viewModel: SongViewModel = viewModel(), songId: Int) {
    val song = viewModel.getSongById(songId)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = song.title, style = MaterialTheme.typography.headlineLarge)
        Text(text = "By ${song.author}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = song.lyrics, style = MaterialTheme.typography.bodyMedium)
    }
}
