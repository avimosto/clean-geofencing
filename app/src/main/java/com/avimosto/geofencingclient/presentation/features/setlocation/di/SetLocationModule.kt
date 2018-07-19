package com.avimosto.geofencingclient.presentation.features.setlocation.di

import com.avimosto.geofencingclient.domain.repository.LocationRepository
import com.avimosto.geofencingclient.domain.usecase.SetTrackedLocationUseCase
import com.avimosto.geofencingclient.presentation.core.navigation.Navigator
import com.avimosto.geofencingclient.presentation.features.setlocation.SetLocationPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SetLocationModule {

    @Singleton
    @Provides
    fun providePresenter(useCase: SetTrackedLocationUseCase, navigator: Navigator) : SetLocationPresenter {
        return SetLocationPresenter(useCase, navigator)
    }

    @Singleton
    @Provides
    fun provideUseCase(repository: LocationRepository) : SetTrackedLocationUseCase {
        return SetTrackedLocationUseCase(repository)
    }
}