package com.example.numbersgameapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhraseTextRecycleView: RecyclerView.Adapter<PhraseTextRecycleView.TextViewHolder>() {


    var textPhraseList: ArrayList<MyTextPhrase> = ArrayList()

    fun setText(textPhraseList: java.util.ArrayList<MyTextPhrase>) {
        this.textPhraseList = textPhraseList
        notifyDataSetChanged()
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myText: TextView = itemView.findViewById(R.id.textInRecycleView)

        fun bind(textPhrase: MyTextPhrase){
            myText.text = textPhrase.text
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.phrase_list_item,parent,false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        var txt : MyTextPhrase = textPhraseList.get(position)
        holder.bind(txt)
    }

    override fun getItemCount(): Int {
        return textPhraseList.size
    }

}