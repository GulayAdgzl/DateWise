package com.example.datewise.ui.anasayfa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.datewise.data.local.database.AppDatabase
import com.example.datewise.data.local.model.DayEntity
import com.example.datewise.databinding.FragmentAnasayfaBinding
import com.google.android.material.snackbar.Snackbar


class AnasayfaFragment : Fragment() {
    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var dayList:List<DayEntity>
    private lateinit var dayDB:AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAnasayfaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DB getir
        dayDB=AppDatabase.getAppDatabase(requireContext())!!
        //dayListe tüm ürünleri getir
        dayList=dayDB.dayDao.tumDay()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tumDayleriGoster()
        /*
        binding.apply{
        buttonYeniUrun.setOnclickListener{
                      findNavController().navigate(R.id.anasayfaToUrunEkle)
               }
        }
        */
    }

    fun tumDayleriGoster(){
        binding.apply {
            if(dayList.isEmpty()){
                Snackbar.make(requireView(),"Etkinlik Bulunamadı",1000).show()
            }else{
                //val dayAdapter=DayAdapter(dayList)
                //rvDay.adapter=dayAdapter
                //rvDay.layoutManager=GridLayoutManager(context,2)
                //rvDay.setHasFixedSize(true)

            }
        }
    }

}