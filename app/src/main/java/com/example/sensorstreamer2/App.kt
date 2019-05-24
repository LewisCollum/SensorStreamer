package com.example.sensorstreamer2

import android.app.Application
import android.content.Context

class App: Application() {
    companion object {
        private var instance: App? = null

        fun getContext(): Context {
            return instance!!.applicationContext
        }
    }

    init {instance = this}
}