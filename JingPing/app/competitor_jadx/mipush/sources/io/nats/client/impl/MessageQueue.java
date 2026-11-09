package io.nats.client.impl;

import io.nats.client.Message;
import io.nats.client.support.NatsConstants;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
class MessageQueue {
    protected static final int DRAINING = 2;
    protected static final long MIN_OFFER_TIMEOUT_NANOS = 100000000;
    protected static final String POISON = "_poison";
    protected static final NatsMessage POISON_PILL = new NatsMessage(POISON, null, NatsConstants.EMPTY_BODY);
    protected static final int RUNNING = 1;
    protected static final int STOPPED = 0;
    protected final boolean discardWhenFull;
    protected final Lock editLock;
    protected final AtomicLong length;
    protected final int maxMessagesInOutgoingQueue;
    protected final long offerLockNanos;
    protected final long offerTimeoutNanos;
    protected final LinkedBlockingQueue<NatsMessage> queue;
    protected final Duration requestCleanupInterval;
    protected final AtomicInteger running;
    protected final boolean singleReaderMode;
    protected final AtomicLong sizeInBytes;

    public MessageQueue(boolean z, Duration duration) {
        this(z, -1, false, duration, null);
    }

    private boolean isPoison(Message message) {
        return message == POISON_PILL;
    }

    public NatsMessage accumulate(long j, long j2, Duration duration) throws InterruptedException {
        NatsMessage natsMessagePoll;
        if (!this.singleReaderMode) {
            throw new IllegalStateException("Accumulate is only supported in single reader mode.");
        }
        if (!isRunning() || (natsMessagePoll = poll(duration)) == null) {
            return null;
        }
        long sizeInBytes = natsMessagePoll.getSizeInBytes();
        if (j2 <= 1 || sizeInBytes >= j) {
            this.sizeInBytes.addAndGet(-sizeInBytes);
            this.length.decrementAndGet();
            return natsMessagePoll;
        }
        NatsMessage natsMessage = natsMessagePoll;
        long j3 = 1;
        while (true) {
            NatsMessage natsMessagePeek = this.queue.peek();
            if (natsMessagePeek != null && !isPoison(natsMessagePeek)) {
                long sizeInBytes2 = natsMessagePeek.getSizeInBytes();
                if (j >= 0 && sizeInBytes + sizeInBytes2 >= j) {
                    break;
                }
                sizeInBytes += sizeInBytes2;
                j3++;
                this.queue.poll();
                natsMessage.next = natsMessagePeek;
                if (natsMessagePeek.flushImmediatelyAfterPublish || j3 == j2) {
                    break;
                }
                natsMessage = natsMessagePeek;
            } else {
                break;
            }
        }
        this.sizeInBytes.addAndGet(-sizeInBytes);
        this.length.addAndGet(-j3);
        return natsMessagePoll;
    }

    public void drain() {
        this.running.set(2);
        poisonTheQueue();
    }

    public void drainTo(MessageQueue messageQueue) {
        this.editLock.lock();
        try {
            this.queue.drainTo(messageQueue.queue);
            messageQueue.length.set(this.queue.size());
        } finally {
            this.editLock.unlock();
        }
    }

    public void filter(Predicate<NatsMessage> predicate) {
        this.editLock.lock();
        try {
            if (isRunning()) {
                throw new IllegalStateException("Filter is only supported when the queue is paused");
            }
            ArrayList arrayList = new ArrayList();
            NatsMessage natsMessagePoll = this.queue.poll();
            while (natsMessagePoll != null) {
                if (predicate.test(natsMessagePoll)) {
                    this.sizeInBytes.addAndGet(-natsMessagePoll.getSizeInBytes());
                    this.length.decrementAndGet();
                } else {
                    arrayList.add(natsMessagePoll);
                }
                natsMessagePoll = this.queue.poll();
            }
            this.queue.addAll(arrayList);
            this.editLock.unlock();
        } catch (Throwable th) {
            this.editLock.unlock();
            throw th;
        }
    }

    public boolean isDrained() {
        return this.running.get() == 2 && length() == 0;
    }

    public boolean isDraining() {
        return this.running.get() == 2;
    }

    public boolean isRunning() {
        return this.running.get() != 0;
    }

    public boolean isSingleReaderMode() {
        return this.singleReaderMode;
    }

    public long length() {
        return this.length.get();
    }

    public void pause() {
        this.running.set(0);
        poisonTheQueue();
    }

    public void poisonTheQueue() {
        try {
            this.queue.add(POISON_PILL);
        } catch (IllegalStateException unused) {
        }
    }

    public NatsMessage poll(Duration duration) throws InterruptedException {
        NatsMessage natsMessagePoll;
        if (duration == null || isDraining()) {
            natsMessagePoll = this.queue.poll();
        } else {
            long nanos = duration.toNanos();
            if (nanos != 0) {
                natsMessagePoll = this.queue.poll(nanos, TimeUnit.NANOSECONDS);
            } else {
                natsMessagePoll = null;
                while (isRunning() && (natsMessagePoll = this.queue.poll(100L, TimeUnit.DAYS)) == null) {
                }
            }
        }
        if (natsMessagePoll == null || isPoison(natsMessagePoll)) {
            return null;
        }
        return natsMessagePoll;
    }

    public NatsMessage pop(Duration duration) throws InterruptedException {
        NatsMessage natsMessagePoll;
        if (!isRunning() || (natsMessagePoll = poll(duration)) == null) {
            return null;
        }
        this.sizeInBytes.getAndAdd(-natsMessagePoll.getSizeInBytes());
        this.length.decrementAndGet();
        return natsMessagePoll;
    }

    public NatsMessage popNow() throws InterruptedException {
        return pop(null);
    }

    public boolean push(NatsMessage natsMessage) {
        return push(natsMessage, false);
    }

    public void resume() {
        this.running.set(1);
    }

    public long sizeInBytes() {
        return this.sizeInBytes.get();
    }

    public MessageQueue(boolean z, Duration duration, MessageQueue messageQueue) {
        this(z, -1, false, duration, messageQueue);
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0027: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:14:0x0027 */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean push(io.nats.client.impl.NatsMessage r12, boolean r13) throws java.lang.Throwable {
        /*
            r11 = this;
            r0 = 0
            long r1 = io.nats.client.NatsSystemClock.nanoTime()     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            java.util.concurrent.locks.Lock r3 = r11.editLock     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            long r4 = r11.offerLockNanos     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            boolean r3 = r3.tryLock(r4, r6)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            java.lang.String r4 = "Output queue is full "
            if (r3 == 0) goto L6c
            r3 = 1
            if (r13 != 0) goto L29
            boolean r13 = r11.discardWhenFull     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            if (r13 == 0) goto L29
            java.util.concurrent.LinkedBlockingQueue<io.nats.client.impl.NatsMessage> r13 = r11.queue     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            boolean r12 = r13.offer(r12)     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            java.util.concurrent.locks.Lock r13 = r11.editLock
            r13.unlock()
            return r12
        L26:
            r12 = move-exception
            r0 = r3
            goto L96
        L29:
            long r7 = r11.offerTimeoutNanos     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            long r9 = io.nats.client.NatsSystemClock.nanoTime()     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            long r9 = r9 - r1
            long r7 = r7 - r9
            r1 = 100000000(0x5f5e100, double:4.94065646E-316)
            long r1 = java.lang.Math.max(r1, r7)     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            java.util.concurrent.LinkedBlockingQueue<io.nats.client.impl.NatsMessage> r13 = r11.queue     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            boolean r13 = r13.offer(r12, r1, r6)     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            if (r13 == 0) goto L54
            java.util.concurrent.atomic.AtomicLong r13 = r11.sizeInBytes     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            long r1 = r12.getSizeInBytes()     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            r13.getAndAdd(r1)     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            java.util.concurrent.atomic.AtomicLong r12 = r11.length     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            r12.incrementAndGet()     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            java.util.concurrent.locks.Lock r12 = r11.editLock
            r12.unlock()
            return r3
        L54:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            r13.<init>(r4)     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            java.util.concurrent.LinkedBlockingQueue<io.nats.client.impl.NatsMessage> r1 = r11.queue     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            r13.append(r1)     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            r12.<init>(r13)     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
            throw r12     // Catch: java.lang.Throwable -> L26 java.lang.InterruptedException -> L87
        L6c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            r13.<init>(r4)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            java.util.concurrent.LinkedBlockingQueue<io.nats.client.impl.NatsMessage> r1 = r11.queue     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            r13.append(r1)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            r12.<init>(r13)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            throw r12     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
        L84:
            r12 = move-exception
            goto L96
        L86:
            r3 = r0
        L87:
            java.lang.Thread r12 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L26
            r12.interrupt()     // Catch: java.lang.Throwable -> L26
            if (r3 == 0) goto L95
            java.util.concurrent.locks.Lock r12 = r11.editLock
            r12.unlock()
        L95:
            return r0
        L96:
            if (r0 == 0) goto L9d
            java.util.concurrent.locks.Lock r13 = r11.editLock
            r13.unlock()
        L9d:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.MessageQueue.push(io.nats.client.impl.NatsMessage, boolean):boolean");
    }

    public MessageQueue(boolean z, int i, boolean z2, Duration duration) {
        this(z, i, z2, duration, null);
    }

    public MessageQueue(boolean z, int i, boolean z2, Duration duration, MessageQueue messageQueue) {
        this.maxMessagesInOutgoingQueue = i;
        this.queue = i > 0 ? new LinkedBlockingQueue<>(i) : new LinkedBlockingQueue<>();
        this.discardWhenFull = z2;
        this.running = new AtomicInteger(1);
        this.sizeInBytes = new AtomicLong(0L);
        this.length = new AtomicLong(0L);
        this.offerLockNanos = duration.toNanos();
        this.offerTimeoutNanos = Math.max(MIN_OFFER_TIMEOUT_NANOS, (duration.toMillis() * 95000000) / 100);
        this.editLock = new ReentrantLock();
        this.singleReaderMode = z;
        this.requestCleanupInterval = duration;
        if (messageQueue != null) {
            messageQueue.drainTo(this);
        }
    }
}
