package com.jtorr.odetocarstar.presentation.makes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtorr.odetocarstar.data.model.CarMake
import com.jtorr.odetocarstar.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarMakeViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {

    companion object {
        private const val DEFAULT_YEAR = 2015
        private const val ID_FIELD = "id"
    }

    private val _state = MutableStateFlow<MakeListState>(MakeListState.Loading)
    val state = _state.asStateFlow()

    private var loadJob: Job? = null

    /**
     * Loads the makes list for the current session. Once a load has completed
     * successfully, or is currently in flight, redundant calls (e.g. from
     * recomposition or config-change re-entry) are ignored so the cached
     * result is never clobbered or re-fetched unnecessarily.
     */
    fun getMakes() {
        if (_state.value is MakeListState.Success || loadJob?.isActive == true) {
            return
        }

        loadJob = viewModelScope.launch {
            _state.value = MakeListState.Loading
            try {
                val makes = repository.getMakes(DEFAULT_YEAR, ID_FIELD)
                _state.value = MakeListState.Success(makes)
            } catch (e: Exception) {
                _state.value = MakeListState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

}

sealed interface MakeListState {
    data object Loading : MakeListState
    data class Success(val makes: List<CarMake>) : MakeListState
    data class Error(val message: String) : MakeListState
}
