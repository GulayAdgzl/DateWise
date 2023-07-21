package com.example.datewise.data.local.dao

import androidx.room.*
import com.example.datewise.data.local.model.DayEntity
import java.util.*

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

    @Query("SELECT * FROM weight WHERE  dayDate BETWEEN :startDate AND :endDate")
    fun fetchBy(startDate: Date, endDate: Date): List<DayEntity>

    @Query("SELECT AVG(value) as average FROM day where dayDate BETWEEN :startDay AND :endDay ORDER BY timestamp ASC")
    fun getAverageByDateRange(
        startDay: Date,
        endDay: Date
    ): Float?

}