package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.Statistics;
import io.nats.client.StatisticsCollector;
import java.text.NumberFormat;
import java.util.LongSummaryStatistics;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
class NatsStatistics implements Statistics, StatisticsCollector {
    private boolean trackAdvanced;
    private final ReentrantLock readStatsLock = new ReentrantLock();
    private final ReentrantLock writeStatsLock = new ReentrantLock();
    private LongSummaryStatistics readStats = new LongSummaryStatistics();
    private LongSummaryStatistics writeStats = new LongSummaryStatistics();
    private AtomicLong flushCounter = new AtomicLong();
    private AtomicLong outstandingRequests = new AtomicLong();
    private AtomicLong requestsSent = new AtomicLong();
    private AtomicLong repliesReceived = new AtomicLong();
    private AtomicLong duplicateRepliesReceived = new AtomicLong();
    private AtomicLong orphanRepliesReceived = new AtomicLong();
    private AtomicLong reconnects = new AtomicLong();
    private AtomicLong inMsgs = new AtomicLong();
    private AtomicLong outMsgs = new AtomicLong();
    private AtomicLong inBytes = new AtomicLong();
    private AtomicLong outBytes = new AtomicLong();
    private AtomicLong pingCount = new AtomicLong();
    private AtomicLong okCount = new AtomicLong();
    private AtomicLong errCount = new AtomicLong();
    private AtomicLong exceptionCount = new AtomicLong();
    private AtomicLong droppedCount = new AtomicLong();

    public void appendNumberStat(StringBuilder sb, String str, long j) {
        sb.append(str);
        sb.append(NumberFormat.getNumberInstance().format(j));
        sb.append("\n");
    }

    @Override // io.nats.client.StatisticsCollector
    public void decrementOutstandingRequests() {
        this.outstandingRequests.decrementAndGet();
    }

    @Override // io.nats.client.Statistics
    public long getDroppedCount() {
        return this.droppedCount.get();
    }

    @Override // io.nats.client.Statistics
    public long getDuplicateRepliesReceived() {
        return this.duplicateRepliesReceived.get();
    }

    @Override // io.nats.client.Statistics
    public long getErrs() {
        return this.errCount.get();
    }

    @Override // io.nats.client.Statistics
    public long getExceptions() {
        return this.exceptionCount.get();
    }

    @Override // io.nats.client.Statistics
    public long getFlushCounter() {
        return this.flushCounter.get();
    }

    @Override // io.nats.client.Statistics
    public long getInBytes() {
        return this.inBytes.get();
    }

    @Override // io.nats.client.Statistics
    public long getInMsgs() {
        return this.inMsgs.get();
    }

    @Override // io.nats.client.Statistics
    public long getOKs() {
        return this.okCount.get();
    }

    @Override // io.nats.client.Statistics
    public long getOrphanRepliesReceived() {
        return this.orphanRepliesReceived.get();
    }

    @Override // io.nats.client.Statistics
    public long getOutBytes() {
        return this.outBytes.get();
    }

    @Override // io.nats.client.Statistics
    public long getOutMsgs() {
        return this.outMsgs.get();
    }

    @Override // io.nats.client.Statistics
    public long getOutstandingRequests() {
        return this.outstandingRequests.get();
    }

    @Override // io.nats.client.Statistics
    public long getPings() {
        return this.pingCount.get();
    }

    @Override // io.nats.client.Statistics
    public long getReconnects() {
        return this.reconnects.get();
    }

    @Override // io.nats.client.Statistics
    public long getRepliesReceived() {
        return this.repliesReceived.get();
    }

    @Override // io.nats.client.Statistics
    public long getRequestsSent() {
        return this.requestsSent.get();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementDroppedCount() {
        this.droppedCount.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementDuplicateRepliesReceived() {
        this.duplicateRepliesReceived.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementErrCount() {
        this.errCount.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementExceptionCount() {
        this.exceptionCount.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementFlushCounter() {
        this.flushCounter.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementInBytes(long j) {
        this.inBytes.addAndGet(j);
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementInMsgs() {
        this.inMsgs.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementOkCount() {
        this.okCount.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementOrphanRepliesReceived() {
        this.orphanRepliesReceived.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementOutBytes(long j) {
        this.outBytes.addAndGet(j);
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementOutMsgs() {
        this.outMsgs.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementOutstandingRequests() {
        this.outstandingRequests.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementPingCount() {
        this.pingCount.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementReconnects() {
        this.reconnects.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementRepliesReceived() {
        this.repliesReceived.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void incrementRequestsSent() {
        this.requestsSent.incrementAndGet();
    }

    @Override // io.nats.client.StatisticsCollector
    public void registerRead(long j) {
        if (this.trackAdvanced) {
            this.readStatsLock.lock();
            try {
                this.readStats.accept(j);
            } finally {
                this.readStatsLock.unlock();
            }
        }
    }

    @Override // io.nats.client.StatisticsCollector
    public void registerWrite(long j) {
        if (this.trackAdvanced) {
            this.writeStatsLock.lock();
            try {
                this.writeStats.accept(j);
            } finally {
                this.writeStatsLock.unlock();
            }
        }
    }

    @Override // io.nats.client.StatisticsCollector
    public void setAdvancedTracking(boolean z) {
        this.trackAdvanced = z;
    }

    public String toString() {
        StringBuilder sbP = zn.p("### Connection ###\n");
        appendNumberStat(sbP, "Reconnects:                      ", this.reconnects.get());
        appendNumberStat(sbP, "Requests Sent:                   ", this.requestsSent.get());
        appendNumberStat(sbP, "Replies Received:                ", this.repliesReceived.get());
        if (this.trackAdvanced) {
            appendNumberStat(sbP, "Duplicate Replies Received:      ", this.duplicateRepliesReceived.get());
            appendNumberStat(sbP, "Orphan Replies Received:         ", this.orphanRepliesReceived.get());
        }
        appendNumberStat(sbP, "Pings Sent:                      ", this.pingCount.get());
        appendNumberStat(sbP, "+OKs Received:                   ", this.okCount.get());
        appendNumberStat(sbP, "-Errs Received:                  ", this.errCount.get());
        appendNumberStat(sbP, "Handled Exceptions:              ", this.exceptionCount.get());
        appendNumberStat(sbP, "Successful Flush Calls:          ", this.flushCounter.get());
        appendNumberStat(sbP, "Outstanding Request Futures:     ", this.outstandingRequests.get());
        appendNumberStat(sbP, "Dropped Messages:                ", this.droppedCount.get());
        sbP.append("\n");
        sbP.append("### Reader ###\n");
        appendNumberStat(sbP, "Messages in:                     ", this.inMsgs.get());
        appendNumberStat(sbP, "Bytes in:                        ", this.inBytes.get());
        sbP.append("\n");
        if (this.trackAdvanced) {
            this.readStatsLock.lock();
            try {
                appendNumberStat(sbP, "Socket Reads:                    ", this.readStats.getCount());
                appendNumberStat(sbP, "Average Bytes Per Read:          ", this.readStats.getAverage());
                appendNumberStat(sbP, "Min Bytes Per Read:              ", this.readStats.getMin());
                appendNumberStat(sbP, "Max Bytes Per Read:              ", this.readStats.getMax());
            } finally {
                this.readStatsLock.unlock();
            }
        }
        sbP.append("\n");
        sbP.append("### Writer ###\n");
        appendNumberStat(sbP, "Messages out:                    ", this.outMsgs.get());
        appendNumberStat(sbP, "Bytes out:                       ", this.outBytes.get());
        sbP.append("\n");
        if (this.trackAdvanced) {
            this.writeStatsLock.lock();
            try {
                appendNumberStat(sbP, "Socket Writes:                   ", this.writeStats.getCount());
                appendNumberStat(sbP, "Average Bytes Per Write:         ", this.writeStats.getAverage());
                appendNumberStat(sbP, "Min Bytes Per Write:             ", this.writeStats.getMin());
                appendNumberStat(sbP, "Max Bytes Per Write:             ", this.writeStats.getMax());
            } finally {
                this.writeStatsLock.unlock();
            }
        }
        return sbP.toString();
    }

    public void appendNumberStat(StringBuilder sb, String str, double d) {
        sb.append(str);
        sb.append(NumberFormat.getNumberInstance().format(d));
        sb.append("\n");
    }
}
