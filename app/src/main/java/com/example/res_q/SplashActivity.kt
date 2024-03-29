package com.example.res_q

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.res_q.databinding.SplashActivityBinding

class SplashActivity:AppCompatActivity() {
    private lateinit var binding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this, OnBoarding1Activity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
