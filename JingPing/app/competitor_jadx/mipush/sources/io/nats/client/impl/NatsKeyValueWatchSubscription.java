package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.api.DeliverPolicy;
import io.nats.client.api.KeyValueEntry;
import io.nats.client.api.KeyValueOperation;
import io.nats.client.api.KeyValueWatchOption;
import io.nats.client.api.KeyValueWatcher;
import io.nats.client.impl.NatsWatchSubscription;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NatsKeyValueWatchSubscription extends NatsWatchSubscription<KeyValueEntry> {

    /* JADX INFO: renamed from: io.nats.client.impl.NatsKeyValueWatchSubscription$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$nats$client$api$KeyValueWatchOption;

        static {
            int[] iArr = new int[KeyValueWatchOption.values().length];
            $SwitchMap$io$nats$client$api$KeyValueWatchOption = iArr;
            try {
                iArr[KeyValueWatchOption.META_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$nats$client$api$KeyValueWatchOption[KeyValueWatchOption.IGNORE_DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$nats$client$api$KeyValueWatchOption[KeyValueWatchOption.UPDATES_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$nats$client$api$KeyValueWatchOption[KeyValueWatchOption.INCLUDE_HISTORY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public NatsKeyValueWatchSubscription(NatsKeyValue natsKeyValue, String str, KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws IOException, JetStreamApiException {
        this(natsKeyValue, (List<String>) Collections.singletonList(str), keyValueWatcher, j, keyValueWatchOptionArr);
    }

    private void kvWatchInit(NatsKeyValue natsKeyValue, List<String> list, final KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption[] keyValueWatchOptionArr) throws IOException, JetStreamApiException {
        DeliverPolicy deliverPolicy;
        DeliverPolicy deliverPolicy2 = DeliverPolicy.LastPerSubject;
        boolean z = false;
        boolean z2 = false;
        for (KeyValueWatchOption keyValueWatchOption : keyValueWatchOptionArr) {
            if (keyValueWatchOption != null) {
                int i = AnonymousClass2.$SwitchMap$io$nats$client$api$KeyValueWatchOption[keyValueWatchOption.ordinal()];
                if (i == 1) {
                    z2 = true;
                } else if (i != 2) {
                    if (i == 3) {
                        deliverPolicy = DeliverPolicy.New;
                    } else if (i == 4) {
                        deliverPolicy = DeliverPolicy.All;
                    }
                    deliverPolicy2 = deliverPolicy;
                } else {
                    z = true;
                }
            }
        }
        final boolean z3 = !z;
        NatsWatchSubscription.WatchMessageHandler<KeyValueEntry> watchMessageHandler = new NatsWatchSubscription.WatchMessageHandler<KeyValueEntry>(keyValueWatcher) { // from class: io.nats.client.impl.NatsKeyValueWatchSubscription.1
            @Override // io.nats.client.MessageHandler
            public void onMessage(Message message) throws InterruptedException {
                KeyValueEntry keyValueEntry = new KeyValueEntry(message);
                if (z3 || keyValueEntry.getOperation() == KeyValueOperation.PUT) {
                    keyValueWatcher.watch(keyValueEntry);
                }
                if (this.endOfDataSent || keyValueEntry.getDelta() != 0) {
                    return;
                }
                sendEndOfData();
            }
        };
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(natsKeyValue.readSubject(it.next().trim()));
        }
        finishInit(natsKeyValue, arrayList, deliverPolicy2, z2, j, watchMessageHandler, keyValueWatcher.getConsumerNamePrefix());
    }

    public NatsKeyValueWatchSubscription(NatsKeyValue natsKeyValue, List<String> list, KeyValueWatcher keyValueWatcher, long j, KeyValueWatchOption... keyValueWatchOptionArr) throws IOException, JetStreamApiException {
        super(natsKeyValue.js);
        kvWatchInit(natsKeyValue, list, keyValueWatcher, j, keyValueWatchOptionArr);
    }
}
