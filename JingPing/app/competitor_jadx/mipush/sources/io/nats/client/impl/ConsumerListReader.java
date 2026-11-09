package io.nats.client.impl;

import io.nats.client.api.ConsumerInfo;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class ConsumerListReader extends AbstractListReader {
    List<ConsumerInfo> consumers;

    public ConsumerListReader() {
        super(ApiConstants.CONSUMERS);
        this.consumers = new ArrayList();
    }

    public List<ConsumerInfo> getConsumers() {
        return this.consumers;
    }

    @Override // io.nats.client.impl.AbstractListReader
    public void processItems(List<JsonValue> list) {
        Iterator<JsonValue> it = list.iterator();
        while (it.hasNext()) {
            this.consumers.add(new ConsumerInfo(it.next()));
        }
    }
}
