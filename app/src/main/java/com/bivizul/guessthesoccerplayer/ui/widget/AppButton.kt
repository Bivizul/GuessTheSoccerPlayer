package com.bivizul.guessthesoccerplayer.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bivizul.guessthesoccerplayer.ui.theme.Typography
import com.simform.ssjetpackcomposeprogressbuttonlibrary.utils.two

@Composable
fun AppButton(
    onClick: () -> Unit,
    text: String,
) {

    Button(
        onClick = onClick,
        modifier = Modifier.size(width = 150.dp, height = 50.dp),
        shape = RoundedCornerShape(size = 10.dp),
        border = BorderStroke(width = two.dp, color = MaterialTheme.colors.onSecondary),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
    ) {
        Text(text = text, style = Typography.button)
    }

}