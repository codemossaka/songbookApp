package com.afrikalabs.songapp.presentation.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.afrikalabs.songapp.presentation.viewmodel.SongViewModel

@Composable
fun SearchBar(
    viewModel: SongViewModel,
) {
    // Gestion du texte de la recherche avec TextFieldValue
    var query by remember { mutableStateOf(TextFieldValue("")) }

    // Gestion du focus et du clavier
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Gestion du BackHandler pour enlever le focus lorsqu'on clique sur le bouton retour
    var isFocused by remember { mutableStateOf(false) }

    // Active le BackHandler uniquement si le champ de texte a le focus
    BackHandler(enabled = isFocused) {
        focusRequester.freeFocus()
        keyboardController?.hide()  // Fermer le clavier
        isFocused = false  // Mettre à jour l'état de focus
    }

    TextField(
        value = query,
        onValueChange = { newValue ->
            query = newValue
            if (query.text.isNotEmpty()) {
                viewModel.search(query.text)  // Rechercher uniquement si le texte n'est pas vide
            }
        },
        trailingIcon = {
            if (query.text.isNotEmpty()) {
                AnimatedVisibility(
                    visible = query.text.isNotEmpty(),  // Montrer ou cacher l'icône
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = {
                        query = TextFieldValue("")
                        viewModel.search("")  // Effectuer une recherche vide
                        focusRequester.freeFocus()
                        keyboardController?.hide()
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Effacer")
                    }
                }
            } else {
                VoiceSearchButton(  // Si aucun texte, afficher le bouton de recherche vocale
                    onQueryChange = { voiceText ->
                        query = TextFieldValue(
                            text = voiceText,
                            selection = TextRange(voiceText.length)  // Place le curseur à la fin du texte
                        )
                        viewModel.search(voiceText)  // Effectuer la recherche avec le texte vocal
                    },
                    viewModel = viewModel
                )
            }
        },
        label = { Text("Recherche par titre...") },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)  // Applique le FocusRequester
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused  // Met à jour l'état de focus
            },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search  // Action "Rechercher" pour le clavier
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                isFocused = false
                keyboardController?.hide()  // Fermer le clavier lors de la recherche
                focusRequester.freeFocus()  // Enlever le focus après la recherche
            }
        )
    )
}
