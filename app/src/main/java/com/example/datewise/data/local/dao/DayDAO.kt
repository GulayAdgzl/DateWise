package com.example.datewise.data.local.dao

import androidx.room.*
import com.example.datewise.data.local.model.DayModel

@Dao
interface DayDAO {
    @Insert
    fun dayEkle(day:DayModel)

    @Update
    fun dayGuncelle(day:DayModel)

    @Delete
    fun daySil(day: DayModel)

    @Query("SELECT * FROM  days_table")
    fun tumDay():List<DayModel>

    @Query("SELECT * FROM days_table WHERE  id=key")
    fun dayGetir(key:Int):DayModel?


}