package com.avimosto.geofencingclient.data.geofence

import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import com.avimosto.geofencingclient.AndroidApplication
import com.avimosto.geofencingclient.R
import com.avimosto.geofencingclient.data.entity.TrackedLocation
import com.avimosto.geofencingclient.domain.repository.LocationRepository
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import timber.log.Timber
import javax.inject.Inject


/**
 * Listener for geofence transition changes.
 *
 * Receives geofence transition events from Location Services in the form of an Intent containing
 * the transition type and geofence id(s) that triggered the transition. Creates a notification
 * as the output.
 */
class GeofenceTransitionsJobIntentService : JobIntentService() {

    @Inject
    lateinit var repository: LocationRepository

    override fun onCreate() {
        super.onCreate()
        (application as AndroidApplication).component.inject(this)
    }

    /**
     * Handles incoming intents.
     * @param intent sent by Location Services. This Intent is provided to Location
     * Services (inside a PendingIntent) when addGeofences() is called.
     */
    override fun onHandleWork(intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        Timber.tag(TAG).d("onHandleWork, event=%s", geofencingEvent)

        if (geofencingEvent.hasError()) {
            val errorMessage = GeofenceErrors.getErrorString(this, geofencingEvent.errorCode)
            Timber.tag(TAG).e(errorMessage)
            return
        }

        // Get the transition type.
        val geofenceTransition = geofencingEvent.geofenceTransition

        when (geofenceTransition) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> {
                enterTimestamp = System.currentTimeMillis()
            }

            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                if (enterTimestamp > 0) {
                    val exitTimestamp = System.currentTimeMillis()
                    val location = geofencingEvent.triggeringLocation
                    repository.addTrackedLocation(TrackedLocation(0, location.latitude, location.longitude, enterTimestamp, exitTimestamp))
                }
            }

            else -> Timber.tag(TAG).e(getString(R.string.geofence_transition_invalid_type, geofenceTransition))
        }
    }

    companion object {

        private val JOB_ID = 573
        private val TAG = "GeofenceTransitionsIS"
        private val CHANNEL_ID = "channel_01"

        var enterTimestamp = 0L

        /**
         * Convenience method for enqueuing work in to this service.
         */
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, GeofenceTransitionsJobIntentService::class.java, JOB_ID, intent)
        }
    }
}