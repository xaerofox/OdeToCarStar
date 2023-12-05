package com.jtor.odetocarstar.presentation.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.domain.model.CarModel
import com.jtor.odetocarstar.domain.usecase.GetModelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarModelViewModel @Inject constructor(
    private val getModelsUseCase: GetModelsUseCase
): ViewModel() {

    private val _state = mutableStateOf(ModelListState())
    val state: State<ModelListState> = _state

    fun getModels(year: Int, make: String) {
        getModelsUseCase(year, make).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value =
                        _state.value.copy(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(models = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class ModelListState(
    val isLoading: Boolean = false,
    val models: List<CarModel> = emptyList(),
    val error: String = ""
)