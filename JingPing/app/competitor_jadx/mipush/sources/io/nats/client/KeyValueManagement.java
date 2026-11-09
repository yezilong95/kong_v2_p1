package io.nats.client;

import io.nats.client.api.KeyValueConfiguration;
import io.nats.client.api.KeyValueStatus;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface KeyValueManagement {
    KeyValueStatus create(KeyValueConfiguration keyValueConfiguration) throws IOException, JetStreamApiException;

    void delete(String str) throws IOException, JetStreamApiException;

    @Deprecated
    KeyValueStatus getBucketInfo(String str) throws IOException, JetStreamApiException;

    List<String> getBucketNames() throws IOException, JetStreamApiException;

    KeyValueStatus getStatus(String str) throws IOException, JetStreamApiException;

    List<KeyValueStatus> getStatuses() throws IOException, JetStreamApiException;

    KeyValueStatus update(KeyValueConfiguration keyValueConfiguration) throws IOException, JetStreamApiException;
}
