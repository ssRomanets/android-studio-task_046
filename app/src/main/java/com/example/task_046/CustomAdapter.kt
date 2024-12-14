package com.example.task_046

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val contacts: MutableList<ContactViewModal>):
    RecyclerView.Adapter<CustomAdapter.ContactViewHolder>(){

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageViewIV: ImageView = itemView.findViewById(R.id.imageViewIV)
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val telephoneTV: TextView = itemView.findViewById(R.id.telephoneTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.imageViewIV.setImageResource(R.drawable.phone_android_ic)
        holder.nameTV.text = contact.name
        holder.telephoneTV.text = contact.telephone
    }
}