package com.jtor.odetocarstar.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE car_makes ADD COLUMN cached_at INTEGER")
            database.execSQL("ALTER TABLE car_models ADD COLUMN cached_at INTEGER")
            database.execSQL("ALTER TABLE car_trims ADD COLUMN cached_at INTEGER")
            database.execSQL("ALTER TABLE car_trim_details ADD COLUMN cached_at INTEGER")
        }
    }
}
