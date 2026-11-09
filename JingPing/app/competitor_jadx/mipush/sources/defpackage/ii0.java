package defpackage;

import android.app.Notification;

/* JADX INFO: loaded from: classes.dex */
public final class ii0 {
    public final String a;
    public final Notification b;

    public ii0(String str, Notification notification) {
        this.a = str;
        this.b = notification;
    }

    public final String toString() {
        return zn.o(new StringBuilder("NotifyTask[packageName:"), this.a, ", id:101, tag:null]");
    }
}
