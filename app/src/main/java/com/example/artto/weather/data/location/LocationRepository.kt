package com.example.artto.weather.data.location

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class LocationRepository(private val locationDataStore: LocationDataStore) {

    private val locationBehaviorSubject = BehaviorSubject.createDefault(locationDataStore.load())

    val locationObservable: Observable<Location> = locationBehaviorSubject.map { it.copy() }

    val locationSingle: Single<Location> = locationObservable.firstOrError()

    fun update(location: Location): Completable = Completable.fromCallable {
        locationDataStore.save(location)
        locationBehaviorSubject.onNext(location)
    }

}