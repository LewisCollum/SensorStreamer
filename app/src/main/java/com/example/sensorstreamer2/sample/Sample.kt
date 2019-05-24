package com.example.sensorstreamer2.sample

import kotlinx.serialization.Serializable

@Serializable
data class Sample(val name: String, val millis: Long, val values: ArrayList<Float>)