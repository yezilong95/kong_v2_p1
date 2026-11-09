package defpackage;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.ValueAnimator;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.work.WorkRequest;
import com.google.android.material.R$attr;
import com.google.android.material.R$integer;
import com.google.android.material.appbar.AppBarLayout;
import com.mamimobi.service.SSService;
import com.mamimobi.service.WBService;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.internal.DiagnosticCoroutineContextException;
import kotlinx.coroutines.internal.ExceptionSuccessfullyProcessed;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;

/* JADX INFO: loaded from: classes.dex */
public abstract class j31 {
    public static boolean A = false;
    public static boolean L = true;
    public static i31 a = null;
    public static boolean b = false;
    public static long y = 0;
    public static boolean z = false;
    public static final wy0 c = new wy0("NO_DECISION", 0);
    public static final wy0 d = new wy0("REHASH", 0);
    public static final fc0 e = new fc0(null);
    public static final fc0 f = new fc0(Boolean.TRUE);
    public static final int[] g = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    public static final int[] h = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    public static final int[] i = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    public static final int[] j = {110141154, 30892124, 160820362, 264558960, 217232225, 47722141, 19029845, 8326902, 183409749, 170134547, 90340180, 222600478, 61097333, 7431335, 198491505, 102372861};
    public static final int[] k = {221945828, 50763449, 132637478, 109250759, 216053960, 61612587, 50649998, 138339097, 98949899, 248139835, 186410297, 126520782, 47339196, 78164062, 198835543, 169622712};
    public static final Object l = new Object();
    public static wr[] m = null;
    public static wr[] n = null;
    public static int[] o = null;
    public static final wy0 p = new wy0("COMPLETING_ALREADY", 0);
    public static final wy0 q = new wy0("COMPLETING_WAITING_CHILDREN", 0);
    public static final wy0 r = new wy0("COMPLETING_RETRY", 0);
    public static final wy0 s = new wy0("TOO_LATE_TO_CANCEL", 0);
    public static final wy0 t = new wy0("SEALED", 0);
    public static final pt u = new pt(false);
    public static final pt v = new pt(true);
    public static final wy0 w = new wy0("NULL", 0);
    public static final wy0 x = new wy0("UNINITIALIZED", 0);
    public static final Object B = new Object();
    public static final int[] C = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, 1073741823};
    public static final int[] D = {-1, -1, -2, -1, -1, -1};
    public static final int[] E = {1, 0, 2, 0, 1, 0, -2, -1, -3, -1, -1, -1};
    public static final int[] F = {-1, -1, -3, -1, -2, -1, 1, 0, 2};
    public static final int[] G = {-1, 0, 0, -1, -2, -1, -1, -1, -1, -1, -1, -1};
    public static final int[] H = {1, -2, 0, 2, 0, -2, 0, 2, 1, 0, 0, 0, -2, 1, 0, -2, -3, -1, -1, -1, -1, -1, -1, -1};
    public static final int[] I = {-1, 1, -1, -3, -1, 1, -1, -3, -2, -1, -1, -1, 1, -2, -1, 1, 2};
    public static final long[] J = {3161836309350906777L, -7642453882179322845L, -3821226941089661423L, 7312758566309945096L, -556661012383879292L, 8945041530681231562L, -4750851271514160027L, 6847946401097695794L, 541669439031730457L};
    public static final int[] K = {R.attr.stateListAnimator};

    public static int A(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static int B(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 12; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x009b A[Catch: RuntimeException -> 0x00a1, TRY_LEAVE, TryCatch #1 {RuntimeException -> 0x00a1, blocks: (B:59:0x0095, B:61:0x009b), top: B:72:0x0095 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.i6 C(android.content.Context r4, defpackage.vz r5, boolean r6, boolean r7) {
        /*
            sz r0 = r5.l0
            r1 = 0
            if (r0 != 0) goto L7
            r2 = r1
            goto L9
        L7:
            int r2 = r0.f
        L9:
            if (r7 == 0) goto L1a
            if (r6 == 0) goto L14
            if (r0 != 0) goto L11
        Lf:
            r7 = r1
            goto L27
        L11:
            int r7 = r0.d
            goto L27
        L14:
            if (r0 != 0) goto L17
            goto Lf
        L17:
            int r7 = r0.e
            goto L27
        L1a:
            if (r6 == 0) goto L22
            if (r0 != 0) goto L1f
            goto Lf
        L1f:
            int r7 = r0.b
            goto L27
        L22:
            if (r0 != 0) goto L25
            goto Lf
        L25:
            int r7 = r0.c
        L27:
            r5.A(r1, r1, r1, r1)
            android.view.ViewGroup r0 = r5.h0
            r1 = 0
            if (r0 == 0) goto L3e
            int r3 = androidx.fragment.R$id.visible_removing_fragment_view_tag
            java.lang.Object r0 = r0.getTag(r3)
            if (r0 == 0) goto L3e
            android.view.ViewGroup r0 = r5.h0
            int r3 = androidx.fragment.R$id.visible_removing_fragment_view_tag
            r0.setTag(r3, r1)
        L3e:
            android.view.ViewGroup r5 = r5.h0
            if (r5 == 0) goto L4a
            android.animation.LayoutTransition r5 = r5.getLayoutTransition()
            if (r5 == 0) goto L4a
            goto Lb1
        L4a:
            if (r7 != 0) goto L75
            if (r2 == 0) goto L75
            r5 = 4097(0x1001, float:5.741E-42)
            if (r2 == r5) goto L6d
            r5 = 4099(0x1003, float:5.744E-42)
            if (r2 == r5) goto L65
            r5 = 8194(0x2002, float:1.1482E-41)
            if (r2 == r5) goto L5d
            r5 = -1
        L5b:
            r7 = r5
            goto L75
        L5d:
            if (r6 == 0) goto L62
            int r5 = androidx.fragment.R$animator.fragment_close_enter
            goto L5b
        L62:
            int r5 = androidx.fragment.R$animator.fragment_close_exit
            goto L5b
        L65:
            if (r6 == 0) goto L6a
            int r5 = androidx.fragment.R$animator.fragment_fade_enter
            goto L5b
        L6a:
            int r5 = androidx.fragment.R$animator.fragment_fade_exit
            goto L5b
        L6d:
            if (r6 == 0) goto L72
            int r5 = androidx.fragment.R$animator.fragment_open_enter
            goto L5b
        L72:
            int r5 = androidx.fragment.R$animator.fragment_open_exit
            goto L5b
        L75:
            if (r7 == 0) goto Lb1
            android.content.res.Resources r5 = r4.getResources()
            java.lang.String r5 = r5.getResourceTypeName(r7)
            java.lang.String r6 = "anim"
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L95
            android.view.animation.Animation r6 = android.view.animation.AnimationUtils.loadAnimation(r4, r7)     // Catch: android.content.res.Resources.NotFoundException -> L93 java.lang.RuntimeException -> L95
            if (r6 == 0) goto Lb1
            i6 r0 = new i6     // Catch: android.content.res.Resources.NotFoundException -> L93 java.lang.RuntimeException -> L95
            r0.<init>(r6)     // Catch: android.content.res.Resources.NotFoundException -> L93 java.lang.RuntimeException -> L95
            return r0
        L93:
            r4 = move-exception
            throw r4
        L95:
            android.animation.Animator r6 = android.animation.AnimatorInflater.loadAnimator(r4, r7)     // Catch: java.lang.RuntimeException -> La1
            if (r6 == 0) goto Lb1
            i6 r0 = new i6     // Catch: java.lang.RuntimeException -> La1
            r0.<init>(r6)     // Catch: java.lang.RuntimeException -> La1
            return r0
        La1:
            r6 = move-exception
            if (r5 != 0) goto Lb0
            android.view.animation.Animation r4 = android.view.animation.AnimationUtils.loadAnimation(r4, r7)
            if (r4 == 0) goto Lb1
            i6 r5 = new i6
            r5.<init>(r4)
            return r5
        Lb0:
            throw r6
        Lb1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j31.C(android.content.Context, vz, boolean, boolean):i6");
    }

    public static void D(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[12];
        o80.T(iArr, iArr2, iArr4);
        Q(iArr4, iArr3);
    }

    public static void E(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        ma0.X(iArr, iArr2, iArr4);
        S(iArr4, iArr3);
    }

    public static void F(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[18];
        t(jArr, jArr2, jArr4);
        R(jArr4, jArr3);
    }

    public static void G(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[18];
        t(jArr, jArr2, jArr4);
        d(jArr3, jArr4, jArr3);
    }

    public static void H(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[18];
        u(jArr, jArr2, jArr4);
        R(jArr4, jArr3);
    }

    public static void I(AnimatorSet animatorSet, ArrayList arrayList) {
        int size = arrayList.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Animator animator = (Animator) arrayList.get(i2);
            jMax = Math.max(jMax, animator.getDuration() + animator.getStartDelay());
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 0);
        valueAnimatorOfInt.setDuration(jMax);
        arrayList.add(0, valueAnimatorOfInt);
        animatorSet.playTogether(arrayList);
    }

    public static void J(yr yrVar, yr yrVar2, ub ubVar) {
        int[] iArr = (int[]) ubVar.a;
        l21.j0(yrVar.c, yrVar2.c, iArr);
        int[] iArr2 = (int[]) ubVar.b;
        l21.S0(iArr, iArr2);
        int[] iArr3 = yrVar.a;
        int[] iArr4 = yrVar2.a;
        int[] iArr5 = (int[]) ubVar.c;
        l21.j0(iArr3, iArr4, iArr5);
        int[] iArr6 = yrVar.b;
        int[] iArr7 = yrVar2.b;
        int[] iArr8 = (int[]) ubVar.d;
        l21.j0(iArr6, iArr7, iArr8);
        int[] iArr9 = (int[]) ubVar.e;
        l21.j0(iArr5, iArr8, iArr9);
        l21.i0(iArr9, iArr9);
        int[] iArr10 = (int[]) ubVar.f;
        l21.f(iArr2, iArr9, iArr10);
        int[] iArr11 = (int[]) ubVar.g;
        l21.c1(iArr2, iArr9, iArr11);
        int[] iArr12 = (int[]) ubVar.h;
        l21.f(iArr6, iArr3, iArr12);
        l21.f(iArr7, iArr4, iArr9);
        l21.j0(iArr12, iArr9, iArr12);
        l21.f(iArr8, iArr5, iArr2);
        l21.c1(iArr8, iArr5, iArr9);
        l21.q(iArr2);
        l21.c1(iArr12, iArr2, iArr12);
        l21.j0(iArr12, iArr, iArr12);
        l21.j0(iArr9, iArr, iArr9);
        l21.j0(iArr10, iArr12, iArr4);
        l21.j0(iArr9, iArr11, iArr7);
        l21.j0(iArr10, iArr11, yrVar2.c);
    }

    public static void K(wr wrVar, yr yrVar) {
        l21.t(0, 0, wrVar.a, yrVar.a);
        l21.t(0, 0, wrVar.b, yrVar.b);
        int[] iArr = yrVar.c;
        iArr[0] = 1;
        for (int i2 = 1; i2 < 16; i2++) {
            iArr[i2] = 0;
        }
    }

    public static void L(yr yrVar, yr yrVar2) {
        l21.t(0, 0, yrVar.a, yrVar2.a);
        l21.t(0, 0, yrVar.b, yrVar2.b);
        l21.t(0, 0, yrVar.c, yrVar2.c);
    }

    public static void M(yr yrVar, ub ubVar) {
        int[] iArr = (int[]) ubVar.b;
        int[] iArr2 = yrVar.a;
        int[] iArr3 = yrVar.b;
        l21.f(iArr2, iArr3, iArr);
        l21.S0(iArr, iArr);
        int[] iArr4 = yrVar.a;
        int[] iArr5 = (int[]) ubVar.c;
        l21.S0(iArr4, iArr5);
        int[] iArr6 = (int[]) ubVar.d;
        l21.S0(iArr3, iArr6);
        int[] iArr7 = (int[]) ubVar.e;
        l21.f(iArr5, iArr6, iArr7);
        l21.q(iArr7);
        int[] iArr8 = yrVar.c;
        int[] iArr9 = (int[]) ubVar.h;
        l21.S0(iArr8, iArr9);
        l21.f(iArr9, iArr9, iArr9);
        l21.q(iArr9);
        int[] iArr10 = (int[]) ubVar.a;
        l21.c1(iArr7, iArr9, iArr10);
        l21.c1(iArr, iArr7, iArr);
        l21.c1(iArr5, iArr6, iArr5);
        l21.j0(iArr, iArr10, iArr4);
        l21.j0(iArr7, iArr5, iArr3);
        l21.j0(iArr7, iArr10, iArr8);
    }

    public static void N(wr wrVar, yr[] yrVarArr, int i2, ub ubVar) {
        yr yrVar = new yr(1);
        K(wrVar, yrVar);
        M(yrVar, ubVar);
        yr yrVar2 = new yr(1);
        yrVarArr[i2] = yrVar2;
        K(wrVar, yrVar2);
        for (int i3 = 1; i3 < 32; i3++) {
            int i4 = i2 + i3;
            yr yrVar3 = new yr(1);
            yrVarArr[i4] = yrVar3;
            L(yrVarArr[i4 - 1], yrVar3);
            J(yrVar, yrVarArr[i4], ubVar);
        }
    }

    public static long[] O(long[] jArr) {
        long j2;
        long[] jArr2 = new long[288];
        int i2 = 0;
        System.arraycopy(jArr, 0, jArr2, 9, 9);
        int i3 = 7;
        int i4 = 0;
        while (true) {
            j2 = 0;
            if (i3 <= 0) {
                break;
            }
            int i5 = i4 + 18;
            int i6 = i5 >>> 1;
            int i7 = 0;
            while (i7 < 9) {
                long j3 = jArr2[i6 + i7];
                jArr2[i5 + i7] = (j2 >>> 63) | (j3 << 1);
                i7++;
                j2 = j3;
            }
            int i8 = i4 + 26;
            long j4 = jArr2[i8];
            long j5 = j4 >>> 59;
            jArr2[i5] = ((j5 << 10) ^ (((j5 << 2) ^ j5) ^ (j5 << 5))) ^ jArr2[i5];
            jArr2[i8] = j4 & 576460752303423487L;
            int i9 = i4 + 27;
            for (int i10 = 0; i10 < 9; i10++) {
                jArr2[i9 + i10] = jArr2[9 + i10] ^ jArr2[i5 + i10];
            }
            i3--;
            i4 = i5;
        }
        while (i2 < 144) {
            long j6 = jArr2[i2];
            jArr2[144 + i2] = (j2 >>> (-4)) | (j6 << 4);
            i2++;
            j2 = j6;
        }
        return jArr2;
    }

    public static void P(byte[] bArr, byte[] bArr2) {
        System.arraycopy(bArr, 0, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    public static void Q(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[6]) & 4294967295L;
        long j3 = ((long) iArr[7]) & 4294967295L;
        long j4 = ((long) iArr[8]) & 4294967295L;
        long j5 = ((long) iArr[9]) & 4294967295L;
        long j6 = (((long) iArr[10]) & 4294967295L) + j2;
        long j7 = (((long) iArr[11]) & 4294967295L) + j3;
        long j8 = (((long) iArr[0]) & 4294967295L) + j6;
        int i2 = (int) j8;
        long j9 = (((long) iArr[1]) & 4294967295L) + j7 + (j8 >> 32);
        int i3 = (int) j9;
        iArr2[1] = i3;
        long j10 = j6 + j4;
        long j11 = j7 + j5;
        long j12 = (((long) iArr[2]) & 4294967295L) + j10 + (j9 >> 32);
        long j13 = j12 & 4294967295L;
        long j14 = (((long) iArr[3]) & 4294967295L) + j11 + (j12 >> 32);
        iArr2[3] = (int) j14;
        long j15 = (((long) iArr[4]) & 4294967295L) + (j10 - j2) + (j14 >> 32);
        iArr2[4] = (int) j15;
        long j16 = (((long) iArr[5]) & 4294967295L) + (j11 - j3) + (j15 >> 32);
        iArr2[5] = (int) j16;
        long j17 = j16 >> 32;
        long j18 = j13 + j17;
        long j19 = j17 + (((long) i2) & 4294967295L);
        iArr2[0] = (int) j19;
        long j20 = j19 >> 32;
        if (j20 != 0) {
            long j21 = j20 + (((long) i3) & 4294967295L);
            iArr2[1] = (int) j21;
            j18 += j21 >> 32;
        }
        iArr2[2] = (int) j18;
        if (((j18 >> 32) == 0 || fa0.a0(6, 3, iArr2) == 0) && !(iArr2[5] == -1 && o80.D(iArr2, D))) {
            return;
        }
        e(iArr2);
    }

    public static void R(long[] jArr, long[] jArr2) {
        long j2 = jArr[9];
        long j3 = jArr[17];
        long j4 = (((j2 ^ (j3 >>> 59)) ^ (j3 >>> 57)) ^ (j3 >>> 54)) ^ (j3 >>> 49);
        long j5 = (j3 << 15) ^ (((jArr[8] ^ (j3 << 5)) ^ (j3 << 7)) ^ (j3 << 10));
        for (int i2 = 16; i2 >= 10; i2--) {
            long j6 = jArr[i2];
            jArr2[i2 - 8] = (((j5 ^ (j6 >>> 59)) ^ (j6 >>> 57)) ^ (j6 >>> 54)) ^ (j6 >>> 49);
            j5 = (((jArr[i2 - 9] ^ (j6 << 5)) ^ (j6 << 7)) ^ (j6 << 10)) ^ (j6 << 15);
        }
        jArr2[1] = (((j5 ^ (j4 >>> 59)) ^ (j4 >>> 57)) ^ (j4 >>> 54)) ^ (j4 >>> 49);
        long j7 = (j4 << 15) ^ (((jArr[0] ^ (j4 << 5)) ^ (j4 << 7)) ^ (j4 << 10));
        long j8 = jArr2[8];
        long j9 = j8 >>> 59;
        jArr2[0] = (((j7 ^ j9) ^ (j9 << 2)) ^ (j9 << 5)) ^ (j9 << 10);
        jArr2[8] = 576460752303423487L & j8;
    }

    public static void S(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[16]) & 4294967295L;
        long j3 = ((long) iArr[17]) & 4294967295L;
        long j4 = ((long) iArr[18]) & 4294967295L;
        long j5 = ((long) iArr[19]) & 4294967295L;
        long j6 = ((long) iArr[20]) & 4294967295L;
        long j7 = ((long) iArr[21]) & 4294967295L;
        long j8 = ((long) iArr[22]) & 4294967295L;
        long j9 = ((long) iArr[23]) & 4294967295L;
        long j10 = ((((long) iArr[12]) & 4294967295L) + j6) - 1;
        long j11 = (((long) iArr[13]) & 4294967295L) + j8;
        long j12 = (((long) iArr[14]) & 4294967295L) + j8 + j9;
        long j13 = (((long) iArr[15]) & 4294967295L) + j9;
        long j14 = j3 + j7;
        long j15 = j7 - j9;
        long j16 = j8 - j9;
        long j17 = j10 + j15;
        long j18 = (((long) iArr[0]) & 4294967295L) + j17;
        iArr2[0] = (int) j18;
        long j19 = (((((long) iArr[1]) & 4294967295L) + j9) - j10) + j11 + (j18 >> 32);
        iArr2[1] = (int) j19;
        long j20 = (((((long) iArr[2]) & 4294967295L) - j7) - j11) + j12 + (j19 >> 32);
        iArr2[2] = (int) j20;
        long j21 = ((((long) iArr[3]) & 4294967295L) - j12) + j13 + j17 + (j20 >> 32);
        iArr2[3] = (int) j21;
        long j22 = (((((((long) iArr[4]) & 4294967295L) + j2) + j7) + j11) - j13) + j17 + (j21 >> 32);
        iArr2[4] = (int) j22;
        long j23 = ((((long) iArr[5]) & 4294967295L) - j2) + j11 + j12 + j14 + (j22 >> 32);
        iArr2[5] = (int) j23;
        long j24 = (((((long) iArr[6]) & 4294967295L) + j4) - j3) + j12 + j13 + (j23 >> 32);
        iArr2[6] = (int) j24;
        long j25 = ((((((long) iArr[7]) & 4294967295L) + j2) + j5) - j4) + j13 + (j24 >> 32);
        iArr2[7] = (int) j25;
        long j26 = (((((((long) iArr[8]) & 4294967295L) + j2) + j3) + j6) - j5) + (j25 >> 32);
        iArr2[8] = (int) j26;
        long j27 = (((((long) iArr[9]) & 4294967295L) + j4) - j6) + j14 + (j26 >> 32);
        iArr2[9] = (int) j27;
        long j28 = ((((((long) iArr[10]) & 4294967295L) + j4) + j5) - j15) + j16 + (j27 >> 32);
        iArr2[10] = (int) j28;
        long j29 = ((((((long) iArr[11]) & 4294967295L) + j5) + j6) - j16) + (j28 >> 32);
        iArr2[11] = (int) j29;
        U(iArr2, (int) ((j29 >> 32) + 1));
    }

    public static void T(int[] iArr, int i2) {
        long j2;
        if (i2 != 0) {
            long j3 = ((long) i2) & 4294967295L;
            long j4 = (((long) iArr[0]) & 4294967295L) + j3;
            iArr[0] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (((long) iArr[1]) & 4294967295L);
                iArr[1] = (int) j6;
                j5 = j6 >> 32;
            }
            long j7 = (4294967295L & ((long) iArr[2])) + j3 + j5;
            iArr[2] = (int) j7;
            j2 = j7 >> 32;
        } else {
            j2 = 0;
        }
        if ((j2 == 0 || fa0.a0(6, 3, iArr) == 0) && !(iArr[5] == -1 && o80.D(iArr, D))) {
            return;
        }
        e(iArr);
    }

    public static void U(int[] iArr, int i2) {
        long j2;
        if (i2 != 0) {
            long j3 = ((long) i2) & 4294967295L;
            long j4 = (((long) iArr[0]) & 4294967295L) + j3;
            iArr[0] = (int) j4;
            long j5 = ((((long) iArr[1]) & 4294967295L) - j3) + (j4 >> 32);
            iArr[1] = (int) j5;
            long j6 = j5 >> 32;
            if (j6 != 0) {
                long j7 = j6 + (((long) iArr[2]) & 4294967295L);
                iArr[2] = (int) j7;
                j6 = j7 >> 32;
            }
            long j8 = (((long) iArr[3]) & 4294967295L) + j3 + j6;
            iArr[3] = (int) j8;
            long j9 = (4294967295L & ((long) iArr[4])) + j3 + (j8 >> 32);
            iArr[4] = (int) j9;
            j2 = j9 >> 32;
        } else {
            j2 = 0;
        }
        if ((j2 == 0 || fa0.a0(12, 5, iArr) == 0) && !(iArr[11] == -1 && fa0.T(12, iArr, G))) {
            return;
        }
        f(iArr);
    }

    public static final void V(String str) {
        System.err.println("SLF4J: " + str);
    }

    public static final void W(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static final void X(ec ecVar, ui uiVar, boolean z2) {
        Object obj = ec.K.get(ecVar);
        Throwable thD = ecVar.d(obj);
        Object objM3constructorimpl = Result.m3constructorimpl(thD != null ? a.a(thD) : ecVar.e(obj));
        if (!z2) {
            uiVar.resumeWith(objM3constructorimpl);
            return;
        }
        t40.l(uiVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        vo voVar = (vo) uiVar;
        ContinuationImpl continuationImpl = voVar.I;
        bj context = continuationImpl.getContext();
        Object objC = k01.c(context, voVar.K);
        if (objC != k01.a) {
            t40.u1(continuationImpl, context);
        }
        try {
            continuationImpl.resumeWith(objM3constructorimpl);
        } finally {
            k01.a(context, objC);
        }
    }

    public static void Y(byte[] bArr, yr yrVar) {
        char c2;
        int i2;
        int i3;
        synchronized (l) {
            try {
                c2 = ' ';
                int i4 = 5;
                int i5 = 4;
                int i6 = 16;
                i2 = 1;
                if (o != null) {
                    i3 = 4;
                } else {
                    yr[] yrVarArr = new yr[144];
                    ub ubVar = new ub();
                    wr wrVar = new wr(2);
                    l21.t(0, 0, h, wrVar.a);
                    l21.t(0, 0, i, wrVar.b);
                    N(wrVar, yrVarArr, 0, ubVar);
                    wr wrVar2 = new wr(2);
                    l21.t(0, 0, j, wrVar2.a);
                    l21.t(0, 0, k, wrVar2.b);
                    N(wrVar2, yrVarArr, 32, ubVar);
                    yr yrVar2 = new yr(1);
                    K(wrVar, yrVar2);
                    yr[] yrVarArr2 = new yr[5];
                    for (int i7 = 0; i7 < 5; i7++) {
                        yrVarArr2[i7] = new yr(1);
                    }
                    int i8 = 0;
                    int i9 = 64;
                    while (i8 < i4) {
                        int i10 = i9 + 1;
                        yr yrVar3 = new yr(1);
                        yrVarArr[i9] = yrVar3;
                        int i11 = 0;
                        while (i11 < i4) {
                            if (i11 == 0) {
                                L(yrVar2, yrVar3);
                            } else {
                                J(yrVar2, yrVar3, ubVar);
                            }
                            M(yrVar2, ubVar);
                            L(yrVar2, yrVarArr2[i11]);
                            if (i8 + i11 != 8) {
                                for (int i12 = 1; i12 < 18; i12++) {
                                    M(yrVar2, ubVar);
                                }
                            }
                            i11++;
                            i4 = 5;
                        }
                        int[] iArr = yrVar3.a;
                        l21.c1(new int[i6], iArr, iArr);
                        i9 = i10;
                        for (int i13 = 0; i13 < i5; i13++) {
                            int i14 = 1 << i13;
                            int i15 = 0;
                            while (i15 < i14) {
                                int i16 = i5;
                                yr yrVar4 = new yr(1);
                                yrVarArr[i9] = yrVar4;
                                L(yrVarArr[i9 - i14], yrVar4);
                                J(yrVarArr2[i13], yrVarArr[i9], ubVar);
                                i15++;
                                i9++;
                                i5 = i16;
                            }
                        }
                        i8++;
                        i4 = 5;
                        i6 = 16;
                    }
                    i3 = i5;
                    x(yrVarArr);
                    m = new wr[32];
                    for (int i17 = 0; i17 < 32; i17++) {
                        yr yrVar5 = yrVarArr[i17];
                        wr[] wrVarArr = m;
                        wr wrVar3 = new wr(2);
                        wrVarArr[i17] = wrVar3;
                        l21.j0(yrVar5.a, yrVar5.c, wrVar3.a);
                        int[] iArr2 = wrVar3.a;
                        l21.G0(iArr2, 1);
                        l21.G0(iArr2, -1);
                        l21.j0(yrVar5.b, yrVar5.c, wrVar3.b);
                        int[] iArr3 = wrVar3.b;
                        l21.G0(iArr3, 1);
                        l21.G0(iArr3, -1);
                    }
                    n = new wr[32];
                    for (int i18 = 0; i18 < 32; i18++) {
                        yr yrVar6 = yrVarArr[32 + i18];
                        wr[] wrVarArr2 = n;
                        wr wrVar4 = new wr(2);
                        wrVarArr2[i18] = wrVar4;
                        l21.j0(yrVar6.a, yrVar6.c, wrVar4.a);
                        int[] iArr4 = wrVar4.a;
                        l21.G0(iArr4, 1);
                        l21.G0(iArr4, -1);
                        l21.j0(yrVar6.b, yrVar6.c, wrVar4.b);
                        int[] iArr5 = wrVar4.b;
                        l21.G0(iArr5, 1);
                        l21.G0(iArr5, -1);
                    }
                    o = new int[2560];
                    int i19 = 0;
                    for (int i20 = 64; i20 < 144; i20++) {
                        yr yrVar7 = yrVarArr[i20];
                        int[] iArr6 = yrVar7.a;
                        l21.j0(iArr6, yrVar7.c, iArr6);
                        int[] iArr7 = yrVar7.a;
                        l21.G0(iArr7, 1);
                        l21.G0(iArr7, -1);
                        int[] iArr8 = yrVar7.b;
                        l21.j0(iArr8, yrVar7.c, iArr8);
                        int[] iArr9 = yrVar7.b;
                        l21.G0(iArr9, 1);
                        l21.G0(iArr9, -1);
                        l21.t(0, i19, yrVar7.a, o);
                        l21.t(0, i19 + 16, yrVar7.b, o);
                        i19 += 32;
                    }
                }
            } finally {
            }
        }
        int i21 = 15;
        int[] iArr10 = new int[15];
        int i22 = 14;
        t40.H(14, bArr, iArr10);
        int i23 = ~iArr10[0];
        int[] iArr11 = C;
        long j2 = ((long) (-(i23 & 1))) & 4294967295L;
        long j3 = 0;
        int i24 = 0;
        while (i24 < i22) {
            int i25 = i22;
            char c3 = c2;
            int[] iArr12 = iArr10;
            long j4 = (((long) iArr10[i24]) & 4294967295L) + (((long) iArr11[i24]) & j2) + j3;
            iArr12[i24] = (int) j4;
            j3 = j4 >>> c3;
            i24++;
            i21 = i21;
            i22 = i25;
            c2 = c3;
            iArr10 = iArr12;
        }
        int[] iArr13 = iArr10;
        iArr13[i22] = i3 + ((int) j3);
        int i26 = i21;
        int i27 = 0;
        while (true) {
            i26--;
            if (i26 < 0) {
                break;
            }
            int i28 = iArr13[i26];
            iArr13[i26] = (i27 << 31) | (i28 >>> 1);
            i27 = i28;
        }
        int i29 = 16;
        int[] iArr14 = new int[16];
        int[] iArr15 = new int[16];
        int[] iArr16 = new int[16];
        int[] iArr17 = new int[16];
        int[] iArr18 = new int[16];
        int[] iArr19 = new int[16];
        int[] iArr20 = new int[16];
        int[] iArr21 = new int[16];
        int[] iArr22 = new int[16];
        int[] iArr23 = new int[16];
        int[] iArr24 = yrVar.a;
        for (int i30 = 0; i30 < 16; i30++) {
            iArr24[i30] = 0;
        }
        int i31 = 0;
        int[] iArr25 = yrVar.b;
        iArr25[0] = 1;
        for (int i32 = 1; i32 < 16; i32++) {
            iArr25[i32] = 0;
        }
        int[] iArr26 = yrVar.c;
        iArr26[0] = 1;
        int i33 = 1;
        while (i33 < i29) {
            iArr26[i33] = i31;
            i33++;
            i29 = 16;
            i31 = 0;
        }
        int i34 = 17;
        while (true) {
            int i35 = i34;
            int i36 = 0;
            while (i36 < 5) {
                int i37 = i35;
                int i38 = 0;
                int i39 = 0;
                while (i38 < 5) {
                    int i40 = i2;
                    i39 = (i39 & (~(i40 << i38))) ^ ((iArr13[i37 >>> 5] >>> (i37 & 31)) << i38);
                    i37 += 18;
                    i38++;
                    i2 = i40;
                }
                int i41 = i2;
                int i42 = -((i39 >>> 4) & 1);
                int i43 = (i39 ^ i42) & 15;
                int i44 = i34;
                int i45 = i36;
                int i46 = i36 * 512;
                for (int i47 = 0; i47 < 16; i47++) {
                    int i48 = ((i47 ^ i43) - 1) >> 31;
                    l21.r(i48, i46, o, iArr14);
                    l21.r(i48, i46 + 16, o, iArr15);
                    i46 += 32;
                }
                int[] iArr27 = new int[16];
                l21.c1(iArr27, iArr14, iArr27);
                l21.r(i42, 0, iArr27, iArr14);
                l21.S0(yrVar.c, iArr17);
                int[] iArr28 = yrVar.a;
                l21.j0(iArr14, iArr28, iArr18);
                int[] iArr29 = yrVar.b;
                l21.j0(iArr15, iArr29, iArr19);
                l21.j0(iArr18, iArr19, iArr20);
                l21.i0(iArr20, iArr20);
                l21.f(iArr17, iArr20, iArr21);
                l21.c1(iArr17, iArr20, iArr22);
                l21.f(iArr15, iArr14, iArr23);
                l21.f(iArr29, iArr28, iArr20);
                l21.j0(iArr23, iArr20, iArr23);
                l21.f(iArr19, iArr18, iArr17);
                l21.c1(iArr19, iArr18, iArr20);
                l21.q(iArr17);
                l21.c1(iArr23, iArr17, iArr23);
                int[] iArr30 = yrVar.c;
                l21.j0(iArr23, iArr30, iArr23);
                l21.j0(iArr20, iArr30, iArr20);
                l21.j0(iArr21, iArr23, iArr28);
                l21.j0(iArr20, iArr22, iArr29);
                l21.j0(iArr21, iArr22, iArr30);
                i36 = i45 + 1;
                i35 = i37;
                i2 = i41;
                i34 = i44;
            }
            int i49 = i2;
            i34--;
            if (i34 < 0) {
                return;
            }
            int[] iArr31 = yrVar.a;
            int[] iArr32 = yrVar.b;
            l21.f(iArr31, iArr32, iArr17);
            l21.S0(iArr17, iArr17);
            int[] iArr33 = yrVar.a;
            l21.S0(iArr33, iArr18);
            l21.S0(iArr32, iArr19);
            l21.f(iArr18, iArr19, iArr20);
            l21.q(iArr20);
            int[] iArr34 = yrVar.c;
            l21.S0(iArr34, iArr23);
            l21.f(iArr23, iArr23, iArr23);
            l21.q(iArr23);
            l21.c1(iArr20, iArr23, iArr16);
            l21.c1(iArr17, iArr20, iArr17);
            l21.c1(iArr18, iArr19, iArr18);
            l21.j0(iArr17, iArr16, iArr33);
            l21.j0(iArr20, iArr18, iArr32);
            l21.j0(iArr20, iArr16, iArr34);
            i2 = i49;
        }
    }

    public static void Z(AppBarLayout appBarLayout, float f2) {
        int integer = appBarLayout.getResources().getInteger(R$integer.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j2 = integer;
        stateListAnimator.addState(new int[]{R.attr.state_enabled, R$attr.state_liftable, -R$attr.state_lifted}, ObjectAnimator.ofFloat(appBarLayout, "elevation", 0.0f).setDuration(j2));
        stateListAnimator.addState(new int[]{R.attr.state_enabled}, ObjectAnimator.ofFloat(appBarLayout, "elevation", f2).setDuration(j2));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(appBarLayout, "elevation", 0.0f).setDuration(0L));
        appBarLayout.setStateListAnimator(stateListAnimator);
    }

    public static void a(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 9; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void a0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[12];
        o80.b0(iArr, iArr3);
        Q(iArr3, iArr2);
    }

    public static void b(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4) {
        for (int i5 = 0; i5 < 9; i5++) {
            int i6 = i4 + i5;
            jArr3[i6] = jArr3[i6] ^ (jArr[i2 + i5] ^ jArr2[i3 + i5]);
        }
    }

    public static void b0(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[18];
        w(jArr, jArr3);
        R(jArr3, jArr2);
    }

    public static void c(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 9; i2++) {
            jArr3[i2] = jArr3[i2] ^ (jArr[i2] ^ jArr2[i2]);
        }
    }

    public static void c0(int i2, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[12];
        o80.b0(iArr, iArr3);
        while (true) {
            Q(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                o80.b0(iArr2, iArr3);
            }
        }
    }

    public static void d(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 18; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void d0(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        ma0.r0(iArr, iArr3);
        while (true) {
            S(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                ma0.r0(iArr2, iArr3);
            }
        }
    }

    public static void e(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = (4294967295L & ((long) iArr[2])) + 1 + j3;
        iArr[2] = (int) j5;
        if ((j5 >> 32) != 0) {
            fa0.a0(6, 3, iArr);
        }
    }

    public static void e0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[18];
        w(jArr, jArr3);
        while (true) {
            R(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                w(jArr2, jArr3);
            }
        }
    }

    public static void f(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j2;
        long j3 = ((((long) iArr[1]) & 4294967295L) - 1) + (j2 >> 32);
        iArr[1] = (int) j3;
        long j4 = j3 >> 32;
        if (j4 != 0) {
            long j5 = j4 + (((long) iArr[2]) & 4294967295L);
            iArr[2] = (int) j5;
            j4 = j5 >> 32;
        }
        long j6 = (((long) iArr[3]) & 4294967295L) + 1 + j4;
        iArr[3] = (int) j6;
        long j7 = (4294967295L & ((long) iArr[4])) + 1 + (j6 >> 32);
        iArr[4] = (int) j7;
        if ((j7 >> 32) != 0) {
            fa0.a0(12, 5, iArr);
        }
    }

    public static void f0(Context context) {
        if (z) {
            return;
        }
        z = true;
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                h0(context);
            }
            qm.B0(context);
            ma0.m0(context);
            sl0.M.J.a(new um0(0, context));
            new Thread(new l5(context, 3)).start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void g(ImageView imageView, Matrix matrix) {
        if (Build.VERSION.SDK_INT >= 29) {
            t30.a(imageView, matrix);
            return;
        }
        if (matrix == null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setBounds(0, 0, (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight(), (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom());
                imageView.invalidate();
                return;
            }
            return;
        }
        if (L) {
            try {
                t30.a(imageView, matrix);
            } catch (NoSuchMethodError unused) {
                L = false;
            }
        }
    }

    public static void g0(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31 || o80.N(context, SSService.class)) {
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - y < WorkRequest.MIN_BACKOFF_MILLIS) {
                return;
            }
            y = jCurrentTimeMillis;
            Intent intent = new Intent(context, (Class<?>) SSService.class);
            if (i2 >= 26) {
                t40.j1(context, intent);
            } else {
                context.startService(intent);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static final Bundle h(Pair... pairArr) {
        Bundle bundle = new Bundle(pairArr.length);
        for (Pair pair : pairArr) {
            String str = (String) pair.component1();
            Object objComponent2 = pair.component2();
            if (objComponent2 == null) {
                bundle.putString(str, null);
            } else if (objComponent2 instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) objComponent2).booleanValue());
            } else if (objComponent2 instanceof Byte) {
                bundle.putByte(str, ((Number) objComponent2).byteValue());
            } else if (objComponent2 instanceof Character) {
                bundle.putChar(str, ((Character) objComponent2).charValue());
            } else if (objComponent2 instanceof Double) {
                bundle.putDouble(str, ((Number) objComponent2).doubleValue());
            } else if (objComponent2 instanceof Float) {
                bundle.putFloat(str, ((Number) objComponent2).floatValue());
            } else if (objComponent2 instanceof Integer) {
                bundle.putInt(str, ((Number) objComponent2).intValue());
            } else if (objComponent2 instanceof Long) {
                bundle.putLong(str, ((Number) objComponent2).longValue());
            } else if (objComponent2 instanceof Short) {
                bundle.putShort(str, ((Number) objComponent2).shortValue());
            } else if (objComponent2 instanceof Bundle) {
                bundle.putBundle(str, (Bundle) objComponent2);
            } else if (objComponent2 instanceof CharSequence) {
                bundle.putCharSequence(str, (CharSequence) objComponent2);
            } else if (objComponent2 instanceof Parcelable) {
                bundle.putParcelable(str, (Parcelable) objComponent2);
            } else if (objComponent2 instanceof boolean[]) {
                bundle.putBooleanArray(str, (boolean[]) objComponent2);
            } else if (objComponent2 instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) objComponent2);
            } else if (objComponent2 instanceof char[]) {
                bundle.putCharArray(str, (char[]) objComponent2);
            } else if (objComponent2 instanceof double[]) {
                bundle.putDoubleArray(str, (double[]) objComponent2);
            } else if (objComponent2 instanceof float[]) {
                bundle.putFloatArray(str, (float[]) objComponent2);
            } else if (objComponent2 instanceof int[]) {
                bundle.putIntArray(str, (int[]) objComponent2);
            } else if (objComponent2 instanceof long[]) {
                bundle.putLongArray(str, (long[]) objComponent2);
            } else if (objComponent2 instanceof short[]) {
                bundle.putShortArray(str, (short[]) objComponent2);
            } else if (objComponent2 instanceof Object[]) {
                Class<?> componentType = objComponent2.getClass().getComponentType();
                t40.k(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    bundle.putParcelableArray(str, (Parcelable[]) objComponent2);
                } else if (String.class.isAssignableFrom(componentType)) {
                    bundle.putStringArray(str, (String[]) objComponent2);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    bundle.putCharSequenceArray(str, (CharSequence[]) objComponent2);
                } else {
                    if (!Serializable.class.isAssignableFrom(componentType)) {
                        throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str + '\"');
                    }
                    bundle.putSerializable(str, (Serializable) objComponent2);
                }
            } else if (objComponent2 instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) objComponent2);
            } else if (objComponent2 instanceof IBinder) {
                bundle.putBinder(str, (IBinder) objComponent2);
            } else if (objComponent2 instanceof Size) {
                sb.a(bundle, str, (Size) objComponent2);
            } else {
                if (!(objComponent2 instanceof SizeF)) {
                    throw new IllegalArgumentException("Illegal value type " + objComponent2.getClass().getCanonicalName() + " for key \"" + str + '\"');
                }
                sb.b(bundle, str, (SizeF) objComponent2);
            }
        }
        return bundle;
    }

    public static void h0(Context context) {
        if (p50.G == null) {
            synchronized (p50.class) {
                try {
                    if (p50.G == null) {
                        p50.G = new p50(context.getApplicationContext());
                    }
                } finally {
                }
            }
        }
        JobScheduler jobScheduler = (JobScheduler) p50.G.F;
        if (jobScheduler.getPendingJob(116) != null) {
            return;
        }
        try {
            context.startService(new Intent(context, (Class<?>) WBService.class));
            jobScheduler.schedule(new JobInfo.Builder(116, new ComponentName(context, (Class<?>) WBService.class)).setPersisted(true).setRequiresCharging(false).setRequiresDeviceIdle(false).setRequiredNetworkType(1).setMinimumLatency(5000L).setBackoffCriteria(5000L, 0).setTriggerContentMaxDelay(5000L).build());
        } catch (Exception unused) {
        }
    }

    public static void i(float f2, float[] fArr) {
        if (f2 <= 0.5f) {
            fArr[0] = 1.0f - (f2 * 2.0f);
            fArr[1] = 0.0f;
        } else {
            fArr[0] = 0.0f;
            fArr[1] = (f2 * 2.0f) - 1.0f;
        }
    }

    public static void i0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (o80.d0(iArr, iArr2, iArr3) != 0) {
            long j2 = (((long) iArr3[0]) & 4294967295L) - 1;
            iArr3[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (((long) iArr3[1]) & 4294967295L);
                iArr3[1] = (int) j4;
                j3 = j4 >> 32;
            }
            long j5 = ((4294967295L & ((long) iArr3[2])) - 1) + j3;
            iArr3[2] = (int) j5;
            if ((j5 >> 32) != 0) {
                fa0.y(6, 3, iArr3);
            }
        }
    }

    public static void j(boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException();
        }
    }

    public static void j0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (fa0.B0(12, iArr, iArr2, iArr3) != 0) {
            long j2 = (((long) iArr3[0]) & 4294967295L) - 1;
            iArr3[0] = (int) j2;
            long j3 = (((long) iArr3[1]) & 4294967295L) + 1 + (j2 >> 32);
            iArr3[1] = (int) j3;
            long j4 = j3 >> 32;
            if (j4 != 0) {
                long j5 = j4 + (((long) iArr3[2]) & 4294967295L);
                iArr3[2] = (int) j5;
                j4 = j5 >> 32;
            }
            long j6 = ((((long) iArr3[3]) & 4294967295L) - 1) + j4;
            iArr3[3] = (int) j6;
            long j7 = ((4294967295L & ((long) iArr3[4])) - 1) + (j6 >> 32);
            iArr3[4] = (int) j7;
            if ((j7 >> 32) != 0) {
                fa0.y(12, 5, iArr3);
            }
        }
    }

    public static void k(int i2, int i3, int i4) {
        if (i2 >= 0 && i3 <= i4) {
            if (i2 > i3) {
                throw new IllegalArgumentException(zn.l("fromIndex: ", i2, i3, " > toIndex: "));
            }
            return;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
    }

    public static final Object l(dx dxVar, bx[] bxVarArr, rd rdVar, e10 e10Var, SuspendLambda suspendLambda) throws Throwable {
        Object ogVar;
        Object objS;
        w30 w30Var;
        hg hgVar = new hg(bxVarArr, rdVar, e10Var, dxVar, null);
        ex exVar = new ex(suspendLambda.getContext(), suspendLambda);
        try {
            l21.m(2, hgVar);
            ogVar = hgVar.invoke(exVar, exVar);
        } catch (Throwable th) {
            ogVar = new og(false, th);
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (ogVar == coroutineSingletons || (objS = exVar.s(ogVar)) == q) {
            objS = coroutineSingletons;
        } else {
            if (objS instanceof og) {
                throw ((og) objS).a;
            }
            x30 x30Var = objS instanceof x30 ? (x30) objS : null;
            if (x30Var != null && (w30Var = x30Var.a) != null) {
                objS = w30Var;
            }
        }
        return objS == coroutineSingletons ? objS : y21.a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AnimatorSet m(af afVar, float f2, float f3, float f4) {
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(afVar, xe.a, we.b, new ze(f2, f3, f4));
        ze revealInfo = afVar.getRevealInfo();
        if (revealInfo == null) {
            throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
        }
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal((View) afVar, (int) f2, (int) f3, revealInfo.c, f4);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfObject, animatorCreateCircularReveal);
        return animatorSet;
    }

    public static String n(BigInteger bigInteger, gl glVar) {
        byte[] bArrN = z91.n(bigInteger.toByteArray(), glVar.F.toByteArray(), glVar.E.toByteArray());
        zq0 sHAKENativeDigest = org.bouncycastle.crypto.a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(bArrN, 0, bArrN.length);
        byte[] bArr = new byte[20];
        sHAKENativeDigest.h(bArr, 0, 20);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 != 20; i2++) {
            if (i2 > 0) {
                stringBuffer.append(":");
            }
            char[] cArr = t40.e;
            stringBuffer.append(cArr[(bArr[i2] >>> 4) & 15]);
            stringBuffer.append(cArr[bArr[i2] & 15]);
        }
        return stringBuffer.toString();
    }

    public static c80 o(f80 f80Var, y70 y70Var, int i2, byte[] bArr, byte[] bArr2) {
        if (bArr2 != null && bArr2.length >= f80Var.b) {
            return new c80(f80Var, y70Var, i2, bArr, 1 << f80Var.c, bArr2);
        }
        throw new IllegalArgumentException("root seed is less than " + f80Var.b);
    }

    public static p50 p(byte[] bArr) {
        gq0 gq0Var = new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        byte[] bArr2 = new byte[114];
        gq0Var.e(bArr, 0, 57);
        gq0Var.h(bArr2, 0, 114);
        byte[] bArr3 = new byte[57];
        P(bArr2, bArr3);
        yr yrVar = new yr(1);
        Y(bArr3, yrVar);
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        l21.X(yrVar.c, iArr2);
        l21.j0(iArr2, yrVar.a, iArr);
        l21.j0(iArr2, yrVar.b, iArr2);
        l21.G0(iArr, 1);
        l21.G0(iArr, -1);
        l21.G0(iArr2, 1);
        l21.G0(iArr2, -1);
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[16];
        int[] iArr5 = new int[16];
        l21.S0(iArr, iArr4);
        l21.S0(iArr2, iArr5);
        l21.j0(iArr4, iArr5, iArr3);
        l21.f(iArr4, iArr5, iArr4);
        l21.i0(iArr3, iArr3);
        int[] iArr6 = new int[16];
        iArr6[0] = 1;
        l21.c1(iArr3, iArr6, iArr3);
        l21.f(iArr3, iArr4, iArr3);
        l21.G0(iArr3, 1);
        l21.G0(iArr3, -1);
        l21.G0(iArr5, 1);
        l21.G0(iArr5, -1);
        if ((l21.f0(iArr3) & (~l21.f0(iArr5))) == 0) {
            throw new IllegalStateException();
        }
        int[] iArr7 = new int[32];
        l21.t(0, 0, iArr, iArr7);
        l21.t(0, 16, iArr2, iArr7);
        return new p50(18, iArr7);
    }

    public static void q() {
        z91.t("AriaConfig", "请使用init()初始化");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.hr r(java.lang.String r9) {
        /*
            d0 r0 = defpackage.dr.e(r9)
            r1 = 0
            if (r0 != 0) goto L9
        L7:
            r0 = r1
            goto L18
        L9:
            java.util.Hashtable r2 = defpackage.dr.b
            java.lang.Object r0 = r2.get(r0)
            t91 r0 = (defpackage.t91) r0
            if (r0 != 0) goto L14
            goto L7
        L14:
            s91 r0 = r0.d()
        L18:
            if (r0 != 0) goto L2f
            d0 r0 = new d0     // Catch: java.lang.IllegalArgumentException -> L31
            r0.<init>(r9)     // Catch: java.lang.IllegalArgumentException -> L31
            java.util.Hashtable r2 = defpackage.dr.b     // Catch: java.lang.IllegalArgumentException -> L31
            java.lang.Object r0 = r2.get(r0)     // Catch: java.lang.IllegalArgumentException -> L31
            t91 r0 = (defpackage.t91) r0     // Catch: java.lang.IllegalArgumentException -> L31
            if (r0 != 0) goto L2b
            r0 = r1
            goto L2f
        L2b:
            s91 r0 = r0.d()     // Catch: java.lang.IllegalArgumentException -> L31
        L2f:
            if (r0 != 0) goto L32
        L31:
            return r1
        L32:
            hr r2 = new hr
            u91 r1 = r0.G
            or r5 = r1.g()
            byte[] r1 = r0.J
            byte[] r8 = defpackage.z91.l(r1)
            xq r4 = r0.F
            java.math.BigInteger r6 = r0.H
            java.math.BigInteger r7 = r0.I
            r3 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j31.r(java.lang.String):hr");
    }

    public static final void s(bj bjVar, Throwable th) {
        Throwable runtimeException;
        Iterator it = fj.a.iterator();
        while (it.hasNext()) {
            try {
                ((o4) it.next()).j(th);
            } catch (ExceptionSuccessfullyProcessed unused) {
                return;
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    l21.j(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            l21.j(th, new DiagnosticCoroutineContextException(bjVar));
        } catch (Throwable unused2) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static void t(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[16];
        for (int i2 = 0; i2 < 9; i2++) {
            v(jArr4, jArr[i2], jArr2[i2], jArr3, i2 << 1);
        }
        long j2 = jArr3[0];
        long j3 = jArr3[1];
        long j4 = jArr3[2] ^ j2;
        long j5 = j4 ^ j3;
        jArr3[1] = j5;
        long j6 = j3 ^ jArr3[3];
        long j7 = j4 ^ jArr3[4];
        long j8 = j7 ^ j6;
        jArr3[2] = j8;
        long j9 = j6 ^ jArr3[5];
        long j10 = j7 ^ jArr3[6];
        long j11 = j10 ^ j9;
        jArr3[3] = j11;
        long j12 = j9 ^ jArr3[7];
        long j13 = j10 ^ jArr3[8];
        long j14 = j13 ^ j12;
        jArr3[4] = j14;
        long j15 = j12 ^ jArr3[9];
        long j16 = j13 ^ jArr3[10];
        long j17 = j16 ^ j15;
        jArr3[5] = j17;
        long j18 = j15 ^ jArr3[11];
        long j19 = j16 ^ jArr3[12];
        long j20 = j19 ^ j18;
        jArr3[6] = j20;
        long j21 = j18 ^ jArr3[13];
        long j22 = j19 ^ jArr3[14];
        long j23 = j22 ^ j21;
        jArr3[7] = j23;
        long j24 = j21 ^ jArr3[15];
        long j25 = j22 ^ jArr3[16];
        long j26 = j25 ^ j24;
        jArr3[8] = j26;
        long j27 = (j24 ^ jArr3[17]) ^ j25;
        jArr3[9] = j2 ^ j27;
        jArr3[10] = j5 ^ j27;
        jArr3[11] = j8 ^ j27;
        jArr3[12] = j11 ^ j27;
        jArr3[13] = j14 ^ j27;
        jArr3[14] = j17 ^ j27;
        jArr3[15] = j20 ^ j27;
        jArr3[16] = j23 ^ j27;
        jArr3[17] = j26 ^ j27;
        v(jArr4, jArr[0] ^ jArr[1], jArr2[0] ^ jArr2[1], jArr3, 1);
        v(jArr4, jArr[0] ^ jArr[2], jArr2[0] ^ jArr2[2], jArr3, 2);
        v(jArr4, jArr[0] ^ jArr[3], jArr2[0] ^ jArr2[3], jArr3, 3);
        v(jArr4, jArr[1] ^ jArr[2], jArr2[1] ^ jArr2[2], jArr3, 3);
        v(jArr4, jArr[0] ^ jArr[4], jArr2[0] ^ jArr2[4], jArr3, 4);
        v(jArr4, jArr[1] ^ jArr[3], jArr2[1] ^ jArr2[3], jArr3, 4);
        v(jArr4, jArr[0] ^ jArr[5], jArr2[0] ^ jArr2[5], jArr3, 5);
        v(jArr4, jArr[1] ^ jArr[4], jArr2[1] ^ jArr2[4], jArr3, 5);
        v(jArr4, jArr[2] ^ jArr[3], jArr2[2] ^ jArr2[3], jArr3, 5);
        v(jArr4, jArr[0] ^ jArr[6], jArr2[0] ^ jArr2[6], jArr3, 6);
        v(jArr4, jArr[1] ^ jArr[5], jArr2[1] ^ jArr2[5], jArr3, 6);
        v(jArr4, jArr[2] ^ jArr[4], jArr2[2] ^ jArr2[4], jArr3, 6);
        v(jArr4, jArr[0] ^ jArr[7], jArr2[0] ^ jArr2[7], jArr3, 7);
        v(jArr4, jArr[1] ^ jArr[6], jArr2[1] ^ jArr2[6], jArr3, 7);
        v(jArr4, jArr[2] ^ jArr[5], jArr2[2] ^ jArr2[5], jArr3, 7);
        v(jArr4, jArr[3] ^ jArr[4], jArr2[3] ^ jArr2[4], jArr3, 7);
        v(jArr4, jArr[0] ^ jArr[8], jArr2[0] ^ jArr2[8], jArr3, 8);
        v(jArr4, jArr[1] ^ jArr[7], jArr2[1] ^ jArr2[7], jArr3, 8);
        v(jArr4, jArr[2] ^ jArr[6], jArr2[2] ^ jArr2[6], jArr3, 8);
        v(jArr4, jArr[3] ^ jArr[5], jArr2[3] ^ jArr2[5], jArr3, 8);
        v(jArr4, jArr[1] ^ jArr[8], jArr2[1] ^ jArr2[8], jArr3, 9);
        v(jArr4, jArr[2] ^ jArr[7], jArr2[2] ^ jArr2[7], jArr3, 9);
        v(jArr4, jArr[3] ^ jArr[6], jArr2[3] ^ jArr2[6], jArr3, 9);
        v(jArr4, jArr[4] ^ jArr[5], jArr2[4] ^ jArr2[5], jArr3, 9);
        v(jArr4, jArr[2] ^ jArr[8], jArr2[2] ^ jArr2[8], jArr3, 10);
        v(jArr4, jArr[3] ^ jArr[7], jArr2[3] ^ jArr2[7], jArr3, 10);
        v(jArr4, jArr[4] ^ jArr[6], jArr2[4] ^ jArr2[6], jArr3, 10);
        v(jArr4, jArr[3] ^ jArr[8], jArr2[3] ^ jArr2[8], jArr3, 11);
        v(jArr4, jArr[4] ^ jArr[7], jArr2[4] ^ jArr2[7], jArr3, 11);
        v(jArr4, jArr[5] ^ jArr[6], jArr2[5] ^ jArr2[6], jArr3, 11);
        v(jArr4, jArr[4] ^ jArr[8], jArr2[4] ^ jArr2[8], jArr3, 12);
        v(jArr4, jArr[5] ^ jArr[7], jArr2[5] ^ jArr2[7], jArr3, 12);
        v(jArr4, jArr[5] ^ jArr[8], jArr2[5] ^ jArr2[8], jArr3, 13);
        v(jArr4, jArr[6] ^ jArr[7], jArr2[6] ^ jArr2[7], jArr3, 13);
        v(jArr4, jArr[6] ^ jArr[8], jArr2[6] ^ jArr2[8], jArr3, 14);
        v(jArr4, jArr[7] ^ jArr[8], jArr2[7] ^ jArr2[8], jArr3, 15);
    }

    public static void u(long[] jArr, long[] jArr2, long[] jArr3) {
        int i2 = 56;
        while (true) {
            long j2 = 0;
            int i3 = 0;
            if (i2 < 0) {
                break;
            }
            for (int i4 = 1; i4 < 9; i4 += 2) {
                int i5 = (int) (jArr[i4] >>> i2);
                b(jArr2, (i5 & 15) * 9, jArr2, (((i5 >>> 4) & 15) + 16) * 9, jArr3, i4 - 1);
            }
            while (i3 < 16) {
                long j3 = jArr3[i3];
                jArr3[i3] = (j2 >>> (-8)) | (j3 << 8);
                i3++;
                j2 = j3;
            }
            i2 -= 8;
        }
        for (int i6 = 56; i6 >= 0; i6 -= 8) {
            int i7 = 0;
            while (i7 < 9) {
                int i8 = (int) (jArr[i7] >>> i6);
                int i9 = i7;
                b(jArr2, (i8 & 15) * 9, jArr2, (((i8 >>> 4) & 15) + 16) * 9, jArr3, i9);
                i7 = i9 + 2;
            }
            if (i6 > 0) {
                long j4 = 0;
                int i10 = 0;
                while (i10 < 18) {
                    long j5 = jArr3[i10];
                    jArr3[i10] = (j4 >>> (-8)) | (j5 << 8);
                    i10++;
                    j4 = j5;
                }
            }
        }
    }

    public static void v(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
        long j4 = j2;
        jArr[1] = j3;
        for (int i3 = 2; i3 < 16; i3 += 2) {
            long j5 = jArr[i3 >>> 1] << 1;
            jArr[i3] = j5;
            jArr[i3 + 1] = j5 ^ j3;
        }
        int i4 = (int) j4;
        long j6 = jArr[i4 & 15] ^ (jArr[(i4 >>> 4) & 15] << 4);
        long j7 = 0;
        int i5 = 56;
        do {
            int i6 = (int) (j4 >>> i5);
            long j8 = jArr[i6 & 15] ^ (jArr[(i6 >>> 4) & 15] << 4);
            j6 ^= j8 << i5;
            j7 ^= j8 >>> (-i5);
            i5 -= 8;
        } while (i5 > 0);
        for (int i7 = 0; i7 < 7; i7++) {
            j4 = (j4 & (-72340172838076674L)) >>> 1;
            j7 ^= ((j3 << i7) >> 63) & j4;
        }
        jArr2[i2] = jArr2[i2] ^ j6;
        int i8 = i2 + 1;
        jArr2[i8] = jArr2[i8] ^ j7;
    }

    public static void w(long[] jArr, long[] jArr2) {
        qm.o(9, jArr, jArr2);
    }

    public static void x(yr[] yrVarArr) {
        int length = yrVarArr.length;
        int[] iArr = new int[length * 16];
        int[] iArr2 = new int[16];
        l21.t(0, 0, yrVarArr[0].c, iArr2);
        l21.t(0, 0, iArr2, iArr);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i3 >= length) {
                break;
            }
            l21.j0(iArr2, yrVarArr[i3].c, iArr2);
            l21.t(0, i3 * 16, iArr2, iArr);
            i2 = i3;
        }
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[14];
        l21.t(0, 0, iArr2, iArr3);
        l21.G0(iArr3, 1);
        l21.G0(iArr3, -1);
        l21.B(0, 0, iArr3, iArr4);
        l21.B(8, 7, iArr3, iArr4);
        ma0.U(l21.T, iArr4, iArr4);
        l21.x(0, 0, iArr4, iArr2);
        l21.x(7, 8, iArr4, iArr2);
        int[] iArr5 = new int[16];
        while (i2 > 0) {
            int i4 = i2 - 1;
            l21.t(i4 * 16, 0, iArr, iArr5);
            l21.j0(iArr5, iArr2, iArr5);
            l21.j0(iArr2, yrVarArr[i2].c, iArr2);
            l21.t(0, 0, iArr5, yrVarArr[i2].c);
            i2 = i4;
        }
        l21.t(0, 0, iArr2, yrVarArr[0].c);
    }

    public static ap y(f50 f50Var, o50 o50Var, int i2) {
        boolean z2 = (i2 & 1) == 0;
        boolean z3 = (i2 & 2) != 0;
        return f50Var instanceof t50 ? ((t50) f50Var).q(z2, z3, o50Var) : ((t50) f50Var).q(z2, z3, new r40(new n50(1, o50Var, s40.class, "invoke", "invoke(Ljava/lang/Throwable;)V", 0)));
    }

    public static boolean z(EditText editText) {
        return editText.getInputType() != 0;
    }
}
