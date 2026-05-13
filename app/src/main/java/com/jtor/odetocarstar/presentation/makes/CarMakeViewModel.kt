package com.jtor.odetocarstar.presentation.makes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarMakeViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MakeListState())
    val state = _state.asStateFlow()

    init {
        getMakes()
    }

    fun getMakes() {
        viewModelScope.launch {
            _state.value = MakeListState(isLoading = true)
            try {
                val makes = repository.getMakes(2015, "id")
                _state.value = MakeListState(isLoading = false, makes = makes)
            } catch (e: Exception) {
                _state.value = MakeListState(isLoading = false, error = e.message ?: "An unexpected error has occurred")
            }
        }
    }
}

data class MakeListState(
    val isLoading: Boolean = false,
    val makes: List<CarMake> = emptyList(),
    val error: String = ""
)