package com.jtorr.odetocarstar.data.local.dao

import androidx.room.*
import com.jtorr.odetocarstar.data.local.entity.CarModelEntity

@Dao
interface CarModelDao {
    @Query("SELECT * FROM car_models WHERE year = :year AND makeId = :makeId ORDER BY name")
    suspend fun getModels(year: Int, makeId: Int): List<CarModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(models: List<CarModelEntity>)

    @Query("DELETE FROM car_models")
    suspend fun clearAll()
}
