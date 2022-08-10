package com.bivizul.guessthesoccerplayer.ui.game

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bivizul.guessthesoccerplayer.data.SettingPreferences
import com.bivizul.guessthesoccerplayer.domain.Resource
import com.bivizul.guessthesoccerplayer.domain.Util
import com.bivizul.guessthesoccerplayer.domain.model.Questions
import com.bivizul.guessthesoccerplayer.ui.theme.Typography
import com.bivizul.guessthesoccerplayer.ui.theme.compositedOnSurface
import com.bivizul.guessthesoccerplayer.ui.widget.ProgressButton

@Composable
fun Game(
    navController: NavController,
    viewModel: GameViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val randomList = SettingPreferences(activity).getList()
    val counter = SettingPreferences(activity).getCounter()
    val score = SettingPreferences(activity).getScore()
    val resourceQuestions by viewModel.gameQuestions.collectAsState()

    when (resourceQuestions) {
        is Resource.Loading -> {}
        is Resource.Success -> {
            resourceQuestions.data?.let { questions ->
                GameScreen(
                    navController = navController,
                    gameQuestions = questions,
                    randomList = randomList,
                    counter = counter,
                    score = score
                )
            }
        }
        is Resource.Error -> {
            Util.getDialogErrorConnect(context, activity)
        }
    }
}

@Composable
fun GameScreen(
    navController: NavController,
    gameQuestions: Questions,
    randomList: List<Int>,
    counter: Int,
    score: Int,
    placeholderColor: Color = MaterialTheme.colors.compositedOnSurface(0.2f),
) {

    val item = gameQuestions.questions[randomList[counter]]
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primarySurface,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = "image",
                    placeholder = ColorPainter(placeholderColor)
                )
                Text(
                    text = item.question,
                    modifier = Modifier.padding(16.dp),
                    style = Typography.h2
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ProgressButton(
                    navController = navController,
                    item = item,
                    counter = counter,
                    score = score,
                    bool = true,
                )
                ProgressButton(
                    navController = navController,
                    item = item,
                    counter = counter,
                    score = score,
                    bool = false,
                )
            }
        }
    }

}




