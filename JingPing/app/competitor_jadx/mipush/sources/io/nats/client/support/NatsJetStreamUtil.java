package io.nats.client.support;

import defpackage.zn;
import io.nats.client.NUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class NatsJetStreamUtil {
    private NatsJetStreamUtil() {
    }

    public static String generateConsumerName() {
        return NUID.nextGlobal();
    }

    public static String generateConsumerName(String str) {
        StringBuilder sbQ = zn.q(str, "-");
        sbQ.append(NUID.nextGlobal());
        return sbQ.toString();
    }
}
