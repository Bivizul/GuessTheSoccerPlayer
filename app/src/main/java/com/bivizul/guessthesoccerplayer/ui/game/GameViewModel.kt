package com.bivizul.guessthesoccerplayer.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.guessthesoccerplayer.data.RepositoryImpl
import com.bivizul.guessthesoccerplayer.domain.Resource
import com.bivizul.guessthesoccerplayer.domain.case.GetQuestionsUseCase
import com.bivizul.guessthesoccerplayer.domain.model.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(repositoryImpl: RepositoryImpl) : ViewModel() {

    private val getQuestionsUseCase = GetQuestionsUseCase(repositoryImpl)

    private val _gameQuestion = MutableStateFlow<Resource<Questions>>(Resource.Loading())
    val gameQuestions: StateFlow<Resource<Questions>> = _gameQuestion.asStateFlow()

    init {
        viewModelScope.launch {
            _gameQuestion.emit(Resource.Loading())
            val response = getQuestionsUseCase()
            if (response.isSuccessful) {
                response.body()?.let { questions ->
                    _gameQuestion.emit(Resource.Success(questions))
                }
            } else {
                _gameQuestion.emit(Resource.Error(response.message()))
            }
        }
    }

}