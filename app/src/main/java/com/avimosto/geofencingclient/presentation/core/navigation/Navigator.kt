package com.avimosto.geofencingclient.presentation.core.navigation

import android.content.Context
import com.avimosto.geofencingclient.presentation.features.locationlist.LocationListActivity
import com.avimosto.geofencingclient.presentation.features.setlocation.SetLocationActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor() {

    fun navigateToSetLocation(context: Context) {
        context.startActivity(SetLocationActivity.callingIntent(context))
    }

    fun navigateToLocationList(context: Context) {
        context.startActivity(LocationListActivity.callingIntent(context))
    }
}