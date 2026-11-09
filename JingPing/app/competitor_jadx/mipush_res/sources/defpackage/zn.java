package defpackage;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class zn {
    public static final void a(View view, int i) {
        int iW = w(i);
        if (iW == 0) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                if (c.B(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                }
                viewGroup.removeView(view);
                return;
            }
            return;
        }
        if (iW == 1) {
            if (c.B(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
            }
            view.setVisibility(0);
            return;
        }
        if (iW == 2) {
            if (c.B(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
            }
            view.setVisibility(8);
            return;
        }
        if (iW != 3) {
            return;
        }
        if (c.B(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
        }
        view.setVisibility(4);
    }

    public static int b(int i) {
        if (i == 0) {
            return 2;
        }
        if (i == 4) {
            return 4;
        }
        if (i == 8) {
            return 3;
        }
        throw new IllegalArgumentException(j(i, "Unknown visibility "));
    }

    public static int c(View view) {
        if (view.getAlpha() == 0.0f && view.getVisibility() == 0) {
            return 4;
        }
        return b(view.getVisibility());
    }

    public static float d(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static long e(long j, long j2, long j3, long j4) {
        return (j * j2) + j3 + j4;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00a2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0096 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ long f(java.lang.String r28) {
        /*
            r0 = r28
            int r1 = r0.length()
            if (r1 == 0) goto Lbd
            r2 = 10
            long r3 = (long) r2
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r8 = 1
            r9 = 0
            r10 = -9223372036854775808
            r12 = -1
            r14 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r7 >= 0) goto L2c
            long r16 = r3 ^ r10
            int r16 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r16 >= 0) goto L27
            r16 = r5
            r18 = r16
            goto L46
        L27:
            r16 = 1
            r18 = r5
            goto L46
        L2c:
            long r16 = r14 / r3
            long r16 = r16 << r8
            long r18 = r16 * r3
            long r18 = r12 - r18
            long r18 = r18 ^ r10
            long r20 = r3 ^ r10
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 < 0) goto L40
            r18 = r5
            r5 = r8
            goto L43
        L40:
            r18 = r5
            r5 = r9
        L43:
            long r5 = (long) r5
            long r16 = r16 + r5
        L46:
            char r5 = r0.charAt(r9)
            r6 = 43
            if (r5 != r6) goto L51
            if (r1 <= r8) goto L51
            r9 = r8
        L51:
            r5 = r18
        L53:
            if (r9 >= r1) goto Lbc
            r20 = r8
            char r8 = r0.charAt(r9)
            int r8 = java.lang.Character.digit(r8, r2)
            r2 = -1
            if (r8 == r2) goto Lb2
            int r2 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r2 < 0) goto La2
            int r2 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r2 > 0) goto La2
            if (r2 != 0) goto L96
            if (r7 >= 0) goto L7b
            long r22 = r3 ^ r10
            int r2 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1))
            if (r2 >= 0) goto L76
            r10 = r12
            goto L93
        L76:
            long r22 = r12 - r3
        L78:
            r10 = r22
            goto L93
        L7b:
            long r22 = r14 / r3
            long r22 = r22 << r20
            long r22 = r22 * r3
            long r22 = r12 - r22
            long r24 = r22 ^ r10
            long r26 = r3 ^ r10
            int r2 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r2 < 0) goto L8e
            r24 = r3
            goto L90
        L8e:
            r24 = r18
        L90:
            long r22 = r22 - r24
            goto L78
        L93:
            int r2 = (int) r10
            if (r8 > r2) goto La2
        L96:
            long r5 = r5 * r3
            long r10 = (long) r8
            long r5 = r5 + r10
            int r9 = r9 + 1
            r8 = r20
            r2 = 10
            r10 = -9223372036854775808
            goto L53
        La2:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Too large for unsigned long: "
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        Lb2:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        Lbc:
            return r5
        Lbd:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = "empty string"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zn.f(java.lang.String):long");
    }

    public static d0 g(String str) {
        return new d0(str).t();
    }

    public static l21 h(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return l21Var.U0().e(l21Var2).e(l21Var3);
    }

    public static ClassCastException i(Iterator it) {
        it.next().getClass();
        return new ClassCastException();
    }

    public static String j(int i, String str) {
        return str + i;
    }

    public static String k(int i, String str, String str2) {
        return str + i + str2;
    }

    public static String l(String str, int i, int i2, String str2) {
        return str + i + str2 + i2;
    }

    public static String m(String str, String str2) {
        return str + str2;
    }

    public static String n(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String o(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder p(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder q(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static void r(d0 d0Var, String str, String str2, String str3, String str4) {
        d0Var.o(str);
        d0Var.o(str2);
        d0Var.o(str3);
        d0Var.o(str4);
    }

    public static /* synthetic */ void s(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static void t(UnsatisfiedLinkError unsatisfiedLinkError, StringBuilder sb, Logger logger, Level level, UnsatisfiedLinkError unsatisfiedLinkError2) {
        sb.append(unsatisfiedLinkError.getMessage());
        logger.log(level, sb.toString(), (Throwable) unsatisfiedLinkError2);
    }

    public static void u(HashMap map, d0 d0Var) {
        map.put(d0Var, new fm0(3));
    }

    public static String v(String str, String str2) {
        return str + str2;
    }

    public static /* synthetic */ int w(int i) {
        if (i != 0) {
            return i - 1;
        }
        throw null;
    }

    public static /* synthetic */ String x(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "null" : "REMOVING" : "ADDING" : "NONE";
    }

    public static /* synthetic */ String y(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "null" : "INVISIBLE" : "GONE" : "VISIBLE" : "REMOVED";
    }
}
