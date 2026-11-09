package defpackage;

import android.graphics.Rect;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class lu0 extends ou0 {
    public static Field e = null;
    public static boolean f = false;
    public static Constructor g = null;
    public static boolean h = false;
    public WindowInsets c;
    public jy d;

    public lu0() {
        this.c = i();
    }

    private static WindowInsets i() {
        if (!f) {
            try {
                e = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException e2) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
            }
            f = true;
        }
        Field field = e;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException e3) {
                Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
            }
        }
        if (!h) {
            try {
                g = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException e4) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
            }
            h = true;
        }
        Constructor constructor = g;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException e5) {
                Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
            }
        }
        return null;
    }

    @Override // defpackage.ou0
    public xu0 b() {
        a();
        xu0 xu0VarH = xu0.h(null, this.c);
        jy[] jyVarArr = this.b;
        uu0 uu0Var = xu0VarH.a;
        uu0Var.o(jyVarArr);
        uu0Var.q(this.d);
        return xu0VarH;
    }

    @Override // defpackage.ou0
    public void e(jy jyVar) {
        this.d = jyVar;
    }

    @Override // defpackage.ou0
    public void g(jy jyVar) {
        WindowInsets windowInsets = this.c;
        if (windowInsets != null) {
            this.c = windowInsets.replaceSystemWindowInsets(jyVar.a, jyVar.b, jyVar.c, jyVar.d);
        }
    }

    public lu0(xu0 xu0Var) {
        super(xu0Var);
        this.c = xu0Var.g();
    }
}
