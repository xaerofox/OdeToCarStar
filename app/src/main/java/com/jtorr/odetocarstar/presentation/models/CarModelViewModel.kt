package com.jtorr.odetocarstar.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtorr.odetocarstar.data.model.CarModel
import com.jtorr.odetocarstar.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarModelViewModel @Inject constructor(
    private val repository: CarRepository
): ViewModel() {

    private val _state = MutableStateFlow(ModelListState())
    val state = _state.asStateFlow()

    fun getModels(year: Int, make: String) {
        viewModelScope.launch {
            _state.value = ModelListState(isLoading = true)
            try {
                val models = repository.getModels(year, make)
                _state.value = ModelListState(isLoading = false, models = models)
            } catch (e: Exception) {
                _state.value = ModelListState(isLoading = false, error = e.message ?: "An unexpected error occurred")
            }
        }
    }
}

data class ModelListState(
    val isLoading: Boolean = false,
    val models: List<CarModel> = emptyList(),
    val error: String = ""
)