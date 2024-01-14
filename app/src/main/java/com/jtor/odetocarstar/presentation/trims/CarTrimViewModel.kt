package com.jtor.odetocarstar.presentation.trims

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.jtor.odetocarstar.BuildConfig
import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.domain.model.CarTrim
import com.jtor.odetocarstar.domain.model.CarTrimDetail
import com.jtor.odetocarstar.domain.repository.CarRepository
import com.jtor.odetocarstar.domain.usecase.GetTrimsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarTrimViewModel @Inject constructor(
    private val getTrimsUseCase: GetTrimsUseCase,
    private val repository: CarRepository
) : ViewModel() {

    private lateinit var generativeModel: GenerativeModel

    private val _state = mutableStateOf(TrimListState())
    val state: State<TrimListState> = _state

    private val _detailState = MutableStateFlow(TrimDetailState())
    val detailState = _detailState.asStateFlow()

    private val _carFactState = MutableStateFlow(CarFactState())
    val carFactState = _carFactState.asStateFlow()

    init {
        generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = BuildConfig.GEMINI_PREVIEW_KEY
        )
    }

    fun getTrims(year: Int, modelId: Int) {
        getTrimsUseCase(year, modelId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value =
                        TrimListState(error = result.message ?: "An unexpected error occurred")
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

    fun getTrimDetail(id: Int) {
        _detailState.value = TrimDetailState(isLoading = true)
        viewModelScope.launch {
            runCatching {
                repository.getTrimDetail(id)
            }.onSuccess {
                _detailState.value = TrimDetailState(detail = it)
            }.onFailure {
                _detailState.value = TrimDetailState(error = it.message ?: "YO! SUMTHING WENT WRONG!")
            }
        }
    }

    fun genCarFact(year: Int, make: String, model: String) {
        _carFactState.value = CarFactState(isLoading = true)
        val prompt = "Give me an interesting fact about the $year $make $model."
        viewModelScope.launch {
            runCatching {
                generativeModel.generateContent(prompt)
            }.onSuccess {
                _carFactState.value = CarFactState(response = it.text ?: "No generative response")
            }.onFailure {
                _carFactState.value = CarFactState(error = it.message ?: "An error occurred with generative response")
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

data class CarFactState(
    val isLoading: Boolean = false,
    val response: String = "",
    val error: String = ""
)