package com.bivizul.guessthesoccerplayer.domain.case

import com.bivizul.guessthesoccerplayer.domain.Repository
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() = repository.getQuestions()

}