package io.nats.client.api;

import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public class KeyValuePurgeOptions {
    public static long DEFAULT_THRESHOLD_MILLIS = Duration.ofMinutes(30).toMillis();
    private final long deleteMarkersThresholdMillis;

    public static Builder builder() {
        return new Builder();
    }

    public long getDeleteMarkersThresholdMillis() {
        return this.deleteMarkersThresholdMillis;
    }

    public static class Builder {
        private long deleteMarkersThresholdMillis = KeyValuePurgeOptions.DEFAULT_THRESHOLD_MILLIS;

        public KeyValuePurgeOptions build() {
            long j = this.deleteMarkersThresholdMillis;
            if (j < 0) {
                this.deleteMarkersThresholdMillis = -1L;
            } else if (j == 0) {
                this.deleteMarkersThresholdMillis = KeyValuePurgeOptions.DEFAULT_THRESHOLD_MILLIS;
            }
            return new KeyValuePurgeOptions(this);
        }

        public Builder deleteMarkersNoThreshold() {
            this.deleteMarkersThresholdMillis = -1L;
            return this;
        }

        public Builder deleteMarkersThreshold(Duration duration) {
            this.deleteMarkersThresholdMillis = duration == null ? KeyValuePurgeOptions.DEFAULT_THRESHOLD_MILLIS : duration.toMillis();
            return this;
        }

        public Builder deleteMarkersThreshold(long j) {
            this.deleteMarkersThresholdMillis = j;
            return this;
        }
    }

    private KeyValuePurgeOptions(Builder builder) {
        this.deleteMarkersThresholdMillis = builder.deleteMarkersThresholdMillis;
    }
}
