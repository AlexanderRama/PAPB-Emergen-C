package com.example.res_q

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.res_q.databinding.AmbulansActivity2Binding
import com.example.res_q.databinding.AmbulansActivityBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AmbulansActivity2 : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: AmbulansActivity2Binding
    lateinit var button: Button
    private val permissionCode = 101
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = AmbulansActivity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocatinUser()

        Handler().postDelayed({
            "Selamat! Driver telah terhubung".also { binding.textView5.text = it }
            binding.buttonNext.setBackgroundColor(resources.getColor(com.google.firebase.appcheck.interop.R.color.common_google_signin_btn_text_light_pressed))
            binding.progressbar.visibility = View.GONE
            binding.progressbar2.visibility = View.VISIBLE
            binding.buttonNext.setOnClickListener{
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }, 5000)
    }
    private fun getCurrentLocatinUser() {
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),permissionCode)
            return
        }
        val getLocation = fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                location ->
            if(location != null) {
                currentLocation = location
                val mapFragment1 = supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
                mapFragment1.getMapAsync(this)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            permissionCode -> if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocatinUser()
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(-7.953703971810938, 112.61442082379727)
        val markerOptions= MarkerOptions().position(latLng).title("Current Location")

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
        googleMap.addMarker(markerOptions)
    }
}