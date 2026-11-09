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
public abstract class o5 {
    public static final q7 E = new q7(new r7());
    public static final int F = -100;
    public static ja0 G = null;
    public static ja0 H = null;
    public static Boolean I = null;
    public static boolean J = false;
    public static final n8 K = new n8(0);
    public static final Object L = new Object();
    public static final Object M = new Object();

    public static boolean c(Context context) {
        if (I == null) {
            try {
                int i = AppLocalesMetadataHolderService.E;
                Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) AppLocalesMetadataHolderService.class), o7.a() | Uuid.SIZE_BITS).metaData;
                if (bundle != null) {
                    I = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                I = Boolean.FALSE;
            }
        }
        return I.booleanValue();
    }

    public static void g(c6 c6Var) {
        synchronized (L) {
            try {
                Iterator it = K.iterator();
                while (true) {
                    zb0 zb0Var = (zb0) it;
                    if (zb0Var.hasNext()) {
                        o5 o5Var = (o5) ((WeakReference) zb0Var.next()).get();
                        if (o5Var == c6Var || o5Var == null) {
                            zb0Var.remove();
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

    public abstract boolean h(int i);

    public abstract void i(int i);

    public abstract void j(View view);

    public abstract void k(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void l(CharSequence charSequence);
}
