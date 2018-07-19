package com.avimosto.geofencingclient.data.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class RxGeocoder
@Inject constructor(private val context: Context) {

    internal fun getGeocoder(locale: Locale?): Geocoder {
        return if (locale != null) {
            Geocoder(context, locale)
        } else {
            Geocoder(context)
        }
    }

    fun fromLocation(location: Location): Maybe<Any>? {
        return fromLocation(null, location, 1).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocation(locale: Locale, location: Location): Maybe<Any>? {
        return fromLocation(locale, location, 1).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocation(location: Location, maxResults: Int): Single<List<Address>> {
        return fromLocation(null, location, maxResults)
    }

    fun fromLocation(locale: Locale?, location: Location, maxResults: Int): Single<List<Address>> {
        return Single.fromCallable({ getGeocoder(locale).getFromLocation(location.getLatitude(), location.getLongitude(), maxResults) })
    }


    fun fromLocation(latitude: Double, longitude: Double): Maybe<Any>? {
        return fromLocation(null, latitude, longitude, 1).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocation(locale: Locale, latitude: Double, longitude: Double): Maybe<Any>? {
        return fromLocation(locale, latitude, longitude, 1).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocation(latitude: Double, longitude: Double, maxResults: Int): Single<List<Address>> {
        return fromLocation(null, latitude, longitude, maxResults)
    }

    fun fromLocation(locale: Locale?, latitude: Double, longitude: Double, maxResults: Int): Single<List<Address>> {
        return Single.fromCallable({ getGeocoder(locale).getFromLocation(latitude, longitude, maxResults) })
    }

    fun fromLocationName(locationName: String, lowerLeftLatitude: Double, lowerLeftLongitude: Double, upperRightLatitude: Double, upperRightLongitude: Double): Maybe<Any>? {
        return fromLocationName(null, locationName, 1, lowerLeftLatitude, lowerLeftLongitude, upperRightLatitude, upperRightLongitude).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocationName(locale: Locale, locationName: String, lowerLeftLatitude: Double, lowerLeftLongitude: Double, upperRightLatitude: Double, upperRightLongitude: Double): Maybe<Any>? {
        return fromLocationName(locale, locationName, 1, lowerLeftLatitude, lowerLeftLongitude, upperRightLatitude, upperRightLongitude).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocationName(locationName: String, maxResults: Int, lowerLeftLatitude: Double, lowerLeftLongitude: Double, upperRightLatitude: Double, upperRightLongitude: Double): Single<List<Address>> {
        return fromLocationName(null, locationName, maxResults, lowerLeftLatitude, lowerLeftLongitude, upperRightLatitude, upperRightLongitude)
    }

    fun fromLocationName(locale: Locale?, locationName: String, maxResults: Int, lowerLeftLatitude: Double, lowerLeftLongitude: Double, upperRightLatitude: Double, upperRightLongitude: Double): Single<List<Address>> {
        return Single.fromCallable({ getGeocoder(locale).getFromLocationName(locationName, maxResults, lowerLeftLatitude, lowerLeftLongitude, upperRightLatitude, upperRightLongitude) })
    }

    fun fromLocationName(locationName: String): Maybe<Any>? {
        return fromLocationName(null, locationName, 1).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocationName(locale: Locale, locationName: String): Maybe<Any>? {
        return fromLocationName(locale, locationName, 1).flatMapMaybe(ADDRESS_MAYBE_FUNCTION)
    }

    fun fromLocationName(locationName: String, maxResults: Int): Single<List<Address>> {
        return fromLocationName(null, locationName, maxResults)
    }

    fun fromLocationName(locale: Locale?, locationName: String, maxResults: Int): Single<List<Address>> {
        return Single.fromCallable({ getGeocoder(locale).getFromLocationName(locationName, maxResults) })
    }

    companion object {
        private val ADDRESS_MAYBE_FUNCTION = { addresses: List<Address> -> if (addresses.isEmpty()) Maybe.empty<Any>() else Maybe.just(addresses.get(0)) }
    }

}