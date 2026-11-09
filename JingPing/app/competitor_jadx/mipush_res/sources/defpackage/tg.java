package defpackage;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.fragment.app.b;
import io.nats.client.Options;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class tg {
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public ArrayList d = new ArrayList();
    public final transient HashMap e = new HashMap();
    public final HashMap f = new HashMap();
    public final Bundle g = new Bundle();

    public final boolean a(int i, int i2, Intent intent) {
        String str = (String) this.a.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        a4 a4Var = (a4) this.e.get(str);
        if (a4Var != null) {
            b bVar = a4Var.a;
            if (this.d.contains(str)) {
                bVar.a(a4Var.b.t0(intent, i2));
                this.d.remove(str);
                return true;
            }
        }
        this.f.remove(str);
        this.g.putParcelable(str, new ActivityResult(intent, i2));
        return true;
    }

    public final i6 b(String str, l21 l21Var, b bVar) {
        int i;
        HashMap map;
        HashMap map2 = this.b;
        if (((Integer) map2.get(str)) == null) {
            int iNextInt = rm0.Default.nextInt(2147418112);
            while (true) {
                i = iNextInt + Options.DEFAULT_BUFFER_SIZE;
                map = this.a;
                if (!map.containsKey(Integer.valueOf(i))) {
                    break;
                }
                iNextInt = rm0.Default.nextInt(2147418112);
            }
            map.put(Integer.valueOf(i), str);
            map2.put(str, Integer.valueOf(i));
        }
        this.e.put(str, new a4(bVar, l21Var));
        HashMap map3 = this.f;
        if (map3.containsKey(str)) {
            Object obj = map3.get(str);
            map3.remove(str);
            bVar.a(obj);
        }
        Bundle bundle = this.g;
        ActivityResult activityResult = (ActivityResult) bundle.getParcelable(str);
        if (activityResult != null) {
            bundle.remove(str);
            bVar.a(l21Var.t0(activityResult.F, activityResult.E));
        }
        return new i6(this, 2, str);
    }
}
