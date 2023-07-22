package com.example.datewise.ui.Reminder.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReminderModel (
    val id:Long,
    val name:String,
    val timestamp:Long,

):Parcelable