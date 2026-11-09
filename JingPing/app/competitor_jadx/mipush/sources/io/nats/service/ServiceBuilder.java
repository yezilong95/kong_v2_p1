package io.nats.service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ServiceBuilder {
    public static final Duration DEFAULT_DRAIN_TIMEOUT = Duration.ofMillis(5000);
    public static final long DEFAULT_DRAIN_TIMEOUT_MILLIS = 5000;
    Connection conn;
    String description;
    Dispatcher infoDispatcher;
    Map<String, String> metadata;
    String name;
    Dispatcher pingDispatcher;
    Dispatcher statsDispatcher;
    String version;
    final Map<String, ServiceEndpoint> serviceEndpoints = new HashMap();
    Duration drainTimeout = DEFAULT_DRAIN_TIMEOUT;

    public ServiceBuilder addServiceEndpoint(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoints.put(serviceEndpoint.getName(), serviceEndpoint);
        return this;
    }

    public Service build() {
        Validator.required(this.conn, "Connection");
        Validator.required(this.name, "Name");
        Validator.required(this.version, "Version");
        return new Service(this);
    }

    public ServiceBuilder connection(Connection connection) {
        this.conn = connection;
        return this;
    }

    public ServiceBuilder description(String str) {
        this.description = str;
        return this;
    }

    public ServiceBuilder drainTimeout(Duration duration) {
        if (duration == null) {
            duration = DEFAULT_DRAIN_TIMEOUT;
        }
        this.drainTimeout = duration;
        return this;
    }

    public ServiceBuilder infoDispatcher(Dispatcher dispatcher) {
        this.infoDispatcher = dispatcher;
        return this;
    }

    public ServiceBuilder metadata(Map<String, String> map) {
        this.metadata = map;
        return this;
    }

    public ServiceBuilder name(String str) {
        this.name = Validator.validateIsRestrictedTerm(str, "Service Name", true);
        return this;
    }

    public ServiceBuilder pingDispatcher(Dispatcher dispatcher) {
        this.pingDispatcher = dispatcher;
        return this;
    }

    @Deprecated
    public ServiceBuilder schemaDispatcher(Dispatcher dispatcher) {
        return this;
    }

    public ServiceBuilder statsDispatcher(Dispatcher dispatcher) {
        this.statsDispatcher = dispatcher;
        return this;
    }

    public ServiceBuilder version(String str) {
        this.version = Validator.validateSemVer(str, "Service Version", true);
        return this;
    }

    public ServiceBuilder drainTimeout(long j) {
        this.drainTimeout = Duration.ofMillis(j);
        return this;
    }
}
