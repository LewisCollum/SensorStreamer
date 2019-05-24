package com.example.sensorstreamer2.writer

import com.example.sensorstreamer2.TcpClient

class TcpWriter(private var client: TcpClient): Writer {
    override fun write(message: String) {
        client.send(message)
    }
}