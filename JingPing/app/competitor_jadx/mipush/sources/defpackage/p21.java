package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class p21 extends ma0 {
    public static final Class q;
    public static final Constructor r;
    public static final Method s;
    public static final Method t;

    static {
        Class<?> cls;
        Constructor<?> constructor;
        Method method;
        Method method2;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            Class cls2 = Integer.TYPE;
            method = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi24Impl", e.getClass().getName(), e);
            cls = null;
            constructor = null;
            method = null;
            method2 = null;
        }
        r = constructor;
        q = cls;
        s = method;
        t = method2;
    }

    public static boolean B0(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) s.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public static Typeface C0(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) q, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) t.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0068 A[SYNTHETIC] */
    @Override // defpackage.ma0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Typeface o(android.content.Context r17, defpackage.lz r18, android.content.res.Resources r19, int r20) {
        /*
            r16 = this;
            r1 = 0
            r0 = 0
            java.lang.reflect.Constructor r2 = defpackage.p21.r     // Catch: java.lang.Throwable -> Lb
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> Lb
            java.lang.Object r2 = r2.newInstance(r3)     // Catch: java.lang.Throwable -> Lb
            goto Lc
        Lb:
            r2 = r1
        Lc:
            if (r2 != 0) goto Lf
            goto L68
        Lf:
            r3 = r18
            mz[] r3 = r3.a
            int r4 = r3.length
            r5 = r0
        L15:
            if (r5 >= r4) goto L71
            r6 = r3[r5]
            int r0 = r6.f
            java.io.File r7 = defpackage.o80.C(r17)
            if (r7 != 0) goto L25
            r8 = r19
        L23:
            r0 = r1
            goto L59
        L25:
            r8 = r19
            boolean r0 = defpackage.o80.o(r7, r8, r0)     // Catch: java.lang.Throwable -> L6c
            if (r0 != 0) goto L31
            r7.delete()
            goto L23
        L31:
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L6c
            r9.<init>(r7)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L6c
            java.nio.channels.FileChannel r10 = r9.getChannel()     // Catch: java.lang.Throwable -> L4a
            long r14 = r10.size()     // Catch: java.lang.Throwable -> L4a
            java.nio.channels.FileChannel$MapMode r11 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L4a
            r12 = 0
            java.nio.MappedByteBuffer r0 = r10.map(r11, r12, r14)     // Catch: java.lang.Throwable -> L4a
            r9.close()     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L6c
            goto L56
        L4a:
            r0 = move-exception
            r10 = r0
            r9.close()     // Catch: java.lang.Throwable -> L50
            goto L54
        L50:
            r0 = move-exception
            r10.addSuppressed(r0)     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L6c
        L54:
            throw r10     // Catch: java.io.IOException -> L55 java.lang.Throwable -> L6c
        L55:
            r0 = r1
        L56:
            r7.delete()
        L59:
            if (r0 != 0) goto L5c
            goto L68
        L5c:
            int r7 = r6.b
            boolean r9 = r6.c
            int r6 = r6.e
            boolean r0 = B0(r2, r0, r6, r7, r9)
            if (r0 != 0) goto L69
        L68:
            return r1
        L69:
            int r5 = r5 + 1
            goto L15
        L6c:
            r0 = move-exception
            r7.delete()
            throw r0
        L71:
            android.graphics.Typeface r0 = C0(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.p21.o(android.content.Context, lz, android.content.res.Resources, int):android.graphics.Typeface");
    }

    @Override // defpackage.ma0
    public final Typeface p(Context context, oz[] ozVarArr, int i) {
        Object objNewInstance;
        int i2 = 0;
        try {
            objNewInstance = r.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance != null) {
            vv0 vv0Var = new vv0();
            int length = ozVarArr.length;
            while (true) {
                if (i2 >= length) {
                    Typeface typefaceC0 = C0(objNewInstance);
                    if (typefaceC0 != null) {
                        return Typeface.create(typefaceC0, i);
                    }
                } else {
                    oz ozVar = ozVarArr[i2];
                    Uri uri = ozVar.a;
                    ByteBuffer byteBufferS = (ByteBuffer) vv0Var.getOrDefault(uri, null);
                    if (byteBufferS == null) {
                        byteBufferS = o80.S(context, uri);
                        vv0Var.put(uri, byteBufferS);
                    }
                    if (byteBufferS == null) {
                        break;
                    }
                    if (!B0(objNewInstance, byteBufferS, ozVar.b, ozVar.c, ozVar.d)) {
                        break;
                    }
                    i2++;
                }
            }
        }
        return null;
    }
}
