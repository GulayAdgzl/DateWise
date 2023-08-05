package com.example.datewise.data.local.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datewise.data.local.DiffUtil.DateWiseDiffUtilCallBack
import com.example.datewise.data.local.model.DayModel
import com.example.datewise.databinding.ItemCardBinding
import com.example.datewise.ui.anasayfa.AnasayfaFragmentDirections

class DayAdapter(private var dayList:List<DayModel?>):ListAdapter<DayModel,DayAdapter.CardHolder>(DateWiseDiffUtilCallBack())
    //RecyclerView.Adapter<DayAdapter.CardHolder>()
{
    class CardHolder( val itemCardBinding: ItemCardBinding):RecyclerView.ViewHolder(itemCardBinding.root)//difften sonra{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val itemCardBinding=ItemCardBinding.inflate(layoutInflater,parent,false)
        return CardHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        //val day=dayList[position]
        val day=getItem(position)

        holder.itemCardBinding.apply {
            day?.let{
                //listelenen kartta görünenler
                //textViewYazi.text=day?.dayname
                //textViewYazi.visibility= View.GONE
               // textViewAd.text=day?.name
                //textViewAd.visibility=View.GONE
                profilePhotoImageView.setImageResource(day.emoji)
                //profilePhotoImageView.setImageResource(day.image)


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

//difften sonra sildim
  //  override fun getItemCount() = dayList.size

    // Bugünün ayını döndüren metot
    /*private fun getBugununAyı(): String? {
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat =
            SimpleDateFormat("MMMM", Locale("tr")) // Türkçe ay adı için "MMMM" formatı kullanılıyor
        return dateFormat.format(calendar.getTime())
    }*/
}


