package com.avimosto.geofencingclient.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.avimosto.geofencingclient.data.entity.TrackedLocation

@Database(entities = arrayOf(TrackedLocation::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackedLocationDao(): TrackedLocationDao
}