package com.avimosto.geofencingclient.presentation.core.mapper

interface Mapper<out V, in E> {
    fun map(type: E): V
}