package com.jtorr.odetocarstar.data.local

import androidx.room.TypeConverter
import com.jtorr.odetocarstar.data.model.MakeModel
import com.jtorr.odetocarstar.data.model.TrimBody
import com.jtorr.odetocarstar.data.model.TrimColor
import com.jtorr.odetocarstar.data.model.TrimEngine
import com.jtorr.odetocarstar.data.model.TrimMileage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type

class DatabaseTypeConverters {
    companion object {
        private val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @TypeConverter
    fun fromTrimBody(value: TrimBody?): String? {
        if (value == null) return null
        return moshi.adapter(TrimBody::class.java).toJson(value)
    }

    @TypeConverter
    fun toTrimBody(value: String?): TrimBody? {
        if (value == null) return null
        return moshi.adapter(TrimBody::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromTrimEngine(value: TrimEngine?): String? {
        if (value == null) return null
        return moshi.adapter(TrimEngine::class.java).toJson(value)
    }

    @TypeConverter
    fun toTrimEngine(value: String?): TrimEngine? {
        if (value == null) return null
        return moshi.adapter(TrimEngine::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromTrimMileage(value: TrimMileage?): String? {
        if (value == null) return null
        return moshi.adapter(TrimMileage::class.java).toJson(value)
    }

    @TypeConverter
    fun toTrimMileage(value: String?): TrimMileage? {
        if (value == null) return null
        return moshi.adapter(TrimMileage::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromMakeModel(value: MakeModel?): String? {
        if (value == null) return null
        return moshi.adapter(MakeModel::class.java).toJson(value)
    }

    @TypeConverter
    fun toMakeModel(value: String?): MakeModel? {
        if (value == null) return null
        return moshi.adapter(MakeModel::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromTrimColorList(value: List<TrimColor>?): String? {
        if (value == null) return null
        val type: Type = Types.newParameterizedType(List::class.java, TrimColor::class.java)
        val adapter: JsonAdapter<List<TrimColor>> = moshi.adapter(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun toTrimColorList(value: String?): List<TrimColor>? {
        if (value == null) return null
        val type: Type = Types.newParameterizedType(List::class.java, TrimColor::class.java)
        val adapter: JsonAdapter<List<TrimColor>> = moshi.adapter(type)
        return adapter.fromJson(value)
    }
}
