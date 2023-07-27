package com.example.datewise.data.local.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.datewise.data.local.model.DayModel
import com.example.datewise.databinding.ItemCardBinding
import com.example.datewise.ui.anasayfa.AnasayfaFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class DayAdapter(private var dayList:List<DayModel?>):
    RecyclerView.Adapter<DayAdapter.CardHolder>() {
    class CardHolder(val itemCardBinding: ItemCardBinding):RecyclerView.ViewHolder(itemCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val itemCardBinding=ItemCardBinding.inflate(layoutInflater,parent,false)
        return CardHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val day=dayList[position]

        holder.itemCardBinding.apply {
            day.let{

                textViewYazi.text=day?.dayname
                textViewAd.text=day?.name

            }
            itemCard.setOnClickListener { button ->
                day?.let {

                    val anasayfaToDetay= AnasayfaFragmentDirections.actionAnasayfaFragmentToDayEkleFragment(day)
                        //.actionAnasayfaFragmentToDayEkleFragment(dayList)

                    Navigation.findNavController(button).navigate(anasayfaToDetay)
                }
            }
        }
    }


    override fun getItemCount() = dayList.size

    // Bugünün ayını döndüren metot
    /*private fun getBugununAyı(): String? {
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat =
            SimpleDateFormat("MMMM", Locale("tr")) // Türkçe ay adı için "MMMM" formatı kullanılıyor
        return dateFormat.format(calendar.getTime())
    }*/
}


