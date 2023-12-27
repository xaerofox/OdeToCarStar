package com.jtor.odetocarstar.domain.usecase

import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.domain.model.CarTrim
import com.jtor.odetocarstar.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetTrimsUseCase @Inject constructor(
    private val repository: CarRepository
) {
    operator fun invoke(year: Int, modelId: Int): Flow<Resource<List<CarTrim>>> = flow {
        try {
            emit(Resource.Loading())
            val trims = repository.getTrims(year, modelId)
            emit(Resource.Success(trims))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Please check your connection"))
        }
    }
}