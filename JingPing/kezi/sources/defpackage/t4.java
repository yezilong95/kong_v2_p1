package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public final class t4 extends BroadcastReceiver {
    public final /* synthetic */ u4 a;

    public t4(u4 u4Var) {
        this.a = u4Var;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.a.h();
    }
}
