package io.nats.client.impl;

import io.nats.client.api.StreamInfo;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class StreamListReader extends AbstractListReader {
    List<StreamInfo> streams;

    public StreamListReader() {
        super(ApiConstants.STREAMS, ApiConstants.SUBJECT);
        this.streams = new ArrayList();
    }

    public List<StreamInfo> getStreams() {
        return this.streams;
    }

    @Override // io.nats.client.impl.AbstractListReader
    public void processItems(List<JsonValue> list) {
        Iterator<JsonValue> it = list.iterator();
        while (it.hasNext()) {
            this.streams.add(new StreamInfo(it.next()));
        }
    }
}
