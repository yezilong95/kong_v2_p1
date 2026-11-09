package io.nats.client;

import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Validator;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public class JetStreamOptions {
    private final boolean defaultPrefix;
    private final String jsPrefix;
    private final boolean optOut290ConsumerCreate;
    private final boolean publishNoAck;
    private final Duration requestTimeout;

    @Deprecated
    public static final Duration DEFAULT_TIMEOUT = Options.DEFAULT_CONNECTION_TIMEOUT;
    public static final JetStreamOptions DEFAULT_JS_OPTIONS = new Builder().build();

    public static class Builder {
        private String jsPrefix;
        private boolean optOut290ConsumerCreate;
        private boolean publishNoAck;
        private Duration requestTimeout;

        public Builder() {
        }

        public JetStreamOptions build() {
            return new JetStreamOptions(this);
        }

        public Builder domain(String str) {
            String strConvertDomainToPrefix = JetStreamOptions.convertDomainToPrefix(str);
            this.jsPrefix = strConvertDomainToPrefix == null ? null : strConvertDomainToPrefix.concat(NatsConstants.DOT);
            return this;
        }

        public Builder optOut290ConsumerCreate(boolean z) {
            this.optOut290ConsumerCreate = z;
            return this;
        }

        public Builder prefix(String str) {
            this.jsPrefix = Validator.ensureEndsWithDot(Validator.validatePrefixOrDomain(str, "Prefix", false));
            return this;
        }

        public Builder publishNoAck(boolean z) {
            this.publishNoAck = z;
            return this;
        }

        public Builder requestTimeout(Duration duration) {
            this.requestTimeout = duration;
            return this;
        }

        public Builder(JetStreamOptions jetStreamOptions) {
            if (jetStreamOptions != null) {
                if (jetStreamOptions.isDefaultPrefix()) {
                    this.jsPrefix = null;
                } else {
                    this.jsPrefix = jetStreamOptions.jsPrefix;
                }
                this.requestTimeout = jetStreamOptions.requestTimeout;
                this.publishNoAck = jetStreamOptions.publishNoAck;
                this.optOut290ConsumerCreate = jetStreamOptions.optOut290ConsumerCreate;
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static String convertDomainToPrefix(String str) {
        String strValidatePrefixOrDomain = Validator.validatePrefixOrDomain(str, "Domain", false);
        if (strValidatePrefixOrDomain == null) {
            return null;
        }
        return NatsJetStreamConstants.PREFIX_DOLLAR_JS_DOT + Validator.ensureEndsWithDot(strValidatePrefixOrDomain) + NatsJetStreamConstants.PREFIX_API;
    }

    public static JetStreamOptions defaultOptions() {
        return DEFAULT_JS_OPTIONS;
    }

    public String getPrefix() {
        return this.jsPrefix;
    }

    public Duration getRequestTimeout() {
        return this.requestTimeout;
    }

    public boolean isDefaultPrefix() {
        return this.defaultPrefix;
    }

    public boolean isOptOut290ConsumerCreate() {
        return this.optOut290ConsumerCreate;
    }

    public boolean isPublishNoAck() {
        return this.publishNoAck;
    }

    private JetStreamOptions(Builder builder) {
        if (builder.jsPrefix == null) {
            this.defaultPrefix = true;
            this.jsPrefix = NatsJetStreamConstants.DEFAULT_API_PREFIX;
        } else {
            this.defaultPrefix = false;
            this.jsPrefix = builder.jsPrefix;
        }
        this.requestTimeout = builder.requestTimeout;
        this.publishNoAck = builder.publishNoAck;
        this.optOut290ConsumerCreate = builder.optOut290ConsumerCreate;
    }

    public static Builder builder(JetStreamOptions jetStreamOptions) {
        return new Builder(jetStreamOptions);
    }
}
