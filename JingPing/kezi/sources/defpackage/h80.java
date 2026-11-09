package defpackage;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class h80 {
    public final /* synthetic */ int a;
    public final jx b;

    public h80(int i) {
        this.a = i;
        switch (i) {
            case 1:
                this.b = new gl0();
                new ConcurrentHashMap();
                new ThreadLocal();
                new k8();
                break;
            default:
                this.b = new yo(7);
                new ConcurrentHashMap();
                break;
        }
    }
}
