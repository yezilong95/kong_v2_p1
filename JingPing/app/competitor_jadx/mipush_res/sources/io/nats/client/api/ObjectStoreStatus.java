package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValueUtils;
import java.time.Duration;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ObjectStoreStatus {
    private final ObjectStoreConfiguration config;
    private final StreamInfo streamInfo;

    public ObjectStoreStatus(StreamInfo streamInfo) {
        this.streamInfo = streamInfo;
        this.config = new ObjectStoreConfiguration(streamInfo.getConfiguration());
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

    public ObjectStoreConfiguration getConfiguration() {
        return this.config;
    }

    public String getDescription() {
        return this.config.getDescription();
    }

    public long getMaxBucketSize() {
        return this.config.getMaxBucketSize();
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

    public long getSize() {
        return this.streamInfo.getStreamState().getByteCount();
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

    public boolean isSealed() {
        return this.config.isSealed();
    }

    public String toString() {
        JsonValueUtils.MapBuilder mapBuilder = new JsonValueUtils.MapBuilder();
        mapBuilder.put(ApiConstants.SIZE, Long.valueOf(getSize()));
        mapBuilder.put("isSealed", Boolean.valueOf(isSealed()));
        mapBuilder.put(ApiConstants.CONFIG, this.config);
        return "ObjectStoreStatus" + mapBuilder.toJsonValue().toJson();
    }
}
