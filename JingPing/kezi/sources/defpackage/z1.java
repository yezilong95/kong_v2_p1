package defpackage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Trace;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle$Event;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.mamimobi.shell.service.MyVpnService;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.EglBase10Impl;
import org.webrtc.EglBase14Impl;
import org.webrtc.EglRenderer;
import org.webrtc.ScreenCapturerAndroid;
import org.webrtc.VideoFileRenderer;
import org.webrtc.VideoFrame;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z1 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ z1(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [int] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.os.Handler] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.os.Handler] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        int i = 1;
        int i2 = 2;
        boolean z = false;
        switch (this.b) {
            case 0:
                Activity activity = (Activity) this.c;
                if (activity.isFinishing()) {
                    return;
                }
                ?? r5 = Build.VERSION.SDK_INT;
                if (r5 >= 28) {
                    Class cls = d2.a;
                    activity.recreate();
                    return;
                }
                Class cls2 = d2.a;
                ?? r6 = 27;
                ?? r7 = 26;
                boolean z2 = r5 == 26 || r5 == 27;
                Method method = d2.f;
                if ((!z2 || method != null) && (d2.e != null || d2.d != null)) {
                    try {
                        Object obj2 = d2.c.get(activity);
                        if (obj2 != null && (obj = d2.b.get(activity)) != null) {
                            Application application = activity.getApplication();
                            c2 c2Var = new c2(activity);
                            application.registerActivityLifecycleCallbacks(c2Var);
                            Handler handler = d2.g;
                            handler.post(new m1(c2Var, obj2, i, z));
                            if (r5 != 26 && r5 != 27) {
                                i = 0;
                            }
                            try {
                                if (i != 0) {
                                    Handler handler2 = handler;
                                    try {
                                        Boolean bool = Boolean.FALSE;
                                        r6 = application;
                                        r7 = c2Var;
                                        method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                                        r5 = handler2;
                                    } catch (Throwable th) {
                                        th = th;
                                        ?? r62 = application;
                                        ?? r72 = c2Var;
                                        ?? r52 = handler2;
                                        r52.post(new m1(r62, r72, i2, z));
                                        throw th;
                                    }
                                } else {
                                    r6 = application;
                                    r7 = c2Var;
                                    r5 = handler;
                                    activity.recreate();
                                }
                                r5.post(new m1(r6, r7, i2, z));
                                return;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                activity.recreate();
                return;
            case 1:
                ((rd) this.c).t(true);
                return;
            case 2:
                ((ComponentActivity) this.c).invalidateOptionsMenu();
                return;
            case 3:
                df dfVar = (df) this.c;
                Runnable runnable = dfVar.c;
                if (runnable != null) {
                    runnable.run();
                    dfVar.c = null;
                    return;
                }
                return;
            case 4:
                x2.a((x2) this.c);
                return;
            case 5:
                ConstraintTrackingWorker.startWork$lambda$0((ConstraintTrackingWorker) this.c);
                return;
            case 6:
                EditText editText = (EditText) this.c;
                editText.requestFocus();
                editText.post(new s8(editText, 2));
                return;
            case 7:
                gm gmVar = (gm) this.c;
                boolean zIsPopupShowing = gmVar.h.isPopupShowing();
                gmVar.t(zIsPopupShowing);
                gmVar.m = zIsPopupShowing;
                return;
            case 8:
                ((EglBase10Impl.EglConnection) this.c).lambda$new$0();
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                ((EglBase14Impl.EglConnection) this.c).lambda$new$0();
                return;
            case WorkDatabaseVersions.VERSION_10 /* 10 */:
                ((EglRenderer) this.c).renderFrameOnRenderThread();
                return;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                ((EglBase.EglConnection) this.c).release();
                return;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                ps psVar = (ps) this.c;
                synchronized (psVar.d) {
                    try {
                        if (psVar.h == null) {
                            return;
                        }
                        try {
                            at atVarC = psVar.c();
                            int i3 = atVarC.e;
                            if (i3 == 2) {
                                synchronized (psVar.d) {
                                }
                            }
                            if (i3 != 0) {
                                throw new RuntimeException("fetchFonts result is not OK. (" + i3 + ")");
                            }
                            try {
                                int i4 = ap0.a;
                                Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                yo yoVar = psVar.c;
                                Context context = psVar.a;
                                yoVar.getClass();
                                Typeface typefaceP = sp0.a.p(context, new at[]{atVarC}, 0);
                                MappedByteBuffer mappedByteBufferL = jq0.L(psVar.a, atVarC.a);
                                if (mappedByteBufferL == null || typefaceP == null) {
                                    throw new RuntimeException("Unable to open file.");
                                }
                                try {
                                    Trace.beginSection("EmojiCompat.MetadataRepo.create");
                                    ba baVar = new ba(typefaceP, nv0.H(mappedByteBufferL));
                                    Trace.endSection();
                                    Trace.endSection();
                                    synchronized (psVar.d) {
                                        try {
                                            za0 za0Var = psVar.h;
                                            if (za0Var != null) {
                                                za0Var.I(baVar);
                                            }
                                        } finally {
                                        }
                                        break;
                                    }
                                    psVar.b();
                                    return;
                                } finally {
                                    int i5 = ap0.a;
                                    Trace.endSection();
                                }
                            } catch (Throwable th3) {
                                throw th3;
                            }
                            break;
                        } catch (Throwable th4) {
                            synchronized (psVar.d) {
                                try {
                                    za0 za0Var2 = psVar.h;
                                    if (za0Var2 != null) {
                                        za0Var2.F(th4);
                                    }
                                    psVar.b();
                                    return;
                                } finally {
                                }
                            }
                        }
                    } finally {
                    }
                }
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                ((VideoFrame.I420Buffer) this.c).release();
                return;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                ((a40) this.c).c.finish();
                return;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                MyVpnService myVpnService = (MyVpnService) this.c;
                int i6 = MyVpnService.e;
                try {
                    FileInputStream fileInputStream = new FileInputStream(myVpnService.b.getFileDescriptor());
                    try {
                        byte[] bArr = new byte[32767];
                        while (myVpnService.d) {
                            fileInputStream.read(bArr);
                            break;
                        }
                        fileInputStream.close();
                        return;
                    } finally {
                    }
                } catch (IOException unused2) {
                    return;
                }
            case 16:
                po poVar = (po) this.c;
                x40 x40Var = (x40) poVar.c;
                if (x40Var != null) {
                    x40Var.b((w40) poVar.d, (FrameLayout) poVar.e, true);
                    return;
                }
                return;
            case 17:
                ec0 ec0Var = (ec0) this.c;
                int i7 = ec0Var.c;
                w10 w10Var = ec0Var.g;
                if (i7 == 0) {
                    ec0Var.d = true;
                    w10Var.e(Lifecycle$Event.ON_PAUSE);
                }
                if (ec0Var.b == 0 && ec0Var.d) {
                    w10Var.e(Lifecycle$Event.ON_STOP);
                    ec0Var.e = true;
                    return;
                }
                return;
            case 18:
                ((ScreenCapturerAndroid) this.c).updateVirtualDisplay();
                return;
            case 19:
                g9 g9Var = (g9) this.c;
                g9Var.c = false;
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) g9Var.e;
                cs0 cs0Var = sideSheetBehavior.j;
                if (cs0Var != null && cs0Var.g()) {
                    g9Var.a(g9Var.b);
                    return;
                } else {
                    if (sideSheetBehavior.i == 2) {
                        sideSheetBehavior.x(g9Var.b);
                        return;
                    }
                    return;
                }
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                ((CountDownLatch) this.c).countDown();
                return;
            default:
                ((VideoFileRenderer) this.c).lambda$release$3();
                return;
        }
    }
}
