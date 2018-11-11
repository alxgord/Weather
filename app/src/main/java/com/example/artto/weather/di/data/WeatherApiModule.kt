package com.example.artto.weather.di.data

import com.example.artto.weather.data.network.weather.WeatherRepository
import com.example.artto.weather.data.network.weather.WeatherApiConstants
import com.example.artto.weather.data.network.weather.WeatherApiMethods
import com.example.artto.weather.di.scope.ApplicationScope
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
class WeatherApiModule {

    @Provides
    @ApplicationScope
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()


    @Provides
    @ApplicationScope
    fun objectMapper(): ObjectMapper =
        ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)


    @Provides
    @ApplicationScope
    fun converterFactory(objectMapper: ObjectMapper): JacksonConverterFactory =
        JacksonConverterFactory.create(objectMapper)


    @Provides
    @ApplicationScope
    fun retrofit(client: OkHttpClient, jacksonConverterFactory: JacksonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(WeatherApiConstants.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(jacksonConverterFactory)
            .build()


    @Provides
    @ApplicationScope
    fun apiMethods(retrofit: Retrofit): WeatherApiMethods =
        retrofit.create<WeatherApiMethods>(WeatherApiMethods::class.java)


    @Provides
    @ApplicationScope
    fun weatherRepository(methods: WeatherApiMethods): WeatherRepository = WeatherRepository(methods)

}