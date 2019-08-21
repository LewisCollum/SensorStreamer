package com.example.sensorstreamer2.sample

abstract class SamplePublisher {
    internal var sample: Sample? = null

    private var subscribers = mutableListOf<SampleSubscriber>()
    internal fun addSubscriber(subscriber: SampleSubscriber) {subscribers.add(subscriber)}
    internal fun removeSubscriber(subscriber: SampleSubscriber) {subscribers.remove(subscriber)}
    protected fun notifySubscribers() {for (subscriber in subscribers) subscriber.update(this)}
    fun clearSubscribers() {subscribers.clear()}
}