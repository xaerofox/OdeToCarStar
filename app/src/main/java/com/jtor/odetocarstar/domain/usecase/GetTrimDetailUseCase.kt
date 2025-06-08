package com.jtor.odetocarstar.domain.usecase

import com.jtor.odetocarstar.core.Resource
import com.jtor.odetocarstar.data.model.CarTrimDetail
import com.jtor.odetocarstar.data.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetTrimDetailUseCase @Inject constructor(
    private val repository: CarRepository
) {
    operator fun invoke(id: Int): Flow<Resource<CarTrimDetail>> = flow {
        try {
            emit(Resource.Loading())
            val detail = repository.getTrimDetail(id)
            emit(Resource.Success(detail))
        } catch (_: IOException) {
            emit(Resource.Error("Couldn't reach server. Please check your connection"))
        }

    }
}