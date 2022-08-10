package com.bivizul.guessthesoccerplayer.ui.widget

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bivizul.guessthesoccerplayer.data.SettingPreferences
import com.bivizul.guessthesoccerplayer.domain.model.Question
import com.bivizul.guessthesoccerplayer.ui.navigation.Route
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonState
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonType
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSJetPackComposeProgressButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProgressButton(
    navController: NavController,
    item: Question,
    counter: Int,
    score: Int,
    bool: Boolean,
) {

    val activity = LocalContext.current as Activity
    var submitButtonState by remember { mutableStateOf(SSButtonState.IDLE) }

    val itemBool = when (bool) {
        true -> item.yes
        false -> item.no
    }

    SSJetPackComposeProgressButton(
        type = SSButtonType.CLOCK,
        width = 150.dp,
        height = 50.dp,
        onClick = {
            submitButtonState = SSButtonState.LOADING
            CoroutineScope(Dispatchers.Main).launch {
                if (itemBool == item.answer) {
                    SettingPreferences(activity).incrementCounter(counter)
                    SettingPreferences(activity).incrementScore(score)
                    submitButtonState = SSButtonState.SUCCESS
                    delay(1000)
                    nextScreen(navController, counter)
                } else {
                    SettingPreferences(activity).incrementCounter(counter)
                    submitButtonState = SSButtonState.FAILIURE
                    delay(1000)
                    nextScreen(navController, counter)
                }
            }
        },
        assetColor = MaterialTheme.colors.primarySurface,
        buttonState = submitButtonState,
        buttonBorderStroke = BorderStroke(width = 2.dp, MaterialTheme.colors.onSecondary),
        cornerRadius = 20,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary),
        text = itemBool,
        fontWeight = FontWeight.Bold,
        hourHandColor = MaterialTheme.colors.primary
    )
}

fun nextScreen(navController: NavController, counter: Int) {
    if (counter < 8) {
        navController.navigate(Route.GAME)
    } else {
        navController.navigate(Route.SCORE)
    }
}