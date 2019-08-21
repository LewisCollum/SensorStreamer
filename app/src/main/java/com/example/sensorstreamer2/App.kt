package com.example.sensorstreamer2

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.SystemClock

class App: Application() {
    companion object {
        private lateinit var instance: App
        fun getContext(): Context {return instance.applicationContext}

        private var nanosSinceStart: Long? = null
        fun nanosSinceStart(nanos: Long): Long {return nanos - nanosSinceStart!!}

        lateinit var preferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        nanosSinceStart = SystemClock.elapsedRealtimeNanos()
        preferences = this.getSharedPreferences(resources.getString(R.string.app_name), 0)
    }

    init {instance = this}
}
