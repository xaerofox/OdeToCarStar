package com.jtorr.odetocarstar.data.local.dao

import androidx.room.*
import com.jtorr.odetocarstar.data.local.entity.CarTrimDetailEntity

@Dao
interface CarTrimDetailDao {
    @Query("SELECT * FROM car_trim_details WHERE id = :id AND year = :year")
    suspend fun getDetail(id: Int, year: Int): CarTrimDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: CarTrimDetailEntity)

    @Query("DELETE FROM car_trim_details")
    suspend fun clearAll()
}
