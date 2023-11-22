package com.jtor.odetocarstar.data.remote.dto

import com.jtor.odetocarstar.domain.model.Collection

data class CollectionDto<T>(
    val collection: Collection,
    val data: List<T>
)