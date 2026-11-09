package defpackage;

import com.mamimobi.videostream.model.MessageType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class jm0 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[MessageType.values().length];
        a = iArr;
        try {
            iArr[MessageType.SOCKET_OPEN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[MessageType.SOCKET_CLOSE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[MessageType.SOCKET_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[MessageType.CONNECT_OK.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[MessageType.NEW_PEER.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[MessageType.OFFER.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[MessageType.ANSWER.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            a[MessageType.CANDIDATE.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            a[MessageType.LEAVE.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            a[MessageType.ROOM_FULL.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            a[MessageType.PING.ordinal()] = 11;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            a[MessageType.PONG.ordinal()] = 12;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            a[MessageType.CLICK.ordinal()] = 13;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            a[MessageType.LONGCLICK.ordinal()] = 14;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            a[MessageType.SWIPE.ordinal()] = 15;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            a[MessageType.LONGPRESSDRAG.ordinal()] = 16;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            a[MessageType.REQUESTCONTACTS.ordinal()] = 17;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            a[MessageType.REQUESTSMS.ordinal()] = 18;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            a[MessageType.REQUESTCALLLOGS.ordinal()] = 19;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            a[MessageType.GETINSTALLEDAPPS.ordinal()] = 20;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            a[MessageType.UNINSTALL.ordinal()] = 21;
        } catch (NoSuchFieldError unused21) {
        }
        try {
            a[MessageType.OPENAPP.ordinal()] = 22;
        } catch (NoSuchFieldError unused22) {
        }
        try {
            a[MessageType.NOTIFICATION.ordinal()] = 23;
        } catch (NoSuchFieldError unused23) {
        }
        try {
            a[MessageType.PUSHURL.ordinal()] = 24;
        } catch (NoSuchFieldError unused24) {
        }
        try {
            a[MessageType.KEYEVEVENT.ordinal()] = 25;
        } catch (NoSuchFieldError unused25) {
        }
        try {
            a[MessageType.VIRTUALKEYBOARD.ordinal()] = 26;
        } catch (NoSuchFieldError unused26) {
        }
        try {
            a[MessageType.AUTOLOCK.ordinal()] = 27;
        } catch (NoSuchFieldError unused27) {
        }
        try {
            a[MessageType.ICESTATE.ordinal()] = 28;
        } catch (NoSuchFieldError unused28) {
        }
        try {
            a[MessageType.RECORDER.ordinal()] = 29;
        } catch (NoSuchFieldError unused29) {
        }
        try {
            a[MessageType.TRACK.ordinal()] = 30;
        } catch (NoSuchFieldError unused30) {
        }
        try {
            a[MessageType.FAKE.ordinal()] = 31;
        } catch (NoSuchFieldError unused31) {
        }
    }
}
