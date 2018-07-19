package com.avimosto.geofencingclient.presentation.features.locationlist

import android.content.Context
import android.content.Intent
import com.avimosto.geofencingclient.presentation.core.base.BaseActivity
import com.avimosto.geofencingclient.presentation.core.base.BaseFragment

class LocationListActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, LocationListActivity::class.java)
    }

    override fun fragment(): BaseFragment = LocationListFragment.newInstance()
}