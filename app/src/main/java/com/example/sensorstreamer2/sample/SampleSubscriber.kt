package com.example.sensorstreamer2.sample

open class SampleSubscriber {
    protected var sample: Sample? = null

    open fun update(publisher: SamplePublisher) {sample = publisher.getSample()}
    fun subscribeToPublisher(publisher: SamplePublisher) {publisher.addSubscriber(this)}
    fun unsubscribeFromPublisher(publisher: SamplePublisher) {publisher.removeSubscriber(this)}
}