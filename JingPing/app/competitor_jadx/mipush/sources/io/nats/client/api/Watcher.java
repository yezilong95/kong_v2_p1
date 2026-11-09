package io.nats.client.api;

/* JADX INFO: loaded from: classes.dex */
public interface Watcher<T> {
    void endOfData();

    default String getConsumerNamePrefix() {
        return null;
    }

    void watch(T t);
}
