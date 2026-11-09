package io.nats.client.impl;

/* JADX INFO: loaded from: classes.dex */
interface PullManagerObserver {
    void heartbeatError();

    void pendingUpdated();
}
