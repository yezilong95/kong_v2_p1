package com.vibelink.client.adb

import android.util.Log
import okio.Buffer
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

/** 单路 TCP ↔ ADB 中继帧。 */
class AdbTcpBridge(
    private val streamId: Int,
    private val host: String,
    private val port: Int,
    private val sendData: (streamId: Int, payload: ByteArray) -> Unit,
    private val onClosed: (streamId: Int) -> Unit,
) {
    private val running = AtomicBoolean(false)
    private var socket: Socket? = null
    private val executor = Executors.newSingleThreadExecutor()

    fun open(): Boolean {
        if (!running.compareAndSet(false, true)) {
            return true
        }
        return try {
            val sock = Socket()
            sock.tcpNoDelay = true
            sock.connect(InetSocketAddress(host, port), 8_000)
            socket = sock
            executor.execute { readLoop(sock) }
            true
        } catch (error: Exception) {
            Log.w(TAG, "open failed stream=$streamId $host:$port", error)
            running.set(false)
            false
        }
    }

    fun write(payload: ByteArray) {
        val sock = socket ?: return
        if (!running.get()) {
            return
        }
        try {
            val out = DataOutputStream(sock.getOutputStream())
            out.write(payload)
            out.flush()
        } catch (error: Exception) {
            Log.w(TAG, "write failed stream=$streamId", error)
            close()
        }
    }

    fun close() {
        if (!running.compareAndSet(true, false)) {
            return
        }
        runCatching { socket?.close() }
        socket = null
        executor.shutdownNow()
        onClosed(streamId)
    }

    private fun readLoop(sock: Socket) {
        val input = DataInputStream(sock.getInputStream())
        val buf = ByteArray(16 * 1024)
        try {
            while (running.get()) {
                val n = input.read(buf)
                if (n < 0) {
                    break
                }
                if (n == 0) {
                    continue
                }
                sendData(streamId, buf.copyOf(n))
            }
        } catch (_: Exception) {
            // closed
        } finally {
            close()
        }
    }

    companion object {
        private const val TAG = "AdbTcpBridge"
    }
}

object AdbFrameCodec {
    const val MAGIC: Int = 0xA1
    const val VERSION: Int = 1
    const val TYPE_OPEN: Int = 1
    const val TYPE_DATA: Int = 2
    const val TYPE_CLOSE: Int = 3
    const val TYPE_ERR: Int = 4

    fun encode(type: Int, streamId: Int, payload: ByteArray = ByteArray(0)): ByteArray {
        val buffer = Buffer()
        buffer.writeByte(MAGIC)
        buffer.writeByte(VERSION)
        buffer.writeByte(type and 0xff)
        buffer.writeByte(0)
        buffer.writeInt(streamId)
        buffer.writeInt(payload.size)
        if (payload.isNotEmpty()) {
            buffer.write(payload)
        }
        return buffer.readByteArray()
    }

    data class Frame(val type: Int, val streamId: Int, val payload: ByteArray)

    fun tryDecode(bytes: ByteArray): Frame? {
        if (bytes.size < 12) {
            return null
        }
        val buffer = Buffer().write(bytes)
        val magic = buffer.readByte().toInt() and 0xff
        val ver = buffer.readByte().toInt() and 0xff
        if (magic != MAGIC || ver != VERSION) {
            return null
        }
        val type = buffer.readByte().toInt() and 0xff
        buffer.readByte() // flags
        val streamId = buffer.readInt()
        val len = buffer.readInt()
        if (len < 0 || len > 4 * 1024 * 1024 || buffer.size < len.toLong()) {
            return null
        }
        val payload = if (len == 0) ByteArray(0) else buffer.readByteArray(len.toLong())
        return Frame(type, streamId, payload)
    }
}
