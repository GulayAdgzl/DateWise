package com.example.datewise.data.local

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Convertes {


    private val dateFormat = SimpleDateFormat("MMM", Locale("tr"))

    @TypeConverter
    fun fromDateToString(date: Date?): String? {
        return date?.let { dateFormat.format(it) }
    }

    @TypeConverter
    fun fromStringToDate(dateString: String?): Date? {
        return dateString?.let { dateFormat.parse(it) }
    }
    @TypeConverter
    fun fromTimestamp(value:Long?): Date?{
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date:Date?):Long?{
        return date?.time
    }
}