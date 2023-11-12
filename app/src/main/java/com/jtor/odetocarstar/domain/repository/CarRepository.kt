package com.jtor.odetocarstar.domain.repository

interface CarRepository {
    suspend fun getMakes()
}