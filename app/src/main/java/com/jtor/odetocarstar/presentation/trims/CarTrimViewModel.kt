package com.jtor.odetocarstar.presentation.trims

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.domain.model.CarTrim
import com.jtor.odetocarstar.domain.usecase.GetTrimsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarTrimViewModel @Inject constructor(
    private val getTrimsUseCase: GetTrimsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TrimListState())
    val state: State<TrimListState> = _state

    fun getTrims(year: Int, modelId: Int) {
        getTrimsUseCase(year, modelId).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = TrimListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = TrimListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = TrimListState(trims = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class TrimListState(
    val isLoading: Boolean = false,
    val trims: List<CarTrim> = emptyList(),
    val error: String = ""
)