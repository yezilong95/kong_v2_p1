package defpackage;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.cardview.widget.CardView;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.WeakHashMap;
import javax.net.ssl.SSLSocket;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public class qm implements mh, q30, y60, zj, ht0, el {
    public static final qm b = new qm();
    public static final /* synthetic */ qm c = new qm();
    public static final qm d = new qm();
    public static final /* synthetic */ qm e = new qm();
    public static final /* synthetic */ qm f = new qm();
    public static final qm g = new qm();
    public static final qm h = new qm();
    public static final qm i = new qm();
    public static final qm j = new qm();
    public static final qm k = new qm();

    public static final rc e(qm qmVar, String str) {
        rc rcVar = new rc(str);
        rc.d.put(str, rcVar);
        return rcVar;
    }

    public static final gf0 g(gf0 gf0Var) {
        if ((gf0Var != null ? gf0Var.h : null) == null) {
            return gf0Var;
        }
        ff0 ff0VarT = gf0Var.t();
        ff0VarT.g = null;
        return ff0VarT.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0045, code lost:
    
        if (java.lang.Character.isHighSurrogate(r5) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0082, code lost:
    
        if (java.lang.Character.isLowSurrogate(r5) != false) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006c A[EDGE_INSN: B:92:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085], EDGE_INSN: B:93:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085, LOOP_LABEL: LOOP:2: B:47:0x006e->B:58:0x0085]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a2 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean l(defpackage.mn r7, android.text.Editable r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qm.l(mn, android.text.Editable, int, int, boolean):boolean");
    }

    public static boolean m(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    @Override // defpackage.zj
    public boolean b(SSLSocket sSLSocket) {
        return dl0.j0(sSLSocket.getClass().getName(), "com.google.android.gms.org.conscrypt.", false);
    }

    @Override // defpackage.zj
    public oj0 c(SSLSocket sSLSocket) {
        Class<?> cls = sSLSocket.getClass();
        Class<?> superclass = cls;
        while (!superclass.getSimpleName().equals("OpenSSLSocketImpl")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
            }
        }
        return new i3(superclass);
    }

    @Override // defpackage.el
    public List d(String str) throws UnknownHostException {
        oy.j(str, "hostname");
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            oy.i(allByName, "getAllByName(hostname)");
            int length = allByName.length;
            return length != 0 ? length != 1 ? new ArrayList(new y6(allByName, false)) : xi.G(allByName[0]) : EmptyList.INSTANCE;
        } catch (NullPointerException e2) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of ".concat(str));
            unknownHostException.initCause(e2);
            throw unknownHostException;
        }
    }

    @Override // defpackage.ht0
    public xu0 f(View view, xu0 xu0Var, it0 it0Var) {
        it0Var.d = xu0Var.a() + it0Var.d;
        WeakHashMap weakHashMap = xr0.a;
        boolean z = view.getLayoutDirection() == 1;
        int iB = xu0Var.b();
        int iC = xu0Var.c();
        int i2 = it0Var.a + (z ? iC : iB);
        it0Var.a = i2;
        int i3 = it0Var.c;
        if (!z) {
            iB = iC;
        }
        int i4 = i3 + iB;
        it0Var.c = i4;
        view.setPaddingRelative(i2, it0Var.b, i4, it0Var.d);
        return xu0Var;
    }

    public synchronized rc h(String str) {
        rc rcVar;
        String strConcat;
        try {
            oy.j(str, "javaName");
            LinkedHashMap linkedHashMap = rc.d;
            rcVar = (rc) linkedHashMap.get(str);
            if (rcVar == null) {
                if (dl0.j0(str, "TLS_", false)) {
                    String strSubstring = str.substring(4);
                    oy.i(strSubstring, "this as java.lang.String).substring(startIndex)");
                    strConcat = "SSL_".concat(strSubstring);
                } else if (dl0.j0(str, "SSL_", false)) {
                    String strSubstring2 = str.substring(4);
                    oy.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
                    strConcat = "TLS_".concat(strSubstring2);
                } else {
                    strConcat = str;
                }
                rcVar = (rc) linkedHashMap.get(strConcat);
                if (rcVar == null) {
                    rcVar = new rc(str);
                }
                linkedHashMap.put(str, rcVar);
            }
        } catch (Throwable th) {
            throw th;
        }
        return rcVar;
    }

    public boolean i() {
        return this instanceof q40;
    }

    public void j(float f2, float f3, float f4, ui0 ui0Var) {
        ui0Var.d(f2, 0.0f);
    }

    public Signature[] k(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }

    public void n(float f2, e5 e5Var) {
        wf0 wf0Var = (wf0) ((Drawable) e5Var.c);
        CardView cardView = (CardView) e5Var.d;
        boolean useCompatPadding = cardView.getUseCompatPadding();
        boolean preventCornerOverlap = cardView.getPreventCornerOverlap();
        if (f2 != wf0Var.e || wf0Var.f != useCompatPadding || wf0Var.g != preventCornerOverlap) {
            wf0Var.e = f2;
            wf0Var.f = useCompatPadding;
            wf0Var.g = preventCornerOverlap;
            wf0Var.b(null);
            wf0Var.invalidateSelf();
        }
        if (!cardView.getUseCompatPadding()) {
            e5Var.R(0, 0, 0, 0);
            return;
        }
        wf0 wf0Var2 = (wf0) ((Drawable) e5Var.c);
        float f3 = wf0Var2.e;
        float f4 = wf0Var2.a;
        int iCeil = (int) Math.ceil(xf0.a(f3, f4, cardView.getPreventCornerOverlap()));
        int iCeil2 = (int) Math.ceil(xf0.b(f3, f4, cardView.getPreventCornerOverlap()));
        e5Var.R(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // defpackage.y60
    public boolean p(i60 i60Var) {
        return false;
    }

    @Override // defpackage.y60
    public void a(i60 i60Var, boolean z) {
    }
}
