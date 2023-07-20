package com.example.datewise.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "days_table")
data class DayModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int=0,
    @ColumnInfo(name = "day_image")
    var image:String,
    @ColumnInfo(name = "day_name")
    var name:String,
    @ColumnInfo(name = "day_dayName")
    var dayname:String,
    @ColumnInfo(name = "day_date")
    var daydate:Date
        )