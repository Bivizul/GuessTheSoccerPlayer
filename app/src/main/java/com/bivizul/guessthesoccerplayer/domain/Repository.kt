package com.bivizul.guessthesoccerplayer.domain

import com.bivizul.guessthesoccerplayer.domain.model.Questions
import com.bivizul.guessthesoccerplayer.domain.model.Tilik
import com.bivizul.guessthesoccerplayer.domain.model.ZovServaka
import retrofit2.Response

interface Repository {

    suspend fun getQuestions(): Response<Questions>

    suspend fun getZovServaka(tilik: Tilik): Response<ZovServaka>

}