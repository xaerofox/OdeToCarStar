package com.jtor.odetocarstar.domain.usecase

import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.domain.model.CarMake
import com.jtor.odetocarstar.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMakesUseCase @Inject constructor(
    private val repository: CarRepository
){
    operator fun invoke(): Flow<Resource<List<CarMake>>> = flow {
        try {
            emit(Resource.Loading())
            val makes = repository.getMakes(2015, "id")
            emit(Resource.Success(makes))

        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your connection"))

        }
    }
}