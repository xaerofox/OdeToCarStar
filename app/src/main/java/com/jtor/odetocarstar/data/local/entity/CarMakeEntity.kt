package com.jtor.odetocarstar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_makes")
data class CarMakeEntity(
    val name: String,
    @PrimaryKey
    val id: Int,
    val year: Int
)
