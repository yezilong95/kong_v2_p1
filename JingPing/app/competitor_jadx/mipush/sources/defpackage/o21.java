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
public class o21 extends ma0 {
    public static Class q = null;
    public static Constructor r = null;
    public static Method s = null;
    public static Method t = null;
    public static boolean u = false;

    public static boolean B0(Object obj, String str, int i, boolean z) {
        C0();
        try {
            return ((Boolean) s.invoke(obj, str, Integer.valueOf(i), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void C0() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        if (u) {
            return;
        }
        u = true;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        r = constructor;
        q = cls;
        s = method2;
        t = method;
    }

    @Override // defpackage.ma0
    public Typeface o(Context context, lz lzVar, Resources resources, int i) {
        C0();
        try {
            Object objNewInstance = r.newInstance(new Object[0]);
            for (mz mzVar : lzVar.a) {
                File fileC = o80.C(context);
                if (fileC == null) {
                    return null;
                }
                try {
                    if (!o80.o(fileC, resources, mzVar.f)) {
                        return null;
                    }
                    if (!B0(objNewInstance, fileC.getPath(), mzVar.b, mzVar.c)) {
                        return null;
                    }
                    fileC.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    fileC.delete();
                }
            }
            C0();
            try {
                Object objNewInstance2 = Array.newInstance((Class<?>) q, 1);
                Array.set(objNewInstance2, 0, objNewInstance);
                return (Typeface) t.invoke(null, objNewInstance2);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // defpackage.ma0
    public Typeface p(Context context, oz[] ozVarArr, int i) {
        String str;
        if (ozVarArr.length >= 1) {
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(A(i, ozVarArr).a, "r", null);
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    try {
                        try {
                            str = Os.readlink("/proc/self/fd/" + parcelFileDescriptorOpenFileDescriptor.getFd());
                        } finally {
                        }
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
