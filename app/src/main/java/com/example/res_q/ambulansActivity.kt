package com.example.res_q

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.res_q.databinding.AmbulansActivityBinding
import com.example.res_q.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ambulansActivity : AppCompatActivity(), OnMapReadyCallback {
        lateinit var button: Button
        private lateinit var binding: AmbulansActivityBinding
        private val permissionCode = 101
        private lateinit var currentLocation: Location
        private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            supportActionBar?.hide()
            binding = AmbulansActivityBinding.inflate(layoutInflater)
            setContentView(binding.root)

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            getCurrentLocatinUser()
            binding.btn1.setOnClickListener{
                binding.btn1.isSelected = !binding.btn1.isSelected
                if(binding.btn1.isSelected) {
                    binding.btn1.setTextColor(resources.getColor(R.color.white))
                    binding.btn1.setTextColor(resources.getColor(R.color.white))
                }
                else {
                    binding.btn1.setTextColor(resources.getColor(R.color.black))
                    binding.btn1.setTextColor(resources.getColor(R.color.black))
                }
                binding.textHarga.text = buildString { append("Gratis")
                }
            }
            binding.btn2.setOnClickListener{
                binding.btn2.isSelected = !binding.btn2.isSelected
                if(binding.btn2.isSelected) {
                    binding.btn2.setTextColor(resources.getColor(R.color.white))
                    binding.btn2.setTextColor(resources.getColor(R.color.white))
                }
                else {
                    binding.btn2.setTextColor(resources.getColor(R.color.black))
                    binding.btn2.setTextColor(resources.getColor(R.color.black))
                }
                binding.textHarga.text = buildString { append("Rp 150.000")
                }
            }
            binding.btn3.setOnClickListener{
                binding.btn3.isSelected = !binding.btn3.isSelected
                if(binding.btn3.isSelected) {
                    binding.btn3.setTextColor(resources.getColor(R.color.white))
                    binding.btn3.setTextColor(resources.getColor(R.color.white))
                }
                else {
                    binding.btn3.setTextColor(resources.getColor(R.color.black))
                    binding.btn3.setTextColor(resources.getColor(R.color.black))
                }
                binding.textHarga.text = buildString { append("Rp 250.000")
                }
            }
            binding.buttonNext.setOnClickListener{
                val intent = Intent(this, AmbulansActivity2::class.java)
                startActivity(intent)
            }
        }

    private fun getCurrentLocatinUser() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),permissionCode)
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
