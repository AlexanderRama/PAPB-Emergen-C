package com.example.res_q

import android.os.Bundle
import android.view.Window
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.res_q.databinding.FragmentTelfBinding
import com.example.res_q.databinding.RegisterActivityBinding
import com.example.res_q.utilities.PreferenceManager
import com.google.firebase.auth.FirebaseAuth

class TelfFragment : AppCompatActivity() {

    private lateinit var binding: FragmentTelfBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var checkbox: CheckBox
    private lateinit var textview1: EditText
    private lateinit var textview2: EditText
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = FragmentTelfBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}