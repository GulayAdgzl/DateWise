package com.example.datewise.data.local.dao

import androidx.room.*
import com.example.datewise.data.local.model.DayModel
import java.util.*

@Dao
interface DayDAO {
    @Insert
     suspend fun dayEkle(day:DayModel)

    @Update
   suspend fun dayguncelle(day:DayModel)

    @Delete
    suspend fun daysil(day: DayModel)

    @Query("SELECT * FROM  days_table")
    suspend fun tumday():List<DayModel>

    @Query("SELECT * FROM days_table WHERE  id = :key")
     suspend fun daygetir(key:Int):DayModel?





}