package com.example.sensorstreamer2.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.sensorstreamer2.App
import com.example.sensorstreamer2.sample.Sample
import com.example.sensorstreamer2.sample.SamplePublisher

class SensorSamplePublisher(sensorLookup: SensorLookup, samplingMillis: Int): SamplePublisher(), SensorEventListener {
    private val sensorManager = App.getContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor = sensorManager.getDefaultSensor(sensorLookup.id)
    init {sensorManager.registerListener(this, sensor, samplingMillis*1000)}

    override fun onSensorChanged(event: SensorEvent?) {
        super.sample = Sample(
            name = event!!.sensor.name,
            millis = App.nanosSinceStart(event.timestamp)/1000000,
            values = event.values.toCollection(ArrayList())
        )
        super.notifySubscribers()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}