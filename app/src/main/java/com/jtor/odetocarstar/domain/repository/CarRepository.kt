package com.jtor.odetocarstar.domain.repository

import com.jtor.odetocarstar.domain.model.CarMake
import com.jtor.odetocarstar.domain.model.CarModel

interface CarRepository {
    suspend fun getMakes(year: Int?, sort: String?) : List<CarMake>

    suspend fun getModels(year: Int, make: String) : List<CarModel>
}