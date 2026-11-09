package com.mamimobi.videostream.model;

import com.mamimobi.videostream.model.MessageBodyVO;

/* JADX INFO: loaded from: classes.dex */
public class MessageRecordVO<V extends MessageBodyVO> {
    private String deviceId;
    private V extraBody;
    private String intentCode;

    public MessageRecordVO() {
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public V getExtraBody() {
        return this.extraBody;
    }

    public String getIntentCode() {
        return this.intentCode;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setExtraBody(V v) {
        this.extraBody = v;
    }

    public void setIntentCode(String str) {
        this.intentCode = str;
    }

    public MessageRecordVO(String str, String str2, V v) {
        this.intentCode = str;
        this.deviceId = str2;
        this.extraBody = v;
    }
}
