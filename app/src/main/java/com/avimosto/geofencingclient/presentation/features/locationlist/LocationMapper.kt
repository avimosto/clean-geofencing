package com.avimosto.geofencingclient.presentation.features.locationlist

import android.content.Context
import android.text.format.DateUtils
import com.avimosto.geofencingclient.data.entity.TrackedLocation
import com.avimosto.geofencingclient.presentation.core.mapper.Mapper
import javax.inject.Inject

class LocationMapper
    @Inject constructor(val context: Context)
    : Mapper<LocationView, TrackedLocation> {

    override fun map(type: TrackedLocation): LocationView {
        val formattedDate     = DateUtils.formatDateTime(context, type.enter, DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NUMERIC_DATE or DateUtils.FORMAT_SHOW_YEAR)
        val formattedDuration = DateUtils.formatElapsedTime((type.exit - type.enter) / 1000)
        val location = String.format("%f, %f", type.lat, type.lon)
        return LocationView(formattedDate, formattedDuration, location)
    }
}