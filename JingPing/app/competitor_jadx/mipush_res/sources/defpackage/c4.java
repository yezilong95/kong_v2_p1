package defpackage;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c4 {
    public final /* synthetic */ int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public Object e;
    public Object f;

    public c4() {
        this.a = 2;
        this.b = new int[10];
        this.c = new int[10];
        this.d = new int[10];
        this.e = new int[10];
        this.f = new int[10];
    }

    public boolean a(int i) {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            b4 b4Var = (b4) arrayList.get(i2);
            int i3 = b4Var.a;
            if (i3 != 8) {
                if (i3 == 1) {
                    int i4 = b4Var.b;
                    int i5 = b4Var.c + i4;
                    while (i4 < i5) {
                        if (f(i4, i2 + 1) == i) {
                            return true;
                        }
                        i4++;
                    }
                } else {
                    continue;
                }
            } else {
                if (f(b4Var.c, i2 + 1) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public void b() {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((bn0) this.e).a((b4) arrayList.get(i));
        }
        l(arrayList);
        ArrayList arrayList2 = (ArrayList) this.c;
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            b4 b4Var = (b4) arrayList2.get(i2);
            int i3 = b4Var.a;
            bn0 bn0Var = (bn0) this.e;
            if (i3 == 1) {
                bn0Var.a(b4Var);
                bn0Var.d(b4Var.b, b4Var.c);
            } else if (i3 == 2) {
                bn0Var.a(b4Var);
                int i4 = b4Var.b;
                int i5 = b4Var.c;
                RecyclerView recyclerView = bn0Var.a;
                recyclerView.O(i4, i5, true);
                recyclerView.K0 = true;
                recyclerView.H0.c += i5;
            } else if (i3 == 4) {
                bn0Var.a(b4Var);
                bn0Var.c(b4Var.b, b4Var.c);
            } else if (i3 == 8) {
                bn0Var.a(b4Var);
                bn0Var.e(b4Var.b, b4Var.c);
            }
        }
        l(arrayList2);
    }

    public void c(b4 b4Var) {
        int i;
        al0 al0Var;
        int i2 = b4Var.a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iP = p(b4Var.b, i2);
        int i3 = b4Var.b;
        int i4 = b4Var.a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + b4Var);
            }
            i = 1;
        }
        int i5 = 1;
        int i6 = 1;
        while (true) {
            int i7 = b4Var.c;
            al0Var = (al0) this.b;
            if (i5 >= i7) {
                break;
            }
            int iP2 = p((i * i5) + b4Var.b, b4Var.a);
            int i8 = b4Var.a;
            if (i8 == 2 ? iP2 != iP : !(i8 == 4 && iP2 == iP + 1)) {
                b4 b4VarJ = j(i8, iP, i6);
                d(b4VarJ, i3);
                al0Var.c(b4VarJ);
                if (b4Var.a == 4) {
                    i3 += i6;
                }
                i6 = 1;
                iP = iP2;
            } else {
                i6++;
            }
            i5++;
        }
        al0Var.c(b4Var);
        if (i6 > 0) {
            b4 b4VarJ2 = j(b4Var.a, iP, i6);
            d(b4VarJ2, i3);
            al0Var.c(b4VarJ2);
        }
    }

    public void d(b4 b4Var, int i) {
        bn0 bn0Var = (bn0) this.e;
        bn0Var.a(b4Var);
        int i2 = b4Var.a;
        if (i2 != 2) {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            bn0Var.c(i, b4Var.c);
        } else {
            int i3 = b4Var.c;
            RecyclerView recyclerView = bn0Var.a;
            recyclerView.O(i, i3, true);
            recyclerView.K0 = true;
            recyclerView.H0.c += i3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARN: Type inference failed for: r1v2, types: [af, android.view.ViewGroup] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(android.graphics.Canvas r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.e
            ze r0 = (defpackage.ze) r0
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
            r1.e(r9)
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
            r1.e(r9)
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
            ze r0 = (defpackage.ze) r0
            if (r0 == 0) goto L9a
            android.graphics.Rect r9 = r9.getBounds()
            java.lang.Object r0 = r8.e
            ze r0 = (defpackage.ze) r0
            float r0 = r0.a
            int r1 = r9.width()
            float r1 = (float) r1
            r3 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r3
            float r0 = r0 - r1
            java.lang.Object r1 = r8.e
            ze r1 = (defpackage.ze) r1
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c4.e(android.graphics.Canvas):void");
    }

    public int f(int i, int i2) {
        ArrayList arrayList = (ArrayList) this.d;
        int size = arrayList.size();
        while (i2 < size) {
            b4 b4Var = (b4) arrayList.get(i2);
            int i3 = b4Var.a;
            if (i3 == 8) {
                int i4 = b4Var.b;
                if (i4 == i) {
                    i = b4Var.c;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (b4Var.c <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = b4Var.b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = b4Var.c;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += b4Var.c;
                }
            }
            i2++;
        }
        return i;
    }

    public ze g() {
        ze zeVar = (ze) this.e;
        if (zeVar == null) {
            return null;
        }
        ze zeVar2 = new ze(zeVar);
        if (zeVar2.c == Float.MAX_VALUE) {
            float f = zeVar2.a;
            float f2 = zeVar2.b;
            View view = (View) this.c;
            zeVar2.c = o80.w(f, f2, view.getWidth(), view.getHeight());
        }
        return zeVar2;
    }

    public boolean h() {
        return ((ArrayList) this.c).size() > 0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [af, android.view.ViewGroup] */
    public boolean i() {
        if (((ViewGroup) this.b).h()) {
            ze zeVar = (ze) this.e;
            if (zeVar == null || zeVar.c == Float.MAX_VALUE) {
                return true;
            }
        }
        return false;
    }

    public b4 j(int i, int i2, int i3) {
        b4 b4Var = (b4) ((al0) this.b).a();
        if (b4Var != null) {
            b4Var.a = i;
            b4Var.b = i2;
            b4Var.c = i3;
            return b4Var;
        }
        b4 b4Var2 = new b4();
        b4Var2.a = i;
        b4Var2.b = i2;
        b4Var2.c = i3;
        return b4Var2;
    }

    public void k(b4 b4Var) {
        ((ArrayList) this.d).add(b4Var);
        int i = b4Var.a;
        bn0 bn0Var = (bn0) this.e;
        if (i == 1) {
            bn0Var.d(b4Var.b, b4Var.c);
            return;
        }
        if (i == 2) {
            int i2 = b4Var.b;
            int i3 = b4Var.c;
            RecyclerView recyclerView = bn0Var.a;
            recyclerView.O(i2, i3, false);
            recyclerView.K0 = true;
            return;
        }
        if (i == 4) {
            bn0Var.c(b4Var.b, b4Var.c);
        } else if (i == 8) {
            bn0Var.e(b4Var.b, b4Var.c);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + b4Var);
        }
    }

    public void l(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            b4 b4Var = (b4) arrayList.get(i);
            b4Var.getClass();
            ((al0) this.b).c(b4Var);
        }
        arrayList.clear();
    }

    public void m(Drawable drawable) {
        this.f = drawable;
        ((View) this.c).invalidate();
    }

    public void n(int i) {
        ((Paint) this.d).setColor(i);
        ((View) this.c).invalidate();
    }

    public void o(ze zeVar) {
        View view = (View) this.c;
        if (zeVar == null) {
            this.e = null;
        } else {
            ze zeVar2 = (ze) this.e;
            if (zeVar2 == null) {
                this.e = new ze(zeVar);
            } else {
                float f = zeVar.a;
                float f2 = zeVar.b;
                float f3 = zeVar.c;
                zeVar2.a = f;
                zeVar2.b = f2;
                zeVar2.c = f3;
            }
            if (zeVar.c + 1.0E-4f >= o80.w(zeVar.a, zeVar.b, view.getWidth(), view.getHeight())) {
                ((ze) this.e).c = Float.MAX_VALUE;
            }
        }
        view.invalidate();
    }

    public int p(int i, int i2) {
        int i3;
        int i4;
        ArrayList arrayList = (ArrayList) this.d;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            b4 b4Var = (b4) arrayList.get(size);
            int i5 = b4Var.a;
            if (i5 == 8) {
                int i6 = b4Var.b;
                int i7 = b4Var.c;
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
                            b4Var.b = i6 + 1;
                            b4Var.c = i7 + 1;
                        } else if (i2 == 2) {
                            b4Var.b = i6 - 1;
                            b4Var.c = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        b4Var.c = i7 + 1;
                    } else if (i2 == 2) {
                        b4Var.c = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        b4Var.b = i6 + 1;
                    } else if (i2 == 2) {
                        b4Var.b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = b4Var.b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= b4Var.c;
                    } else if (i5 == 2) {
                        i += b4Var.c;
                    }
                } else if (i2 == 1) {
                    b4Var.b = i8 + 1;
                } else if (i2 == 2) {
                    b4Var.b = i8 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            b4 b4Var2 = (b4) arrayList.get(size2);
            int i9 = b4Var2.a;
            al0 al0Var = (al0) this.b;
            if (i9 == 8) {
                int i10 = b4Var2.c;
                if (i10 == b4Var2.b || i10 < 0) {
                    arrayList.remove(size2);
                    al0Var.c(b4Var2);
                }
            } else if (b4Var2.c <= 0) {
                arrayList.remove(size2);
                al0Var.c(b4Var2);
            }
        }
        return i;
    }

    public String toString() {
        switch (this.a) {
            case 3:
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

    public c4(String str, String str2, String str3, List list) {
        this.a = 3;
        str.getClass();
        this.b = str;
        str2.getClass();
        this.c = str2;
        this.d = str3;
        list.getClass();
        this.e = list;
        this.f = str + "-" + str2 + "-" + str3;
    }

    public c4(bn0 bn0Var) {
        this.a = 0;
        this.b = new al0(30);
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = bn0Var;
        this.f = new z8(27, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c4(af afVar) {
        this.a = 1;
        this.b = (ViewGroup) afVar;
        View view = (View) afVar;
        this.c = view;
        view.setWillNotDraw(false);
        new Path();
        new Paint(7);
        Paint paint = new Paint(1);
        this.d = paint;
        paint.setColor(0);
    }
}
