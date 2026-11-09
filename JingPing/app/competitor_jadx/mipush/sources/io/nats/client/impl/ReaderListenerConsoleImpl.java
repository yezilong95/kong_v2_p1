package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.Message;
import io.nats.client.ReadListener;
import io.nats.client.support.NatsConstants;

/* JADX INFO: loaded from: classes.dex */
public class ReaderListenerConsoleImpl implements ReadListener {
    @Override // io.nats.client.ReadListener
    public void message(String str, Message message) {
        String str2;
        StringBuilder sbQ = zn.q(str, NatsConstants.SPACE);
        sbQ.append(message.getSubject());
        sbQ.append(NatsConstants.SPACE);
        sbQ.append(message.getReplyTo());
        sbQ.append(NatsConstants.SPACE);
        if (message.getData() == null) {
            str2 = "<no data>";
        } else {
            str2 = "data length: " + message.getData().length;
        }
        sbQ.append(str2);
        String string = sbQ.toString();
        if (message.isJetStream()) {
            System.out.println("RL/JS-Message " + string);
            return;
        }
        System.out.println("RL/Message " + string);
    }

    @Override // io.nats.client.ReadListener
    public void protocol(String str, String str2) {
        System.out.println("RL/Protocol " + str + NatsConstants.SPACE + str2);
    }
}
