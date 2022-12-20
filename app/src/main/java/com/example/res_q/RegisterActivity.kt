package com.example.res_q

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.res_q.databinding.RegisterActivityBinding
import com.example.res_q.ui.pengaturan.PengaturanFragment
import com.example.res_q.utilities.Constants
import com.example.res_q.utilities.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: RegisterActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var checkbox: CheckBox
    private lateinit var textview1: EditText
    private lateinit var textview2: EditText
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(applicationContext)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView4.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.regActivityRegBtn.setOnClickListener {
            val nama = binding.regActivityNama.text.toString()
            val notelf = binding.regActivityNomor.text.toString()
            val email = binding.regActivityEmilet.text.toString()
            val pass = binding.regActivityPasswordet.text.toString()
            checkbox = findViewById(R.id.checkBox)
            textview1 = findViewById(R.id.reg_activity_emilet)
            textview2 = findViewById(R.id.reg_activity_nama)

            if (email.isNotEmpty() && pass.isNotEmpty() && nama.isNotEmpty() && notelf.isNotEmpty() && checkbox.isChecked) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            signUp()
                            Toast.makeText(this, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
                            val intent3 = Intent(this, LoginActivity::class.java)
                            startActivity(intent3)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun signUp() {
        val database = FirebaseFirestore.getInstance()

        val user = hashMapOf(
//            Constants.ID to ("1" + randomUUID().toString()),
            Constants.KEY_NAME to binding.regActivityNama.text.toString(),
            Constants.KEY_EMAIL to binding.regActivityEmilet.text.toString(),
            Constants.KEY_PASSWORD to binding.regActivityPasswordet.text.toString(),
            Constants.KEY_TELEPHONE to binding.regActivityNomor.text.toString(),
        )
        database.collection(Constants.KEY_COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener { documentReference ->
                preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true)
                preferenceManager.putString(Constants.KEY_USER_ID, documentReference.id)
                preferenceManager.putString(
                    Constants.KEY_TELEPHONE,
                    binding.regActivityNomor.text.toString()
                )
                preferenceManager.putString(
                    Constants.KEY_NAME,
                    binding.regActivityNama.text.toString()
                )
            }
    }
}