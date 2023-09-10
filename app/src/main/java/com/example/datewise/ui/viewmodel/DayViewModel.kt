package com.example.datewise.ui.viewmodel



import android.app.Application
import androidx.lifecycle.asLiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.datewise.data.local.dao.DayDAO
import com.example.datewise.data.local.model.DayModel
import com.example.datewise.data.local.repository.DayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 class DayViewModel (
     val db: DayDAO,

     application: Application):AndroidViewModel(application){
     //dataStore




     private val repository: DayRepository
     var dayList = MutableLiveData<List<DayModel>>()


     init {

         repository = DayRepository(db)
         getTumToday()
         dayList=repository.getirDay()
     }

     private fun getTumToday() {
         viewModelScope.launch {
             val days = repository.getTumDayList()
             dayList.value = days
         }
     }
     fun ekleday(day: DayModel) {
         viewModelScope.launch {
             repository.ekleday(day)
         }
     }
     fun guncelleday(day: DayModel) {
         viewModelScope.launch {
             repository.guncelleday(day)
         }
     }
     fun silday(day: DayModel) {
         viewModelScope.launch {
             repository.silday(day)
         }
     }

/*
   var dayList=MutableLiveData<List<DayModel>>()

    init {
        getTumToday()
    }
    sealed class Event {
        object PopBackStack : Event()
        data class ShowToast(@StringRes val textResId: Int) : Event()
    }

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()
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
    }*/

}