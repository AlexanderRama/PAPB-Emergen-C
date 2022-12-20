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
import java.util.ArrayList

class BiodataFragment : Fragment() {

    private var _binding: FragmentBiodataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var contactAdapter: BioAdapter? = null
    private lateinit var btnSubmit: Button
    private val contactList: ArrayList<ContactModel> = ArrayList<ContactModel>()

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
        contactList.add(ContactModel("Jusuf Latifah",
            "+62878555504", "bussines", "bayerhilarious"))
        contactList.add(ContactModel("Burhanuddin Taufik", "+628785555041", "family", "integersjunior"))
        contactList.add(ContactModel("Latifah Bagus",
            "+628785555042", "study", "clearcarbon"))
        contactList.add(ContactModel("Agung Nurul",
            "+628785555043", "family", "opticalwwf"))
        contactList.add(ContactModel("Cahaya Krisna",
            "+628785555044", "bussiness", "gisremedy"))

        for (item in contactList) {
            recyclerView = binding.rvBio
            recyclerView.setHasFixedSize(true)
            contactAdapter = BioAdapter(this, contactList)

            binding.rvBio.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.setAdapter(contactAdapter)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
