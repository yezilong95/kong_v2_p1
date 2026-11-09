package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public interface StatisticsCollector extends Statistics {
    void decrementOutstandingRequests();

    void incrementDroppedCount();

    void incrementDuplicateRepliesReceived();

    void incrementErrCount();

    void incrementExceptionCount();

    void incrementFlushCounter();

    void incrementInBytes(long j);

    void incrementInMsgs();

    void incrementOkCount();

    void incrementOrphanRepliesReceived();

    void incrementOutBytes(long j);

    void incrementOutMsgs();

    void incrementOutstandingRequests();

    void incrementPingCount();

    void incrementReconnects();

    void incrementRepliesReceived();

    void incrementRequestsSent();

    void registerRead(long j);

    void registerWrite(long j);

    void setAdvancedTracking(boolean z);
}
