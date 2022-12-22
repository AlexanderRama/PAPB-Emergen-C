package com.example.res_q.utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.res_q.R
import com.example.res_q.ui.biodata.BiodataFragment

class BioAdapter(private val contactList: ArrayList<ContactModel>, private val context: BiodataFragment) : RecyclerView.Adapter<BioAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact : ContactModel = contactList[position]
        holder.tvName.text = contact.nama
        holder.tvName.setOnClickListener { v: View? ->
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

    private fun startActivity(callIntent: Intent) {

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var contactLayout: CardView
        var tvName: TextView
        override fun onClick(v: View) { clickListener!!.onItemClick(adapterPosition, itemView)
        }

        init {
            contactLayout =
                itemView.findViewById(R.id.contact_layout)
            tvName =
                itemView.findViewById(R.id.nama)
        }
    }


    fun setOnItemClickListener(clickListener: ClickListener?) {
        Companion.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }

    companion object {
        private var clickListener: ClickListener? = null
    }
}