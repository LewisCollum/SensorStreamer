package com.example.sensorstreamer2.sample

open class SampleSubscriber {
    protected var sample: Sample? = null

    open fun update(publisher: SamplePublisher) {sample = publisher.sample}
    fun subscribeTo(publisher: SamplePublisher) {publisher.addSubscriber(this)}
    fun unsubscribeFrom(publisher: SamplePublisher) {publisher.removeSubscriber(this)}
}