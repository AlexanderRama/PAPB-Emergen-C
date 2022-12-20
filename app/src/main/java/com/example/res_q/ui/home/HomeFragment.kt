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

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btn = FragmentHomeBinding.inflate(layoutInflater)
        btn.btnSos123.setOnClickListener{
            val inten = Intent(this@HomeFragment.requireContext(), SosActivity::class.java)
            startActivity(inten)
        }
//        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }
        return btn.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
