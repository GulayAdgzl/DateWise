package com.example.datewise.ui.anasayfa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.datewise.R
import com.example.datewise.data.local.adapter.DayAdapter
import com.example.datewise.data.local.database.AppDatabase
import com.example.datewise.data.local.model.DayEntity
import com.example.datewise.databinding.FragmentAnasayfaBinding
import com.example.datewise.ui.viewmodel.DayViewModel
import com.example.datewise.ui.viewmodel.DayViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class AnasayfaFragment : Fragment() {
    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var dayList:List<DayEntity>
    private lateinit var dayDB:AppDatabase
    private lateinit var dayViewModel: DayViewModel
    private lateinit var adapter: DayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false)

        val application= requireNotNull(this.activity).application
        val dataSource=AppDatabase.getAppDatabase(application)?.dayDao

        val viewModelFactory=dataSource?.let { DayViewModelFactory(it,application) }
        dayViewModel=viewModelFactory?.let {
            ViewModelProvider(this,it).get(DayViewModel::class.java)
        }!!
        dayViewModel.dayList.observe(viewLifecycleOwner){daysList ->
            //dayList=dayList
            dayList=daysList
            adapter= DayAdapter(dayList)
            binding.adapter=adapter
        }
        binding.lifecycleOwner=this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DB getir
        dayDB=AppDatabase.getAppDatabase(requireContext())!!

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tumDayleriGoster()


       binding.textMonth.text=dayViewModel.getBugununAyı()
          

       binding.apply{
            buttonHatRlatC.setOnClickListener {
                findNavController().navigate(R.id.action_anasayfaFragment_to_dayEkleFragment)
            }
        }

    }

    fun tumDayleriGoster(){
        dayViewModel.dayList.observe(viewLifecycleOwner){ daysList ->
            dayList=daysList
            binding.apply {
                if(dayList.isEmpty()){
                    Snackbar.make(requireView(),"Etkinlik Bulunamadı",1000).show()
                }else{

                    rvDay.layoutManager= GridLayoutManager(context,2)
                    rvDay.setHasFixedSize(true)

                }
            }
        }

    }


}