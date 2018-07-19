package com.avimosto.geofencingclient.presentation.features.setlocation

import android.content.Context
import android.content.Intent
import com.avimosto.geofencingclient.presentation.core.base.BaseActivity
import com.avimosto.geofencingclient.presentation.core.base.BaseFragment

class SetLocationActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, SetLocationActivity::class.java)
    }

    override fun fragment(): BaseFragment {
        return SetLocationFragment.newInstance()
    }
}