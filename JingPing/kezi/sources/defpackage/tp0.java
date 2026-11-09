package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class tp0 extends rp0 {
    public static Class e = null;
    public static Constructor f = null;
    public static Method g = null;
    public static Method h = null;
    public static boolean i = false;

    public static boolean d0(Object obj, String str, int i2, boolean z) {
        e0();
        try {
            return ((Boolean) g.invoke(obj, str, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void e0() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        if (i) {
            return;
        }
        i = true;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi21Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        f = constructor;
        e = cls;
        g = method2;
        h = method;
    }

    @Override // defpackage.rp0
    public Typeface o(Context context, xs xsVar, Resources resources, int i2) {
        e0();
        try {
            Object objNewInstance = f.newInstance(new Object[0]);
            for (ys ysVar : xsVar.a) {
                File fileE = jq0.E(context);
                if (fileE == null) {
                    return null;
                }
                try {
                    if (!jq0.h(fileE, resources, ysVar.f)) {
                        return null;
                    }
                    if (!d0(objNewInstance, fileE.getPath(), ysVar.b, ysVar.c)) {
                        return null;
                    }
                    fileE.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    fileE.delete();
                }
            }
            e0();
            try {
                Object objNewInstance2 = Array.newInstance((Class<?>) e, 1);
                Array.set(objNewInstance2, 0, objNewInstance);
                return (Typeface) h.invoke(null, objNewInstance2);
            } catch (IllegalAccessException | InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override // defpackage.rp0
    public Typeface p(Context context, at[] atVarArr, int i2) {
        String str;
        if (atVarArr.length >= 1) {
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(u(i2, atVarArr).a, "r", null);
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    try {
                        try {
                            str = Os.readlink("/proc/self/fd/" + parcelFileDescriptorOpenFileDescriptor.getFd());
                        } catch (ErrnoException unused) {
                        }
                        File file = OsConstants.S_ISREG(Os.stat(str).st_mode) ? new File(str) : null;
                        if (file != null && file.canRead()) {
                            Typeface typefaceCreateFromFile = Typeface.createFromFile(file);
                            parcelFileDescriptorOpenFileDescriptor.close();
                            return typefaceCreateFromFile;
                        }
                        FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                        try {
                            Typeface typefaceQ = q(context, fileInputStream);
                            fileInputStream.close();
                            parcelFileDescriptorOpenFileDescriptor.close();
                            return typefaceQ;
                        } finally {
                        }
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
        return null;
    }
}
