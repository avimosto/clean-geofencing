package com.avimosto.geofencingclient.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.avimosto.geofencingclient.data.database.AppDatabase
import com.avimosto.geofencingclient.data.database.TrackedLocationDao
import com.avimosto.geofencingclient.data.geofence.GeoFencingController
import com.avimosto.geofencingclient.data.geofence.base.GeofenceApi
import com.avimosto.geofencingclient.data.repository.LocationDataRepository
import com.avimosto.geofencingclient.data.util.RxGeocoder
import com.avimosto.geofencingclient.domain.repository.LocationRepository
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context:Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()
    }

    @Provides
    @Singleton
    fun provideTrackedLocationDao(database: AppDatabase): TrackedLocationDao {
        return database.trackedLocationDao()
    }

    @Provides
    @Singleton
    fun provideLocationRepository(database: AppDatabase, geocoder: RxGeocoder, geofence: GeofenceApi): LocationRepository {
        return LocationDataRepository(database, geocoder, geofence)
    }

    @Provides
    @Singleton
    fun provideGeocoder(context: Context): RxGeocoder {
        return RxGeocoder(context)
    }

    @Provides
    @Singleton
    fun provideGeofenceApi(context: Context, client: GeofencingClient): GeofenceApi {
        return GeoFencingController(context, client)
    }

    @Provides
    @Singleton
    fun provideGeofencingClient(context: Context): GeofencingClient {
        return LocationServices.getGeofencingClient(context)
    }
}