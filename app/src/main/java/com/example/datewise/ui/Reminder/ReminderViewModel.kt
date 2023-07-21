package com.example.datewise.ui.Reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datewise.ui.Reminder.data.ReminderModel

class ReminderViewModel: ViewModel() {
    private val _reminders= MutableLiveData<List<ReminderModel>>()
    val reminder:LiveData<List<ReminderModel>>
       get()=_reminders

    //Verileri ViewModel içinde yönetebilirsiniz
    private val reminderList= mutableListOf<ReminderModel>()

    //Hatırlatıcı eklemek için bir fonksiyon
    fun addReminder(reminder:ReminderModel){
        reminderList.add(reminder)
        _reminders.value=reminderList
    }
}