package com.example.datewise.data.local.dao

import androidx.room.*
import com.example.datewise.data.local.model.DayEntity

@Dao
interface DayDAO {
    @Insert
    fun dayEkle(day:DayEntity)

    @Update
    fun dayGuncelle(day:DayEntity)

    @Delete
    fun daySil(day: DayEntity)

    @Query("SELECT * FROM  days_table")
    fun tumDay():List<DayEntity>

    @Query("SELECT * FROM days_table WHERE  id=key")
    fun dayGetir(key:Int):DayEntity?


}