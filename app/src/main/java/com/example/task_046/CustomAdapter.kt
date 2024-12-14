package com.example.task_046

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val emailes: MutableList<EmailViewModal>):
    RecyclerView.Adapter<CustomAdapter.EmailViewHolder>(){

    class EmailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageViewIV: ImageView = itemView.findViewById(R.id.imageViewIV)
        val emailTV: TextView = itemView.findViewById(R.id.emailTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return EmailViewHolder(itemView)
    }

    override fun getItemCount() = emailes.size

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailes[position]
        holder.imageViewIV.setImageResource(email.icon)
        holder.emailTV.text = email.email
    }
}