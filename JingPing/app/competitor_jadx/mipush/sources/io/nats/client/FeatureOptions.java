package io.nats.client;

import io.nats.client.JetStreamOptions;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public abstract class FeatureOptions {
    private final JetStreamOptions jso;

    public FeatureOptions(Builder<?, ?> builder) {
        this.jso = ((Builder) builder).jsoBuilder.build();
    }

    public JetStreamOptions getJetStreamOptions() {
        return this.jso;
    }

    public static abstract class Builder<B, FO> {
        private JetStreamOptions.Builder jsoBuilder;

        public Builder() {
            this.jsoBuilder = JetStreamOptions.builder();
        }

        public abstract FO build();

        public abstract B getThis();

        public B jetStreamOptions(JetStreamOptions jetStreamOptions) {
            this.jsoBuilder = JetStreamOptions.builder(jetStreamOptions);
            return getThis();
        }

        public B jsDomain(String str) {
            this.jsoBuilder.domain(str);
            return getThis();
        }

        public B jsPrefix(String str) {
            this.jsoBuilder.prefix(str);
            return getThis();
        }

        public B jsRequestTimeout(Duration duration) {
            this.jsoBuilder.requestTimeout(duration);
            return getThis();
        }

        public Builder(FeatureOptions featureOptions) {
            if (featureOptions != null) {
                this.jsoBuilder = JetStreamOptions.builder(featureOptions.jso);
            } else {
                this.jsoBuilder = JetStreamOptions.builder();
            }
        }
    }
}
