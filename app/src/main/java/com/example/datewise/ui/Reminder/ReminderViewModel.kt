package com.example.datewise.ui.Reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datewise.ui.Reminder.data.ReminderModel

class ReminderViewModel: ViewModel() {

    private val _reminderList=MutableLiveData<List<ReminderModel>>()
    val reminderList:LiveData<List<ReminderModel>> get()=_reminderList

    init {
        _reminderList.value= emptyList()
    }
    fun addReminder(reminder:ReminderModel){
        _reminderList.value=_reminderList.value?.plus(reminder)
    }
}