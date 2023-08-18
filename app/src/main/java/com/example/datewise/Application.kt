package com.example.datewise

import android.app.Application
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider


class Application:Application() {
    override fun onCreate() {
        super.onCreate()
        setupEmojiManager()
    }
    private fun setupEmojiManager() {
        EmojiManager.install(GoogleEmojiProvider())
    }

}