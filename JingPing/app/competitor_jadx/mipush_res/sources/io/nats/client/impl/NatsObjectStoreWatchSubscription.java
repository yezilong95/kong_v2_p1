package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.ObjectInfo;
import io.nats.client.api.ObjectStoreWatchOption;
import io.nats.client.api.ObjectStoreWatcher;
import io.nats.client.impl.NatsWatchSubscription;
import java.io.IOException;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class NatsObjectStoreWatchSubscription extends NatsWatchSubscription<ObjectInfo> {

    /* JADX INFO: renamed from: io.nats.client.impl.NatsObjectStoreWatchSubscription$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$nats$client$api$ObjectStoreWatchOption;

        static {
            int[] iArr = new int[ObjectStoreWatchOption.values().length];
            $SwitchMap$io$nats$client$api$ObjectStoreWatchOption = iArr;
            try {
                iArr[ObjectStoreWatchOption.IGNORE_DELETE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$nats$client$api$ObjectStoreWatchOption[ObjectStoreWatchOption.UPDATES_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$nats$client$api$ObjectStoreWatchOption[ObjectStoreWatchOption.INCLUDE_HISTORY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public NatsObjectStoreWatchSubscription(NatsObjectStore natsObjectStore, final ObjectStoreWatcher objectStoreWatcher, ObjectStoreWatchOption... objectStoreWatchOptionArr) throws IOException, JetStreamApiException {
        DeliverPolicy deliverPolicy;
        super(natsObjectStore.js);
        DeliverPolicy deliverPolicy2 = DeliverPolicy.LastPerSubject;
        boolean z = false;
        for (ObjectStoreWatchOption objectStoreWatchOption : objectStoreWatchOptionArr) {
            if (objectStoreWatchOption != null) {
                int i = AnonymousClass2.$SwitchMap$io$nats$client$api$ObjectStoreWatchOption[objectStoreWatchOption.ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        deliverPolicy = DeliverPolicy.New;
                    } else if (i == 3) {
                        deliverPolicy = DeliverPolicy.All;
                    }
                    deliverPolicy2 = deliverPolicy;
                } else {
                    z = true;
                }
            }
        }
        final boolean z2 = !z;
        finishInit(natsObjectStore, Collections.singletonList(natsObjectStore.rawAllMetaSubject()), deliverPolicy2, false, 0L, new NatsWatchSubscription.WatchMessageHandler<ObjectInfo>(objectStoreWatcher) { // from class: io.nats.client.impl.NatsObjectStoreWatchSubscription.1
            @Override // io.nats.client.MessageHandler
            public void onMessage(Message message) throws InterruptedException {
                ObjectInfo objectInfo = new ObjectInfo(message);
                if (z2 || !objectInfo.isDeleted()) {
                    objectStoreWatcher.watch(objectInfo);
                }
                if (this.endOfDataSent || message.metaData().pendingCount() != 0) {
                    return;
                }
                sendEndOfData();
            }
        }, objectStoreWatcher.getConsumerNamePrefix());
    }
}
