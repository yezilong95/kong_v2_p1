package io.nats.client;

import io.nats.client.api.ObjectInfo;
import io.nats.client.api.ObjectMeta;
import io.nats.client.api.ObjectStoreStatus;
import io.nats.client.api.ObjectStoreWatchOption;
import io.nats.client.api.ObjectStoreWatcher;
import io.nats.client.impl.NatsObjectStoreWatchSubscription;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ObjectStore {
    ObjectInfo addBucketLink(String str, ObjectStore objectStore) throws IOException, JetStreamApiException;

    ObjectInfo addLink(String str, ObjectInfo objectInfo) throws IOException, JetStreamApiException;

    ObjectInfo delete(String str) throws IOException, JetStreamApiException;

    ObjectInfo get(String str, OutputStream outputStream) throws InterruptedException, NoSuchAlgorithmException, IOException, JetStreamApiException;

    String getBucketName();

    ObjectInfo getInfo(String str) throws IOException, JetStreamApiException;

    ObjectInfo getInfo(String str, boolean z) throws IOException, JetStreamApiException;

    List<ObjectInfo> getList() throws InterruptedException, IOException, JetStreamApiException;

    ObjectStoreStatus getStatus() throws IOException, JetStreamApiException;

    ObjectInfo put(ObjectMeta objectMeta, InputStream inputStream) throws NoSuchAlgorithmException, IOException, JetStreamApiException;

    ObjectInfo put(File file) throws NoSuchAlgorithmException, IOException, JetStreamApiException;

    ObjectInfo put(String str, InputStream inputStream) throws NoSuchAlgorithmException, IOException, JetStreamApiException;

    ObjectInfo put(String str, byte[] bArr) throws NoSuchAlgorithmException, IOException, JetStreamApiException;

    ObjectStoreStatus seal() throws IOException, JetStreamApiException;

    ObjectInfo updateMeta(String str, ObjectMeta objectMeta) throws IOException, JetStreamApiException;

    NatsObjectStoreWatchSubscription watch(ObjectStoreWatcher objectStoreWatcher, ObjectStoreWatchOption... objectStoreWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException;
}
