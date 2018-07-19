package com.avimosto.geofencingclient.data.repository

import android.location.Address
import com.avimosto.geofencingclient.data.database.AppDatabase
import com.avimosto.geofencingclient.data.entity.TrackedLocation
import com.avimosto.geofencingclient.data.geofence.base.GeofenceApi
import com.avimosto.geofencingclient.data.util.RxGeocoder
import com.avimosto.geofencingclient.domain.repository.LocationRepository
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class LocationDataRepository
    @Inject constructor(private val database: AppDatabase,
                        private val geocoder: RxGeocoder,
                        private val geofence: GeofenceApi)

    : LocationRepository {

    override fun trackedLocations(): Single<List<TrackedLocation>> {
        Timber.d("trackedLocations")
        return database.trackedLocationDao().getTrackedLocations()
    }

    override fun addTrackedLocation(location: TrackedLocation) {
        Timber.d("addTrackedLocation: location=%s", location)
        database.trackedLocationDao().insert(location)
    }

    override fun getAddress(location: String): Single<Address> {
        Timber.d("getAddress: location=%s", location)
        return geocoder.fromLocationName(location, 1)
                .map { it[0] }
    }

    override fun addGeofence(address: Address): Observable<Pair<Address, Boolean>> {
        Timber.d("addGeofence: address=%s", address)
        return geofence.addGeoFence(address)
    }
}