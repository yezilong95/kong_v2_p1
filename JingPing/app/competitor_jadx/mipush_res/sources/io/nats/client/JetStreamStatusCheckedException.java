package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public class JetStreamStatusCheckedException extends Exception {
    public JetStreamStatusCheckedException(JetStreamStatusException jetStreamStatusException) {
        super(jetStreamStatusException);
    }
}
