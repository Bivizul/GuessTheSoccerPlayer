package com.bivizul.guessthesoccerplayer.data

import com.bivizul.guessthesoccerplayer.domain.Repository
import com.bivizul.guessthesoccerplayer.domain.model.Questions
import com.bivizul.guessthesoccerplayer.domain.model.Tilik
import com.bivizul.guessthesoccerplayer.domain.model.ZovServaka
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val ethernetService: EthernetService) :
    Repository {

    override suspend fun getQuestions(): Response<Questions> = ethernetService.getQuestions()

    override suspend fun getZovServaka(tilik: Tilik): Response<ZovServaka> =
        ethernetService.getZovServaka(tilik)
}