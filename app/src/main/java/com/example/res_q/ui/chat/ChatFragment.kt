package com.example.res_q.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.res_q.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cardview1.setOnClickListener {
            findNavController().navigate(ChatFragmentDirections.actionNavigationChatToInChatFragment())
        }
        binding.cardview2.setOnClickListener {
            findNavController().navigate(ChatFragmentDirections.actionNavigationChatToInChatFragment())
        }
        return root
    }
}
