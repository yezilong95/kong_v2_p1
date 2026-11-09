package defpackage;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xe implements sg0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ xe(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.sg0
    public final Bundle a() {
        Pair[] pairArr;
        Object obj = this.b;
        switch (this.a) {
            case 0:
                int i = ComponentActivity.u;
                Bundle bundle = new Bundle();
                ze zeVar = ((ComponentActivity) obj).m;
                zeVar.getClass();
                HashMap map = zeVar.b;
                bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(map.values()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(map.keySet()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(zeVar.d));
                bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) zeVar.g.clone());
                return bundle;
            default:
                o2 o2Var = (o2) obj;
                for (Map.Entry entry : o40.w0((LinkedHashMap) o2Var.e).entrySet()) {
                    String str = (String) entry.getKey();
                    ok0 ok0Var = (ok0) ((d80) entry.getValue());
                    ok0Var.getClass();
                    nm nmVar = oy.d;
                    Object obj2 = ok0.f.get(ok0Var);
                    if (obj2 == nmVar) {
                        obj2 = null;
                    }
                    o2Var.p(obj2, str);
                }
                for (Map.Entry entry2 : o40.w0((LinkedHashMap) o2Var.c).entrySet()) {
                    o2Var.p(((sg0) entry2.getValue()).a(), (String) entry2.getKey());
                }
                LinkedHashMap linkedHashMap = (LinkedHashMap) o2Var.b;
                if (linkedHashMap.isEmpty()) {
                    pairArr = new Pair[0];
                } else {
                    ArrayList arrayList = new ArrayList(linkedHashMap.size());
                    for (Map.Entry entry3 : linkedHashMap.entrySet()) {
                        arrayList.add(new Pair((String) entry3.getKey(), entry3.getValue()));
                    }
                    pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
                }
                return rp0.g((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        }
    }
}
