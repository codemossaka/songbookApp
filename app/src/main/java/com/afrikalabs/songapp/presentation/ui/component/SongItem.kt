package com.afrikalabs.songapp.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.afrikalabs.songapp.domain.model.Song

@Composable
fun SongItem(song: Song, onClick: (Song) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(song) }
            .padding(8.dp)
    ) {
        Column {
            Text(text = song.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = "By ${song.author}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}