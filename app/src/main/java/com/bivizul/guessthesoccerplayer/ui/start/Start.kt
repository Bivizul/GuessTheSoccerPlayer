package com.bivizul.guessthesoccerplayer.ui.start

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bivizul.guessthesoccerplayer.domain.ERROR_VIEW_MODEL
import com.bivizul.guessthesoccerplayer.domain.Util
import com.bivizul.guessthesoccerplayer.domain.model.ZovServaka
import com.bivizul.guessthesoccerplayer.ui.navigation.Route
import com.bivizul.guessthesoccerplayer.ui.widget.dopik
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Start(
    navController: NavController,
    startViewModel: StartViewModel,
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val zovServaka by startViewModel.start.collectAsState()

    when (zovServaka.zovServaka) {
        ERROR_VIEW_MODEL -> {
            Util.getDialogErrorConnect(context, activity)
        }
        "" -> {

        }
        else -> {
            StartScreen(navController = navController, zovServaka = zovServaka)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primarySurface,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = MaterialTheme.colors.secondary
            )
        }
    }

}

@Composable
fun StartScreen(
    navController: NavController,
    zovServaka: ZovServaka,
) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    LaunchedEffect(key1 = "key") {
        CoroutineScope(Dispatchers.Main).launch {
            zovServaka.zovServaka.let {
                if (it.length > 2) {
                    delay(1000)
                    dopik(context, zovServaka.zovServaka)
                    activity.finish()
                } else {
                    delay(1000)
                    navController.navigate(Route.MAIN)
                }
            }
        }
    }
}