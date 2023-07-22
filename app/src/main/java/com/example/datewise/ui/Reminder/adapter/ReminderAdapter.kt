package com.example.datewise.ui.Reminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datewise.R
import com.example.datewise.databinding.ItemReminderBinding
import com.example.datewise.ui.Reminder.data.ReminderModel
import java.text.SimpleDateFormat
import java.util.*

class ReminderAdapter(private var reminderList:List<ReminderModel>):
      RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)

        return ReminderViewHolder(ItemReminderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    //ReminderViewHolder(itemView)
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

    class ReminderViewHolder(private val binding: ItemReminderBinding):RecyclerView.ViewHolder(binding.root)
        /*itemView: ItemReminderBinding) :
        RecyclerView.ViewHolder(itemView) */{

        fun bind(reminder: ReminderModel) {
            itemView.findViewById<TextView>(R.id.textViewReminderName).text = reminder.name

            // Unix zamanını okunabilir bir şekilde biçimlendirme örneği:
            val reminderTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(Date(reminder.timestamp))
            itemView.findViewById<TextView>(R.id.textViewReminderTime).text = reminderTime

        }
    }
}