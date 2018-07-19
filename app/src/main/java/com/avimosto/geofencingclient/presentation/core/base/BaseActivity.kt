package com.avimosto.geofencingclient.presentation.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avimosto.geofencingclient.R

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    abstract fun fragment(): BaseFragment

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment()).commit()
}