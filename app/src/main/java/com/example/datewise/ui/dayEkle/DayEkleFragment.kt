package com.example.datewise.ui.dayEkle

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.datewise.R
import com.example.datewise.data.local.database.AppDatabase
import com.example.datewise.data.local.model.DayModel
import com.example.datewise.databinding.FragmentDayEkleBinding
import com.example.datewise.ui.emoji.EmojiFragment
import com.example.datewise.ui.viewmodel.DayViewModel
import com.example.datewise.ui.viewmodel.DayViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class DayEkleFragment : Fragment() {
    private lateinit var binding: FragmentDayEkleBinding
    private lateinit var dayDB: AppDatabase
    private lateinit var dayViewModel: DayViewModel

    private var emoji:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dayDB = AppDatabase.getAppDatabase(requireContext())!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_ekle, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getAppDatabase(application)?.dayDao


        val viewModelFactory = dataSource?.let { DayViewModelFactory(it, application) }

        dayViewModel = viewModelFactory?.let {
            ViewModelProvider(this, it).get(DayViewModel::class.java)
        }!!


        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.apply {
            btnEkle.setOnClickListener {
                val day = editTextName.text.toString()
                val dayName = editTextDayName.text.toString()
                val dayEmoji = txtEmoji.text.toString()

                dayViewModel.ekleday(
                    DayModel(
                        name = day,
                        dayname = dayName,
                        emoji = dayEmoji


                    )
                )
                findNavController().navigate(R.id.action_dayEkleFragment_to_anasayfaFragment)
            }
            //Kapatma butonu-silme
            btnClose.setOnClickListener {
                val day = editTextName.text.toString()
                val dayName = editTextDayName.text.toString()
                val dayEmoji =txtEmoji.text.toString()
                if (day.isEmpty() && dayName.isEmpty()) {
                    // İkisi de boş ise "Boş" Snackbar mesajı göster
                    Snackbar.make(requireView(), "Girilmedi", 1000).show()
                    findNavController().navigate(R.id.action_dayEkleFragment_to_anasayfaFragment)
                } else {
                    Snackbar.make(requireView(), "Silindi ", 1000).show()
                    dayViewModel.silday(
                        DayModel(
                            name = day,
                            dayname = dayName,
                            emoji = dayEmoji

                        )
                    )
                    findNavController().navigate(R.id.action_dayEkleFragment_to_anasayfaFragment)
                }

            }

        }
        binding.txtEmoji.setOnClickListener {
            findNavController().navigate(R.id.action_dayEkleFragment_to_emojiFragment)
        }

    }

    private fun observe() {
       setFragmentResultListener(EmojiFragment.KEY_REQUEST_EMOJI){_,bundle ->
           emoji=bundle.getString(EmojiFragment.KEY_BUNDLE_EMOJI).orEmpty()
           binding.txtEmoji.text=emoji
               //getString(R.string.select_emoji_with_emoji_format,emoji)
       }
    }



}