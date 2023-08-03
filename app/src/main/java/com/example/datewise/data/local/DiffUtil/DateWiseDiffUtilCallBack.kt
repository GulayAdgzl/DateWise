package com.example.datewise.data.local.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.datewise.data.local.model.DayModel

class DateWiseDiffUtilCallBack:DiffUtil.ItemCallback<DayModel>() {

    override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
        return oldItem==newItem
    }

}