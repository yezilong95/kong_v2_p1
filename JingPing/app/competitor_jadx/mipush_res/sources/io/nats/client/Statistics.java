package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public interface Statistics {
    long getDroppedCount();

    long getDuplicateRepliesReceived();

    long getErrs();

    long getExceptions();

    long getFlushCounter();

    long getInBytes();

    long getInMsgs();

    long getOKs();

    long getOrphanRepliesReceived();

    long getOutBytes();

    long getOutMsgs();

    long getOutstandingRequests();

    long getPings();

    long getReconnects();

    long getRepliesReceived();

    long getRequestsSent();
}
