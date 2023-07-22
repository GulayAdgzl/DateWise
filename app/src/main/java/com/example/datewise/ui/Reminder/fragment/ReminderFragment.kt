package com.example.datewise.ui.Reminder.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datewise.R
import com.example.datewise.databinding.FragmentReminderBinding
import com.example.datewise.ui.Reminder.AlarmReceiver
import com.example.datewise.ui.Reminder.ReminderViewModel
import com.example.datewise.ui.Reminder.adapter.ReminderAdapter
import com.example.datewise.ui.Reminder.data.ReminderModel


class ReminderFragment : Fragment() {

    private lateinit var binding:FragmentReminderBinding
    private lateinit var viewModel:ReminderViewModel
    private val reminderList= mutableListOf<ReminderModel>()
    private lateinit var reminderAdapter:ReminderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentReminderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)

        reminderAdapter = ReminderAdapter(emptyList()) // Başlangıçta boş liste ile adapter oluşturulur
        binding.recyclerViewReminders.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewReminders.adapter = reminderAdapter

        binding.viewModel=viewModel
        binding.lifecycleOwner=this

        // ViewModel'deki hatırlatma listesini gözlemleyerek RecyclerView'ı günceller
        viewModel.reminderList.observe(viewLifecycleOwner, Observer { reminders ->
            reminderAdapter.updateData(reminders)
        })

        binding.buttonAddReminder.setOnClickListener {
            val reminderName = binding.editTextReminderName.text.toString()
            if (reminderName.isNotEmpty()) {
                val reminder = ReminderModel(System.currentTimeMillis(), reminderName, System.currentTimeMillis())
                viewModel.addReminder(reminder)
            }
        }
        viewModel.reminderList.observe(viewLifecycleOwner, Observer{ reminders ->
            reminderAdapter.updateData(reminders)
        })
    }
    fun onAddReminderClick(view:View) {
        val reminderName = binding.editTextReminderName.text.toString()
        if (reminderName.isNotEmpty()) {
            val reminder = ReminderModel(System.currentTimeMillis(), reminderName, System.currentTimeMillis())
            viewModel.addReminder(reminder)

            // Hatırlatmayı AlarmManager ile ayarla
            setAlarm(reminder)
        }

    }
    private fun setAlarm(reminder: ReminderModel) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.putExtra("reminder_name", reminder.name)

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            reminder.id.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            reminder.timestamp,
            pendingIntent
        )
    }

}