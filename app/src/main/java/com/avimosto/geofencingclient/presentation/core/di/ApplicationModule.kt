package com.avimosto.geofencingclient.presentation.core.di

import android.content.Context
import com.avimosto.geofencingclient.AndroidApplication
import com.avimosto.geofencingclient.presentation.core.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator()
    }
}