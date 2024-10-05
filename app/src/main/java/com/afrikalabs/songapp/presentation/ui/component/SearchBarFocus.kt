package com.afrikalabs.songapp.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.afrikalabs.songapp.presentation.viewmodel.SongViewModel



@Composable
fun SearchBarFocus(
    viewModel: SongViewModel,
) {
    // Utilisation de TextFieldValue pour contrôler le texte et la position du curseur
    var query by remember { mutableStateOf(TextFieldValue("")) }

    // FocusRequester pour demander le focus
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = query,
        onValueChange = { newValue ->
            query = newValue
            if (query.text.isNotEmpty()) {
                viewModel.search(query.text)
            }
        },
        trailingIcon = {
            VoiceSearchButton(
                onQueryChange = { voiceText ->
                    query = TextFieldValue(
                        text = voiceText,
                        selection = TextFieldValue(voiceText).copy(
                            selection = TextRange(voiceText.length) // Place le curseur à la fin du texte
                        ).selection
                    )
                    viewModel.search(query.text)
                    // Demande le focus et ouvre le clavier
                    focusRequester.requestFocus()
                    keyboardController?.show()
                },
                viewModel = viewModel
            )
        },
        label = { Text(text = "Recherche par titre...") },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),  // Applique le focusRequester
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()  // Ferme le clavier lors de la recherche
            }
        )
    )
}