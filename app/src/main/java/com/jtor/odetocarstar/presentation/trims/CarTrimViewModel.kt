package com.jtor.odetocarstar.presentation.trims

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtor.odetocarstar.data.model.CarTrim
import com.jtor.odetocarstar.data.model.CarTrimDetail
import com.jtor.odetocarstar.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarTrimViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TrimListState())
    val state = _state.asStateFlow()

    private val _detailState = MutableStateFlow(TrimDetailState())
    val detailState = _detailState.asStateFlow()

    fun getTrims(year: Int, modelId: Int) {
        viewModelScope.launch {
            _state.value = TrimListState(isLoading = true)
            try {
                val trims = repository.getTrims(year, modelId)
                _state.value = TrimListState(isLoading = false, trims = trims)
            } catch (e: Exception) {
                _state.value = TrimListState(isLoading = false, error = e.message ?: "An unexpected error occurred")
            }
        }
    }

    fun getTrimDetail(id: Int) {
        viewModelScope.launch {
            _detailState.value = TrimDetailState(isLoading = true)
            try {
                val detail = repository.getTrimDetail(id)
                _detailState.value = TrimDetailState(isLoading = false, detail = detail)
            } catch (e: Exception) {
                _detailState.value = TrimDetailState(isLoading = false, error = e.message ?: "An unexpected error occurred")
            }
        }
    }
}

data class TrimListState(
    val isLoading: Boolean = false,
    val trims: List<CarTrim> = emptyList(),
    val error: String = ""
)

data class TrimDetailState(
    val isLoading: Boolean = false,
    val detail: CarTrimDetail? = null,
    val error: String = ""
)