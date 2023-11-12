package com.jtor.odetocarstar.presentation.makes

import androidx.lifecycle.ViewModel
import com.jtor.odetocarstar.domain.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarMakeViewModel @Inject constructor(
    private val repository: CarRepository
) : ViewModel() {
}