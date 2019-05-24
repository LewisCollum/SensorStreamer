package com.example.sensorstreamer2

import kotlinx.io.IOException
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket

class TcpClient {
    private var socket: Socket? = null
    private var outputStream: OutputStream? = null

    fun send(message: String) {
        outputStream!!.write(message.toByteArray())
        outputStream!!.flush()
    }

    fun connect(address: String, port: Int) {
        if (socket != null) disconnect()

        try {
            socket = Socket(InetAddress.getByName(address), port)
            outputStream = socket!!.getOutputStream()
        } catch(e: IOException) {e.printStackTrace()}
    }

    private fun disconnect() {
        socket!!.close()
        socket = null
    }
}