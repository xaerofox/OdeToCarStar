package com.jtorr.odetocarstar.data.remote.dto

import com.jtorr.odetocarstar.data.model.Collection

data class CollectionDto<T>(
    val collection: Collection,
    val data: List<T>
)