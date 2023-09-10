package com.example.datewise.ui.anasayfa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datewise.R
import com.example.datewise.data.local.adapter.DayAdapter
import com.example.datewise.data.local.database.AppDatabase
import com.example.datewise.data.local.model.DayModel
import com.example.datewise.databinding.FragmentAnasayfaBinding
import com.example.datewise.ui.viewmodel.DayViewModel
import com.example.datewise.ui.viewmodel.DayViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class AnasayfaFragment : Fragment() {
    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var dayList: List<DayModel>
    private lateinit var dayDB: AppDatabase
    private lateinit var dayViewModel: DayViewModel
     private lateinit var adapter: DayAdapter





    //private lateinit var horizontalRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)

        //ImageView

        //binding.profil.setBackgroundColor(R.drawable.round_image_view)


        val application= requireNotNull(this.activity).application
        val dataSource=AppDatabase.getAppDatabase(application)?.dayDao

        val viewModelFactory=dataSource?.let { DayViewModelFactory(it,application) }
        dayViewModel=viewModelFactory?.let {
            ViewModelProvider(this,it).get(DayViewModel::class.java)
        }!!
        dayViewModel.dayList.observe(viewLifecycleOwner){daysList ->
            dayList=daysList
            adapter= DayAdapter(dayList)
            //diften sonra
            adapter.submitList(dayList)
            binding.adapter=adapter

        }

        binding.lifecycleOwner=this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DB getir
        dayDB = AppDatabase.getAppDatabase(requireContext())!!
        //dayList = dayDB.dayDao.tumday()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tumDayleriGoster()

        binding.apply {
            
           //val month= binding.changeDate.month.toString()
          // binding.textMonth.text=month
            // Şu anki tarihi al
            val calendar = Calendar.getInstance()
            val currentDate = calendar.time








            //deneme


           /* val datePicker = binding.changeDate

            val markedDates= mutableListOf<Calendar>()
            for(i in 1..10){
                val pastDate=calendar.clone() as Calendar
                pastDate.add(android.icu.util.Calendar.DAY_OF_MONTH,-1)
                markedDates.add(pastDate)
            }
            datePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ) { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                // Seçilen tarihi kullan
            }
            // Geçmiş tarihleri işaretle
            for (markedDate in markedDates) {
                val year = markedDate.get(Calendar.YEAR)
                val month = markedDate.get(Calendar.MONTH)
                val day = markedDate.get(Calendar.DAY_OF_MONTH)
                val markedTimeInMillis = markedDate.timeInMillis

                if (markedTimeInMillis <= calendar.timeInMillis) {
                    val dpDialog = datePicker
                    dpDialog.minDate = markedTimeInMillis
                    dpDialog.findViewById<View>(Resources.getSystem().getIdentifier("day", "id", "android"))?.apply {
                        background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_marked_date)
                    }
                }
            }*/





// SimpleDateFormat kullanarak ay adını al
            val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
            val currentMonth = monthFormat.format(currentDate)

// Ay adını TextView'e yazdır
            binding.textMonth.text = currentMonth


                buttonYeniDay.setOnClickListener {
                    findNavController().navigate(R.id.action_anasayfaFragment_to_dayEkleFragment)
                }

        }

        //anim
        val ttb=AnimationUtils.loadAnimation(requireContext(),R.anim.ttb)
       binding.rvday.startAnimation(ttb)

    }




    fun tumDayleriGoster() {
        dayViewModel.dayList.observe(viewLifecycleOwner) { daysList ->
            dayList = daysList
            binding.apply {
                if (dayList.isEmpty()) {
                    Snackbar.make(requireView(), "Etkinlik Bulunamadı", 1000).show()
                } else {
                   // val dayAdapter=DayAdapter(dayList)
                    //rvday.adapter=dayAdapter
                    rvday.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                    //(this@AnasayfaFragment,LinearLayoutManager.HORIZONTAL,false)
                        //GridLayoutManager(context, 2)
                    rvday.setHasFixedSize(true)

                }
            }
        }

    }




}



