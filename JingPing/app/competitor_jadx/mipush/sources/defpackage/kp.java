package defpackage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class kp {
    public static final boolean a;
    public static final Method b;
    public static final Field c;
    public static final Field d;
    public static final Field e;
    public static final Field f;

    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    static {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            java.lang.String r3 = "android.graphics.Insets"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.NoSuchFieldException -> L3f java.lang.ClassNotFoundException -> L42 java.lang.NoSuchMethodException -> L45
            java.lang.Class<android.graphics.drawable.Drawable> r4 = android.graphics.drawable.Drawable.class
            java.lang.String r5 = "getOpticalInsets"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchFieldException -> L3f java.lang.ClassNotFoundException -> L42 java.lang.NoSuchMethodException -> L45
            java.lang.reflect.Method r4 = r4.getMethod(r5, r6)     // Catch: java.lang.NoSuchFieldException -> L3f java.lang.ClassNotFoundException -> L42 java.lang.NoSuchMethodException -> L45
            java.lang.String r5 = "left"
            java.lang.reflect.Field r5 = r3.getField(r5)     // Catch: java.lang.NoSuchFieldException -> L36 java.lang.ClassNotFoundException -> L39 java.lang.NoSuchMethodException -> L3c
            java.lang.String r6 = "top"
            java.lang.reflect.Field r6 = r3.getField(r6)     // Catch: java.lang.NoSuchFieldException -> L2f java.lang.ClassNotFoundException -> L32 java.lang.NoSuchMethodException -> L34
            java.lang.String r7 = "right"
            java.lang.reflect.Field r7 = r3.getField(r7)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r8 = "bottom"
            java.lang.reflect.Field r3 = r3.getField(r8)     // Catch: java.lang.Throwable -> L48
            r8 = r0
            goto L4a
        L2d:
            r7 = r1
            goto L48
        L2f:
            r6 = r1
        L30:
            r7 = r6
            goto L48
        L32:
            r6 = r1
            goto L30
        L34:
            r6 = r1
            goto L30
        L36:
            r5 = r1
        L37:
            r6 = r5
            goto L30
        L39:
            r5 = r1
        L3a:
            r6 = r5
            goto L30
        L3c:
            r5 = r1
        L3d:
            r6 = r5
            goto L30
        L3f:
            r4 = r1
            r5 = r4
            goto L37
        L42:
            r4 = r1
            r5 = r4
            goto L3a
        L45:
            r4 = r1
            r5 = r4
            goto L3d
        L48:
            r3 = r1
            r8 = r2
        L4a:
            if (r8 == 0) goto L59
            defpackage.kp.b = r4
            defpackage.kp.c = r5
            defpackage.kp.d = r6
            defpackage.kp.e = r7
            defpackage.kp.f = r3
            defpackage.kp.a = r0
            goto L65
        L59:
            defpackage.kp.b = r1
            defpackage.kp.c = r1
            defpackage.kp.d = r1
            defpackage.kp.e = r1
            defpackage.kp.f = r1
            defpackage.kp.a = r2
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kp.<clinit>():void");
    }
}
