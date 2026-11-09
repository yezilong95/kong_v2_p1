package com.mamimobi.videostream.model;

import defpackage.m20;
import io.nats.client.support.ApiConstants;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMessage<T, E> {
    private E extra;
    private T message;
    private MessageType messageType;

    public Type[] getClassType(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            return ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        }
        throw new IllegalArgumentException(BaseMessage.class.getName().concat("的直接子类必须指定泛型参数！"));
    }

    public E getExtra() {
        return this.extra;
    }

    public T getMessage() {
        return this.message;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public void setExtra(E e) {
        this.extra = e;
    }

    public void setMessage(T t) {
        this.message = t;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String toJson() {
        m20 m20Var = new m20();
        Type[] classType = getClassType(getClass());
        HashMap map = new HashMap();
        map.put("messageType", m20Var.g(this.messageType, MessageType.class));
        map.put(ApiConstants.MESSAGE, m20Var.g(this.message, classType[0]));
        map.put("extra", m20Var.g(this.extra, classType[1]));
        return m20Var.f(map);
    }
}
