package com.avimosto.geofencingclient.presentation.features.locationlist

import com.avimosto.geofencingclient.presentation.core.base.Presentable
import com.avimosto.geofencingclient.presentation.core.base.Viewable

interface LocationListContract {
    interface View : Viewable {
        fun render(locations: List<LocationView>)
        fun showErrorLoadingDataMessage()
    }

    interface Presenter : Presentable<View> {
        fun fetchLocations()
    }
}