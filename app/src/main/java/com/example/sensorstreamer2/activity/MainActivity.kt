package com.example.sensorstreamer2.activity

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Switch
import com.example.sensorstreamer2.R
import com.example.sensorstreamer2.sample.SampleWriteSampleSubscriber
import com.example.sensorstreamer2.sensor.SensorLookup
import com.example.sensorstreamer2.sensor.SensorSamplePublisher
import com.example.sensorstreamer2.sensor.SensorSubscriptionManager
import com.example.sensorstreamer2.writer.LogWriter

class MainActivity : Activity() {
    private var logSubscriber = SampleWriteSampleSubscriber(LogWriter())

    override fun onCreate(savedInstanceState: Bundle?) {
        SensorSamplePublisher.timeOfStart = SystemClock.elapsedRealtimeNanos()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logSwitch = findViewById<Switch>(R.id.subscriber_log)
        logSwitch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) SensorSubscriptionManager.subscribeToAllPublishers(logSubscriber)
            else SensorSubscriptionManager.unsubscribeFromAllPublishers(logSubscriber)
        }

        val linearAccelerationSwitch = findViewById<Switch>(R.id.publisher_linearAcceleration)
        linearAccelerationSwitch?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) SensorSubscriptionManager.addPublisher(SensorLookup.LINEAR_ACCELERATION, 1000)
            else SensorSubscriptionManager.removePublisher(SensorLookup.LINEAR_ACCELERATION)
        }
    }
}