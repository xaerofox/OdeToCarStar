package com.jtor.odetocarstar.domain.repository

import com.jtor.odetocarstar.domain.model.CarMake

interface CarRepository {
    suspend fun getMakes(year: Int?, sort: String?) : List<CarMake>
}