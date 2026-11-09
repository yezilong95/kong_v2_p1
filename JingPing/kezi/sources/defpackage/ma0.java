package defpackage;

import android.window.OnBackInvokedCallback;

/* JADX INFO: loaded from: classes.dex */
public final class ma0 {
    public static final ma0 a = new ma0();

    public final OnBackInvokedCallback a(pu puVar, pu puVar2, ou ouVar, ou ouVar2) {
        oy.j(puVar, "onBackStarted");
        oy.j(puVar2, "onBackProgressed");
        oy.j(ouVar, "onBackInvoked");
        oy.j(ouVar2, "onBackCancelled");
        return new la0(puVar, puVar2, ouVar, ouVar2);
    }
}
