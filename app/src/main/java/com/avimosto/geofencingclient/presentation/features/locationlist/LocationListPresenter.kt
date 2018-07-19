package com.avimosto.geofencingclient.presentation.features.locationlist

import com.avimosto.geofencingclient.data.entity.TrackedLocation
import com.avimosto.geofencingclient.domain.usecase.GetTrackedLocationsUseCase
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class LocationListPresenter
    @Inject constructor(val useCase: GetTrackedLocationsUseCase,
                        val mapper: LocationMapper)
    : LocationListContract.Presenter {

    private var locationListView: LocationListContract.View? = null

    override fun attachView(view: LocationListContract.View) {
        locationListView = view
    }

    override fun fetchLocations() {
        useCase.execute(object: DisposableObserver<List<TrackedLocation>>() {

            override fun onComplete() {
                Timber.d("onComplete")
            }

            override fun onNext(t: List<TrackedLocation>) {
                Timber.d("onNext: %s", t.toString())
                if (!t.isEmpty()) {
                    locationListView?.render(t.map { mapper.map(it) })
                } else {
                    locationListView?.render(mockLocations().map { mapper.map(it) })
                }
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
                locationListView?.showErrorLoadingDataMessage()
            }
        })
    }

    override fun detachView() {
        locationListView = null
    }

    override fun destroy() {
        useCase.dispose()
    }

    private fun mockLocations(): List<TrackedLocation> {
        val l1 = TrackedLocation(0, 37.4183, -122.088321,System.currentTimeMillis() - 10000, System.currentTimeMillis())
        val l2 = TrackedLocation(1, 37.414363, -122.077415,System.currentTimeMillis() - 500000, System.currentTimeMillis())
        return arrayListOf(l1, l2)
    }
}