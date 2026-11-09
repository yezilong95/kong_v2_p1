package com.mamimobi.videostream.model;

import defpackage.m20;
import defpackage.m21;
import defpackage.zn;
import io.nats.client.support.ApiConstants;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Message extends BaseMessage<String, String> {
    private static final m20 gson = new m20();
    private String message;
    private MessageType messageType;

    public Message() {
    }

    @Override // com.mamimobi.videostream.model.BaseMessage
    public Type[] getClassType(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            return ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        }
        throw new IllegalArgumentException("Missing generic type for class: ".concat(cls.getName()));
    }

    @Override // com.mamimobi.videostream.model.BaseMessage
    public MessageType getMessageType() {
        return this.messageType;
    }

    @Override // com.mamimobi.videostream.model.BaseMessage
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Message{messageType=");
        sb.append(this.messageType);
        sb.append(", message='");
        return zn.o(sb, this.message, "'}");
    }

    public <T, E> BaseMessage<T, E> transForm(m21 m21Var) {
        BaseMessage<T, E> baseMessage = (BaseMessage) new m20().c(new m20().f(this), m21Var.b);
        baseMessage.setMessageType(getMessageType());
        return baseMessage;
    }

    public Message(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override // com.mamimobi.videostream.model.BaseMessage
    public String getMessage() {
        return this.message;
    }

    @Override // com.mamimobi.videostream.model.BaseMessage
    public void setMessage(String str) {
        this.message = str;
    }

    public Message(MessageType messageType, String str) {
        this.messageType = messageType;
        this.message = str;
    }

    public Message(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("messageType", null);
            String strOptString2 = jSONObject.optString(ApiConstants.MESSAGE, null);
            String strOptString3 = jSONObject.optString("extra", null);
            if (strOptString != null) {
                setMessageType((MessageType) gson.b(MessageType.class, strOptString));
            }
            setMessage(strOptString2);
            setExtra(strOptString3);
        } catch (JSONException e) {
            throw new RuntimeException("Invalid JSON format for Message", e);
        }
    }
}
