package defpackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class vp0 extends tp0 {
    public final Class j;
    public final Constructor k;
    public final Method l;
    public final Method m;
    public final Method n;
    public final Method o;
    public final Method p;

    public vp0() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method methodK0;
        Method method2;
        Method method3;
        Method methodL0;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            methodK0 = k0(cls);
            Class cls2 = Integer.TYPE;
            method2 = cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
            method3 = cls.getMethod("freeze", new Class[0]);
            method = cls.getMethod("abortCreation", new Class[0]);
            methodL0 = l0(cls);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e.getClass().getName()), e);
            cls = null;
            method = null;
            constructor = null;
            methodK0 = null;
            method2 = null;
            method3 = null;
            methodL0 = null;
        }
        this.j = cls;
        this.k = constructor;
        this.l = methodK0;
        this.m = method2;
        this.n = method3;
        this.o = method;
        this.p = methodL0;
    }

    public static Method k0(Class cls) {
        Class cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public final void f0(Object obj) {
        try {
            this.o.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public final boolean g0(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.l.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface h0(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.j, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.p.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean i0(Object obj) {
        try {
            return ((Boolean) this.n.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final Object j0() {
        try {
            return this.k.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public Method l0(Class cls) throws NoSuchMethodException {
        Class<?> cls2 = Array.newInstance((Class<?>) cls, 1).getClass();
        Class cls3 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", cls2, cls3, cls3);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    @Override // defpackage.tp0, defpackage.rp0
    public final Typeface o(Context context, xs xsVar, Resources resources, int i) {
        Method method = this.l;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.o(context, xsVar, resources, i);
        }
        Object objJ0 = j0();
        if (objJ0 != null) {
            ys[] ysVarArr = xsVar.a;
            int length = ysVarArr.length;
            int i2 = 0;
            while (i2 < length) {
                ys ysVar = ysVarArr[i2];
                String str = ysVar.a;
                FontVariationAxis[] fontVariationAxisArrFromFontVariationSettings = FontVariationAxis.fromFontVariationSettings(ysVar.d);
                Context context2 = context;
                if (!g0(context2, objJ0, str, ysVar.e, ysVar.b, ysVar.c ? 1 : 0, fontVariationAxisArrFromFontVariationSettings)) {
                    f0(objJ0);
                    return null;
                }
                i2++;
                context = context2;
            }
            if (i0(objJ0)) {
                return h0(objJ0);
            }
        }
        return null;
    }

    @Override // defpackage.tp0, defpackage.rp0
    public final Typeface p(Context context, at[] atVarArr, int i) {
        Typeface typefaceH0;
        boolean zBooleanValue;
        if (atVarArr.length >= 1) {
            Method method = this.l;
            if (method == null) {
                Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
            }
            if (method != null) {
                HashMap map = new HashMap();
                for (at atVar : atVarArr) {
                    if (atVar.e == 0) {
                        Uri uri = atVar.a;
                        if (!map.containsKey(uri)) {
                            map.put(uri, jq0.L(context, uri));
                        }
                    }
                }
                Map mapUnmodifiableMap = Collections.unmodifiableMap(map);
                Object objJ0 = j0();
                if (objJ0 != null) {
                    int length = atVarArr.length;
                    int i2 = 0;
                    boolean z = false;
                    while (i2 < length) {
                        at atVar2 = atVarArr[i2];
                        ByteBuffer byteBuffer = (ByteBuffer) mapUnmodifiableMap.get(atVar2.a);
                        if (byteBuffer != null) {
                            try {
                                zBooleanValue = ((Boolean) this.m.invoke(objJ0, byteBuffer, Integer.valueOf(atVar2.b), null, Integer.valueOf(atVar2.c), Integer.valueOf(atVar2.d ? 1 : 0))).booleanValue();
                            } catch (IllegalAccessException | InvocationTargetException unused) {
                                zBooleanValue = false;
                            }
                            if (!zBooleanValue) {
                                f0(objJ0);
                                return null;
                            }
                            z = true;
                        }
                        i2++;
                        z = z;
                    }
                    if (!z) {
                        f0(objJ0);
                        return null;
                    }
                    if (i0(objJ0) && (typefaceH0 = h0(objJ0)) != null) {
                        return Typeface.create(typefaceH0, i);
                    }
                }
            } else {
                at atVarU = u(i, atVarArr);
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(atVarU.a, "r", null);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        try {
                            Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(atVarU.c).setItalic(atVarU.d).build();
                            parcelFileDescriptorOpenFileDescriptor.close();
                            return typefaceBuild;
                        } finally {
                        }
                    }
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return null;
                    }
                } catch (IOException unused2) {
                }
            }
        }
        return null;
    }

    @Override // defpackage.rp0
    public final Typeface r(Context context, Resources resources, int i, String str, int i2) {
        Method method = this.l;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.r(context, resources, i, str, i2);
        }
        Object objJ0 = j0();
        if (objJ0 != null) {
            if (!g0(context, objJ0, str, 0, -1, -1, null)) {
                f0(objJ0);
                return null;
            }
            if (i0(objJ0)) {
                return h0(objJ0);
            }
        }
        return null;
    }
}
