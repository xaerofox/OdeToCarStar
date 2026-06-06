package com.jtor.odetocarstar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jtor.odetocarstar.data.local.dao.CarMakeDao
import com.jtor.odetocarstar.data.local.dao.CarModelDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDetailDao
import com.jtor.odetocarstar.data.local.entity.CarMakeEntity
import com.jtor.odetocarstar.data.local.entity.CarModelEntity
import com.jtor.odetocarstar.data.local.entity.CarTrimDetailEntity
import com.jtor.odetocarstar.data.local.entity.CarTrimEntity

@Database(
    entities = [
        CarMakeEntity::class,
        CarModelEntity::class,
        CarTrimEntity::class,
        CarTrimDetailEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carMakeDao(): CarMakeDao
    abstract fun carModelDao(): CarModelDao
    abstract fun carTrimDao(): CarTrimDao
    abstract fun carTrimDetailDao(): CarTrimDetailDao
}
