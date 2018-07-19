package com.avimosto.geofencingclient.data.geofence

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.Address
import com.avimosto.geofencingclient.data.geofence.base.GeofenceApi
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class GeoFencingController
@Inject constructor(private val context: Context,
                    private val geofencingClient: GeofencingClient)
    : GeofenceApi {

    private val geofences = arrayListOf<Geofence>()

    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    @SuppressLint("MissingPermission")
    override fun addGeoFence(address: Address): Observable<Pair<Address, Boolean>> {
        val result = Pair(address, true)

        val requestId = UUID.randomUUID().toString()
        val geofence = Geofence.Builder()
                .setRequestId(requestId)
                .setCircularRegion(address.latitude, address.longitude, GEOFENCE_RADIUS_IN_METERS)
                .setExpirationDuration(GEOFENCE_EXPIRATION_MILLISECONDS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()

        geofences.add(geofence)

        return Observable.create { emitter ->
            geofencingClient.addGeofences(geofencingRequest(), geofencePendingIntent)?.run {
                addOnSuccessListener {
                    emitter.onNext(result)
                    emitter.onComplete()
                }
                addOnFailureListener {
                    emitter.onError(it)
                }
            }
        }
    }

    override fun removeGeofences() {
        geofencingClient.removeGeofences(geofencePendingIntent)
    }

    private fun geofencingRequest(): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geofences)
        }.build()
    }

    companion object {
        val GEOFENCE_RADIUS_IN_METERS = 1000F
        val GEOFENCE_EXPIRATION_MILLISECONDS = 12 * 60 * 60 * 1000L
    }
}
