package com.bivizul.guessthesoccerplayer.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.guessthesoccerplayer.data.RepositoryImpl
import com.bivizul.guessthesoccerplayer.domain.ERROR_VIEW_MODEL
import com.bivizul.guessthesoccerplayer.domain.case.GetZovServakaUseCase
import com.bivizul.guessthesoccerplayer.domain.model.Tilik
import com.bivizul.guessthesoccerplayer.domain.model.ZovServaka
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(repositoryImpl: RepositoryImpl) : ViewModel() {

    private val getZovServakaUseCase = GetZovServakaUseCase(repositoryImpl)

    private val _start = MutableStateFlow<ZovServaka>(ZovServaka(""))
    val start: StateFlow<ZovServaka> = _start.asStateFlow()

    fun getZovServaka(tilik: Tilik) {
        viewModelScope.launch(Dispatchers.IO) {
            getZovServakaUseCase(tilik).let { response ->
                if (response.isSuccessful) {
                    response.body()?.let { zovPredka ->
                        _start.emit(zovPredka)
                    }
                } else {
                    _start.emit(ZovServaka(ERROR_VIEW_MODEL))
                }
            }
        }
    }
}