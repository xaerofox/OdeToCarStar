package com.jtor.odetocarstar.domain.usecase

import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.domain.model.CarModel
import com.jtor.odetocarstar.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetModelsUseCase @Inject constructor(
    private val repository: CarRepository
) {
    operator fun invoke(year: Int, make: String): Flow<Resource<List<CarModel>>> = flow {
        try {
            emit(Resource.Loading())
            val models = repository.getModels(year, make)
            emit(Resource.Success(models))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your connection"))
        }
    }
}