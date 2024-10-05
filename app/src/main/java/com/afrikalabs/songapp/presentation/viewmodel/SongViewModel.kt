package com.afrikalabs.songapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrikalabs.songapp.domain.model.Song
import com.afrikalabs.songapp.domain.usecase.GetAllSongsUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByAuthorUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByContentUseCase
import com.afrikalabs.songapp.domain.usecase.SearchSongByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val searchSongByTitleUseCase: SearchSongByTitleUseCase,
    private val searchSongByContentUseCase: SearchSongByContentUseCase,
    private val searchSongByAuthorUseCase: SearchSongByAuthorUseCase
) : ViewModel() {

    private var _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs

    init {
        loadAllSongs()
    }

    // Chargement de toutes les chansons au démarrage
    private fun loadAllSongs() {
        viewModelScope.launch {
            try {
                _songs.value = getAllSongsUseCase()
            } catch (e: Exception) {
                // Gérer les erreurs ici (par exemple afficher un message d'erreur dans l'UI)
            }
        }
    }

    // Recherche combinée par titre, contenu et auteur
    fun search(query: String) {
        viewModelScope.launch {
            try {
                // Lancer les trois recherches en parallèle
                val titleResults = async { searchSongByTitleUseCase(query) }
                val contentResults = async { searchSongByContentUseCase(query) }
                val authorResults = async { searchSongByAuthorUseCase(query) }

                // Combiner les résultats dans un Set pour éviter les doublons
                val combinedResults =
                    (titleResults.await() + contentResults.await() + authorResults.await()).distinct()

                // Mettre à jour la liste des chansons avec les résultats combinés
                _songs.value = combinedResults
            } catch (e: Exception) {
                // Gérer les erreurs ici
            }
        }
    }

    // Récupération d'une chanson par ID
    fun getSongById(songId: Int): Song {
        return _songs.value.find { it.id == songId }
            ?: throw IllegalArgumentException("Song not found")
    }
}
