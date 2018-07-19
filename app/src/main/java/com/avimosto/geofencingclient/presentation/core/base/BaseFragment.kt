package com.avimosto.geofencingclient.presentation.core.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avimosto.geofencingclient.AndroidApplication
import com.avimosto.geofencingclient.presentation.core.di.ApplicationComponent
import kotlinx.android.synthetic.main.activity_layout.*

abstract class BaseFragment : Fragment() {
    val component: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as AndroidApplication).component
    }

    abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    fun showMessage(@StringRes message: Int) {
        Snackbar.make((activity as BaseActivity).fragmentContainer, message, Snackbar.LENGTH_LONG).show()
    }

    fun context(): Context {
        return activity!!
    }

    open fun onBackPressed() {}
}