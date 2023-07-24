package com.example.datewise.data.local.dao

import androidx.room.*
import com.example.datewise.data.local.model.DayEntity
import java.util.*

@Dao
interface DayDAO {
    @Insert
    suspend fun dayEkle(day:DayEntity)

    @Update
    suspend fun dayGuncelle(day:DayEntity)

    @Delete
    suspend fun daySil(day: DayEntity)

    @Query("SELECT * FROM  days_table")
    fun tumDay():List<DayEntity>

    @Query("SELECT * FROM days_table WHERE  id=key")
    suspend fun dayGetir(key:Int):DayEntity?

    @Query("SELECT * FROM days_table WHERE  dayDate BETWEEN :startDate AND :endDate")
    suspend fun fetchBy(startDate: Date, endDate: Date): List<DayEntity>

    @Query("SELECT AVG(value) as average FROM day where dayDate BETWEEN :startDay AND :endDay ORDER BY timestamp ASC")
    suspend fun getAverageByDateRange(
        startDay: Date,
        endDay: Date
    ): Float?

}