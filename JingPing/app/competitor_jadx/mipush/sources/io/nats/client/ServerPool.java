package io.nats.client;

import io.nats.client.support.NatsUri;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ServerPool {
    boolean acceptDiscoveredUrls(List<String> list);

    void connectFailed(NatsUri natsUri);

    void connectSucceeded(NatsUri natsUri);

    List<String> getServerList();

    boolean hasSecureServer();

    void initialize(Options options);

    NatsUri nextServer();

    NatsUri peekNextServer();

    List<String> resolveHostToIps(String str);
}
