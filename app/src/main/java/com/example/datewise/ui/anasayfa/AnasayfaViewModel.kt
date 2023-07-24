package com.example.datewise.ui.anasayfa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.datewise.data.local.adapter.DayAdapter
import com.example.datewise.data.local.model.DayEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AnasayfaViewModel:ViewModel() {

    private val _days=MutableLiveData<List<DayEntity>>()
    val days:LiveData<List<DayEntity>>
        get()=_days

    init {
        getTumDayleriGoster()
    }
    private  fun getTumDayleriGoster(){



            if(days.isEmpty()){
                Snackbar.make(requireView(),"Etkinlik Bulunamadı",1000).show()
            }else{
                val dayAdapter=DayAdapter(dayList)
                rvDay.adapter=dayAdapter
                rvDay.layoutManager= GridLayoutManager(context,2)
                rvDay.setHasFixedSize(true)
            }

    }
}