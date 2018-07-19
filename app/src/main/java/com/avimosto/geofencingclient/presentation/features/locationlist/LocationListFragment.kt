package com.avimosto.geofencingclient.presentation.features.locationlist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.avimosto.geofencingclient.R
import com.avimosto.geofencingclient.presentation.core.base.BaseFragment
import javax.inject.Inject

class LocationListFragment : BaseFragment(), LocationListContract.View {

    @BindView(R.id.recycler_view_locations)
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var presenter: LocationListContract.Presenter

    @Inject
    lateinit var adapter: LocationListAdapter

    companion object {
        fun newInstance(): LocationListFragment {
            return LocationListFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_location_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        presenter.attachView(this)
        presenter.fetchLocations()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun render(locations: List<LocationView>) {
        adapter.locations = locations
        adapter.notifyDataSetChanged()
    }

    override fun showErrorLoadingDataMessage() {
        showMessage(R.string.error_loading_data)
    }
}