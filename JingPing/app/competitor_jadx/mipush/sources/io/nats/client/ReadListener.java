package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public interface ReadListener {
    void message(String str, Message message);

    void protocol(String str, String str2);
}
