package com.bivizul.guessthesoccerplayer.ui.main

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bivizul.guessthesoccerplayer.R
import com.bivizul.guessthesoccerplayer.data.SettingPreferences
import com.bivizul.guessthesoccerplayer.domain.Util
import com.bivizul.guessthesoccerplayer.ui.navigation.Route
import com.bivizul.guessthesoccerplayer.ui.theme.Typography
import com.bivizul.guessthesoccerplayer.ui.widget.AppButton

@Composable
fun Main(
    navController: NavController,
) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    var bool by remember { mutableStateOf(true) }
    val painter =
        if (bool) {
            R.drawable.ic_notifications
        } else {
            R.drawable.ic_notifications_off
        }

    SettingPreferences(activity).clearData()
    val randomList = Util.createRandomList()
    SettingPreferences(activity).saveList(randomList)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primarySurface,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding()
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = painter),
                    contentDescription = "Notifications",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            when (bool) {
                                true -> {
                                    bool = false
                                    Toast
                                        .makeText(context, R.string.not_off, Toast.LENGTH_SHORT)
                                        .show()
                                }
                                false -> {
                                    bool = true
                                    Toast
                                        .makeText(context, R.string.not_on, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.app_name), style = Typography.h1)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AppButton(
                    onClick = { navController.navigate(Route.GAME) },
                    text = stringResource(R.string.game))
            }
        }
    }

}

