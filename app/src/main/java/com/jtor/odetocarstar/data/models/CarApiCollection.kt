package com.jtor.odetocarstar.data.models

data class CarApiCollection<T>(
    val collection: Collection,
    val data: List<T>
)