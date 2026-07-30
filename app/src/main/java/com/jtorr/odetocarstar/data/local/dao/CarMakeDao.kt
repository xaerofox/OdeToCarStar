package com.jtorr.odetocarstar.data.local.dao

import androidx.room.*
import com.jtorr.odetocarstar.data.local.entity.CarMakeEntity

@Dao
interface CarMakeDao {
    @Query("SELECT * FROM car_makes WHERE year = :year ORDER BY name")
    suspend fun getMakes(year: Int): List<CarMakeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMakes(makes: List<CarMakeEntity>)

    @Query("DELETE FROM car_makes")
    suspend fun clearAll()
}
