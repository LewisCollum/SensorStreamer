package com.example.sensorstreamer2.sensor

import com.example.sensorstreamer2.sample.SampleSubscriber

object SensorSubscriptionManager {
    private var publishers = hashMapOf<SensorLookup, SensorSamplePublisher>()

    fun addPublisher(lookup: SensorLookup, samplingMillis: Int) {
        if (!publishers.containsKey(lookup))
            publishers[lookup] = SensorSamplePublisher(lookup, samplingMillis)
    }

    fun removePublisher(lookup: SensorLookup) {
        publishers.remove(lookup)!!
    }

    fun subscribeToAllPublishers(subscriber: SampleSubscriber) {
        publishers.forEach { (_, publisher) -> subscriber.subscribeToPublisher(publisher)}
    }

    fun unsubscribeFromAllPublishers(subscriber: SampleSubscriber) {
        publishers.forEach { (_, publisher) -> subscriber.unsubscribeFromPublisher(publisher)}
    }

}