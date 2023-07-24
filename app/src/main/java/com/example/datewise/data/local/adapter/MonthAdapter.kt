package com.example.datewise.data.local.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datewise.R
import com.example.datewise.data.local.model.DayEntity
import com.example.datewise.data.local.model.MyItem
import com.example.datewise.databinding.ItemCardBinding

class MonthAdapter(private val dayList:List<MyItem>):
    RecyclerView.Adapter<MonthAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.fragment_anasayfa, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dayList[position]
        holder.textView.text = item.toString()
    }

    override fun getItemCount(): Int {
        return dayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textMonth)
    }

}