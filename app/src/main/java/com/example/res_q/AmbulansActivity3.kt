package com.example.res_q

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.res_q.databinding.AmbulansActivity3Binding

class AmbulansActivity3 : AppCompatActivity() {
    private lateinit var binding: AmbulansActivity3Binding

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = AmbulansActivity3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }
}