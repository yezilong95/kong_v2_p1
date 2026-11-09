package io.nats.client.impl;

import defpackage.gg0;
import defpackage.mg0;
import defpackage.zn;
import io.nats.client.JetStreamApiException;
import io.nats.client.KeyValue;
import io.nats.client.KeyValueOptions;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.MessageTtl;
import io.nats.client.PublishOptions;
import io.nats.client.PurgeOptions;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.KeyResult;
import io.nats.client.api.KeyValueEntry;
import io.nats.client.api.KeyValueOperation;
import io.nats.client.api.KeyValuePurgeOptions;
import io.nats.client.api.KeyValueStatus;
import io.nats.client.api.KeyValueWatchOption;
import io.nats.client.api.KeyValueWatcher;
import io.nats.client.api.MessageInfo;
import io.nats.client.api.Mirror;
import io.nats.client.api.PublishAck;
import io.nats.client.api.StreamInfo;
import io.nats.client.impl.NatsKeyValue;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsKeyValueUtil;
import io.nats.client.support.Validator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public class NatsKeyValue extends NatsFeatureBase implements KeyValue {
    private final String bucketName;
    private final String readPrefix;
    private final String streamSubject;
    private final String writePrefix;

    public NatsKeyValue(NatsConnection natsConnection, String str, KeyValueOptions keyValueOptions) throws IOException {
        String string;
        super(natsConnection, keyValueOptions);
        this.bucketName = Validator.validateBucketName(str, true);
        String streamName = NatsKeyValueUtil.toStreamName(str);
        this.streamName = streamName;
        try {
            StreamInfo streamInfo = this.jsm.getStreamInfo(streamName);
            this.streamSubject = NatsKeyValueUtil.toStreamSubject(str);
            String keyPrefix = NatsKeyValueUtil.toKeyPrefix(str);
            Mirror mirror = streamInfo.getConfiguration().getMirror();
            if (mirror != null) {
                String strTrimPrefix = NatsKeyValueUtil.trimPrefix(mirror.getName());
                String api = mirror.getExternal() == null ? null : mirror.getExternal().getApi();
                if (api == null) {
                    string = NatsKeyValueUtil.toKeyPrefix(strTrimPrefix);
                } else {
                    keyPrefix = NatsKeyValueUtil.toKeyPrefix(strTrimPrefix);
                    StringBuilder sbQ = zn.q(api, NatsConstants.DOT);
                    sbQ.append(NatsKeyValueUtil.toKeyPrefix(strTrimPrefix));
                    string = sbQ.toString();
                }
            } else if (keyValueOptions == null || keyValueOptions.getJetStreamOptions().isDefaultPrefix()) {
                string = keyPrefix;
            } else {
                string = keyValueOptions.getJetStreamOptions().getPrefix() + keyPrefix;
            }
            this.readPrefix = keyPrefix;
            this.writePrefix = string;
        } catch (JetStreamApiException e) {
            throw new IOException(e);
        }
    }

    private LinkedBlockingQueue<KeyResult> _consumeKeys(List<String> list) {
        LinkedBlockingQueue<KeyResult> linkedBlockingQueue = new LinkedBlockingQueue<>();
        this.js.conn.getOptions().getExecutor().submit(new defpackage.j(this, list, linkedBlockingQueue, 4));
        return linkedBlockingQueue;
    }

    private List<String> _keys(List<String> list) throws InterruptedException, IOException, JetStreamApiException {
        ArrayList arrayList = new ArrayList();
        visitSubject(list, DeliverPolicy.LastPerSubject, true, false, (MessageHandler) new mg0(arrayList, 0));
        return arrayList;
    }

    private long _update(String str, byte[] bArr, long j, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        return _write(str, bArr, null, NatsKeyValueUtil.getPublishOptions(j, messageTtl)).getSeqno();
    }

    private PublishAck _write(String str, byte[] bArr, Headers headers, PublishOptions publishOptions) throws IOException, JetStreamApiException {
        Validator.validateNonWildcardKvKeyRequired(str);
        return this.js.publish(NatsMessage.builder().subject(writeSubject(str)).data(bArr).headers(headers).build(), publishOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_consumeKeys$2(List list, LinkedBlockingQueue linkedBlockingQueue) {
        try {
            visitSubject((List<String>) list, DeliverPolicy.LastPerSubject, true, false, (MessageHandler) new gg0(1, linkedBlockingQueue));
            linkedBlockingQueue.offer(new KeyResult());
        } catch (JetStreamApiException e) {
            e = e;
            linkedBlockingQueue.offer(new KeyResult(e));
        } catch (IOException e2) {
            e = e2;
            linkedBlockingQueue.offer(new KeyResult(e));
        } catch (InterruptedException e3) {
            linkedBlockingQueue.offer(new KeyResult(e3));
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$_keys$0(List list, Message message) throws InterruptedException {
        if (NatsKeyValueUtil.getOperation(message.getHeaders()) == KeyValueOperation.PUT) {
            list.add(new NatsKeyValueUtil.BucketAndKey(message).key);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$history$3(List list, Message message) throws InterruptedException {
        list.add(new KeyValueEntry(message));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$1(LinkedBlockingQueue linkedBlockingQueue, Message message) throws InterruptedException {
        if (NatsKeyValueUtil.getOperation(message.getHeaders()) == KeyValueOperation.PUT) {
            linkedBlockingQueue.offer(new KeyResult(new NatsKeyValueUtil.BucketAndKey(message).key));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$purgeDeletes$4(ZonedDateTime zonedDateTime, List list, List list2, Message message) throws InterruptedException {
        KeyValueEntry keyValueEntry = new KeyValueEntry(message);
        if (keyValueEntry.getOperation() != KeyValueOperation.PUT) {
            if (keyValueEntry.getCreated().isAfter(zonedDateTime)) {
                list.add(new NatsKeyValueUtil.BucketAndKey(message).key);
            } else {
                list2.add(new NatsKeyValueUtil.BucketAndKey(message).key);
            }
        }
    }

    public KeyValueEntry _get(String str) throws IOException, JetStreamApiException {
        MessageInfo messageInfo_getLast = _getLast(readSubject(str));
        if (messageInfo_getLast == null) {
            return null;
        }
        return new KeyValueEntry(messageInfo_getLast);
    }

    @Override // io.nats.client.KeyValue
    public LinkedBlockingQueue<KeyResult> consumeKeys() {
        return _consumeKeys(Collections.singletonList(readSubject(NatsConstants.GREATER_THAN)));
    }

    @Override // io.nats.client.KeyValue
    public long create(String str, byte[] bArr) throws IOException, JetStreamApiException {
        return create(str, bArr, null);
    }

    @Override // io.nats.client.KeyValue
    public void delete(String str) throws IOException, JetStreamApiException {
        _write(str, null, NatsKeyValueUtil.getDeleteHeaders(), null);
    }

    public KeyValueEntry existingOnly(KeyValueEntry keyValueEntry) {
        if (keyValueEntry == null || keyValueEntry.getOperation() != KeyValueOperation.PUT) {
            return null;
        }
        return keyValueEntry;
    }

    @Override // io.nats.client.KeyValue
    public KeyValueEntry get(String str) throws IOException, JetStreamApiException {
        return existingOnly(_get(Validator.validateNonWildcardKvKeyRequired(str)));
    }

    @Override // io.nats.client.KeyValue
    public String getBucketName() {
        return this.bucketName;
    }

    @Override // io.nats.client.KeyValue
    public KeyValueStatus getStatus() throws InterruptedException, IOException, JetStreamApiException {
        return new KeyValueStatus(this.jsm.getStreamInfo(this.streamName));
    }

    @Override // io.nats.client.KeyValue
    public List<KeyValueEntry> history(String str) throws InterruptedException, IOException, JetStreamApiException {
        Validator.validateNonWildcardKvKeyRequired(str);
        ArrayList arrayList = new ArrayList();
        visitSubject(readSubject(str), DeliverPolicy.All, false, true, (MessageHandler) new mg0(arrayList, 1));
        return arrayList;
    }

    @Override // io.nats.client.KeyValue
    public List<String> keys() throws InterruptedException, IOException, JetStreamApiException {
        return _keys(Collections.singletonList(readSubject(NatsConstants.GREATER_THAN)));
    }

    @Override // io.nats.client.KeyValue
    public void purge(String str) throws IOException, JetStreamApiException {
        _write(str, null, NatsKeyValueUtil.getPurgeHeaders(), null);
    }

    @Override // io.nats.client.KeyValue
    public void purgeDeletes() throws InterruptedException, IOException, JetStreamApiException {
        purgeDeletes(null);
    }

    @Override // io.nats.client.KeyValue
    public long put(String str, byte[] bArr) throws IOException, JetStreamApiException {
        return _write(str, bArr, null, null).getSeqno();
    }

    public String readSubject(String str) {
        return zn.o(new StringBuilder(), this.readPrefix, str);
    }

    @Override // io.nats.client.KeyValue
    public long update(String str, byte[] bArr, long j) throws IOException, JetStreamApiException {
        Validator.validateNonWildcardKvKeyRequired(str);
        return _update(str, bArr, j, null);
    }

    @Override // io.nats.client.KeyValue
    public NatsKeyValueWatchSubscription watch(String str, KeyValueWatcher keyValueWatcher, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException {
        Validator.validateKvKeyWildcardAllowedRequired(str);
        Validator.validateNotNull(keyValueWatcher, "Watcher is required");
        return new NatsKeyValueWatchSubscription(this, (List<String>) Collections.singletonList(str), keyValueWatcher, -1L, keyValueWatchOptionArr);
    }

    @Override // io.nats.client.KeyValue
    public NatsKeyValueWatchSubscription watchAll(KeyValueWatcher keyValueWatcher, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException {
        return new NatsKeyValueWatchSubscription(this, (List<String>) Collections.singletonList(NatsConstants.GREATER_THAN), keyValueWatcher, -1L, keyValueWatchOptionArr);
    }

    public String writeSubject(String str) {
        return zn.o(new StringBuilder(), this.writePrefix, str);
    }

    @Override // io.nats.client.KeyValue
    public LinkedBlockingQueue<KeyResult> consumeKeys(String str) {
        return _consumeKeys(Collections.singletonList(readSubject(str)));
    }

    @Override // io.nats.client.KeyValue
    public long create(String str, byte[] bArr, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        KeyValueEntry keyValueEntry_get;
        Validator.validateNonWildcardKvKeyRequired(str);
        try {
            return _update(str, bArr, 0L, messageTtl);
        } catch (JetStreamApiException e) {
            if (e.getApiErrorCode() != 10071 || (keyValueEntry_get = _get(str)) == null || keyValueEntry_get.getOperation() == KeyValueOperation.PUT) {
                throw e;
            }
            return _update(str, bArr, keyValueEntry_get.getRevision(), messageTtl);
        }
    }

    @Override // io.nats.client.KeyValue
    public void delete(String str, long j) throws IOException, JetStreamApiException {
        _write(str, null, NatsKeyValueUtil.getDeleteHeaders(), NatsKeyValueUtil.getPublishOptions(j, null));
    }

    @Override // io.nats.client.KeyValue
    public KeyValueEntry get(String str, long j) throws IOException, JetStreamApiException {
        return existingOnly(_get(Validator.validateNonWildcardKvKeyRequired(str), j));
    }

    @Override // io.nats.client.KeyValue
    public List<String> keys(String str) throws InterruptedException, IOException, JetStreamApiException {
        return _keys(Collections.singletonList(readSubject(str)));
    }

    @Override // io.nats.client.KeyValue
    public void purge(String str, long j) throws IOException, JetStreamApiException {
        _write(str, null, NatsKeyValueUtil.getPurgeHeaders(), NatsKeyValueUtil.getPublishOptions(j, null));
    }

    @Override // io.nats.client.KeyValue
    public void purgeDeletes(KeyValuePurgeOptions keyValuePurgeOptions) throws InterruptedException, IOException, JetStreamApiException {
        long deleteMarkersThresholdMillis = keyValuePurgeOptions == null ? KeyValuePurgeOptions.DEFAULT_THRESHOLD_MILLIS : keyValuePurgeOptions.getDeleteMarkersThresholdMillis();
        final ZonedDateTime zonedDateTimeFromNow = deleteMarkersThresholdMillis < 0 ? DateTimeUtils.fromNow(600000L) : deleteMarkersThresholdMillis == 0 ? DateTimeUtils.fromNow(KeyValuePurgeOptions.DEFAULT_THRESHOLD_MILLIS) : DateTimeUtils.fromNow(-deleteMarkersThresholdMillis);
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        visitSubject(this.streamSubject, DeliverPolicy.LastPerSubject, true, false, new MessageHandler() { // from class: lg0
            @Override // io.nats.client.MessageHandler
            public final void onMessage(Message message) throws InterruptedException {
                NatsKeyValue.lambda$purgeDeletes$4(zonedDateTimeFromNow, arrayList2, arrayList, message);
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.jsm.purgeStream(this.streamName, PurgeOptions.subject(readSubject((String) it.next())));
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            this.jsm.purgeStream(this.streamName, PurgeOptions.builder().subject(readSubject((String) it2.next())).keep(1L).build());
        }
    }

    @Override // io.nats.client.KeyValue
    public long put(String str, String str2) throws IOException, JetStreamApiException {
        return _write(str, str2.getBytes(StandardCharsets.UTF_8), null, null).getSeqno();
    }

    @Override // io.nats.client.KeyValue
    public NatsKeyValueWatchSubscription watchAll(KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException {
        return new NatsKeyValueWatchSubscription(this, (List<String>) Collections.singletonList(NatsConstants.GREATER_THAN), keyValueWatcher, j, keyValueWatchOptionArr);
    }

    public KeyValueEntry _get(String str, long j) throws IOException, JetStreamApiException {
        MessageInfo messageInfo_getBySeq = _getBySeq(j);
        if (messageInfo_getBySeq == null) {
            return null;
        }
        KeyValueEntry keyValueEntry = new KeyValueEntry(messageInfo_getBySeq);
        if (str.equals(keyValueEntry.getKey())) {
            return keyValueEntry;
        }
        return null;
    }

    @Override // io.nats.client.KeyValue
    public LinkedBlockingQueue<KeyResult> consumeKeys(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(readSubject(it.next()));
        }
        return _consumeKeys(arrayList);
    }

    @Override // io.nats.client.KeyValue
    public List<String> keys(List<String> list) throws InterruptedException, IOException, JetStreamApiException {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(readSubject(it.next()));
        }
        return _keys(arrayList);
    }

    @Override // io.nats.client.KeyValue
    public void purge(String str, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        _write(str, null, NatsKeyValueUtil.getPurgeHeaders(), NatsKeyValueUtil.getPublishOptions(-1L, messageTtl));
    }

    @Override // io.nats.client.KeyValue
    public long put(String str, Number number) throws IOException, JetStreamApiException {
        return _write(str, number.toString().getBytes(StandardCharsets.US_ASCII), null, null).getSeqno();
    }

    @Override // io.nats.client.KeyValue
    public long update(String str, String str2, long j) throws IOException, JetStreamApiException {
        return update(str, str2.getBytes(StandardCharsets.UTF_8), j);
    }

    @Override // io.nats.client.KeyValue
    public void purge(String str, long j, MessageTtl messageTtl) throws IOException, JetStreamApiException {
        _write(str, null, NatsKeyValueUtil.getPurgeHeaders(), NatsKeyValueUtil.getPublishOptions(j, messageTtl));
    }

    @Override // io.nats.client.KeyValue
    public NatsKeyValueWatchSubscription watch(String str, KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException {
        Validator.validateKvKeyWildcardAllowedRequired(str);
        Validator.validateNotNull(keyValueWatcher, "Watcher is required");
        return new NatsKeyValueWatchSubscription(this, (List<String>) Collections.singletonList(str), keyValueWatcher, j, keyValueWatchOptionArr);
    }

    @Override // io.nats.client.KeyValue
    public NatsKeyValueWatchSubscription watch(List<String> list, KeyValueWatcher keyValueWatcher, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException {
        Validator.validateKvKeysWildcardAllowedRequired(list);
        Validator.validateNotNull(keyValueWatcher, "Watcher is required");
        return new NatsKeyValueWatchSubscription(this, list, keyValueWatcher, -1L, keyValueWatchOptionArr);
    }

    @Override // io.nats.client.KeyValue
    public NatsKeyValueWatchSubscription watch(List<String> list, KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws InterruptedException, IOException, JetStreamApiException {
        Validator.validateKvKeysWildcardAllowedRequired(list);
        Validator.validateNotNull(keyValueWatcher, "Watcher is required");
        return new NatsKeyValueWatchSubscription(this, list, keyValueWatcher, j, keyValueWatchOptionArr);
    }
}
