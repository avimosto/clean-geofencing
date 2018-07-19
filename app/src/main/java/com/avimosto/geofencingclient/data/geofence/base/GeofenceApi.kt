package com.avimosto.geofencingclient.data.geofence.base

import android.location.Address
import io.reactivex.Observable

interface GeofenceApi {
    fun addGeoFence(address: Address): Observable<Pair<Address, Boolean>>
    fun removeGeofences()
}