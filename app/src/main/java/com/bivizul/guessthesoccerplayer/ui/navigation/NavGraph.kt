package com.bivizul.guessthesoccerplayer.ui.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bivizul.guessthesoccerplayer.domain.Util
import com.bivizul.guessthesoccerplayer.domain.model.Tilik
import com.bivizul.guessthesoccerplayer.ui.game.Game
import com.bivizul.guessthesoccerplayer.ui.main.Main
import com.bivizul.guessthesoccerplayer.ui.score.Score
import com.bivizul.guessthesoccerplayer.ui.start.Start
import com.bivizul.guessthesoccerplayer.ui.start.StartViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = Route.START,
    startViewModel: StartViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    startViewModel.getZovServaka(Tilik(Util.getPostZovServaka(context)))

    NavHost(navController = navHostController, startDestination = startDestination) {

        composable(route = startDestination) {
            Start(
                navController = navHostController,
                startViewModel = startViewModel
            )
        }
        composable(route = Route.MAIN) {
            Main(navController = navHostController)
            BackHandler {
                activity.finish()
            }
        }
        composable(route = Route.GAME) {
            Game(navController = navHostController)
            BackHandler {
                navHostController.navigate(Route.MAIN)
            }
        }
        composable(route = Route.SCORE) {
            Score(navController = navHostController)
            BackHandler {
                navHostController.navigate(Route.MAIN)
            }
        }

    }
}