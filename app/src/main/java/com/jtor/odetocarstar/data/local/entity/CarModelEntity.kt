package com.jtor.odetocarstar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_models")
data class CarModelEntity(
    val name: String,
    @PrimaryKey
    val id: Int,
    val year: Int,
    val makeId: Int
)
