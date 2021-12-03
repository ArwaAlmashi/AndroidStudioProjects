package com.example.guessthephraseupdated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var statementList: ArrayList<Statement> = ArrayList()
    fun setStatement(statementList: java.util.ArrayList<Statement>) {
        this.statementList = statementList
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var myStatement : TextView = itemView.findViewById(R.id.statement)
        fun bind(statement: Statement){
            myStatement.text = statement.text
            myStatement.setTextColor(statement.color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val statement : Statement = statementList.get(position)
        holder.bind(statement)
    }

    override fun getItemCount(): Int {
        return statementList.size
    }

}