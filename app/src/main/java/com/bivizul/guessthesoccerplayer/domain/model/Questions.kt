package com.bivizul.guessthesoccerplayer.domain.model

import androidx.annotation.Keep

@Keep
data class Questions(
    val questions: List<Question>,
)