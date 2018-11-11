package com.example.artto.weather.di.data

import android.arch.persistence.room.Room
import android.content.Context
import com.example.artto.weather.data.database.Database
import com.example.artto.weather.data.database.DatabaseConstants
import com.example.artto.weather.data.database.WeatherCacheDao
import com.example.artto.weather.data.database.WeatherCacheRepository
import com.example.artto.weather.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class WeatherCacheModule {

    @Provides
    @ApplicationScope
    fun database(context: Context) = Room.databaseBuilder(context, Database::class.java, DatabaseConstants.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @ApplicationScope
    fun weatherCacheDao(database: Database) = database.locationCacheDao()

    @Provides
    @ApplicationScope
    fun weatherCacheRepository(dao: WeatherCacheDao) = WeatherCacheRepository(dao)

}