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
public class q21 extends o21 {
    public final Method A;
    public final Method B;
    public final Class v;
    public final Constructor w;
    public final Method x;
    public final Method y;
    public final Method z;

    public q21() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method methodI0;
        Method method2;
        Method method3;
        Method methodJ0;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            methodI0 = I0(cls);
            Class cls2 = Integer.TYPE;
            method2 = cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
            method3 = cls.getMethod("freeze", new Class[0]);
            method = cls.getMethod("abortCreation", new Class[0]);
            methodJ0 = J0(cls);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e.getClass().getName()), e);
            cls = null;
            method = null;
            constructor = null;
            methodI0 = null;
            method2 = null;
            method3 = null;
            methodJ0 = null;
        }
        this.v = cls;
        this.w = constructor;
        this.x = methodI0;
        this.y = method2;
        this.z = method3;
        this.A = method;
        this.B = methodJ0;
    }

    public static Method I0(Class cls) {
        Class cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public final void D0(Object obj) {
        try {
            this.A.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public final boolean E0(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.x.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface F0(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.v, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.B.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean G0(Object obj) {
        try {
            return ((Boolean) this.z.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final Object H0() {
        try {
            return this.w.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public Method J0(Class cls) throws NoSuchMethodException {
        Class<?> cls2 = Array.newInstance((Class<?>) cls, 1).getClass();
        Class cls3 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", cls2, cls3, cls3);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    @Override // defpackage.o21, defpackage.ma0
    public final Typeface o(Context context, lz lzVar, Resources resources, int i) {
        Method method = this.x;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.o(context, lzVar, resources, i);
        }
        Object objH0 = H0();
        if (objH0 != null) {
            mz[] mzVarArr = lzVar.a;
            int length = mzVarArr.length;
            int i2 = 0;
            while (i2 < length) {
                mz mzVar = mzVarArr[i2];
                String str = mzVar.a;
                FontVariationAxis[] fontVariationAxisArrFromFontVariationSettings = FontVariationAxis.fromFontVariationSettings(mzVar.d);
                Context context2 = context;
                if (!E0(context2, objH0, str, mzVar.e, mzVar.b, mzVar.c ? 1 : 0, fontVariationAxisArrFromFontVariationSettings)) {
                    D0(objH0);
                    return null;
                }
                i2++;
                context = context2;
            }
            if (G0(objH0)) {
                return F0(objH0);
            }
        }
        return null;
    }

    @Override // defpackage.o21, defpackage.ma0
    public final Typeface p(Context context, oz[] ozVarArr, int i) {
        Typeface typefaceF0;
        boolean zBooleanValue;
        if (ozVarArr.length >= 1) {
            Method method = this.x;
            if (method == null) {
                Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
            }
            if (method != null) {
                HashMap map = new HashMap();
                for (oz ozVar : ozVarArr) {
                    if (ozVar.e == 0) {
                        Uri uri = ozVar.a;
                        if (!map.containsKey(uri)) {
                            map.put(uri, o80.S(context, uri));
                        }
                    }
                }
                Map mapUnmodifiableMap = Collections.unmodifiableMap(map);
                Object objH0 = H0();
                if (objH0 != null) {
                    int length = ozVarArr.length;
                    int i2 = 0;
                    boolean z = false;
                    while (i2 < length) {
                        oz ozVar2 = ozVarArr[i2];
                        ByteBuffer byteBuffer = (ByteBuffer) mapUnmodifiableMap.get(ozVar2.a);
                        if (byteBuffer != null) {
                            try {
                                zBooleanValue = ((Boolean) this.y.invoke(objH0, byteBuffer, Integer.valueOf(ozVar2.b), null, Integer.valueOf(ozVar2.c), Integer.valueOf(ozVar2.d ? 1 : 0))).booleanValue();
                            } catch (IllegalAccessException | InvocationTargetException unused) {
                                zBooleanValue = false;
                            }
                            if (!zBooleanValue) {
                                D0(objH0);
                                return null;
                            }
                            z = true;
                        }
                        i2++;
                        z = z;
                    }
                    if (!z) {
                        D0(objH0);
                        return null;
                    }
                    if (G0(objH0) && (typefaceF0 = F0(objH0)) != null) {
                        return Typeface.create(typefaceF0, i);
                    }
                }
            } else {
                oz ozVarA = A(i, ozVarArr);
                try {
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(ozVarA.a, "r", null);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        try {
                            Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(ozVarA.c).setItalic(ozVarA.d).build();
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

    @Override // defpackage.ma0
    public final Typeface r(Context context, Resources resources, int i, String str, int i2) {
        Method method = this.x;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        if (method == null) {
            return super.r(context, resources, i, str, i2);
        }
        Object objH0 = H0();
        if (objH0 != null) {
            if (!E0(context, objH0, str, 0, -1, -1, null)) {
                D0(objH0);
                return null;
            }
            if (G0(objH0)) {
                return F0(objH0);
            }
        }
        return null;
    }
}
