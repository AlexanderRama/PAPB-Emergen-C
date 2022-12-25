package com.example.res_q.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.res_q.databinding.FragmentChatBinding
import com.example.res_q.databinding.FragmentInchatBinding

class InChatFragment : Fragment() {

    private lateinit var binding: FragmentInchatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInchatBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
}