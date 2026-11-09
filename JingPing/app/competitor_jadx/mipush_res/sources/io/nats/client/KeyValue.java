package io.nats.client;

import io.nats.client.api.KeyResult;
import io.nats.client.api.KeyValueEntry;
import io.nats.client.api.KeyValuePurgeOptions;
import io.nats.client.api.KeyValueStatus;
import io.nats.client.api.KeyValueWatchOption;
import io.nats.client.api.KeyValueWatcher;
import io.nats.client.impl.NatsKeyValueWatchSubscription;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public interface KeyValue {
    LinkedBlockingQueue<KeyResult> consumeKeys();

    LinkedBlockingQueue<KeyResult> consumeKeys(String str);

    LinkedBlockingQueue<KeyResult> consumeKeys(List<String> list);

    long create(String str, byte[] bArr) throws IOException, JetStreamApiException;

    long create(String str, byte[] bArr, MessageTtl messageTtl) throws IOException, JetStreamApiException;

    void delete(String str) throws IOException, JetStreamApiException;

    void delete(String str, long j) throws IOException, JetStreamApiException;

    KeyValueEntry get(String str) throws IOException, JetStreamApiException;

    KeyValueEntry get(String str, long j) throws IOException, JetStreamApiException;

    String getBucketName();

    KeyValueStatus getStatus() throws InterruptedException, IOException, JetStreamApiException;

    List<KeyValueEntry> history(String str) throws InterruptedException, IOException, JetStreamApiException;

    List<String> keys() throws InterruptedException, IOException, JetStreamApiException;

    List<String> keys(String str) throws InterruptedException, IOException, JetStreamApiException;

    List<String> keys(List<String> list) throws InterruptedException, IOException, JetStreamApiException;

    void purge(String str) throws IOException, JetStreamApiException;

    void purge(String str, long j) throws IOException, JetStreamApiException;

    void purge(String str, long j, MessageTtl messageTtl) throws IOException, JetStreamApiException;

    void purge(String str, MessageTtl messageTtl) throws IOException, JetStreamApiException;

    void purgeDeletes() throws InterruptedException, IOException, JetStreamApiException;

    void purgeDeletes(KeyValuePurgeOptions keyValuePurgeOptions) throws InterruptedException, IOException, JetStreamApiException;

    long put(String str, Number number) throws IOException, JetStreamApiException;

    long put(String str, String str2) throws IOException, JetStreamApiException;

    long put(String str, byte[] bArr) throws IOException, JetStreamApiException;

    long update(String str, String str2, long j) throws IOException, JetStreamApiException;

    long update(String str, byte[] bArr, long j) throws IOException, JetStreamApiException;

    NatsKeyValueWatchSubscription watch(String str, KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException;

    NatsKeyValueWatchSubscription watch(String str, KeyValueWatcher keyValueWatcher, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException;

    NatsKeyValueWatchSubscription watch(List<String> list, KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException;

    NatsKeyValueWatchSubscription watch(List<String> list, KeyValueWatcher keyValueWatcher, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException;

    NatsKeyValueWatchSubscription watchAll(KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException;

    NatsKeyValueWatchSubscription watchAll(KeyValueWatcher keyValueWatcher, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException;
}
