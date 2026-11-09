package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.KeyValueManagement;
import io.nats.client.KeyValueOptions;
import io.nats.client.api.KeyValueConfiguration;
import io.nats.client.api.KeyValueStatus;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.support.NatsKeyValueUtil;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NatsKeyValueManagement implements KeyValueManagement {
    private final NatsJetStreamManagement jsm;
    private final boolean serverOlderThan272;

    public NatsKeyValueManagement(NatsConnection natsConnection, KeyValueOptions keyValueOptions) throws IOException {
        NatsJetStreamManagement natsJetStreamManagement = new NatsJetStreamManagement(natsConnection, keyValueOptions == null ? null : keyValueOptions.getJetStreamOptions());
        this.jsm = natsJetStreamManagement;
        this.serverOlderThan272 = natsJetStreamManagement.conn.getServerInfo().isOlderThanVersion("2.7.2");
    }

    @Override // io.nats.client.KeyValueManagement
    public KeyValueStatus create(KeyValueConfiguration keyValueConfiguration) throws IOException, JetStreamApiException {
        StreamConfiguration backingConfig = keyValueConfiguration.getBackingConfig();
        if (this.serverOlderThan272) {
            backingConfig = StreamConfiguration.builder(backingConfig).discardPolicy(null).build();
        }
        return new KeyValueStatus(this.jsm.addStream(backingConfig));
    }

    @Override // io.nats.client.KeyValueManagement
    public void delete(String str) throws IOException, JetStreamApiException {
        Validator.validateBucketName(str, true);
        this.jsm.deleteStream(NatsKeyValueUtil.toStreamName(str));
    }

    @Override // io.nats.client.KeyValueManagement
    public KeyValueStatus getBucketInfo(String str) throws IOException, JetStreamApiException {
        return getStatus(str);
    }

    @Override // io.nats.client.KeyValueManagement
    public List<String> getBucketNames() throws IOException, JetStreamApiException {
        ArrayList arrayList = new ArrayList();
        for (String str : this.jsm.getStreamNames()) {
            if (str.startsWith(NatsKeyValueUtil.KV_STREAM_PREFIX)) {
                arrayList.add(NatsKeyValueUtil.extractBucketName(str));
            }
        }
        return arrayList;
    }

    @Override // io.nats.client.KeyValueManagement
    public KeyValueStatus getStatus(String str) throws IOException, JetStreamApiException {
        Validator.validateBucketName(str, true);
        return new KeyValueStatus(this.jsm.getStreamInfo(NatsKeyValueUtil.toStreamName(str)));
    }

    @Override // io.nats.client.KeyValueManagement
    public List<KeyValueStatus> getStatuses() throws IOException, JetStreamApiException {
        List<String> bucketNames = getBucketNames();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = bucketNames.iterator();
        while (it.hasNext()) {
            arrayList.add(new KeyValueStatus(this.jsm.getStreamInfo(NatsKeyValueUtil.toStreamName(it.next()))));
        }
        return arrayList;
    }

    @Override // io.nats.client.KeyValueManagement
    public KeyValueStatus update(KeyValueConfiguration keyValueConfiguration) throws IOException, JetStreamApiException {
        return new KeyValueStatus(this.jsm.updateStream(keyValueConfiguration.getBackingConfig()));
    }
}
