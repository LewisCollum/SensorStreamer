package com.example.sensorstreamer2.sample

abstract class SamplePublisher {
    private var subscribers = mutableListOf<SampleSubscriber>()
    fun addSubscriber(subscriber: SampleSubscriber) {subscribers.add(subscriber)}
    fun removeSubscriber(subscriber: SampleSubscriber) {subscribers.remove(subscriber)}

    protected fun notifySubscribers() {for (subscriber in subscribers) subscriber.update(this)}
    abstract fun getSample(): Sample
}