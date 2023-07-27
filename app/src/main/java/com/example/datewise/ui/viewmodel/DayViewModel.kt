package com.example.datewise.ui.viewmodel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.datewise.data.local.dao.DayDAO
import com.example.datewise.data.local.model.DayModel

import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DayViewModel (val db: DayDAO, application: Application):AndroidViewModel(application){
    var dayList=MutableLiveData<List<DayModel>>()

    init {
        getTumToday()
    }

    private fun getTumToday(){
        viewModelScope.launch {
            dayList.value=db.tumday()
        }
    }
    // Bugünün ayını döndüren metot
     /*fun getBugununAyı(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MMMM", Locale("tr")) // Türkçe ay adı için "MMMM" formatı kullanılıyor
        return dateFormat.format(calendar.time)
    }*/



    fun ekleday(day:DayModel){
        viewModelScope.launch {
            db.dayEkle(day)
        }
    }
    fun guncelleday(day: DayModel) {
        viewModelScope.launch {
            db.dayguncelle(day)
        }
    }

    fun silday(day:DayModel){
        viewModelScope.launch {
            db.daysil(day)
        }
    }

}