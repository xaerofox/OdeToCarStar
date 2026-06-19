package com.jtor.odetocarstar.data.local.dao

import androidx.room.*
import com.jtor.odetocarstar.data.local.entity.CarTrimEntity

@Dao
interface CarTrimDao {
    @Query("SELECT * FROM car_trims WHERE year = :year AND modelId = :modelId ORDER BY name")
    suspend fun getTrims(year: Int, modelId: Int): List<CarTrimEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrims(trims: List<CarTrimEntity>)

    @Query("DELETE FROM car_trims")
    suspend fun clearAll()
}
