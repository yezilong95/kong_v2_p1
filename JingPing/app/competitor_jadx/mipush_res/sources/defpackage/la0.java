package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.mamimobi.AccessService;
import io.nats.client.Options;
import io.nats.client.support.ApiConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public abstract class la0 {
    public static String A = null;
    public static String B = null;
    public static String C = null;
    public static String D = null;
    public static String E = null;
    public static String F = null;
    public static String G = null;
    public static String H = null;
    public static String I = null;
    public static String J = null;
    public static String K = null;
    public static String L = null;
    public static String M = null;
    public static String N = null;
    public static String O = null;
    public static String P = null;
    public static String Q = null;
    public static String R = null;
    public static String S = null;
    public static String T = null;
    public static String U = null;
    public static String V = null;
    public static String W = null;
    public static String X = null;
    public static String Y = null;
    public static String Z = null;
    public static String a = null;
    public static String a0 = null;
    public static String b = null;
    public static String b0 = null;
    public static String c = null;
    public static String c0 = null;
    public static String d = null;
    public static String d0 = null;
    public static String e = null;
    public static String e0 = null;
    public static String f = null;
    public static String f0 = null;
    public static String g = null;
    public static String g0 = null;
    public static String h = null;
    public static boolean h0 = true;
    public static String i;
    public static Field i0;
    public static String j;
    public static boolean j0;
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

    public static Intent B(AppCompatActivity appCompatActivity) {
        Intent parentActivityIntent = appCompatActivity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        try {
            String strD = D(appCompatActivity, appCompatActivity.getComponentName());
            if (strD == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(appCompatActivity, strD);
            try {
                return D(appCompatActivity, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strD + "' in manifest");
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static Intent C(AppCompatActivity appCompatActivity, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String strD = D(appCompatActivity, componentName);
        if (strD == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strD);
        return D(appCompatActivity, componentName2) == null ? Intent.makeMainActivity(componentName2) : new Intent().setComponent(componentName2);
    }

    public static String D(Activity activity, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String string;
        ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(componentName, Build.VERSION.SDK_INT >= 29 ? 269222528 : 787072);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return activity.getPackageName() + string;
    }

    public static final Bundle F(String str, Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 != null) {
            return bundle2;
        }
        throw new IllegalArgumentException(zn.n("No valid saved state was found for the key '", str, "'. It may be missing, null, or not of the expected type. This can occur if the value was saved with a different type or if the saved state was modified unexpectedly."));
    }

    public static boolean I(int[] iArr, int[] iArr2) {
        for (int i2 = 3; i2 >= 0; i2--) {
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

    public static boolean J(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static void K(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        jArr2[0] = j2 & 576460752303423487L;
        jArr2[1] = ((j2 >>> 59) ^ (j3 << 5)) & 576460752303423487L;
        jArr2[2] = ((j3 >>> 54) ^ (j4 << 10)) & 576460752303423487L;
        jArr2[3] = ((j4 >>> 49) ^ (j5 << 15)) & 576460752303423487L;
        jArr2[4] = ((j5 >>> 44) ^ (j6 << 20)) & 576460752303423487L;
        jArr2[5] = ((j6 >>> 39) ^ (j7 << 25)) & 576460752303423487L;
        jArr2[6] = (j7 >>> 34) ^ (j8 << 30);
    }

    public static void L(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[7];
        long[] jArr5 = new long[7];
        K(jArr, jArr4);
        K(jArr2, jArr5);
        long[] jArr6 = new long[8];
        for (int i2 = 0; i2 < 7; i2++) {
            M(jArr6, jArr4[i2], jArr5[i2], jArr3, i2 << 1);
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
        long j21 = (j18 ^ jArr3[13]) ^ j19;
        jArr3[7] = j2 ^ j21;
        jArr3[8] = j5 ^ j21;
        jArr3[9] = j8 ^ j21;
        jArr3[10] = j11 ^ j21;
        jArr3[11] = j14 ^ j21;
        jArr3[12] = j17 ^ j21;
        jArr3[13] = j20 ^ j21;
        M(jArr6, jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        M(jArr6, jArr4[0] ^ jArr4[2], jArr5[0] ^ jArr5[2], jArr3, 2);
        M(jArr6, jArr4[0] ^ jArr4[3], jArr5[0] ^ jArr5[3], jArr3, 3);
        M(jArr6, jArr4[1] ^ jArr4[2], jArr5[1] ^ jArr5[2], jArr3, 3);
        M(jArr6, jArr4[0] ^ jArr4[4], jArr5[0] ^ jArr5[4], jArr3, 4);
        M(jArr6, jArr4[1] ^ jArr4[3], jArr5[1] ^ jArr5[3], jArr3, 4);
        M(jArr6, jArr4[0] ^ jArr4[5], jArr5[0] ^ jArr5[5], jArr3, 5);
        M(jArr6, jArr4[1] ^ jArr4[4], jArr5[1] ^ jArr5[4], jArr3, 5);
        M(jArr6, jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 5);
        M(jArr6, jArr4[0] ^ jArr4[6], jArr5[0] ^ jArr5[6], jArr3, 6);
        M(jArr6, jArr4[1] ^ jArr4[5], jArr5[1] ^ jArr5[5], jArr3, 6);
        M(jArr6, jArr4[2] ^ jArr4[4], jArr5[2] ^ jArr5[4], jArr3, 6);
        M(jArr6, jArr4[1] ^ jArr4[6], jArr5[1] ^ jArr5[6], jArr3, 7);
        M(jArr6, jArr4[2] ^ jArr4[5], jArr5[2] ^ jArr5[5], jArr3, 7);
        M(jArr6, jArr4[3] ^ jArr4[4], jArr5[3] ^ jArr5[4], jArr3, 7);
        M(jArr6, jArr4[2] ^ jArr4[6], jArr5[2] ^ jArr5[6], jArr3, 8);
        M(jArr6, jArr4[3] ^ jArr4[5], jArr5[3] ^ jArr5[5], jArr3, 8);
        M(jArr6, jArr4[3] ^ jArr4[6], jArr5[3] ^ jArr5[6], jArr3, 9);
        M(jArr6, jArr4[4] ^ jArr4[5], jArr5[4] ^ jArr5[5], jArr3, 9);
        M(jArr6, jArr4[4] ^ jArr4[6], jArr5[4] ^ jArr5[6], jArr3, 10);
        M(jArr6, jArr4[5] ^ jArr4[6], jArr5[5] ^ jArr5[6], jArr3, 11);
        long j22 = jArr3[0];
        long j23 = jArr3[1];
        long j24 = jArr3[2];
        long j25 = jArr3[3];
        long j26 = jArr3[4];
        long j27 = jArr3[5];
        long j28 = jArr3[6];
        long j29 = jArr3[7];
        long j30 = jArr3[8];
        long j31 = jArr3[9];
        long j32 = jArr3[10];
        long j33 = jArr3[11];
        long j34 = jArr3[12];
        long j35 = jArr3[13];
        jArr3[0] = j22 ^ (j23 << 59);
        jArr3[1] = (j23 >>> 5) ^ (j24 << 54);
        jArr3[2] = (j24 >>> 10) ^ (j25 << 49);
        jArr3[3] = (j25 >>> 15) ^ (j26 << 44);
        jArr3[4] = (j26 >>> 20) ^ (j27 << 39);
        jArr3[5] = (j27 >>> 25) ^ (j28 << 34);
        jArr3[6] = (j28 >>> 30) ^ (j29 << 29);
        jArr3[7] = (j29 >>> 35) ^ (j30 << 24);
        jArr3[8] = (j30 >>> 40) ^ (j31 << 19);
        jArr3[9] = (j31 >>> 45) ^ (j32 << 14);
        jArr3[10] = (j32 >>> 50) ^ (j33 << 9);
        jArr3[11] = ((j33 >>> 55) ^ (j34 << 4)) ^ (j35 << 63);
        jArr3[12] = j35 >>> 1;
    }

    public static void M(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
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

    public static void N(long[] jArr, long[] jArr2) {
        qm.o(6, jArr, jArr2);
        jArr2[12] = qm.n((int) jArr[6]);
    }

    public static boolean P(Context context) {
        return context.getResources().getConfiguration().fontScale >= 1.3f;
    }

    public static boolean Q(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 4; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean T(int[] iArr) {
        for (int i2 = 0; i2 < 4; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static short U(int i2) {
        return (short) ((i2 - (((short) (62209 * i2)) * 3329)) >> 16);
    }

    public static void V(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 4294967295L;
        long j3 = ((long) iArr2[0]) & 4294967295L;
        int i2 = 1;
        long j4 = ((long) iArr2[1]) & 4294967295L;
        long j5 = ((long) iArr2[2]) & 4294967295L;
        long j6 = ((long) iArr2[3]) & 4294967295L;
        long j7 = ((long) iArr[0]) & 4294967295L;
        long j8 = j7 * j3;
        iArr3[0] = (int) j8;
        long j9 = (j7 * j4) + (j8 >>> 32);
        iArr3[1] = (int) j9;
        long j10 = (j7 * j5) + (j9 >>> 32);
        iArr3[2] = (int) j10;
        long j11 = (j7 * j6) + (j10 >>> 32);
        iArr3[3] = (int) j11;
        iArr3[4] = (int) (j11 >>> 32);
        for (int i3 = 4; i2 < i3; i3 = 4) {
            long j12 = ((long) iArr[i2]) & j2;
            long j13 = (j12 * j3) + (((long) iArr3[i2]) & j2);
            iArr3[i2] = (int) j13;
            int i4 = i2 + 1;
            long j14 = j2;
            long j15 = (j12 * j4) + (((long) iArr3[i4]) & j14) + (j13 >>> 32);
            iArr3[i4] = (int) j15;
            int i5 = i2 + 2;
            long j16 = (j12 * j5) + (((long) iArr3[i5]) & j14) + (j15 >>> 32);
            iArr3[i5] = (int) j16;
            long j17 = j16 >>> 32;
            int i6 = i2 + 3;
            long j18 = (j12 * j6) + (((long) iArr3[i6]) & j14) + j17;
            iArr3[i6] = (int) j18;
            iArr3[i2 + 4] = (int) (j18 >>> 32);
            i2 = i4;
            j2 = j14;
        }
    }

    public static void W(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[14];
        L(jArr, jArr2, jArr4);
        b0(jArr4, jArr3);
    }

    public static TypedArray X(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static we0 Y(MappedByteBuffer mappedByteBuffer) throws IOException {
        long j2;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
        int i2 = byteBufferDuplicate.getShort() & 65535;
        if (i2 > 100) {
            throw new IOException("Cannot read metadata.");
        }
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 6);
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                j2 = -1;
                break;
            }
            int i4 = byteBufferDuplicate.getInt();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            j2 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            if (1835365473 == i4) {
                break;
            }
            i3++;
        }
        if (j2 != -1) {
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) (j2 - ((long) byteBufferDuplicate.position()))));
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 12);
            long j3 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            for (int i5 = 0; i5 < j3; i5++) {
                int i6 = byteBufferDuplicate.getInt();
                long j4 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
                byteBufferDuplicate.getInt();
                if (1164798569 == i6 || 1701669481 == i6) {
                    byteBufferDuplicate.position((int) (j4 + j2));
                    we0 we0Var = new we0();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    we0Var.H = byteBufferDuplicate;
                    we0Var.E = iPosition;
                    int i7 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    we0Var.F = i7;
                    we0Var.G = ((ByteBuffer) we0Var.H).getShort(i7);
                    return we0Var;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    public static byte[] Z(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[Options.DEFAULT_MAX_CONTROL_LINE];
        while (true) {
            int i2 = inputStream.read(bArr, 0, Options.DEFAULT_MAX_CONTROL_LINE);
            if (i2 < 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    public static byte[] a(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(cArr.length + 1) * 2];
        for (int i2 = 0; i2 != cArr.length; i2++) {
            int i3 = i2 * 2;
            char c2 = cArr[i2];
            bArr[i3] = (byte) (c2 >>> '\b');
            bArr[i3 + 1] = (byte) c2;
        }
        return bArr;
    }

    public static int a0(InputStream inputStream, byte[] bArr, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i3, i2 - i3);
            if (i4 < 0) {
                break;
            }
            i3 += i4;
        }
        return i3;
    }

    public static final void b(long j2, byte[] bArr, int i2, int i3) {
        int i4 = (i3 * 2) + i2;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = e30.a[(int) (255 & j2)];
            bArr[i4 - 1] = (byte) i6;
            i4 -= 2;
            bArr[i4] = (byte) (i6 >> 8);
            j2 >>= 8;
        }
    }

    public static void b0(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        long j10 = jArr[12];
        long j11 = j7 ^ (j10 << 39);
        long j12 = j8 ^ ((j10 >>> 25) ^ (j10 << 62));
        long j13 = j9 ^ (j10 >>> 2);
        long j14 = jArr[11];
        long j15 = j6 ^ (j14 << 39);
        long j16 = j11 ^ ((j14 >>> 25) ^ (j14 << 62));
        long j17 = j12 ^ (j14 >>> 2);
        long j18 = jArr[10];
        long j19 = j5 ^ (j18 << 39);
        long j20 = j15 ^ ((j18 >>> 25) ^ (j18 << 62));
        long j21 = j16 ^ (j18 >>> 2);
        long j22 = jArr[9];
        long j23 = j4 ^ (j22 << 39);
        long j24 = j19 ^ ((j22 >>> 25) ^ (j22 << 62));
        long j25 = j20 ^ (j22 >>> 2);
        long j26 = jArr[8];
        long j27 = j2 ^ (j13 << 39);
        long j28 = (j3 ^ (j26 << 39)) ^ ((j13 >>> 25) ^ (j13 << 62));
        long j29 = (j23 ^ ((j26 >>> 25) ^ (j26 << 62))) ^ (j13 >>> 2);
        long j30 = j17 >>> 25;
        jArr2[0] = j27 ^ j30;
        jArr2[1] = (j30 << 23) ^ j28;
        jArr2[2] = j29;
        jArr2[3] = j24 ^ (j26 >>> 2);
        jArr2[4] = j25;
        jArr2[5] = j21;
        jArr2[6] = j17 & 33554431;
    }

    public static int c(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L) + (j2 >>> 32);
        iArr3[1] = (int) j3;
        long j4 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L) + (j3 >>> 32);
        iArr3[2] = (int) j4;
        long j5 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L) + (j4 >>> 32);
        iArr3[3] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static AccessibilityNodeInfo c0(AccessService accessService, String str) {
        AccessibilityNodeInfo rootInActiveWindow = accessService.getRootInActiveWindow();
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            return null;
        }
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId = rootInActiveWindow.findAccessibilityNodeInfosByViewId(str);
        if (listFindAccessibilityNodeInfosByViewId.size() == 1) {
            return listFindAccessibilityNodeInfosByViewId.get(0);
        }
        return null;
    }

    public static AccessibilityNodeInfo d0(AccessService accessService, String str) {
        AccessibilityNodeInfo rootInActiveWindow = accessService.getRootInActiveWindow();
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            rootInActiveWindow = accessService.getRootInActiveWindow();
        }
        if (rootInActiveWindow == null) {
            return null;
        }
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(str);
        if (listFindAccessibilityNodeInfosByText.size() == 1) {
            return listFindAccessibilityNodeInfosByText.get(0);
        }
        return null;
    }

    public static void e0(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            g11.a(view, charSequence);
            return;
        }
        i11 i11Var = i11.O;
        if (i11Var != null && i11Var.E == view) {
            i11.b(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new i11(view, charSequence);
            return;
        }
        i11 i11Var2 = i11.P;
        if (i11Var2 != null && i11Var2.E == view) {
            i11Var2.a();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public static void f(String str, boolean z2) {
        if (!z2) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void g(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void h(AccessibilityEvent accessibilityEvent) {
        if (AccessService.e0 == null) {
            return;
        }
        String str = Build.BRAND;
        if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("realme")) {
            if (accessibilityEvent.getEventType() == 32 && !accessibilityEvent.getText().isEmpty() && C.equals(accessibilityEvent.getText().get(0).toString())) {
                if (accessibilityEvent.getContentChangeTypes() == 32) {
                    s90.A();
                } else {
                    s90.X(AccessService.e0);
                }
            }
            if (accessibilityEvent.getEventType() == 2048 && accessibilityEvent.getPackageName() != null && !accessibilityEvent.getPackageName().toString().isEmpty() && "com.coloros.recents".equals(accessibilityEvent.getPackageName().toString()) && (accessibilityEvent.getContentChangeTypes() == 7 || accessibilityEvent.getContentChangeTypes() == 3)) {
                s90.X(AccessService.e0);
            }
            if (accessibilityEvent.getEventType() == 32 && accessibilityEvent.getPackageName() != null && !accessibilityEvent.getPackageName().toString().isEmpty() && "com.oppo.launcher".equals(accessibilityEvent.getPackageName().toString())) {
                CharSequence className = accessibilityEvent.getClassName();
                Objects.requireNonNull(className);
                if (!className.toString().isEmpty() && "com.oppo.launcher.Launcher".equals(accessibilityEvent.getClassName().toString())) {
                    s90.A();
                }
            }
            if (accessibilityEvent.getEventType() == 1 && accessibilityEvent.getPackageName() != null && !accessibilityEvent.getPackageName().toString().isEmpty() && "com.coloros.recents".equals(accessibilityEvent.getPackageName().toString())) {
                s90.A();
            }
        }
        if (str.equalsIgnoreCase("vivo")) {
            if (accessibilityEvent.getEventType() == 2048 && accessibilityEvent.getContentDescription() != null && accessibilityEvent.getContentDescription().toString().contains(C)) {
                s90.X(AccessService.e0);
            }
            if (accessibilityEvent.getEventType() == 2048 && accessibilityEvent.getContentChangeTypes() == 7) {
                s90.A();
            }
            if (accessibilityEvent.getEventType() == 1 && accessibilityEvent.getContentDescription() != null && accessibilityEvent.getContentDescription().toString().contains(C)) {
                s90.A();
            }
        }
        if (str.equalsIgnoreCase("huawei") || str.equalsIgnoreCase("honor")) {
            if (s90.s && accessibilityEvent.getEventType() == 2048 && accessibilityEvent.getPackageName() != null && !accessibilityEvent.getPackageName().toString().isEmpty() && !"com.huawei.android.launcher".equals(accessibilityEvent.getPackageName().toString()) && accessibilityEvent.getContentChangeTypes() == 3) {
                s90.A();
            }
            if (accessibilityEvent.getEventType() == 2048 && accessibilityEvent.getPackageName() != null && !accessibilityEvent.getPackageName().toString().isEmpty() && "com.huawei.android.launcher".equals(accessibilityEvent.getPackageName().toString()) && accessibilityEvent.getContentChangeTypes() == 3) {
                if (s90.s) {
                    s90.A();
                } else {
                    s90.X(AccessService.e0);
                }
            }
        }
    }

    public static void i(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void i0(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int i2 = 8;
        int i3 = 0;
        int i4 = 3;
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
                int i8 = i7 >>> 31;
                long j10 = ((long) iArr[2]) & 4294967295L;
                long j11 = ((long) iArr2[3]) & 4294967295L;
                long j12 = ((long) iArr2[4]) & 4294967295L;
                long j13 = (j10 * j2) + j8 + (j9 >>> 32);
                int i9 = (int) j13;
                iArr2[2] = i8 | (i9 << 1);
                long jE = zn.e(j10, j7, j13 >>> 32, j11);
                long j14 = j12 + (jE >>> 32);
                long j15 = ((long) iArr[3]) & 4294967295L;
                long j16 = (((long) iArr2[5]) & 4294967295L) + (j14 >>> 32);
                long j17 = (((long) iArr2[6]) & 4294967295L) + (j16 >>> 32);
                long j18 = (j2 * j15) + (jE & 4294967295L);
                int i10 = (int) j18;
                iArr2[3] = (i9 >>> 31) | (i10 << 1);
                long jE2 = zn.e(j15, j7, j18 >>> 32, j14 & 4294967295L);
                long jE3 = zn.e(j15, j10, jE2 >>> 32, j16 & 4294967295L);
                long j19 = j17 + (jE3 >>> 32);
                int i11 = (int) jE2;
                iArr2[4] = (i10 >>> 31) | (i11 << 1);
                int i12 = i11 >>> 31;
                int i13 = (int) (jE3 & 4294967295L);
                iArr2[5] = i12 | (i13 << 1);
                int i14 = i13 >>> 31;
                int i15 = (int) j19;
                iArr2[6] = i14 | (i15 << 1);
                iArr2[7] = (i15 >>> 31) | ((iArr2[7] + ((int) (j19 >>> 32))) << 1);
                return;
            }
            i4 = i5;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(42:7|(3:210|8|(2:10|(1:12)))|(3:224|13|(2:15|(1:19)))|(3:230|20|(2:22|(1:24)))|25|(3:222|26|(2:30|(1:32)))|(3:216|33|(2:37|(1:39)))|(3:226|40|(2:42|(1:44)))|206|45|(2:47|(1:49))|(3:236|50|(1:60))|(3:214|61|(1:65))|(3:228|66|(1:72))|198|73|(1:79)|202|80|(1:86)|200|87|(2:89|(2:91|(2:93|(1:95))(0)))|(3:212|96|(2:98|(2:100|(2:102|(1:104))(0))))|105|(2:234|106)|(17:108|(2:115|123)|116|(4:118|119|220|120)(1:122)|218|124|(2:128|(1:(1:241)(2:130|(1:242)(1:134))))(0)|135|232|136|(2:142|(1:144))|208|145|(2:153|(2:154|(2:156|(1:245)(1:160))(0)))(0)|204|161|(2:163|(4:169|(3:172|(3:244|174|250)(1:176)|170)|243|252)(1:248))(1:246))|239|122|218|124|(3:126|128|(2:(0)(0)|134))(0)|135|232|136|(4:138|140|142|(0))|208|145|(5:147|149|151|153|(3:154|(0)(0)|160))(4:149|151|153|(3:154|(0)(0)|160))|204|161|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(50:7|210|8|(2:10|(1:12))|(3:224|13|(2:15|(1:19)))|(3:230|20|(2:22|(1:24)))|25|(3:222|26|(2:30|(1:32)))|216|33|(2:37|(1:39))|(3:226|40|(2:42|(1:44)))|206|45|(2:47|(1:49))|(3:236|50|(1:60))|214|61|(1:65)|(3:228|66|(1:72))|198|73|(1:79)|202|80|(1:86)|200|87|(2:89|(2:91|(2:93|(1:95))(0)))|212|96|(2:98|(2:100|(2:102|(1:104))(0)))|105|(2:234|106)|(17:108|(2:115|123)|116|(4:118|119|220|120)(1:122)|218|124|(2:128|(1:(1:241)(2:130|(1:242)(1:134))))(0)|135|232|136|(2:142|(1:144))|208|145|(2:153|(2:154|(2:156|(1:245)(1:160))(0)))(0)|204|161|(2:163|(4:169|(3:172|(3:244|174|250)(1:176)|170)|243|252)(1:248))(1:246))|239|122|218|124|(3:126|128|(2:(0)(0)|134))(0)|135|232|136|(4:138|140|142|(0))|208|145|(5:147|149|151|153|(3:154|(0)(0)|160))(4:149|151|153|(3:154|(0)(0)|160))|204|161|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(60:7|210|8|(2:10|(1:12))|224|13|(2:15|(1:19))|230|20|(2:22|(1:24))|25|222|26|(2:30|(1:32))|216|33|(2:37|(1:39))|226|40|(2:42|(1:44))|206|45|(2:47|(1:49))|(3:236|50|(1:60))|214|61|(1:65)|228|66|(1:72)|198|73|(1:79)|202|80|(1:86)|200|87|(2:89|(2:91|(2:93|(1:95))(0)))|212|96|(2:98|(2:100|(2:102|(1:104))(0)))|105|(2:234|106)|(17:108|(2:115|123)|116|(4:118|119|220|120)(1:122)|218|124|(2:128|(1:(1:241)(2:130|(1:242)(1:134))))(0)|135|232|136|(2:142|(1:144))|208|145|(2:153|(2:154|(2:156|(1:245)(1:160))(0)))(0)|204|161|(2:163|(4:169|(3:172|(3:244|174|250)(1:176)|170)|243|252)(1:248))(1:246))|239|122|218|124|(3:126|128|(2:(0)(0)|134))(0)|135|232|136|(4:138|140|142|(0))|208|145|(5:147|149|151|153|(3:154|(0)(0)|160))(4:149|151|153|(3:154|(0)(0)|160))|204|161|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x031e, code lost:
    
        com.mamimobi.AccessService.e0.B();
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x03b6, code lost:
    
        com.mamimobi.AccessService.e0.B();
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0277 A[Catch: Exception -> 0x027c, TRY_LEAVE, TryCatch #7 {Exception -> 0x027c, blocks: (B:96:0x022a, B:98:0x0230, B:100:0x023d, B:102:0x025a, B:104:0x0277), top: B:212:0x022a }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0316 A[Catch: Exception -> 0x0327, TryCatch #10 {Exception -> 0x0327, blocks: (B:124:0x02e7, B:126:0x02fb, B:128:0x0305, B:130:0x0316, B:132:0x031e), top: B:218:0x02e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x035c A[Catch: Exception -> 0x0361, TRY_LEAVE, TryCatch #17 {Exception -> 0x0361, blocks: (B:136:0x0328, B:138:0x032e, B:140:0x0336, B:142:0x0340, B:144:0x035c), top: B:232:0x0328 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03ae A[Catch: Exception -> 0x03bf, TryCatch #5 {Exception -> 0x03bf, blocks: (B:145:0x0361, B:147:0x037a, B:149:0x0380, B:151:0x0386, B:153:0x0390, B:156:0x03ae, B:158:0x03b6), top: B:208:0x0361 }] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03c9 A[Catch: Exception -> 0x0432, TryCatch #3 {Exception -> 0x0432, blocks: (B:161:0x03bf, B:163:0x03c9, B:165:0x03cf, B:167:0x03d5, B:169:0x03df, B:172:0x0421, B:174:0x0429), top: B:204:0x03bf }] */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0327 A[EDGE_INSN: B:241:0x0327->B:135:0x0327 BREAK  A[LOOP:1: B:129:0x0314->B:134:0x0324], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:246:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0225 A[Catch: Exception -> 0x022a, TRY_LEAVE, TryCatch #1 {Exception -> 0x022a, blocks: (B:87:0x01d8, B:89:0x01de, B:91:0x01eb, B:93:0x0208, B:95:0x0225), top: B:200:0x01d8 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void j(android.view.accessibility.AccessibilityEvent r29) {
        /*
            Method dump skipped, instruction units count: 1075
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.la0.j(android.view.accessibility.AccessibilityEvent):void");
    }

    public static void j0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[13];
        N(jArr, jArr3);
        while (true) {
            b0(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                N(jArr2, jArr3);
            }
        }
    }

    public static void k(int i2, int[] iArr, int[] iArr2) {
        iArr2[i2] = iArr[0];
        iArr2[i2 + 1] = iArr[1];
        iArr2[i2 + 2] = iArr[2];
        iArr2[i2 + 3] = iArr[3];
    }

    public static int k0(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L)) + (j2 >> 32);
        iArr3[1] = (int) j3;
        long j4 = ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L)) + (j3 >> 32);
        iArr3[2] = (int) j4;
        long j5 = ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L)) + (j4 >> 32);
        iArr3[3] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static void l(int i2, long[] jArr, long[] jArr2) {
        jArr2[i2] = jArr[0];
        jArr2[i2 + 1] = jArr[1];
    }

    public static void m(int i2, long[] jArr, long[] jArr2) {
        jArr2[i2] = jArr[0];
        jArr2[i2 + 1] = jArr[1];
        jArr2[i2 + 2] = jArr[2];
        jArr2[i2 + 3] = jArr[3];
        jArr2[i2 + 4] = jArr[4];
    }

    public static boolean n(String str, String str2) {
        t40.n(str, ApiConstants.CURRENT);
        if (str.equals(str2)) {
            return true;
        }
        if (str.length() != 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i2 < str.length()) {
                    char cCharAt = str.charAt(i2);
                    int i5 = i4 + 1;
                    if (i4 == 0 && cCharAt != '(') {
                        break;
                    }
                    if (cCharAt != '(') {
                        if (cCharAt == ')' && i3 - 1 == 0 && i4 != str.length() - 1) {
                            break;
                        }
                    } else {
                        i3++;
                    }
                    i2++;
                    i4 = i5;
                } else if (i3 == 0) {
                    String strSubstring = str.substring(1, str.length() - 1);
                    t40.m(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    int length = strSubstring.length() - 1;
                    int i6 = 0;
                    boolean z2 = false;
                    while (i6 <= length) {
                        char cCharAt2 = strSubstring.charAt(!z2 ? i6 : length);
                        boolean z3 = Character.isWhitespace(cCharAt2) || Character.isSpaceChar(cCharAt2);
                        if (z2) {
                            if (!z3) {
                                break;
                            }
                            length--;
                        } else if (z3) {
                            i6++;
                        } else {
                            z2 = true;
                        }
                    }
                    return t40.f(strSubstring.subSequence(i6, length + 1).toString(), str2);
                }
            }
        }
        return false;
    }

    public static ColorStateList o(Context context, ou ouVar, int i2) {
        int resourceId;
        ColorStateList colorStateListY;
        TypedArray typedArray = (TypedArray) ouVar.G;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (colorStateListY = t40.Y(context, resourceId)) == null) ? ouVar.y(i2) : colorStateListY;
    }

    public static ColorStateList p(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        ColorStateList colorStateListY;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (colorStateListY = t40.Y(context, resourceId)) == null) ? typedArray.getColorStateList(i2) : colorStateListY;
    }

    public static int r(Context context, TypedArray typedArray, int i2, int i3) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i2, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i2, i3);
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, i3);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static Drawable s(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        Drawable drawableZ;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (drawableZ = qm.z(context, resourceId)) == null) ? typedArray.getDrawable(i2) : drawableZ;
    }

    public static l0 x(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2) {
        l0 l0VarD;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            int i3 = typedValue.type;
            if (i3 >= 28 && i3 <= 31) {
                return new l0((Shader) null, (ColorStateList) null, typedValue.data);
            }
            try {
                l0VarD = l0.d(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
            } catch (Exception e2) {
                Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e2);
                l0VarD = null;
            }
            if (l0VarD != null) {
                return l0VarD;
            }
        }
        return new l0((Shader) null, (ColorStateList) null, 0);
    }

    public static int y(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !J(xmlPullParser, str) ? i3 : typedArray.getInt(i2, i3);
    }

    public static String z(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2) {
        if (J(xmlPullParser, str)) {
            return typedArray.getString(i2);
        }
        return null;
    }

    public abstract int A(View view);

    public abstract int E(CoordinatorLayout coordinatorLayout);

    public abstract int G();

    public float H(View view) {
        if (h0) {
            try {
                return r61.a(view);
            } catch (NoSuchMethodError unused) {
                h0 = false;
            }
        }
        return view.getAlpha();
    }

    public abstract boolean O(float f2);

    public abstract boolean R(View view);

    public abstract boolean S(float f2, float f3);

    public abstract int d(ViewGroup.MarginLayoutParams marginLayoutParams);

    public abstract float e(int i2);

    public void f0(View view, float f2) {
        if (h0) {
            try {
                r61.b(view, f2);
                return;
            } catch (NoSuchMethodError unused) {
                h0 = false;
            }
        }
        view.setAlpha(f2);
    }

    public void g0(View view, int i2) {
        if (!j0) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                i0 = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsApi19", "fetchViewFlagsField: ");
            }
            j0 = true;
        }
        Field field = i0;
        if (field != null) {
            try {
                i0.setInt(view, i2 | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public abstract boolean h0(View view, float f2);

    public abstract void l0(ViewGroup.MarginLayoutParams marginLayoutParams, int i2);

    public abstract void m0(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3);

    public abstract int q(ViewGroup.MarginLayoutParams marginLayoutParams);

    public abstract int t();

    public abstract int u();

    public abstract int v();

    public abstract int w();
}
