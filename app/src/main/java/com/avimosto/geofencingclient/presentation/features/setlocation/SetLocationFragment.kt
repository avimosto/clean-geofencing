package com.avimosto.geofencingclient.presentation.features.setlocation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.avimosto.geofencingclient.R
import com.avimosto.geofencingclient.presentation.core.base.BaseFragment
import com.avimosto.geofencingclient.presentation.core.ext.hideKeyboard
import javax.inject.Inject

class SetLocationFragment: BaseFragment(), SetLocationContract.View {

    @BindView(R.id.et_location)
    lateinit var locationEditText: EditText

    @BindView(R.id.btn_track)
    lateinit var trackButton: Button

    @BindView(R.id.btn_tracking_history)
    lateinit var trackingHistoryButton: Button

    @BindView(R.id.tv_address)
    lateinit var addressTextView: TextView

    @Inject
    lateinit var presenter: SetLocationPresenter

    override fun layoutId(): Int {
        return R.layout.fragment_set_location
    }

    companion object {
        fun newInstance(): SetLocationFragment {
            return SetLocationFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)

        presenter.attachView(this)

        trackButton.setOnClickListener {
            presenter.trackLocation(locationEditText.text.toString())
            hideKeyboard(trackButton)
        }
        trackingHistoryButton.setOnClickListener { presenter.onHistoryButtonClick() }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun showEmptyLocationMessage() {
        showMessage(R.string.error_empty_location)
    }

    override fun showTrackingSuccessMessage() {
        showMessage(R.string.message_tracking_successful)
    }

    override fun showTrackingFailureMessage() {
        showMessage(R.string.error_tracking_location)
    }

    override fun showAddress(address: String) {
        addressTextView.visibility = View.VISIBLE
        addressTextView.text = address
    }
}