package com.example.sensorstreamer2.sensor

import android.hardware.Sensor

enum class SensorLookup(val id: Int) {
    LINEAR_ACCELERATION(Sensor.TYPE_LINEAR_ACCELERATION),
    ROTATION_VECTOR(Sensor.TYPE_ROTATION_VECTOR),
    ACCELERATION(Sensor.TYPE_ACCELEROMETER),
    ACCELERATION_UNCALIBRATED(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED),
    GYROSCOPE(Sensor.TYPE_GYROSCOPE),
    GYROSCOPE_UNCALIBRATED(Sensor.TYPE_GYROSCOPE_UNCALIBRATED),
    MAGNETIC_FIELD(Sensor.TYPE_MAGNETIC_FIELD),
    MAGNETIC_FIELD_UNCALIBRATED(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED)
}