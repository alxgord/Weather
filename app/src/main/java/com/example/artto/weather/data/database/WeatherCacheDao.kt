package com.example.artto.weather.data.database

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface WeatherCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: WeatherCacheEntity): Long

    @Query("SELECT * FROM weather_cache WHERE id = :id")
    fun select(id: Long): Single<WeatherCacheEntity>

    @Delete
    fun delete(entity: WeatherCacheEntity): Int

    @Query("DELETE FROM weather_cache")
    fun clear(): Int

}