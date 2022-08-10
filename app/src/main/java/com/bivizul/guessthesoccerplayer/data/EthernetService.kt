package com.bivizul.guessthesoccerplayer.data

import com.bivizul.guessthesoccerplayer.domain.model.Questions
import com.bivizul.guessthesoccerplayer.domain.model.Tilik
import com.bivizul.guessthesoccerplayer.domain.model.ZovServaka
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EthernetService {

    @GET("18GuessTheSoccerPlayer/questions.json")
    suspend fun getQuestions(): Response<Questions>

    @POST("18GuessTheSoccerPlayer/zovservaka.php")
    suspend fun getZovServaka(@Body tilik: Tilik): Response<ZovServaka>

}