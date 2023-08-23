package com.example.datewise.data.local.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "days_table")
data class DayModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int=0,
    @ColumnInfo(name = "emoji")
    val emoji:String,
    @ColumnInfo(name = "day_name")
    val name:String,
    @ColumnInfo(name = "day_dayName")
    val dayname:String,
    //@ColumnInfo(name = "day_date")
    //val date: String

    ):Serializable