package io.nats.service;

import defpackage.to;
import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.Subscription;
import io.nats.client.support.NatsConstants;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class Discovery {
    public static final int DEFAULT_DISCOVERY_MAX_RESULTS = 10;
    public static final long DEFAULT_DISCOVERY_MAX_TIME_MILLIS = 5000;
    private final Connection conn;
    private Supplier<String> inboxSupplier;
    private final int maxResults;
    private final long maxTimeNanos;

    public Discovery(Connection connection) {
        this(connection, 0L, 0);
    }

    private void discoverMany(String str, String str2, Consumer<byte[]> consumer) throws Throwable {
        String str3;
        Subscription subscriptionSubscribe;
        Subscription subscription = null;
        try {
            try {
                str3 = this.inboxSupplier.get();
                subscriptionSubscribe = this.conn.subscribe(str3);
            } catch (InterruptedException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.conn.publish(Service.toDiscoverySubject(str, str2, null), str3, (byte[]) null);
            int i = this.maxResults;
            long jNanoTime = NatsSystemClock.nanoTime();
            long jNanoTime2 = this.maxTimeNanos;
            while (i > 0 && jNanoTime2 > 0) {
                Message messageNextMessage = subscriptionSubscribe.nextMessage(Duration.ofNanos(jNanoTime2));
                if (messageNextMessage != null) {
                    consumer.accept(messageNextMessage.getData());
                    i--;
                    jNanoTime2 = this.maxTimeNanos - (NatsSystemClock.nanoTime() - jNanoTime);
                }
            }
            try {
                subscriptionSubscribe.unsubscribe();
            } catch (Exception unused) {
            }
        } catch (InterruptedException e2) {
            e = e2;
            subscription = subscriptionSubscribe;
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (Throwable th2) {
            th = th2;
            subscription = subscriptionSubscribe;
            try {
                subscription.unsubscribe();
            } catch (Exception unused2) {
            }
            throw th;
        }
    }

    private byte[] discoverOne(String str, String str2, String str3) {
        try {
            Message messageRequest = this.conn.request(Service.toDiscoverySubject(str, str2, str3), (byte[]) null, Duration.ofNanos(this.maxTimeNanos));
            if (messageRequest != null) {
                return messageRequest.getData();
            }
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$info$1(List list, byte[] bArr) {
        list.add(new InfoResponse(bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$ping$0(List list, byte[] bArr) {
        list.add(new PingResponse(bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$stats$2(List list, byte[] bArr) {
        list.add(new StatsResponse(bArr));
    }

    public List<InfoResponse> info() {
        return info(null);
    }

    public List<PingResponse> ping() {
        return ping(null);
    }

    public void setInboxSupplier(Supplier<String> supplier) {
        if (supplier == null) {
            final Connection connection = this.conn;
            connection.getClass();
            supplier = new Supplier() { // from class: uo
                @Override // java.util.function.Supplier
                public final Object get() {
                    return connection.createInbox();
                }
            };
        }
        this.inboxSupplier = supplier;
    }

    public List<StatsResponse> stats() {
        return stats(null);
    }

    public Discovery(Connection connection, long j, int i) {
        this.conn = connection;
        this.maxTimeNanos = (j < 1 ? 5000L : j) * NatsConstants.NANOS_PER_MILLI;
        this.maxResults = i < 1 ? 10 : i;
        setInboxSupplier(null);
    }

    public List<InfoResponse> info(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        discoverMany("INFO", str, new to(1, arrayList));
        return arrayList;
    }

    public List<PingResponse> ping(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        discoverMany("PING", str, new to(2, arrayList));
        return arrayList;
    }

    public List<StatsResponse> stats(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        discoverMany(Service.SRV_STATS, str, new to(0, arrayList));
        return arrayList;
    }

    public InfoResponse info(String str, String str2) {
        byte[] bArrDiscoverOne = discoverOne("INFO", str, str2);
        if (bArrDiscoverOne == null) {
            return null;
        }
        return new InfoResponse(bArrDiscoverOne);
    }

    public PingResponse ping(String str, String str2) {
        byte[] bArrDiscoverOne = discoverOne("PING", str, str2);
        if (bArrDiscoverOne == null) {
            return null;
        }
        return new PingResponse(bArrDiscoverOne);
    }

    public StatsResponse stats(String str, String str2) {
        byte[] bArrDiscoverOne = discoverOne(Service.SRV_STATS, str, str2);
        if (bArrDiscoverOne == null) {
            return null;
        }
        return new StatsResponse(bArrDiscoverOne);
    }
}
