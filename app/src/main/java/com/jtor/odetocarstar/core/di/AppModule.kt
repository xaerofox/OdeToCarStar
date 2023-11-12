package com.jtor.odetocarstar.core.di

import com.jtor.odetocarstar.BuildConfig
import com.jtor.odetocarstar.data.remote.CarApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCarApi(): CarApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(CarApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideCarRepository(api: CarApi): CarRepository {
//        return CarRepositoryImpl(api)
//    }
}