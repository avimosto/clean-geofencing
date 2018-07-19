package com.avimosto.geofencingclient.presentation.core.di

import com.avimosto.geofencingclient.AndroidApplication
import com.avimosto.geofencingclient.data.di.DataModule
import com.avimosto.geofencingclient.data.geofence.GeofenceTransitionsJobIntentService
import com.avimosto.geofencingclient.presentation.core.navigation.RouteActivity
import com.avimosto.geofencingclient.presentation.features.locationlist.LocationListFragment
import com.avimosto.geofencingclient.presentation.features.locationlist.di.LocationListModule
import com.avimosto.geofencingclient.presentation.features.setlocation.SetLocationFragment
import com.avimosto.geofencingclient.presentation.features.setlocation.di.SetLocationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class, LocationListModule::class, SetLocationModule::class))
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)
    fun inject(locationsFragment: LocationListFragment)
    fun inject(setLocationFragment: SetLocationFragment)
    fun inject(geofenceService: GeofenceTransitionsJobIntentService)
}