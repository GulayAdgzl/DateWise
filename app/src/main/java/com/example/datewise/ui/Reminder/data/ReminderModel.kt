package com.example.datewise.ui.Reminder.data

data class ReminderModel (
    val id:Long,
    val title:String,
    val desription:String,
    val reminderTime:Long  //Hatırlatıcı zamanını saklamak için UNIX zaman
)