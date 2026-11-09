package defpackage;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.R$id;
import com.google.android.material.appbar.MaterialToolbar;
import com.mamimobi.AccessService;
import com.mamimobi.receiver.DAReceiver;
import defpackage.d10;
import defpackage.l21;
import defpackage.t40;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.CancellationException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import kotlin.Result;
import kotlin.UninitializedPropertyAccessException;
import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class t40 {
    public static final Object a = new Object();
    public static final Object[] b = new Object[0];
    public static final wy0 c = new wy0("UNDEFINED", 0);
    public static final wy0 d = new wy0("REUSABLE_CLAIMED", 0);
    public static final char[] e = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final short[] f = {2285, 2571, 2970, 1812, 1493, 1422, 287, 202, 3158, 622, 1577, 182, 962, 2127, 1855, 1468, 573, 2004, 264, 383, 2500, 1458, 1727, 3199, 2648, 1017, 732, 608, 1787, 411, 3124, 1758, 1223, 652, 2777, 1015, 2036, 1491, 3047, 1785, 516, 3321, 3009, 2663, 1711, 2167, 126, 1469, 2476, 3239, 3058, 830, 107, 1908, 3082, 2378, 2931, 961, 1821, 2604, 448, 2264, 677, 2054, 2226, 430, 555, 843, 2078, 871, 1550, 105, 422, 587, 177, 3094, 3038, 2869, 1574, 1653, 3083, 778, 1159, 3182, 2552, 1483, 2727, 1119, 1739, 644, 2457, 349, 418, 329, 3173, 3254, 817, 1097, 603, 610, 1322, 2044, 1864, 384, 2114, 3193, 1218, 1994, 2455, 220, 2142, 1670, 2144, 1799, 2051, 794, 1819, 2475, 2459, 478, 3221, 3021, 996, 991, 958, 1869, 1522, 1628};
    public static final byte[] g = {112, 114, 111, 0};
    public static final byte[] h = {112, 114, 109, 0};
    public static final er0 i = new er0();
    public static final er0 j = new er0();
    public static final er0 k = new er0();
    public static final int[] l = {-21389, -2, -1, -1, -1};
    public static final int[] m = {457489321, 42778, 1, 0, 0, -42778, -3, -1, -1, -1};
    public static final int[] n = {-457489321, -42779, -2, -1, -1, 42777, 2};
    public static final int[] o = {-977, -2, -1, -1, -1, -1, -1, -1};
    public static final int[] p = {954529, 1954, 1, 0, 0, 0, 0, 0, -1954, -3, -1, -1, -1, -1, -1, -1};
    public static final int[] q = {-954529, -1955, -2, -1, -1, -1, -1, -1, 1953, 2};
    public static final long[] r = {-5270498306774157648L, 5270498306774195053L, 19634136210L};
    public static final q4 s = new q4(7);
    public static final int[] t = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    public static final int[] u = {34513072, 59165138, 4688974, 3500415, 6194736, 33281959, 54535759, 32551604, 163342, 5703241};
    public static String v = null;
    public static boolean w = false;
    public static Method x = null;
    public static boolean y = false;
    public static Field z;

    public static void A(int i2, int i3, int[] iArr, int[] iArr2) {
        for (int i4 = 0; i4 < 10; i4++) {
            iArr2[i3 + i4] = iArr[i2 + i4];
        }
    }

    public static int A0(int i2, Rect rect, Rect rect2) {
        int i3;
        int i4;
        if (i2 == 17) {
            i3 = rect.left;
            i4 = rect2.right;
        } else if (i2 == 33) {
            i3 = rect.top;
            i4 = rect2.bottom;
        } else if (i2 == 66) {
            i3 = rect2.left;
            i4 = rect.right;
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i3 = rect2.top;
            i4 = rect.bottom;
        }
        return Math.max(0, i3 - i4);
    }

    public static void A1(ByteArrayOutputStream byteArrayOutputStream, int i2) throws IOException {
        z1(byteArrayOutputStream, i2, 2);
    }

    public static byte[] B(ho[] hoVarArr, byte[] bArr) throws IOException {
        int i2 = 0;
        int length = 0;
        for (ho hoVar : hoVarArr) {
            length += ((((hoVar.g * 2) + 7) & (-8)) / 8) + (hoVar.e * 2) + R(hoVar.a, hoVar.b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + hoVar.f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, l21.F)) {
            int length2 = hoVarArr.length;
            while (i2 < length2) {
                ho hoVar2 = hoVarArr[i2];
                w1(byteArrayOutputStream, hoVar2, R(hoVar2.a, hoVar2.b, bArr));
                v1(byteArrayOutputStream, hoVar2);
                i2++;
            }
        } else {
            for (ho hoVar3 : hoVarArr) {
                w1(byteArrayOutputStream, hoVar3, R(hoVar3.a, hoVar3.b, bArr));
            }
            int length3 = hoVarArr.length;
            while (i2 < length3) {
                v1(byteArrayOutputStream, hoVarArr[i2]);
                i2++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static int B0(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs(((rect.width() / 2) + rect.left) - ((rect2.width() / 2) + rect2.left));
        }
        return Math.abs(((rect.height() / 2) + rect.top) - ((rect2.height() / 2) + rect2.top));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ui C(final d10 d10Var, final Object obj, final ui uiVar) {
        n(d10Var, "<this>");
        n(uiVar, "completion");
        if (d10Var instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) d10Var).create(obj, uiVar);
        }
        final bj context = uiVar.getContext();
        return context == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(uiVar, d10Var, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3
            public int F;
            public final /* synthetic */ d10 G;
            public final /* synthetic */ Object H;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(uiVar);
                this.G = d10Var;
                this.H = obj;
                t40.l(uiVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj2) {
                int i2 = this.F;
                if (i2 != 0) {
                    if (i2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                    this.F = 2;
                    a.b(obj2);
                    return obj2;
                }
                this.F = 1;
                a.b(obj2);
                d10 d10Var2 = this.G;
                t40.l(d10Var2, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                l21.m(2, d10Var2);
                return d10Var2.invoke(this.H, this);
            }
        } : new ContinuationImpl(uiVar, context, d10Var, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4
            public int H;
            public final /* synthetic */ d10 I;
            public final /* synthetic */ Object J;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(uiVar, context);
                this.I = d10Var;
                this.J = obj;
                t40.l(uiVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj2) {
                int i2 = this.H;
                if (i2 != 0) {
                    if (i2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                    this.H = 2;
                    a.b(obj2);
                    return obj2;
                }
                this.H = 1;
                a.b(obj2);
                d10 d10Var2 = this.I;
                t40.l(d10Var2, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                l21.m(2, d10Var2);
                return d10Var2.invoke(this.J, this);
            }
        };
    }

    public static void C0(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = iArr[0];
        int i3 = iArr2[0];
        int i4 = iArr[1];
        int i5 = iArr2[1];
        int i6 = iArr[2];
        int i7 = iArr2[2];
        int i8 = iArr[3];
        int i9 = iArr2[3];
        int i10 = iArr[4];
        int i11 = iArr2[4];
        int i12 = iArr[5];
        int i13 = iArr2[5];
        int i14 = iArr[6];
        int i15 = iArr2[6];
        int i16 = iArr[7];
        int i17 = iArr2[7];
        int i18 = iArr[8];
        int i19 = iArr2[8];
        int i20 = iArr[9];
        int i21 = iArr2[9];
        long j2 = i2;
        long j3 = i3;
        long j4 = j2 * j3;
        long j5 = i5;
        long j6 = j2 * j5;
        long j7 = i4;
        long j8 = (j7 * j3) + j6;
        long j9 = i7;
        long j10 = (j7 * j5) + (j2 * j9);
        long j11 = i6;
        long j12 = (j11 * j3) + j10;
        long j13 = ((j11 * j5) + (j7 * j9)) << 1;
        long j14 = i9;
        long j15 = j2 * j14;
        long j16 = i8;
        long jE = zn.e(j16, j3, j15, j13);
        long j17 = (j11 * j9) << 1;
        long j18 = i11;
        long j19 = i10;
        long jE2 = zn.e(j19, j3, (j16 * j5) + (j7 * j14) + (j2 * j18), j17);
        long j20 = ((j19 * j5) + ((j16 * j9) + ((j11 * j14) + (j7 * j18)))) << 1;
        long j21 = (j16 * j14) + (((j19 * j9) + (j11 * j18)) << 1);
        long j22 = (j19 * j14) + (j16 * j18);
        long j23 = (j19 * j18) << 1;
        long j24 = i12;
        long j25 = i13;
        long j26 = j24 * j25;
        long j27 = i15;
        long j28 = j24 * j27;
        long j29 = i14;
        long j30 = (j29 * j25) + j28;
        long j31 = i17;
        long j32 = (j29 * j27) + (j24 * j31);
        long j33 = i16;
        long j34 = i19;
        long j35 = j24 * j34;
        long j36 = i18;
        long jE3 = zn.e(j36, j25, j35, ((j33 * j27) + (j29 * j31)) << 1);
        long j37 = i21;
        long j38 = (j36 * j27) + (j29 * j34) + (j24 * j37);
        long j39 = i20;
        long jE4 = zn.e(j39, j25, j38, (j33 * j31) << 1);
        long j40 = j4 - (((j27 * j39) + ((j36 * j31) + ((j33 * j34) + (j29 * j37)))) * 76);
        long j41 = j8 - (((j36 * j34) + (((j31 * j39) + (j33 * j37)) << 1)) * 38);
        long j42 = j12 - (((j39 * j34) + (j36 * j37)) * 38);
        long j43 = jE - ((j37 * j39) * 76);
        long j44 = j20 - j26;
        long j45 = j21 - j30;
        long j46 = j22 - ((j33 * j25) + j32);
        long j47 = j23 - jE3;
        long j48 = i2 + i12;
        long j49 = i3 + i13;
        long j50 = j48 * j49;
        long j51 = i5 + i15;
        long j52 = j48 * j51;
        long j53 = i4 + i14;
        long j54 = (j53 * j49) + j52;
        long j55 = i7 + i17;
        long j56 = i6 + i16;
        long j57 = (j56 * j49) + (j53 * j51) + (j48 * j55);
        long j58 = ((j56 * j51) + (j53 * j55)) << 1;
        long j59 = i9 + i19;
        long j60 = i8 + i18;
        long jE5 = zn.e(j60, j49, j48 * j59, j58);
        long j61 = i11 + i21;
        long j62 = i10 + i20;
        long jE6 = zn.e(j62, j49, (j60 * j51) + (j53 * j59) + (j48 * j61), (j56 * j55) << 1);
        long j63 = ((j51 * j62) + ((j60 * j55) + ((j56 * j59) + (j53 * j61)))) << 1;
        long j64 = (j60 * j59) + (((j55 * j62) + (j56 * j61)) << 1);
        long j65 = (j62 * j59) + (j60 * j61);
        long j66 = (j61 * j62) << 1;
        long j67 = (jE5 - j43) + j47;
        int i22 = ((int) j67) & 67108863;
        long j68 = ((jE6 - jE2) - jE4) + (j67 >> 26);
        int i23 = ((int) j68) & 33554431;
        long j69 = ((((j68 >> 25) + j63) - j44) * 38) + j40;
        iArr3[0] = ((int) j69) & 67108863;
        long j70 = ((j64 - j45) * 38) + j41 + (j69 >> 26);
        iArr3[1] = ((int) j70) & 67108863;
        long j71 = ((j65 - j46) * 38) + j42 + (j70 >> 26);
        iArr3[2] = ((int) j71) & 33554431;
        long j72 = ((j66 - j47) * 38) + j43 + (j71 >> 25);
        iArr3[3] = ((int) j72) & 67108863;
        long jE7 = zn.e(jE4, 38L, jE2, j72 >> 26);
        iArr3[4] = ((int) jE7) & 33554431;
        long j73 = (j50 - j40) + j44 + (jE7 >> 25);
        iArr3[5] = ((int) j73) & 67108863;
        long j74 = (j54 - j41) + j45 + (j73 >> 26);
        iArr3[6] = ((int) j74) & 67108863;
        long j75 = (j57 - j42) + j46 + (j74 >> 26);
        iArr3[7] = ((int) j75) & 33554431;
        long j76 = (j75 >> 25) + ((long) i22);
        iArr3[8] = ((int) j76) & 67108863;
        iArr3[9] = i23 + ((int) (j76 >> 26));
    }

    public static void D(int i2, int i3, byte[] bArr, int[] iArr) {
        int iG = G(i2, bArr);
        int iG2 = G(i2 + 4, bArr);
        int iG3 = G(i2 + 8, bArr);
        int iG4 = G(i2 + 12, bArr);
        iArr[i3] = iG & 67108863;
        iArr[i3 + 1] = ((iG >>> 26) | (iG2 << 6)) & 67108863;
        iArr[i3 + 2] = ((iG2 >>> 20) | (iG3 << 12)) & 33554431;
        iArr[i3 + 3] = 67108863 & ((iG4 << 19) | (iG3 >>> 13));
        iArr[i3 + 4] = iG4 >>> 7;
    }

    public static void D0(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[10];
        ma0.W(iArr, iArr2, iArr4);
        V0(iArr4, iArr3);
    }

    public static void E(int i2, int i3, int[] iArr, int[] iArr2) {
        int i4 = iArr[i2];
        int i5 = iArr[i2 + 1];
        int i6 = iArr[i2 + 2];
        int i7 = iArr[i2 + 3];
        iArr2[i3] = i4 & 67108863;
        iArr2[i3 + 1] = ((i4 >>> 26) | (i5 << 6)) & 67108863;
        iArr2[i3 + 2] = ((i5 >>> 20) | (i6 << 12)) & 33554431;
        iArr2[i3 + 3] = 67108863 & ((i7 << 19) | (i6 >>> 13));
        iArr2[i3 + 4] = i7 >>> 7;
    }

    public static void E0(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        fa0.m0(iArr, iArr2, iArr4);
        X0(iArr4, iArr3);
    }

    public static int F(int i2, byte[] bArr) {
        return ((bArr[i2 + 2] & 255) << 16) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8);
    }

    public static void F0(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        s0(jArr, jArr2, jArr4);
        W0(jArr4, jArr3);
    }

    public static int G(int i2, byte[] bArr) {
        return (bArr[i2 + 3] << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public static void G0(int[] iArr, int[] iArr2, int[] iArr3) {
        if ((ma0.Y(iArr, iArr2, iArr3) != 0 || (iArr3[9] == -1 && fa0.T(10, iArr3, m))) && fa0.i(7, n, iArr3) != 0) {
            fa0.a0(10, 7, iArr3);
        }
    }

    public static void H(int i2, byte[] bArr, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = I(i3 * 4, bArr);
        }
    }

    public static void H0(int[] iArr, int[] iArr2) {
        for (int i2 = 0; i2 < 10; i2++) {
            iArr2[i2] = -iArr[i2];
        }
    }

    public static int I(int i2, byte[] bArr) {
        return (bArr[i2 + 3] << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    public static void I0(int[] iArr) {
        int i2 = (iArr[9] >>> 23) & 1;
        U0(iArr, i2);
        U0(iArr, -i2);
    }

    public static boolean J(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z2 = true;
        for (File file2 : fileArrListFiles) {
            z2 = J(file2) && z2;
        }
        return z2;
    }

    public static void J0(int[] iArr) {
        iArr[0] = 1;
        for (int i2 = 1; i2 < 10; i2++) {
            iArr[i2] = 0;
        }
    }

    public static boolean K(View view, KeyEvent keyEvent) {
        ArrayList arrayList;
        int size;
        int iIndexOfKey;
        WeakHashMap weakHashMap = f51.a;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList2 = e51.d;
        e51 e51Var = (e51) view.getTag(R$id.tag_unhandled_key_event_manager);
        WeakReference weakReference = null;
        if (e51Var == null) {
            e51Var = new e51();
            e51Var.a = null;
            e51Var.b = null;
            e51Var.c = null;
            view.setTag(R$id.tag_unhandled_key_event_manager, e51Var);
        }
        WeakReference weakReference2 = e51Var.c;
        if (weakReference2 != null && weakReference2.get() == keyEvent) {
            return false;
        }
        e51Var.c = new WeakReference(keyEvent);
        if (e51Var.b == null) {
            e51Var.b = new SparseArray();
        }
        SparseArray sparseArray = e51Var.b;
        if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference = (WeakReference) sparseArray.valueAt(iIndexOfKey);
            sparseArray.removeAt(iIndexOfKey);
        }
        if (weakReference == null) {
            weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference == null) {
            return false;
        }
        View view2 = (View) weakReference.get();
        if (view2 == null || !view2.isAttachedToWindow() || (arrayList = (ArrayList) view2.getTag(R$id.tag_unhandled_key_listeners)) == null || (size = arrayList.size() - 1) < 0) {
            return true;
        }
        arrayList.get(size).getClass();
        throw new ClassCastException();
    }

    public static String K0(s10 s10Var) {
        StringBuffer stringBuffer = new StringBuffer("GOST3410 Private Key [");
        String str = jx0.a;
        BigInteger bigIntegerModPow = s10Var.G.modPow(null, s10Var.E);
        stringBuffer.append(S(bigIntegerModPow, s10Var));
        stringBuffer.append("]");
        stringBuffer.append(str);
        stringBuffer.append("                  Y: ");
        stringBuffer.append(bigIntegerModPow.toString(16));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static boolean L(m70 m70Var, View view, Window.Callback callback, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener onKeyListener;
        boolean zBooleanValue = false;
        if (m70Var != null) {
            if (Build.VERSION.SDK_INT >= 28) {
                return m70Var.b(keyEvent);
            }
            if (callback instanceof Activity) {
                Activity activity = (Activity) callback;
                activity.onUserInteraction();
                Window window = activity.getWindow();
                if (window.hasFeature(8)) {
                    ActionBar actionBar = activity.getActionBar();
                    if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                        if (!w) {
                            try {
                                x = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                            } catch (NoSuchMethodException unused) {
                            }
                            w = true;
                        }
                        Method method = x;
                        if (method != null) {
                            try {
                                Object objInvoke = method.invoke(actionBar, keyEvent);
                                if (objInvoke != null) {
                                    zBooleanValue = ((Boolean) objInvoke).booleanValue();
                                }
                            } catch (IllegalAccessException | InvocationTargetException unused2) {
                            }
                        }
                        if (zBooleanValue) {
                            return true;
                        }
                    }
                }
                if (window.superDispatchKeyEvent(keyEvent)) {
                    return true;
                }
                View decorView = window.getDecorView();
                if (f51.c(decorView, keyEvent)) {
                    return true;
                }
                return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
            }
            if (callback instanceof Dialog) {
                Dialog dialog = (Dialog) callback;
                if (!y) {
                    try {
                        Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                        z = declaredField;
                        declaredField.setAccessible(true);
                    } catch (NoSuchFieldException unused3) {
                    }
                    y = true;
                }
                Field field = z;
                if (field != null) {
                    try {
                        onKeyListener = (DialogInterface.OnKeyListener) field.get(dialog);
                    } catch (IllegalAccessException unused4) {
                        onKeyListener = null;
                    }
                } else {
                    onKeyListener = null;
                }
                if (onKeyListener != null && onKeyListener.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
                    return true;
                }
                Window window2 = dialog.getWindow();
                if (window2.superDispatchKeyEvent(keyEvent)) {
                    return true;
                }
                View decorView2 = window2.getDecorView();
                if (f51.c(decorView2, keyEvent)) {
                    return true;
                }
                return keyEvent.dispatch(dialog, decorView2 != null ? decorView2.getKeyDispatcherState() : null, dialog);
            }
            if ((view != null && f51.c(view, keyEvent)) || m70Var.b(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] L0(InputStream inputStream, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i3, i2 - i3);
            if (i4 < 0) {
                throw new IllegalStateException(zn.j(i2, "Not enough bytes to read: "));
            }
            i3 += i4;
        }
        return bArr;
    }

    public static void M(int i2, int i3, byte[] bArr, int[] iArr) {
        int i4 = iArr[i2];
        int i5 = iArr[i2 + 1];
        int i6 = iArr[i2 + 2];
        int i7 = iArr[i2 + 3];
        int i8 = iArr[i2 + 4];
        O(bArr, (i5 << 26) | i4, i3);
        O(bArr, (i5 >>> 6) | (i6 << 20), i3 + 4);
        O(bArr, (i6 >>> 12) | (i7 << 13), i3 + 8);
        O(bArr, (i8 << 7) | (i7 >>> 19), i3 + 12);
    }

    public static int[] M0(ByteArrayInputStream byteArrayInputStream, int i2) {
        int[] iArr = new int[i2];
        int iS0 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            iS0 += (int) S0(byteArrayInputStream, 2);
            iArr[i3] = iS0;
        }
        return iArr;
    }

    public static void N(int i2, int i3, int[] iArr, int[] iArr2) {
        int i4 = iArr[i2];
        int i5 = iArr[i2 + 1];
        int i6 = iArr[i2 + 2];
        int i7 = iArr[i2 + 3];
        int i8 = iArr[i2 + 4];
        iArr2[i3] = (i5 << 26) | i4;
        iArr2[i3 + 1] = (i5 >>> 6) | (i6 << 20);
        iArr2[i3 + 2] = (i6 >>> 12) | (i7 << 13);
        iArr2[i3 + 3] = (i8 << 7) | (i7 >>> 19);
    }

    public static byte[] N0(FileInputStream fileInputStream, int i2, int i3) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i3];
            byte[] bArr2 = new byte[2048];
            int i4 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i4 < i2) {
                int i5 = fileInputStream.read(bArr2);
                if (i5 < 0) {
                    throw new IllegalStateException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i2 + " bytes");
                }
                inflater.setInput(bArr2, 0, i5);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i3 - iInflate);
                    i4 += i5;
                } catch (DataFormatException e2) {
                    throw new IllegalStateException(e2.getMessage());
                }
            }
            if (i4 == i2) {
                if (inflater.finished()) {
                    return bArr;
                }
                throw new IllegalStateException("Inflater did not finish");
            }
            throw new IllegalStateException("Didn't read enough bytes during decompression. expected=" + i2 + " actual=" + i4);
        } finally {
            inflater.end();
        }
    }

    public static void O(byte[] bArr, int i2, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }

    public static ho[] O0(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, ho[] hoVarArr) throws IOException {
        byte[] bArr3 = l21.I;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, l21.J)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int iS0 = (int) S0(fileInputStream, 2);
            byte[] bArrN0 = N0(fileInputStream, (int) S0(fileInputStream, 4), (int) S0(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrN0);
            try {
                ho[] hoVarArrQ0 = Q0(byteArrayInputStream, bArr2, iS0, hoVarArr);
                byteArrayInputStream.close();
                return hoVarArrQ0;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(l21.D, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int iS02 = (int) S0(fileInputStream, 1);
        byte[] bArrN02 = N0(fileInputStream, (int) S0(fileInputStream, 4), (int) S0(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrN02);
        try {
            ho[] hoVarArrP0 = P0(byteArrayInputStream2, iS02, hoVarArr);
            byteArrayInputStream2.close();
            return hoVarArrP0;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static void P(byte[] bArr, int i2, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }

    public static ho[] P0(ByteArrayInputStream byteArrayInputStream, int i2, ho[] hoVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new ho[0];
        }
        if (i2 != hoVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i2];
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int iS0 = (int) S0(byteArrayInputStream, 2);
            iArr[i3] = (int) S0(byteArrayInputStream, 2);
            strArr[i3] = new String(L0(byteArrayInputStream, iS0), StandardCharsets.UTF_8);
        }
        for (int i4 = 0; i4 < i2; i4++) {
            ho hoVar = hoVarArr[i4];
            if (!hoVar.b.equals(strArr[i4])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i5 = iArr[i4];
            hoVar.e = i5;
            hoVar.h = M0(byteArrayInputStream, i5);
        }
        return hoVarArr;
    }

    public static void Q(long j2, byte[] bArr, int i2) {
        P(bArr, (int) j2, i2);
        int i3 = (int) (j2 >>> 32);
        bArr[i2 + 4] = (byte) i3;
        bArr[i2 + 5] = (byte) (i3 >>> 8);
        bArr[i2 + 6] = (byte) (i3 >>> 16);
    }

    public static ho[] Q0(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i2, ho[] hoVarArr) throws IOException {
        if (byteArrayInputStream.available() == 0) {
            return new ho[0];
        }
        if (i2 != hoVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            S0(byteArrayInputStream, 2);
            String str = new String(L0(byteArrayInputStream, (int) S0(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long jS0 = S0(byteArrayInputStream, 4);
            int iS0 = (int) S0(byteArrayInputStream, 2);
            ho hoVar = null;
            if (hoVarArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                int i4 = 0;
                while (true) {
                    if (i4 >= hoVarArr.length) {
                        break;
                    }
                    if (hoVarArr[i4].b.equals(strSubstring)) {
                        hoVar = hoVarArr[i4];
                        break;
                    }
                    i4++;
                }
            }
            if (hoVar == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            hoVar.d = jS0;
            int[] iArrM0 = M0(byteArrayInputStream, iS0);
            if (Arrays.equals(bArr, l21.H)) {
                hoVar.e = iS0;
                hoVar.h = iArrM0;
            }
        }
        return hoVarArr;
    }

    public static String R(String str, String str2, byte[] bArr) {
        byte[] bArr2 = l21.H;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = l21.G;
        String str3 = (zEquals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            if ("!".equals(str3)) {
                return str2.replace(":", "!");
            }
            if (":".equals(str3)) {
                return str2.replace("!", ":");
            }
        } else {
            if (str2.equals("classes.dex")) {
                return str;
            }
            if (str2.contains("!") || str2.contains(":")) {
                if ("!".equals(str3)) {
                    return str2.replace(":", "!");
                }
                if (":".equals(str3)) {
                    return str2.replace("!", ":");
                }
            } else if (!str2.endsWith(".apk")) {
                return zn.o(zn.p(str), (Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!", str2);
            }
        }
        return str2;
    }

    public static ho[] R0(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, l21.E)) {
            throw new IllegalStateException("Unsupported version");
        }
        int iS0 = (int) S0(fileInputStream, 1);
        byte[] bArrN0 = N0(fileInputStream, (int) S0(fileInputStream, 4), (int) S0(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrN0);
        try {
            ho[] hoVarArrT0 = T0(byteArrayInputStream, str, iS0);
            byteArrayInputStream.close();
            return hoVarArrT0;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String S(BigInteger bigInteger, s10 s10Var) {
        byte[] bArrN = z91.n(bigInteger.toByteArray(), s10Var.E.toByteArray(), s10Var.G.toByteArray());
        zq0 sHAKENativeDigest = org.bouncycastle.crypto.a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(bArrN, 0, bArrN.length);
        byte[] bArr = new byte[20];
        sHAKENativeDigest.h(bArr, 0, 20);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 != 20; i2++) {
            if (i2 > 0) {
                stringBuffer.append(":");
            }
            char[] cArr = e;
            stringBuffer.append(cArr[(bArr[i2] >>> 4) & 15]);
            stringBuffer.append(cArr[bArr[i2] & 15]);
        }
        return stringBuffer.toString();
    }

    public static long S0(InputStream inputStream, int i2) throws IOException {
        byte[] bArrL0 = L0(inputStream, i2);
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 += ((long) (bArrL0[i3] & 255)) << (i3 * 8);
        }
        return j2;
    }

    public static t40 T(int i2, double[] dArr, double[][] dArr2) {
        int i3 = dArr.length == 1 ? 2 : i2;
        double d2 = 0.0d;
        if (i3 != 0) {
            if (i3 == 2) {
                double d3 = dArr[0];
                double[] dArr3 = dArr2[0];
                dk dkVar = new dk();
                dkVar.A = d3;
                dkVar.B = dArr3;
                return dkVar;
            }
            c8 c8Var = new c8();
            int length = dArr2[0].length;
            c8Var.B = dArr;
            c8Var.C = dArr2;
            if (length > 2) {
                double d4 = 0.0d;
                int i4 = 0;
                while (i4 < dArr.length) {
                    double d5 = dArr2[i4][0];
                    if (i4 > 0) {
                        Math.hypot(d5 - d2, d5 - d4);
                    }
                    i4++;
                    d4 = d5;
                    d2 = d4;
                }
            }
            return c8Var;
        }
        cf0 cf0Var = new cf0();
        int length2 = dArr.length;
        int length3 = dArr2[0].length;
        int i5 = length2 - 1;
        Class cls = Double.TYPE;
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) cls, i5, length3);
        double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) cls, length2, length3);
        for (int i6 = 0; i6 < length3; i6++) {
            int i7 = 0;
            while (i7 < i5) {
                int i8 = i7 + 1;
                double d6 = dArr[i8] - dArr[i7];
                double[] dArr6 = dArr4[i7];
                double d7 = (dArr2[i8][i6] - dArr2[i7][i6]) / d6;
                dArr6[i6] = d7;
                if (i7 == 0) {
                    dArr5[i7][i6] = d7;
                } else {
                    dArr5[i7][i6] = (dArr4[i7 - 1][i6] + d7) * 0.5d;
                }
                i7 = i8;
            }
            dArr5[i5][i6] = dArr4[length2 - 2][i6];
        }
        int i9 = 0;
        while (i9 < i5) {
            int i10 = 0;
            while (i10 < length3) {
                double d8 = dArr4[i9][i10];
                if (d8 == d2) {
                    dArr5[i9][i10] = d2;
                    dArr5[i9 + 1][i10] = d2;
                } else {
                    double d9 = dArr5[i9][i10] / d8;
                    int i11 = i9 + 1;
                    double d10 = dArr5[i11][i10] / d8;
                    double dHypot = Math.hypot(d9, d10);
                    if (dHypot > 9.0d) {
                        double d11 = 3.0d / dHypot;
                        double[] dArr7 = dArr5[i9];
                        double[] dArr8 = dArr4[i9];
                        dArr7[i10] = d9 * d11 * dArr8[i10];
                        dArr5[i11][i10] = d11 * d10 * dArr8[i10];
                    }
                }
                i10++;
                d2 = 0.0d;
            }
            i9++;
            d2 = 0.0d;
        }
        cf0Var.A = dArr;
        cf0Var.B = dArr2;
        cf0Var.C = dArr5;
        return cf0Var;
    }

    public static ho[] T0(ByteArrayInputStream byteArrayInputStream, String str, int i2) throws IOException {
        TreeMap treeMap;
        if (byteArrayInputStream.available() == 0) {
            return new ho[0];
        }
        ho[] hoVarArr = new ho[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int iS0 = (int) S0(byteArrayInputStream, 2);
            int iS02 = (int) S0(byteArrayInputStream, 2);
            hoVarArr[i3] = new ho(str, new String(L0(byteArrayInputStream, iS0), StandardCharsets.UTF_8), S0(byteArrayInputStream, 4), iS02, (int) S0(byteArrayInputStream, 4), (int) S0(byteArrayInputStream, 4), new int[iS02], new TreeMap());
        }
        for (int i4 = 0; i4 < i2; i4++) {
            ho hoVar = hoVarArr[i4];
            int iAvailable = byteArrayInputStream.available() - hoVar.f;
            int iS03 = 0;
            while (true) {
                int iAvailable2 = byteArrayInputStream.available();
                treeMap = hoVar.i;
                if (iAvailable2 <= iAvailable) {
                    break;
                }
                iS03 += (int) S0(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iS03), 1);
                for (int iS04 = (int) S0(byteArrayInputStream, 2); iS04 > 0; iS04--) {
                    S0(byteArrayInputStream, 2);
                    int iS05 = (int) S0(byteArrayInputStream, 1);
                    if (iS05 != 6 && iS05 != 7) {
                        while (iS05 > 0) {
                            S0(byteArrayInputStream, 1);
                            for (int iS06 = (int) S0(byteArrayInputStream, 1); iS06 > 0; iS06--) {
                                S0(byteArrayInputStream, 2);
                            }
                            iS05--;
                        }
                    }
                }
            }
            if (byteArrayInputStream.available() != iAvailable) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            hoVar.h = M0(byteArrayInputStream, hoVar.e);
            int i5 = hoVar.g;
            BitSet bitSetValueOf = BitSet.valueOf(L0(byteArrayInputStream, (((i5 * 2) + 7) & (-8)) / 8));
            for (int i6 = 0; i6 < i5; i6++) {
                int i7 = bitSetValueOf.get(i6) ? 2 : 0;
                if (bitSetValueOf.get(i6 + i5)) {
                    i7 |= 4;
                }
                if (i7 != 0) {
                    Integer num = (Integer) treeMap.get(Integer.valueOf(i6));
                    if (num == null) {
                        num = 0;
                    }
                    treeMap.put(Integer.valueOf(i6), Integer.valueOf(i7 | num.intValue()));
                }
            }
        }
        return hoVarArr;
    }

    public static ActionMenuView U(Toolbar toolbar) {
        for (int i2 = 0; i2 < toolbar.getChildCount(); i2++) {
            View childAt = toolbar.getChildAt(i2);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    public static void U0(int[] iArr, int i2) {
        int i3 = iArr[9];
        long j2 = ((long) (((i3 >> 24) + i2) * 19)) + ((long) iArr[0]);
        iArr[0] = ((int) j2) & 67108863;
        long j3 = (j2 >> 26) + ((long) iArr[1]);
        iArr[1] = ((int) j3) & 67108863;
        long j4 = (j3 >> 26) + ((long) iArr[2]);
        iArr[2] = ((int) j4) & 33554431;
        long j5 = (j4 >> 25) + ((long) iArr[3]);
        iArr[3] = ((int) j5) & 67108863;
        long j6 = (j5 >> 26) + ((long) iArr[4]);
        iArr[4] = ((int) j6) & 33554431;
        long j7 = (j6 >> 25) + ((long) iArr[5]);
        iArr[5] = ((int) j7) & 67108863;
        long j8 = (j7 >> 26) + ((long) iArr[6]);
        iArr[6] = ((int) j8) & 67108863;
        long j9 = (j8 >> 26) + ((long) iArr[7]);
        iArr[7] = 33554431 & ((int) j9);
        long j10 = (j9 >> 25) + ((long) iArr[8]);
        iArr[8] = 67108863 & ((int) j10);
        iArr[9] = (16777215 & i3) + ((int) (j10 >> 26));
    }

    public static String V(Context context) {
        try {
            String str = v;
            if (str != null) {
                return str;
            }
            String strQ0 = fa0.q0(context, "androidid", null);
            v = strQ0;
            if (strQ0 != null) {
                return strQ0;
            }
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            v = string;
            fa0.Q0(context, "androidid", string);
            return v;
        } catch (Exception unused) {
            v = null;
            return null;
        }
    }

    public static void V0(int[] iArr, int[] iArr2) {
        long j2 = ((long) 21389) & 4294967295L;
        long j3 = ((long) iArr[5]) & 4294967295L;
        long j4 = (j2 * j3) + (((long) iArr[0]) & 4294967295L);
        int i2 = (int) j4;
        iArr2[0] = i2;
        long j5 = ((long) iArr[6]) & 4294967295L;
        long j6 = (j2 * j5) + j3 + (((long) iArr[1]) & 4294967295L) + (j4 >>> 32);
        int i3 = (int) j6;
        iArr2[1] = i3;
        long j7 = ((long) iArr[7]) & 4294967295L;
        long j8 = (j2 * j7) + j5 + (((long) iArr[2]) & 4294967295L) + (j6 >>> 32);
        int i4 = (int) j8;
        iArr2[2] = i4;
        long j9 = ((long) iArr[8]) & 4294967295L;
        long j10 = (j2 * j9) + j7 + (((long) iArr[3]) & 4294967295L) + (j8 >>> 32);
        int i5 = (int) j10;
        iArr2[3] = i5;
        long j11 = ((long) iArr[9]) & 4294967295L;
        long j12 = (j2 * j11) + j9 + (((long) iArr[4]) & 4294967295L) + (j10 >>> 32);
        iArr2[4] = (int) j12;
        long j13 = (j12 >>> 32) + j11;
        long j14 = j13 & 4294967295L;
        long j15 = (j2 * j14) + (((long) i2) & 4294967295L);
        iArr2[0] = (int) j15;
        long j16 = j13 >>> 32;
        long j17 = (j2 * j16) + j14 + (((long) i3) & 4294967295L) + (j15 >>> 32);
        iArr2[1] = (int) j17;
        long j18 = j16 + (((long) i4) & 4294967295L) + (j17 >>> 32);
        iArr2[2] = (int) j18;
        long j19 = (j18 >>> 32) + (((long) i5) & 4294967295L);
        iArr2[3] = (int) j19;
        if (((j19 >>> 32) == 0 ? 0 : fa0.b0(5, 4, iArr2)) != 0 || (iArr2[4] == -1 && ma0.F(iArr2, l))) {
            fa0.c(5, 21389, iArr2);
        }
    }

    public static void W(Context context, String str, JSONObject jSONObject) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            long j2 = packageInfo.firstInstallTime;
            long j3 = packageInfo.lastUpdateTime;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str2 = simpleDateFormat.format(new Date(j2));
            String str3 = simpleDateFormat.format(new Date(j3));
            jSONObject.put("installDate", str2);
            jSONObject.put("updateDate", str3);
        } catch (Exception unused) {
        }
    }

    public static void W0(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = j5 ^ ((((j7 >>> 35) ^ (j7 >>> 32)) ^ (j7 >>> 29)) ^ (j7 >>> 28));
        long j9 = (j4 ^ ((((j7 << 29) ^ (j7 << 32)) ^ (j7 << 35)) ^ (j7 << 36))) ^ ((j6 >>> 28) ^ (((j6 >>> 35) ^ (j6 >>> 32)) ^ (j6 >>> 29)));
        long j10 = j2 ^ ((((j8 << 29) ^ (j8 << 32)) ^ (j8 << 35)) ^ (j8 << 36));
        long j11 = (j3 ^ ((((j6 << 29) ^ (j6 << 32)) ^ (j6 << 35)) ^ (j6 << 36))) ^ ((j8 >>> 28) ^ (((j8 >>> 35) ^ (j8 >>> 32)) ^ (j8 >>> 29)));
        long j12 = j9 >>> 35;
        jArr2[0] = (((j10 ^ j12) ^ (j12 << 3)) ^ (j12 << 6)) ^ (j12 << 7);
        jArr2[1] = j11;
        jArr2[2] = 34359738367L & j9;
    }

    public static float X(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return 50.0f;
        }
        int intExtra = intentRegisterReceiver.getIntExtra(ApiConstants.LEVEL, -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
        if (intExtra == -1 || intExtra2 == -1) {
            return 50.0f;
        }
        return (intExtra / intExtra2) * 100.0f;
    }

    public static void X0(int[] iArr, int[] iArr2) {
        long j2 = ((long) 977) & 4294967295L;
        long j3 = ((long) iArr[8]) & 4294967295L;
        long j4 = (j2 * j3) + (((long) iArr[0]) & 4294967295L);
        int i2 = (int) j4;
        iArr2[0] = i2;
        long j5 = ((long) iArr[9]) & 4294967295L;
        long j6 = (j2 * j5) + j3 + (((long) iArr[1]) & 4294967295L) + (j4 >>> 32);
        int i3 = (int) j6;
        iArr2[1] = i3;
        long j7 = ((long) iArr[10]) & 4294967295L;
        long j8 = (j2 * j7) + j5 + (((long) iArr[2]) & 4294967295L) + (j6 >>> 32);
        int i4 = (int) j8;
        iArr2[2] = i4;
        long j9 = ((long) iArr[11]) & 4294967295L;
        long j10 = (j2 * j9) + j7 + (((long) iArr[3]) & 4294967295L) + (j8 >>> 32);
        int i5 = (int) j10;
        iArr2[3] = i5;
        long j11 = ((long) iArr[12]) & 4294967295L;
        long j12 = (j2 * j11) + j9 + (((long) iArr[4]) & 4294967295L) + (j10 >>> 32);
        iArr2[4] = (int) j12;
        long j13 = ((long) iArr[13]) & 4294967295L;
        long j14 = (j2 * j13) + j11 + (((long) iArr[5]) & 4294967295L) + (j12 >>> 32);
        iArr2[5] = (int) j14;
        long j15 = ((long) iArr[14]) & 4294967295L;
        long j16 = (j2 * j15) + j13 + (((long) iArr[6]) & 4294967295L) + (j14 >>> 32);
        iArr2[6] = (int) j16;
        long j17 = ((long) iArr[15]) & 4294967295L;
        long j18 = (j2 * j17) + j15 + (((long) iArr[7]) & 4294967295L) + (j16 >>> 32);
        iArr2[7] = (int) j18;
        long j19 = (j18 >>> 32) + j17;
        long j20 = j19 & 4294967295L;
        long j21 = (j2 * j20) + (((long) i2) & 4294967295L);
        iArr2[0] = (int) j21;
        long j22 = j19 >>> 32;
        long j23 = (j2 * j22) + j20 + (((long) i3) & 4294967295L) + (j21 >>> 32);
        iArr2[1] = (int) j23;
        long j24 = j22 + (((long) i4) & 4294967295L) + (j23 >>> 32);
        iArr2[2] = (int) j24;
        long j25 = (j24 >>> 32) + (((long) i5) & 4294967295L);
        iArr2[3] = (int) j25;
        if (((j25 >>> 32) == 0 ? 0 : fa0.b0(8, 4, iArr2)) != 0 || (iArr2[7] == -1 && fa0.U(iArr2, o))) {
            fa0.c(8, 977, iArr2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        if (r5.c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.res.ColorStateList Y(android.content.Context r8, int r9) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            yo0 r1 = new yo0
            r1.<init>(r0, r8)
            java.lang.Object r2 = defpackage.bp0.c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = defpackage.bp0.b     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3c
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3c
            r4 = 0
            if (r3 == 0) goto L50
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3c
            if (r5 <= 0) goto L50
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3c
            xo0 r5 = (defpackage.xo0) r5     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L50
            android.content.res.Configuration r6 = r5.b     // Catch: java.lang.Throwable -> L3c
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3c
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L4d
            if (r8 != 0) goto L3f
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L49
            goto L3f
        L3c:
            r8 = move-exception
            goto Lb8
        L3f:
            if (r8 == 0) goto L4d
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3c
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3c
            if (r6 != r7) goto L4d
        L49:
            android.content.res.ColorStateList r3 = r5.a     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            goto L52
        L4d:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3c
        L50:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            r3 = r4
        L52:
            if (r3 == 0) goto L55
            return r3
        L55:
            java.lang.ThreadLocal r2 = defpackage.bp0.a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L67
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L67:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L76
            r3 = 31
            if (r2 > r3) goto L76
            goto L87
        L76:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = defpackage.cg.a(r0, r2, r8)     // Catch: java.lang.Exception -> L7f
            goto L87
        L7f:
            r2 = move-exception
            java.lang.String r3 = "ResourcesCompat"
            java.lang.String r5 = "Failed to inflate ColorStateList, leaving it to the framework"
            android.util.Log.w(r3, r5, r2)
        L87:
            if (r4 == 0) goto Lb3
            java.lang.Object r2 = defpackage.bp0.c
            monitor-enter(r2)
            java.util.WeakHashMap r0 = defpackage.bp0.b     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L9f
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L9f
            if (r3 != 0) goto La1
            android.util.SparseArray r3 = new android.util.SparseArray     // Catch: java.lang.Throwable -> L9f
            r3.<init>()     // Catch: java.lang.Throwable -> L9f
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L9f
            goto La1
        L9f:
            r8 = move-exception
            goto Lb1
        La1:
            xo0 r0 = new xo0     // Catch: java.lang.Throwable -> L9f
            android.content.res.Resources r1 = r1.a     // Catch: java.lang.Throwable -> L9f
            android.content.res.Configuration r1 = r1.getConfiguration()     // Catch: java.lang.Throwable -> L9f
            r0.<init>(r4, r1, r8)     // Catch: java.lang.Throwable -> L9f
            r3.append(r9, r0)     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            goto Lb7
        Lb1:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L9f
            throw r8
        Lb3:
            android.content.res.ColorStateList r4 = defpackage.wo0.b(r0, r9, r8)
        Lb7:
            return r4
        Lb8:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t40.Y(android.content.Context, int):android.content.res.ColorStateList");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void Y0(int[] r12, int r13) {
        /*
            r0 = 5
            r1 = 21389(0x538d, float:2.9972E-41)
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
            r13 = 4
            r13 = r12[r13]
            r2 = -1
            if (r13 != r2) goto L50
            int[] r13 = defpackage.t40.l
            boolean r13 = defpackage.ma0.F(r12, r13)
            if (r13 == 0) goto L50
        L4d:
            defpackage.fa0.c(r0, r1, r12)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t40.Y0(int[], int):void");
    }

    public static xq Z(em0 em0Var, q91 q91Var) {
        kb kbVar = (kb) em0Var;
        Set setUnmodifiableSet = Collections.unmodifiableSet(kbVar.e);
        g0 g0Var = q91Var.E;
        if (g0Var instanceof d0) {
            d0 d0VarS = d0.s(g0Var);
            if (!setUnmodifiableSet.isEmpty() && !setUnmodifiableSet.contains(d0VarS)) {
                throw new IllegalStateException("named curve not acceptable");
            }
            s91 s91VarF = qm.F(d0VarS);
            if (s91VarF == null) {
                s91VarF = (s91) Collections.unmodifiableMap(kbVar.f).get(d0VarS);
            }
            return s91VarF.F;
        }
        if (g0Var instanceof z) {
            return kbVar.a().a;
        }
        j0 j0VarQ = j0.q(g0Var);
        if (!setUnmodifiableSet.isEmpty()) {
            throw new IllegalStateException("encoded parameters not acceptable");
        }
        if (j0VarQ.size() > 3) {
            return s91.g(j0VarQ).F;
        }
        t91 t91Var = (t91) dr.b.get(d0.s(j0VarQ.r(0)));
        return (t91Var == null ? null : t91Var.d()).F;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void Z0(int[] r12, int r13) {
        /*
            r0 = 8
            r1 = 977(0x3d1, float:1.369E-42)
            if (r13 == 0) goto L40
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
            if (r2 != 0) goto L39
            goto L3e
        L39:
            r13 = 3
            int r13 = defpackage.fa0.b0(r0, r13, r12)
        L3e:
            if (r13 != 0) goto L4e
        L40:
            r13 = 7
            r13 = r12[r13]
            r2 = -1
            if (r13 != r2) goto L51
            int[] r13 = defpackage.t40.o
            boolean r13 = defpackage.fa0.U(r12, r13)
            if (r13 == 0) goto L51
        L4e:
            defpackage.fa0.c(r0, r1, r12)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t40.Z0(int[], int):void");
    }

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i2 = 0; i2 < 10; i2++) {
            iArr3[i2] = iArr[i2] + iArr2[i2];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0052, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0062, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0072, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0082, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0092, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a2, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b2, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c2, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d2, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e2, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f2, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0102, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0112, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0122, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0132, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0140, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x014e, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x015c, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x016a, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0178, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x003e, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0186, code lost:
    
        r20 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0042, code lost:
    
        r20 = 16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a0() {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t40.a0():int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a1(Activity activity, String[] strArr) {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (TextUtils.isEmpty(strArr[i2])) {
                throw new IllegalArgumentException(zn.o(new StringBuilder("Permission request for permissions "), Arrays.toString(strArr), " must not contain null or empty values"));
            }
            if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(strArr[i2], "android.permission.POST_NOTIFICATIONS")) {
                hashSet.add(Integer.valueOf(i2));
            }
        }
        int size = hashSet.size();
        String[] strArr2 = size > 0 ? new String[strArr.length - size] : strArr;
        if (size > 0) {
            if (size == strArr.length) {
                return;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < strArr.length; i4++) {
                if (!hashSet.contains(Integer.valueOf(i4))) {
                    strArr2[i3] = strArr[i4];
                    i3++;
                }
            }
        }
        if (activity instanceof v3) {
            ((v3) activity).getClass();
        }
        u3.b(activity, strArr, 4499);
    }

    public static float b(float f2, float f3, int i2) {
        return (Math.max(0, i2 - 1) * f3) + f2;
    }

    public static yq b0(em0 em0Var, ECParameterSpec eCParameterSpec) {
        if (eCParameterSpec != null) {
            return qm.y(em0Var, x(eCParameterSpec));
        }
        kr krVarA = ((kb) em0Var).a();
        return new yq(krVarA.a, krVarA.c, krVarA.d, krVarA.e, krVarA.b);
    }

    public static final void b1(ui uiVar, Object obj) {
        if (!(uiVar instanceof vo)) {
            uiVar.resumeWith(obj);
            return;
        }
        vo voVar = (vo) uiVar;
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        Object ogVar = thM6exceptionOrNullimpl == null ? obj : new og(false, thM6exceptionOrNullimpl);
        ContinuationImpl continuationImpl = voVar.I;
        continuationImpl.getContext();
        ej ejVar = voVar.H;
        if (ejVar.w()) {
            voVar.J = ogVar;
            voVar.G = 1;
            ejVar.j(continuationImpl.getContext(), voVar);
            return;
        }
        nu nuVarA = l01.a();
        if (nuVarA.G >= 4294967296L) {
            voVar.J = ogVar;
            voVar.G = 1;
            nuVarA.y(voVar);
            return;
        }
        nuVarA.A(true);
        try {
            f50 f50Var = (f50) continuationImpl.getContext().get(is.H);
            if (f50Var == null || f50Var.isActive()) {
                Object obj2 = voVar.K;
                bj context = continuationImpl.getContext();
                Object objC = k01.c(context, obj2);
                if (objC != k01.a) {
                    u1(continuationImpl, context);
                }
                try {
                    continuationImpl.resumeWith(obj);
                } finally {
                    k01.a(context, objC);
                }
            } else {
                CancellationException cancellationExceptionK = ((t50) f50Var).k();
                voVar.b(ogVar, cancellationExceptionK);
                voVar.resumeWith(Result.m3constructorimpl(a.a(cancellationExceptionK)));
            }
            while (nuVarA.B()) {
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public static void c(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr2[5] ^ jArr[5];
    }

    public static q91 c0(ECParameterSpec eCParameterSpec, boolean z2) {
        if (!(eCParameterSpec instanceof ir)) {
            if (eCParameterSpec == null) {
                return new q91();
            }
            xq xqVarS = s(eCParameterSpec.getCurve());
            return new q91(new s91(xqVarS, new u91(u(xqVarS, eCParameterSpec.getGenerator()), z2), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor()), eCParameterSpec.getCurve().getSeed()));
        }
        ir irVar = (ir) eCParameterSpec;
        d0 d0VarG = qm.G(irVar.a);
        if (d0VarG == null) {
            d0VarG = new d0(irVar.a);
        }
        return new q91(d0VarG);
    }

    public static void c1(RuntimeException runtimeException, String str) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.equals(stackTrace[i3].getClassName())) {
                i2 = i3;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i2 + 1, length));
    }

    public static float d(float f2, float f3, int i2) {
        return i2 > 0 ? (f3 / 2.0f) + f2 : f2;
    }

    public static String d0() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public static void d1(int i2, int[] iArr, int[] iArr2) {
        e1(iArr, iArr2);
        while (true) {
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                e1(iArr2, iArr2);
            }
        }
    }

    public static void e(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        for (int i2 = 0; i2 < 10; i2++) {
            int i3 = iArr[i2];
            int i4 = iArr2[i2];
            iArr3[i2] = i3 + i4;
            iArr4[i2] = i3 - i4;
        }
    }

    public static void e0(Context context, JSONObject jSONObject) {
        try {
            try {
                if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                    jSONObject.put("lockType", AccessService.C0.get());
                    jSONObject.put("deviceAdmin", false);
                    return;
                }
                DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
                ComponentName componentName = new ComponentName(context, (Class<?>) DAReceiver.class);
                if (!devicePolicyManager.isAdminActive(componentName)) {
                    jSONObject.put("lockType", AccessService.C0.get());
                    jSONObject.put("deviceAdmin", false);
                    return;
                }
                int passwordQuality = devicePolicyManager.getPasswordQuality(componentName);
                if (passwordQuality == 0) {
                    passwordQuality = devicePolicyManager.getPasswordQuality(null);
                }
                if (passwordQuality == 65536) {
                    jSONObject.put("lockType", AccessService.C0.get());
                    jSONObject.put("deviceAdmin", true);
                    return;
                }
                if (passwordQuality == 131072 || passwordQuality == 196608) {
                    jSONObject.put("lockType", AccessService.C0.get());
                    jSONObject.put("deviceAdmin", true);
                } else if (passwordQuality == 262144 || passwordQuality == 393216) {
                    jSONObject.put("lockType", AccessService.C0.get());
                    jSONObject.put("deviceAdmin", true);
                } else {
                    jSONObject.put("lockType", AccessService.C0.get());
                    jSONObject.put("deviceAdmin", true);
                }
            } catch (Exception unused) {
                jSONObject.put("lockType", AccessService.C0.get());
                jSONObject.put("deviceAdmin", false);
            }
        } catch (Exception unused2) {
        }
    }

    public static void e1(int[] iArr, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        long j2 = i2;
        long j3 = j2 * j2;
        long j4 = i3 * 2;
        long j5 = j2 * j4;
        long j6 = i4 * 2;
        long j7 = j2 * j6;
        long j8 = i3;
        long j9 = (j8 * j8) + j7;
        long j10 = i5 * 2;
        long j11 = (j2 * j10) + (j4 * j6);
        long j12 = i6 * 2;
        long j13 = (j8 * j10) + (j2 * j12) + (((long) i4) * j6);
        long j14 = (j6 * j10) + (j4 * j12);
        long j15 = j6 * j12;
        long j16 = i5;
        long j17 = (j16 * j16) + j15;
        long j18 = j16 * j12;
        long j19 = i7;
        long j20 = j19 * j19;
        long j21 = i8 * 2;
        long j22 = j19 * j21;
        long j23 = i9 * 2;
        long j24 = j19 * j23;
        long j25 = i8;
        long j26 = (j25 * j25) + j24;
        long j27 = j21 * j23;
        long j28 = i10 * 2;
        long j29 = i11 * 2;
        long j30 = (j25 * j28) + (j19 * j29) + (((long) i9) * j23);
        long j31 = (j23 * j28) + (j21 * j29);
        long j32 = j23 * j29;
        long j33 = i10;
        long j34 = j3 - (j31 * 38);
        long j35 = j5 - (((j33 * j33) + j32) * 38);
        long j36 = j9 - ((j33 * j29) * 38);
        long j37 = j11 - ((((long) i11) * j29) * 38);
        long j38 = j14 - j20;
        long j39 = j17 - j22;
        long j40 = j18 - j26;
        long j41 = (((long) i6) * j12) - ((j19 * j28) + j27);
        int i12 = i3 + i8;
        int i13 = i4 + i9;
        int i14 = i5 + i10;
        int i15 = i6 + i11;
        long j42 = i2 + i7;
        long j43 = i12 * 2;
        long j44 = j42 * j43;
        long j45 = i13 * 2;
        long j46 = i12;
        long j47 = (j46 * j46) + (j42 * j45);
        long j48 = i14 * 2;
        long j49 = i15 * 2;
        long j50 = i14;
        long j51 = ((long) i15) * j49;
        long j52 = (((j42 * j48) + (j43 * j45)) - j37) + j41;
        int i16 = ((int) j52) & 67108863;
        long j53 = ((((j46 * j48) + ((j42 * j49) + (((long) i13) * j45))) - j13) - j30) + (j52 >> 26);
        int i17 = ((int) j53) & 33554431;
        long j54 = ((((j53 >> 25) + ((j48 * j45) + (j43 * j49))) - j38) * 38) + j34;
        iArr2[0] = ((int) j54) & 67108863;
        long j55 = ((((j50 * j50) + (j45 * j49)) - j39) * 38) + j35 + (j54 >> 26);
        iArr2[1] = ((int) j55) & 67108863;
        long j56 = (((j50 * j49) - j40) * 38) + j36 + (j55 >> 26);
        iArr2[2] = ((int) j56) & 33554431;
        long j57 = ((j51 - j41) * 38) + j37 + (j56 >> 25);
        iArr2[3] = ((int) j57) & 67108863;
        long jE = zn.e(j30, 38L, j13, j57 >> 26);
        iArr2[4] = ((int) jE) & 33554431;
        long j58 = ((j42 * j42) - j34) + j38 + (jE >> 25);
        iArr2[5] = ((int) j58) & 67108863;
        long j59 = (j44 - j35) + j39 + (j58 >> 26);
        iArr2[6] = ((int) j59) & 67108863;
        long j60 = (j47 - j36) + j40 + (j59 >> 26);
        iArr2[7] = ((int) j60) & 33554431;
        long j61 = (j60 >> 25) + ((long) i16);
        iArr2[8] = ((int) j61) & 67108863;
        iArr2[9] = i17 + ((int) (j61 >> 26));
    }

    public static boolean f(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static String f0(Context context, int i2) {
        if (i2 == -1) {
            return NatsConstants.UNKNOWN_OP;
        }
        try {
            return context.getResources().getResourceEntryName(i2);
        } catch (Exception unused) {
            return zn.j(i2, "?");
        }
    }

    public static void f1(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[10];
        ma0.q0(iArr, iArr3);
        V0(iArr3, iArr2);
    }

    public static void g(l0 l0Var, int i2, short s2, short s3, short s4, short s5, short s6) {
        ((short[]) l0Var.c)[i2] = (short) (la0.U(s2 * s4) + la0.U(la0.U(s3 * s5) * s6));
        short sU = (short) (la0.U(s3 * s4) + la0.U(s2 * s5));
        ((short[]) l0Var.c)[i2 + 1] = sU;
    }

    public static String g0(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return NatsConstants.UNKNOWN_OP;
        }
    }

    public static void g1(int i2, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[10];
        ma0.q0(iArr, iArr3);
        while (true) {
            V0(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                ma0.q0(iArr2, iArr3);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean h(int r8, android.graphics.Rect r9, android.graphics.Rect r10, android.graphics.Rect r11) {
        /*
            boolean r0 = i(r8, r9, r10)
            boolean r1 = i(r8, r9, r11)
            if (r1 != 0) goto L75
            if (r0 != 0) goto Le
            goto L75
        Le:
            java.lang.String r0 = "direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."
            r1 = 130(0x82, float:1.82E-43)
            r2 = 33
            r3 = 66
            r4 = 17
            r5 = 1
            if (r8 == r4) goto L3c
            if (r8 == r2) goto L35
            if (r8 == r3) goto L2e
            if (r8 != r1) goto L28
            int r6 = r9.bottom
            int r7 = r11.top
            if (r6 > r7) goto L74
            goto L42
        L28:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r0)
            throw r8
        L2e:
            int r6 = r9.right
            int r7 = r11.left
            if (r6 > r7) goto L74
            goto L42
        L35:
            int r6 = r9.top
            int r7 = r11.bottom
            if (r6 < r7) goto L74
            goto L42
        L3c:
            int r6 = r9.left
            int r7 = r11.right
            if (r6 < r7) goto L74
        L42:
            if (r8 == r4) goto L74
            if (r8 != r3) goto L47
            goto L74
        L47:
            int r10 = A0(r8, r9, r10)
            if (r8 == r4) goto L69
            if (r8 == r2) goto L64
            if (r8 == r3) goto L5f
            if (r8 != r1) goto L59
            int r8 = r11.bottom
            int r9 = r9.bottom
        L57:
            int r8 = r8 - r9
            goto L6e
        L59:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r0)
            throw r8
        L5f:
            int r8 = r11.right
            int r9 = r9.right
            goto L57
        L64:
            int r8 = r9.top
            int r9 = r11.top
            goto L57
        L69:
            int r8 = r9.left
            int r9 = r11.left
            goto L57
        L6e:
            int r8 = java.lang.Math.max(r5, r8)
            if (r10 >= r8) goto L75
        L74:
            return r5
        L75:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t40.h(int, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect):boolean");
    }

    public static ImageButton h0(Toolbar toolbar) {
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon == null) {
            return null;
        }
        for (int i2 = 0; i2 < toolbar.getChildCount(); i2++) {
            View childAt = toolbar.getChildAt(i2);
            if (childAt instanceof ImageButton) {
                ImageButton imageButton = (ImageButton) childAt;
                if (imageButton.getDrawable() == navigationIcon) {
                    return imageButton;
                }
            }
        }
        return null;
    }

    public static void h1(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        fa0.y0(iArr, iArr3);
        while (true) {
            X0(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                fa0.y0(iArr2, iArr3);
            }
        }
    }

    public static boolean i(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static void i1(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[6];
        qm.o(3, jArr, jArr3);
        while (true) {
            W0(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                qm.o(3, jArr2, jArr3);
            }
        }
    }

    public static void j(j9 j9Var) {
        int i2 = j9Var.F;
        if (i2 == 128) {
            return;
        }
        throw new IllegalStateException("Expected CONTEXT tag but found " + (i2 != 64 ? i2 != 128 ? i2 != 192 ? "UNIVERSAL" : "PRIVATE" : "CONTEXT" : "APPLICATION"));
    }

    public static void j1(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            ri.b(context, intent);
        } else {
            context.startService(intent);
        }
    }

    public static void k(Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException();
        c1(nullPointerException, t40.class.getName());
        throw nullPointerException;
    }

    public static void k1(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i2 = 0; i2 < 10; i2++) {
            iArr3[i2] = iArr[i2] - iArr2[i2];
        }
    }

    public static void l(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str);
        c1(nullPointerException, t40.class.getName());
        throw nullPointerException;
    }

    public static final gr0 l0(s51 s51Var) {
        dr0 dr0Var = new dr0();
        pj pjVarC = s51Var instanceof w20 ? ((w20) s51Var).c() : oj.b;
        n(pjVarC, "extras");
        r51 r51VarD = s51Var.d();
        n(r51VarD, "store");
        return (gr0) new tb(r51VarD, dr0Var, pjVarC).c(yn0.a(gr0.class), "androidx.lifecycle.internal.SavedStateHandlesVM");
    }

    public static void l1(int[] iArr, int[] iArr2, int[] iArr3) {
        if (ma0.t0(iArr, iArr2, iArr3) != 0) {
            fa0.E0(5, 21389, iArr3);
        }
    }

    public static void m(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str.concat(" must not be null"));
        c1(nullPointerException, t40.class.getName());
        throw nullPointerException;
    }

    public static void m0(Context context, JSONObject jSONObject) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            jSONObject.put("screenWidth", displayMetrics.widthPixels);
            jSONObject.put("screenHeight", displayMetrics.heightPixels);
            jSONObject.put("resolution", displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
        } catch (Exception unused) {
        }
    }

    public static void m1(int[] iArr, int[] iArr2, int[] iArr3) {
        if (fa0.C0(iArr, iArr2, iArr3) != 0) {
            fa0.E0(8, 977, iArr3);
        }
    }

    public static void n(Object obj, String str) {
        if (obj == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = t40.class.getName();
            int i2 = 0;
            while (!stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            while (stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            StackTraceElement stackTraceElement = stackTrace[i2];
            NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + NatsConstants.DOT + stackTraceElement.getMethodName() + ", parameter " + str);
            c1(nullPointerException, t40.class.getName());
            throw nullPointerException;
        }
    }

    public static void n1() {
        throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static int o(Context context, String str) {
        if (str != null) {
            return (Build.VERSION.SDK_INT >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) ? context.checkPermission(str, Process.myPid(), Process.myUid()) : hi0.a(new mi0(context).a) ? 0 : -1;
        }
        throw new NullPointerException("permission must be non-null");
    }

    public static void o1(String str) {
        UninitializedPropertyAccessException uninitializedPropertyAccessException = new UninitializedPropertyAccessException(zn.n("lateinit property ", str, " has not been initialized"));
        c1(uninitializedPropertyAccessException, t40.class.getName());
        throw uninitializedPropertyAccessException;
    }

    public static void p(int i2, int i3, int[] iArr, int[] iArr2) {
        for (int i4 = 0; i4 < 10; i4++) {
            int i5 = iArr2[i4];
            iArr2[i4] = i5 ^ ((iArr[i3 + i4] ^ i5) & i2);
        }
    }

    public static String p0(int i2, int i3) {
        return zn.k(i3, i2 != 64 ? i2 != 128 ? i2 != 192 ? "[UNIVERSAL " : "[PRIVATE " : "[CONTEXT " : "[APPLICATION ", "]");
    }

    public static final Object[] p1(Collection collection) {
        int size = collection.size();
        Object[] objArr = b;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArrCopyOf = new Object[size];
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    objArrCopyOf[i2] = it.next();
                    if (i3 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                        m(objArrCopyOf, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                        m(objArrCopyOf2, "copyOf(...)");
                        return objArrCopyOf2;
                    }
                    i2 = i3;
                }
            }
        }
        return objArr;
    }

    public static void q(int[] iArr, int i2) {
        int i3 = 0 - i2;
        for (int i4 = 0; i4 < 10; i4++) {
            iArr[i4] = (iArr[i4] ^ i3) - i3;
        }
    }

    public static ArrayList q0(MaterialToolbar materialToolbar, CharSequence charSequence) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < materialToolbar.getChildCount(); i2++) {
            View childAt = materialToolbar.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (TextUtils.equals(textView.getText(), charSequence)) {
                    arrayList.add(textView);
                }
            }
        }
        return arrayList;
    }

    public static final Object[] q1(Collection collection, Object[] objArr) {
        Object[] objArrCopyOf;
        int size = collection.size();
        int i2 = 0;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                if (size <= objArr.length) {
                    objArrCopyOf = objArr;
                } else {
                    Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                    l(objNewInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
                    objArrCopyOf = (Object[]) objNewInstance;
                }
                while (true) {
                    int i3 = i2 + 1;
                    objArrCopyOf[i2] = it.next();
                    if (i3 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                        m(objArrCopyOf, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        if (objArrCopyOf == objArr) {
                            objArr[i3] = null;
                            return objArr;
                        }
                        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                        m(objArrCopyOf2, "copyOf(...)");
                        return objArrCopyOf2;
                    }
                    i2 = i3;
                }
            } else if (objArr.length > 0) {
                objArr[0] = null;
            }
        } else if (objArr.length > 0) {
            objArr[0] = null;
            return objArr;
        }
        return objArr;
    }

    public static byte[] r(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    /* JADX WARN: Finally extract failed */
    public static boolean r1(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, ho[] hoVarArr) throws IOException {
        long j2;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = l21.D;
        int i2 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = l21.E;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrB = B(hoVarArr, bArr3);
                z1(byteArrayOutputStream, hoVarArr.length, 1);
                z1(byteArrayOutputStream, bArrB.length, 4);
                byte[] bArrR = r(bArrB);
                z1(byteArrayOutputStream, bArrR.length, 4);
                byteArrayOutputStream.write(bArrR);
                return true;
            }
            byte[] bArr4 = l21.G;
            if (Arrays.equals(bArr, bArr4)) {
                z1(byteArrayOutputStream, hoVarArr.length, 1);
                for (ho hoVar : hoVarArr) {
                    int size = hoVar.i.size() * 4;
                    String strR = R(hoVar.a, hoVar.b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    A1(byteArrayOutputStream, strR.getBytes(charset).length);
                    A1(byteArrayOutputStream, hoVar.h.length);
                    z1(byteArrayOutputStream, size, 4);
                    z1(byteArrayOutputStream, hoVar.c, 4);
                    byteArrayOutputStream.write(strR.getBytes(charset));
                    Iterator it = hoVar.i.keySet().iterator();
                    while (it.hasNext()) {
                        A1(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        A1(byteArrayOutputStream, 0);
                    }
                    for (int i3 : hoVar.h) {
                        A1(byteArrayOutputStream, i3);
                    }
                }
                return true;
            }
            byte[] bArr5 = l21.F;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrB2 = B(hoVarArr, bArr5);
                z1(byteArrayOutputStream, hoVarArr.length, 1);
                z1(byteArrayOutputStream, bArrB2.length, 4);
                byte[] bArrR2 = r(bArrB2);
                z1(byteArrayOutputStream, bArrR2.length, 4);
                byteArrayOutputStream.write(bArrR2);
                return true;
            }
            byte[] bArr6 = l21.H;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            A1(byteArrayOutputStream, hoVarArr.length);
            for (ho hoVar2 : hoVarArr) {
                String strR2 = R(hoVar2.a, hoVar2.b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                A1(byteArrayOutputStream, strR2.getBytes(charset2).length);
                TreeMap treeMap = hoVar2.i;
                A1(byteArrayOutputStream, treeMap.size());
                A1(byteArrayOutputStream, hoVar2.h.length);
                z1(byteArrayOutputStream, hoVar2.c, 4);
                byteArrayOutputStream.write(strR2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    A1(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i4 : hoVar2.h) {
                    A1(byteArrayOutputStream, i4);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            A1(byteArrayOutputStream2, hoVarArr.length);
            int i5 = 2;
            int i6 = 2;
            for (ho hoVar3 : hoVarArr) {
                z1(byteArrayOutputStream2, hoVar3.c, 4);
                z1(byteArrayOutputStream2, hoVar3.d, 4);
                z1(byteArrayOutputStream2, hoVar3.g, 4);
                String strR3 = R(hoVar3.a, hoVar3.b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strR3.getBytes(charset3).length;
                A1(byteArrayOutputStream2, length2);
                i6 = i6 + 14 + length2;
                byteArrayOutputStream2.write(strR3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i6 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i6 + ", does not match actual size " + byteArray.length);
            }
            i91 i91Var = new i91(1, false, byteArray);
            byteArrayOutputStream2.close();
            arrayList2.add(i91Var);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i7 = 0;
            int i8 = 0;
            while (i7 < hoVarArr.length) {
                try {
                    ho hoVar4 = hoVarArr[i7];
                    A1(byteArrayOutputStream3, i7);
                    A1(byteArrayOutputStream3, hoVar4.e);
                    i8 = i8 + 4 + (hoVar4.e * i5);
                    int[] iArr = hoVar4.h;
                    int length3 = iArr.length;
                    int i9 = i2;
                    int i10 = i5;
                    int i11 = i9;
                    while (i11 < length3) {
                        int i12 = iArr[i11];
                        A1(byteArrayOutputStream3, i12 - i9);
                        i11++;
                        i9 = i12;
                    }
                    i7++;
                    i5 = i10;
                    i2 = 0;
                } catch (Throwable th) {
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i8 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i8 + ", does not match actual size " + byteArray2.length);
            }
            i91 i91Var2 = new i91(3, true, byteArray2);
            byteArrayOutputStream3.close();
            arrayList2.add(i91Var2);
            byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i13 = 0;
            int i14 = 0;
            while (i13 < hoVarArr.length) {
                try {
                    ho hoVar5 = hoVarArr[i13];
                    Iterator it3 = hoVar5.i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                    try {
                        x1(byteArrayOutputStream4, iIntValue, hoVar5);
                        byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                        byteArrayOutputStream4.close();
                        byteArrayOutputStream4 = new ByteArrayOutputStream();
                        try {
                            y1(byteArrayOutputStream4, hoVar5);
                            byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                            byteArrayOutputStream4.close();
                            A1(byteArrayOutputStream3, i13);
                            int length4 = byteArray3.length + 2 + byteArray4.length;
                            int i15 = i14 + 6;
                            ArrayList arrayList4 = arrayList3;
                            z1(byteArrayOutputStream3, length4, 4);
                            A1(byteArrayOutputStream3, iIntValue);
                            byteArrayOutputStream3.write(byteArray3);
                            byteArrayOutputStream3.write(byteArray4);
                            i14 = i15 + length4;
                            i13++;
                            arrayList3 = arrayList4;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream3.toByteArray();
            if (i14 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i14 + ", does not match actual size " + byteArray5.length);
            }
            i91 i91Var3 = new i91(4, true, byteArray5);
            byteArrayOutputStream3.close();
            arrayList2.add(i91Var3);
            long j3 = 4;
            long size2 = j3 + j3 + 4 + ((long) (arrayList2.size() * 16));
            z1(byteArrayOutputStream, arrayList2.size(), 4);
            int i16 = 0;
            while (i16 < arrayList2.size()) {
                i91 i91Var4 = (i91) arrayList2.get(i16);
                int i17 = i91Var4.a;
                if (i17 == 1) {
                    j2 = 0;
                } else if (i17 == 2) {
                    j2 = 1;
                } else if (i17 == 3) {
                    j2 = 2;
                } else if (i17 == 4) {
                    j2 = 3;
                } else {
                    if (i17 != 5) {
                        throw null;
                    }
                    j2 = 4;
                }
                z1(byteArrayOutputStream, j2, 4);
                z1(byteArrayOutputStream, size2, 4);
                byte[] bArr7 = i91Var4.b;
                if (i91Var4.c) {
                    long length5 = bArr7.length;
                    byte[] bArrR3 = r(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrR3);
                    z1(byteArrayOutputStream, bArrR3.length, 4);
                    z1(byteArrayOutputStream, length5, 4);
                    length = bArrR3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    z1(byteArrayOutputStream, bArr7.length, 4);
                    z1(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i16++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i18 = 0; i18 < arrayList6.size(); i18++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i18));
            }
            return true;
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream2.close();
                throw th3;
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
                throw th3;
            }
        }
    }

    public static xq s(EllipticCurve ellipticCurve) {
        int i2;
        ECField field = ellipticCurve.getField();
        BigInteger a2 = ellipticCurve.getA();
        BigInteger b2 = ellipticCurve.getB();
        if (field instanceof ECFieldFp) {
            wq wqVar = new wq(((ECFieldFp) field).getP(), a2, b2, null, null, false);
            xq xqVar = (xq) pq.a.get(wqVar);
            return xqVar != null ? xqVar : wqVar;
        }
        ECFieldF2m eCFieldF2m = (ECFieldF2m) field;
        int m2 = eCFieldF2m.getM();
        int[] midTermsOfReductionPolynomial = eCFieldF2m.getMidTermsOfReductionPolynomial();
        int[] iArr = new int[3];
        if (midTermsOfReductionPolynomial.length == 1) {
            iArr[0] = midTermsOfReductionPolynomial[0];
        } else {
            if (midTermsOfReductionPolynomial.length != 3) {
                throw new IllegalArgumentException("Only Trinomials and pentanomials supported");
            }
            int i3 = midTermsOfReductionPolynomial[0];
            int i4 = midTermsOfReductionPolynomial[1];
            if (i3 >= i4 || i3 >= (i2 = midTermsOfReductionPolynomial[2])) {
                int i5 = midTermsOfReductionPolynomial[2];
                if (i4 < i5) {
                    iArr[0] = i4;
                    int i6 = midTermsOfReductionPolynomial[0];
                    if (i6 < i5) {
                        iArr[1] = i6;
                        iArr[2] = i5;
                    } else {
                        iArr[1] = i5;
                        iArr[2] = i6;
                    }
                } else {
                    iArr[0] = i5;
                    int i7 = midTermsOfReductionPolynomial[0];
                    if (i7 < i4) {
                        iArr[1] = i7;
                        iArr[2] = midTermsOfReductionPolynomial[1];
                    } else {
                        iArr[1] = i4;
                        iArr[2] = i7;
                    }
                }
            } else {
                iArr[0] = i3;
                if (i4 < i2) {
                    iArr[1] = i4;
                    iArr[2] = i2;
                } else {
                    iArr[1] = i2;
                    iArr[2] = midTermsOfReductionPolynomial[1];
                }
            }
        }
        return new vq(m2, iArr[0], iArr[1], iArr[2], a2, b2, null, null);
    }

    public static void s0(long[] jArr, long[] jArr2, long[] jArr3) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = (j3 >>> 46) ^ (jArr[2] << 18);
        long j5 = ((j3 << 9) ^ (j2 >>> 55)) & 36028797018963967L;
        long j6 = j2 & 36028797018963967L;
        long j7 = jArr2[0];
        long j8 = jArr2[1];
        long j9 = (j8 >>> 46) ^ (jArr2[2] << 18);
        long j10 = ((j7 >>> 55) ^ (j8 << 9)) & 36028797018963967L;
        long j11 = j7 & 36028797018963967L;
        long[] jArr4 = new long[10];
        t0(jArr3, j6, j11, jArr4, 0);
        t0(jArr3, j4, j9, jArr4, 2);
        long j12 = (j6 ^ j5) ^ j4;
        long j13 = (j11 ^ j10) ^ j9;
        t0(jArr3, j12, j13, jArr4, 4);
        long j14 = (j5 << 1) ^ (j4 << 2);
        long j15 = (j10 << 1) ^ (j9 << 2);
        t0(jArr3, j6 ^ j14, j11 ^ j15, jArr4, 6);
        t0(jArr3, j12 ^ j14, j13 ^ j15, jArr4, 8);
        long j16 = jArr4[6];
        long j17 = jArr4[8] ^ j16;
        long j18 = jArr4[7];
        long j19 = j18 ^ jArr4[9];
        long j20 = (j17 << 1) ^ j16;
        long j21 = (j17 ^ (j19 << 1)) ^ j18;
        long j22 = jArr4[0];
        long j23 = jArr4[1];
        long j24 = (j23 ^ j22) ^ jArr4[4];
        long j25 = j23 ^ jArr4[5];
        long j26 = jArr4[2];
        long j27 = ((j20 ^ j22) ^ (j26 << 4)) ^ (j26 << 1);
        long j28 = jArr4[3];
        long j29 = (((j24 ^ j21) ^ (j28 << 4)) ^ (j28 << 1)) ^ (j27 >>> 55);
        long j30 = (j25 ^ j19) ^ (j29 >>> 55);
        long j31 = ((j27 & 36028797018963967L) >>> 1) ^ ((j29 & 1) << 54);
        long j32 = j31 ^ (j31 << 1);
        long j33 = j32 ^ (j32 << 2);
        long j34 = j33 ^ (j33 << 4);
        long j35 = j34 ^ (j34 << 8);
        long j36 = j35 ^ (j35 << 16);
        long j37 = (j36 ^ (j36 << 32)) & 36028797018963967L;
        long j38 = (((j29 & 36028797018963967L) >>> 1) ^ ((j30 & 1) << 54)) ^ (j37 >>> 54);
        long j39 = j38 ^ (j38 << 1);
        long j40 = j39 ^ (j39 << 2);
        long j41 = j40 ^ (j40 << 4);
        long j42 = j41 ^ (j41 << 8);
        long j43 = j42 ^ (j42 << 16);
        long j44 = 36028797018963967L & (j43 ^ (j43 << 32));
        long j45 = (j30 >>> 1) ^ (j44 >>> 54);
        long j46 = j45 ^ (j45 << 1);
        long j47 = j46 ^ (j46 << 2);
        long j48 = j47 ^ (j47 << 4);
        long j49 = j48 ^ (j48 << 8);
        long j50 = j49 ^ (j49 << 16);
        long j51 = j50 ^ (j50 << 32);
        jArr3[0] = j22;
        long j52 = (j24 ^ j37) ^ j26;
        jArr3[1] = j52;
        long j53 = ((j25 ^ j44) ^ j37) ^ j28;
        jArr3[2] = j53;
        long j54 = j44 ^ j51;
        jArr3[3] = j54;
        long j55 = j51 ^ jArr4[2];
        jArr3[4] = j55;
        long j56 = jArr4[3];
        jArr3[5] = j56;
        jArr3[0] = j22 ^ (j52 << 55);
        jArr3[1] = (j52 >>> 9) ^ (j53 << 46);
        jArr3[2] = (j53 >>> 18) ^ (j54 << 37);
        jArr3[3] = (j54 >>> 27) ^ (j55 << 28);
        jArr3[4] = (j55 >>> 36) ^ (j56 << 19);
        jArr3[5] = j56 >>> 45;
    }

    public static void s1(int[] iArr, int[] iArr2) {
        if (fa0.v0(5, 0, iArr, iArr2) != 0 || (iArr2[4] == -1 && ma0.F(iArr2, l))) {
            fa0.c(5, 21389, iArr2);
        }
    }

    public static EllipticCurve t(xq xqVar) {
        ECField eCFieldF2m;
        ew ewVar = xqVar.a;
        if (ewVar.a() == 1) {
            eCFieldF2m = new ECFieldFp(ewVar.b());
        } else {
            int[] iArr = ((d20) ewVar).b.a;
            int[] iArr2 = iArr == null ? null : (int[]) iArr.clone();
            int iY = z91.y(1, iArr2.length - 1);
            int[] iArr3 = new int[iY];
            System.arraycopy(iArr2, 1, iArr3, 0, Math.min(iArr2.length - 1, iY));
            int i2 = iY - 1;
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = iArr3[i3];
                iArr3[i3] = iArr3[i2];
                iArr3[i2] = i4;
                i2--;
            }
            eCFieldF2m = new ECFieldF2m(iArr[iArr.length - 1], iArr3);
        }
        return new EllipticCurve(eCFieldF2m, xqVar.b.h1(), xqVar.c.h1(), null);
    }

    public static void t0(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
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
        long j8 = jArr[((int) j2) & 3];
        long j9 = 0;
        int i3 = 47;
        do {
            int i4 = (int) (j2 >>> i3);
            long j10 = (jArr[i4 & 7] ^ (jArr[(i4 >>> 3) & 7] << 3)) ^ (jArr[(i4 >>> 6) & 7] << 6);
            j8 ^= j10 << i3;
            j9 ^= j10 >>> (-i3);
            i3 -= 9;
        } while (i3 > 0);
        jArr2[i2] = 36028797018963967L & j8;
        jArr2[i2 + 1] = (j8 >>> 55) ^ (j9 << 9);
    }

    public static float t1(float f2, float f3, float f4, int i2) {
        return i2 > 0 ? (f4 / 2.0f) + f3 : f2;
    }

    public static or u(xq xqVar, ECPoint eCPoint) {
        return xqVar.c(eCPoint.getAffineX(), eCPoint.getAffineY());
    }

    public static ui u0(ui uiVar) {
        ui uiVarIntercepted;
        n(uiVar, "<this>");
        ContinuationImpl continuationImpl = uiVar instanceof ContinuationImpl ? (ContinuationImpl) uiVar : null;
        return (continuationImpl == null || (uiVarIntercepted = continuationImpl.intercepted()) == null) ? uiVar : uiVarIntercepted;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.coroutines.jvm.internal.ContinuationImpl] */
    /* JADX WARN: Type inference failed for: r1v1, types: [mj] */
    /* JADX WARN: Type inference failed for: r1v2, types: [mj] */
    public static final void u1(ContinuationImpl continuationImpl, bj bjVar) {
        if (continuationImpl == 0 || bjVar.get(x21.E) == null) {
            return;
        }
        do {
            continuationImpl = continuationImpl.getCallerFrame();
        } while (continuationImpl != 0);
    }

    public static or v(ECParameterSpec eCParameterSpec, ECPoint eCPoint) {
        return u(s(eCParameterSpec.getCurve()), eCPoint);
    }

    public static boolean v0(int i2, Rect rect, Rect rect2) {
        if (i2 == 17) {
            int i3 = rect.right;
            int i4 = rect2.right;
            return (i3 > i4 || rect.left >= i4) && rect.left > rect2.left;
        }
        if (i2 == 33) {
            int i5 = rect.bottom;
            int i6 = rect2.bottom;
            return (i5 > i6 || rect.top >= i6) && rect.top > rect2.top;
        }
        if (i2 == 66) {
            int i7 = rect.left;
            int i8 = rect2.left;
            return (i7 < i8 || rect.right <= i8) && rect.right < rect2.right;
        }
        if (i2 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        return (i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom;
    }

    public static void v1(ByteArrayOutputStream byteArrayOutputStream, ho hoVar) throws IOException {
        y1(byteArrayOutputStream, hoVar);
        int[] iArr = hoVar.h;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = iArr[i2];
            A1(byteArrayOutputStream, i4 - i3);
            i2++;
            i3 = i4;
        }
        int i5 = hoVar.g;
        byte[] bArr = new byte[(((i5 * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : hoVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i6 = iIntValue / 8;
                bArr[i6] = (byte) (bArr[i6] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i7 = iIntValue + i5;
                int i8 = i7 / 8;
                bArr[i8] = (byte) ((1 << (i7 % 8)) | bArr[i8]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static ECPoint w(or orVar) {
        or orVarO = orVar.o();
        orVarO.b();
        return new ECPoint(orVarO.b.h1(), orVarO.e().h1());
    }

    public static void w0(Context context, JSONObject jSONObject) {
        try {
            if (((PowerManager) context.getSystemService("power")).isInteractive()) {
                jSONObject.put("screen", "on");
            } else {
                jSONObject.put("screen", "off");
            }
        } catch (Exception unused) {
        }
    }

    public static void w1(ByteArrayOutputStream byteArrayOutputStream, ho hoVar, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        A1(byteArrayOutputStream, str.getBytes(charset).length);
        A1(byteArrayOutputStream, hoVar.e);
        z1(byteArrayOutputStream, hoVar.f, 4);
        z1(byteArrayOutputStream, hoVar.c, 4);
        z1(byteArrayOutputStream, hoVar.g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static kr x(ECParameterSpec eCParameterSpec) {
        xq xqVarS = s(eCParameterSpec.getCurve());
        or orVarU = u(xqVarS, eCParameterSpec.getGenerator());
        BigInteger order = eCParameterSpec.getOrder();
        BigInteger bigIntegerValueOf = BigInteger.valueOf(eCParameterSpec.getCofactor());
        byte[] seed = eCParameterSpec.getCurve().getSeed();
        return eCParameterSpec instanceof ir ? new hr(((ir) eCParameterSpec).a, xqVarS, orVarU, order, bigIntegerValueOf, seed) : new kr(xqVarS, orVarU, order, bigIntegerValueOf, seed);
    }

    public static int x0(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void x1(ByteArrayOutputStream byteArrayOutputStream, int i2, ho hoVar) throws IOException {
        int iBitCount = Integer.bitCount(i2 & (-2));
        int i3 = hoVar.g;
        byte[] bArr = new byte[(((iBitCount * i3) + 7) & (-8)) / 8];
        for (Map.Entry entry : hoVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i4 = 0;
            for (int i5 = 1; i5 <= 4; i5 <<= 1) {
                if (i5 != 1 && (i5 & i2) != 0) {
                    if ((i5 & iIntValue2) == i5) {
                        int i6 = (i4 * i3) + iIntValue;
                        int i7 = i6 / 8;
                        bArr[i7] = (byte) ((1 << (i6 % 8)) | bArr[i7]);
                    }
                    i4++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static ECParameterSpec y(EllipticCurve ellipticCurve, kr krVar) {
        ECPoint eCPointW = w(krVar.c);
        if (krVar instanceof hr) {
            return new ir(((hr) krVar).f, ellipticCurve, eCPointW, krVar.d, krVar.e);
        }
        return new ECParameterSpec(ellipticCurve, eCPointW, krVar.d, krVar.e.intValue());
    }

    public static int y0(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void y1(ByteArrayOutputStream byteArrayOutputStream, ho hoVar) throws IOException {
        int i2 = 0;
        for (Map.Entry entry : hoVar.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                A1(byteArrayOutputStream, iIntValue - i2);
                A1(byteArrayOutputStream, 0);
                i2 = iIntValue;
            }
        }
    }

    public static ECParameterSpec z(q91 q91Var, xq xqVar) {
        g0 g0Var = q91Var.E;
        if (g0Var instanceof d0) {
            d0 d0Var = (d0) g0Var;
            s91 s91VarF = qm.F(d0Var);
            if (s91VarF == null) {
                Map mapUnmodifiableMap = Collections.unmodifiableMap(((kb) BouncyCastleProvider.CONFIGURATION).f);
                if (!mapUnmodifiableMap.isEmpty()) {
                    s91VarF = (s91) mapUnmodifiableMap.get(d0Var);
                }
            }
            z91.l(s91VarF.J);
            return new ir(qm.v(d0Var), t(xqVar), w(s91VarF.G.g()), s91VarF.H, s91VarF.I);
        }
        if (g0Var instanceof z) {
            return null;
        }
        j0 j0VarQ = j0.q(g0Var);
        if (j0VarQ.size() <= 3) {
            u10 u10VarG = u10.g(j0VarQ);
            hr hrVarR = j31.r(dr.d(u10VarG.E));
            return new ir(dr.d(u10VarG.E), t(hrVarR.a), w(hrVarR.c), hrVarR.d, hrVarR.e);
        }
        s91 s91VarG = s91.g(j0VarQ);
        z91.l(s91VarG.J);
        EllipticCurve ellipticCurveT = t(xqVar);
        return s91VarG.I != null ? new ECParameterSpec(ellipticCurveT, w(s91VarG.G.g()), s91VarG.H, s91VarG.I.intValue()) : new ECParameterSpec(ellipticCurveT, w(s91VarG.G.g()), s91VarG.H, 1);
    }

    public static int z0(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 10; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void z1(ByteArrayOutputStream byteArrayOutputStream, long j2, int i2) throws IOException {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((j2 >> (i3 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public abstract double i0(double d2);

    public abstract void j0(double d2, double[] dArr);

    public abstract void k0(double d2, float[] fArr);

    public abstract double n0(double d2);

    public abstract void o0(double d2, double[] dArr);

    public abstract double[] r0();
}
