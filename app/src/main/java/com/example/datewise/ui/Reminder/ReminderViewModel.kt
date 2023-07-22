package com.example.datewise.ui.Reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datewise.ui.Reminder.data.ReminderModel

class ReminderViewModel: ViewModel() {

    private val _reminderList=MutableLiveData<List<ReminderModel>>()
    val reminderList:LiveData<List<ReminderModel>> get()=_reminderList

    // ViewModel'ın başlatıcı metodunda hatırlatma listesini boş bir liste ile başlatırız
    init {
        _reminderList.value= emptyList()
    }

    // Hatırlatma eklemek için bu metod kullanılacak
    fun addReminder(reminder:ReminderModel){
        _reminderList.value=_reminderList.value?.plus(reminder)
           /* ?.let { list->
            list.toMutableList().apply {
                add(reminder)
                _reminderList.value=this
            }*/
        // _reminderList değerini güncellemek için yeni bir listeyi ata
        val updatedList = reminderList.value.orEmpty().toMutableList()
        updatedList.add(reminder)
        _reminderList.value = updatedList


    }
}