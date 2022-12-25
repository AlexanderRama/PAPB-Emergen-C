package com.example.res_q

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.res_q.databinding.FragmentTelfBinding
import com.example.res_q.databinding.FragmentTipsBinding
import com.example.res_q.databinding.RegisterActivityBinding
import com.example.res_q.utilities.PreferenceManager
import com.google.firebase.auth.FirebaseAuth

class TelfFragment : AppCompatActivity() {

    private lateinit var binding: FragmentTelfBinding
    private lateinit var btn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = FragmentTelfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.telf1.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + 113)
            startActivity(intent)
        }
        binding.telf2.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + 110)
            startActivity(intent)
        }
        binding.telf3.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + 115)
            startActivity(intent)
        }
        binding.telf4.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + 129)
            startActivity(intent)
        }
        binding.telf5.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + 123)
            startActivity(intent)
        }
    }
}
