package io.nats.client.impl;

import defpackage.uv0;
import io.nats.client.ForceReconnectOptions;
import io.nats.client.NatsSystemClock;
import io.nats.client.Options;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsUri;
import io.nats.client.support.ScheduledTask;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class SocketDataPortWithWriteTimeout extends SocketDataPort {
    private long delayPeriodMillis;
    private final AtomicLong writeMustBeDoneBy = new AtomicLong(Long.MAX_VALUE);
    private long writeTimeoutNanos;
    private ScheduledTask writeWatchTask;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$connect$1() {
        if (NatsSystemClock.nanoTime() > this.writeMustBeDoneBy.get()) {
            this.writeWatchTask.shutdown();
            this.connection.executeCallback(new p(4));
            try {
                this.connection.forceReconnect(ForceReconnectOptions.FORCE_CLOSE_INSTANCE);
            } catch (IOException unused) {
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // io.nats.client.impl.SocketDataPort, io.nats.client.impl.DataPort
    public void afterConstruct(Options options) {
        super.afterConstruct(options);
        long millis = options.getSocketWriteTimeout() == null ? Options.DEFAULT_SOCKET_WRITE_TIMEOUT.toMillis() : options.getSocketWriteTimeout().toMillis();
        this.delayPeriodMillis = (51 * millis) / 100;
        this.writeTimeoutNanos = millis * NatsConstants.NANOS_PER_MILLI;
    }

    @Override // io.nats.client.impl.SocketDataPort, io.nats.client.impl.DataPort
    public void close() throws IOException {
        this.writeWatchTask.shutdown();
        super.close();
    }

    @Override // io.nats.client.impl.SocketDataPort, io.nats.client.impl.DataPort
    public void connect(NatsConnection natsConnection, NatsUri natsUri, long j) throws IOException {
        super.connect(natsConnection, natsUri, j);
        this.writeWatchTask = new ScheduledTask(natsConnection.getScheduledExecutor(), this.delayPeriodMillis, new uv0(1, this));
    }

    @Override // io.nats.client.impl.SocketDataPort, io.nats.client.impl.DataPort
    public void write(byte[] bArr, int i) throws IOException {
        this.writeMustBeDoneBy.set(NatsSystemClock.nanoTime() + this.writeTimeoutNanos);
        this.out.write(bArr, 0, i);
        this.writeMustBeDoneBy.set(Long.MAX_VALUE);
    }
}
