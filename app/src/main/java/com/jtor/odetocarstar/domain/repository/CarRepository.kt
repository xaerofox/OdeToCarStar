package com.jtor.odetocarstar.domain.repository

import com.jtor.odetocarstar.domain.model.CarMake
import com.jtor.odetocarstar.domain.model.CarModel
import com.jtor.odetocarstar.domain.model.CarTrim

interface CarRepository {
    suspend fun getMakes(year: Int?, sort: String?) : List<CarMake>

    suspend fun getModels(year: Int, make: String) : List<CarModel>

    suspend fun getTrims(year: Int, modelId: Int) : List<CarTrim>
}