package com.example.datewise.data.local.repository

import androidx.lifecycle.MutableLiveData
import com.example.datewise.data.local.dao.DayDAO
import com.example.datewise.data.local.model.DayModel

class DayRepository (private val dayDAO: DayDAO){
    val dayList=MutableLiveData<List<DayModel>>()

    suspend fun getTumDayList():List<DayModel>{
        return dayDAO.tumday()
    }
    suspend fun ekleday(day:DayModel){
        dayDAO.dayEkle(day)
    }
    suspend fun guncelleday(day:DayModel){
        dayDAO.dayguncelle(day)
    }
    suspend fun silday(day:DayModel){
        dayDAO.daysil(day)
    }
    fun getirDay():MutableLiveData<List<DayModel>>{
        return dayList
    }
}