package com.jtor.odetocarstar.presentation.makes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.data.model.CarMake
import com.jtor.odetocarstar.domain.usecase.GetMakesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarMakeViewModel @Inject constructor(
    private val getMakesUseCase: GetMakesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MakeListState())
    val state: State<MakeListState> = _state

    init {
        getMakes()
    }

    private fun getMakes() {
        getMakesUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value =
                        MakeListState(error = result.message ?: "An unexpected error has occurred")
                }

                is Resource.Loading -> {
                    _state.value = MakeListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = MakeListState(makes = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class MakeListState(
    val isLoading: Boolean = false,
    val makes: List<CarMake> = emptyList(),
    val error: String = ""
)