package com.jtor.odetocarstar.data.repository

import com.jtor.odetocarstar.domain.repository.CarApi
import com.jtor.odetocarstar.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val api: CarApi
) : CarRepository {
    override suspend fun getMakes() {

    }
}