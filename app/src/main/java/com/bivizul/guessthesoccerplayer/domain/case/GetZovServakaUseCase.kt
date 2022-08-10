package com.bivizul.guessthesoccerplayer.domain.case

import com.bivizul.guessthesoccerplayer.domain.Repository
import com.bivizul.guessthesoccerplayer.domain.model.Tilik
import javax.inject.Inject

class GetZovServakaUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(tilik: Tilik) = repository.getZovServaka(tilik)

}