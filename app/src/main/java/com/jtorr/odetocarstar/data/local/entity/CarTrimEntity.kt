package com.jtorr.odetocarstar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_trims")
data class CarTrimEntity(
    val created: String,
    val description: String,
    val invoice: Int,
    val modelId: Int,
    val modified: String,
    val msrp: Int,
    val name: String,
    @PrimaryKey
    val id: Int,
    val year: Int
)
