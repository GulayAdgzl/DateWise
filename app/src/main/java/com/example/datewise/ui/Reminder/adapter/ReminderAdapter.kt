package com.example.datewise.ui.Reminder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datewise.R
import com.example.datewise.ui.Reminder.data.ReminderModel

class ReminderAdapter(private val reminderList:List<ReminderModel>):
      RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        return ReminderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val currentReminder = reminderList[position]
        holder.bind(currentReminder)
    }

    override fun getItemCount() = reminderList.size

    class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(reminder: ReminderModel) {
            val textViewReminderName = itemView.findViewById<TextView>(R.id.textViewReminderName)
            textViewReminderName.text = reminder.name
        }
    }
}