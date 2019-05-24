package com.example.sensorstreamer2.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.sensorstreamer2.App
import com.example.sensorstreamer2.sample.Sample
import com.example.sensorstreamer2.sample.SamplePublisher

open class SensorSamplePublisher(val sensorLookup: SensorLookup, val samplingMillis: Int): SamplePublisher() {
    private var publishingListener = SensorPublishingListener()

    private inner class SensorPublishingListener : SensorEventListener {
        private val sensorManager = App.getContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        private val sensor = sensorManager.getDefaultSensor(sensorLookup.id)

        init {sensorManager.registerListener(this, sensor, samplingMillis*1000)}

        var sample: Sample? = null
        override fun onSensorChanged(event: SensorEvent?) {
            sample = Sample(
                event!!.sensor.name,
                getRelativeMillis(event.timestamp),
                event.values.toCollection(ArrayList())
            )
            notifySubscribers()
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }


    override fun getSample(): Sample {return publishingListener.sample!!}

    companion object {
        var timeOfStart: Long = 0
        fun getRelativeMillis(timeCurrent: Long): Long {return (timeCurrent - timeOfStart)/1000000}
    }
}