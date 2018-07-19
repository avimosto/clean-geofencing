package com.avimosto.geofencingclient.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "locations")
data class TrackedLocation(
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "latitude")
        val lat: Double,

        @ColumnInfo(name = "longitude")
        val lon: Double,

        @ColumnInfo(name = "enter")
        val enter: Long,

        @ColumnInfo(name = "exit")
        val exit: Long)