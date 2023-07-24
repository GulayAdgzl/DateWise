package com.example.datewise.ui.viewmodel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.datewise.data.local.dao.DayDAO
import com.example.datewise.data.local.model.DayEntity

import kotlinx.coroutines.launch

class DayViewModel (val db: DayDAO, application: Application):AndroidViewModel(application){
    var dayList=MutableLiveData<List<DayEntity>>()

    init {
        getTumToday()
    }

    private fun getTumToday(){
        viewModelScope.launch {
            dayList.value=db.tumDay()
        }
    }
    fun ekletoday(today:DayEntity){
        viewModelScope.launch {
            db.dayEkle(today)
        }
    }
    fun guncelleToday(today: DayEntity) {
        viewModelScope.launch {
            db.dayGuncelle(today)
        }
    }

    fun silToday(today:DayEntity){
        viewModelScope.launch {
            db.daySil(today)
        }
    }

}