package com.example.res_q.ui.pengaturan

import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.parseIntent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.res_q.LoginActivity
import com.example.res_q.databinding.FragmentPengaturanBinding
import com.example.res_q.ui.chat.PengaturanViewModel
import com.example.res_q.utilities.Constants
import com.example.res_q.utilities.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream


class PengaturanFragment : Fragment() {

    private var _binding: FragmentPengaturanBinding? = null
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var encodedImage: String

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
        btn.frameLayout.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }
        val database = FirebaseFirestore.getInstance()

//        val img = hashMapOf(
//            Constants.KEY_IMAGE to encodedImage
//        )
//        database.collection(Constants.KEY_COLLECTION_IMAGE).add(img)
        return btn.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frameLayout.txtNama.text = preferenceManager.getString(Constants.KEY_NAME)
        binding.frameLayout.txtEmail.text = preferenceManager.getString((Constants.KEY_EMAIL))
    }

    private fun encodeImage(bitmap: Bitmap): String {
        val previewWidth:Int = 150
        val previewHeight:Int = bitmap.height * previewWidth / bitmap.width

        val previewBitmap: Bitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false)
        val byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()

        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)

        val bytes: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    private val pickImage: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            if (result.data != null) {
                val imageUri: Uri? = result.data!!.data
                try {
                    val inputStream: InputStream = context?.contentResolver?.openInputStream(imageUri!!)!!
                    val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.frameLayout.profileImage.setImageBitmap(bitmap)
                    encodedImage = encodeImage(bitmap)
                } catch ( e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
