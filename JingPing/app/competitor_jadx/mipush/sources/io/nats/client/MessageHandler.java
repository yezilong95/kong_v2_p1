package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public interface MessageHandler {
    void onMessage(Message message) throws InterruptedException;
}
