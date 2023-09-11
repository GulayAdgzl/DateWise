package com.example.datewise.ui.dayEkle


import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.BitmapFactory
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.work.*
import androidx.work.ExistingWorkPolicy.REPLACE
import com.example.datewise.R
import com.example.datewise.data.local.database.AppDatabase
import com.example.datewise.data.local.model.DayModel
import com.example.datewise.data.util.EMPTY
import com.example.datewise.databinding.FragmentDayEkleBinding
import com.example.datewise.ui.emoji.EmojiFragment
import com.example.datewise.ui.notification.NotificationWorker

import com.example.datewise.ui.notification.NotificationWorker.Companion.NOTIFICATION_ID
import com.example.datewise.ui.notification.NotificationWorker.Companion.NOTIFICATION_WORK
import com.example.datewise.ui.viewmodel.DayViewModel
import com.example.datewise.ui.viewmodel.DayViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale.getDefault
import java.util.concurrent.TimeUnit.MILLISECONDS

class DayEkleFragment : Fragment() {
    private lateinit var binding: FragmentDayEkleBinding
    private lateinit var dayDB: AppDatabase
    private lateinit var dayViewModel: DayViewModel
    private lateinit var request: WorkRequest

    private lateinit var checkNotificationPermission: ActivityResultLauncher<String>
    private var isPermission = false
    private val CHANNEL_ID="channel_id_datewise"


    private var emoji:String=String.EMPTY
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

        checkPermission()


        binding.apply {
            binding.emojiBtn.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_emoji)
            }

            btnEkle.setOnClickListener {


                if(isPermission){
                    val day = editTextName.text.toString()
                    val dayName = editTextDayName.text.toString()
                    val dayEmoji = emojiBtn.text.toString()

                        //.dayOfMonth.toString()


                    dayViewModel.ekleday(
                        DayModel(
                            name = day,
                            dayname = dayName,
                            emoji = dayEmoji,
                        )
                    )


                    val customCalendar= Calendar.getInstance()
                    customCalendar.set(
                        binding.scale.year,
                        binding.scale.month,
                        binding.scale.dayOfMonth,
                        binding.d.hour,
                        binding.d.minute, 0
                    )


                    val customTime = customCalendar.timeInMillis
                    val currentTime = currentTimeMillis()
                    if(customTime>currentTime){
                        val data=Data.Builder().putInt(NOTIFICATION_ID,0).build()
                        val delay=customTime-currentTime
                        val titleNotificationSchedule = getString(R.string.notification_schedule_title)
                        val patternNotificationSchedule = getString(R.string.notification_schedule_pattern)

                        scheduleNotification(delay,data)
                        /////


                        make(
                            binding.co,
                            titleNotificationSchedule + SimpleDateFormat(
                                patternNotificationSchedule, getDefault()

                            ).format(customCalendar.time).toString(),
                            LENGTH_LONG
                        ).show()
                    }else {
                        val errorNotificationSchedule = getString(R.string.notification_schedule_error)
                        make(binding.co, errorNotificationSchedule, LENGTH_LONG).show()
                    }
                }else {
                    if (SDK_INT >= TIRAMISU) {
                        checkNotificationPermission.launch(POST_NOTIFICATIONS)
                    }
                }



                findNavController().navigate(R.id.action_dayEkleFragment_to_anasayfaFragment)

            }



                //Kapatma butonu-silme
                btnClose.setOnClickListener {
                    val day = editTextName.text.toString()
                    val dayName = editTextDayName.text.toString()
                    val dayEmoji = emojiBtn.text.toString()
                   // val dayDate=(scale.dayOfMonth+scale.year).toString()



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
                                emoji = dayEmoji,


                            )
                        )

                        findNavController().navigate(R.id.action_dayEkleFragment_to_anasayfaFragment)
                        //}

                    }

                }
               /* binding.emojiBtn.setOnClickListener {
                    findNavController().navigate(R.id.action_dayEkleFragment_to_emojiFragment)
                }*/
                /* binding.btnBell.setOnClickListener {
                findNavController().navigate(R.id.action_dayEkleFragment_to_notificationFragment)
            }*/
            }


        }


    private fun checkPermission() {
        if (SDK_INT >= TIRAMISU) {
            if (checkSelfPermission(requireContext(), POST_NOTIFICATIONS) == PERMISSION_GRANTED) {
                isPermission = true
            } else {
                isPermission = false

                checkNotificationPermission.launch(POST_NOTIFICATIONS)
            }
        } else {
            isPermission = true
        }
    }
    private fun scheduleNotification(delay: Long, data: Data) {
        val notificationWork = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInitialDelay(delay, MILLISECONDS).setInputData(data).build()

        val instanceWorkManager = WorkManager.getInstance(requireContext())
        instanceWorkManager.beginUniqueWork(NOTIFICATION_WORK, REPLACE, notificationWork).enqueue()
    }

    private fun observe() {
       setFragmentResultListener(EmojiFragment.KEY_REQUEST_EMOJI){_,bundle ->
           emoji=bundle.getString(EmojiFragment.KEY_BUNDLE_EMOJI).orEmpty()
           binding.emojiBtn.text=emoji
               //getString(R.string.select_emoji_with_emoji_format,emoji)
       }
    }



}

