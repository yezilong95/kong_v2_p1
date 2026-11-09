package io.nats.client.impl;

import io.nats.client.support.ApiConstants;

/* JADX INFO: loaded from: classes.dex */
class StreamNamesReader extends StringListReader {
    public StreamNamesReader() {
        super(ApiConstants.STREAMS, ApiConstants.SUBJECT);
    }
}
