package com.example.sensorstreamer2.sample

import com.example.sensorstreamer2.writer.Writer
import kotlinx.serialization.json.Json
import kotlinx.serialization.stringify


class SampleWriteSampleSubscriber(private var writer: Writer) : SampleSubscriber() {
    override fun update(publisher: SamplePublisher) {
        super.update(publisher)

        @UseExperimental(kotlinx.serialization.ImplicitReflectionSerializer::class)
        val message = Json.stringify(sample!!)
        writer.write(message)
    }
}