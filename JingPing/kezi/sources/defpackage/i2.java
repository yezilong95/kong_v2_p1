package defpackage;

import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.lifecycle.Lifecycle$Event;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class i2 implements s10 {
    public final /* synthetic */ String b;
    public final /* synthetic */ h9 c;
    public final /* synthetic */ h2 d;
    public final /* synthetic */ ze e;

    public i2(ze zeVar, String str, h9 h9Var, h2 h2Var) {
        this.e = zeVar;
        this.b = str;
        this.c = h9Var;
        this.d = h2Var;
    }

    @Override // defpackage.s10
    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        boolean zEquals = Lifecycle$Event.ON_START.equals(lifecycle$Event);
        String str = this.b;
        ze zeVar = this.e;
        if (!zEquals) {
            if (Lifecycle$Event.ON_STOP.equals(lifecycle$Event)) {
                zeVar.e.remove(str);
                return;
            } else {
                if (Lifecycle$Event.ON_DESTROY.equals(lifecycle$Event)) {
                    zeVar.e(str);
                    return;
                }
                return;
            }
        }
        HashMap map = zeVar.e;
        h9 h9Var = this.c;
        map.put(str, new l2(h9Var, this.d));
        HashMap map2 = zeVar.f;
        if (map2.containsKey(str)) {
            Object obj = map2.get(str);
            map2.remove(str);
            h9Var.b(obj);
        }
        Bundle bundle = zeVar.g;
        ActivityResult activityResult = (ActivityResult) bundle.getParcelable(str);
        if (activityResult != null) {
            bundle.remove(str);
            h9Var.b(new ActivityResult(activityResult.c, activityResult.b));
        }
    }
}
