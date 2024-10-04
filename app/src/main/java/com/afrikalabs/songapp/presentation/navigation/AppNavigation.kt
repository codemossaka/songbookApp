//package com.afrikalabs.songapp.presentation.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.songsearchapp.domain.model.Song
//import com.example.songsearchapp.presentation.ui.SongDetailScreen
//import com.example.songsearchapp.presentation.ui.SongListScreen
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "songList") {
//        composable("songList") {
//            SongListScreen(viewModel = songViewModel) { song ->
//                navController.navigate("songDetail/${song.id}")
//            }
//        }
//        composable("songDetail/{songId}") { backStackEntry ->
//            val songId = backStackEntry.arguments?.getString("songId")?.toInt() ?: 0
//            val song = songViewModel.getSongById(songId)
//            song?.let { SongDetailScreen(it) }
//        }
//    }
//}
