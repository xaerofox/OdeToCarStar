package com.jtor.odetocarstar.data.local

import android.content.Context
import androidx.room.Room
import com.jtor.odetocarstar.data.local.dao.CarMakeDao
import com.jtor.odetocarstar.data.local.dao.CarModelDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDao
import com.jtor.odetocarstar.data.local.dao.CarTrimDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "odetocarstar_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCarMakeDao(database: AppDatabase): CarMakeDao {
        return database.carMakeDao()
    }

    @Provides
    @Singleton
    fun provideCarModelDao(database: AppDatabase): CarModelDao {
        return database.carModelDao()
    }

    @Provides
    @Singleton
    fun provideCarTrimDao(database: AppDatabase): CarTrimDao {
        return database.carTrimDao()
    }

    @Provides
    @Singleton
    fun provideCarTrimDetailDao(database: AppDatabase): CarTrimDetailDao {
        return database.carTrimDetailDao()
    }
}
