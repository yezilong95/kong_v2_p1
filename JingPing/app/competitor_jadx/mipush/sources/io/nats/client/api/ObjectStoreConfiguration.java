package io.nats.client.api;

import io.nats.client.api.FeatureConfiguration;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.NatsObjectStoreUtil;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ObjectStoreConfiguration extends FeatureConfiguration {

    public static class Builder extends FeatureConfiguration.Builder<Builder, ObjectStoreConfiguration> {
        public Builder() {
            this((ObjectStoreConfiguration) null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder getThis() {
            return this;
        }

        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public /* bridge */ /* synthetic */ Builder metadata(Map map) {
            return metadata((Map<String, String>) map);
        }

        public Builder(String str) {
            this((ObjectStoreConfiguration) null);
            name(str);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public ObjectStoreConfiguration build() {
            String strRequired = Validator.required(this.name, ApiConstants.NAME);
            this.name = strRequired;
            this.scBuilder.name(NatsObjectStoreUtil.toStreamName(strRequired)).subjects(NatsObjectStoreUtil.toMetaStreamSubject(this.name), NatsObjectStoreUtil.toChunkStreamSubject(this.name)).allowRollup(true).allowDirect(true).discardPolicy(DiscardPolicy.New);
            return new ObjectStoreConfiguration(this.scBuilder.build());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder compression(boolean z) {
            return (Builder) super.compression(z);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder description(String str) {
            return (Builder) super.description(str);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder maxBucketSize(long j) {
            return (Builder) super.maxBucketSize(j);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder metadata(Map<String, String> map) {
            return (Builder) super.metadata(map);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder name(String str) {
            return (Builder) super.name(str);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder placement(Placement placement) {
            return (Builder) super.placement(placement);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder replicas(int i) {
            return (Builder) super.replicas(i);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder storageType(StorageType storageType) {
            return (Builder) super.storageType(storageType);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder ttl(Duration duration) {
            return (Builder) super.ttl(duration);
        }

        public Builder(ObjectStoreConfiguration objectStoreConfiguration) {
            if (objectStoreConfiguration == null) {
                this.scBuilder = new StreamConfiguration.Builder();
                replicas(1);
            } else {
                this.scBuilder = new StreamConfiguration.Builder(objectStoreConfiguration.sc);
                this.name = NatsObjectStoreUtil.extractBucketName(objectStoreConfiguration.sc.getName());
            }
        }
    }

    public ObjectStoreConfiguration(StreamConfiguration streamConfiguration) {
        super(streamConfiguration, NatsObjectStoreUtil.extractBucketName(streamConfiguration.getName()));
    }

    public static Builder builder() {
        return new Builder((ObjectStoreConfiguration) null);
    }

    public boolean isSealed() {
        return this.sc.getSealed();
    }

    public String toString() {
        return "ObjectStoreConfiguration" + toJson();
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static Builder builder(ObjectStoreConfiguration objectStoreConfiguration) {
        return new Builder(objectStoreConfiguration);
    }
}
