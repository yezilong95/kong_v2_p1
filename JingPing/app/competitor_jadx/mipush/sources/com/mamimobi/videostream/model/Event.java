package com.mamimobi.videostream.model;

/* JADX INFO: loaded from: classes.dex */
public class Event {
    public int code;
    public String message;
    public Object objA;
    public Object objB;

    public Event() {
    }

    public static Event error(int i, String str) {
        Event event = new Event();
        event.code = i;
        event.message = str;
        return event;
    }

    public String getRawJson() {
        Object obj = this.objB;
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public SignalingMessage getSignaling() {
        Object obj = this.objA;
        if (obj instanceof SignalingMessage) {
            return (SignalingMessage) obj;
        }
        return null;
    }

    public String toString() {
        return "Event{code=" + this.code + ", message='" + this.message + "', objA=" + this.objA + ", objB=" + this.objB + '}';
    }

    public Event(Object obj) {
        this.objA = obj;
    }

    public Event(Object obj, Object obj2) {
        this.objA = obj;
        this.objB = obj2;
    }

    public Event(SignalingMessage signalingMessage, String str) {
        this.objA = signalingMessage;
        this.objB = str;
    }
}
