package com.mamimobi.videostream.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class MessageBodyVO implements Serializable {
    private Long timestamp = Long.valueOf(System.nanoTime());

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }
}
