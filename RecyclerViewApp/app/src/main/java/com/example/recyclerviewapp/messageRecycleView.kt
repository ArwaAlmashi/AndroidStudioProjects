package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class MessageRecycleView : RecyclerView.Adapter<MessageRecycleView.MessageViewHolder>() {

    var messageArrayList : ArrayList<Message> = ArrayList()

    fun setMessage(messageArrayList: ArrayList<Message>) {
        this.messageArrayList = messageArrayList
        notifyDataSetChanged()
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var messatgeText: TextView = itemView.findViewById(R.id.tv_message)

        fun bind(message: Message){
            messatgeText.text = message.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_text,parent,false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        var msg : Message = messageArrayList.get(position)
        holder.bind(msg)
    }

    override fun getItemCount(): Int {
        return messageArrayList.size
    }
}

