package com.avimosto.geofencingclient.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class UseCase<T> constructor(private val subscriberThread: Scheduler,
                                      private val observerThread: Scheduler) {

    private val disposables = CompositeDisposable()

    abstract fun observable(data: Map<String, Any>? = null): Observable<T>

    fun execute(observer: DisposableObserver<T>, data: Map<String, Any>? = null) {
        val disposable = observable(data)
                .subscribeOn(subscriberThread)
                .observeOn(observerThread)
                .subscribeWith(observer)

        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}