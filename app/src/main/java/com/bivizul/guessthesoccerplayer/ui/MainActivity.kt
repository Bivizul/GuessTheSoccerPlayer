package com.bivizul.guessthesoccerplayer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.bivizul.guessthesoccerplayer.domain.Util
import com.bivizul.guessthesoccerplayer.domain.model.Tilik
import com.bivizul.guessthesoccerplayer.ui.navigation.NavGraph
import com.bivizul.guessthesoccerplayer.ui.start.StartViewModel
import com.bivizul.guessthesoccerplayer.ui.theme.GuessTheSoccerPlayerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheSoccerPlayerTheme {
                if (Util.checkEthernet(this)) {
                    NavGraph()
                } else {
                    Util.getDialogErrorConnect(this, this)
                }
            }
        }
    }
}
