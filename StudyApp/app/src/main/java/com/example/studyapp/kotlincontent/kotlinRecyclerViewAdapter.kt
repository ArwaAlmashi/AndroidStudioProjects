package com.example.studyapp.kotlincontent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R


class KotlinRecyclerViewAdapter : RecyclerView.Adapter<KotlinRecyclerViewAdapter.KotlinHolder>() {

    //onItemClickListener
    private lateinit var kListener: OnKotlinItemClickListener

    interface OnKotlinItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnKotlinItemClick(onKotlinItemClickListener: OnKotlinItemClickListener) {
        kListener = onKotlinItemClickListener

    }


    // HolderView
    class KotlinHolder(itemView: View, kListener: OnKotlinItemClickListener) : RecyclerView.ViewHolder(itemView) {

        var titleText: TextView = itemView.findViewById(R.id.kotlin_title_text)
        var descriptionText: TextView = itemView.findViewById(R.id.kotlin_description_text)
        var rootView: CardView = itemView.findViewById(R.id.card_Kotlin)

        fun bind(kotlinContent: KotlinContent) {
            titleText.text = kotlinContent.title
            descriptionText.text = kotlinContent.description
        }

        init {
            itemView.setOnClickListener {
                kListener.onItemClick(adapterPosition)
            }
        }
    }

    // Adapter
    var kotlinContents: ArrayList<KotlinContent> = ArrayList()

    fun setContent(kotlinContents: java.util.ArrayList<KotlinContent>) {
        this.kotlinContents = kotlinContents
        notifyDataSetChanged()
    }

    // Override Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotlinHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_kotlin, parent, false)
        return KotlinHolder(view, kListener)
    }

    override fun onBindViewHolder(holder: KotlinHolder, position: Int) {
        val kotlinContent: KotlinContent = kotlinContents.get(position)
        return holder.bind(kotlinContent)
    }

    override fun getItemCount(): Int {
        return kotlinContents.size
    }


}