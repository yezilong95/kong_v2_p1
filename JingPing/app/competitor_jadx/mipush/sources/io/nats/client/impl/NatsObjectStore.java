package io.nats.client.impl;

import defpackage.mg0;
import defpackage.zn;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.NUID;
import io.nats.client.ObjectStore;
import io.nats.client.ObjectStoreOptions;
import io.nats.client.PurgeOptions;
import io.nats.client.PushSubscribeOptions;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.MessageInfo;
import io.nats.client.api.ObjectInfo;
import io.nats.client.api.ObjectLink;
import io.nats.client.api.ObjectMeta;
import io.nats.client.api.ObjectStoreStatus;
import io.nats.client.api.ObjectStoreWatchOption;
import io.nats.client.api.ObjectStoreWatcher;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.Digester;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamClientError;
import io.nats.client.support.NatsObjectStoreUtil;
import io.nats.client.support.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NatsObjectStore extends NatsFeatureBase implements ObjectStore {
    private final String bucketName;
    private final ObjectStoreOptions oso;
    private final String pubSubChunkPrefix;
    private final String pubSubMetaPrefix;
    private final String rawChunkPrefix;
    private final String rawMetaPrefix;

    public NatsObjectStore(NatsConnection natsConnection, String str, ObjectStoreOptions objectStoreOptions) throws IOException {
        super(natsConnection, objectStoreOptions);
        this.oso = objectStoreOptions;
        this.bucketName = Validator.validateBucketName(str, true);
        this.streamName = NatsObjectStoreUtil.toStreamName(str);
        String chunkPrefix = NatsObjectStoreUtil.toChunkPrefix(str);
        this.rawChunkPrefix = chunkPrefix;
        String metaPrefix = NatsObjectStoreUtil.toMetaPrefix(str);
        this.rawMetaPrefix = metaPrefix;
        if (objectStoreOptions == null) {
            this.pubSubChunkPrefix = chunkPrefix;
            this.pubSubMetaPrefix = metaPrefix;
            return;
        }
        if (objectStoreOptions.getJetStreamOptions().isDefaultPrefix()) {
            this.pubSubChunkPrefix = chunkPrefix;
            this.pubSubMetaPrefix = metaPrefix;
            return;
        }
        this.pubSubChunkPrefix = objectStoreOptions.getJetStreamOptions().getPrefix() + chunkPrefix;
        this.pubSubMetaPrefix = objectStoreOptions.getJetStreamOptions().getPrefix() + metaPrefix;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getList$0(List list, Message message) throws InterruptedException {
        ObjectInfo objectInfo = new ObjectInfo(message);
        if (objectInfo.isDeleted()) {
            return;
        }
        list.add(objectInfo);
    }

    private ObjectInfo publishMeta(ObjectInfo objectInfo) throws IOException, JetStreamApiException {
        this.js.publish(NatsMessage.builder().subject(rawMetaSubject(objectInfo.getObjectName())).headers(NatsObjectStoreUtil.getMetaHeaders()).data(objectInfo.serialize()).build());
        return ObjectInfo.builder(objectInfo).modified(DateTimeUtils.gmtNow()).build();
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo addBucketLink(String str, ObjectStore objectStore) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "object name");
        Validator.validateNotNull(objectStore, "Link-To ObjectStore");
        ObjectInfo info = getInfo(str, false);
        if (info == null || info.isLink()) {
            return publishMeta(ObjectInfo.builder(this.bucketName, str).nuid(NUID.nextGlobal()).bucketLink(objectStore.getBucketName()).build());
        }
        throw NatsJetStreamClientError.OsObjectAlreadyExists.instance();
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo addLink(String str, ObjectInfo objectInfo) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "object name");
        Validator.validateNotNull(objectInfo, "Link-To ObjectInfo");
        Validator.validateNotNull(objectInfo.getObjectName(), "Link-To ObjectMeta");
        if (objectInfo.isDeleted()) {
            throw NatsJetStreamClientError.OsObjectIsDeleted.instance();
        }
        if (objectInfo.isLink()) {
            throw NatsJetStreamClientError.OsCantLinkToLink.instance();
        }
        ObjectInfo info = getInfo(str, false);
        if (info == null || info.isLink()) {
            return publishMeta(ObjectInfo.builder(this.bucketName, str).nuid(NUID.nextGlobal()).objectLink(objectInfo.getBucket(), objectInfo.getObjectName()).build());
        }
        throw NatsJetStreamClientError.OsObjectAlreadyExists.instance();
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo delete(String str) throws IOException, JetStreamApiException {
        ObjectInfo info = getInfo(str, true);
        if (info == null) {
            throw NatsJetStreamClientError.OsObjectNotFound.instance();
        }
        if (info.isDeleted()) {
            return info;
        }
        ObjectInfo objectInfoPublishMeta = publishMeta(ObjectInfo.builder(info).deleted(true).size(0L).chunks(0L).digest(null).build());
        this.jsm.purgeStream(this.streamName, PurgeOptions.subject(rawChunkSubject(info.getNuid())));
        return objectInfoPublishMeta;
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo get(String str, OutputStream outputStream) throws InterruptedException, NoSuchAlgorithmException, IOException, JetStreamApiException {
        long length;
        ObjectInfo info = getInfo(str, false);
        if (info == null) {
            throw NatsJetStreamClientError.OsObjectNotFound.instance();
        }
        if (info.isLink()) {
            ObjectLink link = info.getLink();
            if (link.isBucketLink()) {
                throw NatsJetStreamClientError.OsGetLinkToBucket.instance();
            }
            return link.getBucket().equals(this.bucketName) ? get(link.getObjectName(), outputStream) : this.js.conn.objectStore(link.getBucket(), this.oso).get(link.getObjectName(), outputStream);
        }
        Digester digester = new Digester();
        long chunks = info.getChunks();
        long j = 1;
        if (info.getChunks() == 1) {
            byte[] data = this.jsm.getLastMessage(this.streamName, rawChunkSubject(info.getNuid())).getData();
            length = data.length;
            digester.update(data);
            outputStream.write(data);
        } else {
            JetStreamSubscription jetStreamSubscriptionSubscribe = this.js.subscribe(rawChunkSubject(info.getNuid()), PushSubscribeOptions.builder().stream(this.streamName).ordered(true).build());
            Message messageNextMessage = jetStreamSubscriptionSubscribe.nextMessage(this.jsm.getTimeout());
            long j2 = 0;
            long length2 = 0;
            while (messageNextMessage != null) {
                long jPendingCount = messageNextMessage.metaData().pendingCount();
                j2 += j;
                if (chunks != jPendingCount + j2) {
                    throw NatsJetStreamClientError.OsGetChunksMismatch.instance();
                }
                byte[] data2 = messageNextMessage.getData();
                length2 += (long) data2.length;
                digester.update(data2);
                outputStream.write(data2);
                if (jPendingCount == 0) {
                    break;
                }
                messageNextMessage = jetStreamSubscriptionSubscribe.nextMessage(this.jsm.getTimeout());
                j = 1;
            }
            j = j2;
            try {
                jetStreamSubscriptionSubscribe.unsubscribe();
            } catch (RuntimeException unused) {
            }
            length = length2;
        }
        if (j != info.getChunks()) {
            throw NatsJetStreamClientError.OsGetChunksMismatch.instance();
        }
        if (length != info.getSize()) {
            throw NatsJetStreamClientError.OsGetSizeMismatch.instance();
        }
        if (!digester.matches(info.getDigest())) {
            throw NatsJetStreamClientError.OsGetDigestMismatch.instance();
        }
        outputStream.flush();
        return info;
    }

    @Override // io.nats.client.ObjectStore
    public String getBucketName() {
        return this.bucketName;
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo getInfo(String str) throws IOException, JetStreamApiException {
        return getInfo(str, false);
    }

    @Override // io.nats.client.ObjectStore
    public List<ObjectInfo> getList() throws InterruptedException, IOException, JetStreamApiException {
        ArrayList arrayList = new ArrayList();
        visitSubject(rawAllMetaSubject(), DeliverPolicy.LastPerSubject, false, true, (MessageHandler) new mg0(arrayList, 2));
        return arrayList;
    }

    @Override // io.nats.client.ObjectStore
    public ObjectStoreStatus getStatus() throws IOException, JetStreamApiException {
        return new ObjectStoreStatus(this.jsm.getStreamInfo(this.streamName));
    }

    public String pubSubChunkSubject(String str) {
        return zn.o(new StringBuilder(), this.pubSubChunkPrefix, str);
    }

    public String pubSubMetaSubject(String str) {
        return this.pubSubMetaPrefix + NatsObjectStoreUtil.encodeForSubject(str);
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo put(ObjectMeta objectMeta, InputStream inputStream) throws NoSuchAlgorithmException, IOException, JetStreamApiException {
        Validator.validateNotNull(objectMeta, "ObjectMeta");
        Validator.validateNotNull(objectMeta.getObjectName(), "ObjectMeta name");
        Validator.validateNotNull(inputStream, "InputStream");
        if (objectMeta.getObjectMetaOptions().getLink() != null) {
            throw NatsJetStreamClientError.OsLinkNotAllowOnPut.instance();
        }
        String strNextGlobal = NUID.nextGlobal();
        String strRawChunkSubject = rawChunkSubject(strNextGlobal);
        int chunkSize = objectMeta.getObjectMetaOptions().getChunkSize();
        if (chunkSize <= 0) {
            chunkSize = NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE;
        }
        try {
            try {
                Digester digester = new Digester();
                byte[] bArr = new byte[chunkSize];
                int i = inputStream.read(bArr);
                long j = 0;
                int i2 = 0;
                while (i != -1) {
                    byte[] bArrCopyOfRange = i == chunkSize ? bArr : Arrays.copyOfRange(bArr, 0, i);
                    digester.update(bArrCopyOfRange);
                    this.js.publish(strRawChunkSubject, bArrCopyOfRange);
                    i2++;
                    j += (long) i;
                    i = inputStream.read(bArr);
                }
                ObjectInfo objectInfoPublishMeta = publishMeta(ObjectInfo.builder(this.bucketName, objectMeta).size(j).chunks(i2).nuid(strNextGlobal).chunkSize(chunkSize).digest(digester.getDigestEntry()).build());
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
                return objectInfoPublishMeta;
            } finally {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
        } catch (JetStreamApiException e) {
            e = e;
            try {
                this.jsm.purgeStream(this.streamName, PurgeOptions.subject(rawChunkSubject(strNextGlobal)));
            } catch (Exception unused3) {
            }
            throw e;
        } catch (IOException e2) {
            e = e2;
            this.jsm.purgeStream(this.streamName, PurgeOptions.subject(rawChunkSubject(strNextGlobal)));
            throw e;
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            this.jsm.purgeStream(this.streamName, PurgeOptions.subject(rawChunkSubject(strNextGlobal)));
            throw e;
        }
    }

    public String rawAllMetaSubject() {
        return zn.o(new StringBuilder(), this.rawMetaPrefix, NatsConstants.GREATER_THAN);
    }

    public String rawChunkSubject(String str) {
        return zn.o(new StringBuilder(), this.rawChunkPrefix, str);
    }

    public String rawMetaSubject(String str) {
        return this.rawMetaPrefix + NatsObjectStoreUtil.encodeForSubject(str);
    }

    @Override // io.nats.client.ObjectStore
    public ObjectStoreStatus seal() throws IOException, JetStreamApiException {
        return new ObjectStoreStatus(this.jsm.updateStream(StreamConfiguration.builder(this.jsm.getStreamInfo(this.streamName).getConfiguration()).seal().build()));
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo updateMeta(String str, ObjectMeta objectMeta) throws IOException, JetStreamApiException {
        Validator.validateNotNull(str, "object name");
        Validator.validateNotNull(objectMeta, "ObjectMeta");
        Validator.validateNotNull(objectMeta.getObjectName(), "ObjectMeta name");
        ObjectInfo info = getInfo(str, true);
        if (info == null) {
            throw NatsJetStreamClientError.OsObjectNotFound.instance();
        }
        if (info.isDeleted()) {
            throw NatsJetStreamClientError.OsObjectIsDeleted.instance();
        }
        boolean zEquals = str.equals(objectMeta.getObjectName());
        if (!zEquals && getInfo(objectMeta.getObjectName(), false) != null) {
            throw NatsJetStreamClientError.OsObjectAlreadyExists.instance();
        }
        ObjectInfo objectInfoPublishMeta = publishMeta(ObjectInfo.builder(info).objectName(objectMeta.getObjectName()).description(objectMeta.getDescription()).headers(objectMeta.getHeaders()).build());
        if (!zEquals) {
            this.jsm.purgeStream(this.streamName, PurgeOptions.subject(rawMetaSubject(str)));
        }
        return objectInfoPublishMeta;
    }

    @Override // io.nats.client.ObjectStore
    public NatsObjectStoreWatchSubscription watch(ObjectStoreWatcher objectStoreWatcher, ObjectStoreWatchOption... objectStoreWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException {
        return new NatsObjectStoreWatchSubscription(this, objectStoreWatcher, objectStoreWatchOptionArr);
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo getInfo(String str, boolean z) throws IOException, JetStreamApiException {
        MessageInfo messageInfo_getLast = _getLast(rawMetaSubject(str));
        if (messageInfo_getLast == null) {
            return null;
        }
        ObjectInfo objectInfo = new ObjectInfo(messageInfo_getLast);
        if (z || !objectInfo.isDeleted()) {
            return objectInfo;
        }
        return null;
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo put(String str, InputStream inputStream) throws NoSuchAlgorithmException, IOException, JetStreamApiException {
        return put(ObjectMeta.objectName(str), inputStream);
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo put(String str, byte[] bArr) throws NoSuchAlgorithmException, IOException, JetStreamApiException {
        return put(ObjectMeta.objectName(str), new ByteArrayInputStream(bArr));
    }

    @Override // io.nats.client.ObjectStore
    public ObjectInfo put(File file) throws NoSuchAlgorithmException, IOException, JetStreamApiException {
        return put(ObjectMeta.objectName(file.getName()), Files.newInputStream(file.toPath(), new OpenOption[0]));
    }
}
