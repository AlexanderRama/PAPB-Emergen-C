package com.example.res_q

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.res_q.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var btn: LinearLayout
    private lateinit var btn2: LinearLayout
    private lateinit var btn3: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        btn = findViewById(R.id.ambulans)
        btn2 = findViewById(R.id.telfpenting)
        btn3 = findViewById(R.id.tips)

        btn.setOnClickListener{
            val intent = Intent(this, ambulansActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener{
            val intent = Intent(this, TipsFragment::class.java)
            startActivity(intent)
        }

        btn3.setOnClickListener{
            val intent = Intent(this, TelfFragment::class.java)
            startActivity(intent)
        }

        val navController = findNavController(R.id.nav_host_fragment_activity_home2)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_chat, R.id.navigation_biodata, R.id.navigation_pengaturan
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        fun sosPage(view: View) {
            val i = Intent(this, SosActivity::class.java)
            startActivity(i)
        }
    }
}
