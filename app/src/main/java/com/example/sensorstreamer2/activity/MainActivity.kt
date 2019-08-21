package com.example.sensorstreamer2.activity

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.example.sensorstreamer2.App
import com.example.sensorstreamer2.R
import com.example.sensorstreamer2.sample.SamplePublisherUi
import com.example.sensorstreamer2.sample.SampleWriteSubscriber
import com.example.sensorstreamer2.sensor.SensorLookup
import com.example.sensorstreamer2.sensor.SensorSubscriptionManager
import com.example.sensorstreamer2.writer.LogWriter
import com.example.sensorstreamer2.writer.TcpWriter
import com.example.sensorstreamer2.PreferencedEditText

class MainActivity : Activity() {
    private lateinit var publisherSwitches: List<SamplePublisherUi>
    private var logSubscriber = SampleWriteSubscriber.fromWriter(LogWriter())
	//TODO TcpWriter can be generalized. Create ConnectionWriter(connector: Connector) which allows objects implementing a Connector interface to write. Connector interface has connect, disconnect functions
	private val tcpWriter = TcpWriter()
    private var tcpSubscriber = SampleWriteSubscriber.fromWriter(tcpWriter)

    private lateinit var port: EditText
    private lateinit var ip: EditText
    private lateinit var tcpSwitch: Switch
	private lateinit var logSwitch: Switch
	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

		setViewObjectsFromXml()
		setSubscriberSwitchListeners()
		setPublisherSwitchListeners()
    }

	private fun setViewObjectsFromXml() {
		PreferencedEditText.preferences = App.preferences
        port = PreferencedEditText.fromPreferenceName(findViewById(R.id.port), "port")
        ip = PreferencedEditText.fromPreferenceName(findViewById(R.id.ip), "ip")
		logSwitch = findViewById<Switch>(R.id.subscriber_log)
		tcpSwitch = findViewById(R.id.subscriber_tcp)
		//publisherSwitches = SensorSwitchGenerator.generateList()
		
        publisherSwitches = listOf(
            SamplePublisherUi(
                name = "linearAcceleration",
                switch = findViewById(R.id.publisher_linearAcceleration),
                sampleMillis = findViewById(R.id.publisher_linearAcceleration_time),
                lookup = SensorLookup.LINEAR_ACCELERATION),
			SamplePublisherUi(
                name = "rotationVector",
                switch = findViewById(R.id.publisher_rotationVector),
                sampleMillis = findViewById(R.id.publisher_rotationVector_time),
                lookup = SensorLookup.ROTATION_VECTOR),
            SamplePublisherUi(
                name = "Acceleration",
                switch = findViewById(R.id.publisher_acceleration),
                sampleMillis = findViewById(R.id.publisher_acceleration_time),
                lookup = SensorLookup.ACCELERATION),
            SamplePublisherUi(
                name = "Gyroscope",
                switch = findViewById(R.id.publisher_gyroscope),
                sampleMillis = findViewById(R.id.publisher_gyroscope_time),
                lookup = SensorLookup.GYROSCOPE))
				
		for (publisherSwitch in publisherSwitches) 
			publisherSwitch.sampleMillis = PreferencedEditText.fromPreferenceName(publisherSwitch.sampleMillis, publisherSwitch.name)
	}

	private fun setSubscriberSwitchListeners() {				
        logSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) SensorSubscriptionManager.subscribeToAllPublishers(logSubscriber)
            else SensorSubscriptionManager.unsubscribeFromAllPublishers(logSubscriber)
        }

        tcpSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                SocketTask().execute()}
            else {
                tcpWriter.disconnect()
                SensorSubscriptionManager.unsubscribeFromAllPublishers(tcpSubscriber)}
        }
	}

	private fun setPublisherSwitchListeners() {
		for (publisherSwitch in publisherSwitches) {
			publisherSwitch.switch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    val samplingMillis = publisherSwitch.sampleMillis.text.toString().toInt()
                    SensorSubscriptionManager.addPublisher(publisherSwitch.lookup, samplingMillis)}
                else
                    SensorSubscriptionManager.removePublisher(publisherSwitch.lookup)
            }
        }
	}		

	//TODO extract class
    private inner class SocketTask: AsyncTask<String, Void, Void>() {
        override fun doInBackground(vararg params: String?): Void? {
            tcpWriter.connect(ip.text.toString(), port.text.toString().toInt())
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            val message: String

            if (tcpWriter.isConnected()) {
                SensorSubscriptionManager.subscribeToAllPublishers(tcpSubscriber)
                message = "SUCCESS!"
            }
            else {
                tcpSwitch.isChecked = false
                message = "Not able to connect"
            }

            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
