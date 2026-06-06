package com.jtor.odetocarstar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jtor.odetocarstar.data.local.DatabaseTypeConverters
import com.jtor.odetocarstar.data.model.MakeModel
import com.jtor.odetocarstar.data.model.TrimBody
import com.jtor.odetocarstar.data.model.TrimColor
import com.jtor.odetocarstar.data.model.TrimEngine
import com.jtor.odetocarstar.data.model.TrimMileage

@Entity(tableName = "car_trim_details")
@TypeConverters(DatabaseTypeConverters::class)
data class CarTrimDetailEntity(
    val created: String,
    val description: String,
    val invoice: Int,
    val modelId: Int,
    val modified: String,
    val msrp: Int,
    val name: String,
    @PrimaryKey
    val id: Int,
    val year: Int,
    val makeModel: MakeModel?,
    val trimBody: TrimBody?,
    val trimEngine: TrimEngine?,
    val trimExteriorColors: List<TrimColor>?,
    val trimInteriorColors: List<TrimColor>?,
    val trimMileage: TrimMileage?
)
