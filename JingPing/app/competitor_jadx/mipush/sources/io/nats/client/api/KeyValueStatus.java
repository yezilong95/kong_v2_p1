package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValueUtils;
import java.time.Duration;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class KeyValueStatus {
    private final KeyValueConfiguration config;
    private final StreamInfo streamInfo;

    public KeyValueStatus(StreamInfo streamInfo) {
        this.streamInfo = streamInfo;
        this.config = new KeyValueConfiguration(streamInfo.getConfiguration());
    }

    public String getBackingStore() {
        return "JetStream";
    }

    public StreamInfo getBackingStreamInfo() {
        return this.streamInfo;
    }

    public String getBucketName() {
        return this.config.getBucketName();
    }

    public long getByteCount() {
        return this.streamInfo.getStreamState().getByteCount();
    }

    public KeyValueConfiguration getConfiguration() {
        return this.config;
    }

    public String getDescription() {
        return this.config.getDescription();
    }

    public long getEntryCount() {
        return this.streamInfo.getStreamState().getMsgCount();
    }

    public Duration getLimitMarkerTtl() {
        return this.streamInfo.getConfig().getSubjectDeleteMarkerTtl();
    }

    public long getMaxBucketSize() {
        return this.config.getMaxBucketSize();
    }

    public long getMaxHistoryPerKey() {
        return this.config.getMaxHistoryPerKey();
    }

    @Deprecated
    public long getMaxValueSize() {
        return this.config.getMaximumValueSize();
    }

    public int getMaximumValueSize() {
        return this.config.getMaximumValueSize();
    }

    public Map<String, String> getMetadata() {
        return this.config.getMetadata();
    }

    public Placement getPlacement() {
        return this.config.getPlacement();
    }

    public int getReplicas() {
        return this.config.getReplicas();
    }

    public Republish getRepublish() {
        return this.config.getRepublish();
    }

    public StorageType getStorageType() {
        return this.config.getStorageType();
    }

    public Duration getTtl() {
        return this.config.getTtl();
    }

    public boolean isCompressed() {
        return this.config.isCompressed();
    }

    public String toString() {
        JsonValueUtils.MapBuilder mapBuilder = new JsonValueUtils.MapBuilder();
        mapBuilder.put("entryCount", Long.valueOf(getEntryCount()));
        mapBuilder.put("byteCount", Long.valueOf(getByteCount()));
        mapBuilder.put(ApiConstants.CONFIG, this.config);
        return "KeyValueStatus" + mapBuilder.toJsonValue().toJson();
    }
}
