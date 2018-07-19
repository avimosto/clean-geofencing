package com.avimosto.geofencingclient

import android.app.Application
import com.avimosto.geofencingclient.data.di.DataModule
import com.avimosto.geofencingclient.presentation.core.di.ApplicationComponent
import com.avimosto.geofencingclient.presentation.core.di.ApplicationModule
import com.avimosto.geofencingclient.presentation.core.di.DaggerApplicationComponent
import timber.log.Timber

class AndroidApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dataModule((DataModule(this)))
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}