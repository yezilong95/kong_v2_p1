package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractListReader {
    protected ListRequestEngine engine;
    private final String filterFieldName;
    private final String objectName;

    public AbstractListReader(String str) {
        this(str, null);
    }

    public boolean hasMore() {
        return this.engine.hasMore();
    }

    public byte[] nextJson() {
        return this.engine.internalNextJson();
    }

    public void process(Message message) throws JetStreamApiException {
        ListRequestEngine listRequestEngine = new ListRequestEngine(message);
        this.engine = listRequestEngine;
        processItems(JsonValueUtils.readArray(listRequestEngine.getJv(), this.objectName));
    }

    public abstract void processItems(List<JsonValue> list);

    public AbstractListReader(String str, String str2) {
        this.objectName = str;
        this.filterFieldName = str2;
        this.engine = new ListRequestEngine();
    }

    public byte[] nextJson(String str) {
        String str2 = this.filterFieldName;
        if (str2 != null) {
            return this.engine.internalNextJson(str2, str);
        }
        throw new IllegalArgumentException("Filter not supported.");
    }
}
