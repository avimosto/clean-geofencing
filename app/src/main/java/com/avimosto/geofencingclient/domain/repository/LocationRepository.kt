package com.avimosto.geofencingclient.domain.repository

import android.location.Address
import com.avimosto.geofencingclient.data.entity.TrackedLocation
import io.reactivex.Observable
import io.reactivex.Single

interface LocationRepository {
    fun trackedLocations(): Single<List<TrackedLocation>>
    fun addTrackedLocation(location: TrackedLocation)
    fun getAddress(location: String): Single<Address>
    fun addGeofence(address: Address): Observable<Pair<Address, Boolean>>
}