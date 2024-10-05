package com.afrikalabs.songapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afrikalabs.songapp.presentation.ui.screens.SongDetailScreen
import com.afrikalabs.songapp.presentation.ui.screens.SongListScreen
import com.afrikalabs.songapp.presentation.viewmodel.SongViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val songViewModel = hiltViewModel<SongViewModel>()
    NavHost(modifier = modifier, navController = navController, startDestination = "songList") {
        composable("songList") {

            SongListScreen(viewModel = songViewModel) { song ->
                navController.navigate("songDetail/${song.id}")
            }
        }
        composable("songDetail/{songId}") { backStackEntry ->
            val songId = backStackEntry.arguments?.getString("songId")?.toInt() ?: 0
            SongDetailScreen(viewModel = songViewModel, songId = songId)
        }
    }
}
