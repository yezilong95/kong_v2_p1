package io.nats.client;

import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
public class JetStreamStatusException extends IllegalStateException {
    public static final String DEFAULT_DESCRIPTION = "Unknown or unprocessed status message";
    private final Status status;
    private final JetStreamSubscription sub;

    public JetStreamStatusException(Status status, JetStreamSubscription jetStreamSubscription) {
        super(status == null ? DEFAULT_DESCRIPTION : status.getMessageWithCode());
        this.sub = jetStreamSubscription;
        this.status = status;
    }

    @Deprecated
    public String getDescription() {
        return getMessage();
    }

    public Status getStatus() {
        return this.status;
    }

    public JetStreamSubscription getSubscription() {
        return this.sub;
    }

    public JetStreamStatusException(Status status) {
        this(status, null);
    }
}
