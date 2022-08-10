package com.bivizul.guessthesoccerplayer.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ZovServaka(
    @SerializedName("url")
    val zovServaka: String,
)
