package defpackage;

import android.R;
import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.ImageView;
import androidx.core.R$styleable;
import androidx.work.impl.WorkManagerImpl;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Status;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.FunctionImpl;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public abstract class l21 implements q51, qq {
    public final /* synthetic */ int f;
    public static final int[] g = {R.attr.name, R.attr.tint, R.attr.height, R.attr.width, R.attr.alpha, R.attr.autoMirrored, R.attr.tintMode, R.attr.viewportWidth, R.attr.viewportHeight};
    public static final int[] h = {R.attr.name, R.attr.pivotX, R.attr.pivotY, R.attr.scaleX, R.attr.scaleY, R.attr.rotation, R.attr.translateX, R.attr.translateY};
    public static final int[] i = {R.attr.name, R.attr.fillColor, R.attr.pathData, R.attr.strokeColor, R.attr.strokeWidth, R.attr.trimPathStart, R.attr.trimPathEnd, R.attr.trimPathOffset, R.attr.strokeLineCap, R.attr.strokeLineJoin, R.attr.strokeMiterLimit, R.attr.strokeAlpha, R.attr.fillAlpha, R.attr.fillType};
    public static final int[] j = {R.attr.name, R.attr.pathData, R.attr.fillType};
    public static final int[] k = {R.attr.drawable};
    public static final int[] l = {R.attr.name, R.attr.animation};
    public static final wy0 m = new wy0("CLOSED", 0);
    public static final int[] n = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    public static final int[] o = {1886001095, 1339575613, 1980447930, 258412557, -95215574, -959694548, 2013120334, 2047061138};
    public static final int[] p = {-1886001114, -1339575614, -1980447931, -258412558, 95215573, 959694547, -2013120335, 100422509};
    public static final int[] q = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    public static final int[] r = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    public static final int[] s = {12052516, 1174424, 4087752, 38672185, 20040971, 21899680, 55468344, 20105554, 66708015, 9981791};
    public static final int[] t = {66430571, 45040722, 4842939, 15895846, 18981244, 46308410, 4697481, 8903007, 53646190, 12474675};
    public static final int[] u = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    public static final int[] v = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    public static final int[] w = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    public static final Object x = new Object();
    public static yr[] y = null;
    public static yr[] z = null;
    public static int[] A = null;
    public static final cz B = new cz(0);
    public static final int[] C = {0, 25847, -2608894, -518909, 237124, -777960, -876248, 466468, 1826347, 2353451, -359251, -2091905, 3119733, -2884855, 3111497, 2680103, 2725464, 1024112, -1079900, 3585928, -549488, -1119584, 2619752, -2108549, -2118186, -3859737, -1399561, -3277672, 1757237, -19422, 4010497, 280005, 2706023, 95776, 3077325, 3530437, -1661693, -3592148, -2537516, 3915439, -3861115, -3043716, 3574422, -2867647, 3539968, -300467, 2348700, -539299, -1699267, -1643818, 3505694, -3821735, 3507263, -2140649, -1600420, 3699596, 811944, 531354, 954230, 3881043, 3900724, -2556880, 2071892, -2797779, -3930395, -1528703, -3677745, -3041255, -1452451, 3475950, 2176455, -1585221, -1257611, 1939314, -4083598, -1000202, -3190144, -3157330, -3632928, 126922, 3412210, -983419, 2147896, 2715295, -2967645, -3693493, -411027, -2477047, -671102, -1228525, -22981, -1308169, -381987, 1349076, 1852771, -1430430, -3343383, 264944, 508951, 3097992, 44288, -1100098, 904516, 3958618, -3724342, -8578, 1653064, -3249728, 2389356, -210977, 759969, -1316856, 189548, -3553272, 3159746, -1851402, -2409325, -177440, 1315589, 1341330, 1285669, -1584928, -812732, -1439742, -3019102, -3881060, -3628969, 3839961, 2091667, 3407706, 2316500, 3817976, -3342478, 2244091, -2446433, -3562462, 266997, 2434439, -1235728, 3513181, -3520352, -3759364, -1197226, -3193378, 900702, 1859098, 909542, 819034, 495491, -1613174, -43260, -522500, -655327, -3122442, 2031748, 3207046, -3556995, -525098, -768622, -3595838, 342297, 286988, -2437823, 4108315, 3437287, -3342277, 1735879, 203044, 2842341, 2691481, -2590150, 1265009, 4055324, 1247620, 2486353, 1595974, -3767016, 1250494, 2635921, -3548272, -2994039, 1869119, 1903435, -1050970, -1333058, 1237275, -3318210, -1430225, -451100, 1312455, 3306115, -1962642, -1279661, 1917081, -2546312, -1374803, 1500165, 777191, 2235880, 3406031, -542412, -2831860, -1671176, -1846953, -2584293, -3724270, 594136, -3776993, -2013608, 2432395, 2454455, -164721, 1957272, 3369112, 185531, -1207385, -3183426, 162844, 1616392, 3014001, 810149, 1652634, -3694233, -1799107, -3038916, 3523897, 3866901, 269760, 2213111, -975884, 1717735, 472078, -426683, 1723600, -1803090, 1910376, -1667432, -1104333, -260646, -3833893, -2939036, -2235985, -420899, -2286327, 183443, -976891, 1612842, -3545687, -554416, 3919660, -48306, -1362209, 3937738, 1400424, -846154, 1976782};
    public static final byte[] D = {48, 49, 53, 0};
    public static final byte[] E = {48, 49, 48, 0};
    public static final byte[] F = {48, 48, 57, 0};
    public static final byte[] G = {48, 48, 53, 0};
    public static final byte[] H = {48, 48, 49, 0};
    public static final byte[] I = {48, 48, 49, 0};
    public static final byte[] J = {48, 48, 50, 0};
    public static final int[] K = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    public static final int[] L = {-1424848535, -487721339, 580428573, 1745064566, -770181698, 1036971123, 461123738, -1582065343, 1268693629, -889041821, -731974758, 43769659, 0, 0, 0, 16777216};
    public static final int[] M = {-4553, -2, -1, -1, -1, -1};
    public static final int[] N = {20729809, 9106, 1, 0, 0, 0, -9106, -3, -1, -1, -1, -1};
    public static final int[] O = {-20729809, -9107, -2, -1, -1, -1, 9105, 2};
    public static final int[] P = {-1, -1, -1, 0, 0, 0, 1, -1};
    public static final int[] Q = {1, 0, 0, -2, -1, -1, -2, 1, -2, 1, -2, 1, 1, -2, 2, -2};
    public static final long[] R = {878416384462358536L, 3513665537849438403L, -9076969306111048948L, 585610922974906400L, 34087042};
    public static final er0 S = new er0();
    public static final int[] T = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};

    public /* synthetic */ l21(int i2) {
        this.f = i2;
    }

    public static void A0(wr wrVar, xr[] xrVarArr, wr wrVar2) {
        xr xrVar = new xr();
        int i2 = 0;
        t40.A(0, 0, wrVar.a, xrVar.a);
        int[] iArr = xrVar.b;
        int[] iArr2 = wrVar.b;
        t40.A(0, 0, iArr2, iArr);
        t40.J0(xrVar.c);
        t40.C0(wrVar.a, iArr2, xrVar.d);
        xr xrVar2 = new xr();
        v0(xrVar, xrVar, xrVar2, wrVar2);
        while (true) {
            xr xrVar3 = new xr();
            int[] iArr3 = new int[10];
            xrVar3.a = iArr3;
            int[] iArr4 = new int[10];
            xrVar3.b = iArr4;
            int[] iArr5 = new int[10];
            xrVar3.c = iArr5;
            int[] iArr6 = new int[10];
            xrVar3.d = iArr6;
            xrVarArr[i2] = xrVar3;
            t40.e(xrVar.b, xrVar.a, iArr4, iArr3);
            t40.C0(xrVar.d, v, iArr5);
            int[] iArr7 = xrVar.c;
            t40.a(iArr7, iArr7, iArr6);
            i2++;
            if (i2 == 4) {
                return;
            } else {
                v0(xrVar, xrVar2, xrVar, wrVar2);
            }
        }
    }

    public static void B(int i2, int i3, int[] iArr, int[] iArr2) {
        int i4 = iArr[i2];
        int i5 = iArr[i2 + 1];
        int i6 = iArr[i2 + 2];
        int i7 = iArr[i2 + 3];
        int i8 = iArr[i2 + 4];
        int i9 = iArr[i2 + 5];
        int i10 = iArr[i2 + 6];
        int i11 = iArr[i2 + 7];
        iArr2[i3] = (i5 << 28) | i4;
        iArr2[i3 + 1] = (i5 >>> 4) | (i6 << 24);
        iArr2[i3 + 2] = (i6 >>> 8) | (i7 << 20);
        iArr2[i3 + 3] = (i7 >>> 12) | (i8 << 16);
        iArr2[i3 + 4] = (i8 >>> 16) | (i9 << 12);
        iArr2[i3 + 5] = (i9 >>> 20) | (i10 << 8);
        iArr2[i3 + 6] = (i11 << 4) | (i10 >>> 24);
    }

    public static void B0() {
        synchronized (x) {
            try {
                if (A != null) {
                    return;
                }
                xr[] xrVarArr = new xr[96];
                wr wrVar = new wr(1);
                int[] iArr = new int[10];
                int[] iArr2 = new int[10];
                int[] iArr3 = q;
                int i2 = 0;
                t40.A(0, 0, iArr3, iArr);
                int[] iArr4 = r;
                t40.A(0, 0, iArr4, iArr2);
                xr xrVar = new xr();
                xrVarArr[0] = xrVar;
                t40.A(0, 0, iArr, xrVar.a);
                t40.A(0, 0, iArr2, xrVar.b);
                t40.J0(xrVar.c);
                t40.C0(iArr, iArr2, xrVar.d);
                xr xrVar2 = new xr();
                xr xrVar3 = xrVarArr[0];
                v0(xrVar3, xrVar3, xrVar2, wrVar);
                for (int i3 = 1; i3 < 16; i3++) {
                    int i4 = 0 + i3;
                    xr xrVar4 = xrVarArr[i4 - 1];
                    xr xrVar5 = new xr();
                    xrVarArr[i4] = xrVar5;
                    v0(xrVar4, xrVar2, xrVar5, wrVar);
                }
                int[] iArr5 = new int[10];
                int[] iArr6 = new int[10];
                t40.A(0, 0, s, iArr5);
                t40.A(0, 0, t, iArr6);
                xr xrVar6 = new xr();
                xrVarArr[16] = xrVar6;
                t40.A(0, 0, iArr5, xrVar6.a);
                t40.A(0, 0, iArr6, xrVar6.b);
                t40.J0(xrVar6.c);
                t40.C0(iArr5, iArr6, xrVar6.d);
                xr xrVar7 = new xr();
                xr xrVar8 = xrVarArr[16];
                v0(xrVar8, xrVar8, xrVar7, wrVar);
                for (int i5 = 1; i5 < 16; i5++) {
                    int i6 = 16 + i5;
                    xr xrVar9 = xrVarArr[i6 - 1];
                    xr xrVar10 = new xr();
                    xrVarArr[i6] = xrVar10;
                    v0(xrVar9, xrVar7, xrVar10, wrVar);
                }
                c4 c4Var = new c4();
                t40.A(0, 0, iArr3, (int[]) c4Var.b);
                t40.A(0, 0, iArr4, (int[]) c4Var.c);
                t40.J0((int[]) c4Var.d);
                t40.A(0, 0, (int[]) c4Var.b, (int[]) c4Var.e);
                t40.A(0, 0, (int[]) c4Var.c, (int[]) c4Var.f);
                int i7 = 4;
                xr[] xrVarArr2 = new xr[4];
                for (int i8 = 0; i8 < 4; i8++) {
                    xrVarArr2[i8] = new xr();
                }
                xr xrVar11 = new xr();
                int i9 = 0;
                int i10 = 32;
                while (i9 < 8) {
                    int i11 = i10 + 1;
                    xr xrVar12 = new xr();
                    xrVarArr[i10] = xrVar12;
                    int i12 = i2;
                    while (i12 < i7) {
                        if (i12 == 0) {
                            y0(c4Var, xrVar12);
                        } else {
                            y0(c4Var, xrVar11);
                            v0(xrVar12, xrVar11, xrVar12, wrVar);
                        }
                        z0(c4Var);
                        y0(c4Var, xrVarArr2[i12]);
                        if (i9 + i12 != 10) {
                            for (int i13 = 1; i13 < 8; i13++) {
                                z0(c4Var);
                            }
                        }
                        i12++;
                        i7 = 4;
                    }
                    int[] iArr7 = xrVar12.a;
                    t40.H0(iArr7, iArr7);
                    int[] iArr8 = xrVar12.d;
                    t40.H0(iArr8, iArr8);
                    int i14 = i2;
                    i10 = i11;
                    while (i14 < 3) {
                        int i15 = 1 << i14;
                        int i16 = i2;
                        while (i16 < i15) {
                            xr xrVar13 = new xr();
                            xrVarArr[i10] = xrVar13;
                            v0(xrVarArr[i10 - i15], xrVarArr2[i14], xrVar13, wrVar);
                            i16++;
                            i10++;
                        }
                        i14++;
                        i2 = 0;
                    }
                    i9++;
                    i7 = 4;
                    i2 = 0;
                }
                Z(xrVarArr);
                y = new yr[16];
                for (int i17 = 0; i17 < 16; i17++) {
                    xr xrVar14 = xrVarArr[i17];
                    yr[] yrVarArr = y;
                    yr yrVar = new yr(0);
                    yrVarArr[i17] = yrVar;
                    int[] iArr9 = xrVar14.a;
                    t40.C0(iArr9, xrVar14.c, iArr9);
                    int[] iArr10 = xrVar14.b;
                    t40.C0(iArr10, xrVar14.c, iArr10);
                    t40.e(xrVar14.b, xrVar14.a, yrVar.b, yrVar.a);
                    t40.C0(xrVar14.a, xrVar14.b, yrVar.c);
                    int[] iArr11 = yrVar.c;
                    t40.C0(iArr11, w, iArr11);
                    t40.I0(yrVar.a);
                    t40.I0(yrVar.b);
                    t40.I0(yrVar.c);
                }
                z = new yr[16];
                for (int i18 = 0; i18 < 16; i18++) {
                    xr xrVar15 = xrVarArr[16 + i18];
                    yr[] yrVarArr2 = z;
                    yr yrVar2 = new yr(0);
                    yrVarArr2[i18] = yrVar2;
                    int[] iArr12 = xrVar15.a;
                    t40.C0(iArr12, xrVar15.c, iArr12);
                    int[] iArr13 = xrVar15.b;
                    t40.C0(iArr13, xrVar15.c, iArr13);
                    t40.e(xrVar15.b, xrVar15.a, yrVar2.b, yrVar2.a);
                    t40.C0(xrVar15.a, xrVar15.b, yrVar2.c);
                    int[] iArr14 = yrVar2.c;
                    t40.C0(iArr14, w, iArr14);
                    t40.I0(yrVar2.a);
                    t40.I0(yrVar2.b);
                    t40.I0(yrVar2.c);
                }
                A = new int[1920];
                int[] iArr15 = new int[10];
                int[] iArr16 = new int[10];
                int[] iArr17 = new int[10];
                int i19 = 0;
                for (int i20 = 32; i20 < 96; i20++) {
                    xr xrVar16 = xrVarArr[i20];
                    int[] iArr18 = xrVar16.a;
                    t40.C0(iArr18, xrVar16.c, iArr18);
                    int[] iArr19 = xrVar16.b;
                    t40.C0(iArr19, xrVar16.c, iArr19);
                    t40.e(xrVar16.b, xrVar16.a, iArr16, iArr15);
                    t40.C0(xrVar16.a, xrVar16.b, iArr17);
                    t40.C0(iArr17, w, iArr17);
                    t40.I0(iArr15);
                    t40.I0(iArr16);
                    t40.I0(iArr17);
                    t40.A(0, i19, iArr15, A);
                    t40.A(0, i19 + 10, iArr16, A);
                    t40.A(0, i19 + 20, iArr17, A);
                    i19 += 30;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void C(int i2, int i3, byte[] bArr, int[] iArr) {
        int i4 = iArr[i2];
        int i5 = iArr[i2 + 1];
        int i6 = (i5 << 28) | i4;
        bArr[i3] = (byte) i6;
        bArr[i3 + 1] = (byte) (i6 >>> 8);
        bArr[i3 + 2] = (byte) (i6 >>> 16);
        bArr[i3 + 3] = (byte) (i6 >>> 24);
        bArr[i3 + 4] = (byte) (i5 >>> 4);
        bArr[i3 + 5] = (byte) (i5 >>> 12);
        bArr[i3 + 6] = (byte) (i5 >>> 20);
    }

    public static void C0(byte[] bArr, byte[] bArr2) {
        System.arraycopy(bArr, 0, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        byte b = (byte) (bArr2[31] & 127);
        bArr2[31] = b;
        bArr2[31] = (byte) (b | 64);
    }

    public static void D(z8 z8Var, byte[] bArr, int i2) {
        int[] iArr = (int[]) z8Var.F;
        t40.M(10, i2, bArr, iArr);
        t40.M(15, i2 + 16, bArr, iArr);
        int i3 = i2 + 31;
        bArr[i3] = (byte) (((((int[]) z8Var.F)[0] & 1) << 7) | bArr[i3]);
    }

    public static final Cursor D0(kp0 kp0Var, jy0 jy0Var, boolean z2) throws IOException {
        t40.n(kp0Var, "db");
        t40.n(jy0Var, "sqLiteQuery");
        Cursor cursorQuery = kp0Var.query(jy0Var, (CancellationSignal) null);
        if (z2 && (cursorQuery instanceof AbstractWindowedCursor)) {
            AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) cursorQuery;
            int count = abstractWindowedCursor.getCount();
            if ((abstractWindowedCursor.hasWindow() ? abstractWindowedCursor.getWindow().getNumRows() : count) < count) {
                t40.n(cursorQuery, "c");
                try {
                    MatrixCursor matrixCursor = new MatrixCursor(cursorQuery.getColumnNames(), cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        Object[] objArr = new Object[cursorQuery.getColumnCount()];
                        int columnCount = cursorQuery.getColumnCount();
                        for (int i2 = 0; i2 < columnCount; i2++) {
                            int type = cursorQuery.getType(i2);
                            if (type == 0) {
                                objArr[i2] = null;
                            } else if (type == 1) {
                                objArr[i2] = Long.valueOf(cursorQuery.getLong(i2));
                            } else if (type == 2) {
                                objArr[i2] = Double.valueOf(cursorQuery.getDouble(i2));
                            } else if (type == 3) {
                                objArr[i2] = cursorQuery.getString(i2);
                            } else {
                                if (type != 4) {
                                    throw new IllegalStateException();
                                }
                                objArr[i2] = cursorQuery.getBlob(i2);
                            }
                        }
                        matrixCursor.addRow(objArr);
                    }
                    cursorQuery.close();
                    return matrixCursor;
                } finally {
                }
            }
        }
        return cursorQuery;
    }

    public static List E0(Resources resources, int i2) {
        if (i2 == 0) {
            return Collections.EMPTY_LIST;
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i2);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList = new ArrayList();
            if (jz.a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i3 = 0; i3 < typedArrayObtainTypedArray.length(); i3++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i3, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i2);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [pd] */
    /* JADX WARN: Type inference failed for: r4v1, types: [ch, java.lang.Object, ju0] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r7v0, types: [ob] */
    public static final Object F(pd pdVar, long j2, ob obVar) {
        while (true) {
            long j3 = pdVar.c;
            if (j3 >= j2 && !pdVar.c()) {
                return pdVar;
            }
            Object obj = ch.a.get(pdVar);
            wy0 wy0Var = m;
            if (obj == wy0Var) {
                return wy0Var;
            }
            ju0 ju0Var = (ju0) ((ch) obj);
            if (ju0Var != null) {
                pdVar = ju0Var;
            } else {
                ju0 ju0Var2 = (ju0) obVar.invoke(Long.valueOf(j3 + 1), pdVar);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ch.a;
                    if (atomicReferenceFieldUpdater.compareAndSet(pdVar, null, ju0Var2)) {
                        if (pdVar.c()) {
                            pdVar.d();
                        }
                        pdVar = ju0Var2;
                    } else if (atomicReferenceFieldUpdater.get(pdVar) != null) {
                        break;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        r2 = r4.getAttributeValue(null, "application_locales");
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0045 A[EXC_TOP_SPLITTER, PHI: r2
      0x0045: PHI (r2v2 java.lang.String) = (r2v0 java.lang.String), (r2v4 java.lang.String) binds: [B:25:0x004e, B:21:0x0043] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String F0(android.content.Context r9) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = ""
            java.io.FileInputStream r3 = r9.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L6b
            org.xmlpull.v1.XmlPullParser r4 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r5 = "UTF-8"
            r4.setInput(r3, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            int r5 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L17:
            int r6 = r4.next()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            r7 = 1
            if (r6 == r7) goto L43
            r7 = 3
            if (r6 != r7) goto L2a
            int r8 = r4.getDepth()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r8 <= r5) goto L43
            goto L2a
        L28:
            r9 = move-exception
            goto L65
        L2a:
            if (r6 == r7) goto L17
            r7 = 4
            if (r6 != r7) goto L30
            goto L17
        L30:
            java.lang.String r6 = r4.getName()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            java.lang.String r7 = "locales"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
            if (r6 == 0) goto L17
            java.lang.String r5 = "application_locales"
            r6 = 0
            java.lang.String r2 = r4.getAttributeValue(r6, r5)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L49
        L43:
            if (r3 == 0) goto L51
        L45:
            r3.close()     // Catch: java.io.IOException -> L51
            goto L51
        L49:
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r4)     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L51
            goto L45
        L51:
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L61
            java.lang.String r9 = "Reading app Locales : Locales read from file: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file , appLocales: "
            java.lang.String r9 = r9.concat(r2)
            android.util.Log.d(r1, r9)
            goto L64
        L61:
            r9.deleteFile(r0)
        L64:
            return r2
        L65:
            if (r3 == 0) goto L6a
            r3.close()     // Catch: java.io.IOException -> L6a
        L6a:
            throw r9
        L6b:
            java.lang.String r9 = "Reading app Locales : Locales record file not found: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l21.F0(android.content.Context):java.lang.String");
    }

    public static z8 G(byte[] bArr) {
        fq0 fq0Var = new fq0();
        byte[] bArr2 = new byte[64];
        fq0Var.e(bArr, 0, 32);
        fq0Var.d(0, bArr2);
        byte[] bArr3 = new byte[32];
        C0(bArr2, bArr3);
        c4 c4Var = new c4();
        O0(bArr3, c4Var);
        int[] iArr = new int[10];
        int[] iArr2 = new int[10];
        int[] iArr3 = new int[10];
        int[] iArr4 = new int[8];
        t40.A(0, 0, (int[]) c4Var.d, iArr3);
        t40.I0(iArr3);
        t40.N(0, 0, iArr3, iArr4);
        t40.N(5, 4, iArr3, iArr4);
        ma0.T(t40.t, iArr4, iArr4);
        t40.E(0, 0, iArr4, iArr2);
        t40.E(4, 5, iArr4, iArr2);
        iArr2[9] = iArr2[9] & 16777215;
        t40.C0(iArr2, (int[]) c4Var.b, iArr);
        t40.C0(iArr2, (int[]) c4Var.c, iArr2);
        t40.I0(iArr);
        t40.I0(iArr2);
        int[] iArr5 = new int[10];
        int[] iArr6 = new int[10];
        int[] iArr7 = new int[10];
        t40.e1(iArr, iArr6);
        t40.e1(iArr2, iArr7);
        t40.C0(iArr6, iArr7, iArr5);
        t40.k1(iArr6, iArr7, iArr6);
        t40.C0(iArr5, u, iArr5);
        iArr5[0] = iArr5[0] + 1;
        t40.a(iArr5, iArr6, iArr5);
        t40.I0(iArr5);
        t40.I0(iArr7);
        if ((t40.z0(iArr5) & (~t40.z0(iArr7))) == 0) {
            throw new IllegalStateException();
        }
        int[] iArr8 = new int[20];
        t40.A(0, 0, iArr, iArr8);
        t40.A(0, 10, iArr2, iArr8);
        return new z8(16, iArr8);
    }

    public static void G0(int[] iArr, int i2) {
        int i3;
        int i4 = iArr[15];
        int i5 = i4 & 268435455;
        long j2 = (i4 >>> 28) + i2;
        int i6 = 0;
        long j3 = j2;
        while (true) {
            if (i6 >= 8) {
                break;
            }
            long j4 = j3 + (4294967295L & ((long) iArr[i6]));
            iArr[i6] = ((int) j4) & 268435455;
            j3 = j4 >> 28;
            i6++;
        }
        long j5 = j3 + j2;
        for (i3 = 8; i3 < 15; i3++) {
            long j6 = j5 + (((long) iArr[i3]) & 4294967295L);
            iArr[i3] = ((int) j6) & 268435455;
            j5 = j6 >> 28;
        }
        iArr[15] = i5 + ((int) j5);
    }

    public static po H(y70 y70Var) {
        return v(y70Var.f, y70Var.b);
    }

    public static void H0(int[] iArr, int[] iArr2) {
        long j2 = ((long) 4553) & 4294967295L;
        long j3 = ((long) iArr[6]) & 4294967295L;
        long j4 = (j2 * j3) + (((long) iArr[0]) & 4294967295L);
        int i2 = (int) j4;
        iArr2[0] = i2;
        long j5 = ((long) iArr[7]) & 4294967295L;
        long j6 = (j2 * j5) + j3 + (((long) iArr[1]) & 4294967295L) + (j4 >>> 32);
        int i3 = (int) j6;
        iArr2[1] = i3;
        long j7 = ((long) iArr[8]) & 4294967295L;
        long j8 = (j2 * j7) + j5 + (((long) iArr[2]) & 4294967295L) + (j6 >>> 32);
        int i4 = (int) j8;
        iArr2[2] = i4;
        long j9 = ((long) iArr[9]) & 4294967295L;
        long j10 = (j2 * j9) + j7 + (((long) iArr[3]) & 4294967295L) + (j8 >>> 32);
        int i5 = (int) j10;
        iArr2[3] = i5;
        long j11 = ((long) iArr[10]) & 4294967295L;
        long j12 = (j2 * j11) + j9 + (((long) iArr[4]) & 4294967295L) + (j10 >>> 32);
        iArr2[4] = (int) j12;
        long j13 = ((long) iArr[11]) & 4294967295L;
        long j14 = (j2 * j13) + j11 + (((long) iArr[5]) & 4294967295L) + (j12 >>> 32);
        iArr2[5] = (int) j14;
        long j15 = (j14 >>> 32) + j13;
        long j16 = j15 & 4294967295L;
        long j17 = (j2 * j16) + (((long) i2) & 4294967295L);
        iArr2[0] = (int) j17;
        long j18 = j15 >>> 32;
        long j19 = (j2 * j18) + j16 + (((long) i3) & 4294967295L) + (j17 >>> 32);
        iArr2[1] = (int) j19;
        long j20 = j18 + (((long) i4) & 4294967295L) + (j19 >>> 32);
        iArr2[2] = (int) j20;
        long j21 = (j20 >>> 32) + (((long) i5) & 4294967295L);
        iArr2[3] = (int) j21;
        if (((j21 >>> 32) == 0 ? 0 : fa0.b0(6, 4, iArr2)) != 0 || (iArr2[5] == -1 && o80.D(iArr2, M))) {
            fa0.c(6, 4553, iArr2);
        }
    }

    public static float I(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT >= 31) {
            return hs.b(edgeEffect);
        }
        return 0.0f;
    }

    public static void I0(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        long j10 = jArr[8];
        long j11 = j6 ^ ((((j10 >>> 27) ^ (j10 >>> 22)) ^ (j10 >>> 20)) ^ (j10 >>> 15));
        long j12 = j2 ^ ((((j7 << 37) ^ (j7 << 42)) ^ (j7 << 44)) ^ (j7 << 49));
        long j13 = (j3 ^ ((((j8 << 37) ^ (j8 << 42)) ^ (j8 << 44)) ^ (j8 << 49))) ^ ((((j7 >>> 27) ^ (j7 >>> 22)) ^ (j7 >>> 20)) ^ (j7 >>> 15));
        long j14 = j11 >>> 27;
        jArr2[0] = (((j12 ^ j14) ^ (j14 << 5)) ^ (j14 << 7)) ^ (j14 << 12);
        jArr2[1] = j13;
        jArr2[2] = (j4 ^ ((((j9 << 37) ^ (j9 << 42)) ^ (j9 << 44)) ^ (j9 << 49))) ^ ((((j8 >>> 27) ^ (j8 >>> 22)) ^ (j8 >>> 20)) ^ (j8 >>> 15));
        jArr2[3] = (j5 ^ ((((j10 << 37) ^ (j10 << 42)) ^ (j10 << 44)) ^ (j10 << 49))) ^ ((((j9 >>> 27) ^ (j9 >>> 22)) ^ (j9 >>> 20)) ^ (j9 >>> 15));
        jArr2[4] = 134217727 & j11;
    }

    public static void J0(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[8]) & 4294967295L;
        long j3 = ((long) iArr[9]) & 4294967295L;
        long j4 = ((long) iArr[10]) & 4294967295L;
        long j5 = ((long) iArr[11]) & 4294967295L;
        long j6 = ((long) iArr[12]) & 4294967295L;
        long j7 = ((long) iArr[13]) & 4294967295L;
        long j8 = ((long) iArr[14]) & 4294967295L;
        long j9 = ((long) iArr[15]) & 4294967295L;
        long j10 = j2 - 6;
        long j11 = j10 + j3;
        long j12 = j3 + j4;
        long j13 = (j4 + j5) - j9;
        long j14 = j5 + j6;
        long j15 = j6 + j7;
        long j16 = j7 + j8;
        long j17 = j8 + j9;
        long j18 = j16 - j11;
        long j19 = ((((long) iArr[0]) & 4294967295L) - j14) - j18;
        iArr2[0] = (int) j19;
        long j20 = ((((((long) iArr[1]) & 4294967295L) + j12) - j15) - j17) + (j19 >> 32);
        iArr2[1] = (int) j20;
        long j21 = (((((long) iArr[2]) & 4294967295L) + j13) - j16) + (j20 >> 32);
        iArr2[2] = (int) j21;
        long j22 = ((((((long) iArr[3]) & 4294967295L) + (j14 << 1)) + j18) - j17) + (j21 >> 32);
        iArr2[3] = (int) j22;
        long j23 = ((((((long) iArr[4]) & 4294967295L) + (j15 << 1)) + j8) - j12) + (j22 >> 32);
        iArr2[4] = (int) j23;
        long j24 = (((((long) iArr[5]) & 4294967295L) + (j16 << 1)) - j13) + (j23 >> 32);
        iArr2[5] = (int) j24;
        long j25 = (((long) iArr[6]) & 4294967295L) + (j17 << 1) + j18 + (j24 >> 32);
        iArr2[6] = (int) j25;
        long j26 = (((((((long) iArr[7]) & 4294967295L) + (j9 << 1)) + j10) - j13) - j15) + (j25 >> 32);
        iArr2[7] = (int) j26;
        L0(iArr2, (int) ((j26 >> 32) + 6));
    }

    public static byte[] K(l4 l4Var, a0 a0Var) {
        try {
            return new ol0(l4Var, a0Var.b(), null, null).f();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void K0(int[] r12, int r13) {
        /*
            r0 = 6
            r1 = 4553(0x11c9, float:6.38E-42)
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
            r13 = 5
            r13 = r12[r13]
            r2 = -1
            if (r13 != r2) goto L50
            int[] r13 = defpackage.l21.M
            boolean r13 = defpackage.o80.D(r12, r13)
            if (r13 == 0) goto L50
        L4d:
            defpackage.fa0.c(r0, r1, r12)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l21.K0(int[], int):void");
    }

    public static byte[] L(l4 l4Var, a0 a0Var) {
        try {
            return M(new sx0(l4Var, a0Var));
        } catch (Exception unused) {
            return null;
        }
    }

    public static void L0(int[] iArr, int i2) {
        long j2;
        if (i2 != 0) {
            long j3 = ((long) i2) & 4294967295L;
            long j4 = (((long) iArr[0]) & 4294967295L) + j3;
            iArr[0] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (((long) iArr[1]) & 4294967295L);
                iArr[1] = (int) j6;
                long j7 = (j6 >> 32) + (((long) iArr[2]) & 4294967295L);
                iArr[2] = (int) j7;
                j5 = j7 >> 32;
            }
            long j8 = ((((long) iArr[3]) & 4294967295L) - j3) + j5;
            iArr[3] = (int) j8;
            long j9 = j8 >> 32;
            if (j9 != 0) {
                long j10 = j9 + (((long) iArr[4]) & 4294967295L);
                iArr[4] = (int) j10;
                long j11 = (j10 >> 32) + (((long) iArr[5]) & 4294967295L);
                iArr[5] = (int) j11;
                j9 = j11 >> 32;
            }
            long j12 = ((((long) iArr[6]) & 4294967295L) - j3) + j9;
            iArr[6] = (int) j12;
            long j13 = (4294967295L & ((long) iArr[7])) + j3 + (j12 >> 32);
            iArr[7] = (int) j13;
            j2 = j13 >> 32;
        } else {
            j2 = 0;
        }
        if (j2 != 0 || (iArr[7] == -1 && fa0.U(iArr, P))) {
            i(iArr);
        }
    }

    public static byte[] M(sx0 sx0Var) {
        try {
            return sx0Var.f();
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] M0(byte[] bArr) {
        long jI = ((long) t40.I(0, bArr)) & 4294967295L;
        long jF = ((long) (t40.F(4, bArr) << 4)) & 4294967295L;
        long jI2 = ((long) t40.I(7, bArr)) & 4294967295L;
        long jF2 = ((long) (t40.F(11, bArr) << 4)) & 4294967295L;
        long jI3 = ((long) t40.I(14, bArr)) & 4294967295L;
        long jF3 = ((long) (t40.F(18, bArr) << 4)) & 4294967295L;
        long jI4 = ((long) t40.I(21, bArr)) & 4294967295L;
        long jF4 = ((long) (t40.F(25, bArr) << 4)) & 4294967295L;
        long jI5 = ((long) t40.I(28, bArr)) & 4294967295L;
        long jF5 = ((long) (t40.F(32, bArr) << 4)) & 4294967295L;
        long jI6 = ((long) t40.I(35, bArr)) & 4294967295L;
        long jF6 = ((long) (t40.F(39, bArr) << 4)) & 4294967295L;
        long jI7 = ((long) t40.I(42, bArr)) & 4294967295L;
        long jF7 = ((long) (t40.F(46, bArr) << 4)) & 4294967295L;
        long jI8 = t40.I(49, bArr);
        long j2 = jI8 & 4294967295L;
        long jF8 = ((long) (t40.F(53, bArr) << 4)) & 4294967295L;
        long jI9 = t40.I(56, bArr);
        long jF9 = ((long) (t40.F(60, bArr) << 4)) & 4294967295L;
        long j3 = ((long) bArr[63]) & 255;
        long j4 = jF9 + ((jI9 & 4294967295L) >> 28);
        long j5 = jI9 & 268435455;
        long j6 = (jI7 - (j3 * (-6428113))) - (j4 * 5343);
        long j7 = (jI5 - (j4 * (-50998291))) - (j5 * 19280294);
        long j8 = ((jF5 - (j3 * (-50998291))) - (j4 * 19280294)) - (j5 * 127719000);
        long j9 = ((jI6 - (j3 * 19280294)) - (j4 * 127719000)) - (j5 * (-6428113));
        long j10 = ((jF6 - (j3 * 127719000)) - (j4 * (-6428113))) - (j5 * 5343);
        long j11 = jF8 + (j2 >> 28);
        long j12 = jI8 & 268435455;
        long j13 = j9 - (j11 * 5343);
        long j14 = (j8 - (j11 * (-6428113))) - (j12 * 5343);
        long j15 = (jF7 - (j3 * 5343)) + (j6 >> 28);
        long j16 = ((jI4 - (j11 * (-50998291))) - (j12 * 19280294)) - (j15 * 127719000);
        long j17 = (((jF4 - (j5 * (-50998291))) - (j11 * 19280294)) - (j12 * 127719000)) - (j15 * (-6428113));
        long j18 = ((j7 - (j11 * 127719000)) - (j12 * (-6428113))) - (j15 * 5343);
        long j19 = (j6 & 268435455) + (j10 >> 28);
        long j20 = (jI3 - (j15 * (-50998291))) - (j19 * 19280294);
        long j21 = ((jF3 - (j12 * (-50998291))) - (j15 * 19280294)) - (j19 * 127719000);
        long j22 = j17 - (j19 * 5343);
        long j23 = (j10 & 268435455) + (j13 >> 28);
        long j24 = (jF2 - (j19 * (-50998291))) - (j23 * 19280294);
        long j25 = (j16 - (j19 * (-6428113))) - (j23 * 5343);
        long j26 = (j13 & 268435455) + (j14 >> 28);
        long j27 = jF - (j26 * (-50998291));
        long j28 = (jI2 - (j23 * (-50998291))) - (j26 * 19280294);
        long j29 = j24 - (j26 * 127719000);
        long j30 = (j20 - (j23 * 127719000)) - (j26 * (-6428113));
        long j31 = (j21 - (j23 * (-6428113))) - (j26 * 5343);
        long j32 = j18 + (j22 >> 28);
        long j33 = j22 & 268435455;
        long j34 = (j14 & 268435455) + (j32 >> 28);
        long j35 = j32 & 268435455;
        long j36 = j35 >>> 27;
        long j37 = j34 + j36;
        long j38 = jI - (j37 * (-50998291));
        long j39 = j28 - (j37 * 127719000);
        long j40 = j29 - (j37 * (-6428113));
        long j41 = j30 - (j37 * 5343);
        long j42 = (j27 - (j37 * 19280294)) + (j38 >> 28);
        long j43 = j38 & 268435455;
        long j44 = j39 + (j42 >> 28);
        long j45 = j40 + (j44 >> 28);
        long j46 = j41 + (j45 >> 28);
        long j47 = j31 + (j46 >> 28);
        long j48 = j25 + (j47 >> 28);
        long j49 = j33 + (j48 >> 28);
        long j50 = j35 + (j49 >> 28);
        long j51 = (j50 >> 28) - j36;
        long j52 = j43 + (j51 & (-50998291));
        long j53 = (j42 & 268435455) + (j51 & 19280294) + (j52 >> 28);
        long j54 = (j44 & 268435455) + (j51 & 127719000) + (j53 >> 28);
        long j55 = (j45 & 268435455) + (j51 & (-6428113)) + (j54 >> 28);
        long j56 = (j46 & 268435455) + (j51 & 5343) + (j55 >> 28);
        long j57 = (j47 & 268435455) + (j56 >> 28);
        long j58 = (j48 & 268435455) + (j57 >> 28);
        long j59 = (j49 & 268435455) + (j58 >> 28);
        long j60 = (j50 & 268435455) + (j59 >> 28);
        long j61 = j59 & 268435455;
        byte[] bArr2 = new byte[32];
        t40.Q((j52 & 268435455) | ((j53 & 268435455) << 28), bArr2, 0);
        t40.Q((j54 & 268435455) | ((j55 & 268435455) << 28), bArr2, 7);
        t40.Q(((j57 & 268435455) << 28) | (j56 & 268435455), bArr2, 14);
        t40.Q((j61 << 28) | (j58 & 268435455), bArr2, 21);
        t40.P(bArr2, (int) j60, 28);
        return bArr2;
    }

    public static void N0(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int[] drawableState = textInputLayout.getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
        System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
        int colorForState = colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor());
        Drawable drawableMutate = drawable.mutate();
        ip.h(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public static j4 O(Context context, c4 c4Var) throws PackageManager.NameNotFoundException {
        Cursor cursorQuery;
        PackageManager packageManager = context.getPackageManager();
        Resources resources = context.getResources();
        String str = (String) c4Var.b;
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(zn.v("No package found for authority: ", str));
        }
        String str2 = providerInfoResolveContentProvider.packageName;
        String str3 = (String) c4Var.c;
        if (!str2.equals(str3)) {
            throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + str3);
        }
        Signature[] signatureArr = packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures;
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        cz czVar = B;
        Collections.sort(arrayList, czVar);
        List listE0 = (List) c4Var.e;
        if (listE0 == null) {
            listE0 = E0(resources, 0);
        }
        int i2 = 0;
        loop1: while (true) {
            cursorQuery = null;
            if (i2 >= listE0.size()) {
                providerInfoResolveContentProvider = null;
                break;
            }
            ArrayList arrayList2 = new ArrayList((Collection) listE0.get(i2));
            Collections.sort(arrayList2, czVar);
            if (arrayList.size() == arrayList2.size()) {
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (!Arrays.equals((byte[]) arrayList.get(i3), (byte[]) arrayList2.get(i3))) {
                        break;
                    }
                }
                break loop1;
            }
            i2++;
        }
        if (providerInfoResolveContentProvider == null) {
            return new j4(1, (oz[]) null);
        }
        String str4 = providerInfoResolveContentProvider.authority;
        ArrayList arrayList3 = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str4).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str4).appendPath("file").build();
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(uriBuild);
        try {
            String[] strArr = {"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"};
            String[] strArr2 = {(String) c4Var.d};
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                try {
                    cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(uriBuild, strArr, "query = ?", strArr2, null, null);
                } catch (RemoteException e) {
                    Log.w("FontsProvider", "Unable to query the content provider", e);
                }
            }
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex("result_code");
                arrayList3 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                while (cursorQuery.moveToNext()) {
                    arrayList3.add(new oz(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : Status.BAD_REQUEST_CODE, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0));
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                contentProviderClientAcquireUnstableContentProviderClient.close();
            }
            return new j4(0, (oz[]) arrayList3.toArray(new oz[0]));
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                contentProviderClientAcquireUnstableContentProviderClient.close();
            }
            throw th;
        }
    }

    public static void O0(byte[] bArr, c4 c4Var) {
        char c;
        int[] iArr;
        int[] iArr2;
        B0();
        int i2 = 8;
        int[] iArr3 = new int[8];
        w(bArr, iArr3);
        int i3 = 0;
        int i4 = ~iArr3[0];
        int[] iArr4 = K;
        int i5 = 1;
        long j2 = ((long) (-(i4 & 1))) & 4294967295L;
        long j3 = 0;
        int i6 = 0;
        while (i6 < 8) {
            int i7 = i6;
            long j4 = (((long) iArr3[i6]) & 4294967295L) + (((long) iArr4[i6]) & j2) + j3;
            iArr3[i7] = (int) j4;
            j3 = j4 >>> 32;
            i6 = i7 + 1;
            i3 = i3;
        }
        int i8 = i3;
        int i9 = 8;
        int i10 = 1;
        while (true) {
            i9--;
            if (i9 < 0) {
                break;
            }
            int i11 = iArr3[i9];
            iArr3[i9] = (i10 << 31) | (i11 >>> 1);
            i10 = i11;
        }
        int i12 = i8;
        while (true) {
            c = 7;
            if (i12 >= 8) {
                break;
            }
            iArr3[i12] = o(o(o(o(iArr3[i12], 11141290, 7), 52428, 14), 15728880, 4), 65280, 8);
            i12++;
        }
        int[] iArr5 = new int[10];
        int[] iArr6 = new int[10];
        int[] iArr7 = new int[10];
        int[] iArr8 = new int[10];
        int[] iArr9 = (int[]) c4Var.b;
        for (int i13 = i8; i13 < 10; i13++) {
            iArr9[i13] = i8;
        }
        t40.J0((int[]) c4Var.c);
        t40.J0((int[]) c4Var.d);
        int[] iArr10 = (int[]) c4Var.e;
        for (int i14 = i8; i14 < 10; i14++) {
            iArr10[i14] = i8;
        }
        t40.J0((int[]) c4Var.f);
        int i15 = 28;
        int i16 = i8;
        while (true) {
            int i17 = i8;
            while (true) {
                iArr = (int[]) c4Var.e;
                iArr2 = (int[]) c4Var.b;
                if (i17 >= i2) {
                    break;
                }
                int i18 = iArr3[i17] >>> i15;
                int i19 = (i18 >>> 3) & i5;
                char c2 = c;
                int i20 = ((-i19) ^ i18) & 7;
                int i21 = i17 * 240;
                int i22 = i5;
                int i23 = i8;
                while (i23 < i2) {
                    int i24 = ((i23 ^ i20) - 1) >> 31;
                    t40.p(i24, i21, A, iArr5);
                    t40.p(i24, i21 + 10, A, iArr6);
                    t40.p(i24, i21 + 20, A, iArr7);
                    i21 += 30;
                    i23++;
                    iArr3 = iArr3;
                    i15 = i15;
                    i2 = 8;
                }
                int[] iArr11 = iArr3;
                int i25 = i15;
                int i26 = i16 ^ i19;
                t40.q(iArr2, i26);
                t40.q(iArr, i26);
                int[] iArr12 = (int[]) c4Var.c;
                t40.e(iArr12, iArr2, iArr12, iArr2);
                t40.C0(iArr2, iArr5, iArr2);
                t40.C0(iArr12, iArr6, iArr12);
                int[] iArr13 = (int[]) c4Var.f;
                t40.C0(iArr, iArr13, iArr8);
                t40.C0(iArr8, iArr7, iArr8);
                t40.e(iArr12, iArr2, iArr13, iArr);
                int[] iArr14 = (int[]) c4Var.d;
                t40.e(iArr14, iArr8, iArr12, iArr2);
                t40.C0(iArr2, iArr12, iArr14);
                t40.C0(iArr2, iArr, iArr2);
                t40.C0(iArr12, iArr13, iArr12);
                i17++;
                i16 = i19;
                c = c2;
                i5 = i22;
                iArr3 = iArr11;
                i15 = i25;
                i2 = 8;
            }
            int[] iArr15 = iArr3;
            char c3 = c;
            int i27 = i5;
            i15 -= 4;
            if (i15 < 0) {
                t40.q(iArr2, i16);
                t40.q(iArr, i16);
                return;
            } else {
                z0(c4Var);
                c = c3;
                i5 = i27;
                iArr3 = iArr15;
                i2 = 8;
            }
        }
    }

    public static void P0(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        WeakHashMap weakHashMap = f51.a;
        boolean zHasOnClickListeners = checkableImageButton.hasOnClickListeners();
        boolean z2 = onLongClickListener != null;
        boolean z3 = zHasOnClickListeners || z2;
        checkableImageButton.setFocusable(z3);
        checkableImageButton.setClickable(zHasOnClickListeners);
        checkableImageButton.setPressable(zHasOnClickListeners);
        checkableImageButton.setLongClickable(z2);
        checkableImageButton.setImportantForAccessibility(z3 ? 1 : 2);
    }

    public static String Q(String str, String str2) {
        t40.n(str, "tableName");
        t40.n(str2, "triggerType");
        return "`room_table_modification_trigger_" + str + '_' + str2 + '`';
    }

    public static void Q0(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlResourceParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    public static void R(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        jArr2[0] = j2 & 144115188075855871L;
        jArr2[1] = ((j2 >>> 57) ^ (j3 << 7)) & 144115188075855871L;
        jArr2[2] = ((j3 >>> 50) ^ (j4 << 14)) & 144115188075855871L;
        jArr2[3] = ((j4 >>> 43) ^ (j5 << 21)) & 144115188075855871L;
        jArr2[4] = (j5 >>> 36) ^ (j6 << 28);
    }

    public static void R0(int i2, int[] iArr, int[] iArr2) {
        S0(iArr, iArr2);
        while (true) {
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                S0(iArr2, iArr2);
            }
        }
    }

    public static void S(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[5];
        long[] jArr5 = new long[5];
        R(jArr, jArr4);
        R(jArr2, jArr5);
        long[] jArr6 = new long[26];
        T(jArr3, jArr4[0], jArr5[0], jArr6, 0);
        T(jArr3, jArr4[1], jArr5[1], jArr6, 2);
        T(jArr3, jArr4[2], jArr5[2], jArr6, 4);
        T(jArr3, jArr4[3], jArr5[3], jArr6, 6);
        T(jArr3, jArr4[4], jArr5[4], jArr6, 8);
        long j2 = jArr4[0];
        long j3 = j2 ^ jArr4[1];
        long j4 = jArr5[0];
        long j5 = j4 ^ jArr5[1];
        long j6 = jArr4[2];
        long j7 = j2 ^ j6;
        long j8 = jArr5[2];
        long j9 = j4 ^ j8;
        long j10 = jArr4[4];
        long j11 = j6 ^ j10;
        long j12 = jArr5[4];
        long j13 = j8 ^ j12;
        long j14 = jArr4[3];
        long j15 = j14 ^ j10;
        long j16 = jArr5[3];
        long j17 = j16 ^ j12;
        T(jArr3, j7 ^ j14, j9 ^ j16, jArr6, 18);
        T(jArr3, j11 ^ jArr4[1], j13 ^ jArr5[1], jArr6, 20);
        long j18 = j3 ^ j15;
        long j19 = j5 ^ j17;
        long j20 = j18 ^ jArr4[2];
        long j21 = jArr5[2] ^ j19;
        T(jArr3, j18, j19, jArr6, 22);
        T(jArr3, j20, j21, jArr6, 24);
        T(jArr3, j3, j5, jArr6, 10);
        T(jArr3, j7, j9, jArr6, 12);
        T(jArr3, j11, j13, jArr6, 14);
        T(jArr3, j15, j17, jArr6, 16);
        long j22 = jArr6[0];
        jArr3[0] = j22;
        long j23 = jArr6[9];
        jArr3[9] = j23;
        long j24 = jArr6[0];
        long j25 = jArr6[1] ^ j24;
        long j26 = j25 ^ jArr6[2];
        long j27 = j26 ^ jArr6[10];
        jArr3[1] = j27;
        long j28 = jArr6[3] ^ jArr6[4];
        long j29 = j26 ^ (j28 ^ (jArr6[11] ^ jArr6[12]));
        jArr3[2] = j29;
        long j30 = j25 ^ j28;
        long j31 = jArr6[5] ^ jArr6[6];
        long j32 = jArr6[8];
        long j33 = (j30 ^ j31) ^ j32;
        long j34 = jArr6[13] ^ jArr6[14];
        long j35 = jArr6[18];
        long j36 = jArr6[22];
        long j37 = jArr6[24];
        long j38 = (j33 ^ j34) ^ ((j35 ^ j36) ^ j37);
        jArr3[3] = j38;
        long j39 = jArr6[7] ^ j32;
        long j40 = jArr6[9];
        long j41 = j39 ^ j40;
        long j42 = j41 ^ jArr6[17];
        jArr3[8] = j42;
        long j43 = (j41 ^ j31) ^ (jArr6[15] ^ jArr6[16]);
        jArr3[7] = j43;
        long j44 = jArr6[19] ^ jArr6[20];
        long j45 = jArr6[25];
        long j46 = jArr6[23];
        long j47 = j44 ^ (j45 ^ j37);
        long j48 = (j47 ^ (j35 ^ j46)) ^ (j43 ^ j27);
        jArr3[4] = j48;
        long j49 = jArr6[21];
        long j50 = (j47 ^ (j29 ^ j42)) ^ (j49 ^ j36);
        jArr3[5] = j50;
        long j51 = (((((j24 ^ j33) ^ j40) ^ j34) ^ j49) ^ j46) ^ j45;
        jArr3[6] = j51;
        jArr3[0] = j22 ^ (j27 << 57);
        jArr3[1] = (j27 >>> 7) ^ (j29 << 50);
        jArr3[2] = (j29 >>> 14) ^ (j38 << 43);
        jArr3[3] = (j38 >>> 21) ^ (j48 << 36);
        jArr3[4] = (j48 >>> 28) ^ (j50 << 29);
        jArr3[5] = (j50 >>> 35) ^ (j51 << 22);
        jArr3[6] = (j51 >>> 42) ^ (j43 << 15);
        jArr3[7] = (j43 >>> 49) ^ (j42 << 8);
        jArr3[8] = (j42 >>> 56) ^ (j23 << 1);
        jArr3[9] = j23 >>> 63;
    }

    public static void S0(int[] iArr, int[] iArr2) {
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
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        int i18 = i2 * 2;
        int i19 = i3 * 2;
        int i20 = i6 * 2;
        int i21 = i7 * 2;
        int i22 = i8 * 2;
        int i23 = i10 * 2;
        int i24 = i12 * 2;
        int i25 = i13 * 2;
        int i26 = i14 * 2;
        int i27 = i2 + i10;
        int i28 = i3 + i11;
        int i29 = i4 + i12;
        int i30 = i5 + i13;
        int i31 = i6 + i14;
        int i32 = i7 + i15;
        int i33 = i8 + i16;
        int i34 = i27 * 2;
        int i35 = i28 * 2;
        int i36 = i29 * 2;
        int i37 = i30 * 2;
        long j2 = i2;
        long j3 = j2 * j2;
        long j4 = i9;
        long j5 = i19;
        long j6 = j4 * j5;
        long j7 = i8;
        long j8 = i4 * 2;
        long j9 = (j7 * j8) + j6;
        long j10 = i7;
        long j11 = i5 * 2;
        long j12 = (j10 * j11) + j9;
        long j13 = i6;
        long j14 = i10;
        long j15 = j14 * j14;
        long j16 = i17;
        long j17 = i11 * 2;
        long j18 = i16;
        long j19 = i24;
        long j20 = (j18 * j19) + (j16 * j17);
        long j21 = i15;
        long j22 = i25;
        long j23 = i14;
        long j24 = j23 * j23;
        long j25 = i27;
        long j26 = j25 * j25;
        long j27 = i9 + i17;
        long j28 = ((long) i35) & 4294967295L;
        long j29 = j27 * j28;
        long j30 = i33;
        long j31 = ((long) i36) & 4294967295L;
        long j32 = (j30 * j31) + j29;
        long j33 = i32;
        long j34 = ((long) i37) & 4294967295L;
        long j35 = (j33 * j34) + j32;
        long j36 = i31;
        long j37 = (j36 * j36) + j35;
        long j38 = ((j3 + j15) + j37) - ((j13 * j13) + j12);
        int i38 = ((int) j38) & 268435455;
        long j39 = j38 >>> 28;
        long j40 = (((j24 + ((j21 * j22) + j20)) + j26) - j3) + j37;
        int i39 = ((int) j40) & 268435455;
        long j41 = j40 >>> 28;
        long j42 = i3;
        long j43 = i18;
        long j44 = j42 * j43;
        long j45 = i20;
        long j46 = j10 * j45;
        long j47 = i11;
        long j48 = i23;
        long j49 = j47 * j48;
        long j50 = i26;
        long j51 = j21 * j50;
        long j52 = i28;
        long j53 = ((long) i34) & 4294967295L;
        long j54 = j52 * j53;
        long j55 = (j30 * j34) + (j27 * j31);
        long j56 = ((long) (i31 * 2)) & 4294967295L;
        long j57 = (j33 * j56) + j55;
        long j58 = (((j44 + j49) + j57) - (j46 + ((j7 * j11) + (j4 * j8)))) + j39;
        int i40 = ((int) j58) & 268435455;
        long j59 = j58 >>> 28;
        long j60 = (((j51 + ((j18 * j22) + (j16 * j19))) + j54) - j44) + j57 + j41;
        int i41 = ((int) j60) & 268435455;
        long j61 = j60 >>> 28;
        long j62 = i4;
        long j63 = (j42 * j42) + (j62 * j43);
        long j64 = (j7 * j45) + (j4 * j11);
        long j65 = i12;
        long j66 = (j47 * j47) + (j65 * j48);
        long j67 = (j18 * j50) + (j16 * j22);
        long j68 = i29;
        long j69 = (j52 * j52) + (j68 * j53);
        long j70 = (j33 * j33) + (j30 * j56) + (j27 * j34);
        long j71 = (((j63 + j66) + j70) - ((j10 * j10) + j64)) + j59;
        int i42 = ((int) j71) & 268435455;
        long j72 = j71 >>> 28;
        long j73 = ((((j21 * j21) + j67) + j69) - j63) + j70 + j61;
        int i43 = ((int) j73) & 268435455;
        long j74 = j73 >>> 28;
        long j75 = i5;
        long j76 = (j62 * j5) + (j75 * j43);
        long j77 = j4 * j45;
        long j78 = i21;
        long j79 = (j7 * j78) + j77;
        long j80 = i13;
        long j81 = (j65 * j17) + (j80 * j48);
        long j82 = j16 * j50;
        long j83 = i15 * 2;
        long j84 = (j18 * j83) + j82;
        long j85 = i30;
        long j86 = (j68 * j28) + (j85 * j53);
        long j87 = j56 * j27;
        long j88 = ((long) (i32 * 2)) & 4294967295L;
        long j89 = (j30 * j88) + j87;
        long j90 = (((j76 + j81) + j89) - j79) + j72;
        int i44 = ((int) j90) & 268435455;
        long j91 = j90 >>> 28;
        long j92 = ((j84 + j86) - j76) + j89 + j74;
        int i45 = ((int) j92) & 268435455;
        long j93 = j92 >>> 28;
        long j94 = (j62 * j62) + (j75 * j5) + (j13 * j43);
        long j95 = (j18 * j18) + (j83 * j16);
        long j96 = (j68 * j68) + (j85 * j28) + (j36 * j53);
        long j97 = (j30 * j30) + (j27 * j88);
        long j98 = (((j94 + ((j65 * j65) + ((j80 * j17) + (j23 * j48)))) + j97) - ((j7 * j7) + (j4 * j78))) + j91;
        int i46 = ((int) j98) & 268435455;
        long j99 = j98 >>> 28;
        long j100 = ((j95 + j96) - j94) + j97 + j93;
        int i47 = ((int) j100) & 268435455;
        long j101 = j100 >>> 28;
        long j102 = (j75 * j8) + (j13 * j5) + (j10 * j43);
        long j103 = (j80 * j19) + (j23 * j17) + (j21 * j48);
        long j104 = (j85 * j31) + (j36 * j28) + (j33 * j53);
        long j105 = (((long) (i33 * 2)) & 4294967295L) * j27;
        long j106 = (((j102 + j103) + j105) - (((long) i22) * j4)) + j99;
        int i48 = ((int) j106) & 268435455;
        long j107 = j106 >>> 28;
        long j108 = (((((long) (i16 * 2)) * j16) + j104) - j102) + j105 + j101;
        int i49 = ((int) j108) & 268435455;
        long j109 = j108 >>> 28;
        long j110 = (j75 * j75) + (j13 * j8) + (j10 * j5) + (j7 * j43);
        long j111 = (j85 * j85) + (j36 * j31) + (j33 * j28) + (j30 * j53);
        long j112 = j27 * j27;
        long j113 = (((j110 + ((j80 * j80) + ((j23 * j19) + ((j21 * j17) + (j18 * j48))))) + j112) - (j4 * j4)) + j107;
        int i50 = ((int) j113) & 268435455;
        long j114 = j113 >>> 28;
        long j115 = (((j16 * j16) + j111) - j110) + j112 + j109;
        long j116 = (j11 * j13) + (j10 * j8) + (j7 * j5) + (j4 * j43);
        long j117 = j21 * j19;
        long j118 = (j23 * j22) + j117 + (j18 * j17) + (j16 * j48);
        long j119 = j118 + j116 + j114;
        int i51 = ((int) j119) & 268435455;
        long j120 = (((j36 * j34) + ((j33 * j31) + ((j30 * j28) + (j27 * j53)))) - j116) + (j115 >>> 28);
        long j121 = j120 >>> 28;
        long j122 = (j119 >>> 28) + j121 + ((long) i39);
        long j123 = j121 + ((long) i38);
        iArr2[0] = ((int) j123) & 268435455;
        iArr2[1] = i40 + ((int) (j123 >>> 28));
        iArr2[2] = i42;
        iArr2[3] = i44;
        iArr2[4] = i46;
        iArr2[5] = i48;
        iArr2[6] = i50;
        iArr2[7] = i51;
        iArr2[8] = ((int) j122) & 268435455;
        iArr2[9] = i41 + ((int) (j122 >>> 28));
        iArr2[10] = i43;
        iArr2[11] = i45;
        iArr2[12] = i47;
        iArr2[13] = i49;
        iArr2[14] = ((int) j115) & 268435455;
        iArr2[15] = ((int) j120) & 268435455;
    }

    public static void T(long[] jArr, long j2, long j3, long[] jArr2, int i2) {
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
        long j8 = jArr[((int) j2) & 7];
        long j9 = 0;
        int i3 = 48;
        do {
            int i4 = (int) (j2 >>> i3);
            long j10 = (jArr[i4 & 7] ^ (jArr[(i4 >>> 3) & 7] << 3)) ^ (jArr[(i4 >>> 6) & 7] << 6);
            j8 ^= j10 << i3;
            j9 ^= j10 >>> (-i3);
            i3 -= 9;
        } while (i3 > 0);
        jArr2[i2] = 144115188075855871L & j8;
        jArr2[i2 + 1] = (((((j2 & 72198606942111744L) & ((j3 << 7) >> 63)) >>> 8) ^ j9) << 7) ^ (j8 >>> 57);
    }

    public static void U(byte[] bArr, byte[] bArr2, byte b, byte[] bArr3, int i2, byte[] bArr4) {
        if (b != 0) {
            throw new IllegalArgumentException("ctx");
        }
        fq0 fq0Var = new fq0();
        byte[] bArr5 = new byte[64];
        fq0Var.e(bArr, 0, 32);
        fq0Var.d(0, bArr5);
        byte[] bArr6 = new byte[32];
        C0(bArr5, bArr6);
        fq0Var.e(bArr5, 32, 32);
        fq0Var.e(bArr3, 0, i2);
        fq0Var.d(0, bArr5);
        byte[] bArrM0 = M0(bArr5);
        byte[] bArr7 = new byte[32];
        c4 c4Var = new c4();
        O0(bArrM0, c4Var);
        int[] iArr = new int[10];
        int[] iArr2 = new int[10];
        int[] iArr3 = new int[10];
        int[] iArr4 = new int[8];
        t40.A(0, 0, (int[]) c4Var.d, iArr3);
        t40.I0(iArr3);
        t40.N(0, 0, iArr3, iArr4);
        t40.N(5, 4, iArr3, iArr4);
        ma0.T(t40.t, iArr4, iArr4);
        t40.E(0, 0, iArr4, iArr2);
        t40.E(4, 5, iArr4, iArr2);
        iArr2[9] = iArr2[9] & 16777215;
        t40.C0(iArr2, (int[]) c4Var.b, iArr);
        t40.C0(iArr2, (int[]) c4Var.c, iArr2);
        t40.I0(iArr);
        t40.I0(iArr2);
        int[] iArr5 = new int[10];
        int[] iArr6 = new int[10];
        int[] iArr7 = new int[10];
        t40.e1(iArr, iArr6);
        t40.e1(iArr2, iArr7);
        t40.C0(iArr6, iArr7, iArr5);
        t40.k1(iArr6, iArr7, iArr6);
        t40.C0(iArr5, u, iArr5);
        iArr5[0] = iArr5[0] + 1;
        t40.a(iArr5, iArr6, iArr5);
        t40.I0(iArr5);
        t40.I0(iArr7);
        int iZ0 = (~t40.z0(iArr7)) & t40.z0(iArr5);
        t40.M(0, 0, bArr7, iArr2);
        t40.M(5, 16, bArr7, iArr2);
        bArr7[31] = (byte) (bArr7[31] | ((iArr[0] & 1) << 7));
        if (iZ0 == 0) {
            throw new IllegalStateException();
        }
        fq0Var.e(bArr7, 0, 32);
        fq0Var.e(bArr2, 0, 32);
        fq0Var.e(bArr3, 0, i2);
        fq0Var.d(0, bArr5);
        byte[] bArrM02 = M0(bArr5);
        int[] iArr8 = new int[16];
        w(bArrM0, iArr8);
        int[] iArr9 = new int[8];
        w(bArrM02, iArr9);
        int[] iArr10 = new int[8];
        w(bArr6, iArr10);
        fa0.n0(iArr9, iArr10, iArr8);
        byte[] bArr8 = new byte[64];
        for (int i3 = 0; i3 < 16; i3++) {
            t40.P(bArr8, iArr8[i3], i3 * 4);
        }
        byte[] bArrM03 = M0(bArr8);
        System.arraycopy(bArr7, 0, bArr4, 0, 32);
        System.arraycopy(bArrM03, 0, bArr4, 32, 32);
    }

    public static void V(long[] jArr, long[] jArr2) {
        qm.o(4, jArr, jArr2);
        jArr2[8] = qm.n((int) jArr[4]);
    }

    public static void V0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[12];
        o80.b0(iArr, iArr3);
        H0(iArr3, iArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x04df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean W(byte[] r62, defpackage.z8 r63, byte r64, byte[] r65, int r66) {
        /*
            Method dump skipped, instruction units count: 2751
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l21.W(byte[], z8, byte, byte[], int):boolean");
    }

    public static void W0(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[9];
        V(jArr, jArr3);
        I0(jArr3, jArr2);
    }

    public static void X(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[14];
        t(0, 0, iArr, iArr3);
        G0(iArr3, 1);
        G0(iArr3, -1);
        B(0, 0, iArr3, iArr4);
        B(8, 7, iArr3, iArr4);
        ma0.T(T, iArr4, iArr4);
        x(0, 0, iArr4, iArr2);
        x(7, 8, iArr4, iArr2);
    }

    public static void X0(int i2, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[12];
        o80.b0(iArr, iArr3);
        while (true) {
            H0(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                o80.b0(iArr2, iArr3);
            }
        }
    }

    public static void Y0(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        fa0.y0(iArr, iArr3);
        while (true) {
            J0(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                fa0.y0(iArr2, iArr3);
            }
        }
    }

    public static void Z(xr[] xrVarArr) {
        int length = xrVarArr.length;
        int[] iArr = new int[length * 10];
        int[] iArr2 = new int[10];
        t40.A(0, 0, xrVarArr[0].c, iArr2);
        t40.A(0, 0, iArr2, iArr);
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i3 >= length) {
                break;
            }
            t40.C0(iArr2, xrVarArr[i3].c, iArr2);
            t40.A(0, i3 * 10, iArr2, iArr);
            i2 = i3;
        }
        t40.a(iArr2, iArr2, iArr2);
        int[] iArr3 = new int[10];
        int[] iArr4 = new int[8];
        t40.A(0, 0, iArr2, iArr3);
        t40.I0(iArr3);
        t40.N(0, 0, iArr3, iArr4);
        t40.N(5, 4, iArr3, iArr4);
        ma0.U(t40.t, iArr4, iArr4);
        t40.E(0, 0, iArr4, iArr2);
        t40.E(4, 5, iArr4, iArr2);
        iArr2[9] = iArr2[9] & 16777215;
        int[] iArr5 = new int[10];
        while (i2 > 0) {
            int i4 = i2 - 1;
            t40.A(i4 * 10, 0, iArr, iArr5);
            t40.C0(iArr5, iArr2, iArr5);
            t40.C0(iArr2, xrVarArr[i2].c, iArr2);
            t40.A(0, 0, iArr5, xrVarArr[i2].c);
            i2 = i4;
        }
        t40.A(0, 0, iArr2, xrVarArr[0].c);
    }

    public static void Z0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[9];
        V(jArr, jArr3);
        while (true) {
            I0(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                V(jArr2, jArr3);
            }
        }
    }

    public static boolean a0(int i2, Object obj) {
        int arity;
        if (obj instanceof c10) {
            if (obj instanceof k10) {
                arity = ((k10) obj).getArity();
            } else if (obj instanceof a10) {
                arity = 0;
            } else if (obj instanceof b10) {
                arity = 1;
            } else if (obj instanceof d10) {
                arity = 2;
            } else if (obj instanceof e10) {
                arity = 3;
            } else if (obj instanceof f10) {
                arity = 4;
            } else if (obj instanceof g10) {
                arity = 5;
            } else if (obj instanceof h10) {
                arity = 6;
            } else if (obj instanceof i10) {
                arity = 7;
            } else {
                boolean z2 = obj instanceof FunctionImpl;
                arity = z2 ? 8 : z2 ? 9 : z2 ? 10 : z2 ? 11 : z2 ? 12 : z2 ? 13 : z2 ? 14 : z2 ? 15 : z2 ? 16 : z2 ? 17 : z2 ? 18 : z2 ? 19 : z2 ? 20 : z2 ? 21 : z2 ? 22 : -1;
            }
            if (arity == i2) {
                return true;
            }
        }
        return false;
    }

    public static int c0(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void c1(int[] iArr, int[] iArr2, int[] iArr3) {
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
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        int i18 = iArr2[0];
        int i19 = iArr2[1];
        int i20 = iArr2[2];
        int i21 = iArr2[3];
        int i22 = iArr2[4];
        int i23 = iArr2[5];
        int i24 = iArr2[6];
        int i25 = iArr2[7];
        int i26 = iArr2[8];
        int i27 = iArr2[9];
        int i28 = iArr2[10];
        int i29 = iArr2[11];
        int i30 = iArr2[12];
        int i31 = iArr2[13];
        int i32 = (i3 + 536870910) - i19;
        int i33 = (i7 + 536870910) - i23;
        int i34 = (i11 + 536870910) - i27;
        int i35 = (i15 + 536870910) - i31;
        int i36 = ((i4 + 536870910) - i20) + (i32 >>> 28);
        int i37 = ((i8 + 536870910) - i24) + (i33 >>> 28);
        int i38 = ((i12 + 536870910) - i28) + (i34 >>> 28);
        int i39 = ((i16 + 536870910) - iArr2[14]) + (i35 >>> 28);
        int i40 = ((i5 + 536870910) - i21) + (i36 >>> 28);
        int i41 = ((i9 + 536870910) - i25) + (i37 >>> 28);
        int i42 = ((i13 + 536870910) - i29) + (i38 >>> 28);
        int i43 = ((i17 + 536870910) - iArr2[15]) + (i39 >>> 28);
        int i44 = i43 >>> 28;
        int i45 = ((i2 + 536870910) - i18) + i44;
        int i46 = ((i6 + 536870910) - i22) + (i40 >>> 28);
        int i47 = ((i10 + 536870908) - i26) + i44 + (i41 >>> 28);
        int i48 = ((i14 + 536870910) - i30) + (i42 >>> 28);
        iArr3[0] = i45 & 268435455;
        iArr3[1] = (i32 & 268435455) + (i45 >>> 28);
        iArr3[2] = i36 & 268435455;
        iArr3[3] = i40 & 268435455;
        iArr3[4] = i46 & 268435455;
        iArr3[5] = (i33 & 268435455) + (i46 >>> 28);
        iArr3[6] = i37 & 268435455;
        iArr3[7] = i41 & 268435455;
        iArr3[8] = i47 & 268435455;
        iArr3[9] = (i34 & 268435455) + (i47 >>> 28);
        iArr3[10] = i38 & 268435455;
        iArr3[11] = i42 & 268435455;
        iArr3[12] = i48 & 268435455;
        iArr3[13] = (i35 & 268435455) + (i48 >>> 28);
        iArr3[14] = i39 & 268435455;
        iArr3[15] = i43 & 268435455;
    }

    public static final StackTraceElement d(String str, Exception exc) {
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        return new StackTraceElement("_COROUTINE.".concat(str), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
    }

    public static int e0(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void e1(int[] iArr, int[] iArr2, int[] iArr3) {
        if (o80.d0(iArr, iArr2, iArr3) != 0) {
            fa0.E0(6, 4553, iArr3);
        }
    }

    public static void f(int[] iArr, int[] iArr2, int[] iArr3) {
        for (int i2 = 0; i2 < 16; i2++) {
            iArr3[i2] = iArr[i2] + iArr2[i2];
        }
    }

    public static int f0(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static void f1(int[] iArr, int[] iArr2, int[] iArr3) {
        if (fa0.C0(iArr, iArr2, iArr3) != 0) {
            long j2 = (((long) iArr3[0]) & 4294967295L) - 1;
            iArr3[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (((long) iArr3[1]) & 4294967295L);
                iArr3[1] = (int) j4;
                long j5 = (j4 >> 32) + (((long) iArr3[2]) & 4294967295L);
                iArr3[2] = (int) j5;
                j3 = j5 >> 32;
            }
            long j6 = (((long) iArr3[3]) & 4294967295L) + 1 + j3;
            iArr3[3] = (int) j6;
            long j7 = j6 >> 32;
            if (j7 != 0) {
                long j8 = j7 + (((long) iArr3[4]) & 4294967295L);
                iArr3[4] = (int) j8;
                long j9 = (j8 >> 32) + (((long) iArr3[5]) & 4294967295L);
                iArr3[5] = (int) j9;
                j7 = j9 >> 32;
            }
            long j10 = (((long) iArr3[6]) & 4294967295L) + 1 + j7;
            iArr3[6] = (int) j10;
            iArr3[7] = (int) (((((long) iArr3[7]) & 4294967295L) - 1) + (j10 >> 32));
        }
    }

    public static void g(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr[6] ^ jArr2[6];
        jArr3[7] = jArr[7] ^ jArr2[7];
        jArr3[8] = jArr2[8] ^ jArr[8];
    }

    public static List g0(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        t40.m(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    public static void i(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j4;
            long j5 = (j4 >> 32) + (((long) iArr[2]) & 4294967295L);
            iArr[2] = (int) j5;
            j3 = j5 >> 32;
        }
        long j6 = ((((long) iArr[3]) & 4294967295L) - 1) + j3;
        iArr[3] = (int) j6;
        long j7 = j6 >> 32;
        if (j7 != 0) {
            long j8 = j7 + (((long) iArr[4]) & 4294967295L);
            iArr[4] = (int) j8;
            long j9 = (j8 >> 32) + (((long) iArr[5]) & 4294967295L);
            iArr[5] = (int) j9;
            j7 = j9 >> 32;
        }
        long j10 = ((((long) iArr[6]) & 4294967295L) - 1) + j7;
        iArr[6] = (int) j10;
        iArr[7] = (int) ((4294967295L & ((long) iArr[7])) + 1 + (j10 >> 32));
    }

    public static void i0(int[] iArr, int[] iArr2) {
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
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        long j2 = 39081;
        long j3 = ((long) i3) * j2;
        int i18 = ((int) j3) & 268435455;
        long j4 = j3 >>> 28;
        long j5 = ((long) i7) * j2;
        int i19 = ((int) j5) & 268435455;
        long j6 = j5 >>> 28;
        long j7 = ((long) i11) * j2;
        int i20 = ((int) j7) & 268435455;
        long j8 = j7 >>> 28;
        long j9 = ((long) i15) * j2;
        int i21 = ((int) j9) & 268435455;
        long j10 = j9 >>> 28;
        long j11 = (((long) i4) * j2) + j4;
        iArr2[2] = ((int) j11) & 268435455;
        long j12 = j11 >>> 28;
        long j13 = (((long) i8) * j2) + j6;
        iArr2[6] = ((int) j13) & 268435455;
        long j14 = j13 >>> 28;
        long j15 = (((long) i12) * j2) + j8;
        iArr2[10] = ((int) j15) & 268435455;
        long j16 = (((long) i16) * j2) + j10;
        iArr2[14] = ((int) j16) & 268435455;
        long j17 = j16 >>> 28;
        long j18 = (((long) i5) * j2) + j12;
        iArr2[3] = ((int) j18) & 268435455;
        long j19 = (((long) i9) * j2) + j14;
        iArr2[7] = ((int) j19) & 268435455;
        long j20 = (((long) i13) * j2) + (j15 >>> 28);
        iArr2[11] = ((int) j20) & 268435455;
        long j21 = j20 >>> 28;
        long j22 = (((long) i17) * j2) + j17;
        iArr2[15] = ((int) j22) & 268435455;
        long j23 = j22 >>> 28;
        long j24 = (((long) i6) * j2) + (j18 >>> 28);
        iArr2[4] = ((int) j24) & 268435455;
        long j25 = j24 >>> 28;
        long j26 = (((long) i10) * j2) + (j19 >>> 28) + j23;
        iArr2[8] = ((int) j26) & 268435455;
        long j27 = (((long) i14) * j2) + j21;
        iArr2[12] = ((int) j27) & 268435455;
        long j28 = j27 >>> 28;
        long j29 = (((long) i2) * j2) + j23;
        iArr2[0] = ((int) j29) & 268435455;
        iArr2[1] = i18 + ((int) (j29 >>> 28));
        iArr2[5] = i19 + ((int) j25);
        iArr2[9] = i20 + ((int) (j26 >>> 28));
        iArr2[13] = i21 + ((int) j28);
    }

    public static void j(Throwable th, Throwable th2) {
        t40.n(th, "<this>");
        t40.n(th2, "exception");
        if (th != th2) {
            Integer num = b50.a;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = wk0.a;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static void j0(int[] iArr, int[] iArr2, int[] iArr3) {
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
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = iArr[15];
        int i18 = iArr2[0];
        int i19 = iArr2[1];
        int i20 = iArr2[2];
        int i21 = iArr2[3];
        int i22 = iArr2[4];
        int i23 = iArr2[5];
        int i24 = iArr2[6];
        int i25 = iArr2[7];
        int i26 = iArr2[8];
        int i27 = iArr2[9];
        int i28 = iArr2[10];
        int i29 = iArr2[11];
        int i30 = iArr2[12];
        int i31 = iArr2[13];
        int i32 = iArr2[14];
        int i33 = iArr2[15];
        int i34 = i2 + i10;
        int i35 = i3 + i11;
        int i36 = i4 + i12;
        int i37 = i5 + i13;
        int i38 = i6 + i14;
        int i39 = i7 + i15;
        int i40 = i8 + i16;
        int i41 = i9 + i17;
        int i42 = i18 + i26;
        int i43 = i19 + i27;
        int i44 = i20 + i28;
        int i45 = i21 + i29;
        int i46 = i22 + i30;
        int i47 = i23 + i31;
        int i48 = i24 + i32;
        int i49 = i25 + i33;
        long j2 = i2;
        long j3 = i18;
        long j4 = j2 * j3;
        long j5 = i9;
        long j6 = i19;
        long j7 = j5 * j6;
        long j8 = i8;
        long j9 = i20;
        long j10 = (j8 * j9) + j7;
        long j11 = i7;
        long j12 = i21;
        long j13 = i6;
        long j14 = i22;
        long j15 = i5;
        long j16 = i23;
        long j17 = (j15 * j16) + (j13 * j14) + (j11 * j12) + j10;
        long j18 = i4;
        long j19 = i24;
        long j20 = i3;
        long j21 = i25;
        long j22 = j20 * j21;
        long j23 = i10;
        long j24 = i26;
        long j25 = j23 * j24;
        long j26 = i17;
        long j27 = i27;
        long j28 = j26 * j27;
        long j29 = i16;
        long j30 = i28;
        long j31 = (j29 * j30) + j28;
        long j32 = i15;
        long j33 = i29;
        long j34 = (j32 * j33) + j31;
        long j35 = i14;
        long j36 = i30;
        long j37 = (j35 * j36) + j34;
        long j38 = i13;
        long j39 = i31;
        long j40 = (j38 * j39) + j37;
        long j41 = i12;
        long j42 = i32;
        long j43 = (j41 * j42) + j40;
        long j44 = i11;
        long j45 = i33;
        long j46 = (j44 * j45) + j43;
        long j47 = i34;
        long j48 = i42;
        long j49 = j47 * j48;
        long j50 = i41;
        long j51 = i43;
        long j52 = j50 * j51;
        long j53 = i40;
        long j54 = i44;
        long j55 = (j53 * j54) + j52;
        long j56 = i39;
        long j57 = i45;
        long j58 = (j56 * j57) + j55;
        long j59 = i38;
        long j60 = i46;
        long j61 = (j59 * j60) + j58;
        long j62 = i37;
        long j63 = i47;
        long j64 = (j62 * j63) + j61;
        long j65 = i36;
        long j66 = i48;
        long j67 = (j65 * j66) + j64;
        long j68 = i35;
        long j69 = i49;
        long j70 = (j68 * j69) + j67;
        long j71 = ((j4 + j25) + j70) - (j22 + ((j18 * j19) + j17));
        int i50 = ((int) j71) & 268435455;
        long j72 = ((j46 + j49) - j4) + j70;
        int i51 = ((int) j72) & 268435455;
        long j73 = j72 >>> 28;
        long j74 = (j2 * j6) + (j20 * j3);
        long j75 = (j41 * j45) + (j38 * j42) + (j35 * j39) + (j32 * j36) + (j29 * j33) + (j26 * j30);
        long j76 = (j47 * j51) + (j68 * j48);
        long j77 = (j65 * j69) + (j62 * j66) + (j59 * j63) + (j56 * j60) + (j53 * j57) + (j50 * j54);
        long j78 = (((j74 + ((j23 * j27) + (j44 * j24))) + j77) - ((j18 * j21) + ((j15 * j19) + ((j13 * j16) + ((j11 * j14) + ((j8 * j12) + (j5 * j9))))))) + (j71 >>> 28);
        int i52 = ((int) j78) & 268435455;
        long j79 = j78 >>> 28;
        long j80 = ((j75 + j76) - j74) + j77 + j73;
        int i53 = ((int) j80) & 268435455;
        long j81 = j80 >>> 28;
        long j82 = (j2 * j9) + (j20 * j6) + (j18 * j3);
        long j83 = (j38 * j45) + (j35 * j42) + (j32 * j39) + (j29 * j36) + (j26 * j33);
        long j84 = (j47 * j54) + (j68 * j51) + (j65 * j48);
        long j85 = (j62 * j69) + (j59 * j66) + (j56 * j63) + (j53 * j60) + (j50 * j57);
        long j86 = (((j82 + ((j23 * j30) + ((j44 * j27) + (j41 * j24)))) + j85) - ((j15 * j21) + ((j13 * j19) + ((j11 * j16) + ((j8 * j14) + (j5 * j12)))))) + j79;
        int i54 = ((int) j86) & 268435455;
        long j87 = j86 >>> 28;
        long j88 = ((j83 + j84) - j82) + j85 + j81;
        int i55 = ((int) j88) & 268435455;
        long j89 = j88 >>> 28;
        long j90 = (j2 * j12) + (j20 * j9) + (j18 * j6) + (j15 * j3);
        long j91 = (j35 * j45) + (j32 * j42) + (j29 * j39) + (j26 * j36);
        long j92 = (j47 * j57) + (j68 * j54) + (j65 * j51) + (j62 * j48);
        long j93 = (j59 * j69) + (j56 * j66) + (j53 * j63) + (j50 * j60);
        long j94 = (((j90 + ((j23 * j33) + ((j44 * j30) + ((j41 * j27) + (j38 * j24))))) + j93) - ((j13 * j21) + ((j11 * j19) + ((j8 * j16) + (j5 * j14))))) + j87;
        int i56 = ((int) j94) & 268435455;
        long j95 = j94 >>> 28;
        long j96 = ((j91 + j92) - j90) + j93 + j89;
        int i57 = ((int) j96) & 268435455;
        long j97 = j96 >>> 28;
        long j98 = (j2 * j14) + (j20 * j12) + (j18 * j9) + (j15 * j6) + (j13 * j3);
        long j99 = (j32 * j45) + (j29 * j42) + (j26 * j39);
        long j100 = (j47 * j60) + (j68 * j57) + (j65 * j54) + (j62 * j51) + (j59 * j48);
        long j101 = (j56 * j69) + (j53 * j66) + (j50 * j63);
        long j102 = (((j98 + ((j23 * j36) + ((j44 * j33) + ((j41 * j30) + ((j38 * j27) + (j35 * j24)))))) + j101) - ((j11 * j21) + ((j8 * j19) + (j5 * j16)))) + j95;
        int i58 = ((int) j102) & 268435455;
        long j103 = j102 >>> 28;
        long j104 = ((j99 + j100) - j98) + j101 + j97;
        int i59 = ((int) j104) & 268435455;
        long j105 = j104 >>> 28;
        long j106 = (j2 * j16) + (j20 * j14) + (j18 * j12) + (j15 * j9) + (j13 * j6) + (j11 * j3);
        long j107 = (j29 * j45) + (j26 * j42);
        long j108 = (j47 * j63) + (j68 * j60) + (j65 * j57) + (j62 * j54) + (j59 * j51) + (j56 * j48);
        long j109 = (j53 * j69) + (j50 * j66);
        long j110 = (((j106 + ((j23 * j39) + ((j44 * j36) + ((j41 * j33) + ((j38 * j30) + ((j35 * j27) + (j32 * j24))))))) + j109) - ((j8 * j21) + (j5 * j19))) + j103;
        int i60 = ((int) j110) & 268435455;
        long j111 = j110 >>> 28;
        long j112 = ((j107 + j108) - j106) + j109 + j105;
        int i61 = ((int) j112) & 268435455;
        long j113 = j112 >>> 28;
        long j114 = (j2 * j19) + (j20 * j16) + (j18 * j14) + (j15 * j12) + (j13 * j9) + (j11 * j6) + (j8 * j3);
        long j115 = j26 * j45;
        long j116 = (j47 * j66) + (j68 * j63) + (j65 * j60) + (j62 * j57) + (j59 * j54) + (j56 * j51) + (j53 * j48);
        long j117 = j50 * j69;
        long j118 = (((j114 + ((j23 * j42) + ((j44 * j39) + ((j41 * j36) + ((j38 * j33) + ((j35 * j30) + ((j32 * j27) + (j29 * j24)))))))) + j117) - (j5 * j21)) + j111;
        int i62 = ((int) j118) & 268435455;
        long j119 = j118 >>> 28;
        long j120 = ((j115 + j116) - j114) + j117 + j113;
        long j121 = j15 * j14;
        long j122 = (j2 * j21) + (j20 * j19) + (j18 * j16) + j121 + (j13 * j12) + (j11 * j9) + (j8 * j6) + (j3 * j5);
        long j123 = (j23 * j45) + (j44 * j42) + (j41 * j39) + (j38 * j36) + (j35 * j33) + (j32 * j30) + (j29 * j27) + (j26 * j24) + j122 + j119;
        int i63 = ((int) j123) & 268435455;
        long j124 = (((j47 * j69) + ((j68 * j66) + ((j65 * j63) + ((j62 * j60) + ((j59 * j57) + ((j56 * j54) + ((j53 * j51) + (j50 * j48)))))))) - j122) + (j120 >>> 28);
        long j125 = j124 >>> 28;
        long j126 = (j123 >>> 28) + j125 + ((long) i51);
        long j127 = j125 + ((long) i50);
        iArr3[0] = ((int) j127) & 268435455;
        iArr3[1] = i52 + ((int) (j127 >>> 28));
        iArr3[2] = i54;
        iArr3[3] = i56;
        iArr3[4] = i58;
        iArr3[5] = i60;
        iArr3[6] = i62;
        iArr3[7] = i63;
        iArr3[8] = ((int) j126) & 268435455;
        iArr3[9] = i53 + ((int) (j126 >>> 28));
        iArr3[10] = i55;
        iArr3[11] = i57;
        iArr3[12] = i59;
        iArr3[13] = i61;
        iArr3[14] = ((int) j120) & 268435455;
        iArr3[15] = ((int) j124) & 268435455;
    }

    /* JADX WARN: Removed duplicated region for block: B:178:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0670 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x06a0  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x06b4  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x06b8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:419:0x06d3 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void k(defpackage.zh r39, defpackage.j90 r40, int r41) {
        /*
            Method dump skipped, instruction units count: 1762
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l21.k(zh, j90, int):void");
    }

    public static void l(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            if (colorStateList == null || !colorStateList.isStateful()) {
                ip.h(drawable, colorStateList);
            } else {
                int[] drawableState = textInputLayout.getDrawableState();
                int[] drawableState2 = checkableImageButton.getDrawableState();
                int length = drawableState.length;
                int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
                System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
                ip.h(drawable, ColorStateList.valueOf(colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor())));
            }
            if (mode != null) {
                ip.i(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public static void l0(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[12];
        o80.T(iArr, iArr2, iArr4);
        H0(iArr4, iArr3);
    }

    public static void m(int i2, Object obj) {
        if (obj == null || a0(i2, obj)) {
            return;
        }
        String strJ = zn.j(i2, "kotlin.jvm.functions.Function");
        ClassCastException classCastException = new ClassCastException(obj.getClass().getName() + " cannot be cast to " + strJ);
        t40.c1(classCastException, l21.class.getName());
        throw classCastException;
    }

    public static void m0(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        fa0.m0(iArr, iArr2, iArr4);
        J0(iArr4, iArr3);
    }

    public static void n0(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[10];
        S(jArr, jArr2, jArr4);
        I0(jArr4, jArr3);
    }

    public static int o(int i2, int i3, int i4) {
        int i5 = i3 & ((i2 >>> i4) ^ i2);
        return i2 ^ (i5 ^ (i5 << i4));
    }

    public static long p(long j2, int i2, long j3) {
        long j4 = j3 & ((j2 >>> i2) ^ j2);
        return j2 ^ (j4 ^ (j4 << i2));
    }

    public static void q(int[] iArr) {
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
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = i3 + (i2 >>> 28);
        int i17 = i7 + (i6 >>> 28);
        int i18 = i11 + (i10 >>> 28);
        int i19 = i15 + (i14 >>> 28);
        int i20 = i4 + (i16 >>> 28);
        int i21 = i8 + (i17 >>> 28);
        int i22 = i12 + (i18 >>> 28);
        int i23 = iArr[14] + (i19 >>> 28);
        int i24 = i5 + (i20 >>> 28);
        int i25 = i9 + (i21 >>> 28);
        int i26 = i13 + (i22 >>> 28);
        int i27 = iArr[15] + (i23 >>> 28);
        int i28 = i27 >>> 28;
        int i29 = (i2 & 268435455) + i28;
        int i30 = (i6 & 268435455) + (i24 >>> 28);
        int i31 = (i10 & 268435455) + i28 + (i25 >>> 28);
        int i32 = (i14 & 268435455) + (i26 >>> 28);
        iArr[0] = i29 & 268435455;
        iArr[1] = (i16 & 268435455) + (i29 >>> 28);
        iArr[2] = i20 & 268435455;
        iArr[3] = i24 & 268435455;
        iArr[4] = i30 & 268435455;
        iArr[5] = (i17 & 268435455) + (i30 >>> 28);
        iArr[6] = i21 & 268435455;
        iArr[7] = i25 & 268435455;
        iArr[8] = i31 & 268435455;
        iArr[9] = (i18 & 268435455) + (i31 >>> 28);
        iArr[10] = i22 & 268435455;
        iArr[11] = i26 & 268435455;
        iArr[12] = i32 & 268435455;
        iArr[13] = (i19 & 268435455) + (i32 >>> 28);
        iArr[14] = i23 & 268435455;
        iArr[15] = i27 & 268435455;
    }

    public static void r(int i2, int i3, int[] iArr, int[] iArr2) {
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = iArr2[i4];
            iArr2[i4] = i5 ^ ((iArr[i3 + i4] ^ i5) & i2);
        }
    }

    public static float r0(EdgeEffect edgeEffect, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 31) {
            return hs.c(edgeEffect, f, f2);
        }
        gs.a(edgeEffect, f, f2);
        return f;
    }

    public static ImageView.ScaleType s(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 5 ? i2 != 6 ? ImageView.ScaleType.CENTER : ImageView.ScaleType.CENTER_INSIDE : ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.FIT_END : ImageView.ScaleType.FIT_CENTER : ImageView.ScaleType.FIT_START : ImageView.ScaleType.FIT_XY;
    }

    public static kz s0(XmlResourceParser xmlResourceParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        xmlResourceParser.require(2, null, "font-family");
        if (!xmlResourceParser.getName().equals("font-family")) {
            Q0(xmlResourceParser);
            return null;
        }
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.FontFamily);
        String string = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderAuthority);
        String string2 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderPackage);
        String string3 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderQuery);
        int resourceId = typedArrayObtainAttributes.getResourceId(R$styleable.FontFamily_fontProviderCerts, 0);
        int integer = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = typedArrayObtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchTimeout, BaseConsumeOptions.DEFAULT_MESSAGE_COUNT);
        String string4 = typedArrayObtainAttributes.getString(R$styleable.FontFamily_fontProviderSystemFontFamily);
        typedArrayObtainAttributes.recycle();
        if (string != null && string2 != null && string3 != null) {
            while (xmlResourceParser.next() != 3) {
                Q0(xmlResourceParser);
            }
            return new nz(new c4(string, string2, string3, E0(resources, resourceId)), integer, integer2, string4);
        }
        ArrayList arrayList = new ArrayList();
        while (xmlResourceParser.next() != 3) {
            if (xmlResourceParser.getEventType() == 2) {
                if (xmlResourceParser.getName().equals("font")) {
                    TypedArray typedArrayObtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.FontFamilyFont);
                    int i2 = typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_fontWeight) ? R$styleable.FontFamilyFont_fontWeight : R$styleable.FontFamilyFont_android_fontWeight, Status.BAD_REQUEST_CODE);
                    boolean z2 = 1 == typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_fontStyle) ? R$styleable.FontFamilyFont_fontStyle : R$styleable.FontFamilyFont_android_fontStyle, 0);
                    int i3 = typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_ttcIndex) ? R$styleable.FontFamilyFont_ttcIndex : R$styleable.FontFamilyFont_android_ttcIndex;
                    String string5 = typedArrayObtainAttributes2.getString(typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_fontVariationSettings) ? R$styleable.FontFamilyFont_fontVariationSettings : R$styleable.FontFamilyFont_android_fontVariationSettings);
                    int i4 = typedArrayObtainAttributes2.getInt(i3, 0);
                    int i5 = typedArrayObtainAttributes2.hasValue(R$styleable.FontFamilyFont_font) ? R$styleable.FontFamilyFont_font : R$styleable.FontFamilyFont_android_font;
                    int resourceId2 = typedArrayObtainAttributes2.getResourceId(i5, 0);
                    String string6 = typedArrayObtainAttributes2.getString(i5);
                    typedArrayObtainAttributes2.recycle();
                    while (xmlResourceParser.next() != 3) {
                        Q0(xmlResourceParser);
                    }
                    arrayList.add(new mz(i2, i4, resourceId2, string6, string5, z2));
                } else {
                    Q0(xmlResourceParser);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new lz((mz[]) arrayList.toArray(new mz[0]));
    }

    public static void t(int i2, int i3, int[] iArr, int[] iArr2) {
        for (int i4 = 0; i4 < 16; i4++) {
            iArr2[i3 + i4] = iArr[i2 + i4];
        }
    }

    public static ez u(Context context) {
        ProviderInfo providerInfo;
        c4 c4Var;
        ApplicationInfo applicationInfo;
        di umVar = Build.VERSION.SDK_INT >= 28 ? new um(6) : new di(6);
        PackageManager packageManager = context.getPackageManager();
        la0.i(packageManager, "Package manager required to locate emoji font provider");
        Iterator<ResolveInfo> it = packageManager.queryIntentContentProviders(new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfo = null;
                break;
            }
            providerInfo = it.next().providerInfo;
            if (providerInfo != null && (applicationInfo = providerInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfo == null) {
            c4Var = null;
        } else {
            try {
                String str = providerInfo.authority;
                String str2 = providerInfo.packageName;
                Signature[] signatureArrF = umVar.f(packageManager, str2);
                ArrayList arrayList = new ArrayList();
                for (Signature signature : signatureArrF) {
                    arrayList.add(signature.toByteArray());
                }
                c4Var = new c4(str, str2, "emojicompat-emoji-font", Collections.singletonList(arrayList));
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e);
                c4Var = null;
            }
        }
        if (c4Var == null) {
            return null;
        }
        return new ez(new dz(context, c4Var));
    }

    public static void u0(Context context, String str) {
        if (str.equals("")) {
            context.deleteFile("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file");
            return;
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput("androidx.appcompat.app.AppCompatDelegate.application_locales_record_file", 0);
            XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
            try {
                try {
                    try {
                        xmlSerializerNewSerializer.setOutput(fileOutputStreamOpenFileOutput, null);
                        xmlSerializerNewSerializer.startDocument("UTF-8", Boolean.TRUE);
                        xmlSerializerNewSerializer.startTag(null, "locales");
                        xmlSerializerNewSerializer.attribute(null, "application_locales", str);
                        xmlSerializerNewSerializer.endTag(null, "locales");
                        xmlSerializerNewSerializer.endDocument();
                        Log.d("AppLocalesStorageHelper", "Storing App Locales : app-locales: " + str + " persisted successfully.");
                        if (fileOutputStreamOpenFileOutput != null) {
                            fileOutputStreamOpenFileOutput.close();
                        }
                    } catch (Throwable th) {
                        if (fileOutputStreamOpenFileOutput != null) {
                            try {
                                fileOutputStreamOpenFileOutput.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception e) {
                    Log.w("AppLocalesStorageHelper", "Storing App Locales : Failed to persist app-locales: ".concat(str), e);
                    if (fileOutputStreamOpenFileOutput != null) {
                        fileOutputStreamOpenFileOutput.close();
                    }
                }
            } catch (IOException unused2) {
            }
        } catch (FileNotFoundException unused3) {
            Log.w("AppLocalesStorageHelper", "Storing App Locales : FileNotFoundException: Cannot open file androidx.appcompat.app.AppCompatDelegate.application_locales_record_file for writing ");
        }
    }

    public static po v(d0 d0Var, int i2) {
        po gq0Var;
        if (d0Var.k(yf0.a)) {
            gq0Var = new cq0();
        } else {
            if (!d0Var.k(yf0.m)) {
                throw new IllegalArgumentException("unrecognized digest OID: " + d0Var);
            }
            gq0Var = new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        }
        return (yf0.m.k(d0Var) || gq0Var.c() != i2) ? new qo(i2, gq0Var) : gq0Var;
    }

    public static void v0(xr xrVar, xr xrVar2, xr xrVar3, wr wrVar) {
        int[] iArr = xrVar3.a;
        int[] iArr2 = wrVar.a;
        int[] iArr3 = xrVar.b;
        int[] iArr4 = xrVar.a;
        int[] iArr5 = xrVar3.b;
        t40.e(iArr3, iArr4, iArr5, iArr);
        int[] iArr6 = xrVar2.b;
        int[] iArr7 = xrVar2.a;
        int[] iArr8 = wrVar.b;
        t40.e(iArr6, iArr7, iArr8, iArr2);
        t40.C0(iArr, iArr2, iArr);
        t40.C0(iArr5, iArr8, iArr5);
        t40.C0(xrVar.d, xrVar2.d, iArr2);
        t40.C0(iArr2, v, iArr2);
        int[] iArr9 = xrVar.c;
        t40.a(iArr9, iArr9, iArr8);
        t40.C0(iArr8, xrVar2.c, iArr8);
        t40.e(iArr5, iArr, iArr5, iArr);
        t40.e(iArr8, iArr2, iArr8, iArr2);
        t40.C0(iArr, iArr5, xrVar3.d);
        t40.C0(iArr2, iArr8, xrVar3.c);
        t40.C0(iArr, iArr2, xrVar3.a);
        t40.C0(iArr5, iArr8, iArr5);
    }

    public static void w(byte[] bArr, int[] iArr) {
        t40.H(8, bArr, iArr);
    }

    public static void w0(boolean z2, xr xrVar, c4 c4Var, wr wrVar) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3 = (int[]) c4Var.b;
        int[] iArr4 = wrVar.a;
        int[] iArr5 = (int[]) c4Var.c;
        if (z2) {
            iArr2 = iArr3;
            iArr = iArr5;
        } else {
            iArr = iArr3;
            iArr2 = iArr5;
        }
        t40.e(iArr5, iArr3, iArr5, iArr3);
        t40.C0(iArr, xrVar.a, iArr);
        t40.C0(iArr2, xrVar.b, iArr2);
        int[] iArr6 = (int[]) c4Var.e;
        int[] iArr7 = (int[]) c4Var.f;
        t40.C0(iArr6, iArr7, iArr4);
        t40.C0(iArr4, xrVar.c, iArr4);
        int[] iArr8 = xrVar.d;
        int[] iArr9 = (int[]) c4Var.d;
        t40.C0(iArr9, iArr8, iArr9);
        t40.e(iArr5, iArr3, iArr7, iArr6);
        t40.e(iArr9, iArr4, iArr2, iArr);
        t40.C0(iArr3, iArr5, iArr9);
        t40.C0(iArr3, iArr6, (int[]) c4Var.b);
        t40.C0(iArr5, iArr7, iArr5);
    }

    public static void x(int i2, int i3, int[] iArr, int[] iArr2) {
        int i4 = iArr[i2];
        int i5 = iArr[i2 + 1];
        int i6 = iArr[i2 + 2];
        int i7 = iArr[i2 + 3];
        int i8 = iArr[i2 + 4];
        int i9 = iArr[i2 + 5];
        int i10 = iArr[i2 + 6];
        iArr2[i3] = i4 & 268435455;
        iArr2[i3 + 1] = ((i4 >>> 28) | (i5 << 4)) & 268435455;
        iArr2[i3 + 2] = ((i5 >>> 24) | (i6 << 8)) & 268435455;
        iArr2[i3 + 3] = ((i6 >>> 20) | (i7 << 12)) & 268435455;
        iArr2[i3 + 4] = ((i7 >>> 16) | (i8 << 16)) & 268435455;
        iArr2[i3 + 5] = ((i8 >>> 12) | (i9 << 20)) & 268435455;
        iArr2[i3 + 6] = 268435455 & ((i9 >>> 8) | (i10 << 24));
        iArr2[i3 + 7] = i10 >>> 4;
    }

    public static void x0(boolean z2, yr yrVar, c4 c4Var, wr wrVar) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3 = (int[]) c4Var.b;
        int[] iArr4 = wrVar.a;
        int[] iArr5 = (int[]) c4Var.c;
        if (z2) {
            iArr2 = iArr3;
            iArr = iArr5;
        } else {
            iArr = iArr3;
            iArr2 = iArr5;
        }
        t40.e(iArr5, iArr3, iArr5, iArr3);
        t40.C0(iArr, yrVar.a, iArr);
        t40.C0(iArr2, yrVar.b, iArr2);
        int[] iArr6 = (int[]) c4Var.e;
        int[] iArr7 = (int[]) c4Var.f;
        t40.C0(iArr6, iArr7, iArr4);
        t40.C0(iArr4, yrVar.c, iArr4);
        t40.e(iArr5, iArr3, iArr7, iArr6);
        int[] iArr8 = (int[]) c4Var.d;
        t40.e(iArr8, iArr4, iArr2, iArr);
        t40.C0(iArr3, iArr5, iArr8);
        t40.C0(iArr3, iArr6, (int[]) c4Var.b);
        t40.C0(iArr5, iArr7, iArr5);
    }

    public static void y(int i2, int i3, byte[] bArr, int[] iArr) {
        int i4 = (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | (bArr[i2 + 3] << 24);
        int i5 = ((bArr[i2 + 6] & 255) << 16) | (bArr[i2 + 4] & 255) | ((bArr[i2 + 5] & 255) << 8);
        iArr[i3] = 268435455 & i4;
        iArr[i3 + 1] = (i5 << 4) | (i4 >>> 28);
    }

    public static void y0(c4 c4Var, xr xrVar) {
        t40.A(0, 0, (int[]) c4Var.b, xrVar.a);
        t40.A(0, 0, (int[]) c4Var.c, xrVar.b);
        t40.A(0, 0, (int[]) c4Var.d, xrVar.c);
        t40.C0((int[]) c4Var.e, (int[]) c4Var.f, xrVar.d);
    }

    public static boolean z(byte[] bArr, boolean z2, wr wrVar) {
        int i2 = (bArr[31] & 128) >>> 7;
        int[] iArr = wrVar.b;
        t40.D(0, 0, bArr, iArr);
        t40.D(16, 5, bArr, iArr);
        iArr[9] = iArr[9] & 16777215;
        int[] iArr2 = new int[10];
        int[] iArr3 = new int[10];
        t40.e1(wrVar.b, iArr2);
        t40.C0(u, iArr2, iArr3);
        iArr2[0] = iArr2[0] - 1;
        iArr3[0] = iArr3[0] + 1;
        int[] iArr4 = new int[10];
        int[] iArr5 = new int[10];
        t40.C0(iArr2, iArr3, iArr4);
        t40.e1(iArr3, iArr5);
        t40.C0(iArr4, iArr5, iArr4);
        t40.e1(iArr5, iArr5);
        t40.C0(iArr5, iArr4, iArr5);
        int[] iArr6 = new int[10];
        int[] iArr7 = new int[10];
        t40.e1(iArr5, iArr6);
        t40.C0(iArr5, iArr6, iArr6);
        int[] iArr8 = new int[10];
        t40.e1(iArr6, iArr8);
        t40.C0(iArr5, iArr8, iArr8);
        t40.d1(2, iArr8, iArr8);
        t40.C0(iArr6, iArr8, iArr8);
        int[] iArr9 = new int[10];
        t40.d1(5, iArr8, iArr9);
        t40.C0(iArr8, iArr9, iArr9);
        int[] iArr10 = new int[10];
        t40.d1(5, iArr9, iArr10);
        t40.C0(iArr8, iArr10, iArr10);
        t40.d1(10, iArr10, iArr8);
        t40.C0(iArr9, iArr8, iArr8);
        t40.d1(25, iArr8, iArr9);
        t40.C0(iArr8, iArr9, iArr9);
        t40.d1(25, iArr9, iArr10);
        t40.C0(iArr8, iArr10, iArr10);
        t40.d1(50, iArr10, iArr8);
        t40.C0(iArr9, iArr8, iArr8);
        t40.d1(125, iArr8, iArr9);
        t40.C0(iArr8, iArr9, iArr9);
        t40.d1(2, iArr9, iArr8);
        t40.C0(iArr8, iArr5, iArr7);
        t40.C0(iArr7, iArr4, iArr7);
        int[] iArr11 = new int[10];
        t40.e1(iArr7, iArr11);
        t40.C0(iArr11, iArr3, iArr11);
        t40.k1(iArr11, iArr2, iArr6);
        t40.I0(iArr6);
        boolean z3 = t40.z0(iArr6) != 0;
        int[] iArr12 = wrVar.a;
        if (z3) {
            t40.A(0, 0, iArr7, iArr12);
        } else {
            t40.a(iArr11, iArr2, iArr6);
            t40.I0(iArr6);
            if (t40.z0(iArr6) == 0) {
                return false;
            }
            t40.C0(iArr7, t40.u, iArr12);
        }
        t40.I0(iArr12);
        if (i2 == 1 && t40.z0(iArr12) != 0) {
            return false;
        }
        if (z2 ^ (i2 != (iArr12[0] & 1))) {
            t40.H0(iArr12, iArr12);
            t40.I0(iArr12);
        }
        return true;
    }

    public static void z0(c4 c4Var) {
        int[] iArr = (int[]) c4Var.b;
        int[] iArr2 = (int[]) c4Var.c;
        int[] iArr3 = (int[]) c4Var.e;
        t40.a(iArr, iArr2, iArr3);
        int[] iArr4 = (int[]) c4Var.b;
        t40.e1(iArr4, iArr);
        t40.e1(iArr2, iArr2);
        int[] iArr5 = (int[]) c4Var.d;
        t40.e1(iArr5, iArr5);
        t40.a(iArr5, iArr5, iArr5);
        int[] iArr6 = (int[]) c4Var.f;
        t40.e(iArr, iArr2, iArr6, iArr2);
        t40.e1(iArr3, iArr3);
        t40.k1(iArr6, iArr3, iArr3);
        t40.a(iArr5, iArr2, iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = i4 + (i3 >> 26);
        int i12 = i6 + (i5 >> 26);
        int i13 = i9 + (i8 >> 26);
        int i14 = iArr[9] + (i10 >> 26);
        int i15 = (i5 & 67108863) + (i11 >> 25);
        int i16 = i7 + (i12 >> 25);
        int i17 = (i10 & 67108863) + (i13 >> 25);
        int i18 = ((i14 >> 25) * 38) + i2;
        int i19 = (i3 & 67108863) + (i18 >> 26);
        int i20 = (i8 & 67108863) + (i16 >> 26);
        iArr[0] = i18 & 67108863;
        iArr[1] = i19 & 67108863;
        iArr[2] = (i11 & 33554431) + (i19 >> 26);
        iArr[3] = i15 & 67108863;
        iArr[4] = (i12 & 33554431) + (i15 >> 26);
        iArr[5] = i16 & 67108863;
        iArr[6] = i20 & 67108863;
        iArr[7] = (i13 & 33554431) + (i20 >> 26);
        iArr[8] = i17 & 67108863;
        iArr[9] = (i14 & 33554431) + (i17 >> 26);
        t40.C0(iArr, iArr2, iArr5);
        t40.C0(iArr, iArr3, iArr4);
        t40.C0(iArr2, iArr6, iArr2);
    }

    public abstract l21 A(l21 l21Var);

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void E(int r6, byte[] r7) {
        /*
            r5 = this;
            java.math.BigInteger r0 = r5.h1()
            int r1 = r5.J()
            java.math.BigInteger r2 = defpackage.oa.a
            byte[] r0 = r0.toByteArray()
            int r2 = r0.length
            r3 = 0
            if (r2 != r1) goto L16
            java.lang.System.arraycopy(r0, r3, r7, r6, r1)
            return
        L16:
            r2 = r0[r3]
            if (r2 != 0) goto L1f
            int r2 = r0.length
            r4 = 1
            if (r2 == r4) goto L1f
            goto L20
        L1f:
            r4 = r3
        L20:
            int r2 = r0.length
            int r2 = r2 - r4
            if (r2 > r1) goto L2d
            int r1 = r1 - r2
            int r1 = r1 + r6
            java.util.Arrays.fill(r7, r6, r1, r3)
            java.lang.System.arraycopy(r0, r4, r7, r1, r2)
            return
        L2d:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "standard length exceeded for value"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l21.E(int, byte[]):void");
    }

    public int J() {
        return (N() + 7) / 8;
    }

    public abstract int N();

    public abstract int P();

    public abstract l21 T0();

    public abstract l21 U0();

    public abstract l21 Y();

    public l21 a1(l21 l21Var, l21 l21Var2) {
        return U0().e(l21Var.k0(l21Var2));
    }

    public boolean b0() {
        return n() == 1;
    }

    public l21 b1(int i2) {
        l21 l21VarU0 = this;
        for (int i3 = 0; i3 < i2; i3++) {
            l21VarU0 = l21VarU0.U0();
        }
        return l21VarU0;
    }

    public boolean d0() {
        return h1().signum() == 0;
    }

    public abstract l21 d1(l21 l21Var);

    public abstract l21 e(l21 l21Var);

    public boolean g1() {
        return h1().testBit(0);
    }

    public abstract l21 h();

    public abstract or h0(int i2);

    public abstract BigInteger h1();

    public abstract l21 k0(l21 l21Var);

    public int n() {
        return h1().bitLength();
    }

    public l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return k0(l21Var).d1(l21Var2.k0(l21Var3));
    }

    public l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return k0(l21Var).e(l21Var2.k0(l21Var3));
    }

    public abstract l21 q0();

    public abstract Object t0(Intent intent, int i2);

    public String toString() {
        switch (this.f) {
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                return h1().toString(16);
            default:
                return super.toString();
        }
    }
}
