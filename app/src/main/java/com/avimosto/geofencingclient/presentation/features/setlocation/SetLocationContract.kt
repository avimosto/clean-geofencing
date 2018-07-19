package com.avimosto.geofencingclient.presentation.features.setlocation

import com.avimosto.geofencingclient.presentation.core.base.Presentable
import com.avimosto.geofencingclient.presentation.core.base.Viewable

interface SetLocationContract {

    interface View : Viewable {
        fun showAddress(address: String)
        fun showEmptyLocationMessage()
        fun showTrackingSuccessMessage()
        fun showTrackingFailureMessage()
    }

    interface Presenter : Presentable<View> {
        fun trackLocation(location: String)
        fun onHistoryButtonClick()
    }
}