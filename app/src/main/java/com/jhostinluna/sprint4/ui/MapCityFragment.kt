package com.jhostinluna.sprint4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentMapCityBinding


const val USER_ID_PARAMS = "userId"
/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class MapCityFragment : BaseFragment<FragmentMapCityBinding>(), OnMapReadyCallback {
    private var userId: String? = null
    var mGoogleMap: GoogleMap? = null
    var map: GoogleMap? = null
    override fun inflateBinding() {
        binding = FragmentMapCityBinding.inflate(layoutInflater)
    }


    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        val mapFragment = childFragmentManager.findFragmentById(binding?.fcvMap?.id!!) as com.google.android.gms.maps.SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun observeViewModel() {
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
    }

    override fun configureToolbarAndConfigScreenSections() {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )

    }

}