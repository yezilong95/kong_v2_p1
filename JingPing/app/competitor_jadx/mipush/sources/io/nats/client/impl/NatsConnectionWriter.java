package io.nats.client.impl;

import defpackage.eg0;
import io.nats.client.Options;
import io.nats.client.StatisticsCollector;
import io.nats.client.support.BuilderBase;
import io.nats.client.support.ByteArrayBuilder;
import io.nats.client.support.NatsConstants;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
class NatsConnectionWriter implements Runnable {
    private static final int BUFFER_BLOCK_SIZE = 256;
    private final NatsConnection connection;
    private DataPort dataPort;
    private Future<DataPort> dataPortFuture;
    private final MessageQueue outgoing;
    private final long reconnectBufferSize;
    private final AtomicBoolean reconnectMode;
    private final MessageQueue reconnectOutgoing;
    private byte[] sendBuffer;
    private final AtomicInteger sendBufferLength;
    private final ReentrantLock startStopLock;
    private Future<Boolean> stopped;
    private final ReentrantLock writerLock = new ReentrantLock();
    private final AtomicBoolean running = new AtomicBoolean(false);

    public NatsConnectionWriter(NatsConnection natsConnection, NatsConnectionWriter natsConnectionWriter) {
        this.connection = natsConnection;
        this.reconnectMode = new AtomicBoolean(natsConnectionWriter != null);
        this.startStopLock = new ReentrantLock();
        CompletableFuture completableFuture = new CompletableFuture();
        this.stopped = completableFuture;
        completableFuture.complete(Boolean.TRUE);
        Options options = natsConnection.getOptions();
        int iBufferAllocSize = BuilderBase.bufferAllocSize(options.getBufferSize(), 256);
        this.sendBufferLength = new AtomicInteger(iBufferAllocSize);
        this.sendBuffer = new byte[iBufferAllocSize];
        this.outgoing = new MessageQueue(true, options.getMaxMessagesInOutgoingQueue(), options.isDiscardMessagesWhenOutgoingQueueFull(), options.getRequestCleanupInterval(), natsConnectionWriter == null ? null : natsConnectionWriter.outgoing);
        this.reconnectOutgoing = new MessageQueue(true, options.getRequestCleanupInterval(), natsConnectionWriter != null ? natsConnectionWriter.reconnectOutgoing : null);
        this.reconnectBufferSize = options.getReconnectBufferSize();
    }

    public boolean canQueueDuringReconnect(NatsMessage natsMessage) {
        if (this.reconnectBufferSize >= 0) {
            return natsMessage.getSizeInBytes() + this.outgoing.sizeInBytes() < this.reconnectBufferSize;
        }
        return true;
    }

    public void flushBuffer() {
        this.writerLock.lock();
        try {
            if (this.running.get()) {
                this.dataPort.flush();
            }
        } catch (Exception unused) {
        } finally {
            this.writerLock.unlock();
        }
    }

    public boolean isRunning() {
        return this.running.get();
    }

    public boolean queue(NatsMessage natsMessage) {
        return this.outgoing.push(natsMessage);
    }

    public void queueInternalMessage(NatsMessage natsMessage) {
        if (this.reconnectMode.get()) {
            this.reconnectOutgoing.push(natsMessage);
        } else {
            this.outgoing.push(natsMessage, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0077 A[Catch: all -> 0x003f, TRY_LEAVE, TryCatch #4 {all -> 0x003f, blocks: (B:3:0x0009, B:4:0x0019, B:6:0x0021, B:8:0x0027, B:10:0x002f, B:19:0x0056, B:17:0x0045, B:25:0x006f, B:27:0x0077, B:23:0x0062), top: B:33:0x0009, inners: #5, #3 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r14 = this;
            java.time.Duration r5 = defpackage.oj0.t()
            java.time.Duration r11 = defpackage.c60.v()
            r12 = 0
            java.util.concurrent.Future<io.nats.client.impl.DataPort> r0 = r14.dataPortFuture     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            io.nats.client.impl.DataPort r0 = (io.nats.client.impl.DataPort) r0     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            r14.dataPort = r0     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            io.nats.client.impl.NatsConnection r0 = r14.connection     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            io.nats.client.StatisticsCollector r13 = r0.getNatsStatistics()     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
        L19:
            java.util.concurrent.atomic.AtomicBoolean r0 = r14.running     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            boolean r0 = r0.get()     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            if (r0 == 0) goto L5c
            boolean r0 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            if (r0 != 0) goto L5c
            java.util.concurrent.atomic.AtomicBoolean r0 = r14.reconnectMode     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            boolean r0 = r0.get()     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            if (r0 == 0) goto L45
            io.nats.client.impl.MessageQueue r6 = r14.reconnectOutgoing     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            java.util.concurrent.atomic.AtomicInteger r0 = r14.sendBufferLength     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            int r0 = r0.get()     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            long r7 = (long) r0     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            r9 = 1000(0x3e8, double:4.94E-321)
            io.nats.client.impl.NatsMessage r0 = r6.accumulate(r7, r9, r11)     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            goto L54
        L3f:
            r0 = move-exception
            goto L7e
        L41:
            r0 = move-exception
            goto L6f
        L43:
            r0 = move-exception
            goto L6f
        L45:
            io.nats.client.impl.MessageQueue r0 = r14.outgoing     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            java.util.concurrent.atomic.AtomicInteger r1 = r14.sendBufferLength     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            int r1 = r1.get()     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            r3 = 1000(0x3e8, double:4.94E-321)
            io.nats.client.impl.NatsMessage r0 = r0.accumulate(r1, r3, r5)     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
        L54:
            if (r0 == 0) goto L19
            io.nats.client.impl.DataPort r1 = r14.dataPort     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            r14.sendMessageBatch(r0, r1, r13)     // Catch: java.lang.Throwable -> L3f java.nio.BufferOverflowException -> L41 java.io.IOException -> L43 java.lang.InterruptedException -> L62 java.lang.Throwable -> L69
            goto L19
        L5c:
            java.util.concurrent.atomic.AtomicBoolean r0 = r14.running
            r0.set(r12)
            return
        L62:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L3f
            r0.interrupt()     // Catch: java.lang.Throwable -> L3f
        L69:
            java.util.concurrent.atomic.AtomicBoolean r0 = r14.running
            r0.set(r12)
            goto L7d
        L6f:
            java.util.concurrent.atomic.AtomicBoolean r1 = r14.running     // Catch: java.lang.Throwable -> L3f
            boolean r1 = r1.get()     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L69
            io.nats.client.impl.NatsConnection r1 = r14.connection     // Catch: java.lang.Throwable -> L3f
            r1.handleCommunicationIssue(r0)     // Catch: java.lang.Throwable -> L3f
            goto L69
        L7d:
            return
        L7e:
            java.util.concurrent.atomic.AtomicBoolean r1 = r14.running
            r1.set(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsConnectionWriter.run():void");
    }

    public void sendMessageBatch(NatsMessage natsMessage, DataPort dataPort, StatisticsCollector statisticsCollector) throws IOException {
        this.writerLock.lock();
        try {
            int iBufferAllocSize = this.sendBufferLength.get();
            int i = 0;
            while (natsMessage != null) {
                long sizeInBytes = natsMessage.getSizeInBytes();
                long j = i;
                long j2 = iBufferAllocSize;
                if (j + sizeInBytes > j2) {
                    if (i > 0) {
                        dataPort.write(this.sendBuffer, i);
                        this.connection.getNatsStatistics().registerWrite(j);
                        i = 0;
                    }
                    if (sizeInBytes > j2) {
                        iBufferAllocSize = BuilderBase.bufferAllocSize((int) sizeInBytes, 256);
                        this.sendBufferLength.set(iBufferAllocSize);
                        this.sendBuffer = new byte[iBufferAllocSize];
                    }
                }
                ByteArrayBuilder protocolBab = natsMessage.getProtocolBab();
                int length = protocolBab.length();
                System.arraycopy(protocolBab.internalArray(), 0, this.sendBuffer, i, length);
                int i2 = i + length;
                byte[] bArr = this.sendBuffer;
                int i3 = i2 + 1;
                bArr[i2] = NatsConstants.CR;
                i = i2 + 2;
                bArr[i3] = 10;
                if (!natsMessage.isProtocol()) {
                    int iCopyNotEmptyHeaders = i + natsMessage.copyNotEmptyHeaders(i, this.sendBuffer);
                    byte[] data = natsMessage.getData();
                    if (data.length > 0) {
                        System.arraycopy(data, 0, this.sendBuffer, iCopyNotEmptyHeaders, data.length);
                        iCopyNotEmptyHeaders += data.length;
                    }
                    byte[] bArr2 = this.sendBuffer;
                    int i4 = iCopyNotEmptyHeaders + 1;
                    bArr2[iCopyNotEmptyHeaders] = NatsConstants.CR;
                    i = iCopyNotEmptyHeaders + 2;
                    bArr2[i4] = 10;
                }
                statisticsCollector.incrementOutMsgs();
                statisticsCollector.incrementOutBytes(sizeInBytes);
                if (natsMessage.flushImmediatelyAfterPublish) {
                    dataPort.flush();
                }
                natsMessage = natsMessage.next;
            }
            if (i > 0) {
                dataPort.write(this.sendBuffer, i);
                this.connection.getNatsStatistics().registerWrite(i);
            }
            this.writerLock.unlock();
        } catch (Throwable th) {
            this.writerLock.unlock();
            throw th;
        }
    }

    public void setReconnectMode(boolean z) {
        this.reconnectMode.set(z);
    }

    public void start(Future<DataPort> future) {
        this.startStopLock.lock();
        try {
            this.dataPortFuture = future;
            this.running.set(true);
            this.outgoing.resume();
            this.reconnectOutgoing.resume();
            this.stopped = this.connection.getExecutor().submit(this, Boolean.TRUE);
        } finally {
            this.startStopLock.unlock();
        }
    }

    public Future<Boolean> stop() {
        if (this.running.get()) {
            this.running.set(false);
            this.startStopLock.lock();
            try {
                this.outgoing.pause();
                this.reconnectOutgoing.pause();
                this.outgoing.filter(new eg0());
            } finally {
                this.startStopLock.unlock();
            }
        }
        return this.stopped;
    }
}
