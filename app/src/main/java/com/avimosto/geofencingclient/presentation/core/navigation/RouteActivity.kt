package com.avimosto.geofencingclient.presentation.core.navigation

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.avimosto.geofencingclient.AndroidApplication
import com.avimosto.geofencingclient.R
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject


class RouteActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AndroidApplication).component.inject(this)

        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { granted ->
                    if (granted) {
                        navigator.navigateToSetLocation(this)
                    } else {
                        onInsufficientPermissions()
                    }
                }
    }

    private fun onInsufficientPermissions() {
        Toast.makeText(this, R.string.error_insufficient_permissions, Toast.LENGTH_LONG).show()
        finish()
    }
}
