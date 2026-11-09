package io.nats.client.api;

import io.nats.client.api.FeatureConfiguration;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.NatsKeyValueUtil;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class KeyValueConfiguration extends FeatureConfiguration {

    public static class Builder extends FeatureConfiguration.Builder<Builder, KeyValueConfiguration> {
        Duration limitMarkerTtl;
        Mirror mirror;
        final List<Source> sources;

        public Builder() {
            this((KeyValueConfiguration) null);
        }

        public Builder addSource(Source source) {
            if (source != null && !this.sources.contains(source)) {
                this.sources.add(source);
            }
            return this;
        }

        public Builder addSources(Source... sourceArr) {
            return sourceArr != null ? addSources(Arrays.asList(sourceArr)) : this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public Builder getThis() {
            return this;
        }

        public Builder limitMarker(Duration duration) {
            this.limitMarkerTtl = Validator.validateDurationNotRequiredGtOrEqSeconds(1L, duration, null, "Limit Marker Ttl");
            return this;
        }

        public Builder maxHistoryPerKey(int i) {
            this.scBuilder.maxMessagesPerSubject(Validator.validateMaxHistory(i));
            return this;
        }

        @Deprecated
        public Builder maxValueSize(long j) {
            this.scBuilder.maximumMessageSize((int) Validator.validateMaxValueSize(j));
            return this;
        }

        public Builder maximumValueSize(int i) {
            this.scBuilder.maximumMessageSize((int) Validator.validateMaxValueSize(i));
            return this;
        }

        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public /* bridge */ /* synthetic */ Builder metadata(Map map) {
            return metadata((Map<String, String>) map);
        }

        public Builder mirror(Mirror mirror) {
            this.mirror = mirror;
            return this;
        }

        public Builder republish(Republish republish) {
            this.scBuilder.republish(republish);
            return this;
        }

        public Builder sources(Source... sourceArr) {
            this.sources.clear();
            return addSources(sourceArr);
        }

        public Builder(String str) {
            this((KeyValueConfiguration) null);
            name(str);
        }

        public Builder addSources(Collection<Source> collection) {
            if (collection != null) {
                for (Source source : collection) {
                    if (source != null && !this.sources.contains(source)) {
                        this.sources.add(source);
                    }
                }
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.FeatureConfiguration.Builder
        public KeyValueConfiguration build() {
            String strRequired = Validator.required(this.name, ApiConstants.NAME);
            this.name = strRequired;
            this.scBuilder.name(NatsKeyValueUtil.toStreamName(strRequired)).allowRollup(true).allowDirect(true).discardPolicy(DiscardPolicy.New).denyDelete(true);
            if (this.mirror != null) {
                this.scBuilder.mirrorDirect(true);
                String name = this.mirror.getName();
                if (NatsKeyValueUtil.hasPrefix(name)) {
                    this.scBuilder.mirror(this.mirror);
                } else {
                    this.scBuilder.mirror(Mirror.builder(this.mirror).name(NatsKeyValueUtil.toStreamName(name)).build());
                }
            } else if (this.sources.isEmpty()) {
                this.scBuilder.subjects(NatsKeyValueUtil.toStreamSubject(this.name));
            } else {
                for (Source source : this.sources) {
                    String name2 = source.getName();
                    if (NatsKeyValueUtil.hasPrefix(name2)) {
                        this.scBuilder.addSource(source);
                    } else {
                        this.scBuilder.addSource(Source.builder(source).name(NatsKeyValueUtil.toStreamName(name2)).build());
                    }
                }
            }
            Duration duration = this.limitMarkerTtl;
            if (duration != null) {
                this.scBuilder.subjectDeleteMarkerTtl(duration).allowMessageTtl();
            }
            long millis = this.ttl.toMillis();
            if (millis <= 0 || millis >= NatsJetStreamConstants.SERVER_DEFAULT_DUPLICATE_WINDOW_MS) {
                millis = 120000;
            }
            this.scBuilder.duplicateWindow(millis);
            return new KeyValueConfiguration(this.scBuilder.build());
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

        public Builder limitMarker(long j) {
            if (j <= 0) {
                this.limitMarkerTtl = null;
                return this;
            }
            this.limitMarkerTtl = Validator.validateDurationGtOrEqSeconds(1L, j, "Limit Marker Ttl");
            return this;
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

        public Builder sources(Collection<Source> collection) {
            this.sources.clear();
            return addSources(collection);
        }

        public Builder(KeyValueConfiguration keyValueConfiguration) {
            this.sources = new ArrayList();
            if (keyValueConfiguration == null) {
                this.scBuilder = new StreamConfiguration.Builder();
                maxHistoryPerKey(1);
                replicas(1);
            } else {
                this.scBuilder = new StreamConfiguration.Builder(keyValueConfiguration.sc);
                this.name = NatsKeyValueUtil.extractBucketName(keyValueConfiguration.sc.getName());
            }
        }
    }

    public KeyValueConfiguration(StreamConfiguration streamConfiguration) {
        super(streamConfiguration, NatsKeyValueUtil.extractBucketName(streamConfiguration.getName()));
    }

    public static Builder builder() {
        return new Builder((KeyValueConfiguration) null);
    }

    public Duration getLimitMarkerTtl() {
        return this.sc.getSubjectDeleteMarkerTtl();
    }

    public long getMaxHistoryPerKey() {
        return this.sc.getMaxMsgsPerSubject();
    }

    @Deprecated
    public long getMaxValueSize() {
        return this.sc.getMaximumMessageSize();
    }

    public int getMaximumValueSize() {
        return this.sc.getMaximumMessageSize();
    }

    public Mirror getMirror() {
        return this.sc.getMirror();
    }

    public Republish getRepublish() {
        return this.sc.getRepublish();
    }

    public List<Source> getSources() {
        return this.sc.getSources();
    }

    @Override // io.nats.client.api.FeatureConfiguration, io.nats.client.support.JsonSerializable
    public JsonValue toJsonValue() {
        JsonValueUtils.MapBuilder mapBuilder = new JsonValueUtils.MapBuilder(super.toJsonValue());
        mapBuilder.jv.mapOrder.remove("metaData");
        mapBuilder.put("maxHistoryPerKey", Long.valueOf(getMaxHistoryPerKey()));
        mapBuilder.put("maxValueSize", Long.valueOf(getMaxValueSize()));
        mapBuilder.put(ApiConstants.REPUBLISH, getRepublish());
        mapBuilder.put(ApiConstants.MIRROR, getMirror());
        mapBuilder.put(ApiConstants.SOURCES, getSources());
        mapBuilder.put("limitMarkerTtl", getLimitMarkerTtl());
        mapBuilder.jv.mapOrder.add("metaData");
        return mapBuilder.toJsonValue();
    }

    public String toString() {
        return "KeyValueConfiguration" + toJson();
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static Builder builder(KeyValueConfiguration keyValueConfiguration) {
        return new Builder(keyValueConfiguration);
    }
}
