package com.example.datewise.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.example.datewise.R
import com.example.datewise.data.local.NotificationWorker
import com.example.datewise.databinding.FragmentNotificationBinding
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.TimeUnit

class NotificationFragment : Fragment() {
    private lateinit var binding:FragmentNotificationBinding
    private lateinit var request: WorkRequest


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_notification,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            floatBell.setOnClickListener {
                if(dakikaKontrol()){
                    request= PeriodicWorkRequestBuilder<NotificationWorker>(
                        notificationContent.text.toString().toLong(),
                        TimeUnit.MINUTES
                    ).build()

                    WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork("com.example.datewise.data.local.NotificationWorker",ExistingPeriodicWorkPolicy.KEEP,request as PeriodicWorkRequest)

                }
            }
        }
    }

    //bildiriöm
    private fun dakikaKontrol():Boolean{
        var dogruMu=false
        binding.apply {
            val tarihText=notificationContent.text
            if (tarihText.isEmpty()){
                Snackbar.make(co,"Aralık Boşuk olamaz", Snackbar.LENGTH_LONG).show()
            }else if(tarihText.toString().toInt()<15){
                Snackbar.make(co,"Aralık 15 dakikadan az olamaz", Snackbar.LENGTH_LONG).show()
            }else{
                dogruMu=true
            }
        }
        return dogruMu
    }


}