package defpackage;

import java.io.File;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes.dex */
public final class cg0 implements PrivilegedAction {
    public final /* synthetic */ int a;
    public final /* synthetic */ File b;

    public /* synthetic */ cg0(File file, int i) {
        this.a = i;
        this.b = file;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        switch (this.a) {
            case 0:
                System.load(this.b.getAbsolutePath());
                break;
            default:
                System.load(this.b.getAbsolutePath());
                break;
        }
        return new Object();
    }
}
