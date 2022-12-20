package com.example.res_q.ui.pengaturan

import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.parseIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.res_q.LoginActivity
import com.example.res_q.databinding.FragmentPengaturanBinding
import com.example.res_q.ui.chat.PengaturanViewModel
import com.example.res_q.utilities.Constants
import com.example.res_q.utilities.PreferenceManager
import com.google.firebase.auth.FirebaseAuth


class PengaturanFragment : Fragment() {

    private var _binding: FragmentPengaturanBinding? = null
    private lateinit var preferenceManager: PreferenceManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        preferenceManager = PreferenceManager(requireContext())
        val view = inflater.inflate(com.example.res_q.R.layout.fragment_pengaturan, container, false)
        val auth = FirebaseAuth.getInstance()
        val pengaturanViewModel = ViewModelProvider(this).get(PengaturanViewModel::class.java)
        _binding = FragmentPengaturanBinding.inflate(inflater, container, false)
        val btn = FragmentPengaturanBinding.inflate(layoutInflater)
        btn.signOutBtn.setOnClickListener{
            auth.signOut()
            val inten = Intent(this@PengaturanFragment.requireContext(), LoginActivity::class.java)
            startActivity(inten)
        }
//        val textView: TextView = binding.textBiodata
        pengaturanViewModel.text.observe(viewLifecycleOwner) {

        }
        return btn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frameLayout.txtNama.text = preferenceManager.getString(Constants.KEY_NAME)
        binding.frameLayout.txtEmail.text = preferenceManager.getString((Constants.KEY_EMAIL))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
