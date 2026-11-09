package defpackage;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Lifecycle$Event;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class ym0 implements v80 {
    public final /* synthetic */ int E;
    public final Object F;

    public /* synthetic */ ym0(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // defpackage.v80
    public final void j(x80 x80Var, Lifecycle$Event lifecycle$Event) {
        AutoCloseable autoCloseable;
        View view;
        switch (this.E) {
            case 0:
                if (lifecycle$Event != Lifecycle$Event.ON_CREATE) {
                    throw new AssertionError("Next event must be ON_CREATE");
                }
                x80Var.getLifecycle().b(this);
                kr0 kr0Var = (kr0) this.F;
                Bundle bundleD = kr0Var.a().d("androidx.savedstate.Restarter");
                if (bundleD == null) {
                    return;
                }
                ArrayList<String> stringArrayList = bundleD.getStringArrayList("classes_to_restore");
                if (stringArrayList == null) {
                    throw new IllegalStateException("SavedState with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
                }
                for (String str : stringArrayList) {
                    try {
                        Class<? extends U> clsAsSubclass = Class.forName(str, false, ym0.class.getClassLoader()).asSubclass(hr0.class);
                        t40.k(clsAsSubclass);
                        try {
                            Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                            declaredConstructor.setAccessible(true);
                            try {
                                Object objNewInstance = declaredConstructor.newInstance(new Object[0]);
                                t40.k(objNewInstance);
                                if (!(kr0Var instanceof s51)) {
                                    throw new IllegalStateException(("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner. Received owner: " + kr0Var).toString());
                                }
                                r51 r51VarD = ((s51) kr0Var).d();
                                i6 i6VarA = kr0Var.a();
                                r51VarD.getClass();
                                LinkedHashMap linkedHashMap = r51VarD.a;
                                for (String str2 : new HashSet(linkedHashMap.keySet())) {
                                    t40.n(str2, "key");
                                    o51 o51Var = (o51) linkedHashMap.get(str2);
                                    if (o51Var != null) {
                                        s80 lifecycle = kr0Var.getLifecycle();
                                        t40.n(i6VarA, "registry");
                                        t40.n(lifecycle, "lifecycle");
                                        p51 p51Var = o51Var.a;
                                        if (p51Var != null) {
                                            synchronized (p51Var.a) {
                                                autoCloseable = (AutoCloseable) p51Var.b.get("androidx.lifecycle.savedstate.vm.tag");
                                            }
                                        } else {
                                            autoCloseable = null;
                                        }
                                    }
                                }
                                if (!new HashSet(linkedHashMap.keySet()).isEmpty()) {
                                    i6VarA.M();
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(zn.v("Failed to instantiate ", str), e);
                            }
                        } catch (NoSuchMethodException e2) {
                            throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                        }
                    } catch (ClassNotFoundException e3) {
                        throw new RuntimeException(zn.n("Class ", str, " wasn't found"), e3);
                    }
                }
                return;
            case 1:
                new HashMap();
                b20[] b20VarArr = (b20[]) this.F;
                if (b20VarArr.length > 0) {
                    b20 b20Var = b20VarArr[0];
                    throw null;
                }
                if (b20VarArr.length <= 0) {
                    return;
                }
                b20 b20Var2 = b20VarArr[0];
                throw null;
            case 2:
                if (lifecycle$Event != Lifecycle$Event.ON_STOP || (view = ((vz) this.F).i0) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
                return;
            default:
                if (lifecycle$Event != Lifecycle$Event.ON_CREATE) {
                    throw new IllegalStateException(("Next event must be ON_CREATE, it was " + lifecycle$Event).toString());
                }
                x80Var.getLifecycle().b(this);
                fr0 fr0Var = (fr0) this.F;
                if (fr0Var.b) {
                    return;
                }
                Bundle bundleD2 = fr0Var.a.d("androidx.lifecycle.internal.SavedStateHandlesProvider");
                Bundle bundleH = j31.h((Pair[]) Arrays.copyOf(new Pair[0], 0));
                Bundle bundle = fr0Var.c;
                if (bundle != null) {
                    bundleH.putAll(bundle);
                }
                if (bundleD2 != null) {
                    bundleH.putAll(bundleD2);
                }
                fr0Var.c = bundleH;
                fr0Var.b = true;
                return;
        }
    }
}
