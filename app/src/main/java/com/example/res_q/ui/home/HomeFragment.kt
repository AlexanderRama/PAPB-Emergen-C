package com.example.res_q.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.res_q.LoginActivity
import com.example.res_q.SosActivity
import com.example.res_q.databinding.FragmentHomeBinding
import com.example.res_q.databinding.FragmentPengaturanBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btn = FragmentHomeBinding.inflate(layoutInflater)
        btn.btnSos123.setOnClickListener{
            val inten = Intent(this@HomeFragment.requireContext(), SosActivity::class.java)
            startActivity(inten)
        }
        return btn.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
