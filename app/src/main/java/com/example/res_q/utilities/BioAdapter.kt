package com.example.res_q.utilities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.res_q.R
import com.example.res_q.ui.biodata.BiodataFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class BioAdapter(private val contactList: ArrayList<ContactModel>, private val context: Context) : RecyclerView.Adapter<BioAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact : ContactModel = contactList[position]
        holder.tvName.text = contact.nama
        holder.btn2.setOnClickListener { v: View? ->
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            callIntent.data = Uri.parse("tel:" + contact.telf)
            context.startActivity(callIntent)
        }
//        holder.contactLayout.setOnClickListener { v: View? ->
//            val dataName = holder.tvName.text.toString()
//            val dataNumber = holder.tvNumber.text.toString()
//            val bundle = Bundle()
//            bundle.putString("cname", dataName)
//            bundle.putString("cnumber", dataNumber)
//        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var database = FirebaseFirestore.getInstance()
        private var contactLayout: CardView
        var tvName: TextView
        private var btn: AppCompatImageButton
        var btn2: AppCompatImageButton
        override fun onClick(v: View) {
            clickListener!!.onItemClick(adapterPosition, itemView)
        }

        init {
            contactLayout =
                itemView.findViewById(R.id.contact_layout)
            tvName = itemView.findViewById(R.id.nama)
            btn = itemView.findViewById(R.id.telf)
            btn2 = itemView.findViewById(R.id.number)
            btn.setOnClickListener { popupMenus(it) }
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun popupMenus(v: View) {
            val position = contactList[adapterPosition]
            val popupMenus = PopupMenu(context, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v = LayoutInflater.from(context).inflate(R.layout.add_item, null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val number = v.findViewById<EditText>(R.id.userNo)
                        AlertDialog.Builder(context)
                            .setView(v)
                            .setPositiveButton("Ok") { dialog, _ ->
                                position.nama = name.text.toString()
                                position.telf = number.text.toString()
                                notifyDataSetChanged()

                                val mapUpdate = mapOf(
                                    "nama" to name.text.toString(),
                                    "telf" to number.text.toString()
                                )
                                val query = database.collection("contact").whereEqualTo("telf", position.telf).get()
                                query.addOnSuccessListener {
                                    for (document in it)
                                        database.collection("contact").document(document.id).set(mapUpdate, SetOptions.merge())
                                }
                                Toast.makeText(
                                    context,
                                    "User Information is Edited",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete -> {
                        AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes") { dialog, _ ->
                                contactList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(
                                    context,
                                    "Deleted this Information",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }
    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }

    companion object {
        private var clickListener: ClickListener? = null
    }
}