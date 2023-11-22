package com.jtor.odetocarstar.core.di

import com.jtor.odetocarstar.BuildConfig
import com.jtor.odetocarstar.data.remote.CarApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCarApi(): CarApi {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(SimpleAuthInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(CarApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideCarRepository(api: CarApi): CarRepository {
//        return CarRepositoryImpl(api)
//    }
}

class SimpleAuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val updatedRequest = originalRequest.newBuilder()
            .header("X-RapidAPI-Key", BuildConfig.RAPID_API_KEY)
            .header("X-RapidAPI-Host", BuildConfig.RAPID_HOST)

            .build()

        return chain.proceed(updatedRequest)
    }
}