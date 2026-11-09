package io.nats.client;

import io.nats.client.api.ObjectStoreConfiguration;
import io.nats.client.api.ObjectStoreStatus;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ObjectStoreManagement {
    ObjectStoreStatus create(ObjectStoreConfiguration objectStoreConfiguration) throws IOException, JetStreamApiException;

    void delete(String str) throws IOException, JetStreamApiException;

    List<String> getBucketNames() throws IOException, JetStreamApiException;

    ObjectStoreStatus getStatus(String str) throws IOException, JetStreamApiException;

    List<ObjectStoreStatus> getStatuses() throws IOException, JetStreamApiException;
}
