package com.example.datewise.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "days_table")
data class DayEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
   // @ColumnInfo(name = "id")
    //val id:Int=0,
    @ColumnInfo(name = "day_image")
    val image:String,
    @ColumnInfo(name = "day_name")
    val name:String,
    @ColumnInfo(name = "day_dayName")
    val dayname:String,
    @ColumnInfo(name = "day_date")
    val dayDate: Date
    ):Serializable