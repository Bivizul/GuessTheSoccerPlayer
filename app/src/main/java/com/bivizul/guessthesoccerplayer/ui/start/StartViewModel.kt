package com.bivizul.guessthesoccerplayer.ui.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.guessthesoccerplayer.data.RepositoryImpl
import com.bivizul.guessthesoccerplayer.domain.Resource
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

    private val _start = MutableStateFlow<Resource<ZovServaka>>(Resource.Success(ZovServaka("")))
    val start: StateFlow<Resource<ZovServaka>> = _start.asStateFlow()

    fun getZovServaka(tilik: Tilik) {
        viewModelScope.launch(Dispatchers.IO) {
            _start.emit(Resource.Loading())
            val response = getZovServakaUseCase(tilik)
            if (response.isSuccessful) {
                response.body()?.let { zovPredka ->
                    _start.emit(Resource.Success(zovPredka))
                }
            } else {
                _start.emit(Resource.Error(response.message()))
            }
        }
    }
}