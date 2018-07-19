package com.avimosto.geofencingclient.presentation.features.locationlist.di

import android.content.Context
import com.avimosto.geofencingclient.domain.repository.LocationRepository
import com.avimosto.geofencingclient.domain.usecase.GetTrackedLocationsUseCase
import com.avimosto.geofencingclient.presentation.features.locationlist.LocationListAdapter
import com.avimosto.geofencingclient.presentation.features.locationlist.LocationListContract
import com.avimosto.geofencingclient.presentation.features.locationlist.LocationListPresenter
import com.avimosto.geofencingclient.presentation.features.locationlist.LocationMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationListModule {

    @Singleton
    @Provides
    fun providePresenter(useCase: GetTrackedLocationsUseCase, mapper: LocationMapper) : LocationListContract.Presenter {
        return LocationListPresenter(useCase, mapper)
    }

    @Singleton
    @Provides
    fun provideUseCase(repository: LocationRepository) : GetTrackedLocationsUseCase {
        return GetTrackedLocationsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideMapper(context: Context) : LocationMapper {
        return LocationMapper(context)
    }

    @Singleton
    @Provides
    fun provideAdapter() : LocationListAdapter {
        return LocationListAdapter()
    }
}