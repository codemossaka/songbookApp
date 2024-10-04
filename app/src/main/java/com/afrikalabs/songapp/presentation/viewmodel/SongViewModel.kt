package com.afrikalabs.songapp.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrikalabs.songapp.domain.model.Song
import com.afrikalabs.songapp.domain.usecase.GetAllSongsUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByAuthorUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByContentUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByTitleUseCase
import kotlinx.coroutines.launch

class SongViewModel(
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val searchSongByTitleUseCase: SearchSongByTitleUseCase,
    private val searchSongByContentUseCase: SearchSongByContentUseCase,
    private val searchSongByAuthorUseCase: SearchSongByAuthorUseCase
) : ViewModel() {

    var songs by mutableStateOf<List<Song>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Initialiser la liste avec toutes les chansons
    init {
        loadAllSongs()
    }

    private fun loadAllSongs() {
        viewModelScope.launch {
            try {
                isLoading = true
                songs = getAllSongsUseCase()
            } catch (e: Exception) {
                errorMessage = "Erreur de chargement des chansons."
            } finally {
                isLoading = false
            }
        }
    }

    // Fonction de recherche par titre
    fun searchByTitle(title: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                songs = searchSongByTitleUseCase(title)
            } catch (e: Exception) {
                errorMessage = "Erreur de recherche par titre."
            } finally {
                isLoading = false
            }
        }
    }

    // Fonction de recherche par auteur
    fun searchByAuthor(author: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                songs = searchSongByAuthorUseCase(author)
            } catch (e: Exception) {
                errorMessage = "Erreur de recherche par auteur."
            } finally {
                isLoading = false
            }
        }
    }

    // Fonction de recherche par contenu (paroles)
    fun searchByContent(content: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                songs = searchSongByContentUseCase(content)
            } catch (e: Exception) {
                errorMessage = "Erreur de recherche par contenu."
            } finally {
                isLoading = false
            }
        }
    }
}
