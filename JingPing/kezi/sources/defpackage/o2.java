package defpackage;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class o2 {
    public final /* synthetic */ int a;
    public Object b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;

    public /* synthetic */ o2(boolean z) {
        this.a = 3;
    }

    public z4 a() {
        Map mapUnmodifiableMap;
        fx fxVar = (fx) this.b;
        if (fxVar == null) {
            throw new IllegalStateException("url == null");
        }
        String str = (String) this.c;
        tv tvVarT = ((o70) this.d).t();
        d5 d5Var = (d5) this.e;
        LinkedHashMap linkedHashMap = (LinkedHashMap) this.f;
        byte[] bArr = kq0.a;
        oy.j(linkedHashMap, "<this>");
        if (linkedHashMap.isEmpty()) {
            mapUnmodifiableMap = ao.b;
        } else {
            mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(linkedHashMap));
            oy.i(mapUnmodifiableMap, "{\n    Collections.unmodi…(LinkedHashMap(this))\n  }");
        }
        return new z4(fxVar, str, tvVarT, d5Var, mapUnmodifiableMap);
    }

    public boolean b(int i) {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n2 n2Var = (n2) arrayList.get(i2);
            int i3 = n2Var.a;
            if (i3 != 8) {
                if (i3 == 1) {
                    int i4 = n2Var.b;
                    int i5 = n2Var.c + i4;
                    while (i4 < i5) {
                        if (g(i4, i2 + 1) == i) {
                            return true;
                        }
                        i4++;
                    }
                } else {
                    continue;
                }
            } else {
                if (g(n2Var.c, i2 + 1) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public void c() {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((gd0) this.e).a((n2) arrayList.get(i));
        }
        o(arrayList);
        ArrayList arrayList2 = (ArrayList) this.c;
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            n2 n2Var = (n2) arrayList2.get(i2);
            int i3 = n2Var.a;
            gd0 gd0Var = (gd0) this.e;
            if (i3 == 1) {
                gd0Var.a(n2Var);
                gd0Var.d(n2Var.b, n2Var.c);
            } else if (i3 == 2) {
                gd0Var.a(n2Var);
                int i4 = n2Var.b;
                int i5 = n2Var.c;
                RecyclerView recyclerView = gd0Var.a;
                recyclerView.O(i4, i5, true);
                recyclerView.h0 = true;
                recyclerView.e0.c += i5;
            } else if (i3 == 4) {
                gd0Var.a(n2Var);
                gd0Var.c(n2Var.b, n2Var.c);
            } else if (i3 == 8) {
                gd0Var.a(n2Var);
                gd0Var.e(n2Var.b, n2Var.c);
            }
        }
        o(arrayList2);
    }

    public void d(n2 n2Var) {
        int i;
        vb0 vb0Var;
        int i2 = n2Var.a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iT = t(n2Var.b, i2);
        int i3 = n2Var.b;
        int i4 = n2Var.a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + n2Var);
            }
            i = 1;
        }
        int i5 = 1;
        int i6 = 1;
        while (true) {
            int i7 = n2Var.c;
            vb0Var = (vb0) this.b;
            if (i5 >= i7) {
                break;
            }
            int iT2 = t((i * i5) + n2Var.b, n2Var.a);
            int i8 = n2Var.a;
            if (i8 == 2 ? iT2 != iT : !(i8 == 4 && iT2 == iT + 1)) {
                n2 n2VarM = m(i8, iT, i6);
                e(n2VarM, i3);
                vb0Var.c(n2VarM);
                if (n2Var.a == 4) {
                    i3 += i6;
                }
                i6 = 1;
                iT = iT2;
            } else {
                i6++;
            }
            i5++;
        }
        vb0Var.c(n2Var);
        if (i6 > 0) {
            n2 n2VarM2 = m(n2Var.a, iT, i6);
            e(n2VarM2, i3);
            vb0Var.c(n2VarM2);
        }
    }

    public void e(n2 n2Var, int i) {
        gd0 gd0Var = (gd0) this.e;
        gd0Var.a(n2Var);
        int i2 = n2Var.a;
        if (i2 != 2) {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            gd0Var.c(i, n2Var.c);
        } else {
            int i3 = n2Var.c;
            RecyclerView recyclerView = gd0Var.a;
            recyclerView.O(i, i3, true);
            recyclerView.h0 = true;
            recyclerView.e0.c += i3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.view.ViewGroup, gd] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(android.graphics.Canvas r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.e
            fd r0 = (defpackage.fd) r0
            if (r0 == 0) goto L12
            float r0 = r0.c
            r1 = 2139095039(0x7f7fffff, float:3.4028235E38)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L10
            goto L12
        L10:
            r0 = 0
            goto L13
        L12:
            r0 = 1
        L13:
            java.lang.Object r1 = r8.d
            r7 = r1
            android.graphics.Paint r7 = (android.graphics.Paint) r7
            java.lang.Object r1 = r8.b
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            java.lang.Object r2 = r8.c
            android.view.View r2 = (android.view.View) r2
            if (r0 != 0) goto L42
            r1.a(r9)
            int r0 = r7.getColor()
            int r0 = android.graphics.Color.alpha(r0)
            if (r0 == 0) goto L40
            int r0 = r2.getWidth()
            float r5 = (float) r0
            int r0 = r2.getHeight()
            float r6 = (float) r0
            r3 = 0
            r4 = 0
            r2 = r9
            r2.drawRect(r3, r4, r5, r6, r7)
            goto L5f
        L40:
            r2 = r9
            goto L5f
        L42:
            r1.a(r9)
            int r0 = r7.getColor()
            int r0 = android.graphics.Color.alpha(r0)
            if (r0 == 0) goto L40
            int r0 = r2.getWidth()
            float r5 = (float) r0
            int r0 = r2.getHeight()
            float r6 = (float) r0
            r3 = 0
            r4 = 0
            r2 = r9
            r2.drawRect(r3, r4, r5, r6, r7)
        L5f:
            java.lang.Object r9 = r8.f
            android.graphics.drawable.Drawable r9 = (android.graphics.drawable.Drawable) r9
            if (r9 == 0) goto L9a
            java.lang.Object r0 = r8.e
            fd r0 = (defpackage.fd) r0
            if (r0 == 0) goto L9a
            android.graphics.Rect r9 = r9.getBounds()
            java.lang.Object r0 = r8.e
            fd r0 = (defpackage.fd) r0
            float r0 = r0.a
            int r1 = r9.width()
            float r1 = (float) r1
            r3 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r3
            float r0 = r0 - r1
            java.lang.Object r1 = r8.e
            fd r1 = (defpackage.fd) r1
            float r1 = r1.b
            int r9 = r9.height()
            float r9 = (float) r9
            float r9 = r9 / r3
            float r1 = r1 - r9
            r2.translate(r0, r1)
            java.lang.Object r9 = r8.f
            android.graphics.drawable.Drawable r9 = (android.graphics.drawable.Drawable) r9
            r9.draw(r2)
            float r9 = -r0
            float r0 = -r1
            r2.translate(r9, r0)
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o2.f(android.graphics.Canvas):void");
    }

    public int g(int i, int i2) {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        while (i2 < size) {
            n2 n2Var = (n2) arrayList.get(i2);
            int i3 = n2Var.a;
            if (i3 == 8) {
                int i4 = n2Var.b;
                if (i4 == i) {
                    i = n2Var.c;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (n2Var.c <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = n2Var.b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = n2Var.c;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += n2Var.c;
                }
            }
            i2++;
        }
        return i;
    }

    public fd h() {
        fd fdVar = (fd) this.e;
        if (fdVar == null) {
            return null;
        }
        fd fdVar2 = new fd(fdVar);
        if (fdVar2.c == Float.MAX_VALUE) {
            float f = fdVar2.a;
            float f2 = fdVar2.b;
            View view = (View) this.c;
            fdVar2.c = za0.o(f, f2, view.getWidth(), view.getHeight());
        }
        return fdVar2;
    }

    public boolean i() {
        return ((ArrayList) this.c).size() > 0;
    }

    public void j(String str, String str2) {
        oy.j(str2, "value");
        o70 o70Var = (o70) this.d;
        o70Var.getClass();
        oy.f(str);
        oy.k(str2, str);
        o70Var.B(str);
        o70Var.s(str, str2);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.view.ViewGroup, gd] */
    public boolean k() {
        if (((ViewGroup) this.b).h()) {
            fd fdVar = (fd) this.e;
            if (fdVar == null || fdVar.c == Float.MAX_VALUE) {
                return true;
            }
        }
        return false;
    }

    public void l(String str, d5 d5Var) {
        oy.j(str, "method");
        if (str.length() <= 0) {
            throw new IllegalArgumentException("method.isEmpty() == true");
        }
        if (d5Var == null) {
            if (str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT")) {
                throw new IllegalArgumentException(hk.j("method ", str, " must have a request body.").toString());
            }
        } else if (!jq0.O(str)) {
            throw new IllegalArgumentException(hk.j("method ", str, " must not have a request body.").toString());
        }
        this.c = str;
        this.e = d5Var;
    }

    public n2 m(int i, int i2, int i3) {
        n2 n2Var = (n2) ((vb0) this.b).a();
        if (n2Var != null) {
            n2Var.a = i;
            n2Var.b = i2;
            n2Var.c = i3;
            return n2Var;
        }
        n2 n2Var2 = new n2();
        n2Var2.a = i;
        n2Var2.b = i2;
        n2Var2.c = i3;
        return n2Var2;
    }

    public void n(n2 n2Var) {
        ((ArrayList) this.d).add(n2Var);
        int i = n2Var.a;
        gd0 gd0Var = (gd0) this.e;
        if (i == 1) {
            gd0Var.d(n2Var.b, n2Var.c);
            return;
        }
        if (i == 2) {
            int i2 = n2Var.b;
            int i3 = n2Var.c;
            RecyclerView recyclerView = gd0Var.a;
            recyclerView.O(i2, i3, false);
            recyclerView.h0 = true;
            return;
        }
        if (i == 4) {
            gd0Var.c(n2Var.b, n2Var.c);
        } else if (i == 8) {
            gd0Var.e(n2Var.b, n2Var.c);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + n2Var);
        }
    }

    public void o(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            n2 n2Var = (n2) arrayList.get(i);
            n2Var.getClass();
            ((vb0) this.b).c(n2Var);
        }
        arrayList.clear();
    }

    public void p(Object obj, String str) {
        oy.j(str, "key");
        ((LinkedHashMap) this.b).put(str, obj);
        d80 d80Var = (d80) ((LinkedHashMap) this.d).get(str);
        if (d80Var != null) {
            ((ok0) d80Var).b(obj);
        }
        d80 d80Var2 = (d80) ((LinkedHashMap) this.e).get(str);
        if (d80Var2 != null) {
            ((ok0) d80Var2).b(obj);
        }
    }

    public void q(Drawable drawable) {
        this.f = drawable;
        ((View) this.c).invalidate();
    }

    public void r(int i) {
        ((Paint) this.d).setColor(i);
        ((View) this.c).invalidate();
    }

    public void s(fd fdVar) {
        View view = (View) this.c;
        if (fdVar == null) {
            this.e = null;
        } else {
            fd fdVar2 = (fd) this.e;
            if (fdVar2 == null) {
                this.e = new fd(fdVar);
            } else {
                float f = fdVar.a;
                float f2 = fdVar.b;
                float f3 = fdVar.c;
                fdVar2.a = f;
                fdVar2.b = f2;
                fdVar2.c = f3;
            }
            if (fdVar.c + 1.0E-4f >= za0.o(fdVar.a, fdVar.b, view.getWidth(), view.getHeight())) {
                ((fd) this.e).c = Float.MAX_VALUE;
            }
        }
        view.invalidate();
    }

    public int t(int i, int i2) {
        int i3;
        int i4;
        ArrayList arrayList = (ArrayList) this.d;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            n2 n2Var = (n2) arrayList.get(size);
            int i5 = n2Var.a;
            if (i5 == 8) {
                int i6 = n2Var.b;
                int i7 = n2Var.c;
                if (i6 < i7) {
                    i4 = i6;
                    i3 = i7;
                } else {
                    i3 = i6;
                    i4 = i7;
                }
                if (i < i4 || i > i3) {
                    if (i < i6) {
                        if (i2 == 1) {
                            n2Var.b = i6 + 1;
                            n2Var.c = i7 + 1;
                        } else if (i2 == 2) {
                            n2Var.b = i6 - 1;
                            n2Var.c = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        n2Var.c = i7 + 1;
                    } else if (i2 == 2) {
                        n2Var.c = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        n2Var.b = i6 + 1;
                    } else if (i2 == 2) {
                        n2Var.b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = n2Var.b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= n2Var.c;
                    } else if (i5 == 2) {
                        i += n2Var.c;
                    }
                } else if (i2 == 1) {
                    n2Var.b = i8 + 1;
                } else if (i2 == 2) {
                    n2Var.b = i8 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            n2 n2Var2 = (n2) arrayList.get(size2);
            int i9 = n2Var2.a;
            vb0 vb0Var = (vb0) this.b;
            if (i9 == 8) {
                int i10 = n2Var2.c;
                if (i10 == n2Var2.b || i10 < 0) {
                    arrayList.remove(size2);
                    vb0Var.c(n2Var2);
                }
            } else if (n2Var2.c <= 0) {
                arrayList.remove(size2);
                vb0Var.c(n2Var2);
            }
        }
        return i;
    }

    public String toString() {
        switch (this.a) {
            case 2:
                StringBuilder sb = new StringBuilder();
                sb.append("FontRequest {mProviderAuthority: " + ((String) this.b) + ", mProviderPackage: " + ((String) this.c) + ", mQuery: " + ((String) this.d) + ", mCertificates:");
                int i = 0;
                while (true) {
                    List list = (List) this.e;
                    if (i >= list.size()) {
                        sb.append("}mCertificatesArray: 0");
                        return sb.toString();
                    }
                    sb.append(" [");
                    List list2 = (List) list.get(i);
                    for (int i2 = 0; i2 < list2.size(); i2++) {
                        sb.append(" \"");
                        sb.append(Base64.encodeToString((byte[]) list2.get(i2), 0));
                        sb.append("\"");
                    }
                    sb.append(" ]");
                    i++;
                }
                break;
            default:
                return super.toString();
        }
    }

    public void u(String str) {
        oy.j(str, "url");
        if (dl0.j0(str, "ws:", true)) {
            String strSubstring = str.substring(3);
            oy.i(strSubstring, "this as java.lang.String).substring(startIndex)");
            str = "http:".concat(strSubstring);
        } else if (dl0.j0(str, "wss:", true)) {
            String strSubstring2 = str.substring(4);
            oy.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
            str = "https:".concat(strSubstring2);
        }
        oy.j(str, "<this>");
        ex exVar = new ex();
        exVar.c(null, str);
        this.b = exVar.a();
    }

    public o2(Map map) {
        this.a = 4;
        oy.j(map, "initialState");
        this.b = new LinkedHashMap(map);
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.e = new LinkedHashMap();
        this.f = new xe(1, this);
    }

    public o2(String str, String str2, String str3, List list) {
        this.a = 2;
        str.getClass();
        this.b = str;
        str2.getClass();
        this.c = str2;
        this.d = str3;
        list.getClass();
        this.e = list;
        this.f = str + "-" + str2 + "-" + str3;
    }

    public o2(gd0 gd0Var) {
        this.a = 0;
        this.b = new vb0(30);
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = gd0Var;
        this.f = new d90(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public o2(gd gdVar) {
        this.a = 1;
        this.b = (ViewGroup) gdVar;
        View view = (View) gdVar;
        this.c = view;
        view.setWillNotDraw(false);
        new Path();
        new Paint(7);
        Paint paint = new Paint(1);
        this.d = paint;
        paint.setColor(0);
    }

    public o2() {
        this.a = 3;
        this.f = new LinkedHashMap();
        this.c = "GET";
        this.d = new o70(23);
    }
}
