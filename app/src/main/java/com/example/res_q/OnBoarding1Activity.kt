package com.example.res_q

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView

class OnBoarding1Activity : AppCompatActivity() {
    private lateinit var btn: TextView
    private lateinit var btn1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_on_boarding1)

        btn = findViewById(R.id.back)
        btn1 = findViewById(R.id.btn2)


        btn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btn1.setOnClickListener {
            val intent = Intent(this,OnBoarding2Activity::class.java)
            startActivity(intent)
        }
    }
}
