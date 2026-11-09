package io.nats.client.impl;

import io.nats.client.support.ApiConstants;

/* JADX INFO: loaded from: classes.dex */
class ConsumerNamesReader extends StringListReader {
    public ConsumerNamesReader() {
        super(ApiConstants.CONSUMERS, ApiConstants.SUBJECT);
    }
}
