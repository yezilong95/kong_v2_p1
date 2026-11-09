package io.nats.client.support;

import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.Options;
import java.time.Duration;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class NatsRequestCompletableFuture extends CompletableFuture<Message> {
    private static final String CANCEL_MESSAGE = "Future cancelled, response not registered in time, check connection status.";
    private static final String CLOSING_MESSAGE = "Future cancelled, connection closing.";
    private static final long DEFAULT_TIMEOUT_NANOS = Options.DEFAULT_REQUEST_CLEANUP_INTERVAL.toNanos();
    private static final long HYDRATION_TIME = 10000000;
    private final CancelAction cancelAction;
    private final long timeOutAfterNanoTime;
    private final boolean useTimeoutException;
    private boolean wasCancelledClosing;
    private boolean wasCancelledTimedOut;

    public enum CancelAction {
        CANCEL,
        REPORT,
        COMPLETE
    }

    public NatsRequestCompletableFuture(CancelAction cancelAction, Duration duration, boolean z) {
        this.cancelAction = cancelAction;
        this.timeOutAfterNanoTime = NatsSystemClock.nanoTime() + HYDRATION_TIME + (duration == null ? DEFAULT_TIMEOUT_NANOS : duration.toNanos());
        this.useTimeoutException = z;
    }

    public void cancelClosing() {
        this.wasCancelledClosing = true;
        completeExceptionally(new CancellationException(CLOSING_MESSAGE));
    }

    public void cancelTimedOut() {
        this.wasCancelledTimedOut = true;
        completeExceptionally(this.useTimeoutException ? new TimeoutException(CANCEL_MESSAGE) : new CancellationException(CANCEL_MESSAGE));
    }

    public CancelAction getCancelAction() {
        return this.cancelAction;
    }

    public boolean hasExceededTimeout() {
        return NatsSystemClock.nanoTime() > this.timeOutAfterNanoTime;
    }

    public boolean useTimeoutException() {
        return this.useTimeoutException;
    }

    public boolean wasCancelledClosing() {
        return this.wasCancelledClosing;
    }

    public boolean wasCancelledTimedOut() {
        return this.wasCancelledTimedOut;
    }
}
