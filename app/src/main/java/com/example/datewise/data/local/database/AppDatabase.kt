package com.example.datewise.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.datewise.data.local.dao.DayDAO

import com.example.datewise.data.local.model.DayModel

@Database(entities = [DayModel::class], version = 2, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract val dayDao: DayDAO
    companion object {

        @Volatile
        private var  INSTANCE:AppDatabase?=null

        fun getAppDatabase(context: Context): AppDatabase?
        {
            //singelton
            synchronized(this){
                var instance= INSTANCE
                if(instance == null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "day_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return INSTANCE
            }

        }
    }
}