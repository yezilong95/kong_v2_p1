package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.ObjectStoreManagement;
import io.nats.client.ObjectStoreOptions;
import io.nats.client.api.ObjectStoreConfiguration;
import io.nats.client.api.ObjectStoreStatus;
import io.nats.client.support.NatsObjectStoreUtil;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NatsObjectStoreManagement implements ObjectStoreManagement {
    private final NatsJetStreamManagement jsm;

    public NatsObjectStoreManagement(NatsConnection natsConnection, ObjectStoreOptions objectStoreOptions) throws IOException {
        this.jsm = new NatsJetStreamManagement(natsConnection, objectStoreOptions == null ? null : objectStoreOptions.getJetStreamOptions());
    }

    @Override // io.nats.client.ObjectStoreManagement
    public ObjectStoreStatus create(ObjectStoreConfiguration objectStoreConfiguration) throws IOException, JetStreamApiException {
        return new ObjectStoreStatus(this.jsm.addStream(objectStoreConfiguration.getBackingConfig()));
    }

    @Override // io.nats.client.ObjectStoreManagement
    public void delete(String str) throws IOException, JetStreamApiException {
        Validator.validateBucketName(str, true);
        this.jsm.deleteStream(NatsObjectStoreUtil.toStreamName(str));
    }

    @Override // io.nats.client.ObjectStoreManagement
    public List<String> getBucketNames() throws IOException, JetStreamApiException {
        ArrayList arrayList = new ArrayList();
        for (String str : this.jsm.getStreamNames()) {
            if (str.startsWith(NatsObjectStoreUtil.OBJ_STREAM_PREFIX)) {
                arrayList.add(NatsObjectStoreUtil.extractBucketName(str));
            }
        }
        return arrayList;
    }

    @Override // io.nats.client.ObjectStoreManagement
    public ObjectStoreStatus getStatus(String str) throws IOException, JetStreamApiException {
        Validator.validateBucketName(str, true);
        return new ObjectStoreStatus(this.jsm.getStreamInfo(NatsObjectStoreUtil.toStreamName(str)));
    }

    @Override // io.nats.client.ObjectStoreManagement
    public List<ObjectStoreStatus> getStatuses() throws IOException, JetStreamApiException {
        List<String> bucketNames = getBucketNames();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = bucketNames.iterator();
        while (it.hasNext()) {
            arrayList.add(new ObjectStoreStatus(this.jsm.getStreamInfo(NatsObjectStoreUtil.toStreamName(it.next()))));
        }
        return arrayList;
    }
}
