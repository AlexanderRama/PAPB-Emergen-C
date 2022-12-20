package com.example.res_q

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.res_q.databinding.AmbulansActivityBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ambulansActivity : AppCompatActivity() {
        lateinit var mapFragment : SupportMapFragment
        lateinit var googleMap: GoogleMap
        lateinit var button: Button
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.ambulans_activity)

            button = findViewById(R.id.buttonNext)
            mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(OnMapReadyCallback {
                this.googleMap = it
                googleMap.isMyLocationEnabled = true
                val location1 = LatLng(13.03,77.60)
                googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))

                val location2 = LatLng(9.89,78.11)
                googleMap.addMarker(MarkerOptions().position(location2).title("Madurai"))


                val location3 = LatLng(13.00,77.00)
                googleMap.addMarker(MarkerOptions().position(location3).title("Bangalore"))

            })
            button.setOnClickListener {
                val inten = Intent(this@ambulansActivity, AmbulansActivity2::class.java)
                startActivity(inten)
            }
        }
    }
//    private lateinit var binding: AmbulansActivityBinding
//    lateinit var mapFragment : SupportMapFragment
//    lateinit var googleMap: GoogleMap
//    @SuppressLint("SetTextI18n")
//    fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = AmbulansActivityBinding.inflate(layoutInflater, container, false)
//
//        binding.btn1.setOnClickListener{
//            binding.btn1.isSelected = !binding.btn1.isSelected
//            if(binding.btn1.isSelected) {
//                binding.btn1.setTextColor(resources.getColor(R.color.white))
//                binding.btn1.setTextColor(resources.getColor(R.color.white))
//            }
//            else {
//                binding.btn1.setTextColor(resources.getColor(R.color.black))
//                binding.btn1.setTextColor(resources.getColor(R.color.black))
//            }
//        }
//        binding.btn2.setOnClickListener{
//            binding.btn2.isSelected = !binding.btn2.isSelected
//            if(binding.btn2.isSelected) {
//                binding.btn2.setTextColor(resources.getColor(R.color.white))
//                binding.btn2.setTextColor(resources.getColor(R.color.white))
//            }
//            else {
//                binding.btn2.setTextColor(resources.getColor(R.color.black))
//                binding.btn2.setTextColor(resources.getColor(R.color.black))
//            }
//            binding.textHarga.setText(buildString { append("Rp 150.000")
//    })
//        }
//        binding.btn3.setOnClickListener{
//            binding.btn3.isSelected = !binding.btn3.isSelected
//            if(binding.btn3.isSelected) {
//                binding.btn3.setTextColor(resources.getColor(R.color.white))
//                binding.btn3.setTextColor(resources.getColor(R.color.white))
//            }
//            else {
//                binding.btn3.setTextColor(resources.getColor(R.color.black))
//                binding.btn3.setTextColor(resources.getColor(R.color.black))
//            }
//            binding.textHarga.setText(buildString { append("Rp 250.000")
//            })
//        }
//        binding.buttonNext.setOnClickListener{
//            val intent = Intent(this, AmbulansActivity2::class.java)
//            startActivity(intent)
//        }
//        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(OnMapReadyCallback {
//            googleMap = it
//            googleMap.isMyLocationEnabled = true
//            val location1 = LatLng(13.03,77.60)
//            googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))
//
//            val location2 = LatLng(9.89,78.11)
//            googleMap.addMarker(MarkerOptions().position(location2).title("Madurai"))
//
//
//            val location3 = LatLng(13.00,77.00)
//            googleMap.addMarker(MarkerOptions().position(location3).title("Bangalore"))
//
//        })
//        return binding.root
//    }
//}