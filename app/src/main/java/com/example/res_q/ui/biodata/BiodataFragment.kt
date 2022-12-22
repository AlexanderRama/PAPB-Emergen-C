package com.example.res_q.ui.biodata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.res_q.databinding.FragmentBiodataBinding
import com.example.res_q.utilities.BioAdapter
import com.example.res_q.utilities.ContactModel
import com.google.firebase.firestore.*
import java.util.ArrayList

class BiodataFragment : Fragment() {

    private var _binding: FragmentBiodataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var contactAdapter: BioAdapter? = null
    private lateinit var btnSubmit: Button
    private lateinit var contactList :  ArrayList<ContactModel>
    var database = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val biodataViewModel =
            ViewModelProvider(this).get(BiodataViewModel::class.java)

        _binding = FragmentBiodataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonadd.setOnClickListener {
            findNavController().navigate(BiodataFragmentDirections.actionNavigationBiodataToAddBio())
        }
//        val textView: TextView = binding.textBiodata
        biodataViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }
        contactList = arrayListOf()
        recyclerView = binding.rvBio
        binding.rvBio.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        contactAdapter = BioAdapter(contactList, this)
        recyclerView.adapter = contactAdapter
        EventChangeListerner()

        return root
    }

    private fun EventChangeListerner(){
        database.collection("contact").orderBy("nama", Query.Direction.ASCENDING).addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        contactList.add(dc.document.toObject(ContactModel::class.java))
                    }
                }
                contactAdapter?.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
