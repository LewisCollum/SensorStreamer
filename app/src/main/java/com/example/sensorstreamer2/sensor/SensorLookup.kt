package com.example.sensorstreamer2.sensor

import android.hardware.Sensor

enum class SensorLookup(val id: Int) {
    LINEAR_ACCELERATION(Sensor.TYPE_LINEAR_ACCELERATION),
    ROTATION_VECTOR(Sensor.TYPE_ROTATION_VECTOR)
}