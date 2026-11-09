package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.transition.Transition;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class z91 {
    public static Method O = null;
    public static boolean P = false;
    public static int a0 = 3;
    public static final float[][] E = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
    public static final float[][] F = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};
    public static final float[] G = {95.047f, 100.0f, 108.883f};
    public static final float[][] H = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};
    public static final int[] I = new int[0];
    public static final Object[] J = new Object[0];
    public static final String[] K = new String[0];
    public static final wy0 L = new wy0("CONDITION_FALSE", 0);
    public static final boolean[] M = new boolean[3];
    public static final Object N = new Object();
    public static final int[] Q = {-1, -1, -1, -3};
    public static final int[] R = {1, 0, 0, 4, -2, -1, 3, -4};
    public static final int[] S = {-1, -1, -1, -5, 1, 0, -4, 3};
    public static final int[] T = {-6803, -2, -1, -1, -1, -1, -1};
    public static final int[] U = {46280809, 13606, 1, 0, 0, 0, 0, -13606, -3, -1, -1, -1, -1, -1};
    public static final int[] V = {-46280809, -13607, -2, -1, -1, -1, -1, 13605, 2};
    public static final int[] W = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 511};
    public static final wy0 X = new wy0("NONE", 0);
    public static final wy0 Y = new wy0("PENDING", 0);
    public static final String[] Z = {"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

    public static final ec A(ui uiVar) {
        ec ecVar;
        ec ecVar2;
        if (!(uiVar instanceof vo)) {
            return new ec(1, uiVar);
        }
        vo voVar = (vo) uiVar;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = vo.L;
            Object obj = atomicReferenceFieldUpdater.get(voVar);
            wy0 wy0Var = t40.d;
            ecVar = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(voVar, wy0Var);
                ecVar2 = null;
                break;
            }
            if (obj instanceof ec) {
                while (!atomicReferenceFieldUpdater.compareAndSet(voVar, obj, wy0Var)) {
                    if (atomicReferenceFieldUpdater.get(voVar) != obj) {
                        break;
                    }
                }
                ecVar2 = (ec) obj;
                break loop0;
            }
            if (obj != wy0Var && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (ecVar2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = ec.K;
            Object obj2 = atomicReferenceFieldUpdater2.get(ecVar2);
            if (!(obj2 instanceof mg) || ((mg) obj2).c == null) {
                ec.J.set(ecVar2, 536870911);
                atomicReferenceFieldUpdater2.set(ecVar2, n3.a);
                ecVar = ecVar2;
            } else {
                ecVar2.k();
            }
            if (ecVar != null) {
                return ecVar;
            }
        }
        return new ec(2, uiVar);
    }

    public static int C(a21 a21Var, int i) {
        int[] iArr;
        if (a21Var == null || (iArr = (int[]) a21Var.a.get("android:visibilityPropagation:center")) == null) {
            return -1;
        }
        return iArr[i];
    }

    public static final void D(bj bjVar, Throwable th) {
        try {
            o4 o4Var = (o4) bjVar.get(is.G);
            if (o4Var != null) {
                o4Var.j(th);
            } else {
                j31.s(bjVar, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                l21.j(runtimeException, th);
                th = runtimeException;
            }
            j31.s(bjVar, th);
        }
    }

    public static int E(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ bArr[length];
        }
    }

    public static int F(int[] iArr, int i) {
        if (iArr == null) {
            return 0;
        }
        int i2 = i + 1;
        while (true) {
            i--;
            if (i < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ iArr[i];
        }
    }

    public static int G(long[] jArr, int i) {
        if (jArr == null) {
            return 0;
        }
        int i2 = i + 1;
        while (true) {
            i--;
            if (i < 0) {
                return i2;
            }
            long j = jArr[i];
            i2 = (((i2 * 257) ^ ((int) j)) * 257) ^ ((int) (j >>> 32));
        }
    }

    public static void H(int[] iArr, int[] iArr2, int[] iArr3) {
        fa0.m0(iArr, iArr2, iArr3);
        long j = ((long) iArr2[8]) & 4294967295L;
        long j2 = ((long) iArr2[9]) & 4294967295L;
        long j3 = ((long) iArr2[10]) & 4294967295L;
        long j4 = ((long) iArr2[11]) & 4294967295L;
        long j5 = ((long) iArr2[12]) & 4294967295L;
        long j6 = ((long) iArr2[13]) & 4294967295L;
        long j7 = ((long) iArr2[14]) & 4294967295L;
        long j8 = ((long) iArr2[15]) & 4294967295L;
        long j9 = ((long) iArr[8]) & 4294967295L;
        long j10 = j9 * j;
        iArr3[16] = (int) j10;
        int i = 32;
        long j11 = (j9 * j2) + (j10 >>> 32);
        iArr3[17] = (int) j11;
        long j12 = (j9 * j3) + (j11 >>> 32);
        iArr3[18] = (int) j12;
        long j13 = (j9 * j4) + (j12 >>> 32);
        iArr3[19] = (int) j13;
        long j14 = (j9 * j5) + (j13 >>> 32);
        iArr3[20] = (int) j14;
        long j15 = (j9 * j6) + (j14 >>> 32);
        iArr3[21] = (int) j15;
        long j16 = (j9 * j7) + (j15 >>> 32);
        iArr3[22] = (int) j16;
        long j17 = (j9 * j8) + (j16 >>> 32);
        iArr3[23] = (int) j17;
        iArr3[24] = (int) (j17 >>> 32);
        int i2 = 16;
        int i3 = 1;
        while (i3 < 8) {
            int i4 = i2 + 1;
            long j18 = j5;
            int i5 = i;
            long j19 = ((long) iArr[8 + i3]) & 4294967295L;
            long j20 = j6;
            long j21 = (j19 * j) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j21;
            int i6 = i2 + 2;
            long j22 = (j19 * j2) + (((long) iArr3[i6]) & 4294967295L) + (j21 >>> i5);
            iArr3[i6] = (int) j22;
            int i7 = i2 + 3;
            long j23 = (j19 * j3) + (((long) iArr3[i7]) & 4294967295L) + (j22 >>> i5);
            iArr3[i7] = (int) j23;
            int i8 = i2 + 4;
            long j24 = (j19 * j4) + (((long) iArr3[i8]) & 4294967295L) + (j23 >>> i5);
            iArr3[i8] = (int) j24;
            int i9 = i2 + 5;
            long j25 = (j19 * j18) + (((long) iArr3[i9]) & 4294967295L) + (j24 >>> i5);
            iArr3[i9] = (int) j25;
            int i10 = i2 + 6;
            long j26 = (j19 * j20) + (((long) iArr3[i10]) & 4294967295L) + (j25 >>> i5);
            iArr3[i10] = (int) j26;
            int i11 = i2 + 7;
            long j27 = (j19 * j7) + (((long) iArr3[i11]) & 4294967295L) + (j26 >>> i5);
            iArr3[i11] = (int) j27;
            int i12 = i2 + 8;
            long j28 = (j19 * j8) + (((long) iArr3[i12]) & 4294967295L) + (j27 >>> i5);
            iArr3[i12] = (int) j28;
            iArr3[i2 + 9] = (int) (j28 >>> i5);
            i3++;
            i2 = i4;
            i = i5;
            j5 = j18;
            j3 = j3;
            j6 = j20;
        }
        int i13 = i;
        int iJ = fa0.j(iArr3, iArr3);
        int i14 = 0;
        int iG = fa0.g(24, 16, fa0.g(0, 8, 0, iArr3, iArr3) + iJ, iArr3, iArr3) + iJ;
        int[] iArr4 = new int[8];
        int[] iArr5 = new int[8];
        boolean z = fa0.A(iArr, iArr, iArr4) != fa0.A(iArr2, iArr2, iArr5);
        int[] iArr6 = new int[16];
        fa0.m0(iArr4, iArr5, iArr6);
        fa0.k(i13, iG + (z ? fa0.h(16, 8, iArr6, iArr3) : fa0.F0(16, 8, iArr6, iArr3)), 24, iArr3);
        int i15 = iArr[16];
        int i16 = iArr2[16];
        long j29 = ((long) i15) & 4294967295L;
        long j30 = ((long) i16) & 4294967295L;
        long j31 = 0;
        while (true) {
            long j32 = j29;
            int i17 = 16 + i14;
            long j33 = ((((long) iArr[i14]) & 4294967295L) * j30) + (j32 * (((long) iArr2[i14]) & 4294967295L)) + (((long) iArr3[i17]) & 4294967295L) + j31;
            iArr3[i17] = (int) j33;
            long j34 = j33 >>> 32;
            i14++;
            if (i14 >= 16) {
                iArr3[32] = (i15 * i16) + ((int) j34);
                return;
            } else {
                j31 = j34;
                j29 = j32;
            }
        }
    }

    public static void I(int[] iArr, int[] iArr2) {
        long j;
        fa0.y0(iArr, iArr2);
        long j2 = 4294967295L;
        long j3 = ((long) iArr[8]) & 4294967295L;
        int i = 7;
        int i2 = 16;
        int i3 = 0;
        while (true) {
            int i4 = i - 1;
            long j4 = ((long) iArr[i + 8]) & j2;
            long j5 = j4 * j4;
            j = j2;
            iArr2[i2 + 15] = ((int) (j5 >>> 33)) | (i3 << 31);
            int i5 = i2 - 2;
            iArr2[i2 + 14] = (int) (j5 >>> 1);
            i3 = (int) j5;
            if (i4 <= 0) {
                break;
            }
            i2 = i5;
            i = i4;
            j2 = j;
        }
        long j6 = j3 * j3;
        long j7 = (((long) (i3 << 31)) & j) | (j6 >>> 33);
        iArr2[16] = (int) j6;
        int i6 = ((int) (j6 >>> 32)) & 1;
        long j8 = ((long) iArr[9]) & j;
        long j9 = ((long) iArr2[18]) & j;
        long j10 = (j8 * j3) + j7;
        int i7 = (int) j10;
        iArr2[17] = (i7 << 1) | i6;
        int i8 = i7 >>> 31;
        long j11 = j9 + (j10 >>> 32);
        long j12 = ((long) iArr[10]) & j;
        long j13 = ((long) iArr2[19]) & j;
        long j14 = ((long) iArr2[20]) & j;
        long j15 = (j12 * j3) + j11;
        int i9 = (int) j15;
        iArr2[18] = (i9 << 1) | i8;
        int i10 = i9 >>> 31;
        long jE = zn.e(j12, j8, j15 >>> 32, j13);
        long j16 = j14 + (jE >>> 32);
        long j17 = ((long) iArr[11]) & j;
        long j18 = (((long) iArr2[21]) & j) + (j16 >>> 32);
        long j19 = (((long) iArr2[22]) & j) + (j18 >>> 32);
        long j20 = j18 & j;
        long j21 = (j17 * j3) + (jE & j);
        int i11 = (int) j21;
        iArr2[19] = i10 | (i11 << 1);
        long jE2 = zn.e(j17, j8, j21 >>> 32, j16 & j);
        long jE3 = zn.e(j17, j12, jE2 >>> 32, j20);
        long j22 = j19 + (jE3 >>> 32);
        long j23 = jE3 & j;
        long j24 = ((long) iArr[12]) & j;
        long j25 = (((long) iArr2[23]) & j) + (j22 >>> 32);
        long j26 = j22 & j;
        long j27 = (((long) iArr2[24]) & j) + (j25 >>> 32);
        long j28 = j25 & j;
        long j29 = (j24 * j3) + (jE2 & j);
        int i12 = (int) j29;
        iArr2[20] = (i11 >>> 31) | (i12 << 1);
        int i13 = i12 >>> 31;
        long jE4 = zn.e(j24, j8, j29 >>> 32, j23);
        long jE5 = zn.e(j24, j12, jE4 >>> 32, j26);
        long jE6 = zn.e(j24, j17, jE5 >>> 32, j28);
        long j30 = jE5 & j;
        long j31 = j27 + (jE6 >>> 32);
        long j32 = ((long) iArr[13]) & j;
        long j33 = (((long) iArr2[25]) & j) + (j31 >>> 32);
        long j34 = j31 & j;
        long j35 = (((long) iArr2[26]) & j) + (j33 >>> 32);
        long j36 = (j32 * j3) + (jE4 & j);
        int i14 = (int) j36;
        iArr2[21] = (i14 << 1) | i13;
        long jE7 = zn.e(j32, j8, j36 >>> 32, j30);
        long jE8 = zn.e(j32, j12, jE7 >>> 32, jE6 & j);
        long jE9 = zn.e(j32, j17, jE8 >>> 32, j34);
        long jE10 = zn.e(j32, j24, jE9 >>> 32, j33 & j);
        long j37 = j35 + (jE10 >>> 32);
        long j38 = jE10 & j;
        long j39 = ((long) iArr[14]) & j;
        long j40 = (((long) iArr2[27]) & j) + (j37 >>> 32);
        long j41 = j37 & j;
        long j42 = (((long) iArr2[28]) & j) + (j40 >>> 32);
        long j43 = j40 & j;
        long j44 = (j39 * j3) + (jE7 & j);
        int i15 = (int) j44;
        iArr2[22] = (i14 >>> 31) | (i15 << 1);
        int i16 = i15 >>> 31;
        long jE11 = zn.e(j39, j8, j44 >>> 32, jE8 & j);
        long jE12 = zn.e(j39, j12, jE11 >>> 32, jE9 & j);
        long jE13 = zn.e(j39, j17, jE12 >>> 32, j38);
        long j45 = jE12 & j;
        long jE14 = zn.e(j39, j24, jE13 >>> 32, j41);
        long j46 = jE13 & j;
        long jE15 = zn.e(j39, j32, jE14 >>> 32, j43);
        long j47 = jE14 & j;
        long j48 = j42 + (jE15 >>> 32);
        long j49 = ((long) iArr[15]) & j;
        long j50 = (((long) iArr2[29]) & j) + (j48 >>> 32);
        long j51 = j48 & j;
        long j52 = (((long) iArr2[30]) & j) + (j50 >>> 32);
        long j53 = j50 & j;
        long j54 = (j3 * j49) + (jE11 & j);
        int i17 = (int) j54;
        iArr2[23] = i16 | (i17 << 1);
        int i18 = i17 >>> 31;
        long jE16 = zn.e(j49, j8, j54 >>> 32, j45);
        long jE17 = zn.e(j49, j12, jE16 >>> 32, j46);
        long jE18 = zn.e(j49, j17, jE17 >>> 32, j47);
        long jE19 = zn.e(j49, j24, jE18 >>> 32, jE15 & j);
        long jE20 = zn.e(j49, j32, jE19 >>> 32, j51);
        long jE21 = zn.e(j49, j39, jE20 >>> 32, j53);
        long j55 = j52 + (jE21 >>> 32);
        int i19 = (int) jE16;
        iArr2[24] = (i19 << 1) | i18;
        int i20 = (int) jE17;
        iArr2[25] = (i19 >>> 31) | (i20 << 1);
        int i21 = i20 >>> 31;
        int i22 = (int) jE18;
        iArr2[26] = i21 | (i22 << 1);
        int i23 = i22 >>> 31;
        int i24 = (int) jE19;
        iArr2[27] = i23 | (i24 << 1);
        int i25 = i24 >>> 31;
        int i26 = (int) jE20;
        iArr2[28] = i25 | (i26 << 1);
        int i27 = (int) jE21;
        iArr2[29] = (i27 << 1) | (i26 >>> 31);
        int i28 = (int) j55;
        iArr2[30] = (i27 >>> 31) | (i28 << 1);
        iArr2[31] = (i28 >>> 31) | ((iArr2[31] + ((int) (j55 >>> 32))) << 1);
        int iJ = fa0.j(iArr2, iArr2);
        int i29 = 0;
        int iG = iJ + fa0.g(24, 16, fa0.g(0, 8, 0, iArr2, iArr2) + iJ, iArr2, iArr2);
        int[] iArr3 = new int[8];
        fa0.A(iArr, iArr, iArr3);
        int[] iArr4 = new int[16];
        fa0.y0(iArr3, iArr4);
        fa0.k(32, fa0.F0(16, 8, iArr4, iArr2) + iG, 24, iArr2);
        int i30 = iArr[16];
        long j56 = ((long) (i30 << 1)) & j;
        long j57 = 0;
        do {
            int i31 = 16 + i29;
            long j58 = ((((long) iArr[i29]) & j) * j56) + (((long) iArr2[i31]) & j) + j57;
            iArr2[i31] = (int) j58;
            j57 = j58 >>> 32;
            i29++;
        } while (i29 < 16);
        iArr2[32] = (i30 * i30) + ((int) j57);
    }

    public static int J(float f) {
        if (f < 1.0f) {
            return -16777216;
        }
        if (f > 99.0f) {
            return -1;
        }
        float f2 = (f + 16.0f) / 116.0f;
        float f3 = f > 8.0f ? f2 * f2 * f2 : f / 903.2963f;
        float f4 = f2 * f2 * f2;
        boolean z = f4 > 0.008856452f;
        float f5 = z ? f4 : ((f2 * 116.0f) - 16.0f) / 903.2963f;
        if (!z) {
            f4 = ((f2 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = G;
        return dg.a(f5 * fArr[0], f3 * fArr[1], f4 * fArr[2]);
    }

    public static int K(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static int L(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static int M(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 17; i2++) {
            i |= iArr[i2];
        }
        return (((i >>> 1) | (i & 1)) - 1) >> 31;
    }

    public static xy0 N(a10 a10Var) {
        t40.n(a10Var, "initializer");
        return new xy0(a10Var);
    }

    public static float O(int i) {
        float f = i / 255.0f;
        return (f <= 0.04045f ? f / 12.92f : (float) Math.pow((f + 0.055f) / 1.055f, 2.4000000953674316d)) * 100.0f;
    }

    public static Class P(Class cls, String str) {
        try {
            ClassLoader classLoader = cls.getClassLoader();
            return classLoader != null ? classLoader.loadClass(str) : (Class) AccessController.doPrivileged(new ef(str, 0));
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static or Q(or orVar, BigInteger bigInteger) {
        int iSignum = bigInteger.signum();
        if (iSignum == 0 || orVar.l()) {
            return orVar.a.k();
        }
        BigInteger bigIntegerAbs = bigInteger.abs();
        xq xqVar = orVar.a;
        BigInteger bigInteger2 = xqVar.d;
        if (bigIntegerAbs.bitLength() > (bigInteger2 == null ? xqVar.j() + 1 : bigInteger2.bitLength())) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        iw iwVar = (iw) xqVar.n(orVar, "bc_fixed_point", new i6(15, xqVar, orVar, false));
        l21 l21Var = iwVar.b;
        int i = iwVar.c;
        int i2 = ((r2 + i) - 1) / i;
        or orVarK = xqVar.k();
        int i3 = i * i2;
        int[] iArrJ = fa0.J(i3, bigIntegerAbs);
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = 0;
            for (int i7 = i4 - i5; i7 >= 0; i7 -= i2) {
                int i8 = iArrJ[i7 >>> 5] >>> (i7 & 31);
                i6 = ((i6 ^ (i8 >>> 1)) << 1) ^ i8;
            }
            orVarK = orVarK.v(l21Var.h0(i6));
        }
        or orVarA = orVarK.a(iwVar.a);
        if (iSignum <= 0) {
            orVarA = orVarA.n();
        }
        if (orVarA.k(false, false)) {
            return orVarA;
        }
        throw new IllegalStateException("Invalid result");
    }

    public static void R(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[8];
        la0.V(iArr, iArr2, iArr4);
        d0(iArr4, iArr3);
    }

    public static void S(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        H(iArr, iArr2, iArr4);
        f0(iArr4, iArr3);
    }

    public static void T(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[14];
        s90.L(iArr, iArr2, iArr4);
        e0(iArr4, iArr3);
    }

    public static void U(EditorInfo editorInfo, InputConnection inputConnection, TextView textView) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = textView.getParent(); parent instanceof View; parent = parent.getParent()) {
        }
    }

    public static final Object Z(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static int a0(int i, String str, String str2) {
        if (a0 > i) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "null";
        }
        return Log.println(i, str, str2);
    }

    public static void c(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (j >>> 32);
        iArr3[1] = (int) j2;
        long j3 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (j2 >>> 32);
        iArr3[2] = (int) j3;
        long j4 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (j3 >>> 32);
        int i = (int) j4;
        iArr3[3] = i;
        if (((int) (j4 >>> 32)) != 0 || ((i >>> 1) >= 2147483646 && la0.I(iArr3, Q))) {
            f(iArr3);
        }
    }

    public static void d0(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & 4294967295L;
        long j2 = ((long) iArr[1]) & 4294967295L;
        long j3 = ((long) iArr[2]) & 4294967295L;
        long j4 = ((long) iArr[3]) & 4294967295L;
        long j5 = ((long) iArr[4]) & 4294967295L;
        long j6 = ((long) iArr[5]) & 4294967295L;
        long j7 = ((long) iArr[6]) & 4294967295L;
        long j8 = ((long) iArr[7]) & 4294967295L;
        long j9 = j4 + j8;
        long j10 = j7 + (j8 << 1);
        long j11 = j6 + (j10 << 1);
        long j12 = j2 + j11;
        long j13 = j5 + (j11 << 1);
        long j14 = j + j13;
        iArr2[0] = (int) j14;
        long j15 = j12 + (j14 >>> 32);
        iArr2[1] = (int) j15;
        long j16 = j3 + j10 + (j15 >>> 32);
        iArr2[2] = (int) j16;
        long j17 = j9 + (j13 << 1) + (j16 >>> 32);
        iArr2[3] = (int) j17;
        h0(iArr2, (int) (j17 >>> 32));
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3) {
        int iA = fa0.a(16, iArr, iArr2, iArr3) + iArr[16] + iArr2[16];
        if (iA > 511 || (iA == 511 && fa0.D(16, iArr3, W))) {
            iA = (fa0.Z(iArr3) + iA) & 511;
        }
        iArr3[16] = iA;
    }

    public static void e0(int[] iArr, int[] iArr2) {
        long j = ((long) 6803) & 4294967295L;
        long j2 = ((long) iArr[7]) & 4294967295L;
        long j3 = (j * j2) + (((long) iArr[0]) & 4294967295L);
        int i = (int) j3;
        iArr2[0] = i;
        long j4 = ((long) iArr[8]) & 4294967295L;
        long j5 = (j * j4) + j2 + (((long) iArr[1]) & 4294967295L) + (j3 >>> 32);
        int i2 = (int) j5;
        iArr2[1] = i2;
        long j6 = ((long) iArr[9]) & 4294967295L;
        long j7 = (j * j6) + j4 + (((long) iArr[2]) & 4294967295L) + (j5 >>> 32);
        int i3 = (int) j7;
        iArr2[2] = i3;
        long j8 = ((long) iArr[10]) & 4294967295L;
        long j9 = (j * j8) + j6 + (((long) iArr[3]) & 4294967295L) + (j7 >>> 32);
        int i4 = (int) j9;
        iArr2[3] = i4;
        long j10 = ((long) iArr[11]) & 4294967295L;
        long j11 = (j * j10) + j8 + (((long) iArr[4]) & 4294967295L) + (j9 >>> 32);
        iArr2[4] = (int) j11;
        long j12 = ((long) iArr[12]) & 4294967295L;
        long j13 = (j * j12) + j10 + (((long) iArr[5]) & 4294967295L) + (j11 >>> 32);
        iArr2[5] = (int) j13;
        long j14 = ((long) iArr[13]) & 4294967295L;
        long j15 = (j * j14) + j12 + (((long) iArr[6]) & 4294967295L) + (j13 >>> 32);
        iArr2[6] = (int) j15;
        long j16 = (j15 >>> 32) + j14;
        long j17 = j16 & 4294967295L;
        long j18 = (j * j17) + (((long) i) & 4294967295L);
        iArr2[0] = (int) j18;
        long j19 = j16 >>> 32;
        long j20 = (j * j19) + j17 + (((long) i2) & 4294967295L) + (j18 >>> 32);
        iArr2[1] = (int) j20;
        long j21 = j19 + (((long) i3) & 4294967295L) + (j20 >>> 32);
        iArr2[2] = (int) j21;
        long j22 = (j21 >>> 32) + (((long) i4) & 4294967295L);
        iArr2[3] = (int) j22;
        if (((j22 >>> 32) == 0 ? 0 : fa0.b0(7, 4, iArr2)) != 0 || (iArr2[6] == -1 && s90.z(iArr2, T))) {
            fa0.c(7, 6803, iArr2);
        }
    }

    public static void f(int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j;
        long j2 = j >> 32;
        if (j2 != 0) {
            long j3 = j2 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j3;
            long j4 = (j3 >> 32) + (((long) iArr[2]) & 4294967295L);
            iArr[2] = (int) j4;
            j2 = j4 >> 32;
        }
        iArr[3] = (int) ((4294967295L & ((long) iArr[3])) + 2 + j2);
    }

    public static void f0(int[] iArr, int[] iArr2) {
        int i = iArr[32];
        int i2 = i;
        int i3 = 16;
        while (true) {
            int i4 = i3 - 1;
            if (i4 < 0) {
                break;
            }
            int i5 = iArr[i3 + 15];
            iArr2[i4] = (i2 << (-9)) | (i5 >>> 9);
            i2 = i5;
            i3 = i4;
        }
        int i6 = fa0.i(16, iArr, iArr2) + ((i2 << (-9)) >>> 23) + (i >>> 9);
        if (i6 > 511 || (i6 == 511 && fa0.D(16, iArr2, W))) {
            i6 = (fa0.Z(iArr2) + i6) & 511;
        }
        iArr2[16] = i6;
    }

    public static int g(int i, int i2, int[] iArr) {
        int i3 = i - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i4 = i5 + 1;
            } else {
                if (i6 <= i2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public static void g0(int[] iArr) {
        int i = iArr[16];
        int iL = fa0.l(16, i >>> 9, iArr) + (i & 511);
        if (iL > 511 || (iL == 511 && fa0.D(16, iArr, W))) {
            iL = (fa0.Z(iArr) + iL) & 511;
        }
        iArr[16] = iL;
    }

    public static int h(long[] jArr, int i, long j) {
        int i2 = i - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            long j2 = jArr[i4];
            if (j2 < j) {
                i3 = i4 + 1;
            } else {
                if (j2 <= j) {
                    return i4;
                }
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }

    public static void h0(int[] iArr, int i) {
        while (i != 0) {
            long j = ((long) i) & 4294967295L;
            long j2 = (((long) iArr[0]) & 4294967295L) + j;
            iArr[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (((long) iArr[1]) & 4294967295L);
                iArr[1] = (int) j4;
                long j5 = (j4 >> 32) + (((long) iArr[2]) & 4294967295L);
                iArr[2] = (int) j5;
                j3 = j5 >> 32;
            }
            long j6 = (4294967295L & ((long) iArr[3])) + (j << 1) + j3;
            iArr[3] = (int) j6;
            i = (int) (j6 >> 32);
        }
        if ((iArr[3] >>> 1) < 2147483646 || !la0.I(iArr, Q)) {
            return;
        }
        f(iArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void i0(int[] r12, int r13) {
        /*
            r0 = 7
            r1 = 6803(0x1a93, float:9.533E-42)
            if (r13 == 0) goto L3f
            long r2 = (long) r1
            r4 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r2 = r2 & r4
            long r6 = (long) r13
            long r6 = r6 & r4
            long r2 = r2 * r6
            r13 = 0
            r8 = r12[r13]
            long r8 = (long) r8
            long r8 = r8 & r4
            long r2 = r2 + r8
            int r8 = (int) r2
            r12[r13] = r8
            r8 = 32
            long r2 = r2 >>> r8
            r9 = 1
            r10 = r12[r9]
            long r10 = (long) r10
            long r10 = r10 & r4
            long r6 = r6 + r10
            long r6 = r6 + r2
            int r2 = (int) r6
            r12[r9] = r2
            long r2 = r6 >>> r8
            r6 = 2
            r7 = r12[r6]
            long r9 = (long) r7
            long r4 = r4 & r9
            long r2 = r2 + r4
            int r4 = (int) r2
            r12[r6] = r4
            long r2 = r2 >>> r8
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L38
            goto L3d
        L38:
            r13 = 3
            int r13 = defpackage.fa0.b0(r0, r13, r12)
        L3d:
            if (r13 != 0) goto L4d
        L3f:
            r13 = 6
            r13 = r12[r13]
            r2 = -1
            if (r13 != r2) goto L50
            int[] r13 = defpackage.z91.T
            boolean r13 = defpackage.s90.z(r12, r13)
            if (r13 == 0) goto L50
        L4d:
            defpackage.fa0.c(r0, r1, r12)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z91.i0(int[], int):void");
    }

    public static void j0(EditorInfo editorInfo, CharSequence charSequence, int i, int i2) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i2);
    }

    public static void k0(Drawable drawable, int i) {
        ip.g(drawable, i);
    }

    public static byte[] l(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public static void l0(Drawable drawable, ColorStateList colorStateList) {
        ip.h(drawable, colorStateList);
    }

    public static byte[] m(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return l(bArr2);
        }
        if (bArr2 == null) {
            return l(bArr);
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static void m0(Drawable drawable, PorterDuff.Mode mode) {
        ip.i(drawable, mode);
    }

    public static byte[] n(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            return m(bArr2, bArr3);
        }
        if (bArr2 == null) {
            return m(bArr, bArr3);
        }
        if (bArr3 == null) {
            return m(bArr, bArr2);
        }
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        int length = bArr.length;
        System.arraycopy(bArr2, 0, bArr4, length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, length + bArr2.length, bArr3.length);
        return bArr4;
    }

    public static void n0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[8];
        la0.i0(iArr, iArr3);
        d0(iArr3, iArr2);
    }

    public static byte[] o(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr == null) {
            return n(bArr2, bArr3, bArr4);
        }
        if (bArr2 == null) {
            return n(bArr, bArr3, bArr4);
        }
        if (bArr3 == null) {
            return n(bArr, bArr2, bArr4);
        }
        if (bArr4 == null) {
            return n(bArr, bArr2, bArr3);
        }
        byte[] bArr5 = new byte[bArr.length + bArr2.length + bArr3.length + bArr4.length];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        int length = bArr.length;
        System.arraycopy(bArr2, 0, bArr5, length, bArr2.length);
        int length2 = length + bArr2.length;
        System.arraycopy(bArr3, 0, bArr5, length2, bArr3.length);
        System.arraycopy(bArr4, 0, bArr5, length2 + bArr3.length, bArr4.length);
        return bArr5;
    }

    public static void o0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[14];
        s90.Y(iArr, iArr3);
        e0(iArr3, iArr2);
    }

    public static byte[] p(byte[][] bArr) {
        int length = 0;
        for (int i = 0; i != bArr.length; i++) {
            length += bArr[i].length;
        }
        byte[] bArr2 = new byte[length];
        int length2 = 0;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            byte[] bArr3 = bArr[i2];
            System.arraycopy(bArr3, 0, bArr2, length2, bArr3.length);
            length2 += bArr[i2].length;
        }
        return bArr2;
    }

    public static void p0(int i, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[8];
        la0.i0(iArr, iArr3);
        while (true) {
            d0(iArr3, iArr2);
            i--;
            if (i <= 0) {
                return;
            } else {
                la0.i0(iArr2, iArr3);
            }
        }
    }

    public static boolean q(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            if (bArr == bArr2) {
                return true;
            }
            int length = bArr.length < bArr2.length ? bArr.length : bArr2.length;
            int length2 = bArr.length ^ bArr2.length;
            for (int i = 0; i != length; i++) {
                length2 |= bArr[i] ^ bArr2[i];
            }
            while (length < bArr2.length) {
                byte b = bArr2[length];
                length2 |= b ^ (~b);
                length++;
            }
            if (length2 == 0) {
                return true;
            }
        }
        return false;
    }

    public static void q0(int i, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[14];
        s90.Y(iArr, iArr3);
        while (true) {
            e0(iArr3, iArr2);
            i--;
            if (i <= 0) {
                return;
            } else {
                s90.Y(iArr2, iArr3);
            }
        }
    }

    public static byte[] r(byte[] bArr, int i, int i2) {
        int iY = y(i, i2);
        byte[] bArr2 = new byte[iY];
        System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, iY));
        return bArr2;
    }

    public static void r0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (la0.k0(iArr, iArr2, iArr3) != 0) {
            long j = (((long) iArr3[0]) & 4294967295L) - 1;
            iArr3[0] = (int) j;
            long j2 = j >> 32;
            if (j2 != 0) {
                long j3 = j2 + (((long) iArr3[1]) & 4294967295L);
                iArr3[1] = (int) j3;
                long j4 = (j3 >> 32) + (((long) iArr3[2]) & 4294967295L);
                iArr3[2] = (int) j4;
                j2 = j4 >> 32;
            }
            iArr3[3] = (int) (((4294967295L & ((long) iArr3[3])) - 2) + j2);
        }
    }

    public static or s(xq xqVar, byte[] bArr) {
        l21 l21VarK0;
        l21 l21VarH = xqVar.h(BigInteger.valueOf(bArr[bArr.length - 1] & 1));
        l21 l21VarH2 = xqVar.h(new BigInteger(1, bArr));
        if (!u0(l21VarH2).equals(xqVar.b)) {
            l21VarH2 = l21VarH2.h();
        }
        if (l21VarH2.d0()) {
            l21VarK0 = xqVar.c.T0();
        } else {
            l21 l21VarE = l21VarH2.U0().Y().k0(xqVar.c).e(xqVar.b).e(l21VarH2);
            if (!l21VarE.d0()) {
                l21 l21VarH3 = xqVar.h(qq.a);
                Random random = new Random();
                int iN = l21VarE.N();
                while (true) {
                    l21 l21VarH4 = xqVar.h(new BigInteger(iN, random));
                    l21 l21VarE2 = l21VarE;
                    l21 l21VarE3 = l21VarH3;
                    for (int i = 1; i <= iN - 1; i++) {
                        l21 l21VarU0 = l21VarE2.U0();
                        l21VarE3 = l21VarE3.U0().e(l21VarU0.k0(l21VarH4));
                        l21VarE2 = l21VarU0.e(l21VarE);
                    }
                    if (!l21VarE2.d0()) {
                        l21VarE = null;
                        break;
                    }
                    if (!l21VarE3.U0().e(l21VarE3).d0()) {
                        l21VarE = l21VarE3;
                        break;
                    }
                }
            }
            if (l21VarE != null) {
                if (!u0(l21VarE).equals(l21VarH)) {
                    l21VarE = l21VarE.h();
                }
                l21VarK0 = l21VarH2.k0(l21VarE);
            } else {
                l21VarK0 = null;
            }
        }
        if (l21VarK0 != null) {
            return xqVar.q(l21VarH2.h1(), l21VarK0.h1());
        }
        throw new IllegalArgumentException("Invalid point compression");
    }

    public static void s0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (s90.b0(iArr, iArr2, iArr3) != 0) {
            fa0.E0(7, 6803, iArr3);
        }
    }

    public static void t(String str, String str2) {
        a0(6, str, str2);
    }

    public static void t0(int[] iArr, int[] iArr2, int[] iArr3) {
        int iB0 = (fa0.B0(16, iArr, iArr2, iArr3) + iArr[16]) - iArr2[16];
        if (iB0 < 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= 16) {
                    i = -1;
                    break;
                }
                int i3 = iArr3[i2] - 1;
                iArr3[i2] = i3;
                if (i3 != -1) {
                    break;
                } else {
                    i2++;
                }
            }
            iB0 = (iB0 + i) & 511;
        }
        iArr3[16] = iB0;
    }

    public static l21 u0(l21 l21Var) {
        l21 l21VarE = l21Var;
        for (int i = 1; i < l21Var.N(); i++) {
            l21VarE = l21VarE.U0().e(l21Var);
        }
        return l21VarE;
    }

    public static void v() {
        throw new NullPointerException("请在Application中调用init进行数据库工具注册注册");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Drawable v0(Drawable drawable) {
        if (!(drawable instanceof g91)) {
            return drawable;
        }
        ((h91) ((g91) drawable)).getClass();
        return null;
    }

    public static final Class w(i60 i60Var) {
        Class clsA = ((cf) i60Var).a();
        t40.l(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return clsA;
    }

    public static float w0() {
        return ((float) Math.pow((((double) 50.0f) + 16.0d) / 116.0d, 3.0d)) * 100.0f;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class x(i60 i60Var) {
        t40.n(i60Var, "<this>");
        Class clsA = ((cf) i60Var).a();
        if (clsA.isPrimitive()) {
            String name = clsA.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    break;
            }
        }
        return clsA;
    }

    public static int y(int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0) {
            return i3;
        }
        throw new IllegalArgumentException(i + " > " + i2);
    }

    public static d0 z(String str) {
        d0 d0VarE = (d0) p91.a.get(jx0.c(str));
        if (d0VarE == null) {
            d0VarE = (d0) zp0.a.get(jx0.c(str));
        }
        if (d0VarE == null) {
            d0VarE = (d0) xf0.a.get(jx0.e(str));
        }
        if (d0VarE == null) {
            d0VarE = (d0) nz0.a.get(jx0.c(str));
        }
        if (d0VarE == null) {
            d0VarE = (d0) m.a.get(jx0.c(str));
        }
        if (d0VarE == null) {
            d0VarE = dr.e(str);
        }
        if (d0VarE == null) {
            d0VarE = (d0) m10.a.get(jx0.c(str));
        }
        return (d0VarE == null && str.equals("curve25519")) ? qj.a : d0VarE;
    }

    public abstract long B(ViewGroup viewGroup, Transition transition, a21 a21Var, a21 a21Var2);

    public abstract void V(Throwable th);

    public abstract View W(int i);

    public abstract boolean X();

    public abstract void Y(tb tbVar);

    public abstract void b0(f1 f1Var, f1 f1Var2);

    public abstract void c0(f1 f1Var, Thread thread);

    public abstract boolean i(g1 g1Var, c1 c1Var, c1 c1Var2);

    public abstract boolean j(g1 g1Var, Object obj, Object obj2);

    public abstract boolean k(g1 g1Var, f1 f1Var, f1 f1Var2);

    public abstract void u(jv0 jv0Var, float f, float f2);
}
