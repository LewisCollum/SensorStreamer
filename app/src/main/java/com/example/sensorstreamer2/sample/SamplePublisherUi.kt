package com.example.sensorstreamer2.sample

import android.widget.EditText
import android.widget.Switch
import com.example.sensorstreamer2.sensor.SensorLookup

data class SamplePublisherUi(
    val name: String,
    val switch: Switch,
    var sampleMillis: EditText,
    val lookup: SensorLookup
)
