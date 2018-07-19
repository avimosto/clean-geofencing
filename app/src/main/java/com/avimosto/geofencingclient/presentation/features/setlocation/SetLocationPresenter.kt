package com.avimosto.geofencingclient.presentation.features.setlocation

import android.location.Address
import android.text.TextUtils
import com.avimosto.geofencingclient.domain.usecase.SetTrackedLocationUseCase
import com.avimosto.geofencingclient.presentation.core.navigation.Navigator
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class SetLocationPresenter
@Inject constructor(val useCase: SetTrackedLocationUseCase,
                    val navigator: Navigator)
    : SetLocationContract.Presenter {

    private var view: SetLocationContract.View? = null

    override fun attachView(view: SetLocationContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {
        useCase.dispose()
    }

    override fun trackLocation(location: String) {
        if (TextUtils.isEmpty(location)) {
            view?.showEmptyLocationMessage()
        } else {
            val data = HashMap<String, String>()
            data[SetTrackedLocationUseCase.PARAM_LOCATION] = location
            useCase.execute(object: DisposableObserver<Pair<Address, Boolean>>() {
                override fun onComplete() {
                    Timber.d("onComplete")
                }

                override fun onNext(t: Pair<Address, Boolean>) {
                    Timber.d("onNext: result=$t")
                    view?.showAddress(t.first.toString())
                    if (t.second) {
                        view?.showTrackingSuccessMessage()
                    } else {
                        view?.showTrackingFailureMessage()
                    }
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                    view?.showTrackingFailureMessage()
                }
            }, data)
        }
    }

    override fun onHistoryButtonClick() {
        view?.context()?.let { navigator.navigateToLocationList(it) }
    }
}