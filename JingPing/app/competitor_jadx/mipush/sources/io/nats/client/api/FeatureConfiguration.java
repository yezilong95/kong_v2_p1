package io.nats.client.api;

import io.nats.client.api.StreamConfiguration;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class FeatureConfiguration implements JsonSerializable {
    protected final String bucketName;
    protected final StreamConfiguration sc;
    protected static final CompressionOption JS_COMPRESSION_YES = CompressionOption.S2;
    protected static final CompressionOption JS_COMPRESSION_NO = CompressionOption.None;

    public static abstract class Builder<B, FC> {
        protected String name;
        protected StreamConfiguration.Builder scBuilder;
        protected Duration ttl = Duration.ZERO;

        public abstract FC build();

        public B compression(boolean z) {
            this.scBuilder.compressionOption(z ? FeatureConfiguration.JS_COMPRESSION_YES : FeatureConfiguration.JS_COMPRESSION_NO);
            return getThis();
        }

        public B description(String str) {
            this.scBuilder.description(str);
            return getThis();
        }

        public abstract B getThis();

        public B maxBucketSize(long j) {
            this.scBuilder.maxBytes(Validator.validateMaxBucketBytes(j));
            return getThis();
        }

        public B metadata(Map<String, String> map) {
            this.scBuilder.metadata(map);
            return getThis();
        }

        public B name(String str) {
            this.name = Validator.validateBucketName(str, true);
            return getThis();
        }

        public B placement(Placement placement) {
            this.scBuilder.placement(placement);
            return getThis();
        }

        public B replicas(int i) {
            this.scBuilder.replicas(i);
            return getThis();
        }

        public B storageType(StorageType storageType) {
            this.scBuilder.storageType(storageType);
            return getThis();
        }

        public B ttl(Duration duration) {
            if (duration == null) {
                duration = Duration.ZERO;
            }
            this.ttl = duration;
            this.scBuilder.maxAge(duration);
            return getThis();
        }
    }

    public FeatureConfiguration(StreamConfiguration streamConfiguration, String str) {
        this.sc = streamConfiguration;
        this.bucketName = str;
    }

    public StreamConfiguration getBackingConfig() {
        return this.sc;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getDescription() {
        return this.sc.getDescription();
    }

    public long getMaxBucketSize() {
        return this.sc.getMaxBytes();
    }

    public Map<String, String> getMetadata() {
        return this.sc.getMetadata();
    }

    public Placement getPlacement() {
        return this.sc.getPlacement();
    }

    public int getReplicas() {
        return this.sc.getReplicas();
    }

    public StorageType getStorageType() {
        return this.sc.getStorageType();
    }

    public Duration getTtl() {
        return this.sc.getMaxAge();
    }

    public boolean isCompressed() {
        return this.sc.getCompressionOption() == JS_COMPRESSION_YES;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        return toJsonValue().toString();
    }

    @Override // io.nats.client.support.JsonSerializable
    public JsonValue toJsonValue() {
        JsonValueUtils.MapBuilder mapBuilder = new JsonValueUtils.MapBuilder();
        mapBuilder.put(ApiConstants.NAME, this.bucketName);
        mapBuilder.put(ApiConstants.DESCRIPTION, getDescription());
        mapBuilder.put("maxBucketSize", Long.valueOf(getMaxBucketSize()));
        mapBuilder.put("ttl", getTtl());
        mapBuilder.put("storageType", getStorageType());
        mapBuilder.put(ApiConstants.REPLICAS, Integer.valueOf(getReplicas()));
        mapBuilder.put(ApiConstants.PLACEMENT, getPlacement());
        mapBuilder.put("isCompressed", Boolean.valueOf(isCompressed()));
        mapBuilder.put("metaData", getMetadata());
        return mapBuilder.toJsonValue();
    }
}
