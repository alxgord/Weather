package com.example.artto.weather.data.database

import io.reactivex.Single

class WeatherCacheRepository(private val dao: WeatherCacheDao) {

    fun insert(entity: WeatherCacheEntity): Single<Long> = Single.fromCallable { dao.insert(entity) }

    fun select(id: Long): Single<WeatherCacheEntity> = dao.select(id)

    fun clear(): Single<Int> = Single.fromCallable { dao.clear() }

}