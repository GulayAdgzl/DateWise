package com.example.datewise.ui.dayEkle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.datewise.R
import com.example.datewise.data.local.database.AppDatabase
import com.example.datewise.data.local.model.DayModel
import com.example.datewise.databinding.FragmentDayEkleBinding
import com.example.datewise.ui.viewmodel.DayViewModel
import com.example.datewise.ui.viewmodel.DayViewModelFactory


class DayEkleFragment : Fragment() {
    private lateinit var binding: FragmentDayEkleBinding
    private lateinit var dayDB:AppDatabase
    private lateinit var dayViewModel: DayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dayDB=AppDatabase.getAppDatabase(requireContext())!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_day_ekle,container,false)

        val application= requireNotNull(this.activity).application
        val dataSource=AppDatabase.getAppDatabase(application)?.dayDao


        val viewModelFactory=dataSource?.let { DayViewModelFactory(it,application) }

        dayViewModel=viewModelFactory?.let {
            ViewModelProvider(this,it).get(DayViewModel::class.java)
        }!!

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnEkle.setOnClickListener{
                val day=editTextName.text.toString()
                val dayName=editTextDayName.text.toString()
                //val image=imageView.setImageDrawable()
                dayViewModel.ekleday(
                    DayModel(
                        name=day,
                        dayname = dayName,

                    )
                )
                findNavController().navigate(R.id.action_dayEkleFragment_to_anasayfaFragment)
            }
        }
    }

}