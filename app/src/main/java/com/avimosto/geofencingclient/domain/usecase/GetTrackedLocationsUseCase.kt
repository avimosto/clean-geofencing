package com.avimosto.geofencingclient.domain.usecase

import com.avimosto.geofencingclient.data.entity.TrackedLocation
import com.avimosto.geofencingclient.domain.repository.LocationRepository
import com.avimosto.geofencingclient.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetTrackedLocationsUseCase
    @Inject constructor(private val locationRepository: LocationRepository,
                        subscriberThread: Scheduler = Schedulers.io(),
                        observerThread: Scheduler = AndroidSchedulers.mainThread())

    : UseCase<List<TrackedLocation>>(subscriberThread, observerThread) {

    override fun observable(data: Map<String, Any>?): Observable<List<TrackedLocation>> {
        return locationRepository.trackedLocations().toObservable()
    }
}