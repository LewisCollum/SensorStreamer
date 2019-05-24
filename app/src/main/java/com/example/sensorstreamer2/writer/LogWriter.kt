package com.example.sensorstreamer2.writer

import android.util.Log

class LogWriter : Writer {
    override fun write(message: String) {
        Log.v("LogWriter", message)
    }
}