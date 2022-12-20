package com.example.res_q

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class SosActivity : AppCompatActivity() {
    var items = arrayOf("Material", "Design", "Components", "Android", "5.0 Lollipop")
    var autoCompleteTxt: AutoCompleteTextView? = null
    private lateinit var button: AppCompatImageButton
    private lateinit var button1: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sos)
        val adapterItems = ArrayAdapter(this, R.layout.list_items, items)
        autoCompleteTxt?.setAdapter(adapterItems)
        button = findViewById(R.id.buttonvn)
        button1 = findViewById(R.id.button1)
        button.setOnClickListener{
           button.isSelected = !button.isSelected
        }
        button1.setOnClickListener {
            val inten = Intent(this@SosActivity, ambulansActivity::class.java)
            startActivity(inten)
        }
        autoCompleteTxt?.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = parent.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, "Item: $item", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}