package com.bivizul.guessthesoccerplayer.domain.model

import androidx.annotation.Keep

@Keep
data class Question(
    val id: Int,
    val image: String,
    val question: String,
    val yes: String,
    val no: String,
    val answer: String,
)