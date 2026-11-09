package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppLocalesMetadataHolderService;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public abstract class k4 {
    public static final m6 b = new m6(new n6());
    public static final int c = -100;
    public static f30 d = null;
    public static f30 e = null;
    public static Boolean f = null;
    public static boolean g = false;
    public static final f7 h = new f7(0);
    public static final Object i = new Object();
    public static final Object j = new Object();

    public static boolean c(Context context) {
        if (f == null) {
            try {
                int i2 = AppLocalesMetadataHolderService.b;
                Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) AppLocalesMetadataHolderService.class), k6.a() | Uuid.SIZE_BITS).metaData;
                if (bundle != null) {
                    f = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                f = Boolean.FALSE;
            }
        }
        return f.booleanValue();
    }

    public static void f(x4 x4Var) {
        synchronized (i) {
            try {
                Iterator it = h.iterator();
                while (true) {
                    k40 k40Var = (k40) it;
                    if (k40Var.hasNext()) {
                        k4 k4Var = (k4) ((WeakReference) k40Var.next()).get();
                        if (k4Var == x4Var || k4Var == null) {
                            k40Var.remove();
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract void a();

    public abstract void b();

    public abstract void d();

    public abstract void e();

    public abstract boolean g(int i2);

    public abstract void h(int i2);

    public abstract void j(View view);

    public abstract void k(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void l(CharSequence charSequence);
}
