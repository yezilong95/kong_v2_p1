package io.nats.client;

import io.nats.client.api.ServerInfo;
import io.nats.client.impl.Headers;
import java.io.IOException;
import java.net.InetAddress;
import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public interface Connection extends AutoCloseable {

    public enum Status {
        DISCONNECTED,
        CONNECTED,
        CLOSED,
        RECONNECTING,
        CONNECTING
    }

    Duration RTT() throws IOException;

    void addConnectionListener(ConnectionListener connectionListener);

    void clearLastError();

    @Override // java.lang.AutoCloseable
    void close() throws InterruptedException;

    void closeDispatcher(Dispatcher dispatcher);

    Dispatcher createDispatcher();

    Dispatcher createDispatcher(MessageHandler messageHandler);

    String createInbox();

    CompletableFuture<Boolean> drain(Duration duration) throws InterruptedException, TimeoutException;

    void flush(Duration duration) throws InterruptedException, TimeoutException;

    void flushBuffer() throws IOException;

    void forceReconnect() throws InterruptedException, IOException;

    void forceReconnect(ForceReconnectOptions forceReconnectOptions) throws InterruptedException, IOException;

    InetAddress getClientInetAddress();

    String getConnectedUrl();

    ConsumerContext getConsumerContext(String str, String str2) throws IOException, JetStreamApiException;

    ConsumerContext getConsumerContext(String str, String str2, JetStreamOptions jetStreamOptions) throws IOException, JetStreamApiException;

    String getLastError();

    long getMaxPayload();

    Options getOptions();

    ServerInfo getServerInfo();

    Collection<String> getServers();

    Statistics getStatistics();

    Status getStatus();

    StreamContext getStreamContext(String str) throws IOException, JetStreamApiException;

    StreamContext getStreamContext(String str, JetStreamOptions jetStreamOptions) throws IOException, JetStreamApiException;

    JetStream jetStream() throws IOException;

    JetStream jetStream(JetStreamOptions jetStreamOptions) throws IOException;

    JetStreamManagement jetStreamManagement() throws IOException;

    JetStreamManagement jetStreamManagement(JetStreamOptions jetStreamOptions) throws IOException;

    KeyValue keyValue(String str) throws IOException;

    KeyValue keyValue(String str, KeyValueOptions keyValueOptions) throws IOException;

    KeyValueManagement keyValueManagement() throws IOException;

    KeyValueManagement keyValueManagement(KeyValueOptions keyValueOptions) throws IOException;

    ObjectStore objectStore(String str) throws IOException;

    ObjectStore objectStore(String str, ObjectStoreOptions objectStoreOptions) throws IOException;

    ObjectStoreManagement objectStoreManagement() throws IOException;

    ObjectStoreManagement objectStoreManagement(ObjectStoreOptions objectStoreOptions) throws IOException;

    void publish(Message message);

    void publish(String str, Headers headers, byte[] bArr);

    void publish(String str, String str2, Headers headers, byte[] bArr);

    void publish(String str, String str2, byte[] bArr);

    void publish(String str, byte[] bArr);

    void removeConnectionListener(ConnectionListener connectionListener);

    Message request(Message message, Duration duration) throws InterruptedException;

    Message request(String str, Headers headers, byte[] bArr, Duration duration) throws InterruptedException;

    Message request(String str, byte[] bArr, Duration duration) throws InterruptedException;

    CompletableFuture<Message> request(Message message);

    CompletableFuture<Message> request(String str, Headers headers, byte[] bArr);

    CompletableFuture<Message> request(String str, byte[] bArr);

    CompletableFuture<Message> requestWithTimeout(Message message, Duration duration);

    CompletableFuture<Message> requestWithTimeout(String str, Headers headers, byte[] bArr, Duration duration);

    CompletableFuture<Message> requestWithTimeout(String str, byte[] bArr, Duration duration);

    Subscription subscribe(String str);

    Subscription subscribe(String str, String str2);
}
