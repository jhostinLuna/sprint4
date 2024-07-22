package com.jhostinluna.sprint4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentMapCityBinding
import com.jhostinluna.sprint4.ui.navigation.ARG_PERSON_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MapCityFragment : BaseFragment<FragmentMapCityBinding>(), OnMapReadyCallback {

    private val viewModel: MapCityViewModel by viewModels()
    private var personIdParam: Int? = null
    private var mGoogleMap: GoogleMap? = null
    private var personLocation: LatLng? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personIdParam = it.getInt(ARG_PERSON_ID)
        }
    }
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
        lifecycleScope.launch {
            viewModel.personStateFlow.collect {person->
                person?.coordinate?.apply {
                    personLocation = LatLng(latitude.toDouble(), longitude.toDouble())
                }
                person?.city?.let { viewModel.loadAddressFromText(it) }
            }
        }

        lifecycleScope.launch {
            viewModel.addressStateFlow.collect{address->
                address?.let {
                    val location = LatLng(it.latitude, it.longitude)
                    mGoogleMap?.addMarker(
                        MarkerOptions()
                            .position(location)
                            .title("Ciudad Favorita")
                    )
                    mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
                }
            }
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        personIdParam?.let {
            viewModel.loadDetailPerson(it)
        }
    }

    override fun configureToolbarAndConfigScreenSections() {
        showToolbar(showEditIcon = false)

    }

    private fun addMarkerToMap() {
        personLocation?.let { location ->
            mGoogleMap?.addMarker(
                MarkerOptions()
                    .position(location)
                    .title("Persona")
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        addMarkerToMap()

    }

}