package com.avimosto.geofencingclient.presentation.core.base

interface Presentable<in V : Viewable> {
    fun attachView(view: V)

    fun detachView()

    fun destroy()
}