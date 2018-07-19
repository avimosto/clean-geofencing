package com.avimosto.geofencingclient.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.avimosto.geofencingclient.data.entity.TrackedLocation
import io.reactivex.Single

@Dao
interface TrackedLocationDao {

    @Query("SELECT * FROM locations")
    fun getTrackedLocations(): Single<List<TrackedLocation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: TrackedLocation)

    @Query("SELECT COUNT(*) FROM locations")
    fun count(): Int
}