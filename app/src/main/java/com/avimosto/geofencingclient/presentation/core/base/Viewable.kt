package com.avimosto.geofencingclient.presentation.core.base

import android.content.Context
import android.support.annotation.StringRes

interface Viewable {
    fun showMessage(@StringRes message: Int)
    fun context(): Context
}