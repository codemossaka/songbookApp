package com.afrikalabs.songapp.presentation.ui.component

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardVoice
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.afrikalabs.songapp.presentation.viewmodel.SongViewModel
import java.util.Locale

@Composable
fun VoiceSearchButton(
    onQueryChange: (String) -> Unit, // Met à jour la barre de recherche avec la voix
    viewModel: SongViewModel,
) {
    val context = LocalContext.current

    // Launcher pour démarrer la reconnaissance vocale
    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = results?.get(0) ?: ""
            onQueryChange(spokenText) // Mettre à jour la barre de recherche avec le texte reconnu
            viewModel.search(spokenText) // Déclenche la recherche avec le texte reconnu
        }
    }

    // Intent pour la reconnaissance vocale
    val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toString())
        putExtra(RecognizerIntent.EXTRA_PROMPT, "Dites le titre, l'auteur ou les paroles")
    }
    IconButton(onClick = {
        speechRecognizerLauncher.launch(speechRecognizerIntent)
    }) {
        Icon(Icons.Default.KeyboardVoice, contentDescription = "Recherche")
    }
}
