package com.afrikalabs.songapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.afrikalabs.songapp.presentation.navigation.AppNavigation
import com.afrikalabs.songapp.presentation.ui.theme.SongAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SongAppTheme {
                Scaffold {
                    AppNavigation(modifier = Modifier.padding(it))
                }
            }
        }
    }
}