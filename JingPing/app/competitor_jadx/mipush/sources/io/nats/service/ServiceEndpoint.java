package io.nats.service;

import io.nats.client.Dispatcher;
import io.nats.client.support.JsonValue;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.Validator;
import io.nats.service.Endpoint;
import java.util.Map;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class ServiceEndpoint {
    private final Dispatcher dispatcher;
    private final Endpoint endpoint;
    private final Group group;
    private final ServiceMessageHandler handler;
    private final Supplier<JsonValue> statsDataSupplier;

    public static class Builder {
        private Dispatcher dispatcher;
        private Endpoint.Builder endpointBuilder = Endpoint.builder();
        private Group group;
        private ServiceMessageHandler handler;
        private Supplier<JsonValue> statsDataSupplier;

        public ServiceEndpoint build() {
            Endpoint endpointBuild = this.endpointBuilder.build();
            Validator.required(this.handler, "Message Handler");
            return new ServiceEndpoint(this, endpointBuild);
        }

        public Builder dispatcher(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }

        public Builder endpoint(Endpoint endpoint) {
            this.endpointBuilder = Endpoint.builder().endpoint(endpoint);
            return this;
        }

        public Builder endpointMetadata(Map<String, String> map) {
            this.endpointBuilder.metadata(map);
            return this;
        }

        public Builder endpointName(String str) {
            this.endpointBuilder.name(str);
            return this;
        }

        public Builder endpointQueueGroup(String str) {
            this.endpointBuilder.queueGroup(str);
            return this;
        }

        public Builder endpointSubject(String str) {
            this.endpointBuilder.subject(str);
            return this;
        }

        public Builder group(Group group) {
            this.group = group;
            return this;
        }

        public Builder handler(ServiceMessageHandler serviceMessageHandler) {
            this.handler = serviceMessageHandler;
            return this;
        }

        public Builder statsDataSupplier(Supplier<JsonValue> supplier) {
            this.statsDataSupplier = supplier;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }

    public Group getGroup() {
        return this.group;
    }

    public String getGroupName() {
        Group group = this.group;
        if (group == null) {
            return null;
        }
        return group.getName();
    }

    public ServiceMessageHandler getHandler() {
        return this.handler;
    }

    public Map<String, String> getMetadata() {
        return this.endpoint.getMetadata();
    }

    public String getName() {
        return this.endpoint.getName();
    }

    public String getQueueGroup() {
        return this.endpoint.getQueueGroup();
    }

    public Supplier<JsonValue> getStatsDataSupplier() {
        return this.statsDataSupplier;
    }

    public String getSubject() {
        if (this.group == null) {
            return this.endpoint.getSubject();
        }
        return this.group.getSubject() + NatsConstants.DOT + this.endpoint.getSubject();
    }

    private ServiceEndpoint(Builder builder, Endpoint endpoint) {
        this.group = builder.group;
        this.endpoint = endpoint;
        this.handler = builder.handler;
        this.dispatcher = builder.dispatcher;
        this.statsDataSupplier = builder.statsDataSupplier;
    }

    public ServiceEndpoint(Endpoint endpoint, ServiceMessageHandler serviceMessageHandler, Dispatcher dispatcher) {
        this.group = null;
        this.endpoint = endpoint;
        this.handler = serviceMessageHandler;
        this.dispatcher = dispatcher;
        this.statsDataSupplier = null;
    }
}
