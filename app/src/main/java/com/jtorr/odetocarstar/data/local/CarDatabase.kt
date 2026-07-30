package com.jtorr.odetocarstar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jtorr.odetocarstar.data.local.dao.CarMakeDao
import com.jtorr.odetocarstar.data.local.dao.CarModelDao
import com.jtorr.odetocarstar.data.local.dao.CarTrimDao
import com.jtorr.odetocarstar.data.local.dao.CarTrimDetailDao
import com.jtorr.odetocarstar.data.local.entity.CarMakeEntity
import com.jtorr.odetocarstar.data.local.entity.CarModelEntity
import com.jtorr.odetocarstar.data.local.entity.CarTrimDetailEntity
import com.jtorr.odetocarstar.data.local.entity.CarTrimEntity

@Database(
    entities = [
        CarMakeEntity::class,
        CarModelEntity::class,
        CarTrimEntity::class,
        CarTrimDetailEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carMakeDao(): CarMakeDao
    abstract fun carModelDao(): CarModelDao
    abstract fun carTrimDao(): CarTrimDao
    abstract fun carTrimDetailDao(): CarTrimDetailDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}
