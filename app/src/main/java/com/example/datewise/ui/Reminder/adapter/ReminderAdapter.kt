package com.example.datewise.ui.Reminder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datewise.R
import com.example.datewise.ui.Reminder.data.ReminderModel
import java.text.SimpleDateFormat
import java.util.*

class ReminderAdapter(private var reminderList:List<ReminderModel>):
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

    fun updateData(newReminderList: List<ReminderModel>) {
        reminderList = newReminderList
        notifyDataSetChanged()
    }

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(reminder: ReminderModel) {
            itemView.findViewById<TextView>(R.id.textViewReminderName).text = reminder.name

            // Unix zamanını okunabilir bir şekilde biçimlendirme örneği:
            val reminderTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(Date(reminder.timestamp))
            itemView.findViewById<TextView>(R.id.textViewReminderTime).text = reminderTime

        }
    }
}