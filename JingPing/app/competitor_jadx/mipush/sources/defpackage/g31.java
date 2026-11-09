package defpackage;

import com.google.gson.JsonIOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class g31 extends zn0 {
    public static Class d;
    public final Object b;
    public final Field c;

    public g31() {
        Object obj;
        Field declaredField = null;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            d = cls;
            Field declaredField2 = cls.getDeclaredField("theUnsafe");
            declaredField2.setAccessible(true);
            obj = declaredField2.get(null);
        } catch (Exception unused) {
            obj = null;
        }
        this.b = obj;
        try {
            declaredField = AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused2) {
        }
        this.c = declaredField;
    }

    @Override // defpackage.zn0
    public final void a(AccessibleObject accessibleObject) {
        Field field;
        Object obj = this.b;
        if (obj != null && (field = this.c) != null) {
            try {
                Long l = (Long) d.getMethod("objectFieldOffset", Field.class).invoke(obj, field);
                l.getClass();
                d.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(obj, accessibleObject, l, Boolean.TRUE);
                return;
            } catch (Exception unused) {
            }
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e) {
            throw new JsonIOException("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e);
        }
    }
}
