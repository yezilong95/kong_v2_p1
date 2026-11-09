package defpackage;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Lifecycle$Event;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class dd0 implements s10 {
    public final /* synthetic */ int b;
    public final Object c;

    public /* synthetic */ dd0(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // defpackage.s10
    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        View view;
        switch (this.b) {
            case 0:
                if (lifecycle$Event != Lifecycle$Event.ON_CREATE) {
                    throw new AssertionError("Next event must be ON_CREATE");
                }
                u10Var.getLifecycle().b(this);
                vg0 vg0Var = (vg0) this.c;
                Bundle bundleD = vg0Var.b().d("androidx.savedstate.Restarter");
                if (bundleD == null) {
                    return;
                }
                ArrayList<String> stringArrayList = bundleD.getStringArrayList("classes_to_restore");
                if (stringArrayList == null) {
                    throw new IllegalStateException("SavedState with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
                }
                for (String str : stringArrayList) {
                    try {
                        Class<? extends U> clsAsSubclass = Class.forName(str, false, dd0.class.getClassLoader()).asSubclass(rg0.class);
                        oy.g(clsAsSubclass);
                        try {
                            Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                            declaredConstructor.setAccessible(true);
                            try {
                                Object objNewInstance = declaredConstructor.newInstance(new Object[0]);
                                oy.g(objNewInstance);
                                if (!(vg0Var instanceof ls0)) {
                                    throw new IllegalStateException(("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner. Received owner: " + vg0Var).toString());
                                }
                                ks0 ks0VarD = ((ls0) vg0Var).d();
                                e5 e5VarB = vg0Var.b();
                                ks0VarD.getClass();
                                LinkedHashMap linkedHashMap = ks0VarD.a;
                                for (String str2 : new HashSet(linkedHashMap.keySet())) {
                                    oy.j(str2, "key");
                                    gs0 gs0Var = (gs0) linkedHashMap.get(str2);
                                    if (gs0Var != null) {
                                        za0.e(gs0Var, e5VarB, vg0Var.getLifecycle());
                                    }
                                }
                                if (!new HashSet(linkedHashMap.keySet()).isEmpty()) {
                                    e5VarB.P();
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(hk.n("Failed to instantiate ", str), e);
                            }
                        } catch (NoSuchMethodException e2) {
                            throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                        }
                    } catch (ClassNotFoundException e3) {
                        throw new RuntimeException(hk.j("Class ", str, " wasn't found"), e3);
                    }
                }
                return;
            case 1:
                new HashMap();
                cv[] cvVarArr = (cv[]) this.c;
                if (cvVarArr.length > 0) {
                    cv cvVar = cvVarArr[0];
                    throw null;
                }
                if (cvVarArr.length <= 0) {
                    return;
                }
                cv cvVar2 = cvVarArr[0];
                throw null;
            case 2:
                if (lifecycle$Event != Lifecycle$Event.ON_STOP || (view = ((jt) this.c).F) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
                return;
            default:
                if (lifecycle$Event != Lifecycle$Event.ON_CREATE) {
                    throw new IllegalStateException(("Next event must be ON_CREATE, it was " + lifecycle$Event).toString());
                }
                u10Var.getLifecycle().b(this);
                ((pg0) this.c).b();
                return;
        }
    }
}
