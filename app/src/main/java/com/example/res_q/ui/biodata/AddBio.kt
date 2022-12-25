package com.example.res_q.ui.biodata


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.res_q.databinding.FragmentAddkontakBinding
import com.example.res_q.databinding.FragmentBiodataBinding
import com.example.res_q.utilities.BioAdapter
import com.example.res_q.utilities.Constants
import com.example.res_q.utilities.ContactModel
import com.example.res_q.utilities.PreferenceManager
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.util.ArrayList

class AddBio : Fragment() {

    private lateinit var binding: FragmentAddkontakBinding
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var contactList : ArrayList<ContactModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        preferenceManager = PreferenceManager(requireContext())
        binding = FragmentAddkontakBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.buttonsave.setOnClickListener {
            addKontak()
            findNavController().navigate(AddBioDirections.actionAddBioToNavigationBiodata())
        }
        contactList = arrayListOf()
        return root
    }
    private fun addKontak() {
        val database = FirebaseFirestore.getInstance()
        val kontak = hashMapOf(
            Constants.KEY_NAMA to binding.addnama.text.toString(),
            Constants.KEY_TELF to binding.addno.text.toString(),
            Constants.KEY_UMUR to binding.addumur.text.toString(),
            Constants.KEY_INFO to binding.addinfo.text.toString(),
        )
        database.collection(Constants.KEY_COLLECTION_BIO).add(kontak)
    }
}