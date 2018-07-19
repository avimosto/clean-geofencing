package com.avimosto.geofencingclient.data.geofence

import android.content.Context
import com.avimosto.geofencingclient.R
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.GeofenceStatusCodes


/**
 * Geofence error codes mapped to error messages.
 */
internal object GeofenceErrors {

    fun getErrorString(context: Context, e: Exception): String {
        return if (e is ApiException) {
            getErrorString(context, e.statusCode)
        } else {
            context.resources.getString(R.string.unknown_geofence_error)
        }
    }

    fun getErrorString(context: Context, errorCode: Int): String {
        val mResources = context.getResources()
        return when (errorCode) {
            GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> mResources.getString(R.string.geofence_not_available)
            GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> mResources.getString(R.string.geofence_too_many_geofences)
            GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> mResources.getString(R.string.geofence_too_many_pending_intents)
            else -> mResources.getString(R.string.unknown_geofence_error)
        }
    }
}