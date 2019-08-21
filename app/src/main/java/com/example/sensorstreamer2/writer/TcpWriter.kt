package com.example.sensorstreamer2.writer

import kotlinx.io.IOException
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket

class TcpWriter: Writer {
    private var socket: Socket? = null
    private var outputStream: OutputStream? = null

    override fun write(message: String) {
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

    fun disconnect() {
        socket?.close()
    }

    fun isConnected(): Boolean = when (socket!=null) {
        true -> socket!!.isConnected
        false -> false
    }
}
