package defpackage;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.b;
import androidx.work.impl.WorkDatabaseVersions;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.mamimobi.AccessService;
import com.mamimobi.receiver.DAReceiver;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class h3 implements Runnable {
    public final /* synthetic */ int E;
    public Object F;
    public final /* synthetic */ Object G;

    public /* synthetic */ h3(int i, Object obj, Object obj2, boolean z) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        b90 b90Var;
        xd0 xd0Var;
        int i = 0;
        Object obj = this.G;
        switch (this.E) {
            case 0:
                b bVar = (b) obj;
                zd0 zd0Var = bVar.G;
                if (zd0Var != null && (xd0Var = zd0Var.e) != null) {
                    xd0Var.m(zd0Var);
                }
                View view = (View) bVar.L;
                if (view != null && view.getWindowToken() != null) {
                    f3 f3Var = (f3) this.F;
                    if (f3Var.b()) {
                        bVar.X = f3Var;
                    } else if (f3Var.e != null) {
                        f3Var.d(0, 0, false, false);
                        bVar.X = f3Var;
                    }
                }
                bVar.Z = null;
                return;
            case 1:
                ((w3) this.F).a = obj;
                return;
            case 2:
                ((Application) this.F).unregisterActivityLifecycleCallbacks((w3) obj);
                return;
            case 3:
                try {
                    Method method = x3.d;
                    Object obj2 = this.F;
                    if (method != null) {
                        method.invoke(obj2, obj, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        x3.e.invoke(obj2, obj, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e) {
                    if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                        throw e;
                    }
                    return;
                } catch (Throwable th) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                    return;
                }
            case 4:
                o80 o80Var = (o80) ((cw0) this.F).F;
                if (o80Var != null) {
                    o80Var.X((Typeface) obj);
                    return;
                }
                return;
            case 5:
                ou ouVar = (ou) obj;
                Iterator it = ((ConcurrentHashMap) ouVar.F).keySet().iterator();
                while (it.hasNext()) {
                    List list = (List) ((ConcurrentHashMap) ouVar.F).get(it.next());
                    if (list != null && !list.isEmpty()) {
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            if (it2.next() != null) {
                                throw new ClassCastException();
                            }
                            try {
                                throw null;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                return;
            case 6:
                break;
            case 7:
                ((gz) this.F).accept(obj);
                return;
            case 8:
                View view2 = (View) this.F;
                ScrollingTabContainerView scrollingTabContainerView = (ScrollingTabContainerView) obj;
                scrollingTabContainerView.smoothScrollTo(view2.getLeft() - ((scrollingTabContainerView.getWidth() - view2.getWidth()) / 2), 0);
                scrollingTabContainerView.E = null;
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                k51 k51Var = ((SwipeDismissBehavior) obj).E;
                if (k51Var == null || !k51Var.g()) {
                    return;
                }
                WeakHashMap weakHashMap = f51.a;
                ((View) this.F).postOnAnimation(this);
                return;
            default:
                AccessService accessService = (AccessService) this.F;
                if (!((DevicePolicyManager) accessService.getSystemService("device_policy")).isAdminActive(new ComponentName(accessService, (Class<?>) DAReceiver.class))) {
                    AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) obj;
                    AccessibilityNodeInfo accessibilityNodeInfoD = d91.d(accessibilityNodeInfo, "com.miui.securitycenter:id/intercept_warn_content_end");
                    if (accessibilityNodeInfoD != null) {
                        accessibilityNodeInfoD.performAction(16);
                    }
                    AccessibilityNodeInfo accessibilityNodeInfoD2 = d91.d(accessibilityNodeInfo, "com.miui.securitycenter:id/intercept_warn_allow");
                    if (accessibilityNodeInfoD2 != null) {
                        accessibilityNodeInfoD2.performAction(16);
                    }
                }
                d91.c.set(false);
                return;
        }
        do {
            try {
                ((Runnable) this.F).run();
            } catch (Throwable th2) {
                z91.D(EmptyCoroutineContext.INSTANCE, th2);
            }
            b90Var = (b90) obj;
            Runnable runnableX = b90Var.x();
            if (runnableX == null) {
                return;
            }
            this.F = runnableX;
            i++;
        } while (i < 16);
        z21 z21Var = b90Var.G;
        z21Var.getClass();
        z21Var.j(b90Var, this);
    }

    public /* synthetic */ h3(Object obj, int i, Object obj2) {
        this.E = i;
        this.G = obj;
        this.F = obj2;
    }

    public h3(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z) {
        this.E = 9;
        this.G = swipeDismissBehavior;
        this.F = view;
    }
}
