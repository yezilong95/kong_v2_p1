package io.nats.service;

import defpackage.fb;
import defpackage.zn;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.NUID;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.Validator;
import io.nats.service.ServiceMessage;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class Service {
    public static final String DEFAULT_SERVICE_PREFIX = "$SRV.";
    public static final String SRV_INFO = "INFO";
    public static final String SRV_PING = "PING";
    public static final String SRV_STATS = "STATS";
    private final Connection conn;
    private final List<Dispatcher> dInternals;
    private final List<EndpointContext> discoveryContexts;
    private final Duration drainTimeout;
    private final InfoResponse infoResponse;
    private final PingResponse pingResponse;
    private CompletableFuture<Boolean> runningIndicator;
    private final ConcurrentHashMap<String, EndpointContext> serviceContexts;
    private final ReentrantLock startStopLock;
    private ZonedDateTime started;

    public Service(ServiceBuilder serviceBuilder) {
        Dispatcher dispatcherCreateDispatcher;
        String next = new NUID().next();
        Connection connection = serviceBuilder.conn;
        this.conn = connection;
        this.drainTimeout = serviceBuilder.drainTimeout;
        ArrayList arrayList = new ArrayList();
        this.dInternals = arrayList;
        this.startStopLock = new ReentrantLock();
        PingResponse pingResponse = new PingResponse(next, serviceBuilder.name, serviceBuilder.version, serviceBuilder.metadata);
        this.pingResponse = pingResponse;
        InfoResponse infoResponse = new InfoResponse(next, serviceBuilder.name, serviceBuilder.version, serviceBuilder.metadata, serviceBuilder.description);
        this.infoResponse = infoResponse;
        this.serviceContexts = new ConcurrentHashMap<>();
        addServiceEndpoints(serviceBuilder.serviceEndpoints.values());
        if (serviceBuilder.pingDispatcher == null || serviceBuilder.infoDispatcher == null || serviceBuilder.statsDispatcher == null) {
            dispatcherCreateDispatcher = connection.createDispatcher();
            arrayList.add(dispatcherCreateDispatcher);
        } else {
            dispatcherCreateDispatcher = null;
        }
        this.discoveryContexts = new ArrayList();
        addDiscoveryContexts("PING", pingResponse, serviceBuilder.pingDispatcher, dispatcherCreateDispatcher);
        addDiscoveryContexts("INFO", infoResponse, serviceBuilder.infoDispatcher, dispatcherCreateDispatcher);
        addStatsContexts(serviceBuilder.statsDispatcher, dispatcherCreateDispatcher);
    }

    private void addDiscoveryContexts(String str, Dispatcher dispatcher, Dispatcher dispatcher2, ServiceMessageHandler serviceMessageHandler) {
        Endpoint[] endpointArr = {internalEndpoint(str, null, null), internalEndpoint(str, this.pingResponse.getName(), null), internalEndpoint(str, this.pingResponse.getName(), this.pingResponse.getId())};
        for (int i = 0; i < 3; i++) {
            this.discoveryContexts.add(new EndpointContext(this.conn, dispatcher2, true, new ServiceEndpoint(endpointArr[i], serviceMessageHandler, dispatcher)));
        }
    }

    private void addStatsContexts(Dispatcher dispatcher, Dispatcher dispatcher2) {
        addDiscoveryContexts(SRV_STATS, dispatcher, dispatcher2, new fb(7, this));
    }

    public static ServiceBuilder builder() {
        return new ServiceBuilder();
    }

    private Endpoint internalEndpoint(String str, String str2, String str3) {
        String discoverySubject = toDiscoverySubject(str, str2, str3);
        return new Endpoint(discoverySubject, discoverySubject, null, null, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addDiscoveryContexts$0(ServiceResponse serviceResponse, ServiceMessage serviceMessage) {
        serviceMessage.respond(this.conn, serviceResponse.serialize());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addStatsContexts$1(ServiceMessage serviceMessage) {
        serviceMessage.respond(this.conn, getStatsResponse().serialize());
    }

    public static String toDiscoverySubject(String str, String str2, String str3) {
        if (Validator.nullOrEmpty(str3)) {
            if (Validator.nullOrEmpty(str2)) {
                return zn.v(DEFAULT_SERVICE_PREFIX, str);
            }
            return DEFAULT_SERVICE_PREFIX + str + NatsConstants.DOT + str2;
        }
        return DEFAULT_SERVICE_PREFIX + str + NatsConstants.DOT + str2 + NatsConstants.DOT + str3;
    }

    public void addServiceEndpoints(ServiceEndpoint... serviceEndpointArr) {
        addServiceEndpoints(Arrays.asList(serviceEndpointArr));
    }

    public String getDescription() {
        return this.infoResponse.getDescription();
    }

    public Duration getDrainTimeout() {
        return this.drainTimeout;
    }

    public EndpointStats getEndpointStats(String str) {
        EndpointContext endpointContext = this.serviceContexts.get(str);
        if (endpointContext == null) {
            return null;
        }
        return endpointContext.getEndpointStats();
    }

    public String getId() {
        return this.infoResponse.getId();
    }

    public InfoResponse getInfoResponse() {
        return this.infoResponse;
    }

    public String getName() {
        return this.infoResponse.getName();
    }

    public PingResponse getPingResponse() {
        return this.pingResponse;
    }

    public StatsResponse getStatsResponse() {
        ArrayList arrayList = new ArrayList();
        Iterator<EndpointContext> it = this.serviceContexts.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getEndpointStats());
        }
        return new StatsResponse(this.pingResponse, this.started, arrayList);
    }

    public String getVersion() {
        return this.infoResponse.getVersion();
    }

    public void reset() {
        this.started = DateTimeUtils.gmtNow();
        Iterator<EndpointContext> it = this.discoveryContexts.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
        Iterator<EndpointContext> it2 = this.serviceContexts.values().iterator();
        while (it2.hasNext()) {
            it2.next().reset();
        }
    }

    public CompletableFuture<Boolean> startService() {
        this.startStopLock.lock();
        try {
            if (this.runningIndicator == null) {
                this.runningIndicator = new CompletableFuture<>();
                Iterator<EndpointContext> it = this.serviceContexts.values().iterator();
                while (it.hasNext()) {
                    it.next().start();
                }
                Iterator<EndpointContext> it2 = this.discoveryContexts.iterator();
                while (it2.hasNext()) {
                    it2.next().start();
                }
                this.started = DateTimeUtils.gmtNow();
            }
            CompletableFuture<Boolean> completableFuture = this.runningIndicator;
            this.startStopLock.unlock();
            return completableFuture;
        } catch (Throwable th) {
            this.startStopLock.unlock();
            throw th;
        }
    }

    public void stop() {
        stop(true, null);
    }

    public String toString() {
        StringBuilder sbBeginJsonPrefixed = JsonUtils.beginJsonPrefixed("\"Service\":");
        JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.ID, this.infoResponse.getId());
        JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.NAME, this.infoResponse.getName());
        JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.VERSION, this.infoResponse.getVersion());
        JsonUtils.addField(sbBeginJsonPrefixed, ApiConstants.DESCRIPTION, this.infoResponse.getDescription());
        return JsonUtils.endJson(sbBeginJsonPrefixed).toString();
    }

    public void addServiceEndpoints(Collection<ServiceEndpoint> collection) {
        EndpointContext endpointContext;
        this.startStopLock.lock();
        try {
            this.infoResponse.addServiceEndpoints(collection);
            for (ServiceEndpoint serviceEndpoint : collection) {
                Dispatcher dispatcherCreateDispatcher = null;
                if (serviceEndpoint.getDispatcher() == null) {
                    if (!this.dInternals.isEmpty()) {
                        dispatcherCreateDispatcher = this.dInternals.get(0);
                    }
                    if (dispatcherCreateDispatcher == null) {
                        dispatcherCreateDispatcher = this.conn.createDispatcher();
                        this.dInternals.add(dispatcherCreateDispatcher);
                    }
                    endpointContext = new EndpointContext(this.conn, dispatcherCreateDispatcher, false, serviceEndpoint);
                } else {
                    endpointContext = new EndpointContext(this.conn, null, false, serviceEndpoint);
                }
                this.serviceContexts.put(serviceEndpoint.getName(), endpointContext);
                if (this.runningIndicator != null) {
                    endpointContext.start();
                }
            }
            this.startStopLock.unlock();
        } catch (Throwable th) {
            this.startStopLock.unlock();
            throw th;
        }
    }

    public void stop(Throwable th) {
        stop(true, th);
    }

    public void stop(boolean z) {
        stop(z, null);
    }

    public void stop(boolean z, Throwable th) {
        this.startStopLock.lock();
        try {
            if (this.runningIndicator != null) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<Dispatcher> it = this.dInternals.iterator();
                    while (it.hasNext()) {
                        try {
                            arrayList.add(it.next().drain(this.drainTimeout));
                        } catch (Exception unused) {
                        }
                    }
                    for (EndpointContext endpointContext : this.serviceContexts.values()) {
                        if (endpointContext.isNotInternalDispatcher()) {
                            try {
                                arrayList.add(endpointContext.drain(this.drainTimeout));
                            } catch (Exception unused2) {
                            }
                        }
                    }
                    for (EndpointContext endpointContext2 : this.discoveryContexts) {
                        if (endpointContext2.isNotInternalDispatcher()) {
                            try {
                                arrayList.add(endpointContext2.drain(this.drainTimeout));
                            } catch (Exception unused3) {
                            }
                        }
                    }
                    long millis = this.drainTimeout.toMillis();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        try {
                            ((CompletableFuture) it2.next()).get(millis, TimeUnit.MILLISECONDS);
                        } catch (Exception unused4) {
                        }
                    }
                }
                Iterator<Dispatcher> it3 = this.dInternals.iterator();
                while (it3.hasNext()) {
                    this.conn.closeDispatcher(it3.next());
                }
                if (th == null) {
                    this.runningIndicator.complete(Boolean.TRUE);
                } else {
                    this.runningIndicator.completeExceptionally(th);
                }
                this.runningIndicator = null;
            }
        } finally {
            this.startStopLock.unlock();
        }
    }

    private void addDiscoveryContexts(String str, final ServiceResponse serviceResponse, Dispatcher dispatcher, Dispatcher dispatcher2) {
        addDiscoveryContexts(str, dispatcher, dispatcher2, new ServiceMessageHandler() { // from class: tu0
            @Override // io.nats.service.ServiceMessageHandler
            public final void onMessage(ServiceMessage serviceMessage) {
                this.E.lambda$addDiscoveryContexts$0(serviceResponse, serviceMessage);
            }
        });
    }
}
