package com.avimosto.geofencingclient.domain.usecase

import android.location.Address
import com.avimosto.geofencingclient.domain.repository.LocationRepository
import com.avimosto.geofencingclient.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException
import javax.inject.Inject

class SetTrackedLocationUseCase
@Inject constructor(private val locationRepository: LocationRepository,
                    subscriberThread: Scheduler = Schedulers.io(),
                    observerThread: Scheduler = AndroidSchedulers.mainThread())
    : UseCase<Pair<Address, Boolean>>(subscriberThread, observerThread) {

    companion object {
        const val PARAM_LOCATION = "param:location"
    }

    override fun observable(data: Map<String, Any>?): Observable<Pair<Address, Boolean>> {
        val location = data?.get(PARAM_LOCATION)
        location?.let {
            val locationStr = it as String
            return locationRepository.getAddress(locationStr).toObservable()
                    .flatMap { locationRepository.addGeofence(it) }
        } ?: return Observable.error({ IllegalArgumentException("location must be provided.") })
    }
}