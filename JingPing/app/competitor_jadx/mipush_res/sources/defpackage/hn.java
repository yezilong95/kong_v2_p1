package defpackage;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.Lifecycle$Event;
import java.util.HashMap;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public final class hn implements v80 {
    public final /* synthetic */ int E;
    public final Object F;
    public final Object G;

    public /* synthetic */ hn(Object obj, int i, Object obj2) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
    }

    @Override // defpackage.v80
    public final void j(x80 x80Var, Lifecycle$Event lifecycle$Event) {
        switch (this.E) {
            case 0:
                int i = gn.a[lifecycle$Event.ordinal()];
                at atVar = (at) this.F;
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                    case 6:
                        break;
                    case 3:
                        (Build.VERSION.SDK_INT >= 28 ? bh.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new ia(4), 500L);
                        atVar.E.b(atVar);
                        break;
                    case 7:
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                v80 v80Var = (v80) this.G;
                if (v80Var != null) {
                    v80Var.j(x80Var, lifecycle$Event);
                    return;
                }
                return;
            case 1:
                if (lifecycle$Event == Lifecycle$Event.ON_START) {
                    ((s80) this.F).b(this);
                    ((i6) this.G).M();
                    return;
                }
                return;
            default:
                HashMap map = ((ff) this.G).a;
                List list = (List) map.get(lifecycle$Event);
                w80 w80Var = (w80) this.F;
                ff.a(list, x80Var, lifecycle$Event, w80Var);
                ff.a((List) map.get(Lifecycle$Event.ON_ANY), x80Var, lifecycle$Event, w80Var);
                return;
        }
    }

    public hn(w80 w80Var) {
        this.E = 2;
        this.F = w80Var;
        hf hfVar = hf.c;
        Class<?> cls = w80Var.getClass();
        ff ffVar = (ff) hfVar.a.get(cls);
        this.G = ffVar == null ? hfVar.a(cls, null) : ffVar;
    }
}
