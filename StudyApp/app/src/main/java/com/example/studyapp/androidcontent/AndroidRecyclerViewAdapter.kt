package com.example.studyapp.androidcontent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R

class AndroidRecyclerViewAdapter: RecyclerView.Adapter<AndroidRecyclerViewAdapter.AndroidHolder>() {

    // HolderView
    class AndroidHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var titleText: TextView = itemView.findViewById(R.id.android_title_text)
        var descriptionText: TextView = itemView.findViewById(R.id.android_description_text)

        fun bind(androidContent: AndroidContent) {
            titleText.text = androidContent.title
            descriptionText.text = androidContent.description
        }
    }

    // Adapter
    var androidContents: ArrayList<AndroidContent> = ArrayList()

    fun setContent(androidContents: java.util.ArrayList<AndroidContent>) {
        this.androidContents = androidContents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_android, parent, false)
        return AndroidHolder(view)
    }

    override fun onBindViewHolder(holder: AndroidHolder, position: Int) {
        val androidContent: AndroidContent = androidContents.get(position)
        return holder.bind(androidContent)
    }

    override fun getItemCount(): Int {
        return androidContents.size
    }
}