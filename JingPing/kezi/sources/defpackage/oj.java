package defpackage;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.Lifecycle$Event;
import java.util.HashMap;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public final class oj implements s10 {
    public final /* synthetic */ int b;
    public final Object c;
    public final Object d;

    public /* synthetic */ oj(Object obj, int i, Object obj2) {
        this.b = i;
        this.c = obj;
        this.d = obj2;
    }

    @Override // defpackage.s10
    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        switch (this.b) {
            case 0:
                int i = nj.a[lifecycle$Event.ordinal()];
                in inVar = (in) this.c;
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                    case 6:
                        break;
                    case 3:
                        (Build.VERSION.SDK_INT >= 28 ? hf.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new kn(), 500L);
                        inVar.b.b(inVar);
                        break;
                    case 7:
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                s10 s10Var = (s10) this.d;
                if (s10Var != null) {
                    s10Var.a(u10Var, lifecycle$Event);
                    return;
                }
                return;
            case 1:
                if (lifecycle$Event == Lifecycle$Event.ON_START) {
                    ((p10) this.c).b(this);
                    ((e5) this.d).P();
                    return;
                }
                return;
            default:
                HashMap map = ((kd) this.d).a;
                List list = (List) map.get(lifecycle$Event);
                t10 t10Var = (t10) this.c;
                kd.a(list, u10Var, lifecycle$Event, t10Var);
                kd.a((List) map.get(Lifecycle$Event.ON_ANY), u10Var, lifecycle$Event, t10Var);
                return;
        }
    }

    public oj(t10 t10Var) {
        this.b = 2;
        this.c = t10Var;
        md mdVar = md.c;
        Class<?> cls = t10Var.getClass();
        kd kdVar = (kd) mdVar.a.get(cls);
        this.d = kdVar == null ? mdVar.a(cls, null) : kdVar;
    }
}
