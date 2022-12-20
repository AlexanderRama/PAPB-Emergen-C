package com.example.res_q

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.res_q.databinding.AmbulansActivity2Binding

class AmbulansActivity2 : AppCompatActivity() {
    private lateinit var binding: AmbulansActivity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.ambulans_activity2)

        Handler().postDelayed({
            val intent = Intent(this, AmbulansActivity3::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}