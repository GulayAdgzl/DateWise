package com.example.datewise.data.local.adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.datewise.data.local.model.DayEntity

class DayAdapter(private var dayList:List<DayEntity?>):
    RecyclerView.Adapter<DayAdapter.CardHolder>() {
    class CardHolder(val itemCardBinding: ItemCardBinding):RecyclerView.ViewHolder(itemCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val itemCardBinding=ItemCardBinding.inflate(layoutInflater,parent,false)
        return CardHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val today=todayList[position]

        holder.itemCardBinding.apply {
            today?.let{
                textViewTarih.text=today.todayDate.toString()
                textViewYazi.text=today.todayCom
            }
            itemCard.setOnClickListener { button ->
                today?.let {

                    val anasayfaToDetay= AnasayfaFragmentDirections.anasayfaToTodayGuncelle(today)

                    Navigation.findNavController(button).navigate(anasayfaToDetay)
                }
            }
        }
    }

    override fun getItemCount() = todayList.size
}