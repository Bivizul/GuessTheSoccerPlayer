package com.bivizul.guessthesoccerplayer.ui.score

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.bivizul.guessthesoccerplayer.data.SettingPreferences
import com.bivizul.guessthesoccerplayer.ui.navigation.Route
import com.bivizul.guessthesoccerplayer.ui.theme.Typography
import com.bivizul.guessthesoccerplayer.ui.widget.AppButton

@Composable
fun Score(
    navController: NavController,
) {
    val activity = LocalContext.current as Activity

    val score = SettingPreferences(activity).getScore()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primarySurface,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "Score : $score", style = Typography.h2)
            AppButton(onClick = { navController.navigate(Route.MAIN) }, text = "To main")
        }
    }
}

